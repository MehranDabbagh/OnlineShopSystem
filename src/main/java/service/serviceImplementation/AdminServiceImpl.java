package service.serviceImplementation;

import entity.Admin;
import entity.Product;
import repository.AdminRepository;
import repository.jdbc.AdminRepositoryImpl;
import service.AdminService;
import service.CategoryService;
import service.ProductService;

public class AdminServiceImpl implements AdminService {
    private  AdminRepositoryImpl repository=new AdminRepositoryImpl();
    private ProductService productService;
    private CategoryService categoryService;
    public void AdminServiceImpl(AdminRepositoryImpl repository, ProductService productService, CategoryService categoryService) {
        this.repository=repository;
        this.productService=productService;
        this.categoryService=categoryService;
    }

    @Override
    public void findByUsernameAndPassword(Admin admin) {

    }

    @Override
    public Integer saveProduct(Product product) {
        return null;
    }

    @Override
    public void updateProduct(Product product) {

    }

    @Override
    public void findAllProduct() {

    }

    @Override
    public Object save(Object entity) {
        return null;
    }
}
