package repository;

import entity.Category;

import java.util.Set;

public interface CategoryRepository extends CrudRepository <Category,Integer> {
    void findAllChildes(Category category);
}
