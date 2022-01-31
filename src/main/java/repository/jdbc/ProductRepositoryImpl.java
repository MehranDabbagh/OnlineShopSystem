package repository.jdbc;

import connection.PostgresConnection;
import entity.Admin;
import entity.Category;
import entity.Product;
import repository.ProductRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductRepositoryImpl implements ProductRepository {
    Connection connection= PostgresConnection.getInstance().getConnection();
    @Override
    public List<Product> findAll() {
        String sql="SELECT * from products ";
        List<Product> products=new ArrayList<>();
        try{
            PreparedStatement preparedStatement=connection.prepareStatement(sql);
            Product product;
            ResultSet resultSet=preparedStatement.executeQuery();
            while(resultSet.next()){
                Category parentcategory=new Category();
               parentcategory.setId(  resultSet.getInt("categoryid"));
                product= new Product(resultSet.getString("label"),resultSet.getInt("price"),
                     parentcategory );
                products.add(product);
            }
            return products;
        }catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Product findById(Integer id) {
        String sql="SELECT * from products ";

        try{
            PreparedStatement preparedStatement=connection.prepareStatement(sql);
            Product product;
            ResultSet resultSet=preparedStatement.executeQuery();
            if(resultSet.next()){
                Category parentcategory=new Category();
                parentcategory.setId(  resultSet.getInt("categoryid"));
                product= new Product(resultSet.getString("label"),resultSet.getInt("price"),
                        parentcategory );
                return product;
            }

        }catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Integer save(Product entity) {
        String sql="insert into product(label,price,category) values (?,?,?)";
        try{
            PreparedStatement preparedStatement=connection.prepareStatement(sql);
            preparedStatement.setString(1,entity.getName());
            preparedStatement.setInt(2,entity.getPrice());
            preparedStatement.setString(3,entity.getCategory().getName());
            preparedStatement.executeUpdate();
            String sql1="select id from product where label=? and price=? andcategory=?";
            preparedStatement=connection.prepareStatement(sql1);
            preparedStatement.setString(1,entity.getName());
            preparedStatement.setInt(2,entity.getPrice());
            preparedStatement.setString(3,entity.getCategory().getName());
            ResultSet resultSet=preparedStatement.executeQuery();
            if(resultSet.next()){
                return  resultSet.getInt("id");
            }return 0;
        }catch (SQLException e){

        }
        return null;
    }

    @Override
    public void update(Product entity) {
        String sql="select * from product where  id=?";
        try {
            PreparedStatement preparedStatement=connection.prepareStatement(sql);
            preparedStatement.setInt(1,entity.getId());
            ResultSet resultSet=preparedStatement.executeQuery();
            if(resultSet.next()){
                String sql1="update product set label=? ,price=?,parentcategory=? where id=?";
                PreparedStatement preparedStatement1=connection.prepareStatement(sql1);
                preparedStatement1.setString(1,entity.getName());
                preparedStatement1.setInt(2,entity.getPrice());
                preparedStatement1.setString(3,entity.getCategory().getName());
                preparedStatement1.setInt(4,resultSet.getInt("id"));
                preparedStatement1.executeUpdate();
            }

        }catch (SQLException e){
            e.printStackTrace();
        }

    }

    @Override
    public void deleteById(Integer id) {
        String sql="delete from product where id=?";
        try {
            PreparedStatement preparedStatement=connection.prepareStatement(sql);
            preparedStatement.setInt(1,id);
            preparedStatement.executeUpdate();
        }catch (SQLException e){}

    }
}
