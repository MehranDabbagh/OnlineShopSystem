package service;

import entity.Cart;
import entity.Category;
import entity.Customer;

import java.util.Set;

public interface CustomerService extends Service{
    void findByUsernameAndPassword(Customer customer);
    void findAllCategories();
    void findAllProductsByCategory(Category category);
    Integer saveCart(Cart cart);
}
