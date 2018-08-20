package dao;

import entity.Category;
import util.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CategoryDAO {
    //获取总数
    public int getTotal(){
        int total=0;
        String sql="select count ( * ) from category";
        try (Connection c=DBUtil.getConnection(); PreparedStatement ps=c.prepareStatement(sql)){

            ResultSet rs=ps.executeQuery();
            while (rs.next()){
                total=rs.getInt(1);
            }
            System.out.println(total);
        }catch (SQLException e){
            e.printStackTrace();
        }

        return total;
    }

    //增加
    public void add(Category category){
        String sql="insert into category values(null,?)";
        try (Connection c=DBUtil.getConnection(); PreparedStatement ps=c.prepareStatement(sql)){
            ps.setString(1,category.name);
            ps.execute();

            ResultSet rs=ps.getGeneratedKeys();
            if (rs.next()){
                category.id=rs.getInt(1);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    //更新
    public void update(Category category){
        String sql="update category set name =? where id=?";
        try (Connection c=DBUtil.getConnection();PreparedStatement ps=c.prepareStatement(sql)){
            ps.setString(1,category.name);
            ps.setInt(2,category.id);

            ps.execute();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    //删除
    public void delete(int id){
        String sql="delete from category where id=?";
        try (Connection c=DBUtil.getConnection();PreparedStatement ps=c.prepareStatement(sql)){
            ps.setInt(1,id);

            ps.execute();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    //获取
    public Category get(int id){
        Category category=null;
        String sql="select * from category where id=?";
        try (Connection c=DBUtil.getConnection();PreparedStatement ps=c.prepareStatement(sql)){
            ps.setInt(1,id);
            ResultSet rs=ps.executeQuery();

            while (rs.next()){
                category=new Category();

                category.id=id;
                category.name=rs.getString("name");
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return category;
    }
    //查询所有
    public List<Category> list(){
        return list(0,Integer.MAX_VALUE);
    }

    //分页查询所有
    public List<Category> list(int start,int count){
        List<Category> categories=new ArrayList<>();
        Category category=null;
        String sql="select * from category order by id desc limit ?,?";
        try (Connection c=DBUtil.getConnection(); PreparedStatement ps=c.prepareStatement(sql)){
            ps.setInt(1,start);
            ps.setInt(2,count);

            ResultSet rs=ps.executeQuery();
            while (rs.next()){
                category=new Category();

                category.id=rs.getInt(1);
                category.name=rs.getString("name");

                categories.add(category);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return categories;
    }
}
