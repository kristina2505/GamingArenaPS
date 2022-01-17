/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.ac.bg.fon.ps.view.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import rs.ac.bg.fon.ps.communication.Communication;
import rs.ac.bg.fon.ps.domain.Klijent;
import rs.ac.bg.fon.ps.view.cordinator.MainCordinator;
import rs.ac.bg.fon.ps.view.form.FrmPregledKlijenata;
import rs.ac.bg.fon.ps.view.form.component.table.KlijentTableModel;

/**
 *
 * @author Lenovo
 */
public class PregledKlijenataController {

    private final FrmPregledKlijenata frmPregledKlijenata;

    public PregledKlijenataController(FrmPregledKlijenata frmPregledKlijenata) {
        this.frmPregledKlijenata = frmPregledKlijenata;
        frmPregledKlijenata.setTitle("Pregled klijenata");
        addActionListeners();
    }

    public void openFrmPregledKlijenata() {
        try {
            prepareView();
            frmPregledKlijenata.setLocationRelativeTo(MainCordinator.getInstance().getMainController().getFrmMain());
            frmPregledKlijenata.setVisible(true);

        } catch (Exception e) {
        }
    }

    private void addActionListeners() {
        frmPregledKlijenata.addBtnPretraziActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {

                    pretraga();

                    JOptionPane.showMessageDialog(frmPregledKlijenata, "Sistem je nasao naloge klijenata po zaadatoj vrednosti!");
                } catch (Exception ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(frmPregledKlijenata, "Sistem ne moze da nadje naloge klijenata po zadatoj vrednosti:\n" + ex.getMessage(), "Pretraga klijenata", JOptionPane.ERROR_MESSAGE);

                }

            }

            private void pretraga() throws Exception {
                Klijent klijent = new Klijent();
                klijent.setIme(frmPregledKlijenata.getTxtIme().getText().trim());
                klijent.setPrezime(frmPregledKlijenata.getTxtPrezime().getText().trim());
                List<Klijent> listaKlijenata = new ArrayList<>();
                listaKlijenata = Communication.getInstance().getKlijentiPoKriterijumu(klijent);
                KlijentTableModel model = (KlijentTableModel) frmPregledKlijenata.getTblKlijenti().getModel();
                model.setListaKlijenata(listaKlijenata);
                resetForm();
            }

        });
        frmPregledKlijenata.addBtnObrisiActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                obrisi();
            }

            private void obrisi() {
                try {
                    int red = frmPregledKlijenata.getTblKlijenti().getSelectedRow();
                    if (red >= 0) {
                        KlijentTableModel model = (KlijentTableModel) frmPregledKlijenata.getTblKlijenti().getModel();
                        Klijent klijent = model.vratiKlijenta(red);
                        int option = JOptionPane.showConfirmDialog(frmPregledKlijenata, "Da li zaista zelite da obrisete klijenta " + klijent.getIme() + " " + klijent.getPrezime(), "Brisanje klijenta", JOptionPane.YES_NO_CANCEL_OPTION);
                        if (option == JOptionPane.YES_OPTION) {
                            Communication.getInstance().obrisiKlijenta(klijent);
                            JOptionPane.showMessageDialog(frmPregledKlijenata, "Sistem je obrisao nalog klijenta!\n", "Brisanje klijenta", JOptionPane.INFORMATION_MESSAGE);
                            osveziPrikaz();
                        }
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(frmPregledKlijenata, "Sistem ne moze da obrise nalog klijenta!\n" + ex.getMessage(), "Brisanje klijenta", JOptionPane.ERROR_MESSAGE);
                }
            }

        });
        frmPregledKlijenata.getBtnUcitaj().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                int red = frmPregledKlijenata.getTblKlijenti().getSelectedRow();
                if (red >= 0) {
                    KlijentTableModel model = (KlijentTableModel) frmPregledKlijenata.getTblKlijenti().getModel();
                    Klijent klijent = model.vratiKlijenta(red);
                    MainCordinator.getInstance().openFrmUcitanKlijent(klijent);
                } else {
                    JOptionPane.showMessageDialog(frmPregledKlijenata, "Sistem ne moze da ucita nalog klijenta!");
                }

            }
        });
    }

    private void resetForm() {
        frmPregledKlijenata.getTxtIme().setText("");
        frmPregledKlijenata.getTxtPrezime().setText("");

    }

    private void prepareView() throws Exception {

        popuniTabelu();
    }

    private void osveziPrikaz() throws Exception {
        popuniTabelu();
    }

    private void popuniTabelu() throws Exception {
        KlijentTableModel model = new KlijentTableModel(Communication.getInstance().getAllKlijenti());
        frmPregledKlijenata.getTblKlijenti().setModel(model);
    }
}
