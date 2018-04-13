/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Naimesql;
import java.sql.*;
import java.util.HashSet;
import java.util.Set;
import util.DbUtil;

/**
 *
 * @author u27brvz14
 */
public class SearchNaimesql extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    private Connection connection = null;
    private  PreparedStatement preparedStatement = null;
    public SearchNaimesql() {
        super();
        connection = DbUtil.getConnection();
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet SearchNaimesql</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet SearchNaimesql at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
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

        List<Naimesql> naimesql = new ArrayList<>();
       
        try {
            preparedStatement = connection.prepareStatement("select osd_t,osd_c,osd_r,naim,nizv from clippersql.naimesql limit 100");
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                Naimesql n = new Naimesql();
                n.setNaim(rs.getString("naim"));
                n.setNizv(rs.getString("nizv"));
                n.setOsd_c(rs.getString("osd_c"));
                n.setOsd_r(rs.getString("osd_r"));
                n.setOsd_t(rs.getString("osd_t"));
                naimesql.add(n);
            }
        } catch (Exception ex) {
            System.out.println(ex);
        }

        //processRequest(request, response);
        RequestDispatcher view = request.getRequestDispatcher("/naimesql.jsp");
        request.setAttribute("naimesql",naimesql);
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
        processRequest(request, response);
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
