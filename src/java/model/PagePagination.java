/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import com.google.gson.Gson;
import com.google.gson.*;
import com.google.gson.JsonSyntaxException;
import com.google.gson.stream.JsonReader;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author u27brvz14
 */
public class PagePagination {
   
    private int limit;
    private int offset;

    public PagePagination(int limit, int offset) {
        this.limit = limit;
        this.offset = offset;
    }

    public void SetPagination(String _pagination) {

        try {
            PagePagination p = new Gson().fromJson(_pagination, this.getClass());
            this.setLimit(p.getLimit());
            this.setOffset(p.getOffset());

        } catch (Exception e) {
            System.out.println("model.PagePagination.SetPagination()" + e.toString());
        }
    }

    public void SetPagination(int limit, int offset) {
        this.limit = limit;
        this.offset = offset;
    }

    public void setOffset(String offset) {
        try {
            this.setOffset(Integer.parseInt(offset));

        } catch (NumberFormatException e) {
            System.out.println(e);
        }
    }

    public void setLimit(String limit) {
        try {
            this.setLimit(Integer.parseInt(limit));

        } catch (NumberFormatException e) {
            System.out.println(e);
        }
    }

    @Override
    public String toString() {
        return new Gson().toJson(this);
    }

    /**
     * @return the limit
     */
    public int getLimit() {
        return limit;
    }

    /**
     * @param limit the limit to set
     */
    public void setLimit(int limit) {
        this.limit = limit;
    }

    /**
     * @return the offset
     */
    public int getOffset() {
        return offset;
    }

    /**
     * @param offset the offset to set
     */
    public void setOffset(int offset) {
        this.offset = offset;
    }
}
