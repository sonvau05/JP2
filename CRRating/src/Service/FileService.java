package Service;

import Entity.StatisticsView;
import General.IFileService;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class FileService implements IFileService<StatisticsView> {
    public FileService() {;}

    @Override
    public List<StatisticsView> readFileStatistics(String fileInPath) {
        List<StatisticsView> statisticsViews = new ArrayList<>();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fileInPath))) {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                if (!line.isEmpty()) {
                    String[] arrData = line.split(";");
                    int id = Integer.parseInt(arrData[0].trim());
                    int view = Integer.parseInt(arrData[1].trim());
                    int addToCart = Integer.parseInt(arrData[2].trim());
                    int checkOut = Integer.parseInt(arrData[3].trim());
                    LocalDate createAtDate = LocalDate.parse(arrData[4].trim());

                    statisticsViews.add(new StatisticsView(id, createAtDate, checkOut, addToCart, view));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return statisticsViews;
    }

    @Override
    public void writeFileStatistics(String fileOutPath, List<String> data) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(fileOutPath))) {
            for (String stat : data) {
                bw.write(stat);
                bw.newLine();
            }
            bw.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
