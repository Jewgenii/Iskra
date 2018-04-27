/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.List;
import org.apache.jasper.tagplugins.jstl.core.ForEach;

/**
 *
 * @author u27brvz14
 */
public abstract class Filter {

    protected String FieldName;
    protected List<String> Values;

    public abstract String toQuery() throws Exception;
    //   List<String> values;

    /**
     * @return the FieldName
     */
    public String getFieldName() {
        return FieldName;
    }

    /**
     * @param FieldName the FieldName to set
     */
    public void setFieldName(String FieldName) {
        this.FieldName = FieldName;
    }

    /**
     * @return the Values
     */
    public List<String> getValues() {
        return Values;
    }

    /**
     * @param Values the Values to set
     */
    public void setValues(List<String> Values) {
        this.Values = Values;
    }
}

class FilterLessThan extends Filter {

    @Override
    public String toQuery() throws Exception {
        if (Values.size() > 1) {
            throw new Exception(this.getClass().getName().concat(" too many parameters"));
        }
        StringBuilder sb = new StringBuilder(FieldName);
        getValues().forEach(value -> {
            sb.append("<").append(value);
        });
        return sb.toString();
    }
}

class FilterGreaterThan extends Filter {

    @Override
    public String toQuery() throws Exception {
        if (Values.size() > 1) {
            throw new Exception(this.getClass().getName().concat(" too many parameters"));
        }
        StringBuilder sb = new StringBuilder(FieldName);
        getValues().forEach(value -> {
            sb.append(">").append(value);
        });
        return sb.toString();
    }
}
