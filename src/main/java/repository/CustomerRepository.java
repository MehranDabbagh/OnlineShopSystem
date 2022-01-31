package repository;

import entity.Customer;

public interface CustomerRepository extends CrudRepository <Customer,Integer>{
    void findByUsernameAndPassword(Customer customer);
}
