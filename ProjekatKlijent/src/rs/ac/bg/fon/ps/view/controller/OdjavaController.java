/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.ac.bg.fon.ps.view.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import rs.ac.bg.fon.ps.communication.Communication;
import rs.ac.bg.fon.ps.domain.Klijent;
import rs.ac.bg.fon.ps.domain.Prijava;
import rs.ac.bg.fon.ps.domain.StatusPrijave;
import rs.ac.bg.fon.ps.view.cordinator.MainCordinator;
import rs.ac.bg.fon.ps.view.form.FrmOdjava;
import rs.ac.bg.fon.ps.view.form.component.table.PrijavaTableModel;

/**
 *
 * @author Lenovo
 */
public class OdjavaController {

    private final FrmOdjava frmOdjava;

    public OdjavaController(FrmOdjava frmOdjava) {
        this.frmOdjava = frmOdjava;
        addActionListeners();
    }

    public void openFrmOdjava() {
        try {
            prepareView();
            frmOdjava.setLocationRelativeTo(MainCordinator.getInstance().getMainController().getFrmMain());
            frmOdjava.setVisible(true);

        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(frmOdjava, "Greska prilikom ucitavanja forme: \n" + e.getMessage(), "Greska forme pregled klijenata", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void addActionListeners() {
        frmOdjava.addBtnPretragaActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    popuniTabelu();
                } catch (Exception ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(frmOdjava, "Sistem ne moze da nadje prijave po zadatoj vrednosti: \n" + ex.getMessage(), "Greska forme pregled klijenata", JOptionPane.ERROR_MESSAGE);

                }
            }
        });
        frmOdjava.addBtnOdjaviActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    PrijavaTableModel model = (PrijavaTableModel) frmOdjava.getTblPrijave().getModel();
                    int row = frmOdjava.getTblPrijave().getSelectedRow();
                    if (row >= 0) {
                        Prijava prijava = model.vratiPrijavu(row);
                        Communication.getInstance().odjaviKorisnika(prijava);
                        SimpleDateFormat datum = new SimpleDateFormat("dd.MM.yyyy.");
                        SimpleDateFormat vreme = new SimpleDateFormat("hh:mm");

                        JOptionPane.showMessageDialog(frmOdjava, "Odjava klijenta " + prijava.getKlijent().getIme() + " " + prijava.getKlijent().getPrezime() + "\n je uspesno "
                                + "evidentirana dana " + datum.format(new Date()) + " u " + vreme.format(new Date()) + "!", "Odjava klijenta", JOptionPane.INFORMATION_MESSAGE);
                        resetForm();
                        popuniTabelu();
                    } else {
                        throw new Exception();
                    }
                } catch (Exception exc) {
                    exc.printStackTrace();
                    JOptionPane.showMessageDialog(frmOdjava, "Sistem ne moze da sacuva podatke o odjavi klijenta sa paketa: \n" + exc.getMessage(), "Greska forme pregled klijenata", JOptionPane.ERROR_MESSAGE);

                }

            }

        });
        frmOdjava.getBtnUcitajPrijavu().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    PrijavaTableModel model = (PrijavaTableModel) frmOdjava.getTblPrijave().getModel();
                    int row = frmOdjava.getTblPrijave().getSelectedRow();
                    if (row >= 0) {
                        Prijava prijava = model.vratiPrijavu(row);
                        MainCordinator.getInstance().openFrmUcitanaPrijava(prijava);
                    } else {
                        throw new Exception();
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(frmOdjava, "Sistem ne moze da ucita podatke o prijavi  na paket\n", "Greska forme pregled klijenata", JOptionPane.ERROR_MESSAGE);

                }

            }
        });
    }

    private void prepareView() throws Exception {
        popuniTabelu();
    }

    private void popuniTabelu() throws Exception {
        Klijent klijent = new Klijent();
        klijent.setIme(frmOdjava.getTxtIme().getText());
        klijent.setPrezime(frmOdjava.getTxtPrezime().getText());
        Prijava prijava = new Prijava();
        prijava.setKlijent(klijent);
        prijava.setStatusPrijave(StatusPrijave.PRIJAVLJEN);
        PrijavaTableModel model = new PrijavaTableModel(Communication.getInstance().getAktivnePrijave(prijava));
        frmOdjava.getTblPrijave().setModel(model);
    }

    private void resetForm() {
        frmOdjava.getTxtIme().setText("");
        frmOdjava.getTxtPrezime().setText("");
    }
}
