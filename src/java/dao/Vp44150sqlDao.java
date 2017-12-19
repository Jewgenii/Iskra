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
import model.Skisql;
import model.Vp44150sql;
import util.DbUtil;

/**
 *
 * @author u27brvz18
 */
public class Vp44150sqlDao {
     private Connection connection;
    // boolean tableKiz = false;
    public Vp44150sqlDao() {
        connection = DbUtil.getConnection();
    }

    public void addVp44150sql(Vp44150sql vp44150sql) {
        try {

            PreparedStatement preparedStatement = connection
                    .prepareStatement("insert into clippersql.vp44150sql(osdch_t,osdch_c,osdch_r,osdk_t,osdk_c,osdk_r,kiz,svi,kol,koliz,\"user\",datez,user_ip) values(?,?,?,?,?,?,?,?,?,?,default,default,default)");
            // Parameters start with 1 
            preparedStatement.setString(1, vp44150sql.getOsdch_t());
            preparedStatement.setString(2, vp44150sql.getOsdch_c());
            preparedStatement.setString(3, vp44150sql.getOsdch_r());
            preparedStatement.setString(4, vp44150sql.getOsdk_t());
            preparedStatement.setString(5, vp44150sql.getOsdk_c());
            preparedStatement.setString(6, vp44150sql.getOsdk_r());
            preparedStatement.setString(7, vp44150sql.getKiz());
            Date date = Date.valueOf(vp44150sql.getSvi().toString());
            preparedStatement.setDate(8, date);
            preparedStatement.setString(9, vp44150sql.getKol());
            preparedStatement.setString(10, vp44150sql.getKoliz());
            
             
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

    public void deleteVp44150sql(String kiz) {
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

    public void updateVp44150sql(Vp44150sql vp44150sql) {
        try {
            PreparedStatement preparedStatement = connection
                    .prepareStatement("update clippersql.vp44150sql set osdch_t=?,osdch_c=?,osdch_r=?,osdk_t=?,osdk_c=?,osdk_r=?,kiz=?,svi=?,kol=?,koliz=?,\"user\"=default,datez=default,user_ip=default where kiz=?");
            // Parameters start with 1
             preparedStatement.setString(1, vp44150sql.getOsdch_t());
            preparedStatement.setString(2, vp44150sql.getOsdch_c());
            preparedStatement.setString(3, vp44150sql.getOsdch_r());
            preparedStatement.setString(4, vp44150sql.getOsdk_t());
            preparedStatement.setString(5, vp44150sql.getOsdk_c());
            preparedStatement.setString(6, vp44150sql.getOsdk_r());
            preparedStatement.setString(7, vp44150sql.getKiz());
            Date date = Date.valueOf(vp44150sql.getSvi().toString());
            preparedStatement.setDate(8, date);
            preparedStatement.setString(9, vp44150sql.getKol());
            preparedStatement.setString(10, vp44150sql.getKoliz());
            preparedStatement.setString(11, vp44150sql.getKiz());
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Vp44150sql> getAllVp44150sql() {
        List<Vp44150sql> vp44150sqls = new ArrayList<Vp44150sql>();
        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("select * from clippersql.vp44150sql ");
            while (rs.next()) {

                Vp44150sql vp44150sql = new Vp44150sql();
                vp44150sql.setOsdch_t(rs.getString("osdch_t"));
                vp44150sql.setOsdch_c(rs.getString("osdch_c"));
                vp44150sql.setOsdch_r(rs.getString("osdch_r"));
                vp44150sql.setOsdk_t(rs.getString("osdk_t"));
                vp44150sql.setOsdk_c(rs.getString("osdk_c"));
                vp44150sql.setOsdk_r(rs.getString("osdk_r"));
                vp44150sql.setKiz(rs.getString("kiz"));
                vp44150sql.setSvi(rs.getDate("svi"));
                vp44150sql.setKol(rs.getString("kol"));
                vp44150sql.setKoliz(rs.getString("koliz"));
                vp44150sql.setUser(rs.getString("user"));
                vp44150sql.setDatez(rs.getDate("datez"));
                vp44150sql.setUser_ip(rs.getString("user_ip"));
                vp44150sqls.add(vp44150sql);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return vp44150sqls;
    }

    public Vp44150sql getVp44150sqlByKiz(String kiz) {
        Vp44150sql vp44150sql = new Vp44150sql();
        try {
            PreparedStatement preparedStatement = connection.
                    prepareStatement("select * from clippersql.vp44150sql where kiz=?");
            preparedStatement.setString(1, kiz);
            ResultSet rs = preparedStatement.executeQuery();

            if (rs.next()) {
                vp44150sql.setOsdch_t(rs.getString("osdch_t"));
                vp44150sql.setOsdch_c(rs.getString("osdch_c"));
                vp44150sql.setOsdch_r(rs.getString("osdch_r"));
                vp44150sql.setOsdk_t(rs.getString("osdk_t"));
                vp44150sql.setOsdk_c(rs.getString("osdk_c"));
                vp44150sql.setOsdk_r(rs.getString("osdk_r"));
                vp44150sql.setKiz(rs.getString("kiz"));
                vp44150sql.setSvi(rs.getDate("svi"));
                vp44150sql.setKol(rs.getString("kol"));
                vp44150sql.setKoliz(rs.getString("koliz"));
                vp44150sql.setUser(rs.getString("user"));
                vp44150sql.setDatez(rs.getDate("datez"));
                vp44150sql.setUser_ip(rs.getString("user_ip"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return vp44150sql;
    }
        /*Просмотр данных*/
    public List<Vp44150sql> getAllVp44150sql(int page) {
        
        List<Vp44150sql> vp44150sqls = new ArrayList<Vp44150sql>();
        
        try {
            
            Statement statement = connection.createStatement();
/*work*/

/*ResultSet rs = statement.executeQuery("with " +

	"src as ( " +
		"select (osdch_c||osdch_r) as osdch,(osdk_c||osdk_r) " +
		"as osdk,osdch_t,osdch_r,osdch_c,osdk_t,osdk_r,osdk_c,kiz,svi,kol,koliz, naim as naim_osdch " +

        "from clippersql.vp44150sql v  " +
		"left join clippersql.naimesql n on (n.osd_t=v.osdch_t and n.osd_r=v.osdch_r and n.osd_c=v.osdch_c) " +
		"order by osdch_t,osdch_r,osdch_c,osdk_t,osdk_r,osdk_c,kiz,svi " +
		"limit " + 100 + " offset " + (page - 1) * 100 + "), " +

        "src2 as ( " +
		"select osdch, osdk, osdch_t, osdch_r, osdch_c,osdk_t,osdk_r,osdk_c,kiz,svi,kol,koliz, naim_osdch, naim as naim_osdk from src v " +
		"left join clippersql.naimesql n on (n.osd_t=v.osdk_t and n.osd_r=v.osdk_r and n.osd_c=v.osdk_c) " +
        "), " +


        "maxtmi AS ( " +
            "SELECT DISTINCT ON (foo.osdch_t, foo.osdch_r, foo.osdch_c) foo.osdch_t, " +
                "foo.osdch_r, " +
                "foo.osdch_c, " +
                "foo.nc " +
            "FROM ( SELECT tmisql.osdch_t, " +
                    "tmisql.osdch_r, " +
                    "tmisql.osdch_c, " +
                    "tmisql.nc, " +
                    "tmisql.svi " +
                        "FROM clippersql.tmisql " +
                        "WHERE tmisql.svi <= tmisql.datez and (osdch_t,osdch_r, osdch_c) in (select osdch_t,osdch_r, osdch_c from src2 ) " +                 
                        "ORDER BY tmisql.osdch_t, tmisql.osdch_r, tmisql.osdch_c, tmisql.svi DESC) foo " +
        "), " +

        "maxtmp AS ( " +
            "SELECT DISTINCT ON (foo.osdch_t, foo.osdch_r, foo.osdch_c, foo.osdk_t, foo.osdk_r, foo.osdk_c) foo.osdch_t, " +
                "foo.osdch_r, " +
                "foo.osdch_c, " +
                "foo.osdk_t, " +
                "foo.osdk_r, " +
                "foo.osdk_c, " +
                "foo.cp " +
                    "FROM ( SELECT tmpsql.osdch_t, " +
                        "tmpsql.osdch_r, " +
                        "tmpsql.osdch_c, " +
                        "tmpsql.osdk_t, " +
                        "tmpsql.osdk_r, " +
                        "tmpsql.osdk_c, " +
                        "tmpsql.cp, " +
                        "tmpsql.svi " +
                            "FROM clippersql.tmpsql " +
                            "WHERE tmpsql.svi <= tmpsql.datez and (osdch_t,osdch_r, osdch_c,osdk_t,osdk_r, osdk_c) in (select osdch_t,osdch_r, osdch_c,osdk_t,osdk_r, osdk_c from src2 ) " +
                            "ORDER BY tmpsql.osdch_t, tmpsql.osdch_r, tmpsql.osdch_c, tmpsql.osdk_t, tmpsql.osdk_r, tmpsql.osdk_c, tmpsql.svi DESC) foo " +
        "), " +
        "src_with_rownumbers as ( " +
	"select  " +
		"row_number() over(partition by src2.osdch_t,src2.osdch_r,src2.osdch_c " +
			"order by src2.osdch_t,src2.osdch_r,src2.osdch_c) " +
			"as num_in_grp_ch, " +

        "row_number() " +
		"over(partition by src2.osdch_t,src2.osdch_r,src2.osdch_c,src2.osdk_t,src2.osdk_r,src2.osdk_c order by src2.osdch_t,src2.osdch_r,src2.osdch_c,src2.osdk_t,src2.osdk_r,src2.osdk_c) " +
			"as num_in_grp_k, " +
			"osdch,src2.osdch_t,src2.osdch_r,src2.osdch_c,osdk,src2.osdk_t,src2.osdk_r,src2.osdk_c,kiz,svi,kol,koliz,naim_osdch,naim_osdk,nc,cp from src2 " +

	"left join maxtmi mt on (src2.osdch_t=mt.osdch_t and src2.osdch_r=mt.osdch_r and src2.osdch_c=mt.osdch_c) " +
	"left join maxtmp mtmp on (src2.osdch_t=mtmp.osdch_t and src2.osdch_r=mtmp.osdch_r and src2.osdch_c=mtmp.osdch_c and src2.osdk_t=mtmp.osdk_t and src2.osdk_r=mtmp.osdk_r and src2.osdk_c=mtmp.osdk_c) " +
        ") " +


        "select  " +
        "case when num_in_grp_ch=1 then osdch else '' end::varchar(20) " +
        "as osdch_disp, " +

        "case when num_in_grp_k=1 then osdk else '' end::varchar(20) " +
        "as osdk_disp, kiz,svi,kol,koliz, " +

        "case when num_in_grp_ch=1 then naim_osdch else '' end::varchar(80) " +
        "as naim_osdch, " +
         
        "case when num_in_grp_k=1 then naim_osdk else '' end::varchar(80) " +
        "as naim_osdk, " +

        "case when num_in_grp_ch=1 then nc else '' end::varchar(42) " +
        "as nc, " +

        "case when num_in_grp_k=1 then cp else '' end::varchar(42) " +
        "as cp " +
        "from src_with_rownumbers order by osdch_t,osdch_r,osdch_c,osdk_t,osdk_r,osdk_c,num_in_grp_ch, num_in_grp_k, kiz,svi"

);*/

ResultSet rs = statement.executeQuery("with  " +
    "src as ( " +
        "select (osdch_c||osdch_r) as osdch,(osdk_c||osdk_r) " +
        "as osdk,osdch_t,osdch_r,osdch_c,osdk_t,osdk_r,osdk_c,kiz,svi,kol,koliz, naim as naim_osdch " +

        "from clippersql.vp44150sql v  " +
            "left join clippersql.naimesql n "
                    + "on (n.osd_t=v.osdch_t and n.osd_r=v.osdch_r and n.osd_c=v.osdch_c) " +
            "order by osdch_t,osdch_r,osdch_c,osdk_t,osdk_r,osdk_c,kiz,svi " +
            "limit " + 100 + " offset " + (page - 1) * 100 + "), " +

    "src2 as ( " +
        "select osdch, osdk, osdch_t, osdch_r, osdch_c,osdk_t,osdk_r,osdk_c,kiz,svi,kol,koliz, naim_osdch, naim as naim_osdk from src v " +
            "left join clippersql.naimesql n "
                    + "on (n.osd_t=v.osdk_t and n.osd_r=v.osdk_r and n.osd_c=v.osdk_c) " +
    "), " +


        "maxtmi AS ( " +
            "SELECT DISTINCT ON (foo.osdch_t, foo.osdch_r, foo.osdch_c) foo.osdch_t, " +
            "foo.osdch_r, " +
            "foo.osdch_c, " +
            "foo.nc " +
                "FROM ( SELECT tmisql.osdch_t, " +
                "tmisql.osdch_r, " +
                "tmisql.osdch_c, " +
                "tmisql.nc, " +
                "tmisql.svi " +
                    "FROM clippersql.tmisql " +
                        "WHERE tmisql.svi <= tmisql.datez and (osdch_t,osdch_r, osdch_c) in (select osdch_t,osdch_r, osdch_c from src2) or (osdch_t, osdch_r, osdch_c) in (select osdk_t AS osdch_t, osdk_r AS osdch_r, osdk_c AS osdch_c from src2) " +
                        "ORDER BY tmisql.osdch_t, tmisql.osdch_r, tmisql.osdch_c, tmisql.svi DESC) foo " +
        "), " +

        "maxtmp AS ( " +
            "SELECT DISTINCT ON (foo.osdch_t, foo.osdch_r, foo.osdch_c, foo.osdk_t, foo.osdk_r, foo.osdk_c) foo.osdch_t, " +
            "foo.osdch_r, " +
            "foo.osdch_c, " +
            "foo.osdk_t, " +
            "foo.osdk_r, " +
            "foo.osdk_c, " +
            "foo.cp " +
                "FROM ( SELECT tmpsql.osdch_t, " +
                "tmpsql.osdch_r, " +
                "tmpsql.osdch_c, " +
                "tmpsql.osdk_t, " +
                "tmpsql.osdk_r, " +
                "tmpsql.osdk_c, " +
                "tmpsql.cp, " +
                "tmpsql.svi " +
                    "FROM clippersql.tmpsql " +
                    "WHERE tmpsql.svi <= tmpsql.datez and (osdch_t,osdch_r, osdch_c) in (select osdch_t,osdch_r, osdch_c from src2 ) and (osdk_t,osdk_r, osdk_c) in (select osdk_t,osdk_r, osdk_c from src2 ) " +
                    "ORDER BY tmpsql.osdch_t, tmpsql.osdch_r, tmpsql.osdch_c, tmpsql.osdk_t, tmpsql.osdk_r, tmpsql.osdk_c, tmpsql.svi DESC) foo " +
        "), " +
    "src_with_rownumbers as ( " +
        "select " +
            "row_number() over(partition by src2.osdch_t,src2.osdch_r,src2.osdch_c " +
            "order by src2.osdch_t,src2.osdch_r,src2.osdch_c) " +
            "as num_in_grp_ch, " +

            "row_number() " +
            "over(partition by src2.osdch_t,src2.osdch_r,src2.osdch_c,src2.osdk_t,src2.osdk_r,src2.osdk_c order by src2.osdch_t,src2.osdch_r,src2.osdch_c,src2.osdk_t,src2.osdk_r,src2.osdk_c) " +
            "as num_in_grp_k, " +
            "osdch,src2.osdch_t,src2.osdch_r,src2.osdch_c,osdk,src2.osdk_t,src2.osdk_r,src2.osdk_c,kiz,svi,kol,koliz,naim_osdch,naim_osdk, mtch.nc AS nc_ch, mtk.nc AS nc_k, cp from src2 " +

        "left join maxtmi mtch on "
                    + "(src2.osdch_t=mtch.osdch_t and src2.osdch_r=mtch.osdch_r and src2.osdch_c=mtch.osdch_c) " +
        "left join maxtmp mtmp on "
                    + "(src2.osdch_t=mtmp.osdch_t and src2.osdch_r=mtmp.osdch_r and src2.osdch_c=mtmp.osdch_c and src2.osdk_t=mtmp.osdk_t and src2.osdk_r=mtmp.osdk_r and src2.osdk_c=mtmp.osdk_c) " +
        "left join maxtmi mtk on "
                    + "(src2.osdk_t=mtk.osdch_t and src2.osdk_r=mtk.osdch_r and src2.osdk_c=mtk.osdch_c) " +
    ") " +


        "select " +
            "case when num_in_grp_ch=1 then osdch else '' end::varchar(20) " +
                "as osdch_disp, " +

            "case when num_in_grp_k=1 then osdk else '' end::varchar(20) " +
                "as osdk_disp, kiz,svi,kol,koliz, " +

            "case when num_in_grp_ch=1 then naim_osdch else '' end::varchar(80) " +
                "as naim_osdch, " +

            "case when num_in_grp_k=1 then naim_osdk else '' end::varchar(80) " +
                "as naim_osdk, " +

            "case when num_in_grp_ch=1 then nc_ch else '' end::varchar(42) " +
                "as nc_ch, " +

            "case when num_in_grp_k=1 then nc_k else '' end::varchar(42) " +
                "as nc_k," +

            "case when num_in_grp_k=1 then cp else '' end::varchar(42) " +
                "as cp, num_in_grp_ch, num_in_grp_k " +
                    "from src_with_rownumbers order by osdch_t,osdch_r,osdch_c,osdk_t,osdk_r,osdk_c,num_in_grp_ch, num_in_grp_k, kiz,svi");



//ResultSet rs = statement.executeQuery("select osdch_c||osdch_r As osdch, osdk_c||osdk_r As osdk, kiz, svi, kol, koliz from clippersql.vp44150sql order by osdch_t,osdch_r,osdch_c,osdk_t,osdk_r,osdk_c,kiz,svi limit " + 100 + " offset " + (page - 1) * 100);

            
           
            while (rs.next()) {
                Vp44150sql vp44150sql = new Vp44150sql();
                
                vp44150sql.setOsdch(rs.getString("osdch_disp"));
                vp44150sql.setOsdk(rs.getString("osdk_disp"));
                vp44150sql.setKiz(rs.getString("kiz"));
                vp44150sql.setSvi(rs.getDate("svi"));
                vp44150sql.setKol(rs.getString("kol"));
                vp44150sql.setKoliz(rs.getString("koliz")/*Удаление нулей .replaceAll("\\.(.*?)0+$", ".$1").replaceAll("\\.$", "")*/);
                vp44150sql.setNaim(rs.getString("naim_osdch"));
                vp44150sql.setNaimk(rs.getString("naim_osdk"));
                vp44150sql.setNc(rs.getString("nc_ch"));
                vp44150sql.setNcK(rs.getString("nc_k"));
                vp44150sql.setCp(rs.getString("cp"));
                //vp44150sql.setUser(rs.getString("user"));
                //vp44150sql.setDatez(rs.getDate("datez"));
                //vp44150sql.setUser_ip(rs.getString("user_ip"));
                vp44150sqls.add(vp44150sql);
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return vp44150sqls;
    }
    /*Количество записей в колизе - для пагинации*/
     public int getCounts() {
        int counts = 0;

        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("SELECT COUNT (*) AS total from clippersql.vp44150sql");
            while (rs.next()) {
                counts = rs.getInt("total");
                
            }
            counts = (counts / 1000) + 1;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return counts;
    }  
     /*Поиск уникальных кизов для Autocomplete*/
      public ArrayList<String> getDistinctKiz(String frameWork) {
        ArrayList<String> list = new ArrayList<String>();
        PreparedStatement ps = null;
        String data;
        try {
            ps = connection.prepareStatement("select distinct kiz from clippersql.mv_vp44150_distinct_kiz_svi where lower(kiz) like lower(?) order by kiz limit 10");
            //ps = connection.prepareStatement("select distinct kiz from clippersql.mv_vp44150_distinct_kiz_svi where kiz like ? order by kiz limit 10");
            ps.setString(1, frameWork + "%");
            ResultSet rs = ps.executeQuery();
          
            while (rs.next()) {
                data = rs.getString("kiz");
                list.add(data);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return list;
    }     
}
