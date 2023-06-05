/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rafael.servlets;

import com.rafael.dao.ObjectDAO;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.simple.JSONObject;

public class DeleteImage extends HttpServlet {

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
        JSONObject obj = new JSONObject();
        try{
            int id = Integer.parseInt(request.getParameter("id"));
            System.out.println(id);
            ObjectDAO odao = new ObjectDAO();
            int ans = odao.deleteObject(id);
            System.out.println(ans);
            if(ans >= 1){
                obj.put("message", "Un elmento ha sido eliminado");
                obj.put("id", id);
            } else if(ans <= 0){
                obj.put("error", "Algo ha ido mal y no se elimino nada1");
            }

        } catch(Exception e){
            e.printStackTrace();
            obj.put("error", "Algo ha ido mal y no se elimino nada2");
        }
        
        out.print(obj);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
