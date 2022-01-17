/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.ac.bg.fon.ps.repository.db;

import rs.ac.bg.fon.ps.repository.Repository;

/**
 *
 * @author Lenovo
 */
public interface DbRepository <T> extends Repository<T>{

    @Override
    public default void connect() throws Exception {
        DbConnectionFactory.getInstance().getConnection();
    }

    @Override
    public default void disconnect() throws Exception {
        DbConnectionFactory.getInstance().getConnection().close();
    }

    @Override
    public default void commit() throws Exception {
        DbConnectionFactory.getInstance().getConnection().commit();
    }

    @Override
    public default void rollback() throws Exception {
        DbConnectionFactory.getInstance().getConnection().rollback();
    }
    
    
    
}
