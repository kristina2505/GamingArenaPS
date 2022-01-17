/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.ac.bg.fon.ps.domain;

import java.io.Serializable;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author Lenovo
 */
public class Klijent implements GenericEntity {

    private int klijentID;
    private String ime;
    private String prezime;
    private String jmbg;
    private String eMail;
    private String telefon;
    private String adresa;

    public Klijent() {
    }

    public Klijent(int klijentID, String ime, String prezime, String jmbg, String eMail, String telefon, String adresa) {
        this.klijentID = klijentID;
        this.ime = ime;
        this.prezime = prezime;
        this.jmbg = jmbg;
        this.eMail = eMail;
        this.telefon = telefon;
        this.adresa = adresa;
    }

    public String getAdresa() {
        return adresa;
    }

    public void setAdresa(String adresa) {
        this.adresa = adresa;
    }

    public int getKlijentID() {
        return klijentID;
    }

    public void setKlijentID(int klijentID) {
        this.klijentID = klijentID;
    }

    public String getIme() {
        return ime;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public String getPrezime() {
        return prezime;
    }

    public void setPrezime(String prezime) {
        this.prezime = prezime;
    }

    public String getJmbg() {
        return jmbg;
    }

    public void setJmbg(String jmbg) {
        this.jmbg = jmbg;
    }

    public String geteMail() {
        return eMail;
    }

    public void seteMail(String eMail) {
        this.eMail = eMail;
    }

    public String getTelefon() {
        return telefon;
    }

    public void setTelefon(String telefon) {
        this.telefon = telefon;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 17 * hash + this.klijentID;
        hash = 17 * hash + Objects.hashCode(this.ime);
        hash = 17 * hash + Objects.hashCode(this.prezime);
        hash = 17 * hash + Objects.hashCode(this.jmbg);
        hash = 17 * hash + Objects.hashCode(this.eMail);
        hash = 17 * hash + Objects.hashCode(this.telefon);
        hash = 17 * hash + Objects.hashCode(this.adresa);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Klijent other = (Klijent) obj;
        if (!Objects.equals(this.ime, other.ime)) {
            return false;
        }
        if (!Objects.equals(this.prezime, other.prezime)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Klijent{" + "klijentID=" + klijentID + ", ime=" + ime + ", prezime=" + prezime + ", jmbg=" + jmbg + ", eMail=" + eMail + ", telefon=" + telefon + ", adresa=" + adresa + '}';
    }

    @Override
    public String getTableName() {
        return "klijent";
    }

    @Override
    public String getColumnNamesForInsert() {
        return "klijentID, ime, prezime, jmbg, e_mail, telefon, adresa";
    }

    @Override
    public String getInsertValues() {
        StringBuilder sb = new StringBuilder();
        sb.append(klijentID).append(", ").append("'").append(ime).append("', ").append("'").append(prezime)
                .append("', ").append("'").append(jmbg).append("', ")
                .append("'").append(eMail).append("', ")
                .append("'").append(telefon).append("', ")
                .append("'").append(adresa).append("'");
        return sb.toString();
    }

    @Override
    public void setID(int id) {
        this.klijentID = id;
    }

    @Override
    public String getColumns() {
        return "*";
    }

    @Override
    public String tableNameForGetAll() {
        return "klijent";
    }

    @Override
    public List<GenericEntity> getList(ResultSet rs) throws Exception {
        List<GenericEntity> lista = new ArrayList<>();
        while (rs.next()) {
            Klijent klijent = new Klijent(rs.getInt("klijentID"), rs.getString("ime"), rs.getString("prezime"), rs.getString("jmbg"), rs.getString("e_mail"), rs.getString("telefon"), rs.getString("adresa"));
            lista.add(klijent);
        }
        return lista;
    }

    @Override
    public String getUslovBrisanja() {
        return "klijentID="+klijentID;
    }

    @Override
    public String getPoljaIZmene() {
        return "";
    }

    @Override
    public GenericEntity getEntity(ResultSet rs) throws Exception {
        if(rs.next()){
            return new Klijent(rs.getInt("klijentID"), rs.getString("ime"), rs.getString("prezime"), rs.getString("jmbg"), rs.getString("e_mail"), rs.getString("telefon"), rs.getString("adresa"));
        }
        throw new Exception("Ne postoji klijent sa unetim podacima!");
    }

    @Override
    public String getUslovPretrage() {
        return " ime like '%" + ime + "%' and prezime like '%" + prezime + "%' ";
    }

}
