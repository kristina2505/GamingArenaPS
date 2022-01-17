/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.ac.bg.fon.ps.operation.uredjaj;

import java.util.List;
import rs.ac.bg.fon.ps.domain.Uredjaj;
import rs.ac.bg.fon.ps.operation.AbstractGenericOperation;

/**
 *
 * @author Lenovo
 */
public class VratiListuUredjaja extends AbstractGenericOperation{
    List<Uredjaj> listaUredjaja;
    @Override
    protected void preconditions(Object param) throws Exception {
        if(param==null || !(param instanceof Uredjaj)){
            throw new Exception("Objekat nije validan!");
        }
    }

    @Override
    protected void executeOperation(Object param) throws Exception {
        this.listaUredjaja=repository.getAll((Uredjaj)param);
    }

    public List<Uredjaj> getListaUredjaja() {
        return listaUredjaja;
    }
    
}
