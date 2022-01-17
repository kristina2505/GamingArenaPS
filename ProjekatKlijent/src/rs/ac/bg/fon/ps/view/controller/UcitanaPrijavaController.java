/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.ac.bg.fon.ps.view.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import rs.ac.bg.fon.ps.domain.Prijava;
import rs.ac.bg.fon.ps.view.cordinator.MainCordinator;
import rs.ac.bg.fon.ps.view.form.FrmUcitanaPrijava;

/**
 *
 * @author Lenovo
 */
public class UcitanaPrijavaController {

    private final FrmUcitanaPrijava frmUcitanaPrijava;
    private final Prijava prijava;
    
    public UcitanaPrijavaController(FrmUcitanaPrijava frmUcitanaPrijava, Prijava prijava) {
        this.frmUcitanaPrijava = frmUcitanaPrijava;
        this.prijava = prijava;
        addActionListeners();
    }
    
    private void addActionListeners() {
        frmUcitanaPrijava.getBtnZatvori().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frmUcitanaPrijava.dispose();
            }
        });
    }
    
    
    public void openFrmUcitanKlijent() {
        popuniPodatke(prijava);
        this.frmUcitanaPrijava.setLocationRelativeTo(MainCordinator.getInstance().getMainController().getFrmMain());
        this.frmUcitanaPrijava.setVisible(true);
    }
    
    private void popuniPodatke(Prijava prijava) {
        frmUcitanaPrijava.getTxtKlijent().setText(prijava.getKlijent().getIme()+" "+prijava.getKlijent().getPrezime());
        frmUcitanaPrijava.getTxtPaket().setText(prijava.getPaket().getNazivPaketa());
        frmUcitanaPrijava.getTxtUredjaj().setText(prijava.getPaket().getUredjaj().getVrstaUredjaja());
        frmUcitanaPrijava.getTxtBrojSati().setText(String.valueOf(prijava.getPaket().getBrojSati()));
        frmUcitanaPrijava.getTxtCena().setText(String.valueOf(prijava.getPaket().getCena()));
        SimpleDateFormat sdfDatum=new SimpleDateFormat("dd.MM.yyyy");
        SimpleDateFormat sdfVreme=new SimpleDateFormat("hh:mm");
        frmUcitanaPrijava.getTxtDatum().setText(sdfDatum.format(prijava.getDatumPrijave()));
        frmUcitanaPrijava.getTxtVreme().setText(sdfVreme.format(prijava.getDatumPrijave()));
        
//frmUcitanaPrijava.gettxt().setText(klijent.getTelefon());
    }
}
