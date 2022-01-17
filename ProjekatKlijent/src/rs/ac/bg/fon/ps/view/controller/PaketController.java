/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.ac.bg.fon.ps.view.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import rs.ac.bg.fon.ps.communication.Communication;
import rs.ac.bg.fon.ps.domain.Paket;
import rs.ac.bg.fon.ps.domain.Uredjaj;
import rs.ac.bg.fon.ps.view.constants.Constants;
import rs.ac.bg.fon.ps.view.cordinator.MainCordinator;
import rs.ac.bg.fon.ps.view.form.FrmPaket;
import rs.ac.bg.fon.ps.view.form.util.FormMode;

/**
 *
 * @author Lenovo
 */
public class PaketController {

    private final FrmPaket frmPaket;

    public PaketController(FrmPaket frmPaket) {
        this.frmPaket = frmPaket;
        frmPaket.setTitle("Paket");
        addActionListeners();
    }

    private void addActionListeners() {
        frmPaket.addBtnSacuvajActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                sacuvaj();
            }

            private void sacuvaj() {
                try {
                    Paket paket = paketSaFormeUnos();
                    Communication.getInstance().addPaket(paket);
                    JOptionPane.showMessageDialog(frmPaket, "Sistem je zapamtio paket!", "Dodavanje paketa", JOptionPane.INFORMATION_MESSAGE);
                    resetForm();
                } catch (Exception e) {
                    e.printStackTrace();
                    JOptionPane.showMessageDialog(frmPaket, "Sistem ne moze da zapamti paket:\n" + e.getMessage(), "Novi paket", JOptionPane.ERROR_MESSAGE);
                }
            }

        });

        frmPaket.addBtnIzmeniActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                izmeni();
            }

            private void izmeni() {
                try {
                    Paket paket = paketSaForme();
                    int option = JOptionPane.showConfirmDialog(frmPaket, "Da li zaista zelite da izmenite paket " + paket.getNazivPaketa(), "Izmena paketa", JOptionPane.YES_NO_CANCEL_OPTION);
                    if (option == JOptionPane.YES_OPTION) {
                        Communication.getInstance().izmeniPaket(paket);
                        JOptionPane.showMessageDialog(frmPaket, "Sistem je zapamtio paket!", "Izmena paketa", JOptionPane.INFORMATION_MESSAGE);
                    }
                    frmPaket.dispose();
                } catch (Exception ex) {
                    Logger.getLogger(FrmPaket.class.getName()).log(Level.SEVERE, null, ex);
                    JOptionPane.showMessageDialog(frmPaket, "Sistem ne moze da zapamti paket:\n" + ex.getMessage(), "Izmena paketa", JOptionPane.ERROR_MESSAGE);

                }
            }
        });

        frmPaket.addBtnIzbrisiActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                delete();
            }

            private void delete() {
                try {
                    Paket paket = paketSaForme();
                    int option = JOptionPane.showConfirmDialog(frmPaket, "Da li zaista zelite da obrisete paket " + paket.getNazivPaketa(), "Brisanje paketa", JOptionPane.YES_NO_CANCEL_OPTION);
                    if (option == JOptionPane.YES_OPTION) {
                        Communication.getInstance().obrisiPaket(paket);
                        JOptionPane.showMessageDialog(frmPaket, "Paket uspesno obrisan!", "Brisanje paketa", JOptionPane.INFORMATION_MESSAGE);
                    }
                    frmPaket.dispose();
                } catch (Exception ex) {
                    Logger.getLogger(FrmPaket.class.getName()).log(Level.SEVERE, null, ex);
                    JOptionPane.showMessageDialog(frmPaket, "Greska prilikom brisanja paketa:\n" + ex.getMessage(), "Brisanje paketa", JOptionPane.ERROR_MESSAGE);

                }
            }
        });

        frmPaket.addBtnOmoguciIzmene(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                postaviElementeForme(FormMode.FORMA_IZMENA);
            }
        });
    }

    private Paket paketSaForme() throws Exception {
//        if (frmPaket.getTxtBrojSati().getText().isEmpty() || frmPaket.getTxtCena().getText().isEmpty() || frmPaket.getTxtNazivPaketa().getText().isEmpty() || frmPaket.getTxtPaketID().getText().isEmpty()) {
//            throw new Exception("Sva polja su obavezna!");
//        }
        Paket paket = new Paket();
        paket.setUredjaj((Uredjaj) frmPaket.getCmbUredjaj().getSelectedItem());
        paket.setPaketID(Integer.parseInt(frmPaket.getTxtPaketID().getText().trim()));
        paket.setNazivPaketa(frmPaket.getTxtNazivPaketa().getText().trim());
        paket.setBrojSati(Integer.parseInt(frmPaket.getTxtBrojSati().getText().trim()));
        paket.setCena(Double.parseDouble(frmPaket.getTxtCena().getText().trim()));
        return paket;
    }

    private Paket paketSaFormeUnos() throws Exception {
//        if (frmPaket.getTxtBrojSati().getText().isEmpty() || frmPaket.getTxtCena().getText().isEmpty() || frmPaket.getTxtNazivPaketa().getText().isEmpty() || frmPaket.getTxtPaketID().getText().isEmpty()) {
//            throw new Exception("Sva polja su obavezna!");
//        }
        Paket paket = new Paket();
        paket.setUredjaj((Uredjaj) frmPaket.getCmbUredjaj().getSelectedItem());
        paket.setNazivPaketa(frmPaket.getTxtNazivPaketa().getText().trim());
        paket.setBrojSati(Integer.parseInt(frmPaket.getTxtBrojSati().getText().trim()));
        paket.setCena(Double.parseDouble(frmPaket.getTxtCena().getText().trim()));
        return paket;
    }

    private void resetForm() {
        frmPaket.getTxtPaketID().setText("");
        frmPaket.getTxtNazivPaketa().setText("");
        frmPaket.getTxtCena().setText("");
        frmPaket.getTxtBrojSati().setText("");
    }

    public void openFrmPaket(FormMode formMode) {
        try {
            prepareView(formMode);
            frmPaket.setLocationRelativeTo(MainCordinator.getInstance().getMainController().getFrmMain());
            frmPaket.setVisible(true);
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(frmPaket, "Greska prilikom ucitavanja forme: \n", "Greska forme paketa", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void prepareView(FormMode formMode) throws Exception {
        popuniCmbUredjaji();
        postaviElementeForme(formMode);
    }

    private void popuniCmbUredjaji() throws Exception {
        frmPaket.getCmbUredjaj().removeAllItems();
        List<Uredjaj> uredjaji = Communication.getInstance().getAllUredjaji();
        for (Uredjaj uredjaj : uredjaji) {
            frmPaket.getCmbUredjaj().addItem(uredjaj);
        }
    }

    private void postaviElementeForme(FormMode formMode) {
        switch (formMode) {
            case FORMA_DODAVANJE:
                frmPaket.getBtnSacuvaj().setEnabled(true);
                frmPaket.getBtnOmoguciIzmene().setEnabled(false);
                frmPaket.getBtnIzmeni().setVisible(false);
                frmPaket.getBtnObrisi().setVisible(false);
                //resetForm();

                frmPaket.getTxtPaketID().setVisible(false);
                frmPaket.getLblID().setVisible(false);
                frmPaket.getTxtNazivPaketa().setEnabled(true);
                frmPaket.getTxtCena().setEnabled(true);
                frmPaket.getTxtBrojSati().setEnabled(true);
                frmPaket.getCmbUredjaj().setEnabled(true);
                break;

            case FORMA_PREGLED:
                frmPaket.getTxtPaketID().setEnabled(false);
                frmPaket.getTxtNazivPaketa().setEnabled(false);
                frmPaket.getTxtCena().setEnabled(false);
                frmPaket.getTxtBrojSati().setEnabled(false);
                frmPaket.getBtnOmoguciIzmene().setEnabled(true);
                frmPaket.getCmbUredjaj().setEnabled(false);

                frmPaket.getBtnSacuvaj().setEnabled(false);
                frmPaket.getBtnIzmeni().setEnabled(false);
                frmPaket.getBtnObrisi().setEnabled(false);
                frmPaket.getBtnOmoguciIzmene().setEnabled(true);

                Paket paket = (Paket) MainCordinator.getInstance().getParam(Constants.PARAM_PAKET);
                frmPaket.getTxtPaketID().setText(paket.getPaketID() + "");
                frmPaket.getTxtNazivPaketa().setText(paket.getNazivPaketa());
                frmPaket.getTxtBrojSati().setText(paket.getBrojSati() + "");
                frmPaket.getTxtCena().setText(paket.getCena() + "");
                frmPaket.getCmbUredjaj().setSelectedItem(paket.getUredjaj());
                break;

            case FORMA_IZMENA:
                frmPaket.getTxtPaketID().setEnabled(false);
                frmPaket.getBtnSacuvaj().setEnabled(false);
                frmPaket.getBtnIzmeni().setEnabled(true);
                frmPaket.getBtnObrisi().setEnabled(true);
                frmPaket.getBtnOmoguciIzmene().setEnabled(false);

                frmPaket.getTxtNazivPaketa().setEnabled(true);
                frmPaket.getTxtCena().setEnabled(true);
                frmPaket.getTxtBrojSati().setEnabled(true);
                frmPaket.getCmbUredjaj().setEnabled(true);

                break;
        }
    }

}
