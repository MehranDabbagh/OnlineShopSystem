package repository;

import entity.Customer;

public interface CustomerRepository extends CrudRepository{
    void findByUsernameAndPassword(Customer customer);
}
