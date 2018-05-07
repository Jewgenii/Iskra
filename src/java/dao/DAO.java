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
import java.util.List;
import java.util.Map;
import model.PagePagination;
import org.apache.jasper.tagplugins.jstl.core.ForEach;
import util.DbUtil;

public abstract class DAO {

    protected Connection connection = null;

    public DAO() {
        connection = DbUtil.getConnection();
    }

    public DAO(Connection connection) {
        this.connection = connection;
    }

    public void ChangeConnection(Connection connection) {
        this.connection = connection;
    }

    protected Map<String, String> getParams(Object... ObjParams) {
        Map<String, String> map = new HashMap<>();
        for (Object obj : ObjParams) {
            if (obj.getClass().getCanonicalName().equals(PagePagination.class.getCanonicalName())) {
                PagePagination p = (PagePagination) obj;
                map.put("limit", String.valueOf(p.getLimit()));
                map.put("offset", String.valueOf(p.getOffset()));
            }
            if (true) {

            }
        }
        return map;
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
}
