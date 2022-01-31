package repository;

import entity.Admin;

public interface AdminRepository  extends CrudRepository{
    void findByUsernameAndPassword(Admin admin);

}
