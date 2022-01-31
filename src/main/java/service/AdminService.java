package service;

import entity.Admin;
import entity.Product;

import java.util.Set;

public interface AdminService extends Service {
    void findByUsernameAndPassword(Admin admin);
    Integer saveProduct(Product product);
    void updateProduct(Product product);
   void findAllProduct();


}
