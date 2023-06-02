package com.rafael.servlets;

import com.rafael.deteobjetos.Conexion;
import com.rafael.model.ObjectCamera;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;


public class Objects extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("application/json");
        response.addHeader("Access-Control-Allow-Origin", "*");
        response.setCharacterEncoding("UTF-8");
        
        Connection con = Conexion.abrir();
        PrintWriter out = response.getWriter();
        try {
            PreparedStatement ps;
            ps = con.prepareStatement("SELECT * FROM object GROUP BY nameObj;");
            ResultSet rs = ps.executeQuery();
            
            JSONArray list = new JSONArray();
            while(rs.next()){
                ObjectCamera obj = new ObjectCamera();
                obj.setId(rs.getInt("id"));
                obj.setName(rs.getString("nameObj"));
                obj.setRoute(rs.getString("route"));
                list.add(obj.parsetToJson());
            }
            out.print(list);
        } catch (SQLException ex) {
            out.print(ex);
        }
        
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
