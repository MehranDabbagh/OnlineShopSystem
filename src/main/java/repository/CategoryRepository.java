package repository;

import entity.Category;

import java.util.List;
import java.util.Set;

public interface CategoryRepository extends CrudRepository <Category,Integer> {
    List<Category> findAllChildes(Category category);
}
