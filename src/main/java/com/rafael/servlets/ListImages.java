package com.rafael.servlets;

import com.rafael.dao.ObjectDAO;
import com.rafael.model.ObjectCamera;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.simple.JSONArray;


public class ListImages extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("application/json");
        response.addHeader("Access-Control-Allow-Origin", "*");
        response.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();
        
        int idType = Integer.parseInt(request.getParameter("id"));
        ObjectDAO odao = new ObjectDAO();
        List<ObjectCamera> list = odao.list(idType);
        JSONArray array = new JSONArray();
        for(ObjectCamera oc : list){
            array.add(oc.parsetToJson());
        }
        out.print(array);
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
    }


    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
