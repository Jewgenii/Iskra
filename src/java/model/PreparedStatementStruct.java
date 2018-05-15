
package model;

import java.sql.PreparedStatement;

/**
 *
 * @author u27brvz14
 * helper class for data exchange.
 * i needed it for IPreparedStatementCreatable interface :
 * public void UpdatePreparedStatement(PreparedStatementStruct ps);
 * to update ps object without returning a value because java has no passing arguments
 * to functions by reference, only by value
 */
public class PreparedStatementStruct {

    public PreparedStatementStruct(PreparedStatement statement) {
        this.statement = statement;
    }

    public PreparedStatement statement;
}
