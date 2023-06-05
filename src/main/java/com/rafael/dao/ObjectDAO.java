package com.rafael.dao;

import com.rafael.deteobjetos.Conexion;
import com.rafael.model.ObjectCamera;
import com.rafael.model.TypeObject;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;


public class ObjectDAO implements ObjectDAOI{

    @Override
    public List<ObjectCamera> list(int idType) {
        List<ObjectCamera> objects = new ArrayList<>();
        try {
            Connection con = Conexion.abrir();
            PreparedStatement ps = con.prepareStatement("SELECT * FROM object o INNER JOIN type_obj tyo ON o.id_type = tyo.idType WHERE tyo.idType=?");
            ps.setInt(1, idType);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                ObjectCamera obj = new ObjectCamera();
                obj.setId(rs.getInt("id"));
                obj.setName(rs.getString("nameObj"));
                obj.setRoute(rs.getString("route"));
                TypeObject type = new TypeObject(rs.getInt("idType"), rs.getString("nametype"));
                obj.setType(type);
                objects.add(obj);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ObjectDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return objects;
    }

    @Override
    public ObjectCamera getObject(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int updateObject(ObjectCamera object) {
        int result = 0;
        try {
            Connection con = Conexion.abrir();
            PreparedStatement ps = con.prepareStatement("UPDATE object SET nameObj = ?, route = ? WHERE id=?");
            ps.setString(1, object.getName());
            ps.setString(2, object.getRoute());
            ps.setInt(3, object.getId());
            result = ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ObjectDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

    @Override
    public int deleteObject(int id) {
        int result = 0;
        try {
            Connection con = Conexion.abrir();
            PreparedStatement ps = con.prepareStatement("DELETE FROM object WHERE id = ?");
            ps.setInt(1, id);
            result = ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ObjectDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

    @Override
    public int insertObject(ObjectCamera oc) {
        int result = 0;
        try {
            Connection con = Conexion.abrir();
            PreparedStatement ps = con.prepareStatement("INSERT INTO object (nameObj, route, id_type) VALUES (?, ?, ?)");
            ps.setString(1, oc.getName());
            ps.setString(2, oc.getRoute());
            ps.setInt(3, oc.getType().getIdType());
            result = ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ObjectDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }
    
}
