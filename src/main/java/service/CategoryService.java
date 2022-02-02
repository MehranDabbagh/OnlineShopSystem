package service;

import entity.Category;

import java.util.List;

public interface CategoryService extends Service<Category,Integer>{
    List<Category> findAll();
    List<Category> findAllChildes(Category category);

}
