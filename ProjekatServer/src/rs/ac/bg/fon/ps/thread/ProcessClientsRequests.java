/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.ac.bg.fon.ps.thread;

import java.io.IOException;
import java.net.Socket;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import rs.ac.bg.fon.ps.communication.Receiver;
import rs.ac.bg.fon.ps.communication.Request;
import rs.ac.bg.fon.ps.communication.Response;
import rs.ac.bg.fon.ps.communication.Sender;
import rs.ac.bg.fon.ps.controller.Controller;
import rs.ac.bg.fon.ps.domain.Klijent;
import rs.ac.bg.fon.ps.domain.Paket;
import rs.ac.bg.fon.ps.domain.Prijava;
import rs.ac.bg.fon.ps.domain.Zaposleni;
import rs.ac.bg.fon.ps.view.controller.MainController;

/**
 *
 * @author Lenovo
 */
public class ProcessClientsRequests extends Thread {

    Socket socket;
    private boolean state=false;

    public ProcessClientsRequests(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        Sender sender = new Sender(socket);
        Receiver receiver = new Receiver(socket);
        while (!false) {
            try {
                Request request = (Request) receiver.receive();
                Response response = new Response();
                try {
                    switch (request.getOperation()) {

                        case ZAPOSLENI_LOGIN:
                            Zaposleni zaposleni = (Zaposleni) request.getArgument();
                            Zaposleni ulogovani=Controller.getInstance().zaposleniLogin(zaposleni.getUsername(), zaposleni.getPassword(), this);
                            MainController.getInstance().dodajUlogovanog(ulogovani);
                            response.setResult(ulogovani);
                            break;
                        case GET_ALL_UREDJAJI:
                            response.setResult(Controller.getInstance().getAllUredjaji());
                            break;
                        case GET_ALL_KLIJENTI:
                            response.setResult(Controller.getInstance().getAllKlijenti());
                            break;
                        case GET_ALL_PAKETI:
                            response.setResult(Controller.getInstance().getAllPaketi());
                            break;
                        case ADD_PAKET:
                            Paket paket = (Paket) request.getArgument();
                            Controller.getInstance().addPaket(paket);
                            break;
                        case ADD_PRIJAVA:
                            Prijava prijava = (Prijava) request.getArgument();
                            Controller.getInstance().addPrijava(prijava);
                            response.setResult(prijava);
                            break;
                        case IZMENI_PAKET:
                            Paket paket1 = (Paket) request.getArgument();
                            Controller.getInstance().izmeniPaket(paket1);
                            break;
                        case OBRISI_PAKET:
                            Paket paket2 = (Paket) request.getArgument();
                            Controller.getInstance().obrisiPaket(paket2);
                            break;
                        case VRATI_PAKET:
                            Paket paketZahtev = (Paket) request.getArgument();
                            Paket paket3 = Controller.getInstance().vratiPaket(paketZahtev);
                            response.setResult(paket3);
                            break;
                        case ADD_KLIJENT:
                            Klijent noviKlijent=(Klijent) request.getArgument();
                            Controller.getInstance().unesiKlijenta(noviKlijent);
                            break;
                        case OBRISI_KLIJENTA:
                            Klijent klijentBrisanje=(Klijent) request.getArgument();
                            Controller.getInstance().obrisiKlijenta(klijentBrisanje);
                            break;
                        case PRETRAGA_KLIJENATA:
                            Klijent klijent=(Klijent) request.getArgument();
                            List<Klijent> listaPretragaKlijenata=Controller.getInstance().vratiKlijentePoUslovu(klijent);
                            response.setResult(listaPretragaKlijenata);
                            break;
                        case VRATI_AKTIVNE_PRIJAVE:
                            Prijava prijavaUslov=(Prijava) request.getArgument();
                            List<Prijava> listaAktivnihPrijava=Controller.getInstance().vratiAktivnePrijave(prijavaUslov);
                            response.setResult(listaAktivnihPrijava);
                            break;
                        case ODJAVI_KORISNIKA:
                            Prijava prijavaOdj=(Prijava) request.getArgument();
                            Controller.getInstance().odjaviSaPaketa(prijavaOdj);
                            break;
                        case PRETRAGA_PAKETA:
                            Paket paketPretraga=(Paket) request.getArgument();
                            List<Paket> listaPaketaIzPretrage=Controller.getInstance().pretraziPakete(paketPretraga);
                            response.setResult(listaPaketaIzPretrage);
                            break;
                        case ODJAVI_ZAPOSLENOG:
                            Zaposleni zaposleniLogout=(Zaposleni) request.getArgument();
                            MainController.getInstance().izbaciOdjavljenog(zaposleniLogout);
                            Controller.getInstance().odjaviZaposlenog(this);
                            break;
                        case SACUVAJ_SVE:
                            List<Prijava> listaZaCuvanje=(List<Prijava>) request.getArgument();
                            Controller.getInstance().sacuvajSvePrijave(listaZaCuvanje);
                            break;
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                    response.setException(ex);
                }
                sender.send(response);
            } catch (Exception ex) {
                Logger.getLogger(ProcessClientsRequests.class.getName()).log(Level.SEVERE, null, ex);
            }System.out.println("PREKIDA SE KLIJENTSKA NIT");
        }
    }

    public void ugasiNit() {
        state=true;
        try {
            socket.close();
        } catch (IOException ex) {
            System.out.println("Gasi se klijentska nit!");
        }
    }

}
