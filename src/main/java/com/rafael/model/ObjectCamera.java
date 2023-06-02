package com.rafael.model;

import org.json.simple.JSONObject;


public class ObjectCamera {
    private String name;
    private String route;
    private int id;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRoute() {
        return route;
    }

    public void setRoute(String route) {
        this.route = route;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    public JSONObject parsetToJson(){
        JSONObject obj = new JSONObject();
        obj.put("route", this.route);
        obj.put("name", this.name);
        obj.put("id", this.id);
        
        return obj;
    }
    
}
