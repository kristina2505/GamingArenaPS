/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.ac.bg.fon.ps.operation.klijent;

import java.util.List;
import rs.ac.bg.fon.ps.domain.GenericEntity;
import rs.ac.bg.fon.ps.domain.Klijent;
import rs.ac.bg.fon.ps.operation.AbstractGenericOperation;

/**
 *
 * @author Lenovo
 */
public class PronadjiKlijente extends AbstractGenericOperation {
    private List<Klijent> lista;
    @Override
    protected void preconditions(Object param) throws Exception {
        if (param == null || !(param instanceof Klijent)) {
            throw new Exception("Objekat nije validan");
        }
    }

    @Override
    protected void executeOperation(Object param) throws Exception {
        this.lista=repository.getAllPoUslovu((Klijent)param);
    }

    public List<Klijent> getLista() {
        return lista;
    }

}
