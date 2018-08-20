package dao;

import entity.Config;
import util.DBUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ConfigDAO {
    //获取总数
    public int getTotal(){
        int total=0;
        String sql="select count ( * ) from config";
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
    public void add(Config config){
        String sql="insert into config values(null,?,?)";
        try (Connection c=DBUtil.getConnection(); PreparedStatement ps=c.prepareStatement(sql)){
            ps.setString(1,config.key);
            ps.setString(2,config.value);
            ps.execute();

            ResultSet rs=ps.getGeneratedKeys();
            if (rs.next()){
                config.id=rs.getInt(1);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    //更新
    public void update(Config config){
        String sql="update config set key_=? ,value=? where id=?";
        try (Connection c=DBUtil.getConnection();PreparedStatement ps=c.prepareStatement(sql)){
            ps.setString(1,config.key);
            ps.setString(2,config.value);
            ps.setInt(3,config.id);

            ps.execute();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    //删除
    public void delete(int id){
        String sql="delete from config where id=?";
        try (Connection c=DBUtil.getConnection();PreparedStatement ps=c.prepareStatement(sql)){
            ps.setInt(1,id);

            ps.execute();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    //获取
    public Config get(int id){
        Config config=null;
        String sql="select * from config where id=?";
        try (Connection c=DBUtil.getConnection();PreparedStatement ps=c.prepareStatement(sql)){
            ps.setInt(1,id);
            ResultSet rs=ps.executeQuery();

            while (rs.next()){
                config=new Config();

                config.id=id;
                config.key=rs.getString("key_");
                config.value=rs.getString("value");
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return config;
    }
    //查询所有
    public List<Config> lis(){
        return list(0,Integer.MAX_VALUE);
    }

    //分页查询所有
    public List<Config> list(int start,int count){
        List<Config> configs=new ArrayList<>();
        Config config=null;
        String sql="select * from config order by id desc limit ?,?";
        try (Connection c=DBUtil.getConnection();PreparedStatement ps=c.prepareStatement(sql)){
            ps.setInt(1,start);
            ps.setInt(2,count);

            ResultSet rs=ps.executeQuery();
            while (rs.next()){
                config.id=rs.getInt(1);
                config.key=rs.getString("key_");
                config.value=rs.getString("value");

                configs.add(config);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return configs;
    }

    //通过键获取
    public Config getByKey(String key){
        Config config=null;
        String sql="select * from config where key_ = ?";
        try (Connection c=DBUtil.getConnection();PreparedStatement ps=c.prepareStatement(sql)){
            ps.setString(1,key);

            ResultSet rs=ps.executeQuery();
            if (rs.next()){
                config=new Config();

                config.id=rs.getInt("id");
                config.key=key;
                config.value=rs.getString("value");
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return config;
    }
}
