/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Dictionary;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import model.Naimesql;
import model.PagePagination;

/**
 *
 * @author u27brvz14
 */
public class NaimesqlDao extends DAO {

    private PreparedStatement preparedStatement = null;

    @Override
    public List<Object> select(Object... obj) {
        List<Object> naimesql = new ArrayList<>();
        Map<String, String> map = this.getParams(obj);

        try {
            String query
                    = new StringBuilder("select (osd_c||osd_r) as osd,naim,nizv from clippersql.naimesql")
                            .append(" limit ")
                            .append(" ? ")
                            .append(" offset ")
                            .append(" ? ")
                            .toString();

            preparedStatement = connection.prepareStatement(query);

            preparedStatement.setInt(1, Integer.parseInt(map.get("limit")));
            preparedStatement.setInt(2, Integer.parseInt(map.get("offset")));

            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                Naimesql n = new Naimesql();

                n.setNaim(rs.getString("naim"));
                n.setNizv(rs.getString("nizv"));
                n.setOsd(rs.getString("osd"));
                naimesql.add(n);
            }

        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return naimesql;
    }
}
