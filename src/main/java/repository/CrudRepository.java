package repository;

import java.util.List;
import java.util.Set;

public interface CrudRepository <T,I> {
    List<T> findAll();
    T findById(I id);
    I save(T entity);
    void update(T entity);
    void deleteById(I id);
}
