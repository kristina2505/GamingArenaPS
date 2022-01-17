/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.ac.bg.fon.ps.operation.paket;

import rs.ac.bg.fon.ps.domain.Paket;
import rs.ac.bg.fon.ps.operation.AbstractGenericOperation;

/**
 *
 * @author Lenovo
 */
public class ZapamtiPaket extends AbstractGenericOperation{

    @Override
    protected void preconditions(Object param) throws Exception {
        if(param==null || !(param instanceof Paket)){
            throw new Exception("Neispravno unet paket!");
        }
    }

    @Override
    protected void executeOperation(Object param) throws Exception {
        repository.add((Paket)param);
    }
    
}
