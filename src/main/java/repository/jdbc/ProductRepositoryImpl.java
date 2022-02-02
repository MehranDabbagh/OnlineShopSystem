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
    Connection connection;

    public ProductRepositoryImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public List<Product> findAll() {
        String sql="SELECT * from products ";
        List<Product> products=new ArrayList<>();
        try{
            PreparedStatement preparedStatement=connection.prepareStatement(sql);
            Product product;
            ResultSet resultSet=preparedStatement.executeQuery();
            while(resultSet.next()){
                Category category=new Category();
                category.setId(  resultSet.getInt("categoryid"));
                product= new Product(resultSet.getString("label"),resultSet.getInt("price"),resultSet.getInt("stock"),
                        category );
                product.setId(resultSet.getInt("id"));
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
                product= new Product(resultSet.getString("label"),resultSet.getInt("price"),resultSet.getInt("stock"),
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
        String sql="insert into products (label,price,categoryid,stock) values (?,?,?,?)";
        try{
            PreparedStatement preparedStatement=connection.prepareStatement(sql);
            preparedStatement.setString(1,entity.getName());
            preparedStatement.setInt(2,entity.getPrice());
            preparedStatement.setInt(3,entity.getCategory().getId());
            preparedStatement.setInt(4,entity.getStock());
            preparedStatement.executeUpdate();
            String sql1="select id from products where label=? and price=? and categoryid=?";
            preparedStatement=connection.prepareStatement(sql1);
            preparedStatement.setString(1,entity.getName());
            preparedStatement.setInt(2,entity.getPrice());
            preparedStatement.setInt(3,entity.getCategory().getId());
            ResultSet resultSet=preparedStatement.executeQuery();
            if(resultSet.next()){
                return  resultSet.getInt("id");
            }
            return 0;
        }catch (SQLException e){
e.printStackTrace();
        }
        return null;
    }

    @Override
    public void update(Product entity) {
        String sql="select * from products where  id=?";
        try {
            PreparedStatement preparedStatement=connection.prepareStatement(sql);
            preparedStatement.setInt(1,entity.getId());
            ResultSet resultSet=preparedStatement.executeQuery();
            if(resultSet.next()){
                String sql1="update products set label=? ,price=?, categoryid=?,stock=? where id=?";
                PreparedStatement preparedStatement1=connection.prepareStatement(sql1);
                preparedStatement1.setString(1,entity.getName());
                preparedStatement1.setInt(2,entity.getPrice());
                preparedStatement1.setInt(3,entity.getCategory().getId());
                preparedStatement1.setInt(4,entity.getStock());
                preparedStatement1.setInt(5,resultSet.getInt("id"));
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

    @Override
    public List<Product> findAllProductByCategory(Category category) {
        String sql="WITH RECURSIVE q AS (\n" +
                "        SELECT  *\n" +
                "        FROM    category \n" +
                "        WHERE   parentcategoryid = ? -- this condition defines the ultimate ancestors in your chain, change it as appropriate\n" +
                "        UNION ALL\n" +
                "        SELECT  m.*\n" +
                "        FROM    category m\n" +
                "        JOIN    q\n" +
                "        ON      m.parent_ID = q.id\n" +
                "        )\n" +
                "SELECT  *\n" +
                "FROM    q;";
        List<Category> categories=new ArrayList<>();
        try{
            PreparedStatement preparedStatement=connection.prepareStatement(sql);
            preparedStatement.setInt(1,category.getParentCategory().getId());
            ResultSet resultSet=preparedStatement.executeQuery();
            while(resultSet.next()){
                Category category1=new Category();category1.setId(resultSet.getInt("id"));
                Category  category2=new Category(resultSet.getString("name"),category1);
                category.setId(resultSet.getInt("id"));
                categories.add(category2);
            }
        List<Product> products=findAll();
        List<Product> finalProducts=new ArrayList<>();
            for (Product product:products
                 ) {
                for (Category category1:categories
                     ) {
                    if(product.getCategory()==category1){
                        finalProducts.add(product);
                    }
                }
            }
            return finalProducts;

        }catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }
}
