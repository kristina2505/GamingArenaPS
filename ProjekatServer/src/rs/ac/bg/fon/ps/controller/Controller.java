/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.ac.bg.fon.ps.controller;

import java.util.ArrayList;
import java.util.List;
import rs.ac.bg.fon.ps.domain.Klijent;
import rs.ac.bg.fon.ps.domain.Paket;
import rs.ac.bg.fon.ps.domain.Prijava;
import rs.ac.bg.fon.ps.domain.Uredjaj;
import rs.ac.bg.fon.ps.domain.Zaposleni;
import rs.ac.bg.fon.ps.operation.AbstractGenericOperation;
import rs.ac.bg.fon.ps.operation.klijent.ObrisiKlijenta;
import rs.ac.bg.fon.ps.operation.klijent.PronadjiKlijente;
import rs.ac.bg.fon.ps.operation.klijent.UnesiKlijenta;
import rs.ac.bg.fon.ps.operation.klijent.UcitajKlijenta;
import rs.ac.bg.fon.ps.operation.klijent.VratiListuKlijenata;
import rs.ac.bg.fon.ps.operation.paket.IzmeniPaket;
import rs.ac.bg.fon.ps.operation.paket.ObrisiPaket;
import rs.ac.bg.fon.ps.operation.paket.PretragaPaketa;
import rs.ac.bg.fon.ps.operation.paket.ZapamtiPaket;
import rs.ac.bg.fon.ps.operation.paket.UcitajPaket;
import rs.ac.bg.fon.ps.operation.paket.VratiListuPaketa;
import rs.ac.bg.fon.ps.operation.prijava.SacuvajOdjavu;
import rs.ac.bg.fon.ps.operation.prijava.PronadjiPrijave;
import rs.ac.bg.fon.ps.operation.prijava.SacuvajPrijavu;
import rs.ac.bg.fon.ps.operation.prijava.SacuvajSvePrijave;
import rs.ac.bg.fon.ps.operation.prijava.VratiListuPrijava;
import rs.ac.bg.fon.ps.operation.uredjaj.VratiListuUredjaja;
import rs.ac.bg.fon.ps.operation.zaposleni.ZaposleniLogin;
import rs.ac.bg.fon.ps.repository.Repository;
import rs.ac.bg.fon.ps.repository.db.impl.RepositoryDBGeneric;
import rs.ac.bg.fon.ps.repository.db.impl.RepositoryDBKlijent;
import rs.ac.bg.fon.ps.repository.db.impl.RepositoryDBPaket;
import rs.ac.bg.fon.ps.repository.db.impl.RepositoryDBPrijava;
import rs.ac.bg.fon.ps.repository.db.impl.RepositoryDBUredjaj;
import rs.ac.bg.fon.ps.repository.db.impl.RepositoryDBZaposleni;
import rs.ac.bg.fon.ps.thread.ProcessClientsRequests;

/**
 *
 * @author Lenovo
 */
public class Controller {

    private static Controller instance;
    private final Repository repositoryZaposleni;
    private final Repository repositoryPaket;
    private final Repository repositoryUredjaj;
    private final Repository repositoryKlijent;
    private final Repository repositoryPrijava;
    private final Repository repositoryGeneric;
    private List<ProcessClientsRequests> nitiKlijenata;

    private Controller() {
        this.repositoryZaposleni = new RepositoryDBZaposleni();
        this.repositoryKlijent = new RepositoryDBKlijent();
        this.repositoryPaket = new RepositoryDBPaket();
        this.repositoryPrijava = new RepositoryDBPrijava();
        this.repositoryUredjaj = new RepositoryDBUredjaj();
        this.repositoryGeneric = new RepositoryDBGeneric();
        this.nitiKlijenata = new ArrayList<>();
    }

    public static Controller getInstance() {
        if (instance == null) {
            instance = new Controller();
        }
        return instance;
    }

    public void addPaket(Paket paket) throws Exception {
        AbstractGenericOperation operation = new ZapamtiPaket();
        operation.execute(paket);
    }

    public List<Paket> getAllPaketi() throws Exception {
        AbstractGenericOperation operation = new VratiListuPaketa();
        operation.execute(new Paket());
        return ((VratiListuPaketa) operation).getPaketi();
    }

