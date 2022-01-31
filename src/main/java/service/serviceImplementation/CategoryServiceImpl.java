package service.serviceImplementation;

import entity.Category;
import repository.jdbc.CategoryRepositoryImpl;
import service.CategoryService;

public class CategoryServiceImpl  implements CategoryService {
    private  CategoryRepositoryImpl categoryRepository=new CategoryRepositoryImpl() ;
    public void CategoryServiceImpl(CategoryRepositoryImpl categoryRepository) {
      this.categoryRepository=categoryRepository;
    }

    @Override
    public void findAll() {

    }

    @Override
    public void findAllChildes(Category category) {

    }

    @Override
    public Object save(Object entity) {
        return null;
    }
}
