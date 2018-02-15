/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import model.Tmisql;
import util.DbUtil;

/**
 *
 * @author Sergey Nikonenko
 */
public class TmisqlDao {

    private Connection connection;

    // boolean tableKiz = false;
    public TmisqlDao() {
        connection = DbUtil.getConnection();
    }

    public void addTmisql(Tmisql tmisql) {
        try {

            PreparedStatement preparedStatement = connection
                    .prepareStatement("insert into clippersql.vp44150sql(osdch_t,osdch_c,osdch_r,osdk_t,osdk_c,osdk_r,kiz,svi,kol,koliz,\"user\",datez,user_ip) values(?,?,?,?,?,?,?,?,?,?,default,default,default)");
            // Parameters start with 1 
            preparedStatement.setString(1, tmisql.getOsdch_t());
            preparedStatement.setString(2, tmisql.getOsdch_c());
            preparedStatement.setString(3, tmisql.getOsdch_r());

            Date date = Date.valueOf(tmisql.getSvi().toString());
            preparedStatement.setDate(8, date);

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

    public void deleteTmisql(String kiz) {
        try {
            PreparedStatement preparedStatement = connection
                    .prepareStatement("delete from clippersql.vp44150sql where kiz=?");
            // Parameters start with 1
            preparedStatement.setString(1, kiz);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateTmisql(Tmisql tmisql) {
        try {
            PreparedStatement preparedStatement = connection
                    .prepareStatement("update clippersql.vp44150sql set osdch_t=?,osdch_c=?,osdch_r=?,osdk_t=?,osdk_c=?,osdk_r=?,kiz=?,svi=?,kol=?,koliz=?,\"user\"=default,datez=default,user_ip=default where kiz=?");
            // Parameters start with 1
            preparedStatement.setString(1, tmisql.getOsdch_t());
            preparedStatement.setString(2, tmisql.getOsdch_c());
            preparedStatement.setString(3, tmisql.getOsdch_r());

            Date date = Date.valueOf(tmisql.getSvi().toString());
            preparedStatement.setDate(8, date);

            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Tmisql getTmisqlByKiz(String kiz) {
        Tmisql tmisql = new Tmisql();
        try {
            PreparedStatement preparedStatement = connection.
                    prepareStatement("select * from clippersql.vp44150sql where kiz=?");
            preparedStatement.setString(1, kiz);
            ResultSet rs = preparedStatement.executeQuery();

            if (rs.next()) {
                tmisql.setOsdch_t(rs.getString("osdch_t"));
                tmisql.setOsdch_c(rs.getString("osdch_c"));
                tmisql.setOsdch_r(rs.getString("osdch_r"));

                tmisql.setSvi(rs.getDate("svi"));

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return tmisql;
    }

    public List<Tmisql> getAllTmisql(int page) {

        List<Tmisql> tmisqls = new ArrayList<Tmisql>();

        try {
            
            Statement statement = connection.createStatement();
            
            ResultSet rs = statement.executeQuery(
                "select (osdch_c||osdch_r) as osdch,ncm,svi,tmi.nizv,naim as naim_osdch "
                    + "from clippersql.tmisql tmi "
                    + "left join clippersql.naimesql n "
                        + "on (n.osd_t=tmi.osdch_t and n.osd_r=tmi.osdch_r and n.osd_c=tmi.osdch_c) "
                    + "order by osdch_t,osdch_r,osdch_c,svi "
                    + "limit " + 100 + " offset " + (page - 1) * 100);
            
            while (rs.next()) {
                Tmisql tmisql = new Tmisql();
                
                tmisql.setOsdch(rs.getString("osdch"));
                tmisql.setNcm(rs.getString("ncm"));
                tmisql.setSvi(rs.getDate("svi"));
                tmisql.setNizv(rs.getString("nizv"));
                tmisql.setNaim(rs.getString("naim_osdch"));
                
                tmisqls.add(tmisql);
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return tmisqls;
    }

    /*Количество записей в колизе - для пагинации*/
    public int getCounts() {
        int counts = 0;

        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("SELECT COUNT (*) AS total from clippersql.tmisql");
            while (rs.next()) {
                counts = rs.getInt("total");

            }
            counts = (counts / 100) + 1;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return counts;
    }
}
