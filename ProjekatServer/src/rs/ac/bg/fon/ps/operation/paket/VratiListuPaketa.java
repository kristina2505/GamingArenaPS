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
public class VratiListuPaketa extends AbstractGenericOperation{
    private List<Paket> paketi;

    @Override
    protected void preconditions(Object param) throws Exception {
    }

    @Override
    protected void executeOperation(Object param) throws Exception {
        paketi=repository.getAll((Paket)param);
    }

    public List<Paket> getPaketi() {
        return paketi;
    }
    
    
}
