package service.impl;

import entity.Category;
import repository.impl.CategoryRepositoryImpl;
import service.CategoryService;

import java.util.List;

public class CategoryServiceImpl  implements CategoryService {
    private  CategoryRepositoryImpl categoryRepository ;
    public  CategoryServiceImpl(CategoryRepositoryImpl categoryRepository) {
      this.categoryRepository=categoryRepository;
    }
    @Override
    public List<Category> findAll() {
       return categoryRepository.findAll();
    }

    @Override
    public List<Category> findAllChildes(Category category) {
        return categoryRepository.findAllChildes(category);
    }
    @Override
    public Integer save(Category entity) {
     return categoryRepository.save(entity);
    }
}
