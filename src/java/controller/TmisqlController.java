/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.TmisqlDao;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Tmisql;

/**
 *
 * @author Sergey Nikonenko
 */
public class TmisqlController extends HttpServlet {

    private static String INSERT_OR_EDIT = "/";
    private static String LIST_TMISQL = "/listTmisql.jsp";
    private TmisqlDao dao;

    public TmisqlController() {
        super();
        dao = new TmisqlDao();
    }

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
        
        if (action.equalsIgnoreCase("list")) {
            String page = (request.getParameter("page"));
            forward = LIST_TMISQL;
            request.setAttribute("tmisqls", dao.getAllTmisql(Integer.parseInt(page)));
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
        Tmisql tmisql = new Tmisql();
        
        tmisql.setOsdch(request.getParameter("osdch"));
        
        Date svi = Date.valueOf(request.getParameter("svi"));
        tmisql.setSvi(svi);
        tmisql.setNc(request.getParameter("nc"));
        tmisql.setNizv(request.getParameter("nizv"));
        tmisql.setNaim(request.getParameter("naim"));
        
        dao.addTmisql(tmisql);
        
        RequestDispatcher view = request.getRequestDispatcher(LIST_TMISQL);
        request.setAttribute("tmisqls", dao.getAllTmisql(1));
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
