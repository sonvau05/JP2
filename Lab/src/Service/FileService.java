package Service;

import Entity.Product;
import General.IGeneral;

import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class FileService implements IGeneral<Product> {

    @Override
    public List<Product> readFile(String filePath) {
        List<Product> products = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(";");
                int productId = Integer.parseInt(parts[0].trim());
                int clicks = Integer.parseInt(parts[1].trim());
                int addToCart = Integer.parseInt(parts[2].trim());
                int checkout = Integer.parseInt(parts[3].trim());
                LocalDate date = LocalDate.parse(parts[4].trim(), formatter);

                products.add(new Product(productId, clicks, addToCart, checkout, date));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return products;
    }

    @Override
    public void writeFile(String filePath, List<String> data) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filePath))) {
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
