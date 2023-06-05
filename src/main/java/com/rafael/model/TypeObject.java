package com.rafael.model;

import org.json.simple.JSONObject;


public class TypeObject {
    private String nameType;
    private int idType;
    
    public TypeObject(){}
    public TypeObject(int idType){
        this.idType = idType;
    }
    
    public TypeObject(int idType, String nameType){
        this.nameType = nameType;
        this.idType = idType;
    }
    
    public String getType() {
        return nameType;
    }

    public void setNameType(String type) {
        this.nameType = type;
    }

    public int getIdType() {
        return idType;
    }

    public void setIdType(int idType) {
        this.idType = idType;
    }
    
    public JSONObject parsetToJson(){
        JSONObject obj = new JSONObject();
        obj.put("id", this.idType);
        obj.put("name", this.nameType);
        return obj;
    }
}
