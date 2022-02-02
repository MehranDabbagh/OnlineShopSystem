package service.serviceImplementation;

import entity.Admin;
import entity.Product;
import repository.AdminRepository;
import repository.jdbc.AdminRepositoryImpl;
import service.AdminService;
import service.CategoryService;
import service.ProductService;

import java.util.List;

public class AdminServiceImpl implements AdminService {
    private  AdminRepositoryImpl adminRepository=new AdminRepositoryImpl();
    private ProductService productService;
    private CategoryService categoryService;
    public void AdminServiceImpl(AdminRepositoryImpl adminRepository, ProductService productService, CategoryService categoryService) {
        this.adminRepository=adminRepository;
        this.productService=productService;
        this.categoryService=categoryService;
    }

    @Override
    public Integer findByUsernameAndPassword(Admin admin) {
    return    adminRepository.findByUsernameAndPassword(admin);

    }

    @Override
    public Integer save(Admin entity) {
        return   adminRepository.save(entity);
    }
}
