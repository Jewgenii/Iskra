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
import model.IPreparedStatementCreatable;
import model.JsonToFilters;
import model.Naimesql;
import model.JsonToPagination;

/**
 *
 * @author u27brvz14
 */
public class NaimesqlDAO extends DAO {

    private PreparedStatement preparedStatement = null;

    @Override
    public List<Object> select(Object... obj) {
        List<Object> naimesql = new ArrayList<>();

        JsonToPagination pagination = this.getPagination(obj);
        JsonToFilters filters = this.getFilters(obj);
        List<IPreparedStatementCreatable> updaters = new ArrayList();

        try {
            String query = "select (osd_c||osd_r) as osd,naim,nizv from clippersql.naimesql";
            preparedStatement = connection.prepareStatement(query);

            //updaters.addAll(filters.getFilter()); // or sort by type and get some particular filter objects from the colletion
            updaters.add(pagination); // add as the last parameter e.a. pagination
            updaters.forEach((updater) -> {
                preparedStatement = updater.UpdatePreparedStatement(preparedStatement);
            });

            // java cant pass a value into function by reference that`s why must return a value
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

    public List<String> getDistinctNaim(String term) {
        List<String> lst = new ArrayList<>(10);
        String query
                = new StringBuilder("select distinct naim from clippersql.naimesql where lower(naim) like lower(?) order by naim limit 10")
                        .toString();
        try {
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, term + "%");

            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                lst.add(rs.getString("naim"));
            }
        } catch (SQLException e) {
            System.out.println(e);
        }

        return lst;
    }

    public List<String> getDistinctOSD(String term) {
        List<String> lst = new ArrayList<>(10);
        String query
                = new StringBuilder("select distinct (osd_c||osd_r) as osd from clippersql.naimesql where lower(osd_c||osd_r) like lower(?) order by (osd_c||osd_r) limit 10")
                        .toString();
        try {
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, term + "%");

            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                lst.add(rs.getString("osd"));
            }
        } catch (SQLException e) {
            System.out.println(e);
        }

        return lst;
    }
}
