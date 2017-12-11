/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.SkisqlDao;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Skisql;

/**
 *
 * @author u27brvz17
 */
public class SkisqlController extends HttpServlet {

    //private static final long serialVersionUID = 1L;
    private static String INSERT_OR_EDIT = "/skisql.jsp";
    private static String LIST_SKISQL = "/listSkisql.jsp";
    private SkisqlDao dao;

    public SkisqlController() {
        super();
        dao = new SkisqlDao();
    }

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    //protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    //   throws ServletException, IOException {
    // response.setContentType("text/html;charset=UTF-8");
    // try (PrintWriter out = response.getWriter()) {
    /* TODO output your page here. You may use following sample code. */
    //     out.println("<!DOCTYPE html>");
    //     out.println("<html>");
    //     out.println("<head>");
    //     out.println("<title>Servlet SkisqlController</title>");            
    //     out.println("</head>");
    //     out.println("<body>");
    //     out.println("<h1>Servlet SkisqlController at " + request.getContextPath() + "</h1>");
    //     out.println("</body>");
    //     out.println("</html>");
    //   }
    // }
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
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        String forward = "";
        String action = request.getParameter("action");

        if (action.equalsIgnoreCase("delete")) {
            String kiz = (request.getParameter("kiz"));
            dao.deleteSkisql(kiz);
            forward = LIST_SKISQL;
            request.setAttribute("skisqls", dao.getAllSkisqls(1));
            request.setAttribute("page", 1);
            request.setAttribute("counts", dao.getCounts());

        } else if (action.equalsIgnoreCase("insert")) {
            forward = INSERT_OR_EDIT;
            String kiz = (request.getParameter("kiz"));
            Skisql skisql = dao.getSkisqlByKiz(kiz);

            request.setAttribute("skisql", skisql);
            request.setAttribute("skisqls", dao.getAllSkisqls(1));
            request.setAttribute("page", 1);
            request.setAttribute("counts", dao.getCounts());

        } else if (action.equalsIgnoreCase("list")) {
            String page = (request.getParameter("page"));
            forward = LIST_SKISQL;
            request.setAttribute("skisqls", dao.getAllSkisqls(Integer.parseInt(page)));
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
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        Skisql skisql = new Skisql();
        skisql.setNzak(request.getParameter("nzak"));
        skisql.setOsd_t(request.getParameter("osd_t"));
        skisql.setOsd_c(request.getParameter("osd_c"));
        skisql.setOsd_r(request.getParameter("osd_r"));
        //skisql.setKiz(request.getParameter("kiz"));
        skisql.setPri(Integer.parseInt(request.getParameter("pri")));
        skisql.setNvp(request.getParameter("nvp"));
        skisql.setNaim(request.getParameter("naim"));
        skisql.setNizv(request.getParameter("nizv"));
        //skisql.setUser(request.getParameter("user"));
        //skisql.setUser_ip(request.getParameter("user_ip"));
        /*Date datez = Date.valueOf(request.getParameter("datez"));
        skisql.setDatez(datez);*/
        //skisql.setUser(request.getParameter("user"));
        String kiz = request.getParameter("kiz");
        //if (kiz == null || kiz.isEmpty()) {
        //    skisql.setKiz(kiz);
        //    dao.updateSkisql(skisql);
        //} else {
        skisql.setKiz(kiz);
        dao.addSkisql(skisql);

        //}
        RequestDispatcher view = request.getRequestDispatcher(LIST_SKISQL);
        request.setAttribute("skisqls", dao.getAllSkisqls(1));
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
