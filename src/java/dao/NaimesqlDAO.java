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
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Filters.Filter;
import model.JsonToFilters;
import model.Naimesql;
import model.jsonToPagination;
import model.PreparedStatementStruct;
import model.IPreparedStatementUpdatable;

/**
 *
 * @author u27brvz14
 */
public class NaimesqlDAO extends DAO {

    private PreparedStatement preparedStatement = null;

    @Override
    public List<Object> select(Object... obj) {

        List<Object> naimesql = new ArrayList<>(100);

        PreparedStatementStruct struct = null;
        jsonToPagination pagination = getJsonToPagination(obj);
        JsonToFilters jfilters = getJsonToFilters(obj);

        struct = (jfilters == null) ? selectWithoutFilters(pagination)
                : selectWithFilters(jfilters, pagination);

        if (struct != null) {
            try {
                ResultSet rs = struct.statement.executeQuery();

                while (rs.next()) {
                    Naimesql n = new Naimesql();

                    n.setNaim(rs.getString("naim"));
                    n.setNizv(rs.getString("nizv"));
                    n.setOsd(rs.getString("osd"));

                    naimesql.add(n);
                }
            } catch (SQLException ex) {

            }
        }

        return naimesql;
    }

    private PreparedStatementStruct selectWithoutFilters(jsonToPagination _pagination) {

        jsonToPagination pagination = _pagination;
        PreparedStatementStruct prepStatement = null;

        try {
            prepStatement
                    = new PreparedStatementStruct(this, "select (osd_c||osd_r) as osd,naim,nizv from clippersql.naimesql");
            pagination.updatePreparedStatement(prepStatement);
        } catch (SQLException ex) {
            System.out.println(ex);
        }

        return prepStatement;
    }

    private PreparedStatementStruct selectWithFilters(JsonToFilters _filters, jsonToPagination _pagination) {

        jsonToPagination pagination = _pagination;
        JsonToFilters filters = _filters;

        IPreparedStatementUpdatable fitlerExecutor = null;
        PreparedStatementStruct filtersStatement = null;

        IPreparedStatementUpdatable fitlerExecutor2 = null;
        PreparedStatementStruct paginationStatement = null;

        try {

            filtersStatement = new PreparedStatementStruct(this, "");
            paginationStatement = new PreparedStatementStruct(this, "where ");
            try {

                IPreparedStatementUpdatable naimLike = filters.getFilter("naim");
                IPreparedStatementUpdatable osd = filters.getFilter("osd");

                fitlerExecutor = osd;
                fitlerExecutor.setNextUpdater(naimLike);
                naimLike.setNextUpdater(pagination);

                fitlerExecutor2 = pagination;

                fitlerExecutor2.updatePreparedStatement(paginationStatement);

            } catch (Exception e) {
                fitlerExecutor = pagination;
            }

            fitlerExecutor.updatePreparedStatement(filtersStatement);

            String querySegment = "select (osd_c||osd_r) as osd,naim,nizv from clippersql.naimesql where"
                    + filtersStatement.toString()
                    + paginationStatement.toString();

            filtersStatement.prependToQuery(querySegment);// updates the struct`s PreparedStatement object 

        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return filtersStatement;
    }

    public List<String> getDistinctNaim(String term) {
        List<String> lst = new ArrayList<>(10);

        String query
                = new StringBuilder("select distinct naim from clippersql.naimesql where lower(naim) like lower(?) order by naim limit 10")
                        .toString();
        try {
            preparedStatement = connection.prepareStatement(query);

            if (!term.endsWith("*")) {
                term += "*";
            }

            term = term.replace("*", "%");

            preparedStatement.setString(1, term);

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
