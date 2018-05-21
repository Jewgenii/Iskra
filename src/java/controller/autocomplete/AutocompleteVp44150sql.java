/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.autocomplete;

import com.google.gson.Gson;
import dao.NaimesqlDAO;
import dao.Vp44150sqlDao;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author u27brvz14
 */
@WebServlet(name = "AutocompleteVp44150sql", urlPatterns = {"/Autocomplete-Vp44150sql"})
public class AutocompleteVp44150sql extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        response.setContentType("application/json");

        List<String> lst = null;
        String out = null;
        String fieldName = request.getParameter("field").toLowerCase();// field name
        String value = request.getParameter("term").toLowerCase(); // value fo the field

        Vp44150sqlDao dao = new Vp44150sqlDao();

        if (value != null) {
            value = value.toUpperCase();
            switch (fieldName) {
                case "osdch":
                    lst = dao.getDistinctOSDCH(value);
                    break;
                case "kiz":
                    lst = dao.getDistinctKiz(value);
                    break;
                default:
            }
            out = new Gson().toJson(lst);
        }

        response.getWriter().write(out);
    }

}
