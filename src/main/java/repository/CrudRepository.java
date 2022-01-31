package repository;

import java.util.Set;

public interface CrudRepository <T,I> {
    void findAll();
    T findById(I id);
    I save(T entity);
    void update(T entity);
    void deleteById(I id);
}
