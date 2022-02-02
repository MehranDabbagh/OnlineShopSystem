package service.serviceImplementation;

import entity.Category;
import entity.Product;
import repository.jdbc.CategoryRepositoryImpl;
import repository.jdbc.ProductRepositoryImpl;
import service.CategoryService;
import service.ProductService;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ProductServiceImpl  implements ProductService {
    private ProductRepositoryImpl productRepository;
    public ProductServiceImpl(ProductRepositoryImpl productRepository) {
        this.productRepository = productRepository;
    }
    @Override
    public void update(Product product) {
productRepository.update(product);
    }

    @Override
    public List<Product> findAll() {
        return productRepository.findAll();
    }



    @Override
    public List<Product> findAllProductByCategory(Category category) {
        return productRepository.findAllProductByCategory(category);
    }

    @Override
    public Integer save(Product entity) {

        return productRepository.save(entity);
    }
}
