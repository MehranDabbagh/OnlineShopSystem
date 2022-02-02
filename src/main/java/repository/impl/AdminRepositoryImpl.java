package repository.impl;

import entity.Admin;
import repository.AdminRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AdminRepositoryImpl implements AdminRepository {
    Connection connection;

    public AdminRepositoryImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public Integer findByUsernameAndPassword(Admin admin) {
        String sql="SELECT id from admins where username=? and password=? ";
        try{
            PreparedStatement preparedStatement=connection.prepareStatement(sql);
            preparedStatement.setString(1,admin.getUsername());
            preparedStatement.setString(2,admin.getPassword());
            ResultSet resultSet=preparedStatement.executeQuery();
            if(resultSet.next()){
                return resultSet.getInt("id");
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public List<Admin> findAll() {
        String sql="SELECT * from admins ";
        List<Admin> admins=new ArrayList<>();
        try{
            PreparedStatement preparedStatement=connection.prepareStatement(sql);

            ResultSet resultSet=preparedStatement.executeQuery();
            while(resultSet.next()){
             Admin admin=new Admin(resultSet.getString("username"),resultSet.getString("password"));
             admin.setId(resultSet.getInt("id"));
             admins.add(admin);
            }
            return admins;
        }catch (SQLException e){
            e.printStackTrace();
        }
  return null;
    }

    @Override
    public Admin findById(Integer id) {
        String sql="select * from admins where id=?";
        try {
            PreparedStatement preparedStatement=connection.prepareStatement(sql);
            preparedStatement.setInt(1,id);
            ResultSet result=preparedStatement.executeQuery();
            if(result.next()){
              Admin admin=new Admin(result.getString("username"),result.getString("password"));
              admin.setId(id);
              return admin;
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Integer save(Admin entity) {
        String sql="insert into admins(username,password) values (?,?)";
        try{
            PreparedStatement preparedStatement=connection.prepareStatement(sql);
            preparedStatement.setString(1,entity.getUsername());
            preparedStatement.setString(2,entity.getPassword());
            preparedStatement.executeUpdate();
            String sql1="select id from admins where username=? and password=?";
            preparedStatement=connection.prepareStatement(sql1);
            preparedStatement.setString(1,entity.getUsername());
            preparedStatement.setString(2,entity.getPassword());
            ResultSet resultSet=preparedStatement.executeQuery();
            if(resultSet.next()){
                return  resultSet.getInt("id");
            }return 0;
        }catch (SQLException e){

        }
        return null;
    }

    @Override
    public void update(Admin entity) {
String sql="select * from admins where  id=?";
try {
    PreparedStatement preparedStatement=connection.prepareStatement(sql);
    preparedStatement.setInt(1,entity.getId());
    ResultSet resultSet=preparedStatement.executeQuery();
    if(resultSet.next()){
        String sql1="update admins set username=? ,password=? where id=?";
        PreparedStatement preparedStatement1=connection.prepareStatement(sql1);
        preparedStatement1.setString(1,entity.getUsername());
        preparedStatement1.setString(2,entity.getPassword());
        preparedStatement1.setInt(3,resultSet.getInt("id"));
        preparedStatement1.executeUpdate();
    }

}catch (SQLException e){
    e.printStackTrace();
}
    }

    @Override
    public void deleteById(Integer id) {
        String sql="delete from admins where id=?";
        try {
            PreparedStatement preparedStatement=connection.prepareStatement(sql);
            preparedStatement.setInt(1,id);
            preparedStatement.executeUpdate();
        }catch (SQLException e){}

    }
}
