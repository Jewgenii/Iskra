/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.Filters;

import java.sql.PreparedStatement;
import model.IPreparedStatementCreatable;

/**
 *
 * @author u27brvz14
 */
public class FilterLike extends Filter implements IPreparedStatementCreatable {

    @Override
    public String toQuery(PreparedStatement _statement) throws Exception {
        if (values.size() > 1) {
            throw new Exception(this.getClass().getName().concat(" too many parameters"));
        }
        StringBuilder sb = new StringBuilder(fieldName);
        getValues().forEach(value -> {
            sb.append(" like ").append("%").append(value).append("%");
        });
        return sb.toString();
    }

    @Override
    public PreparedStatement UpdatePreparedStatement(PreparedStatement ps) {
        String oldQuery = ps.toString();
        PreparedStatement new_ps;
        String newQuery;
        try {
            try {
                newQuery = String.join(" ", oldQuery, " limit ? offset ?");
                new_ps = ps.getConnection().prepareStatement(newQuery);

                new_ps.setInt(1, limit);
                new_ps.setInt(2, offset);

            } catch (SQLException ex) {
                new_ps = ps;
            }

        } catch (Exception e) {
        }

        if (this.filter != null) {
            this.filter.UpdatePreparedStatement(new_ps);
        }
        return new_ps;
    }
}
