package service.impl;

import entity.Category;
import entity.Product;
import repository.impl.ProductRepositoryImpl;
import service.ProductService;

import java.util.List;

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
