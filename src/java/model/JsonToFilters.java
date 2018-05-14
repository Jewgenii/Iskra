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
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import model.Filters.Filter;
import org.apache.jasper.tagplugins.jstl.core.ForEach;

/**
 *
 * @author u27brvz14
 */
public class JsonToFilters {

    private List<IPreparedStatementCreatable> filter;

    public JsonToFilters(String filters) {
        filter = new ArrayList();
        this.SetFiltersFromJSON(filters);
    }

    public JsonToFilters(JsonToFilters filters) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void SetFiltersFromJSON(String filters) {
        try {
            JsonParser jsonParser = new JsonParser();
            JsonArray jsonArray = (JsonArray) jsonParser.parse(filters);

            jsonArray.forEach(element -> {

                // creating new filters depending on the input values
                filter.add(null);
            });

        } catch (JsonSyntaxException ex) {

        }
    }

    public List<IPreparedStatementCreatable> getFilter() {
        return filter;
    }
}

//
//
//class FilterLessThan extends JsonToFilters {
//
//    @Override
//    public String toQuery() throws Exception {
//        if (values.size() > 1) {
//            throw new Exception(this.getClass().getName().concat(" too many parameters"));
//        }
//        StringBuilder sb = new StringBuilder(fieldName);
//        getValues().forEach(value -> {
//            sb.append("<").append(value);
//        });
//        return sb.toString();
//    }
//}
//
//class FilterLessEquelsThan extends JsonToFilters {
//
//    @Override
//    public String toQuery() throws Exception {
//        if (values.size() > 1) {
//            throw new Exception(this.getClass().getName().concat(" too many parameters"));
//        }
//        StringBuilder sb = new StringBuilder(fieldName);
//        getValues().forEach(value -> {
//            sb.append("<=").append(value);
//        });
//        return sb.toString();
//    }
//}
//
//class FilterGreaterThan extends JsonToFilters {
//
//    @Override
//    public String toQuery() throws Exception {
//        if (values.size() > 1) {
//            throw new Exception(this.getClass().getName().concat(" too many parameters"));
//        }
//        StringBuilder sb = new StringBuilder(fieldName);
//        getValues().forEach(value -> {
//            sb.append(">").append(value);
//        });
//        return sb.toString();
//    }
//}
//
//class FilterGreaterEquelsThan extends JsonToFilters {
//
//    @Override
//    public String toQuery() throws Exception {
//        if (values.size() > 1) {
//            throw new Exception(this.getClass().getName().concat(" too many parameters"));
//        }
//        StringBuilder sb = new StringBuilder(fieldName);
//        getValues().forEach(value -> {
//            sb.append(">=").append(value);
//        });
//        return sb.toString();
//    }
//}
//
//class FilterLike extends JsonToFilters {
//
//    @Override
//    public String toQuery() throws Exception {
//        if (values.size() > 1) {
//            throw new Exception(this.getClass().getName().concat(" too many parameters"));
//        }
//        StringBuilder sb = new StringBuilder(fieldName);
//        getValues().forEach(value -> {
//            sb.append(" like ").append("%" + value + "%");
//        });
//        return sb.toString();
//    }
//}
//
//class FilterIn extends JsonToFilters {
//
//    @Override
//    public String toQuery() throws Exception {
//        StringBuilder sb = new StringBuilder(fieldName + " in (");
//        getValues().forEach(value -> {
//            sb.append(value).append(",");
//        });
//        sb.append(")");
//        return sb.toString();
//    }
//}
//
//class FilterBetween extends JsonToFilters {
//
//    @Override
//    public String toQuery() throws Exception {
//        if (values.size() > 2) {
//            throw new Exception(this.getClass().getName().concat(" too many parameters"));
//        }
//
//        StringBuilder sb = new StringBuilder(fieldName).append(" between ");
//        getValues().forEach((String value) -> {
//            try {
//                Integer.parseInt(value);
//            } catch (NumberFormatException e) {
//                System.out.println(e);
//                throw e;
//            }
//            sb.append(value).append("%" + value + "%");
//        });
//        return sb.toString();
//    }
//}
