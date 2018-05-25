/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.sql.PreparedStatement;

/*
 * @author u27brvz14
 * Pattern Chain of responsibility
 */
public interface IPreparedStatementUpdatable {

    //this interface helps to treat different classes uniformly(that have the same responsibility, but different types,e.a. paginaton and filters so on)
    public void updatePreparedStatement(PreparedStatementStruct ps);

    public void setNextUpdater(IPreparedStatementUpdatable updater);
}
