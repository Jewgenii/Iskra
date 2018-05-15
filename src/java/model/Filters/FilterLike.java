/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.Filters;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.IPreparedStatementCreatable;
import model.PreparedStatementStruct;

/**
 *
 * @author u27brvz14
 */
public class FilterLike extends Filter implements IPreparedStatementCreatable {

    @Override
    public void UpdatePreparedStatement(PreparedStatementStruct ps) {
        PreparedStatement previous = ps.statement;
        try {
            if (values.size() > 3 || values.isEmpty()) { // approximately 3 
                throw new Exception(this.getClass().getName().concat(" too many parameters or null params"));
            }
            List<String> segments = new ArrayList(values.size());

            for (int i = 0; i < values.size(); i++) {
                segments.add(field + " like ?");
            }

            String prevQuery = ps.statement.toString();
            String joiner = !prevQuery.toLowerCase().contains("where") ? " where " : " and ";
            String newQuery = String.join(joiner, prevQuery, "(" + String.join(" or ", segments) + ")");
            ps.statement = ps.statement.getConnection().prepareStatement(newQuery);

            int index = 1;
            for (Object value : values) {
                ps.statement.setString(index, value + "%");
                ++index;
            }

        } catch (Exception e) {
            ps.statement = previous;
        }
        super.UpdatePreparedStatement(ps);
    }
}
