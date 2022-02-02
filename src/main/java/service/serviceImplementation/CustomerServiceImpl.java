package service.serviceImplementation;

import entity.Cart;
import entity.Category;
import entity.Customer;
import entity.Product;
import repository.jdbc.CartRepositoryImpl;
import repository.jdbc.CustomerRepositoryImpl;
import service.CartService;
import service.CategoryService;
import service.CustomerService;
import service.ProductService;

import java.util.List;

public class CustomerServiceImpl  implements CustomerService {
    private CustomerRepositoryImpl customerRepository;

    public  CustomerServiceImpl(CustomerRepositoryImpl customerRepository) {
       this.customerRepository=customerRepository;
    }

    @Override
    public Customer findByUsernameAndPassword(Customer customer) {
      return customerRepository.findByUsernameAndPassword(customer);
    }

    @Override
    public Integer save(Customer entity) {
       return  customerRepository.save(entity);
    }
}
