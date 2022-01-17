/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.ac.bg.fon.ps.view.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultCellEditor;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.table.TableColumn;
import rs.ac.bg.fon.ps.communication.Communication;
import rs.ac.bg.fon.ps.domain.Paket;
import rs.ac.bg.fon.ps.domain.Uredjaj;
import rs.ac.bg.fon.ps.view.constants.Constants;
import rs.ac.bg.fon.ps.view.cordinator.MainCordinator;
import rs.ac.bg.fon.ps.view.form.FrmPregledPaketa;
import rs.ac.bg.fon.ps.view.form.component.table.PaketTableModel;

/**
 *
 * @author Lenovo
 */
public class PregledPaketaController {

    private final FrmPregledPaketa frmPregledPaketa;

    public PregledPaketaController(FrmPregledPaketa form) {
        this.frmPregledPaketa = form;
        addActionListeners();
    }

    private void addActionListeners() {
        frmPregledPaketa.addBtnOpsirnijeActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int red = frmPregledPaketa.getTblPaketi().getSelectedRow();
                if (red >= 0) {
                    PaketTableModel model = (PaketTableModel) frmPregledPaketa.getTblPaketi().getModel();
                    Paket paket = model.getPaket(red);
                    MainCordinator.getInstance().addParam(Constants.PARAM_PAKET, paket);
                    MainCordinator.getInstance().openFrmPaketDetaljnije();
                } else {
                    JOptionPane.showMessageDialog(frmPregledPaketa, "Sistem ne moze da ucita podatke o paketu!", "Opsirnije", JOptionPane.ERROR_MESSAGE);
                    return;
                }
            }
        });
        frmPregledPaketa.addBtnObrisiActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int red = frmPregledPaketa.getTblPaketi().getSelectedRow();
                    if (red >= 0) {
                        PaketTableModel model = (PaketTableModel) frmPregledPaketa.getTblPaketi().getModel();
                        Paket paket = model.getPaket(red);
                        int option = JOptionPane.showConfirmDialog(frmPregledPaketa, "Da li zaista zelite da obrisete paket " + paket.getNazivPaketa(), "Brisanje paketa", JOptionPane.YES_NO_CANCEL_OPTION);
                        if (option == JOptionPane.YES_OPTION) {
                            Communication.getInstance().obrisiPaket(paket);
                            JOptionPane.showMessageDialog(frmPregledPaketa, "Paket uspesno obrisan!\n", "Brisanje paketa", JOptionPane.INFORMATION_MESSAGE);
                            osveziPrikaz();
                        }
                    }else{
                        throw new Exception();
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(frmPregledPaketa, "Sistem ne moze da obrise paket\n" + ex.getMessage(), "Brisanje paketa", JOptionPane.ERROR_MESSAGE);
                }
            }

        });
        frmPregledPaketa.addWindowListener(new WindowAdapter() {
            @Override
            public void windowActivated(WindowEvent e) {
                try {
                    popuniTabelu();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });
        frmPregledPaketa.addBtnPretraziActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    if(frmPregledPaketa.getCmbUredjajiPretraga().getSelectedIndex()!=-1){
                    Uredjaj uredjaj=(Uredjaj) frmPregledPaketa.getCmbUredjajiPretraga().getSelectedItem();
                    Paket paket=new Paket();
                    paket.setUredjaj(uredjaj);
                    List<Paket> listaPaketaPretraga=Communication.getInstance().pretraziPakete(paket);
                    PaketTableModel model=(PaketTableModel) frmPregledPaketa.getTblPaketi().getModel();
                    //JOptionPane.showMessageDialog(frmPregledPaketa, "Sistem je nasao pakete po zadatoj vrednosti");
                    model.setListaPaketa(listaPaketaPretraga);
                    }else{
                        throw new Exception();
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(frmPregledPaketa, "Sistem ne moze da nadje pakete po zadatoj vrednosti");
                }
            }
        });
    }

    public void openFrmPregledPaketa() {
        try {
            frmPregledPaketa.setLocationRelativeTo(MainCordinator.getInstance().getMainController().getFrmMain());
            pripremiPrikaz();
            frmPregledPaketa.setVisible(true);
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(frmPregledPaketa, "Greska prilikom ucitavanja forme: \n", "Greska forme paketa", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void pripremiPrikaz() throws Exception {
        frmPregledPaketa.setTitle("Pregled paketa");
        popuniTabelu();
        popuniCmbUredjaji();
    }

    private void popuniTabelu() throws Exception {
        List<Paket> listaPaketa = Communication.getInstance().getAllPaketi();
        PaketTableModel model = new PaketTableModel(listaPaketa);
        frmPregledPaketa.getTblPaketi().setModel(model);

        JComboBox cbUredjaji = new JComboBox(Communication.getInstance().getAllUredjaji().toArray());
        TableColumn tcUredjaj = frmPregledPaketa.getTblPaketi().getColumnModel().getColumn(4);
        tcUredjaj.setCellEditor(new DefaultCellEditor(cbUredjaji));
    }

    private void osveziPrikaz() throws Exception {
        popuniTabelu();
        frmPregledPaketa.getCmbUredjajiPretraga().setSelectedIndex(-1);
    }
    
    private void popuniCmbUredjaji() throws Exception {
        frmPregledPaketa.getCmbUredjajiPretraga().removeAllItems();
        frmPregledPaketa.getCmbUredjajiPretraga().setSelectedIndex(-1);
        List<Uredjaj> uredjaji = Communication.getInstance().getAllUredjaji();
        for (Uredjaj uredjaj : uredjaji) {
            frmPregledPaketa.getCmbUredjajiPretraga().addItem(uredjaj);
        }
        
    }

}
