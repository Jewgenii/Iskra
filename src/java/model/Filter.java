/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.List;
import java.util.function.Consumer;
import org.apache.jasper.tagplugins.jstl.core.ForEach;

/**
 *
 * @author u27brvz14
 */
public abstract class Filter {

    private Filter filter;
    protected String FieldName;
    protected List<String> Values;

    public abstract String toQuery() throws Exception;

    public String getFieldName() {
        return FieldName;
    }

    public void setFieldName(String FieldName) {
        this.FieldName = FieldName;
    }

    public List<String> getValues() {
        return Values;
    }

    public void setValues(List<String> Values) {
        this.Values = Values;
    }

    public Filter getFilter() {
        return filter;
    }

    public void setFilter(Filter filter) {
        this.filter = filter;
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

class FilterLessEquelsThan extends Filter {

    @Override
    public String toQuery() throws Exception {
        if (Values.size() > 1) {
            throw new Exception(this.getClass().getName().concat(" too many parameters"));
        }
        StringBuilder sb = new StringBuilder(FieldName);
        getValues().forEach(value -> {
            sb.append("<=").append(value);
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

class FilterGreaterEquelsThan extends Filter {

    @Override
    public String toQuery() throws Exception {
        if (Values.size() > 1) {
            throw new Exception(this.getClass().getName().concat(" too many parameters"));
        }
        StringBuilder sb = new StringBuilder(FieldName);
        getValues().forEach(value -> {
            sb.append(">=").append(value);
        });
        return sb.toString();
    }
}

class FilterLike extends Filter {

    @Override
    public String toQuery() throws Exception {
        if (Values.size() > 1) {
            throw new Exception(this.getClass().getName().concat(" too many parameters"));
        }
        StringBuilder sb = new StringBuilder(FieldName);
        getValues().forEach(value -> {
            sb.append(" like ").append("%" + value + "%");
        });
        return sb.toString();
    }
}

class FilterIn extends Filter {

    @Override
    public String toQuery() throws Exception {
        StringBuilder sb = new StringBuilder(FieldName + " in (");
        getValues().forEach(value -> {
            sb.append(value).append(",");
        });
        sb.append(")");
        return sb.toString();
    }
}

class FilterBetween extends Filter {

    @Override
    public String toQuery() throws Exception {
        if (Values.size() > 2) {
            throw new Exception(this.getClass().getName().concat(" too many parameters"));
        }

        StringBuilder sb = new StringBuilder(FieldName).append(" between ");
        getValues().forEach((String value) -> {
            try
            {
                Integer.parseInt(value);
            } catch (NumberFormatException e) {
                System.out.println(e);
                throw e;
            }
            sb.append(value).append("%" + value + "%");
        });
        return sb.toString();
    }
}
