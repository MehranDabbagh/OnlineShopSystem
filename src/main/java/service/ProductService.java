package service;

import entity.Category;
import entity.Product;

import java.util.List;
import java.util.Set;

public interface ProductService extends Service<Product,Integer>{

    void update(Product product);
    List<Product> findAll();
    List<Product> findAllProductByCategory(Category category);
}
