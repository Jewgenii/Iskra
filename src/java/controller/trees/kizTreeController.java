/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.trees;

import com.google.gson.Gson;
import dao.DAO;
import dao.NaimesqlDAO;
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

@WebServlet(name = "kizTreeController", urlPatterns = {"/kizTreeController"})
public class kizTreeController extends HttpServlet {

    private DAO kizDao = null;

    public kizTreeController() {
        kizDao = new NaimesqlDAO();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        response.setContentType("application/json");

        RequestDispatcher view = request.getRequestDispatcher("/kiz_tree.jsp");
        view.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        response.setContentType("application/json");

        String param = request.getParameter("osd");
        if (param != null) {
            param = param.trim();

            List<OsdKiz> lst = new ArrayList<>(100);
            String query
                    = "SELECT (osdch_c||osdch_r) as osd_ch FROM clippersql.vp44150sql where (osdk_c||osdk_r)  = ? order by osdch_c,osdch_r";
            try {
                Connection connection = kizDao.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(query);

                preparedStatement.setString(1, param);

                ResultSet rs = preparedStatement.executeQuery();

                while (rs.next()) {
                    String osd_ch = rs.getString("osd_ch");

                    lst.add(new OsdKiz(osd_ch));

                }
            } catch (SQLException e) {
                System.out.println(e);
            }

            response.getWriter().write(new Gson().toJson(lst));

        }
    }

}
