package model;

import dao.DAO;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 *
 * @author u27brvz14 helper class for data exchange. i needed it for
 * IPreparedStatementCreatable interface : public void
 * UpdatePreparedStatement(PreparedStatementStruct ps); to update ps object
 * without returning a value because java has no passing arguments to functions
 * by reference, only by value
 */
public class PreparedStatementStruct {

    private DAO dao;
    public PreparedStatement statement;

    public PreparedStatementStruct(PreparedStatement statement) {
        this.statement = statement;
    }

    public PreparedStatementStruct(DAO dao, String query) throws SQLException {
        this.dao = dao;
        this.statement = dao.getConnection().prepareStatement(query);
    }

    public PreparedStatementStruct(DAO dao) throws SQLException {
        this.dao = dao;
        this.statement = dao.getConnection().prepareStatement("");
    }

    public void queryToPreparedStatement(String _segment) throws SQLException {
        this.statement = dao.getConnection().prepareStatement(_segment);
    }

    public void appendToQuery(String _segment) throws SQLException {
        String segment = String.join(" ", this.toString(), _segment);
        queryToPreparedStatement(segment);
    }

    public void appendToQuery(PreparedStatement _statement) throws SQLException {
        String segment = String.join(" ", this.toString(), _statement.toString());
        queryToPreparedStatement(segment);
    }

    public void prependToQuery(PreparedStatement _statement) throws SQLException {
        String segment = String.join(" ", _statement.toString(), this.toString());
        queryToPreparedStatement(segment);
    }

    public void prependToQuery(String _segment) throws SQLException {
        String segment = String.join(" ", _segment, this.toString());
        queryToPreparedStatement(segment);
    }

    @Override
    public String toString() {
        return this.statement.toString();
    }
}
