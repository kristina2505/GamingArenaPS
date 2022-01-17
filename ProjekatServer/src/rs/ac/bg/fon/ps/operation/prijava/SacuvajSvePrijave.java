/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.ac.bg.fon.ps.operation.prijava;

import java.util.List;
import rs.ac.bg.fon.ps.domain.Prijava;
import rs.ac.bg.fon.ps.operation.AbstractGenericOperation;

/**
 *
 * @author Lenovo
 */
public class SacuvajSvePrijave extends AbstractGenericOperation{

    @Override
    protected void preconditions(Object param) throws Exception {
        if(param==null ){
            throw new Exception("Objekat nije validan!");
        }
    }

    @Override
    protected void executeOperation(Object param) throws Exception {
        List<Prijava> listaPRijava=(List<Prijava>) param;
        for (Prijava prijava : listaPRijava) {
            repository.add(prijava);
        }
    }
    
}
