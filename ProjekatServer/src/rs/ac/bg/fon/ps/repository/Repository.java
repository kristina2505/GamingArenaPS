/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.ac.bg.fon.ps.repository;

import java.util.List;

/**
 *
 * @author Lenovo
 */
public interface Repository <T> {
    public void connect() throws Exception;
    
    public void disconnect() throws Exception;
    
    public void commit() throws Exception;
    
    public void rollback() throws Exception;
    
    public List<T> getAll() throws Exception;
    
    public List<T> getAll(T param) throws Exception;
    
    public void add(T param) throws Exception;
    
    public T get(T param) throws Exception;
    
    public void edit(T param) throws Exception;
    
    public void delete(T param) throws Exception;
    
    public List<T> getAllPoUslovu(T param) throws Exception;
}
