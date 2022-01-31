package repository;

import entity.Admin;

public interface AdminRepository  extends CrudRepository <Admin,Integer> {
    Integer findByUsernameAndPassword(Admin admin);

}
