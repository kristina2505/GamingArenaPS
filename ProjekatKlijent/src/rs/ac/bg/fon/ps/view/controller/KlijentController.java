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
import rs.ac.bg.fon.ps.view.form.FrmKlijent;
import rs.ac.bg.fon.ps.view.form.component.table.KlijentTableModel;

/**
 *
 * @author Lenovo
 */
public class KlijentController {

    private final FrmKlijent frmKlijent;

    public KlijentController(FrmKlijent frmKlijent) {
        this.frmKlijent = frmKlijent;
        frmKlijent.setTitle("Klijent");
        addActionListeners();
    }

    private void addActionListeners() {
        frmKlijent.addBtnSacuvajActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                sacuvaj();
            }

            private void sacuvaj() {
                try {
                    Klijent klijent = klijentSaForme();
                    Communication.getInstance().addKlijent(klijent);
                    JOptionPane.showMessageDialog(frmKlijent, "Klijent uspesno sacuvan!", "Dodavanje klijenta", JOptionPane.INFORMATION_MESSAGE);
                    resetForm();
                    osveziPrikaz();
                } catch (Exception e) {
                    e.printStackTrace();
                    JOptionPane.showMessageDialog(frmKlijent, "Sistem ne moze da sacuva klijenta!\n" + e.getMessage(), "Novi klijent", JOptionPane.ERROR_MESSAGE);
                }
            }

        });
        frmKlijent.addBtnPretraziActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    //validacijaPolja();
                    pretraga();
                    JOptionPane.showMessageDialog(frmKlijent, "Sistem je nasao naloge klijenata po zadatoj vrednosti!");
                } catch (Exception ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(frmKlijent, "Sistem ne moze da nadje naloge klijenata po zadatoj vrednosti:\n" + ex.getMessage(), "Pretraga klijenata", JOptionPane.ERROR_MESSAGE);
                
                }
                
            }

            private void pretraga() throws Exception {
                Klijent klijent = new Klijent();
                klijent.setIme(frmKlijent.getTxtImePretraga().getText().trim());
                klijent.setPrezime(frmKlijent.getTxtPrezimePretraga().getText().trim());
                List<Klijent> listaKlijenata=new ArrayList<>();
                listaKlijenata=Communication.getInstance().getKlijentiPoKriterijumu(klijent);
                KlijentTableModel model= (KlijentTableModel) frmKlijent.getTblKlijent().getModel();
                model.setListaKlijenata(listaKlijenata);
            }

            private void validacijaPolja() throws Exception {
                if(frmKlijent.getTxtImePretraga().getText().isEmpty()){
                    throw new Exception("Niste uneli ime klijenta!");
                    
                }
                if(frmKlijent.getTxtPrezimePretraga().getText().isEmpty()){
                     throw new Exception("Niste uneli prezime klijenta!");
                    }
            }

        });
        frmKlijent.addBtnObrisiActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                obrisi();
            }

            private void obrisi() {
                try {
                    int red = frmKlijent.getTblKlijent().getSelectedRow();
                    if (red >= 0) {
                        KlijentTableModel model = (KlijentTableModel) frmKlijent.getTblKlijent().getModel();
                        Klijent klijent = model.vratiKlijenta(red);
                        int option = JOptionPane.showConfirmDialog(frmKlijent, "Da li zaista zelite da obrisete klijenta " + klijent.getIme() + " " + klijent.getPrezime(), "Brisanje klijenta", JOptionPane.YES_NO_CANCEL_OPTION);
                        if (option == JOptionPane.YES_OPTION) {
                            Communication.getInstance().obrisiKlijenta(klijent);
                            JOptionPane.showMessageDialog(frmKlijent, "Klijent uspesno obrisan!\n", "Brisanje klijenta", JOptionPane.INFORMATION_MESSAGE);
                            osveziPrikaz();
                        }
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(frmKlijent, "Greska prilikom brisanja klijenta!\n" + ex.getMessage(), "Brisanje klijenta", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }

    private Klijent klijentSaForme() throws Exception {
        String regex1="([a-zA-Z0-9]+(?:[._+-][a-zA-Z0-9]+)*)@([a-zA-Z0-9]+(?:[.-][a-zA-Z0-9]+)*[.][a-zA-Z]{2,})";
        if(!frmKlijent.getTxtEmail().getText().matches(regex1)){
            //JOptionPane.showMessageDialog(frmKlijent, "E-mail nije u validnom formatu!");
            frmKlijent.getTxtEmail().setText("");
            throw new Exception("E-mail nije u validnom formatu");
            
        }
        
        Klijent klijent = new Klijent();
        klijent.setIme(frmKlijent.getTxtIme().getText().trim());
        klijent.setPrezime(frmKlijent.getTxtPrezime().getText().trim());
        klijent.setJmbg(frmKlijent.getTxtJMBG().getText().trim());
        klijent.seteMail(frmKlijent.getTxtEmail().getText());
        klijent.setTelefon(frmKlijent.getTxtTelefon().getText().trim());
        klijent.setAdresa(frmKlijent.getTxtAdresa().getText().trim());
        return klijent;
    }

    private void resetForm() {
        frmKlijent.getTxtIme().setText("");
        frmKlijent.getTxtPrezime().setText("");
        frmKlijent.getTxtJMBG().setText("");
        frmKlijent.getTxtEmail().setText("");
        frmKlijent.getTxtAdresa().setText("");
        frmKlijent.getTxtTelefon().setText("");
    }

    public void openFrmKlijent() {
        try {
            prepareView();
            frmKlijent.setLocationRelativeTo(MainCordinator.getInstance().getMainController().getFrmMain());
            frmKlijent.setVisible(true);
            
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(frmKlijent, "Greska prilikom ucitavanja forme: \n", "Greska forme klijent", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void prepareView() throws Exception {

        popuniTabelu();
    }

    private void osveziPrikaz() throws Exception {
        popuniTabelu();
    }

    private void popuniTabelu() throws Exception {
        KlijentTableModel model = new KlijentTableModel(Communication.getInstance().getAllKlijenti());
        frmKlijent.getTblKlijent().setModel(model);
    }
}
