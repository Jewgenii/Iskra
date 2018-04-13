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
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.TmpsqlCh;
import util.DbUtil;

/**
 *
 * @author u27brvz18
 */
public class SearchTmpK extends HttpServlet {
 private Connection connection;
    public SearchTmpK() {
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
        
        
        List<TmpsqlCh> tmpsqlChS = new ArrayList<TmpsqlCh>();
        
        try {
            
            String osdch = request.getParameter("osdch").replace("*", "%").toUpperCase();
            String osdk = request.getParameter("osdk").replace("*", "%").toUpperCase();
            String cp = request.getParameter("cp").replace("*", "%").toUpperCase();
           
            /*long curTime = System.currentTimeMillis();
            String svi = new SimpleDateFormat("dd.MM.yyyy").format(curTime);*/
            
            String svi = request.getParameter("svi");
            
            int page = Integer.parseInt(request.getParameter("page"));
            int count = Integer.parseInt(request.getParameter("count"));
            while ((cp.length()<3) && (cp.length()!=0) ){
            cp=" "+cp; 
            }
            
            PreparedStatement preparedStatement = connection.prepareStatement(
"   with tmpb as (\n" +
"      select osdch_t,osdch_r,osdch_c,osdk_t,osdk_r,osdk_c,cp,svi,nizv\n" +
"      from clippersql.tmpsql\n" +
"      where svi <= '" + svi + "%' \n" +
"	   and osdch_c||osdch_r like '" + osdch + "%'\n" +
"	   and osdk_c||osdk_r like '" + osdk + "%'\n" +
"	   and cp like '%" + cp + "%' \n" +
"      order by osdk_t,osdk_r,osdk_c,osdch_t,osdch_r,osdch_c,svi desc\n" +
"limit " + 100 + " offset " + count + "),\n" +

"   tmi as (\n" +
"      select distinct on (osdch_t,osdch_r,osdch_c) osdch_t,osdch_r,osdch_c,nc\n" +
"      from clippersql.tmisql\n" +
"      where (osdch_t,osdch_r,osdch_c) in (\n" +
"         select osdch_t,osdch_r,osdch_c from tmpb\n" +
"         union\n" +
"         select osdk_t,osdk_r,osdk_c from tmpb\n" +
"         )\n" +
"      order by osdch_t,osdch_r,osdch_c,svi desc\n" +
"      ),\n" +
"   src as (\n" +
"      select p.osdch_t,p.osdch_r,p.osdch_c,p.osdk_t,p.osdk_r,p.osdk_c,(p.osdch_c||p.osdch_r) as osdch,(p.osdk_c||p.osdk_r) as osdk,ich.nc as nc_ch,ik.nc as nc_k,cp,p.nizv,p.svi\n" +
"      from tmpb p\n" +
"      left join tmi ich\n" +
"         on (ich.osdch_t=p.osdch_t\n" +
"         and ich.osdch_r=p.osdch_r\n" +
"         and ich.osdch_c=p.osdch_c)\n" +
"      left join tmi ik\n" +
"         on (ik.osdch_t=p.osdk_t\n" +
"         and ik.osdch_r=p.osdk_r\n" +
"         and ik.osdch_c=p.osdk_c)\n" +
"      order by p.osdk_t,p.osdk_r,p.osdk_c\n" +
"      ),\n" +
"   naim as (\n" +
"	select osd_t,osd_r,osd_c,naim \n" +
"	from clippersql.naimesql\n" +
"   ),\n" +
"   src2 as (\n" +
"      select osdch,osdk,osdch_t,osdch_r,osdch_c,osdk_t,osdk_r,osdk_c,nc_ch,nc_k,cp,nizv,nch.naim as naim_ch,nk.naim as naim_k,svi\n" +
"      from src p\n" +
"      left join naim nch\n" +
"	 on (nch.osd_t=p.osdch_t\n" +
"	 and nch.osd_r=p.osdch_r\n" +
"	 and nch.osd_c=p.osdch_c)\n" +
"      left join naim nk\n" +
"	 on (nk.osd_t=p.osdk_t\n" +
"	 and nk.osd_r=p.osdk_r\n" +
"	 and nk.osd_c=p.osdk_c)\n" +
"	 order by p.osdk_t,p.osdk_r,p.osdk_c,p.osdch_t,p.osdch_r,p.osdch_c,svi\n" +
"   ),\n" +
"           \n" +
"   src_with_rownumbers as (\n" +
"      select row_number() over(\n" +
"	partition by src2.osdk_t,src2.osdk_r,src2.osdk_c\n" +
"        order by src2.osdk_t,src2.osdk_r,src2.osdk_c,src2.osdch_t,src2.osdch_r,src2.osdch_c\n" +
"           ) as num_in_grp_k,\n" +
"        row_number() over(\n" +
"           partition by src2.osdk_t,src2.osdk_r,src2.osdk_c,src2.osdch_t,src2.osdch_r,src2.osdch_c\n" +
"           order by src2.osdk_t,src2.osdk_r,src2.osdk_c,src2.osdch_t,src2.osdch_r,src2.osdch_c\n" +
"           ) as num_in_grp_ch,\n" +
"        osdk,src2.osdk_t,src2.osdk_r,src2.osdk_c,osdch,src2.osdch_t,src2.osdch_r,src2.osdch_c,\n" +
"        nc_ch,nc_k,cp,nizv,naim_ch,naim_k,svi\n" +
"      from src2\n" +
"  ),\n" +
"	tmp as(\n" +

"   select\n" +
"	max(num_in_grp_k) over (partition by osdk) as osdk_cnt,\n" +
"	count(*) over (partition by osdk,osdch order by osdk,osdch ) as osdch_cnt,\n" +
"	case when num_in_grp_ch=1 then osdch else '' end::varchar(20) as osdch_disp,\n" +
"	case when num_in_grp_k=1 then osdk else '' end::varchar(20) as osdk_disp,\n" +
"	cp,nizv,svi,\n" +
"	case when num_in_grp_ch=1 then naim_ch else '' end::varchar(80) as naim_ch,\n" +
"	case when num_in_grp_k=1 then naim_k else '' end::varchar(80) as naim_k,\n" +
"	case when num_in_grp_ch=1 then nc_ch else '' end::varchar(42) as nc_ch,\n" +
"	case when num_in_grp_k=1 then nc_k else '' end::varchar(42) as nc_k,num_in_grp_ch, num_in_grp_k\n" +
"   \n" +
"   from src_with_rownumbers\n" +
"   order by osdk_t,osdk_r,osdk_c,num_in_grp_k,osdch_t,osdch_r,osdch_c,num_in_grp_ch,svi)\n" +
"\n" +
"   select \n" +
"    case when osdch_disp != '' then osdch_cnt::varchar(3)  else '' end::varchar(3)  as  osdch_cnt,\n" +
"    case when osdk_disp != '' then osdk_cnt::varchar(3)  else '' end::varchar(3)  as  osdk_cnt,\n" +
"    osdch_disp,osdk_disp,cp,nizv,svi,naim_ch,naim_k,nc_ch,nc_k,num_in_grp_ch, num_in_grp_k\n" +
"   from tmp" );
           
ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                TmpsqlCh tmpsqlCh = new TmpsqlCh();
                
                tmpsqlCh.setOsdch(rs.getString("osdch_disp"));
                tmpsqlCh.setOsdk(rs.getString("osdk_disp"));
                tmpsqlCh.setCp(rs.getString("cp"));
                tmpsqlCh.setNizv(rs.getString("nizv"));
                tmpsqlCh.setSvi(rs.getDate("svi"));
                tmpsqlCh.setNaim_ch(rs.getString("naim_ch"));
                tmpsqlCh.setNaim_k(rs.getString("naim_k"));
                tmpsqlCh.setNc_ch(rs.getString("nc_ch"));
                tmpsqlCh.setNc_k(rs.getString("nc_k"));
                tmpsqlCh.setOsdch_cnt(rs.getString("osdch_cnt"));
                tmpsqlCh.setOsdk_cnt(rs.getString("osdk_cnt"));
                tmpsqlChS.add(tmpsqlCh);                
            }
            
            RequestDispatcher view = request.getRequestDispatcher("/searchViewTmpK.jsp");
            int size = tmpsqlChS.size();
            request.setAttribute("page", page);
            request.setAttribute("osdch", osdch.replace("%", "*"));
            request.setAttribute("osdk", osdk.replace("%", "*"));
            request.setAttribute("cp", cp.replace("%", "*"));
            request.setAttribute("svi", svi);
            request.setAttribute("size", size);
            
            request.setAttribute("count", count);
            
            request.setAttribute("tmpsqlChS", tmpsqlChS);
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
