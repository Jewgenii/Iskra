/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import model.JsonToFilters;
import model.jsonToPagination;
import model.PreparedStatementStruct;
import org.apache.jasper.tagplugins.jstl.core.ForEach;
import util.DbUtil;

public abstract class DAO {

    protected Connection connection = null;

    public DAO() {
        connection = DbUtil.getConnection();
    }

    public Connection getConnection() {
        return connection;
    }

    public DAO(Connection connection) {
        this.connection = connection;
    }

    public void ChangeConnection(Connection connection) {
        this.connection = connection;
    }

    public int insert(Object... o) {
        throw new UnsupportedOperationException();
    }

    public List<Object> select(Object... obj) {
        throw new UnsupportedOperationException();
    }

    public int update(Object o, Integer id) {
        throw new UnsupportedOperationException();
    }

    public int delete(Integer id) {
        throw new UnsupportedOperationException();
    }

    public static jsonToPagination getJsonToPagination(Object... ObjParams) {
        for (Object obj : ObjParams) {
            if (obj.getClass().getCanonicalName().equals(jsonToPagination.class.getCanonicalName())) {
                return (jsonToPagination) obj;
            }
        }
        return null;
    }

    public static JsonToFilters getJsonToFilters(Object... ObjParams) {
        for (Object obj : ObjParams) {
            if (obj.getClass().getCanonicalName().equals(JsonToFilters.class.getCanonicalName())) {
                return (JsonToFilters) obj;
            }
        }
        return null;
    }
}
