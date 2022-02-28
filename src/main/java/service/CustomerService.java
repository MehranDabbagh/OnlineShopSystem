package service;

import entity.Cart;
import entity.Category;
import entity.Customer;
import entity.Product;

import java.util.List;
import java.util.Set;

public interface CustomerService extends Service<Customer,Long>{
    Customer findByUsernameAndPassword(Customer customer);

}
