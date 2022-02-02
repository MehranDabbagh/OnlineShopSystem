package service.impl;

import entity.Customer;
import repository.impl.CustomerRepositoryImpl;
import service.CustomerService;

public class CustomerServiceImpl  implements CustomerService {
    private CustomerRepositoryImpl customerRepository;

    public  CustomerServiceImpl(CustomerRepositoryImpl customerRepository) {
       this.customerRepository=customerRepository;
    }

    @Override
    public Customer findByUsernameAndPassword(Customer customer) {
      return customerRepository.findByUsernameAndPassword(customer);
    }

    @Override
    public Integer save(Customer entity) {
       return  customerRepository.save(entity);
    }
}
