/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.TmpsqlChDao;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.TmpsqlCh;

/**
 *
 * @author u27brvz18
 */
public class TmpsqlKController extends HttpServlet {

   private static String INSERT_OR_EDIT = "/";
    private static String LIST_TMPSQLK = "/listTmpsqlK.jsp";
    private TmpsqlChDao dao;

    public TmpsqlKController() {
        super();
        dao = new TmpsqlChDao();
    }
    
 @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        String forward = "";
        String action = request.getParameter("action");
        
        if (action.equalsIgnoreCase("list")) {
            String page = (request.getParameter("page"));
            String svi = (request.getParameter("svi"));
            forward = LIST_TMPSQLK;
            request.setAttribute("tmpsqlChs", dao.getAllTmpsqlK(Integer.parseInt(page)));
            request.setAttribute("page", page);
            request.setAttribute("svi", svi);
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
        TmpsqlCh tmpsqlCh = new TmpsqlCh();
        
        tmpsqlCh.setOsdch(request.getParameter("osdch"));
        tmpsqlCh.setOsdk(request.getParameter("osdk"));
        Date svi = Date.valueOf(request.getParameter("svi"));
        tmpsqlCh.setSvi(svi);
        tmpsqlCh.setCp(request.getParameter("cp"));
        tmpsqlCh.setNc_ch(request.getParameter("nc_ch"));
        tmpsqlCh.setNizv(request.getParameter("nizv"));
        tmpsqlCh.setNc_k(request.getParameter("nc_k"));
        tmpsqlCh.setNaim_ch(request.getParameter("naim_ch"));
        tmpsqlCh.setNc_k(request.getParameter("naim_k"));
        
        //dao.addTmpsqlCh(tmpsqlCh);
        
        RequestDispatcher view = request.getRequestDispatcher(LIST_TMPSQLK);
        request.setAttribute("tmpsqlChs", dao.getAllTmpsqlK(1));
        request.setAttribute("page", 1);
        request.setAttribute("svi", svi);
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

   
