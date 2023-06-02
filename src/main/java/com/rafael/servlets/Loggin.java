package com.rafael.servlets;

import com.rafael.deteobjetos.Conexion;
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
import org.json.simple.JSONObject; 

public class Loggin extends HttpServlet {

       @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException { 
        response.setContentType("application/json");
        response.addHeader("Access-Control-Allow-Origin", "*");
        response.setCharacterEncoding("UTF-8");
        
        PrintWriter out = response.getWriter();
        String username = request.getParameter("username");
        String passwd = request.getParameter("passwd");
        Connection con = Conexion.abrir();
        try {
            PreparedStatement ps;
            ps = con.prepareStatement("SELECT * FROM login WHERE username=? AND passwd=?");
            ps.setString(1, username);
            ps.setString(2, passwd);
            System.out.println("username: " + username);
            System.out.println("password: " + passwd);
            ResultSet rs = ps.executeQuery();
            JSONObject obj = new JSONObject();
            if(rs.next()){
                
                obj.put("username", rs.getString("username"));
                obj.put("id", rs.getInt("id"));
                //out.print(rs.getInt("id"));
                //out.print(rs.getString("username"));
            } else {
                
                obj.put("error", "Usuario no encontrado en la base de datos");
            }
            out.print(obj);
            
        } catch (SQLException ex) {
            out.print(ex);
        }
        
    }
}
