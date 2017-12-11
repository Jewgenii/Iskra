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
            Statement statement = connection.createStatement();
           /* Class.forName(driver).newInstance();
            conn = DriverManager.getConnection(url + dbName, userName, password);*/
            System.out.println("Connected!");
            
            String osdch = request.getParameter("osdch").replace("*", "%");
            String osdk = request.getParameter("osdk").replace("*", "%");
            String kiz = request.getParameter("kiz").replace("*", "%");
            String svi = request.getParameter("svi");
            
                
            int page = Integer.parseInt(request.getParameter("page"));

            //ArrayList al = null;
            //ArrayList pid_list = new ArrayList();
            ResultSet rs = statement.executeQuery( "select osdch_c||osdch_r As osdch, osdk_c||osdk_r As osdk, kiz, svi, kol, koliz "
                    + "from clippersql.vp44150sql where "
                        + "osdch_c||osdch_r like'" + osdch + "%'"  
                        + " and osdk_c||osdk_r like '" + osdk + "%'" 
                        + " and kiz like '" + kiz + "%'"  
                        + " and CAST(svi AS text) like '" + svi + "%'"  
                            + " order by osdch_t,osdch_r,osdch_c,osdk_t,osdk_r,osdk_c,kiz,svi" 
                            + " limit " + 100 + " offset " + (page - 1) * 100);

            //System.out.println("query " + query);
            //Statement statement = connection.createStatement();
            //st = connection.createStatement();
            //ResultSet rs = st.executeQuery(query);

            while (rs.next()) {
                Vp44150sql vp44150sql = new Vp44150sql();
                
                vp44150sql.setOsdch(rs.getString("osdch"));              
                vp44150sql.setOsdk(rs.getString("osdk"));
                vp44150sql.setKiz(rs.getString("kiz"));
                vp44150sql.setKol(rs.getString("kol"));
                vp44150sql.setKoliz(rs.getString("koliz"));
                vp44150sql.setSvi(rs.getDate("svi"));
                
                vp44150sqlS.add(vp44150sql);
                
            }
            
            int counts = 0;
            String query2 ="SELECT COUNT (*) AS total from clippersql.vp44150sql where osdch_c||osdch_r like '" + osdch + "%'" + " and osdk_c||osdk_r like '" + osdk + "%'" + " and CAST(svi AS text) like '" + svi + "%'" + " and kiz like '" + kiz + "%'";
            
            ResultSet rs2 = statement.executeQuery(query2);
            while (rs2.next()) {
                counts = rs2.getInt("total");
            }

            counts = (counts / 100) + 1;
           
           // request.setAttribute("piList", pid_list);
            
            RequestDispatcher view = request.getRequestDispatcher("/searchview.jsp");
            request.setAttribute("page", page);
            request.setAttribute("osdch", osdch.replace("%", "*"));
            request.setAttribute("osdk", osdk.replace("%", "*"));
            request.setAttribute("kiz", kiz.replace("%", "*"));
            request.setAttribute("svi", svi);

            request.setAttribute("counts", counts);
            
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
