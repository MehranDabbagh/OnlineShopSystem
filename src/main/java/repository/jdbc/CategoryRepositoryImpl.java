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
    Connection connection;

    public CategoryRepositoryImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public List<Category> findAllChildes(Category category) {
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
                 String sql1="select * from category where id=?";
                 PreparedStatement preparedStatement1=connection.prepareStatement(sql1);
                 preparedStatement1.setInt(1,resultSet.getInt("parentcategoryid"));
                 ResultSet resultSet1=preparedStatement1.executeQuery();
                 Category category1=new Category();
                 if(resultSet1.next()) {
                     category1.setId(resultSet1.getInt("id"));
                     category = new Category(resultSet.getString("label"), category1);
                     category.setId(resultSet.getInt("id"));
                     categories.add(category);
                 }else {
                     category1.setId(0);
                     category = new Category(resultSet.getString("label"), category1);
                     category.setId(resultSet.getInt("id"));
                     categories.add(category);
                 }
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
                String sql1="select id from category where id=?";
                PreparedStatement preparedStatement1=connection.prepareStatement(sql1);
                preparedStatement1.setInt(1,resultSet.getInt("parentcategoryid"));
                ResultSet resultSet1=preparedStatement1.executeQuery();
                if(resultSet1.next()) {
                    Category category1 = new Category();
                    category1.setId(resultSet1.getInt("id"));
                    Category category = new Category(resultSet.getString("label"), category1);
                    category.setId(resultSet.getInt("id"));
                    return category;
                }else{
                    Category category1 = new Category();
                    category1.setId(0);
                    Category category = new Category(resultSet.getString("label"), category1);
                    category.setId(resultSet.getInt("id"));
                    return category;
                }
            }

        }catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Integer save(Category entity) {

        String sql="insert into category(label,parentcategoryid) values (?,?)";
        try{
            PreparedStatement preparedStatement=connection.prepareStatement(sql);
            preparedStatement.setString(1,entity.getName());
            preparedStatement.setInt(2,entity.getParentCategory().getId());
            preparedStatement.execute();
            String sql1="select id from category where label=? and parentcategoryid=?";
            preparedStatement=connection.prepareStatement(sql1);
            preparedStatement.setString(1,entity.getName());
            preparedStatement.setInt(2,entity.getParentCategory().getId());
            ResultSet resultSet=preparedStatement.executeQuery();
            if(resultSet.next()){
                return  resultSet.getInt("id");
            }return 0;
        }catch (SQLException e){
        e.printStackTrace();

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
