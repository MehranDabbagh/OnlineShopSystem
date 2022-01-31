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
    public void findAllChildes(Category category) {

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
if(resultSet1.next()){
    Category category1=findById(resultSet1.getInt("id"));
    category=new Category(resultSet.getString("name"),category1);
    category.setId(resultSet.getInt("id"));
}else {
    category=new Category(resultSet.getString("name"),null);
    category.setId(resultSet.getInt("id"));
}
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
        return null;
    }

    @Override
    public Integer save(Category entity) {
        return null;
    }

    @Override
    public void update(Category entity) {

    }

    @Override
    public void deleteById(Integer id) {

    }
}
