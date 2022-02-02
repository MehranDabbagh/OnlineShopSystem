package repository;

import entity.Category;
import entity.Product;

import java.util.List;

public interface ProductRepository extends CrudRepository<Product,Integer> {
    List<Product> findAllProductByCategory(Category category);
}
