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
public class VratiListuPrijava extends AbstractGenericOperation{
    List<Prijava> lista;
    @Override
    protected void preconditions(Object param) throws Exception {
        if(param==null || !(param instanceof Prijava)){
            throw new Exception("Objekat nije validan!");
        }
    }

    @Override
    protected void executeOperation(Object param) throws Exception {
        this.lista=repository.getAll((Prijava)param);
    }

    public List<Prijava> getLista() {
        return lista;
    }
    
}
