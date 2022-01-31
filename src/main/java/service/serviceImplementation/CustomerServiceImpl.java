package service.serviceImplementation;

import entity.Cart;
import entity.Category;
import entity.Customer;
import repository.jdbc.CustomerRepositoryImpl;
import service.CartService;
import service.CategoryService;
import service.CustomerService;
import service.ProductService;

public class CustomerServiceImpl  implements CustomerService {
    private CustomerRepositoryImpl customerRepository=new CustomerRepositoryImpl();
    private CartService cartService;
    private ProductService productService;
    private CategoryService categoryService;
    public void CustomerServiceImpl(CustomerRepositoryImpl customerRepository, CartService cartService, ProductService productService, CategoryService categoryService) {
       this.customerRepository=customerRepository;
       this.cartService=cartService;
       this.productService=productService;
       this.categoryService=categoryService;
    }

    @Override
    public void findByUsernameAndPassword(Customer customer) {

    }

    @Override
    public void findAllCategories() {

    }

    @Override
    public void findAllProductsByCategory(Category category) {

    }

    @Override
    public Integer saveCart(Cart cart) {
        return null;
    }

    @Override
    public Object save(Object entity) {
        return null;
    }
}
