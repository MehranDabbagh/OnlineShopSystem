package service;

import entity.Category;
import entity.Product;

import java.util.Set;

public interface ProductService extends Service{

    void update(Product product);

    /**
     * @return
     */
    void findAll();

    /**
     * @param category
     * @return
     */
    void findAllByCategory(Category category);
}
