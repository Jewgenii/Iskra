/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import util.DbUtil;
import model.Skisql;
import java.sql.Date;
import java.sql.Types;

/**
 *
 * @author Sergey Nikonenko
 */
public class SkisqlDao {

    private Connection connection;

    public SkisqlDao() {
        connection = DbUtil.getConnection();
    }

    public void addSkisql(Skisql skisql) {
        try {
            PreparedStatement preparedStatement = connection
                    .prepareStatement("insert into clippersql.skisql(nzak,osd_t,osd_c,osd_r,kiz,pri,nvp,naim,nizv,\"user\",datez,user_ip) values(?,?,?,?,?,?,?,?,?,default,default,default)");
            // Parameters start with 1 
            preparedStatement.setString(1, skisql.getNzak());
            preparedStatement.setString(2, skisql.getOsd_t());
            preparedStatement.setString(3, skisql.getOsd_c());
            preparedStatement.setString(4, skisql.getOsd_r());
            preparedStatement.setString(5, skisql.getKiz());
            preparedStatement.setInt(6, skisql.getPri());
            preparedStatement.setString(7, skisql.getNvp());
            preparedStatement.setString(8, skisql.getNaim());
            preparedStatement.setString(9, skisql.getNizv());
             
           /* if (skisql.getUser() == null || skisql.getUser().trim().isEmpty()){preparedStatement.setNull(10, Types.VARCHAR);} 
                else {preparedStatement.setString(10, skisql.getUser());}*/
            
           /* Date date = Date.valueOf(skisql.getDatez().toString());
            preparedStatement.setDate(10, date);*/
            
           /* if (skisql.getUser_ip()== null || skisql.getUser_ip().trim().isEmpty()){preparedStatement.setNull(12, Types.VARCHAR);} 
                else {preparedStatement.setString(12, skisql.getUser_ip());}*/
            
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteSkisql(String kiz) {
        try {
            PreparedStatement preparedStatement = connection
                    .prepareStatement("delete from clippersql.skisql where kiz=?");
            // Parameters start with 1
            preparedStatement.setString(1, kiz);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateSkisql(Skisql skisql) {
        try {
            PreparedStatement preparedStatement = connection
                    .prepareStatement("update clippersql.skisql set nzak=?,osd_t=?,osd_c=?,osd_r=?,kiz=?,pri=?,nvp=?,naim=?,nizv=?,\"user\"=default,datez=default,user_ip=default where kiz=?");
            // Parameters start with 1
            preparedStatement.setString(1, skisql.getNzak());
            preparedStatement.setString(2, skisql.getOsd_t());
            preparedStatement.setString(3, skisql.getOsd_c());
            preparedStatement.setString(4, skisql.getOsd_r());
            preparedStatement.setString(5, skisql.getKiz());
            preparedStatement.setInt(6, skisql.getPri());
            preparedStatement.setString(7, skisql.getNvp());
            preparedStatement.setString(8, skisql.getNaim());
            preparedStatement.setString(9, skisql.getNizv());
            /*preparedStatement.setString(10, skisql.getUser());
            Date datez = Date.valueOf(skisql.getDatez().toString());
            preparedStatement.setDate(11, datez);
            preparedStatement.setString(12, skisql.getUser_ip());*/
            preparedStatement.setString(10, skisql.getKiz());
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Skisql> getAllSkisqls() {
        List<Skisql> skisqls = new ArrayList<Skisql>();
        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("select * from clippersql.skisql ");
            while (rs.next()) {

                Skisql skisql = new Skisql();
                skisql.setNzak(rs.getString("nzak"));
                skisql.setOsd_t(rs.getString("osd_t"));
                skisql.setOsd_c(rs.getString("osd_c"));
                skisql.setOsd_r(rs.getString("osd_r"));
                skisql.setKiz(rs.getString("kiz"));
                skisql.setPri(rs.getInt("pri"));
                skisql.setNvp(rs.getString("nvp"));
                skisql.setNaim(rs.getString("naim"));
                skisql.setNizv(rs.getString("nizv"));
                skisql.setUser(rs.getString("user"));
                skisql.setDatez(rs.getDate("datez"));
                skisql.setUser_ip(rs.getString("user_ip"));
                skisqls.add(skisql);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return skisqls;
    }

    public Skisql getSkisqlByKiz(String kiz) {
        Skisql skisql = new Skisql();
        try {
            PreparedStatement preparedStatement = connection.
                    prepareStatement("select * from clippersql.skisql where kiz=?");
            preparedStatement.setString(1, kiz);
            ResultSet rs = preparedStatement.executeQuery();

            if (rs.next()) {
                skisql.setNzak(rs.getString("nzak"));
                skisql.setOsd_t(rs.getString("osd_t"));
                skisql.setOsd_c(rs.getString("osd_c"));
                skisql.setOsd_r(rs.getString("osd_r"));
                skisql.setKiz(rs.getString("kiz"));
                skisql.setPri(rs.getInt("pri"));
                skisql.setNvp(rs.getString("nvp"));
                skisql.setNaim(rs.getString("naim"));
                skisql.setNizv(rs.getString("nizv"));
                skisql.setUser(rs.getString("user"));
                skisql.setDatez(rs.getDate("datez"));
                skisql.setUser_ip(rs.getString("user_ip"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return skisql;
    }

    public List<Skisql> getAllSkisqls(int page) {
        List<Skisql> skisqls = new ArrayList<Skisql>();
        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("select * from clippersql.skisql order by osd_t limit " + 100 + " offset " + (page - 1) * 100);
            
            while (rs.next()) {
                Skisql skisql = new Skisql();
                skisql.setNzak(rs.getString("nzak"));
                skisql.setOsd_t(rs.getString("osd_t"));
                skisql.setOsd_c(rs.getString("osd_c"));
                skisql.setOsd_r(rs.getString("osd_r"));
                skisql.setKiz(rs.getString("kiz"));
                skisql.setPri(rs.getInt("pri"));
                skisql.setNvp(rs.getString("nvp"));
                skisql.setNaim(rs.getString("naim"));
                skisql.setNizv(rs.getString("nizv"));
                skisql.setUser(rs.getString("user"));
                skisql.setDatez(rs.getDate("datez"));
                skisql.setUser_ip(rs.getString("user_ip"));
                skisqls.add(skisql);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return skisqls;
    }
    
     public int getCounts() {
        int counts = 0;

        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("SELECT COUNT (*) AS total from clippersql.skisql");
            while (rs.next()) {
                counts = rs.getInt("total");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return (counts / 100) + 1;
    }
    
}
