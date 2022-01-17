/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.ac.bg.fon.ps.communication;

import java.io.IOException;
import java.net.Socket;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import rs.ac.bg.fon.ps.domain.Klijent;
import rs.ac.bg.fon.ps.domain.Paket;
import rs.ac.bg.fon.ps.domain.Prijava;
import rs.ac.bg.fon.ps.domain.Uredjaj;
import rs.ac.bg.fon.ps.domain.Zaposleni;

/**
 *
 * @author Lenovo
 */
public class Communication {

    Socket socket;
    Sender sender;
    Receiver receiver;
    private static Communication instance;

    private Communication() throws Exception {
        try {
           socket = new Socket("127.0.0.1", 9000); 
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Server nije pokrenut", "ERROR",JOptionPane.ERROR_MESSAGE);
            System.exit(0);
        }
        
        sender = new Sender(socket);
        receiver = new Receiver(socket);
    }

    public static Communication getInstance() throws Exception {
        if (instance == null) {
            instance = new Communication();
        }
        return instance;
    }

    public void addKlijent(Klijent klijent) throws Exception {
        Request request = new Request(Operation.ADD_KLIJENT, klijent);
        sender.send(request);
        Response response = (Response) receiver.receive();
        if (response.getException() == null) {

        } else {
            throw response.getException();
        }
    }

    public void addPaket(Paket paket) throws Exception {
        Request request = new Request(Operation.ADD_PAKET, paket);
        sender.send(request);
        Response response = (Response) receiver.receive();
        if (response.getException() == null) {

        } else {
            throw response.getException();
        }
    }

    public List<Paket> getAllPaketi() throws Exception {
        Request request = new Request(Operation.GET_ALL_PAKETI, null);
        sender.send(request);
        Response response = (Response) receiver.receive();
        if (response.getException() == null) {
            return (List<Paket>) response.getResult();
        } else {
            throw response.getException();
        }
    }

    public Zaposleni zaposleniLogin(String username, String password) throws Exception {
        Zaposleni zaposleni = new Zaposleni();
        zaposleni.setPassword(password);
        zaposleni.setUsername(username);
        Request request = new Request(Operation.ZAPOSLENI_LOGIN, zaposleni);
        sender.send(request);
        Response response = (Response) receiver.receive();
        if (response.getException() == null) {
            return (Zaposleni) response.getResult();
        } else {
            throw response.getException();
        }
    }

    public List<Uredjaj> getAllUredjaji() throws Exception {
        Request request = new Request(Operation.GET_ALL_UREDJAJI, null);
        sender.send(request);
        Response response = (Response) receiver.receive();
        if (response.getException() == null) {
            return (List<Uredjaj>) response.getResult();
        } else {
            throw response.getException();
        }
    }

    public List<Klijent> getAllKlijenti() throws Exception {
        Request request = new Request(Operation.GET_ALL_KLIJENTI, new Klijent());
        sender.send(request);
        Response response = (Response) receiver.receive();
        if (response.getException() == null) {
            return (List<Klijent>) response.getResult();
        } else {
            throw response.getException();
        }
    }

//    public List<Prijava> getAllPrijave() throws Exception {
//        Request request = new Request(Operation.GET_ALL_UREDJAJI, null);
//        sender.send(request);
//        Response response = (Response) receiver.receive();
//        if (response.getException() == null) {
//            return (List<Prijava>) response.getResult();
//        } else {
//            throw response.getException();
//        }
//    }
    public void addPrijava(Prijava prijava) throws Exception {
        Request request = new Request(Operation.ADD_PRIJAVA, prijava);
        sender.send(request);
        Response response = (Response) receiver.receive();
        if (response.getException() == null) {
            prijava.setPrijavaID(((Prijava) response.getResult()).getPrijavaID());
        } else {
            throw response.getException();
        }
    }

    public void obrisiPaket(Paket paket) throws Exception {
        Request request = new Request(Operation.OBRISI_PAKET, paket);
        sender.send(request);
        Response response = (Response) receiver.receive();
        if (response.getException() != null) {
            throw response.getException();
        }
    }

    public void obrisiKlijenta(Klijent klijent) throws Exception {
        Request request = new Request(Operation.OBRISI_KLIJENTA, klijent);
        sender.send(request);
        Response response = (Response) receiver.receive();
        if (response.getException() == null) {

        } else {
            throw response.getException();
        }
    }

    public void izmeniPaket(Paket paket) throws Exception {
        Request request = new Request(Operation.IZMENI_PAKET, paket);
        sender.send(request);
        Response response = (Response) receiver.receive();
        if (response.getException() != null) {
            throw response.getException();
        }
    }

    public Paket vratiPaket(Paket paket) throws Exception {
        Request request = new Request(Operation.VRATI_PAKET, paket);
        sender.send(request);
        Response response = (Response) receiver.receive();
        if (response.getException() == null) {
            return (Paket) response.getResult();
        } else {
            throw response.getException();
        }
    }

    public List<Klijent> getKlijentiPoKriterijumu(Klijent klijent) throws Exception {
        Request request = new Request(Operation.PRETRAGA_KLIJENATA, klijent);
        sender.send(request);
        Response response = (Response) receiver.receive();
        if (response.getException() == null) {
            return (List<Klijent>) response.getResult();
        } else {
            throw response.getException();
        }
    }


    public List<Prijava> getAktivnePrijave(Prijava prijava) throws Exception {
        Request request = new Request(Operation.VRATI_AKTIVNE_PRIJAVE, prijava);
        sender.send(request);
        Response response = (Response) receiver.receive();
        if (response.getException() == null) {
            return (List<Prijava>) response.getResult();
        } else {
            throw response.getException();
        }
    }

    public void odjaviKorisnika(Prijava prijava) throws Exception {
        Request request = new Request(Operation.ODJAVI_KORISNIKA, prijava);
        sender.send(request);
        Response response = (Response) receiver.receive();
        if (response.getException() != null) {
            throw response.getException();
        }
    }

    public List<Paket> pretraziPakete(Paket paket) throws Exception {
        Request request = new Request(Operation.PRETRAGA_PAKETA, paket);
        sender.send(request);
        Response response = (Response) receiver.receive();
        if (response.getException() == null) {
            return (List<Paket>) response.getResult();
        } else {
            throw response.getException();
        }
    }

    public void odjaviZaposlenog(Zaposleni zaposleni) throws Exception {
        Request request = new Request(Operation.ODJAVI_ZAPOSLENOG, zaposleni);
        sender.send(request);
//        Response response = (Response) receiver.receive();
//        if (response.getException() != null) {
//            throw response.getException();
//        }
    }

    public void sacuvajPrijave(List<Prijava> listaPrijava) throws Exception {
        Request request = new Request(Operation.SACUVAJ_SVE, listaPrijava);
        sender.send(request);
        Response response = (Response) receiver.receive();
        if (response.getException() == null) {
            
        } else {
            throw response.getException();
        }
    }

}
