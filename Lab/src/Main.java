import Entity.Product;
import Entity.ProductStatistics;
import Service.FileService;

import java.time.LocalDate;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        String inputFilePath = "data/input.txt";
        String outputFilePath = "data/output.txt";

        FileService fileService = new FileService();

        List<Product> products = fileService.readFile(inputFilePath);

        Map<Integer, ProductStatistics> statisticsMap = new HashMap<>();
        LocalDate startDate = LocalDate.of(2024, 2, 1);
        LocalDate endDate = LocalDate.of(2024, 3, 1);

        for (Product product : products) {
            if (product.getDate().isAfter(startDate.minusDays(1)) && product.getDate().isBefore(endDate.plusDays(1))) {
                statisticsMap.putIfAbsent(product.getProductId(), new ProductStatistics(product.getProductId()));
                statisticsMap.get(product.getProductId()).updateStatistics(product);
            }
        }

        List<String> statisticsList = new ArrayList<>();
        int totalClicks = statisticsMap.values().stream().mapToInt(ProductStatistics::getTotalClicks).sum();

        for (ProductStatistics stats : statisticsMap.values()) {
            statisticsList.add(stats.toString(totalClicks));
        }

        fileService.writeFile(outputFilePath, statisticsList);

        for (String stat : statisticsList) {
            System.out.println(stat);
        }

        ProductStatistics bestProduct = Collections.max(statisticsMap.values(), Comparator.comparingDouble(ps -> ps.getClickPercentage(totalClicks)));
        System.out.println("\nBest product for marketing strategy: Product ID " + bestProduct.getProductId());
    }
}
