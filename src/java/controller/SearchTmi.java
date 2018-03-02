/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Tmisql;
import util.DbUtil;

/**
 *
 * @author u27brvz18
 * 
 * Сервлет который отвечает за фильтр по БД tmisql
 */
public class SearchTmi extends HttpServlet {

  private Connection connection;
    public SearchTmi() {
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
        
        
        List<Tmisql> tmisqlS = new ArrayList<Tmisql>();
        
        try {
            
            String osdch = request.getParameter("osdch").replace("*", "%").toUpperCase();
            String nc = request.getParameter("nc").replace("*", "%").toUpperCase();
            String svi = request.getParameter("svi");
            
            int page = Integer.parseInt(request.getParameter("page"));
            int count = Integer.parseInt(request.getParameter("count"));
            while ((nc.length()<3) && (nc.length()!=0) ){
            nc=" "+nc; 
            }
            
            PreparedStatement preparedStatement = connection.prepareStatement(
    " with tmib as (\n" +
        "select distinct on(osdch_t,osdch_r,osdch_c)\n" +
        "osdch_t,osdch_c,osdch_r,nc,svi,nizv\n" +
        "from clippersql.tmisql\n" +
            "where osdch_c||osdch_r like'" + osdch + "%'"   +
            "and nc like '%" + nc + "%'" +
            "and svi <= '" + svi + "%'" +
            "order by osdch_t, osdch_r, osdch_c, svi desc\n" +

            "limit " + 100 + " offset " + count + "),\n" + 

    "src as (\n" +
        "select (osdch_c||osdch_r) as osdch, osdch_t,osdch_c,osdch_r,nc,svi,t.nizv,naim as naim_osdch\n" +
        "from tmib t\n" +
            "left join clippersql.naimesql n\n" +
                "on (n.osd_t=t.osdch_t\n" +
                "and n.osd_r=t.osdch_r\n" +
                "and n.osd_c=t.osdch_c)\n" +
        "order by osdch_t,osdch_r,osdch_c,svi desc\n" +
    "  )\n" +
        "select osdch,nc,svi,nizv, naim_osdch\n" +
        "from src\n" +
        "order by osdch_t,osdch_r,osdch_c,svi desc\n" 
/*"  src_with_rownumbers as (\n" +
"	select row_number() over(\n" +
"           partition by src.osdch_t,src.osdch_r,src.osdch_c\n" +
"           order by src.osdch_t,src.osdch_r,src.osdch_c\n" +
"           ) as num_in_grp_ch, osdch, osdch_t,osdch_c,osdch_r,nc,svi,nizv,naim_osdch\n" +
"        from src)\n" +
"	select \n" +
"	   case when num_in_grp_ch=1 then osdch else '' end::varchar(80) as osdch_disp,nc,svi,nizv,\n" +
"	   case when num_in_grp_ch=1 then naim_osdch else '' end::varchar(80) as naim_ch\n" +
"	from src_with_rownumbers\n" +
"	order by osdch_t,osdch_r,osdch_c,svi"*/);
           
ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                Tmisql tmisql = new Tmisql();
                
                tmisql.setOsdch(rs.getString("osdch"));
                tmisql.setNc(rs.getString("nc"));
                tmisql.setNizv(rs.getString("nizv"));
                tmisql.setSvi(rs.getDate("svi"));
                tmisql.setNaim(rs.getString("naim_osdch"));
                
                tmisqlS.add(tmisql);                
            }
            
            RequestDispatcher view = request.getRequestDispatcher("/searchViewTmi.jsp");
            int size = tmisqlS.size();
            request.setAttribute("page", page);
            request.setAttribute("osdch", osdch.replace("%", "*"));
            request.setAttribute("nc", nc.replace("%", "*"));
            request.setAttribute("svi", svi);
            request.setAttribute("size", size);
            
            request.setAttribute("count", count);
            
            request.setAttribute("tmisqlS", tmisqlS);
            view.forward(request, response);
            //connection.close();
           
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
