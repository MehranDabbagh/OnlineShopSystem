package service;

import entity.Admin;
import entity.Product;

import java.util.List;
import java.util.Set;

public interface AdminService extends Service<Admin,Integer> {
    Integer findByUsernameAndPassword(Admin admin);



}
