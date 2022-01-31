package repository;

import entity.Customer;

public interface CustomerRepository extends CrudRepository <Customer,Integer>{
    Customer findByUsernameAndPassword(Customer customer);
}
