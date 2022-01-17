/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.ac.bg.fon.ps.view.controller;

import java.util.HashMap;
import java.util.Map;
import javax.swing.JFrame;
import rs.ac.bg.fon.ps.domain.Zaposleni;
import rs.ac.bg.fon.ps.view.form.FrmLogin;
import rs.ac.bg.fon.ps.view.form.FrmMain;
import rs.ac.bg.fon.ps.view.form.FrmPaket;
import rs.ac.bg.fon.ps.view.form.FrmPregledPaketa;
import rs.ac.bg.fon.ps.view.form.util.FormMode;

/**
 *
 * @author Lenovo
 */
public class ViewController {
    private static ViewController instance;
    JFrame mainForm;
    FrmPregledPaketa frmPregledPaketa;
    
    Zaposleni ulogovaniZaposleni;
    
    Map<String,Object> paramMap = new HashMap<>();

    public ViewController() {
    }

    public static ViewController getInstance() {
        if(instance == null){
            instance=new ViewController();
        }
        return instance;
    }
    
    public void pokreniAplikaciju(){
        new FrmLogin().setVisible(true);
    }
    
    public void openFrmMain(Zaposleni ulogovaniZaposleni){
        mainForm=new FrmMain();
        mainForm.setVisible(true);
        this.ulogovaniZaposleni=ulogovaniZaposleni;
    }
    
    public void pokreniFrmPregledPaketa(){
        frmPregledPaketa=new FrmPregledPaketa(mainForm, true);
        frmPregledPaketa.setLocationRelativeTo(mainForm);
        frmPregledPaketa.setVisible(true);
    }
    
    public void pokreniFrmPaket(){
    //    new FrmPaket(mainForm, true, FormMode.FORMA_DODAVANJE).setVisible(true);
    }
    
    public void pokreniFrmPaket(int paketID){
        paramMap.put("FORMA_PAKET_OPSIRNIJE", paketID);
    //   new FrmPaket(mainForm, true, FormMode.FORMA_IZMENA).setVisible(true);
    }

    public Map<String, Object> getParamMap() {
        return paramMap;
    }
    
//    public void osveziPrikazPaketa() throws Exception{
//        if(frmPregledPaketa != null){
//            frmPregledPaketa.osveziPrikaz();
//        }
//    }
    
}
