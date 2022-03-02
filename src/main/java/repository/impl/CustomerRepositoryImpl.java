package repository.impl;

import connection.SessionFactorySingleton;
import entity.Customer;
import lombok.var;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import repository.CustomerRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CustomerRepositoryImpl implements CustomerRepository {

    private SessionFactory sessionFactory = SessionFactorySingleton.getInstance();

    @Override
    public List<Customer> findAll() {
        Session session = sessionFactory.openSession();
        Query q = session.createQuery("from Customer ");
        List<Customer> customers = (List<Customer>) q.getResultList();

        return customers;
    }

    @Override
    public Customer findById(Long id) {
        try (var session = sessionFactory.openSession()) {
            return session.find(Customer.class, id);
        }
    }

    @Override
    public Long save(Customer entity) {
        try (var session = sessionFactory.openSession()) {
            var transaction = session.beginTransaction();
            try {
                session.save(entity);
                transaction.commit();
                return entity.getId();
            } catch (Exception e) {
                transaction.rollback();
                throw e;
            }
        }
    }

    @Override
    public void update(Customer entity) {

        try (var session = sessionFactory.openSession()) {
            var transaction = session.beginTransaction();
            try {
                session.update(entity);
                transaction.commit();
            } catch (Exception e) {
                transaction.rollback();
                throw e;
            }
        }
    }

    @Override
    public void deleteById(Long id) {
        Customer customer=findById(id);
        try (var session = sessionFactory.openSession()) {
            var transaction = session.beginTransaction();
            try {
                session.delete(customer);
                transaction.commit();
            } catch (Exception e) {
                transaction.rollback();
                throw e;
            }
        }
    }

    @Override
    public Customer findByUsernameAndPassword(Customer customer) {
        Session session = sessionFactory.openSession();
        Query q = session.createQuery("from Customer where username = :name and password = :pass");
        q.setParameter("name",customer.getUsername());
        q.setParameter("pass",customer.getPassword());
        List<Customer> customers = (List<Customer>) q.getResultList();
        if(customers.size()>0)
        return customers.get(0);
        else return null;
    }
}
