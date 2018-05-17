/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonSyntaxException;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import model.Filters.Filter;
import model.Filters.FilterLike;
import org.apache.jasper.tagplugins.jstl.core.ForEach;

/**
 *
 * @author u27brvz14
 */
public class JsonToFilters {

    private List<Filter> lstFilters;

    public JsonToFilters(String filters) {
        lstFilters = new ArrayList();
        this.SetFiltersFromJSON(filters);
    }

    public void SetFiltersFromJSON(String filters) {
        try {
            JsonParser jsonParser = new JsonParser();
            JsonArray jsonFiltersArray = (JsonArray) jsonParser.parse(filters);
            Filter filter = null;

            for (JsonElement jsonFilter : jsonFiltersArray) {

                JsonObject obj = jsonFilter.getAsJsonObject();

                String type = obj.get("type").getAsString();
                
                JsonArray values = obj.getAsJsonArray("values");
                obj.remove("values");

                JsonArray newArr = new JsonArray();
                values.forEach(el -> {
                    if (!el.getAsString().isEmpty()) {
                        newArr.add(el);
                    }
                });
                //if (newArr.size() > 0) { // if there is any value in values then adds a filter to filter collection
                obj.add("values", newArr);

                switch (type) {
                    case "like":
                        filter = new Gson().fromJson(obj.toString(), FilterLike.class);
                        break;
                    default:
                        break;
                }
                lstFilters.add(filter);
            }
        } catch (JsonSyntaxException ex) {

        }
    }

    public List<Filter> getFilter() {
        return lstFilters;
    }

    public Filter getFilter(String name) {
        String _name = name.toLowerCase();

        for (Filter fl : lstFilters) {
            if (fl.getFieldName().equals(_name)) {
                return fl;
            }
        }
        return null;
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
