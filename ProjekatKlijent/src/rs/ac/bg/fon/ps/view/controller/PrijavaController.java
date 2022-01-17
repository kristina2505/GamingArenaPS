/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.ac.bg.fon.ps.view.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import rs.ac.bg.fon.ps.communication.Communication;
import rs.ac.bg.fon.ps.domain.Klijent;
import rs.ac.bg.fon.ps.domain.Paket;
import rs.ac.bg.fon.ps.domain.Prijava;
import rs.ac.bg.fon.ps.domain.StatusPrijave;
import rs.ac.bg.fon.ps.domain.Uredjaj;
import rs.ac.bg.fon.ps.domain.Zaposleni;
import rs.ac.bg.fon.ps.view.constants.Constants;
import rs.ac.bg.fon.ps.view.cordinator.MainCordinator;
import rs.ac.bg.fon.ps.view.form.FrmPrijava;
import rs.ac.bg.fon.ps.view.form.component.table.KlijentTableModel;
import rs.ac.bg.fon.ps.view.form.component.table.PaketTableModel;
import rs.ac.bg.fon.ps.view.form.component.table.PrijavaTableModel;
import rs.ac.bg.fon.ps.view.form.util.FormMode;

/**
 *
 * @author Lenovo
 */
public class PrijavaController {

    private final FrmPrijava frmPrijava;
    private Klijent klijent;
     Paket paket;
     private List<Prijava> listaPrijava;

    public PrijavaController(FrmPrijava frmPrijava) {
        this.frmPrijava = frmPrijava;
        this.frmPrijava.setTitle("Prijava");
        addActionListener();
        klijent = new Klijent();
        this.listaPrijava=new ArrayList<>();
    }

    public void openFrmPrijava(FormMode formMode) {
        try {
            popuniFormu();
            postaviElementeForme(formMode);
            frmPrijava.setLocationRelativeTo(MainCordinator.getInstance().getMainController().getFrmMain());
            frmPrijava.setVisible(true);
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(frmPrijava, "Greska prilikom ucitavanja forme za unos prijava: \n" + ex.getMessage(), "Unos prijave", JOptionPane.ERROR_MESSAGE);
        }

    }

    private void postaviElementeForme(FormMode formMode) {
        switch (formMode) {
            case FORMA_DODAVANJE:
                //frmPrijava.getLblPrijavaID().setVisible(false);
                //frmPrijava.getTxtPrijavaID().setVisible(false);
                break;
            case FORMA_IZMENA:

                break;
            case FORMA_PREGLED:

                break;
        }
    }

    private void popuniFormu() throws Exception {
        frmPrijava.getCmbUredjaji().removeAllItems();
        frmPrijava.getCmbUredjaji().setModel(new DefaultComboBoxModel<>(Communication.getInstance().getAllUredjaji().toArray()));
        frmPrijava.getCmbUredjaji().setSelectedIndex(-1);
        //postaviVrednostiPaketa(frmPrijava.getCmbPaketi().getSelectedItem());
        String datum = new SimpleDateFormat("dd.MM.yyyy.").format(new Date());
        frmPrijava.getTxtDatum().setText(datum);
        popuniTabeliKlijenti();
        popuniTableuPaketi();
        popuniTabeluPrijave();
        
    }

    private void postaviVrednostiPaketa(Paket izabran) {
        frmPrijava.getTxtNazivPaketa().setText(izabran.getNazivPaketa());
        frmPrijava.getTxtBrojSati().setText(izabran.getBrojSati() + "");
        frmPrijava.getTxtCena().setText(izabran.getCena() + "");
        frmPrijava.getTxtUredjaj().setText(izabran.getUredjaj().toString());
        frmPrijava.getTxtPaketID().setText(izabran.getPaketID() + "");

    }

    private void popuniTabeliKlijenti() throws Exception {
        KlijentTableModel klijentTableModel = new KlijentTableModel(Communication.getInstance().getAllKlijenti());
        frmPrijava.getTblKorisnici().setModel(klijentTableModel);
    }

