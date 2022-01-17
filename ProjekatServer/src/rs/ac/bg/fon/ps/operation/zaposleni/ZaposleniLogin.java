/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.ac.bg.fon.ps.operation.zaposleni;

import rs.ac.bg.fon.ps.domain.Zaposleni;
import rs.ac.bg.fon.ps.operation.AbstractGenericOperation;

/**
 *
 * @author Lenovo
 */
public class ZaposleniLogin extends AbstractGenericOperation{
    private Zaposleni zaposleni;
    @Override
    protected void preconditions(Object param) throws Exception {
        if(param==null || !(param instanceof Zaposleni)){
            throw new Exception("Niste uneli podatke korisnika!");
        }
    }

    @Override
    protected void executeOperation(Object param) throws Exception {
        zaposleni=(Zaposleni) repository.get((Zaposleni)param);
    }

    public Zaposleni getZaposleni() {
        return zaposleni;
    }
    
}
