/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.ac.bg.fon.ps.view.cordinator;

import java.util.HashMap;
import java.util.Map;
import rs.ac.bg.fon.ps.domain.Klijent;
import rs.ac.bg.fon.ps.domain.Paket;
import rs.ac.bg.fon.ps.domain.Prijava;
import rs.ac.bg.fon.ps.view.constants.Constants;
import rs.ac.bg.fon.ps.view.controller.KlijentController;
import rs.ac.bg.fon.ps.view.controller.LoginController;
import rs.ac.bg.fon.ps.view.controller.MainController;
import rs.ac.bg.fon.ps.view.controller.OdjavaController;
import rs.ac.bg.fon.ps.view.controller.PaketController;
import rs.ac.bg.fon.ps.view.controller.PregledKlijenataController;
import rs.ac.bg.fon.ps.view.controller.PregledPaketaController;
import rs.ac.bg.fon.ps.view.controller.PrijavaController;
import rs.ac.bg.fon.ps.view.controller.UcitanKlijentController;
import rs.ac.bg.fon.ps.view.controller.UcitanaPrijavaController;
import rs.ac.bg.fon.ps.view.form.FrmKlijent;
import rs.ac.bg.fon.ps.view.form.FrmLogin;
import rs.ac.bg.fon.ps.view.form.FrmMain;
import rs.ac.bg.fon.ps.view.form.FrmOdjava;
import rs.ac.bg.fon.ps.view.form.FrmPaket;
import rs.ac.bg.fon.ps.view.form.FrmPregledKlijenata;
import rs.ac.bg.fon.ps.view.form.FrmPregledPaketa;
import rs.ac.bg.fon.ps.view.form.FrmPrijava;
import rs.ac.bg.fon.ps.view.form.FrmUcitanKlijent;
import rs.ac.bg.fon.ps.view.form.FrmUcitanaPrijava;
import rs.ac.bg.fon.ps.view.form.util.FormMode;

/**
 *
 * @author Lenovo
 */
public class MainCordinator {
    private static MainCordinator instance;
    private final MainController mainController;
    private final Map<String, Object> params;

    private MainCordinator() {
        mainController=new MainController(new FrmMain());
        params=new HashMap<>();
    }

    public MainController getMainController() {
        return mainController;
    }
    
    public void addParam(String name, Object key) {
        params.put(name, key);
    }

    public Object getParam(String name) {
        return params.get(name);
    }

    public static MainCordinator getInstance() {
        if(instance==null){
            instance=new MainCordinator();
        }
        return instance;
    }
    
    public void openLoginForm(){
        LoginController loginController=new LoginController(new FrmLogin());
        loginController.openForm();
    }

    public void openMainForm() {
        mainController.openForm();
    }

    public void openFrmPaket() {
        PaketController paketController=new PaketController(new FrmPaket(mainController.getFrmMain(), true));
        paketController.openFrmPaket(FormMode.FORMA_DODAVANJE);
    }

    public void openFrmPregledPaketa() {
        FrmPregledPaketa form=new FrmPregledPaketa(mainController.getFrmMain(), true);
        PregledPaketaController pregledPaketaController=new PregledPaketaController(form);
        pregledPaketaController.openFrmPregledPaketa();
    }
    
    public void openFrmPaketDetaljnije(){
        FrmPaket frmPaketOpsirnije=new FrmPaket(mainController.getFrmMain(), true);
        PaketController paketController=new PaketController(frmPaketOpsirnije);
        paketController.openFrmPaket(FormMode.FORMA_PREGLED);
        params.put(Constants.PARAM_PAKET, frmPaketOpsirnije);
    }

    public void openFrmAddPrijava() {
        PrijavaController prijavaController=new PrijavaController(new FrmPrijava(mainController.getFrmMain(), true));
        prijavaController.openFrmPrijava(FormMode.FORMA_DODAVANJE);
    }

    public void openFrmKlijent() {
        KlijentController klijentController=new KlijentController(new FrmKlijent(mainController.getFrmMain(), true));
        klijentController.openFrmKlijent();
    }

    public void openFrmPregledKlijenata() {
        PregledKlijenataController pregledKlijenataController=new PregledKlijenataController(new FrmPregledKlijenata(mainController.getFrmMain(), true));
        pregledKlijenataController.openFrmPregledKlijenata();
    }

    public void openFrmOdjava() {
        OdjavaController odjavaController=new OdjavaController(new FrmOdjava(mainController.getFrmMain(), true));
        odjavaController.openFrmOdjava();
    }

    public void openFrmUcitanKlijent(Klijent klijent) {
        UcitanKlijentController ucitanKlijentController=new UcitanKlijentController(new FrmUcitanKlijent(mainController.getFrmMain(), true), klijent);
        ucitanKlijentController.openFrmUcitanKlijent();
    }
    
    public void openFrmUcitanaPrijava(Prijava prijava) {
        UcitanaPrijavaController ucitanaPrijavaController=new UcitanaPrijavaController(new FrmUcitanaPrijava(mainController.getFrmMain(), true), prijava);
        ucitanaPrijavaController.openFrmUcitanKlijent();
    }

}
