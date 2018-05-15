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
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import org.apache.commons.lang.*;

/**
 *
 * @author u27brvz14 Pattern Chain of responsibility
 */
public class JsonToPagination implements IPreparedStatementCreatable {

    static int MinOffset = 0;
    static int MinLimit = 0;
    static int MaxLimit = 100;

    private int limit;
    private int offset;
    private IPreparedStatementCreatable updater;

    @Override
    public void SetNextUpdater(IPreparedStatementCreatable updater) {
        this.updater = updater;
    }

    @Override
    public void UpdatePreparedStatement(PreparedStatementStruct ps) {

        PreparedStatement previous = ps.statement;

        try {
            String prevQuery = ps.statement.toString();
            String newQuery = String.join(" ", prevQuery, "limit ? offset ?");
            ps.statement = ps.statement.getConnection().prepareStatement(newQuery);
            // an exception can occur down here
            ps.statement.setInt(1, limit);
            ps.statement.setInt(2, offset);

        } catch (SQLException ex) {
            ps.statement = previous;// safe rollback to previous statement of ps.statement
        }
        if (updater != null) {
            updater.UpdatePreparedStatement(ps);
        }
    }

    public JsonToPagination(int limit, int offset) {
        setLimit(limit);
        setOffset(offset);
    }

    public void SetPagination(String _pagination) {

        try {
            JsonToPagination p = new Gson().fromJson(_pagination, this.getClass());
            int _limit = p.getLimit();
            int _offset = p.getOffset();

            this.setLimit(_limit);
            this.setOffset(_offset);

        } catch (JsonSyntaxException e) {
            System.out.println("model.PagePagination.SetPagination()" + e.toString());
        }
    }

    public void SetPagination(int limit, int offset) {
        this.limit = limit;
        this.offset = offset;
    }

    public void setOffset(String offset) {
        try {
            int _offset = Integer.parseInt(offset);
            this.setOffset(_offset);

        } catch (NumberFormatException e) {
            System.out.println(e);
        }
    }

    public void setLimit(String limit) {
        try {
            int _limit = Integer.parseInt(limit);
            this.setLimit(_limit);

        } catch (NumberFormatException e) {
            System.out.println(e);
        }
    }

    @Override
    public String toString() {
        String p = new Gson().toJson(this);
        //return StringEscapeUtils.escapeJavaScript(p);
        return p;
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int _limit) {
        if (_limit <= MinLimit) {
            this.limit = MinLimit;
        } else {
            this.limit = _limit <= MaxLimit ? _limit : MaxLimit;
        }
    }

    public int getOffset() {
        return offset;
    }

    public void setOffset(int _offset) {
        if (_offset <= MinOffset) {
            this.offset = MinOffset;
        } else {
            this.offset = _offset;
        }
    }
}
