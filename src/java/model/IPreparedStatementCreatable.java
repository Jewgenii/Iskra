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
public interface IPreparedStatementCreatable {

    //this interface helps to teat different classes uniformly(that have the same responsibility, but different types,e.a. paginaton and filters so on)
    public void UpdatePreparedStatement(PreparedStatementStruct ps);

    public void SetNextUpdater(IPreparedStatementCreatable updater);
}
