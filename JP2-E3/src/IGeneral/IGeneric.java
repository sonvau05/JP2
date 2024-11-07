package IGeneral;

import java.util.List;

public interface IGeneric<T> {
    void add(T item);
    T findById(String id);
    List<T> getAll();
}




