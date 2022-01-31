package repository.jdbc;

import entity.Customer;
import repository.CustomerRepository;

import java.util.List;

public class CustomerRepositoryImpl implements CustomerRepository {


    @Override
    public List<Customer> findAll() {
        return null;
    }

    @Override
    public Customer findById(Integer id) {
        return null;
    }

    @Override
    public Integer save(Customer entity) {
        return null;
    }

    @Override
    public void update(Customer entity) {

    }

    @Override
    public void deleteById(Integer id) {

    }

    @Override
    public void findByUsernameAndPassword(Customer customer) {

    }
}
