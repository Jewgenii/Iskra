package controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.Vp44150sqlDao;
import com.google.gson.Gson;
import java.io.PrintWriter;

public class AutocompleteKizController extends HttpServlet {
        private static final long serialVersionUID = 1L;

        protected void doGet(HttpServletRequest request,
                HttpServletResponse response) throws ServletException, IOException {
                response.setContentType("text/html");
                request.setCharacterEncoding("utf-8");
                response.setCharacterEncoding("utf-8");
                PrintWriter out = response.getWriter();
                response.setContentType("application/json");
                try {
                        String term = request.getParameter("term");
                        
                        System.out.println("Data from ajax call " + term);
                            /*Преобразование списка кизов в json формат*/
                        Vp44150sqlDao vp44150sqlDao = new Vp44150sqlDao();
                        ArrayList<String> list = vp44150sqlDao.getDistinctKiz(term);

                        String searchList = new Gson().toJson(list);
                        response.getWriter().write(searchList);
                } catch (Exception e) {
                        System.err.println(e.getMessage());
                }
        }
}