    private void addActionListener() {
        frmPrijava.getBtnPretragaPaketa().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Paket paket=new Paket();
                    if(frmPrijava.getCmbUredjaji().getSelectedIndex()!=-1){
                        paket.setUredjaj((Uredjaj) frmPrijava.getCmbUredjaji().getSelectedItem());
                        List<Paket> listaPaketa=new ArrayList<>();
                        listaPaketa=Communication.getInstance().pretraziPakete(paket);
                        PaketTableModel tableModel=(PaketTableModel) frmPrijava.getTblPaketi().getModel();
                        tableModel.setListaPaketa(listaPaketa);
                        JOptionPane.showMessageDialog(frmPrijava, "Sistem je nasao pakete po zaadatoj vrednosti!");
                    }else{
                        throw new Exception();
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(frmPrijava, "Sistem ne moze da nadje pakete po zadatoj vrednosti:\n" + ex.getMessage(), "Pretraga klijenata", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        frmPrijava.getBtnIzaberiPaket().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                PaketTableModel model = (PaketTableModel) frmPrijava.getTblPaketi().getModel();
                int red = frmPrijava.getTblPaketi().getSelectedRow();
                if (red >= 0) {
                    paket=model.getPaket(red);
                    postaviVrednostiPaketa(paket);
                } else {
                    JOptionPane.showMessageDialog(frmPrijava, "Morate odabrati paket!", "Dodavanje paketa", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        frmPrijava.addDodajKlijentaActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                KlijentTableModel model = (KlijentTableModel) frmPrijava.getTblKorisnici().getModel();
                int red = frmPrijava.getTblKorisnici().getSelectedRow();
                if (red >= 0) {
                    Klijent izabraniKlijent = model.vratiKlijenta(red);
                    frmPrijava.getTxtKorisnik().setText(izabraniKlijent.getIme() + " " + izabraniKlijent.getPrezime());
                    klijent = izabraniKlijent;
                } else {
                    JOptionPane.showMessageDialog(frmPrijava, "Morate odabrati klijenta!", "Dodavanje klijenta", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        frmPrijava.addSacuvajPrijavuActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    if (frmPrijava.getTxtCena().getText().isEmpty() || frmPrijava.getTxtKorisnik().getText().isEmpty() || frmPrijava.getTxtDatum().getText().isEmpty()) {
                        throw new Exception("Sva polja su obavezna!");
                    }
                    Prijava prijava = getPrijavaSaForme();
                    listaPrijava.add(prijava);
                    popuniTabeluPrijave();
                    //Communication.getInstance().addPrijava(prijava);
                    //frmPrijava.getTxtPrijavaID().setText(prijava.getPrijavaID() + "");
                    //JOptionPane.showMessageDialog(frmPrijava, "Sistem je sacuvao dodatu prijavu na paket!", "Dodavanje prijave", JOptionPane.INFORMATION_MESSAGE);
                } catch (Exception ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(frmPrijava, "Sistem ne moze da sacuva dodatu prijavu na paket!: \n" + ex.getMessage(), "Dodavanje prijave", JOptionPane.ERROR_MESSAGE);
                }
            }

        });
        frmPrijava.addBtnPretragaActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Klijent klijent = new Klijent();
                    klijent.setIme(frmPrijava.getTxtIme().getText().trim());
                    klijent.setPrezime(frmPrijava.getTxtPrezime().getText().trim());
                    List<Klijent> listaKlijenata = new ArrayList<>();
                    listaKlijenata = Communication.getInstance().getKlijentiPoKriterijumu(klijent);
                    KlijentTableModel model = (KlijentTableModel) frmPrijava.getTblKorisnici().getModel();
                    model.setListaKlijenata(listaKlijenata);
                    JOptionPane.showMessageDialog(frmPrijava, "Sistem je nasao naloge klijenata po zaadatoj vrednosti!");

                } catch (Exception ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(frmPrijava, "Sistem ne moze da nadje naloge klijenata po zadatoj vrednosti:\n" + ex.getMessage(), "Pretraga klijenata", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        frmPrijava.addBtnOtkaziActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int option = JOptionPane.showConfirmDialog(frmPrijava, "Da li zaista zelite da otkazete prijavu?", "Otkazi prijavu", JOptionPane.YES_NO_CANCEL_OPTION);
                if (option == JOptionPane.YES_OPTION) {
                    frmPrijava.dispose();
                }
            }
        });
        frmPrijava.getBtnSacuvajSve().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(!listaPrijava.isEmpty()){
                    try {
                        Communication.getInstance().sacuvajPrijave(listaPrijava);
                    } catch (Exception ex) {
                        Logger.getLogger(PrijavaController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        });
    }

    private Prijava getPrijavaSaForme() throws Exception {
        Prijava prijava = new Prijava();
        
        Klijent prijavljenKlijent = klijent;
        Zaposleni zaposleni = (Zaposleni) MainCordinator.getInstance().getParam(Constants.TRENUTNO_ULOGOVANI);
        SimpleDateFormat sdf = new SimpleDateFormat("yy.MM.yyyy.");
        String datumString = frmPrijava.getTxtDatum().getText();
        Date datumPrijave = sdf.parse(datumString);
        double cena = Double.parseDouble(frmPrijava.getTxtCena().getText().trim());

        prijava.setPaket(paket);
        prijava.setKlijent(klijent);
        prijava.setZaposleni(zaposleni);
        prijava.setDatumPrijave(datumPrijave);
        prijava.setCena(cena);
        prijava.setStatusPrijave(StatusPrijave.PRIJAVLJEN);

        return prijava;
    }

    private void popuniTableuPaketi() throws Exception {
        PaketTableModel model=new PaketTableModel(Communication.getInstance().getAllPaketi());
        frmPrijava.getTblPaketi().setModel(model);
    }

    private void popuniTabeluPrijave() {
        PrijavaTableModel model= new PrijavaTableModel(listaPrijava);
        frmPrijava.getTblPrijave().setModel(model);
    }
}
