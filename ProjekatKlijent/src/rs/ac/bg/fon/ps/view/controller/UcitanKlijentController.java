/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.ac.bg.fon.ps.view.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import rs.ac.bg.fon.ps.domain.Klijent;
import rs.ac.bg.fon.ps.view.cordinator.MainCordinator;
import rs.ac.bg.fon.ps.view.form.FrmUcitanKlijent;

/**
 *
 * @author Lenovo
 */
public class UcitanKlijentController {
    private final FrmUcitanKlijent frmUcitanKlijent;
    private final Klijent klijent;

    public UcitanKlijentController(FrmUcitanKlijent frmUcitanKlijent, Klijent klijent1) {
        this.frmUcitanKlijent = frmUcitanKlijent;
        this.klijent=klijent1;
        this.frmUcitanKlijent.setTitle("Klijent");
        addActionListeners();
    }

    private void addActionListeners() {
        frmUcitanKlijent.getBtnZatvori().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frmUcitanKlijent.dispose();
            }
        });
    }
    
    public void openFrmUcitanKlijent(){
        popuniPodatke(klijent);
        this.frmUcitanKlijent.setLocationRelativeTo(MainCordinator.getInstance().getMainController().getFrmMain());
        this.frmUcitanKlijent.setVisible(true);
    }

    private void popuniPodatke(Klijent klijent) {
        frmUcitanKlijent.getTxtIme().setText(klijent.getIme());
        frmUcitanKlijent.getTxtPrezime().setText(klijent.getPrezime());
        frmUcitanKlijent.getTxtJmbg().setText(klijent.getJmbg());
        frmUcitanKlijent.getTxtEmail().setText(klijent.geteMail());
        frmUcitanKlijent.getTxtAdresa().setText(klijent.getAdresa());
        frmUcitanKlijent.getTxtTelefon().setText(klijent.getTelefon());
    }
    
}
