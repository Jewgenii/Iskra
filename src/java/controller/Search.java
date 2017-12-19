/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.Vp44150sqlDao;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import util.DbUtil;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.List;
import model.Vp44150sql;
/**
 *  Сервлет который отвечает за фильтр по БД Vp44150sql
 * @author u27brvz18
 */
public class Search extends HttpServlet {

    
    private Connection connection;
    public Search() {
        super();
        
        connection = DbUtil.getConnection();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
       
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        /*PrintWriter out = response.getWriter();
        Connection conn = null;
        String url = "jdbc:postgresql://ant:5432/";
        String dbName = "antdb_test";
        String driver = "org.postgresql.Driver";
        String userName = "mexahik";
        String password = "123";*/
        
        List<Vp44150sql> vp44150sqlS = new ArrayList<Vp44150sql>();
        
        try {
            
            //Statement statement = connection.createStatement();
           /* Class.forName(driver).newInstance();
            conn = DriverManager.getConnection(url + dbName, userName, password);*/
            System.out.println("Connected!");
            
            String osdch = request.getParameter("osdch").replace("*", "%");
            String osdk = request.getParameter("osdk").replace("*", "%");
            String kiz = request.getParameter("kiz").replace("*", "%");
            String svi = request.getParameter("svi");
                            
            int page = Integer.parseInt(request.getParameter("page"));
            int count = Integer.parseInt(request.getParameter("count"));
           
            PreparedStatement preparedStatement = connection.prepareStatement(
    "with koliz as (select distinct on(osdch_t,osdch_r,osdch_c,osdk_t,osdk_r,osdk_c " +
        ",kiz) osdch_t, osdch_r, osdch_c, osdk_t, osdk_r, osdk_c, kiz, svi, kol, koliz " +
    "from clippersql.vp44150sql " +
    "where osdch_c||osdch_r like'" + osdch + "%'"   +
            "and osdk_c||osdk_r like '" + osdk + "%'" +
            "and kiz like '" + kiz + "%'" +
            "and svi <= '" + svi + "%' " +
    "order by osdch_t,osdch_r,osdch_c,osdk_t,osdk_r,osdk_c,kiz,svi desc " +
/*"	limit " + 100 + " offset " + (page - 1) * 100 + "), " +*/
    "limit " + 100 + " offset " + count + "), " +

    "src as (" +
        "select (osdch_c||osdch_r) as osdch,(osdk_c||osdk_r) " +
        "as osdk,osdch_t,osdch_r,osdch_c,osdk_t,osdk_r,osdk_c,kiz,svi,kol,koliz, naim as naim_osdch " +

    "from koliz v " +
        "left join clippersql.naimesql n " +
        "on (n.osd_t=v.osdch_t and n.osd_r=v.osdch_r and n.osd_c=v.osdch_c) " +
        "order by osdch_t,osdch_r,osdch_c,osdk_t,osdk_r,osdk_c,kiz,svi " +
    "), " +

    "src2 as ( " +
        "select osdch, osdk, osdch_t, osdch_r, osdch_c,osdk_t,osdk_r,osdk_c,kiz,svi,kol,koliz, naim_osdch, naim as naim_osdk from src v " +
        "left join clippersql.naimesql n " +
        "on (n.osd_t=v.osdk_t and n.osd_r=v.osdk_r and n.osd_c=v.osdk_c) " +
    "), " +


    "maxtmi AS ( " +
        "SELECT DISTINCT ON (foo.osdch_t, foo.osdch_r, foo.osdch_c) foo.osdch_t," +
            "foo.osdch_r," +
            "foo.osdch_c," +
            "foo.nc " +
        "FROM ( SELECT tmisql.osdch_t," +
            "tmisql.osdch_r," +
            "tmisql.osdch_c," +
            "tmisql.nc," +
            "tmisql.svi " +
        "FROM clippersql.tmisql " +
        "WHERE tmisql.svi <= tmisql.datez and (osdch_t,osdch_r, osdch_c) in (select osdch_t,osdch_r, osdch_c from src2) or (osdch_t, osdch_r, osdch_c) in (select osdk_t AS osdch_t, osdk_r AS osdch_r, osdk_c AS osdch_c from src2) " +

        "ORDER BY tmisql.osdch_t, tmisql.osdch_r, tmisql.osdch_c, tmisql.svi DESC) foo " +
    "), " +

    "maxtmp AS (" +
        "SELECT DISTINCT ON (foo.osdch_t, foo.osdch_r, foo.osdch_c, foo.osdk_t, foo.osdk_r, foo.osdk_c) foo.osdch_t," +
            "foo.osdch_r," +
            "foo.osdch_c," +
            "foo.osdk_t," +
            "foo.osdk_r," +
            "foo.osdk_c," +
            "foo.cp " +
        "FROM ( SELECT tmpsql.osdch_t," +
            "tmpsql.osdch_r," +
            "tmpsql.osdch_c," +
            "tmpsql.osdk_t," +
            "tmpsql.osdk_r," +
            "tmpsql.osdk_c," +
            "tmpsql.cp," +
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

    "left join maxtmi mtch on " +
        "(src2.osdch_t=mtch.osdch_t and src2.osdch_r=mtch.osdch_r and src2.osdch_c=mtch.osdch_c) " +
    "left join maxtmp mtmp on " +
        "(src2.osdch_t=mtmp.osdch_t and src2.osdch_r=mtmp.osdch_r and src2.osdch_c=mtmp.osdch_c and src2.osdk_t=mtmp.osdk_t and src2.osdk_r=mtmp.osdk_r and src2.osdk_c=mtmp.osdk_c) " +
    "left join maxtmi mtk on " +
        "(src2.osdk_t=mtk.osdch_t and src2.osdk_r=mtk.osdch_r and src2.osdk_c=mtk.osdch_c) " +
    ")" +


    "select " +
        "case when num_in_grp_ch=1 then osdch else '' end::varchar(20) " +
        "as osdch_disp," +

        "case when num_in_grp_k=1 then osdk else '' end::varchar(20) " +
        "as osdk_disp, kiz,svi,kol,koliz, " +

        "case when num_in_grp_ch=1 then naim_osdch else '' end::varchar(80) " +
        "as naim_osdch, " +

        "case when num_in_grp_k=1 then naim_osdk else '' end::varchar(80) " +
        "as naim_osdk, " +

        "case when num_in_grp_ch=1 then nc_ch else '' end::varchar(42) " +
        "as nc_ch," +
            
        "case when num_in_grp_k=1 then nc_k else '' end::varchar(42) " +
        "as nc_k , " +

        "case when num_in_grp_k=1 then cp else '' end::varchar(42) " +
        "as cp, num_in_grp_ch, num_in_grp_k " +
    "from src_with_rownumbers order by osdch_t,osdch_r,osdch_c,osdk_t,osdk_r,osdk_c,num_in_grp_ch, num_in_grp_k, kiz,svi");
            //ArrayList al = null;
            //ArrayList pid_list = new ArrayList();
           /* ResultSet rs = statement.executeQuery( "select osdch_c||osdch_r As osdch, osdk_c||osdk_r As osdk, kiz, svi, kol, koliz "
                    + "from clippersql.vp44150sql where "
                        + "osdch_c||osdch_r like'" + osdch + "%'"  
                        + " and osdk_c||osdk_r like '" + osdk + "%'" 
                        + " and kiz like '" + kiz + "%'"  
                        + " and CAST(svi AS text) like '" + svi + "%'"  
                            + " order by osdch_t,osdch_r,osdch_c,osdk_t,osdk_r,osdk_c,kiz,svi" 
                            + " limit " + 100 + " offset " + (page - 1) * 100);*/
            /*ResultSet rs = statement.executeQuery(
    );*/
            //System.out.println("query " + query);
            //Statement statement = connection.createStatement();
            //st = connection.createStatement();
            //ResultSet rs = st.executeQuery(query);
ResultSet rs = preparedStatement.executeQuery();
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
                
                vp44150sqlS.add(vp44150sql);                
            }
            
           /* int counts = 0;
            String query2 ="SELECT COUNT (*) AS total from clippersql.vp44150sql where osdch_c||osdch_r like '" + osdch + "%'" + " and osdk_c||osdk_r like '" + osdk + "%'" + " and CAST(svi AS text) like '" + svi + "%'" + " and kiz like '" + kiz + "%'";
            
            ResultSet rs2 = statement.executeQuery(query2);
            while (rs2.next()) {
                counts = rs2.getInt("total");
            }

            counts = (counts / 100) + 1;*/
           
           // request.setAttribute("piList", pid_list);
            
            
            RequestDispatcher view = request.getRequestDispatcher("/searchview.jsp");
            int size = vp44150sqlS.size();
            request.setAttribute("page", page);
            request.setAttribute("osdch", osdch.replace("%", "*"));
            request.setAttribute("osdk", osdk.replace("%", "*"));
            request.setAttribute("kiz", kiz.replace("%", "*"));
            request.setAttribute("svi", svi);
            request.setAttribute("size", size);
            
            request.setAttribute("count", count);
            
            request.setAttribute("vp44150sqlS", vp44150sqlS);
            view.forward(request, response);
            //connection.close();
            System.out.println("Disconnected!");
        } catch (Exception e) {
            e.printStackTrace();
        }      
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
}
