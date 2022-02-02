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
    private CustomerRepositoryImpl customerRepository=new CustomerRepositoryImpl();
    private CartRepositoryImpl cartRepository=new CartRepositoryImpl();
    private ProductService productService;
    private CategoryService categoryService;
    public void CustomerServiceImpl(CustomerRepositoryImpl customerRepository, CartRepositoryImpl cartRepository, ProductService productService, CategoryService categoryService) {
       this.customerRepository=customerRepository;
       this.cartRepository=cartRepository;
       this.productService=productService;
       this.categoryService=categoryService;
    }

    @Override
    public Customer findByUsernameAndPassword(Customer customer) {
      return customerRepository.findByUsernameAndPassword(customer);
    }

    @Override
    public List<Category> findAllCategories() {
       return categoryService.findAll();
    }

    @Override
    public List<Product> findAllProductsByCategory(Category category) {
       return productService.findAllByCategory(category);
    }

    @Override
    public Integer saveCart(Cart cart) {
         cartRepository.update(cart);
         return 0;
    }

    @Override
    public Integer save(Customer entity) {
       return  customerRepository.save(entity);
    }
}
