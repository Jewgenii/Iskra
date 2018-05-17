/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.Filters;

import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;
import model.JsonToFilters;
import model.PreparedStatementStruct;
import model.IPreparedStatementUpdatable;

/**
 *
 * @author u27brvz14 Pattern Chain of responsibility
 */
public abstract class Filter implements IPreparedStatementUpdatable {

    protected IPreparedStatementUpdatable updater;
    protected String type;
    protected String field;
    protected List<Object> values;

    public Filter() {
        values = new ArrayList();
    }

    public Filter(String type, String fieldName, Object[] values) {
        this();

        this.type = type;
        this.field = fieldName;
        //  this.values = values;
    }

    //  public abstract String toQuery(PreparedStatement _statement);
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getFieldName() {
        return field;
    }

    public void setFieldName(String FieldName) {
        this.field = FieldName;
    }

    public List<Object> getValues() {
        return values;
    }

    public void setValues(List<Object> Values) {
        this.values = Values;
    }

    @Override
    public void UpdatePreparedStatement(PreparedStatementStruct ps) {
        if (this.updater != null) {
            this.updater.UpdatePreparedStatement(ps);
        }
    }

    @Override
    public void SetNextUpdater(IPreparedStatementUpdatable updater) {
        this.updater = updater;
    }
}
