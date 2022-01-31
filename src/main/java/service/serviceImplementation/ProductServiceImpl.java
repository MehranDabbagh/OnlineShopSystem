package service.serviceImplementation;

import entity.Category;
import entity.Product;
import repository.jdbc.ProductRepositoryImpl;
import service.CategoryService;
import service.ProductService;

public class ProductServiceImpl  implements ProductService {
    private ProductRepositoryImpl productRepository=new ProductRepositoryImpl();
    private CategoryService categoryService;
    public void ProductServiceImpl(ProductRepositoryImpl productRepository, CategoryService categoryService) {
    this.productRepository=productRepository;
    this.categoryService=categoryService;
    }

    @Override
    public void update(Product product) {

    }

    @Override
    public void findAll() {

    }

    @Override
    public void findAllByCategory(Category category) {

    }

    @Override
    public Object save(Object entity) {
        return null;
    }
}
