/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;


import dao.Vp44150sqlDao;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Vp44150sql;

/**
 *
 * @author Sergey Nikonenko
 */
public class Vp44150sqlController extends HttpServlet {
  private static String INSERT_OR_EDIT = "/";
    private static String LIST_VP44150SQL = "/listVp44150sql.jsp";
    private Vp44150sqlDao dao;

    public Vp44150sqlController() {
        super();
        dao = new Vp44150sqlDao();
    }
   

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
         response.setContentType("text/html");
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        String forward = "";
        String action = request.getParameter("action");

        if (action.equalsIgnoreCase("delete")) {
            String kiz = (request.getParameter("kiz"));
            dao.deleteVp44150sql(kiz);
            forward = LIST_VP44150SQL;
            request.setAttribute("Vp44150sqls", dao.getAllVp44150sql(1));
            request.setAttribute("page", 1);
            request.setAttribute("counts", dao.getCounts());

        } else if (action.equalsIgnoreCase("insert")) {
            forward = INSERT_OR_EDIT;
            String kiz = (request.getParameter("kiz"));
            Vp44150sql vp44150sql = dao.getVp44150sqlByKiz(kiz);

            request.setAttribute("vp44150sql", vp44150sql);
            request.setAttribute("vp44150sqls", dao.getAllVp44150sql(1));
            request.setAttribute("page", 1);
            request.setAttribute("counts", dao.getCounts());

        } else if (action.equalsIgnoreCase("list")) {
            String page = (request.getParameter("page"));
            forward = LIST_VP44150SQL;
            request.setAttribute("vp44150sqls", dao.getAllVp44150sql(Integer.parseInt(page)));
            request.setAttribute("page", page);
            request.setAttribute("counts", dao.getCounts());
        } else {
            forward = INSERT_OR_EDIT;
        }

        RequestDispatcher view = request.getRequestDispatcher(forward);
        view.forward(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        Vp44150sql vp44150sql = new Vp44150sql();
        //vp44150sql.setOsdch_t(request.getParameter("osdch_t"));
        //vp44150sql.setOsdch_c(request.getParameter("osdch_c"));
        vp44150sql.setOsdch(request.getParameter("osdch_disp"));
        //vp44150sql.setOsdch_r(request.getParameter("osdch_r"));
        //vp44150sql.setOsdk_t(request.getParameter("osdk_t"));
        //vp44150sql.setOsdk_c(request.getParameter("osdk_c"));
        vp44150sql.setOsdk(request.getParameter("osdk_disp"));
        //vp44150sql.setOsdk_r(request.getParameter("osdk_r"));
        String kiz = request.getParameter("kiz");
        vp44150sql.setKiz(kiz);
        Date svi = Date.valueOf(request.getParameter("svi"));
        vp44150sql.setSvi(svi);
        vp44150sql.setKol(request.getParameter("kol"));
        vp44150sql.setKoliz(request.getParameter("koliz"));
       // vp44150sql.setNaim(request.getParameter("naim_osdch"));
        //vp44150sql.setNaimk(request.getParameter("naim_osdk"));
        //skisql.setUser(request.getParameter("user"));
        //skisql.setUser_ip(request.getParameter("user_ip"));
        /*Date datez = Date.valueOf(request.getParameter("datez"));
        skisql.setDatez(datez);*/
        //skisql.setUser(request.getParameter("user"));
        
        //if (kiz == null || kiz.isEmpty()) {
        //    skisql.setKiz(kiz);
        //    dao.updateSkisql(skisql);
        //} else {
       
        dao.addVp44150sql(vp44150sql);

        //}
        RequestDispatcher view = request.getRequestDispatcher(LIST_VP44150SQL);
        request.setAttribute("vp44150sqls", dao.getAllVp44150sql(1));
        request.setAttribute("page", 1);
        request.setAttribute("counts", dao.getCounts());
        view.forward(request, response);
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
