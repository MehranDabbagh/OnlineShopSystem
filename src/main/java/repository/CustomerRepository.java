package repository;

import entity.Customer;

public interface CustomerRepository extends CrudRepository <Customer,Long>{
    Customer findByUsernameAndPassword(Customer customer);
}
