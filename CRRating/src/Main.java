import Entity.CRStatistics;
import Entity.StatisticsView;
import Service.FileService;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        String fileInPathStatistics = System.getProperty("user.dir").replace("/", "\\") + "/data/statistics.view.txt";
        String fileOutPathStatistics = System.getProperty("user.dir").replace("/", "\\") + "/data/statistics.result.txt";
        FileService fileService = new FileService();
        List<StatisticsView> statisticsViews = fileService.readFileStatistics(fileInPathStatistics);

        //Data Analytics
        Map<CRStatistics, CRStatistics> dataCRS = statisticsViews.stream()
                .collect(Collectors.groupingBy(
                        cr -> new CRStatistics(cr.getId(), cr.getMonthOfDate(), cr.getYearOfDate()),
                        Collectors.collectingAndThen(
                                Collectors.toList(),
                                listCR -> {
                                    StatisticsView firstStatisticsView = listCR.get(0);
                                    int totalView = listCR.stream().mapToInt(StatisticsView::getView).sum();
                                    int totalAddToCart = listCR.stream().mapToInt(StatisticsView::getAddToCart).sum();
                                    int totalCheckOut = listCR.stream().mapToInt(StatisticsView::getCheckOut).sum();

                                    return new CRStatistics(
                                            firstStatisticsView.getId(),
                                            firstStatisticsView.getYearOfDate(),
                                            firstStatisticsView.getMonthOfDate(),
                                            totalCheckOut,
                                            totalAddToCart,
                                            totalView
                                    );
                                }
                        )
                ));

        List<String> outputData = new ArrayList<>();
        
        Set<String> existingMonths = statisticsViews.stream()
                .map(sv -> sv.getYearOfDate() + "-" + String.format("%02d", sv.getMonthOfDate()))
                .collect(Collectors.toSet());

        dataCRS.values().forEach(cr -> {
            double clickRate = cr.getTotalView() > 0 ? (double) cr.getTotalCheckOut() / cr.getTotalView() * 100 : 0;
            double addToCartRate = cr.getTotalView() > 0 ? (double) cr.getTotalAddToCart() / cr.getTotalView() * 100 : 0;
            double checkOutRate = cr.getTotalView() > 0 ? (double) cr.getTotalCheckOut() / cr.getTotalView() * 100 : 0;

            String formattedData = cr.getId() + ";" +
                    String.format("%.2f", clickRate) + ";" +
                    String.format("%.2f", addToCartRate) + ";" +
                    String.format("%.2f", checkOutRate) + ";" +
                    cr.getYear() + "-" + String.format("%02d", cr.getMonth());

            outputData.add(formattedData);
        });

        existingMonths.forEach(month -> {
            statisticsViews.stream()
                    .map(StatisticsView::getId)
                    .distinct()
                    .forEach(id -> {
                        if (dataCRS.values().stream().noneMatch(cr -> cr.getId() == id &&
                                (cr.getYear() + "-" + String.format("%02d", cr.getMonth())).equals(month))) {
                            outputData.add(id + ";;;" + month);
                        }
                    });
        });

        fileService.writeFileStatistics(fileOutPathStatistics, outputData);

        outputData.forEach(System.out::println);
    }
}


