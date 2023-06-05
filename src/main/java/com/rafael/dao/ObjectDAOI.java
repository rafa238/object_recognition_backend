package com.rafael.dao;

import com.rafael.model.ObjectCamera;
import java.util.List;

public interface ObjectDAOI {
    List<ObjectCamera> list(int id);
    ObjectCamera getObject(int id);
    int updateObject(ObjectCamera object);
    int deleteObject(int id);
    int insertObject(ObjectCamera object);
}
