package repository.jdbc;

import connection.PostgresConnection;
import entity.Admin;
import entity.Category;
import repository.CategoryRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CategoryRepositoryImpl implements CategoryRepository {
    Connection connection= PostgresConnection.getInstance().getConnection();
    @Override
    public List<Category> findAllChildes(Category category) {
        String sql="SELECT * from category where parentcategory=? ";
        List<Category> categories=new ArrayList<>();
        try{
            PreparedStatement preparedStatement=connection.prepareStatement(sql);
            preparedStatement.setString(1,category.getParentCategory().getName());
            ResultSet resultSet=preparedStatement.executeQuery();
            while(resultSet.next()){
                String sql1="select id from category where name=?";
                PreparedStatement preparedStatement1=connection.prepareStatement(sql1);
                preparedStatement1.setString(1,resultSet.getString("parentcategory"));
                ResultSet resultSet1=preparedStatement1.executeQuery();
                Category category1=new Category();category1.setId(resultSet1.getInt("id"));
                Category  category2=new Category(resultSet.getString("name"),category1);
                category.setId(resultSet.getInt("id"));
                categories.add(category2);
            }
            return categories;
        }catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }


    @Override
    public List<Category> findAll() {
        String sql="SELECT * from category ";
        List<Category> categories=new ArrayList<>();
        try{
            PreparedStatement preparedStatement=connection.prepareStatement(sql);

            ResultSet resultSet=preparedStatement.executeQuery();
            Category category;
            while(resultSet.next()){
                 String sql1="select id from category where name=?";
                 PreparedStatement preparedStatement1=connection.prepareStatement(sql1);
                 preparedStatement1.setString(1,resultSet.getString("parentcategory"));
                 ResultSet resultSet1=preparedStatement1.executeQuery();
                 Category category1=new Category();
                 category1.setId(resultSet1.getInt("id"));
                 category=new Category(resultSet.getString("name"),category1);
                 category.setId(resultSet.getInt("id"));
                 categories.add(category);
            }
            return categories;
        }catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Category findById(Integer id) {
        String sql="SELECT * from category where id=? ";
        try{
            PreparedStatement preparedStatement=connection.prepareStatement(sql);
            preparedStatement.setInt(1,id);
            ResultSet resultSet=preparedStatement.executeQuery();
            if(resultSet.next()){
                String sql1="select id from category where name=?";
                PreparedStatement preparedStatement1=connection.prepareStatement(sql1);
                preparedStatement1.setString(1,resultSet.getString("parentcategory"));
                ResultSet resultSet1=preparedStatement1.executeQuery();
                Category category1=new Category();category1.setId(resultSet1.getInt("id"));
                Category  category=new Category(resultSet.getString("name"),category1);
                category.setId(resultSet.getInt("id"));
                return category;
            }

        }catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Integer save(Category entity) {
        String sql="insert into category(label,parentcategory) values (?,?)";
        try{
            PreparedStatement preparedStatement=connection.prepareStatement(sql);
            preparedStatement.setString(1,entity.getName());
            preparedStatement.setString(2,entity.getParentCategory().getName());
            preparedStatement.executeUpdate();
            String sql1="select id from category where label=? and parentcategory=?";
            preparedStatement=connection.prepareStatement(sql1);
            preparedStatement.setString(1,entity.getName());
            preparedStatement.setString(2,entity.getParentCategory().getName());
            ResultSet resultSet=preparedStatement.executeQuery();
            if(resultSet.next()){
                return  resultSet.getInt("id");
            }return 0;
        }catch (SQLException e){

        }
        return null;
    }

    @Override
    public void update(Category entity) {
        String sql="select * from category where  id=?";
        try {
            PreparedStatement preparedStatement=connection.prepareStatement(sql);
            preparedStatement.setInt(1,entity.getId());
            ResultSet resultSet=preparedStatement.executeQuery();
            if(resultSet.next()){
                String sql1="update category set label=? ,parentcategory=? where id=?";
                PreparedStatement preparedStatement1=connection.prepareStatement(sql1);
                preparedStatement1.setString(1,entity.getName());
                preparedStatement1.setString(2,entity.getParentCategory().getName());
                preparedStatement1.setInt(3,entity.getId());
                preparedStatement1.executeUpdate();
            }

        }catch (SQLException e){
            e.printStackTrace();
        }

    }

    @Override
    public void deleteById(Integer id) {
        String sql="delete from category where id=?";
        try {
            PreparedStatement preparedStatement=connection.prepareStatement(sql);
            preparedStatement.setInt(1,id);
            preparedStatement.executeUpdate();
        }catch (SQLException e){}

    }
}
