package service.impl;

import entity.Admin;
import repository.impl.AdminRepositoryImpl;
import service.AdminService;

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
