package service;

import entity.Category;

public interface CategoryService extends Service{
    void findAll();
    void findAllChildes(Category category);
}
