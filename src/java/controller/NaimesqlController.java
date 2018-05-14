/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.sun.org.apache.xalan.internal.xsltc.compiler.util.Util;
import dao.DAO;
import dao.NaimesqlDAO;
import model.JsonToPagination;
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
import java.sql.*;
import model.*;
import model.Filters.Filter;
import model.Filters.FilterLike;
import util.DbUtil;
import model.Naimesql;
import org.apache.commons.lang.*;

/**
 *
 * @author u27brvz14
 */
public class NaimesqlController extends HttpServlet {

    private DAO _naimesqlDao = null;
    private JsonToPagination _pagination = null;

    public NaimesqlController() {
        _naimesqlDao = new NaimesqlDAO();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        RequestDispatcher view = request.getRequestDispatcher("/naimesql.jsp");
        view.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        response.setContentType("application/json");

        String paginationParam = request.getParameter("pagination");

        String filtersParam = request.getParameter("filters");

        _pagination = new JsonToPagination(50, 0);
        _pagination.SetPagination(paginationParam);

        JsonToFilters filters = new JsonToFilters(filtersParam);

        List<Object> naimesql = _naimesqlDao.select(_pagination, filters);

        JsonObject j = new JsonObject();
        j.addProperty("tableContent", new Gson().toJson(naimesql));
        j.addProperty("pagination", _pagination.toString());
        j.addProperty("filters", "lala");

        response.getWriter().write(j.toString());
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
}
