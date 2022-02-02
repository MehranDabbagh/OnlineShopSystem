package service;

import entity.Cart;
import entity.Category;
import entity.Customer;
import entity.Product;

import java.util.List;
import java.util.Set;

public interface CustomerService extends Service<Customer,Integer>{
    Customer findByUsernameAndPassword(Customer customer);
    List<Category> findAllCategories();
    List<Product> findAllProductsByCategory(Category category);
    Integer saveCart(Cart cart);
}
