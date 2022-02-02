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
    private  AdminRepositoryImpl adminRepository;



    public  AdminServiceImpl(AdminRepositoryImpl adminRepository) {
        this.adminRepository=adminRepository;
    }

    @Override
    public Integer findByUsernameAndPassword(Admin admin) {
    return    adminRepository.findByUsernameAndPassword(admin);}

    @Override
    public Integer save(Admin entity) {
        return   adminRepository.save(entity);
    }
}
