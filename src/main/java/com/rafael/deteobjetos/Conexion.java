/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rafael.deteobjetos;


import java.sql.*;

public class Conexion {
    public static Connection con;
    private static String bd = "deteccion";
    private static String usr = "admin";
    public static String passw = "1234";
    public static String url = "jdbc:mysql://localhost:3306/"+bd;
    
    public static Connection abrir(){
        if(con != null){
            return con;
        }
        try{
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection(url, usr, passw);
            System.out.println("Se abrio la conexion");
        } catch (Exception e){
            System.out.println("Error en la conexion...");
            e.printStackTrace();
        }
        return con;
    }
    
    public static void cerrar(){
        try{
            if(con != null){
                con.close();
            }
        } catch( Exception e){
            System.out.println("Erro: no se logro cerrar la conexion\n"+e);
        }
    }
}

