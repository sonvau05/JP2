package Interface;

import java.util.List;
import java.util.Map;

public interface IGeneral<T> {
    List<T> getAll();
    void add(T item);
    Map<String, List<T>> groupBy(String attribute);
}

