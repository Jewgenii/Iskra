/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.autocomplete;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import dao.DAO;
import dao.NaimesqlDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
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
@WebServlet(name = "AutocompleteNaimesql", urlPatterns = {"/Autocomplete-Naimesql"})
public class AutocompleteNaimesql extends HttpServlet {

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

        String name = request.getParameter("field").toLowerCase();
        String term = request.getParameter("term").toLowerCase();

        NaimesqlDAO dao = new NaimesqlDAO();
        List<String> lst = null;
        switch (name) {
            case "naim":
                lst = dao.getDistinctNaim(term);
                break;
            case "osd":
                lst = dao.getDistinctOSD(term);
                break;
            default:
        }

        String gsonlst = new Gson().toJson(lst);
        response.getWriter().write(gsonlst);

    }
}
