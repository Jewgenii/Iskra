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
import model.PreparedStatementStruct;
import model.IPreparedStatementUpdatable;

/**
 *
 * @author u27brvz14
 */
public class FilterLike extends Filter {

    @Override
    public void UpdatePreparedStatement(PreparedStatementStruct ps) {

        if (!values.isEmpty()) { // build query segment only if there is any value available
            PreparedStatement previous = ps.statement;
            try {

                List<String> segments = new ArrayList(values.size());

                values.forEach((_item) -> {
                    segments.add(field + " like ?");
                });

                String prevQuery = ps.toString();
                String joiner = " and ";
                String newQuery = String.join(joiner, prevQuery, "(" + String.join(" or ", segments) + ")");
                ps.queryToPreparedStatement(newQuery);

                int index = 1;
                for (Object value : values) {
                    ps.statement.setString(index, value.toString());
                    ++index;
                }

            } catch (SQLException e) {
                ps.statement = previous;
            }
        }

        super.UpdatePreparedStatement(ps);
    }
}
