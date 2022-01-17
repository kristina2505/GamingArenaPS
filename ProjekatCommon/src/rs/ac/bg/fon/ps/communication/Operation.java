/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.ac.bg.fon.ps.communication;

import java.io.Serializable;

/**
 *
 * @author Lenovo
 */
public enum Operation implements Serializable{
    ZAPOSLENI_LOGIN,
    ADD_PAKET,
    GET_ALL_PAKETI,
    OBRISI_PAKET,
    IZMENI_PAKET,
    VRATI_PAKET,
    ADD_PRIJAVA,
    GET_ALL_UREDJAJI,
    GET_ALL_KLIJENTI,
    ADD_KLIJENT,
    OBRISI_KLIJENTA,
    PRETRAGA_KLIJENATA,
    VRATI_AKTIVNE_PRIJAVE,
    ODJAVI_KORISNIKA,
    PRETRAGA_PAKETA,
    ODJAVI_ZAPOSLENOG,
    SACUVAJ_SVE;
}
