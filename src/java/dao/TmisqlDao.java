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
               /* "select (osdch_c||osdch_r) as osdch,nc,svi,tmi.nizv,naim as naim_osdch "
                    + "from clippersql.tmisql tmi "
                    + "left join clippersql.naimesql n "
                        + "on (n.osd_t=tmi.osdch_t and n.osd_r=tmi.osdch_r and n.osd_c=tmi.osdch_c) "
                    + "order by osdch_t,osdch_r,osdch_c,svi "
                    + "limit " + 100 + " offset " + (page - 1) * 100);*/
                "with tmib as(\n" +
                    "select osdch_t, osdch_r, osdch_c, nc, nizv, svi from clippersql.tmisql \n" +
                    "order by osdch_t, osdch_r, osdch_c, svi desc \n" +
                    "limit " + 100 + " offset " + (page - 1) * 100 + ")," +

                "src as (\n" +
                    "select (osdch_c||osdch_r) as osdch, osdch_t, osdch_c, osdch_r, nc, svi, t.nizv, naim as naim_osdch\n" +
                    "from tmib t\n" +
                        "left join clippersql.naimesql n\n" +
                        "on (n.osd_t=t.osdch_t\n" +
                        "and n.osd_r=t.osdch_r\n" +
                        "and n.osd_c=t.osdch_c)\n" +
                    "order by osdch_t, osdch_r, osdch_c, svi DESC\n" +
                "),\n" +

                "src_with_rownumbers as (\n" +
                    "select row_number() over(\n" +
                        "partition by src.osdch_t,src.osdch_r,src.osdch_c\n" +
                    "order by src.osdch_t,src.osdch_r,src.osdch_c\n" +
                    ") as num_in_grp_ch, osdch, osdch_t,osdch_c,osdch_r,nc,svi,nizv,naim_osdch\n" +
                    "from src)\n" +

                "select \n" +
                    "case when num_in_grp_ch=1 then osdch else '' end::varchar(80) as osdch_disp,nc,svi,nizv,\n" + 
                    "case when num_in_grp_ch=1 then naim_osdch else '' end::varchar(80) as naim_ch\n" +
                    "from src_with_rownumbers\n" +
                    "order by osdch_t,osdch_r,osdch_c,svi desc" );
            
            while (rs.next()) {
                Tmisql tmisql = new Tmisql();
                
                tmisql.setOsdch(rs.getString("osdch_disp"));
                tmisql.setNc(rs.getString("nc"));
                tmisql.setSvi(rs.getDate("svi"));
                tmisql.setNizv(rs.getString("nizv"));
                tmisql.setNaim(rs.getString("naim_ch"));
                
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
