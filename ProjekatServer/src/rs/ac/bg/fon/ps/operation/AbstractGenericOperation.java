/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.ac.bg.fon.ps.operation;

import rs.ac.bg.fon.ps.repository.Repository;
import rs.ac.bg.fon.ps.repository.db.DbRepository;
import rs.ac.bg.fon.ps.repository.db.impl.RepositoryDBGeneric;

/**
 *
 * @author Lenovo
 */
public abstract class AbstractGenericOperation {
    protected final Repository repository;

    public AbstractGenericOperation() {
        this.repository = new RepositoryDBGeneric();
    }
    
    public final void execute(Object param) throws Exception{
        try {
            preconditions(param);
            startTransacton();
            executeOperation(param);
            commitTransaction();
        } catch (Exception e) {
            e.printStackTrace();
            rollbackTransaction();
            throw e;
        }
        finally{
            disconnect();
        }
    }
    
    protected abstract void preconditions(Object param) throws Exception;
    
    private void startTransacton() throws Exception{
        ((DbRepository)repository).connect();
    }

    protected abstract void executeOperation(Object param) throws Exception;

    private void commitTransaction() throws Exception{
        ((DbRepository)repository).commit();
    }

    private void rollbackTransaction() throws Exception{
        ((DbRepository)repository).rollback();
    }

    private void disconnect() throws Exception {
        ((DbRepository)repository).disconnect();
    }

}
