/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.ac.bg.fon.ps.view.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import rs.ac.bg.fon.ps.view.form.FrmKonfiguracija;

/**
 *
 * @author Lenovo
 */
public class PodesavanjaController {

    private final FrmKonfiguracija frmKonfiguracija;
    Properties properties;

    public PodesavanjaController(FrmKonfiguracija frmKonfiguracija) {
        this.frmKonfiguracija = frmKonfiguracija;
        this.frmKonfiguracija.setTitle("Konfiguracija");
        this.properties = new Properties();
        
        addActionListeners();
    }

    

    public void openFrmKonfiguracija() {
        try {
            this.frmKonfiguracija.setLocationRelativeTo(MainController.getInstance().getFrmMain());
            prepareView();
            this.frmKonfiguracija.setVisible(true);
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(frmKonfiguracija, "Greska prilikom ucitavanja forme: \n" + e.getMessage(), "Podesavanja", JOptionPane.ERROR_MESSAGE);
        }

    }

    private void addActionListeners() {
        frmKonfiguracija.getBtnSacuvaj().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    izmeniPodesavanja();
                    JOptionPane.showMessageDialog(frmKonfiguracija, "Uspesno ste izmenili podesavanja");
                } catch (Exception ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(frmKonfiguracija, "Greska prilikom izmene konfiguracije: \n"+ex.getMessage(), "Izmena kofiguracije", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        frmKonfiguracija.getBtnOtkazi().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frmKonfiguracija.dispose();
            }
        });
        frmKonfiguracija.getBtnIzmeni().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                postaviElemente(true);
            }
        });
    }

    private void prepareView() throws Exception {
        frmKonfiguracija.getBtnIzmeni().setEnabled(true);
        postaviElemente(false);
        popuniVrednosti();
        properties.load(new FileInputStream("config/dbconfig.properties"));
    }

    private void postaviElemente(boolean vrednost) {
        frmKonfiguracija.getBtnOtkazi().setEnabled(vrednost);
        frmKonfiguracija.getBtnSacuvaj().setEnabled(vrednost);
        frmKonfiguracija.getTxtUrl().setEnabled(vrednost);
        frmKonfiguracija.getTxtPassword().setEnabled(vrednost);
        frmKonfiguracija.getTxtUsername().setEnabled(vrednost);
        frmKonfiguracija.getTxtPort().setEnabled(vrednost);
    }

    private void popuniVrednosti() throws Exception {
        
        properties.load(new FileInputStream("config/dbconfig.properties"));
        String url = properties.getProperty("url");
        String user = properties.getProperty("user");
        String password = properties.getProperty("password");
        String port = properties.getProperty("port");

        frmKonfiguracija.getTxtUrl().setText(url);
        frmKonfiguracija.getTxtPassword().setText(password);
        frmKonfiguracija.getTxtUsername().setText(user);
        frmKonfiguracija.getTxtPort().setText(port);
    }

    private void izmeniPodesavanja() throws Exception {
        properties.load(new FileInputStream("config/dbconfig.properties"));
        String url=frmKonfiguracija.getTxtUrl().getText().trim();
        String user=frmKonfiguracija.getTxtUsername().getText().trim();
        String password=frmKonfiguracija.getTxtPassword().getText().trim();
        String port=frmKonfiguracija.getTxtPort().getText().trim();
        properties.setProperty("url", url);
        properties.store(new FileOutputStream("config/dbconfig.properties"), null);
        properties.setProperty("user", user);
        properties.store(new FileOutputStream("config/dbconfig.properties"), null);
        properties.setProperty("password", password);
        properties.store(new FileOutputStream("config/dbconfig.properties"), null);
        properties.setProperty("port", port);
        properties.store(new FileOutputStream("config/dbconfig.properties"), null);
    }
}
