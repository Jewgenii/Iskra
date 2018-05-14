/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.Filters;

import java.sql.PreparedStatement;
import java.util.List;
import model.IPreparedStatementCreatable;
import model.JsonToFilters;

/**
 *
 * @author u27brvz14
 */
public abstract class Filter implements IPreparedStatementCreatable {

    protected Filter filter;
    protected String fieldName;
    protected List<String> values;

   // public static PreparedStatement 
    public String toQuery(PreparedStatement _statement) throws Exception {
        String s = null;
        
        StringBuilder sb = new StringBuilder(s);
        if (s.trim().toLowerCase().startsWith("where")) {
            sb.append(" and (").append(" fieldname ").append(" like or what ").append(" values ").append(")");
        } else {

        }
        return sb.toString();
    }

    public String getFieldName() {
        return fieldName;
    }

    public void setFieldName(String FieldName) {
        this.fieldName = FieldName;
    }

    public List<String> getValues() {
        return values;
    }

    public void setValues(List<String> Values) {
        this.values = Values;
    }

    public Filter getFilter() {
        return filter;
    }

    public void setFilter(Filter filter) {
        this.filter = filter;
    }

    @Override
    public PreparedStatement UpdatePreparedStatement(PreparedStatement ps) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
