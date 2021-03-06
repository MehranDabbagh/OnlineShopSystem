package repository.impl;

import entity.Cart;
import entity.Customer;
import entity.Product;
import repository.CartRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CartRepositoryImpl implements CartRepository {
    Connection connection;

    public CartRepositoryImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public List<Cart> findAll() {
        String sql="SELECT * from cart ";
        List<Cart> carts=new ArrayList<>();
        try{
            PreparedStatement preparedStatement=connection.prepareStatement(sql);

            ResultSet resultSet=preparedStatement.executeQuery();
            Customer customer;
            while(resultSet.next()){
             customer= new Customer();
             customer.setId(resultSet.getLong("customerid"));
            Cart cart=new Cart(resultSet.getString("address"),resultSet.getLong("phonenumber"),resultSet.getBoolean("done"),customer);
            cart.setId(resultSet.getInt("id"));
            carts.add(cart);
            }
            return carts;
        }catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Cart findById(Integer id) {
        String sql="select * from cart where id=?";
        try {
            PreparedStatement preparedStatement=connection.prepareStatement(sql);
            preparedStatement.setInt(1,id);
            ResultSet resultSet=preparedStatement.executeQuery();
            if(resultSet.next()){
              Customer customer=new Customer();
              customer.setId(resultSet.getLong("customerid"));
                Cart cart=new Cart(resultSet.getString("address"),resultSet.getLong("phonenumber"),resultSet.getBoolean("done"),customer);
                cart.setId(id);
                return cart;
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Integer save(Cart entity) {
        String sql="insert into cart(address,phonenumber,done,customerid) values (?,?,?,?)";
        try{
            PreparedStatement preparedStatement=connection.prepareStatement(sql);
            preparedStatement.setString(1,entity.getAddress());
            preparedStatement.setLong(2,entity.getPhoneNumber());
            preparedStatement.setBoolean(3,entity.isDone());
            preparedStatement.setLong(4,entity.getCostumer().getId());
            preparedStatement.executeUpdate();
            String sql1="select * from cart where address=? and phonenumber=?  and done=? and customerid=?";
            preparedStatement=connection.prepareStatement(sql1);
            preparedStatement.setString(1,entity.getAddress());
            preparedStatement.setLong(2,entity.getPhoneNumber());
            preparedStatement.setBoolean(3,true);
            preparedStatement.setLong(4,entity.getCostumer().getId());
            ResultSet resultSet=preparedStatement.executeQuery();
            System.out.println(entity.getAddress());
            System.out.println(entity.getPhoneNumber());
            System.out.println(entity.getCostumer().getId());
            if(entity.getProductList()!=null && resultSet.next()) {
                System.out.println("here");
                for (Product product : entity.getProductList()) {
                    String insert = "insert into cartproducts (cartid,productid) values (?,?)";
                    PreparedStatement preparedStatement1 = connection.prepareStatement(insert);
                    preparedStatement1.setInt(1, resultSet.getInt("id"));
                    preparedStatement1.setInt(2, product.getId());
                    preparedStatement1.execute();
                }
            }
            if(resultSet.next()){
                return  resultSet.getInt("id");
            }return 0;
        }catch (SQLException e){

        }
        return null;
    }

    @Override
    public void update(Cart entity) {
        String sql="select * from cart where  id=?";
        try {
            PreparedStatement preparedStatement=connection.prepareStatement(sql);
            preparedStatement.setInt(1,entity.getId());
            ResultSet resultSet=preparedStatement.executeQuery();
            Integer updateCount=0;
            if(resultSet.next()){
                String sql1="update cart set address=? ,phonenumber=?,done=? ,customerid=? where id=?";
                PreparedStatement preparedStatement1=connection.prepareStatement(sql1);
                preparedStatement1.setString(1,entity.getAddress());
                preparedStatement1.setLong(2,entity.getPhoneNumber());
                preparedStatement1.setBoolean(3,entity.isDone());
                preparedStatement1.setLong(4,entity.getCostumer().getId());
                preparedStatement1.setInt(5,entity.getId());
               updateCount= preparedStatement1.executeUpdate();
            }
         List<Product> products=entity.getProductList();
            if(entity.getProductList()!=null ){
                for (Product product:entity.getProductList()) {
                    String selection="select stock from products where id=?";
                    PreparedStatement preparedStatement2=connection.prepareStatement(selection);
                    preparedStatement2.setInt(1,product.getId());
                    ResultSet resultSet1=preparedStatement2.executeQuery();
                    if(resultSet1.next()) {
                        if (resultSet1.getInt("stock") > 0) {
                            String insert = "insert into cartproducts (cartid,productid) values (?,?)";
                            PreparedStatement preparedStatement1 = connection.prepareStatement(insert);
                            preparedStatement1.setInt(1, entity.getId());
                            preparedStatement1.setInt(2, product.getId());
                            preparedStatement1.execute();
                            String update = "update products set stock=? where id=?";
                            PreparedStatement preparedStatement3 = connection.prepareStatement(update);
                            preparedStatement3.setInt(1, resultSet1.getInt("stock") - 1);
                            preparedStatement3.setInt(2, product.getId());
                            preparedStatement3.execute();
                        }
                    }
                }
            }

        }catch (SQLException e){
            e.printStackTrace();
        }

    }

    @Override
    public void deleteById(Integer id) {
        String sql="delete from cart where id=?";
        try {
            PreparedStatement preparedStatement=connection.prepareStatement(sql);
            preparedStatement.setInt(1,id);
            preparedStatement.executeUpdate();
        }catch (SQLException e){}

    }

}
