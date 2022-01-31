package repository;

import entity.Category;

import java.util.Set;

public interface CategoryRepository extends CrudRepository{
    void findAllChildes(Category category);
}
