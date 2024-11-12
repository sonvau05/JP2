package General;

import java.util.List;

public interface IGeneral<T> {
    List<T> readFile(String filePath);
    void writeFile(String filePath, List<String> data);
}
