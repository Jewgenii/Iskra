/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.tree;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import dao.DAO;
import dao.NaimesqlDAO;
import dao.Vp44150sqlDao;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import model.OsdKiz;
import model.Vp44150sql;

@WebServlet(name = "vp44150sqlTreeController", urlPatterns = {"/vp44150sqlTree"})
public class vp44150sqlTreeController extends HttpServlet {

    private Vp44150sqlDao vp44150sqlDao = null;

    public vp44150sqlTreeController() {

        vp44150sqlDao = new Vp44150sqlDao();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        response.setContentType("application/json");

        String osdch = request.getParameter("osdch");
        String kiz = request.getParameter("kiz");

        osdch = (osdch == null) ? "" : osdch;
        kiz = (kiz == null) ? "" : kiz;

        request.setAttribute("osdch", osdch);
        request.setAttribute("kiz", kiz);

        RequestDispatcher view = request.getRequestDispatcher("/Vp44150sqlTreeView.jsp");
        view.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        response.setContentType("application/json");

        String osdch = request.getParameter("osdch");
        String kiz = request.getParameter("kiz");

        String contextPath = this.getServletContext().getContextPath();
        vp44150sqlDao.setContextPath(contextPath);

        if (osdch != null && kiz != null) {
            osdch = osdch.trim().toUpperCase();
            kiz = kiz.trim().toUpperCase();

            JsonArray jsArr = null;

            try {
                jsArr = vp44150sqlDao.getTreeNodes(osdch, kiz);
            } catch (SQLException e) {
                System.out.println(e);
            }

            String out = new Gson().toJson(jsArr);
            response.getWriter().write(out);
        }
    }

}
