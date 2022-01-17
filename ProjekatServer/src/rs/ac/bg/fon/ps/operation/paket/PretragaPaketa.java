/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.ac.bg.fon.ps.operation.paket;

import java.util.List;
import rs.ac.bg.fon.ps.domain.Paket;
import rs.ac.bg.fon.ps.operation.AbstractGenericOperation;

/**
 *
 * @author Lenovo
 */
public class PretragaPaketa extends AbstractGenericOperation{
    private List<Paket> listaPaketa;
    @Override
    protected void preconditions(Object param) throws Exception {
        if (param == null || !(param instanceof Paket)) {
            throw new Exception("Objekat nije validan!");
        }
    }

    @Override
    protected void executeOperation(Object param) throws Exception {
        this.listaPaketa=repository.getAllPoUslovu((Paket)param);
    }

    public List<Paket> getListaPaketa() {
        return listaPaketa;
    }
    
}
