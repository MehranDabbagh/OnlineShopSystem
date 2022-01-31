package repository.jdbc;

import connection.PostgresConnection;
import entity.Admin;
import entity.Customer;
import repository.CustomerRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CustomerRepositoryImpl implements CustomerRepository {

    Connection connection= PostgresConnection.getInstance().getConnection();
    @Override
    public List<Customer> findAll() {
        String sql="SELECT * from customer ";
        List<Customer> customers=new ArrayList<>();
        try{
            PreparedStatement preparedStatement=connection.prepareStatement(sql);

            ResultSet resultSet=preparedStatement.executeQuery();
            Customer customer;
            while(resultSet.next()){
       customer=new Customer(resultSet.getString("username"),resultSet.getString("password"),
               resultSet.getString("firstname"),resultSet.getString("lastname"),
               resultSet.getLong("nationalcode"),resultSet.getString("nationalcode"),
               resultSet.getLong("phonenumber"));
       customers.add(customer);
            }
            return customers;
        }catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Customer findById(Integer id) {
        String sql="select * from customer where id=?";
        try {
            PreparedStatement preparedStatement=connection.prepareStatement(sql);
            preparedStatement.setInt(1,id);
            ResultSet resultSet=preparedStatement.executeQuery();
            if(resultSet.next()){
               Customer customer=new Customer(resultSet.getString("username"),resultSet.getString("password"),
                        resultSet.getString("firstname"),resultSet.getString("lastname"),
                        resultSet.getLong("nationalcode"),resultSet.getString("nationalcode"),
                        resultSet.getLong("phonenumber"));
               customer.setId(id);
                return customer;
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Integer save(Customer entity) {
        String sql="insert into customer(firstname,lastname,username,password,nationalcode,email,phonenumber) values (?,?,?,?,?,?,?)";
        try{
            PreparedStatement preparedStatement=connection.prepareStatement(sql);
            preparedStatement.setString(1,entity.getFirstName());
            preparedStatement.setString(2,entity.getLastName());
            preparedStatement.setString(3,entity.getUsername());
            preparedStatement.setString(4,entity.getPassword());
            preparedStatement.setLong(5,entity.getNationalCode());
            preparedStatement.setString(6,entity.getEmail());
            preparedStatement.setLong(7,entity.getPhoneNumber());
            preparedStatement.executeUpdate();
            String sql1="select id from customer where nationalcode=?";
            preparedStatement=connection.prepareStatement(sql1);
            preparedStatement.setLong(1,entity.getNationalCode());
            ResultSet resultSet=preparedStatement.executeQuery();
            if(resultSet.next()){
                return  resultSet.getInt("id");
            }return 0;
        }catch (SQLException e){

        }
        return null;
    }

    @Override
    public void update(Customer entity) {
        String sql="select * from customer where  id=?";
        try {
            PreparedStatement preparedStatement=connection.prepareStatement(sql);
            preparedStatement.setInt(1,entity.getId());
            ResultSet resultSet=preparedStatement.executeQuery();
            if(resultSet.next()){
                String sql1="update customer set  firstname=?,password=?,username=? ,password=?,nationalcode=?,email=?,phonenumber=? where id=?";
                PreparedStatement preparedStatement1=connection.prepareStatement(sql1);
                preparedStatement1.setString(1,entity.getFirstName());
                preparedStatement1.setString(2,entity.getLastName());
                preparedStatement1.setString(3,entity.getUsername());
                preparedStatement1.setString(4,entity.getPassword());
                preparedStatement1.setLong(5,entity.getNationalCode());
                preparedStatement1.setString(6,entity.getEmail());
                preparedStatement1.setLong(7,entity.getPhoneNumber());
                preparedStatement1.setInt(8,entity.getId());
                preparedStatement1.executeUpdate();
            }

        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    @Override
    public void deleteById(Integer id) {
        String sql="delete from customer where id=?";
        try {
            PreparedStatement preparedStatement=connection.prepareStatement(sql);
            preparedStatement.setInt(1,id);
            preparedStatement.executeUpdate();
        }catch (SQLException e){}
    }

    @Override
    public Customer findByUsernameAndPassword(Customer customer) {
        String sql="select * from customer where username=? and password=?";
        try {
            PreparedStatement preparedStatement=connection.prepareStatement(sql);
            preparedStatement.setString(1,customer.getUsername());
            preparedStatement.setString(2,customer.getPassword());
            ResultSet resultSet=preparedStatement.executeQuery();
            if(resultSet.next()){
                Customer customer1=new Customer(resultSet.getString("username"),resultSet.getString("password"),
                        resultSet.getString("firstname"),resultSet.getString("lastname"),
                        resultSet.getLong("nationalcode"),resultSet.getString("nationalcode"),
                        resultSet.getLong("phonenumber"));
                customer1.setId(resultSet.getInt("id"));
                return customer1;
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }
}