    public Zaposleni zaposleniLogin(String username, String password, ProcessClientsRequests klijentskaNit) throws Exception {
        Zaposleni zaposleni = new Zaposleni();
        zaposleni.setUsername(username);
        zaposleni.setPassword(password);
        AbstractGenericOperation operation = new ZaposleniLogin();
        operation.execute(zaposleni);
        nitiKlijenata.add(klijentskaNit);
        return ((ZaposleniLogin) operation).getZaposleni();
    }

    public List<Uredjaj> getAllUredjaji() throws Exception {
        AbstractGenericOperation operation = new VratiListuUredjaja();
        operation.execute(new Uredjaj());
        return ((VratiListuUredjaja) operation).getListaUredjaja();
    }

    public List<Klijent> getAllKlijenti() throws Exception {
        AbstractGenericOperation operation = new VratiListuKlijenata();
        operation.execute(new Klijent());
        return ((VratiListuKlijenata) operation).getListaKlijenata();
    }

    public List<Prijava> getAllPrijave() throws Exception {
        AbstractGenericOperation operation = new VratiListuPrijava();
        operation.execute(new Prijava());
        return ((VratiListuPrijava) operation).getLista();
    }

    public void addPrijava(Prijava prijava) throws Exception {
        AbstractGenericOperation operation = new SacuvajPrijavu();
        operation.execute(prijava);
    }

    public void odjaviSaPaketa(Prijava prijava) throws Exception {
        AbstractGenericOperation operation = new SacuvajOdjavu();
        operation.execute(prijava);
    }

    public List<Prijava> vratiAktivnePrijave(Prijava prijava) throws Exception {
        AbstractGenericOperation operation = new PronadjiPrijave();
        operation.execute(prijava);
        return ((PronadjiPrijave) operation).getLista();
    }

    public void obrisiPaket(Paket paket) throws Exception {
        AbstractGenericOperation operation = new ObrisiPaket();
        operation.execute(paket);
    }

    public void izmeniPaket(Paket paket) throws Exception {
        AbstractGenericOperation operation = new IzmeniPaket();
        operation.execute(paket);
    }

    public Paket vratiPaket(Paket paket) throws Exception {
        AbstractGenericOperation operation = new UcitajPaket();
        operation.execute(paket);
        return ((UcitajPaket) operation).getPaket();
    }

    public void unesiKlijenta(Klijent klijent) throws Exception {
        AbstractGenericOperation operation = new UnesiKlijenta();
        operation.execute(klijent);
    }

    public List<Klijent> vratiKlijentePoUslovu(Klijent klijent) throws Exception {
        AbstractGenericOperation operation = new PronadjiKlijente();
        operation.execute(klijent);
        return ((PronadjiKlijente) operation).getLista();
    }

    public List<Paket> pretraziPakete(Paket paket) throws Exception {
        AbstractGenericOperation operation = new PretragaPaketa();
        operation.execute(paket);
        return ((PretragaPaketa) operation).getListaPaketa();
    }

    public void obrisiKlijenta(Klijent klijent) throws Exception {
        AbstractGenericOperation operation = new ObrisiKlijenta();
        operation.execute(klijent);
    }

    public Klijent vratiKlijenta(Klijent klijent) throws Exception {
        AbstractGenericOperation operation = new UcitajKlijenta();
        operation.execute(klijent);
        return ((UcitajKlijenta) operation).getKlijent();
    }

    public void odjaviZaposlenog(ProcessClientsRequests klijentskaNit) {
        nitiKlijenata.remove(klijentskaNit);
        klijentskaNit.ugasiNit();
    }

    public void ugasiNiti() {
        for (ProcessClientsRequests processClientsRequests : nitiKlijenata) {
            processClientsRequests.ugasiNit();
        }
    }

    public void sacuvajSvePrijave(List<Prijava> listaZaCuvanje) throws Exception {
//        for (Prijava prijava : listaZaCuvanje) {
//            AbstractGenericOperation operation=new SacuvajPrijavu();
//            operation.execute(prijava);
//        }
        AbstractGenericOperation operation=new SacuvajSvePrijave();
        operation.execute(listaZaCuvanje);
    }
}
