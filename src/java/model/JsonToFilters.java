/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonSyntaxException;
import java.util.List;
import java.util.function.Consumer;
import org.apache.jasper.tagplugins.jstl.core.ForEach;

/**
 *
 * @author u27brvz14
 */
public class JsonToFilters {

    public enum FilterType {
        LessThan, LessEquelsThan, GreaterThan, GreaterEquelsThan, Like, In, Between
    };

    private JsonToFilters filter;
    protected String fieldName;
    protected List<String> values;
    protected String type;

    public void SetFiltersFromJSON(String filters) {
        try {
            JsonParser jsonParser = new JsonParser();
            JsonArray jsonArray = (JsonArray) jsonParser.parse(filters);
            
            jsonArray.forEach(element -> {
                
            });
            
        } catch (JsonSyntaxException ex) {

        }
    }

    public String toQuery() throws Exception {

        String s = filter.toQuery();
        StringBuilder sb = new StringBuilder(s);
        if (s.trim().toLowerCase().startsWith("where")) {
            sb.append(" and (")
                    .append(")");
        } else {

        }
        return "";
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

    public JsonToFilters getFilter() {
        return filter;
    }

    public void setFilter(JsonToFilters filter) {
        this.filter = filter;
    }
}

class FilterLessThan extends JsonToFilters {

    @Override
    public String toQuery() throws Exception {
        if (values.size() > 1) {
            throw new Exception(this.getClass().getName().concat(" too many parameters"));
        }
        StringBuilder sb = new StringBuilder(fieldName);
        getValues().forEach(value -> {
            sb.append("<").append(value);
        });
        return sb.toString();
    }
}

class FilterLessEquelsThan extends JsonToFilters {

    @Override
    public String toQuery() throws Exception {
        if (values.size() > 1) {
            throw new Exception(this.getClass().getName().concat(" too many parameters"));
        }
        StringBuilder sb = new StringBuilder(fieldName);
        getValues().forEach(value -> {
            sb.append("<=").append(value);
        });
        return sb.toString();
    }
}

class FilterGreaterThan extends JsonToFilters {

    @Override
    public String toQuery() throws Exception {
        if (values.size() > 1) {
            throw new Exception(this.getClass().getName().concat(" too many parameters"));
        }
        StringBuilder sb = new StringBuilder(fieldName);
        getValues().forEach(value -> {
            sb.append(">").append(value);
        });
        return sb.toString();
    }
}

class FilterGreaterEquelsThan extends JsonToFilters {

    @Override
    public String toQuery() throws Exception {
        if (values.size() > 1) {
            throw new Exception(this.getClass().getName().concat(" too many parameters"));
        }
        StringBuilder sb = new StringBuilder(fieldName);
        getValues().forEach(value -> {
            sb.append(">=").append(value);
        });
        return sb.toString();
    }
}

class FilterLike extends JsonToFilters {

    @Override
    public String toQuery() throws Exception {
        if (values.size() > 1) {
            throw new Exception(this.getClass().getName().concat(" too many parameters"));
        }
        StringBuilder sb = new StringBuilder(fieldName);
        getValues().forEach(value -> {
            sb.append(" like ").append("%" + value + "%");
        });
        return sb.toString();
    }
}

class FilterIn extends JsonToFilters {

    @Override
    public String toQuery() throws Exception {
        StringBuilder sb = new StringBuilder(fieldName + " in (");
        getValues().forEach(value -> {
            sb.append(value).append(",");
        });
        sb.append(")");
        return sb.toString();
    }
}

class FilterBetween extends JsonToFilters {

    @Override
    public String toQuery() throws Exception {
        if (values.size() > 2) {
            throw new Exception(this.getClass().getName().concat(" too many parameters"));
        }

        StringBuilder sb = new StringBuilder(fieldName).append(" between ");
        getValues().forEach((String value) -> {
            try {
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
