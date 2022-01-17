/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.ac.bg.fon.ps.view.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.logging.Level;
import java.util.logging.Logger;
import rs.ac.bg.fon.ps.communication.Communication;
import rs.ac.bg.fon.ps.domain.Zaposleni;
import rs.ac.bg.fon.ps.view.constants.Constants;
import rs.ac.bg.fon.ps.view.cordinator.MainCordinator;
import rs.ac.bg.fon.ps.view.form.FrmMain;

/**
 *
 * @author Lenovo
 */
public class MainController {

    private final FrmMain frmMain;

    public MainController(FrmMain frmMain) {
        this.frmMain = frmMain;
        addActionListener();
    }

    public void openForm() {
        Zaposleni trenutnoUlogovani=(Zaposleni) MainCordinator.getInstance().getParam(Constants.TRENUTNO_ULOGOVANI);
        frmMain.getLblTrenutnoUlogovani().setText("Trenutno ulogovani: "+trenutnoUlogovani.getIme()+" "+trenutnoUlogovani.getPrezime());
        frmMain.setVisible(true);
    }

    private void addActionListener() {
        frmMain.jmiNoviPaketAddActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jmiNoviPaketActionPerformed(e);
            }

            private void jmiNoviPaketActionPerformed(ActionEvent e) {
                MainCordinator.getInstance().openFrmPaket();
            }
        });
        frmMain.jmiPregledPaketaAddActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jmiPregledPaketaActionPerformed(e);
            }

            private void jmiPregledPaketaActionPerformed(ActionEvent e) {
                MainCordinator.getInstance().openFrmPregledPaketa();
            }
        });
        frmMain.getJmiNovaPrijava().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MainCordinator.getInstance().openFrmAddPrijava();
            }
        });
        frmMain.getJmiUnosKlijenta().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MainCordinator.getInstance().openFrmKlijent();
            }
        });
        
        frmMain.getJmiPregledKlijenata().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MainCordinator.getInstance().openFrmPregledKlijenata();
            }
        });
        frmMain.getJmiOdjava().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MainCordinator.getInstance().openFrmOdjava();
            }
        });
        frmMain.getBtnOdjava().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    frmMain.dispose();
                    Communication.getInstance().odjaviZaposlenog((Zaposleni) MainCordinator.getInstance().getParam(Constants.TRENUTNO_ULOGOVANI));
                    
                } catch (Exception ex) {
                    Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    public FrmMain getFrmMain() {
        return frmMain;
    }
    
    
    
}
