package General;

import java.util.List;

public interface IFileService<T> {
    List<T> readFileStatistics(String fileInPath);
    void writeFileStatistics(String fileOutPath, List<String> data);
}
