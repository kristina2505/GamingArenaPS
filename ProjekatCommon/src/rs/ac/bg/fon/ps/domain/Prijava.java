/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.ac.bg.fon.ps.domain;

import java.io.Serializable;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author Lenovo
 */
public class Prijava implements GenericEntity {

    private int prijavaID;
    private Paket paket;
    private Klijent klijent;
    private Zaposleni zaposleni;
    private Date datumPrijave;
    private Date datumOdjave;
    private double cena;
    private StatusPrijave statusPrijave;

    public Prijava() {
    }

    public Prijava(int prijavaID, Paket paket, Klijent klijent, Zaposleni zaposleni, Date datumPrijave, Date datumOdjave, double cena, StatusPrijave statusPrijave) {
        this.prijavaID = prijavaID;
        this.paket = paket;
        this.klijent = klijent;
        this.zaposleni = zaposleni;
        this.datumPrijave = datumPrijave;
        this.datumOdjave = datumOdjave;
        this.cena = cena;
        this.statusPrijave = statusPrijave;
    }

    public int getPrijavaID() {
        return prijavaID;
    }

    public void setPrijavaID(int prijavaID) {
        this.prijavaID = prijavaID;
    }

    public Paket getPaket() {
        return paket;
    }

    public void setPaket(Paket paket) {
        this.paket = paket;
    }

    public Klijent getKlijent() {
        return klijent;
    }

    public void setKlijent(Klijent klijent) {
        this.klijent = klijent;
    }

    public Zaposleni getZaposleni() {
        return zaposleni;
    }

    public void setZaposleni(Zaposleni zaposleni) {
        this.zaposleni = zaposleni;
    }

    public Date getDatumPrijave() {
        return datumPrijave;
    }

    public void setDatumPrijave(Date datumPrijave) {
        this.datumPrijave = datumPrijave;
    }

    public Date getDatumOdjave() {
        return datumOdjave;
    }

    public void setDatumOdjave(Date datumOdjave) {
        this.datumOdjave = datumOdjave;
    }

    public double getCena() {
        return cena;
    }

    public void setCena(double cena) {
        this.cena = cena;
    }

    public StatusPrijave getStatusPrijave() {
        return statusPrijave;
    }

    public void setStatusPrijave(StatusPrijave statusPrijave) {
        this.statusPrijave = statusPrijave;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 97 * hash + this.prijavaID;
        hash = 97 * hash + Objects.hashCode(this.paket);
        hash = 97 * hash + Objects.hashCode(this.klijent);
        hash = 97 * hash + Objects.hashCode(this.zaposleni);
        hash = 97 * hash + Objects.hashCode(this.datumPrijave);
        hash = 97 * hash + Objects.hashCode(this.datumOdjave);
        hash = 97 * hash + (int) (Double.doubleToLongBits(this.cena) ^ (Double.doubleToLongBits(this.cena) >>> 32));
        hash = 97 * hash + Objects.hashCode(this.statusPrijave);
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
        final Prijava other = (Prijava) obj;
        if (this.prijavaID != other.prijavaID) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Prijava{" + "prijavaID=" + prijavaID + ", paket=" + paket + ", klijent=" + klijent + ", zaposleni=" + zaposleni + ", datumPrijave=" + datumPrijave + ", datumOdjave=" + datumOdjave + ", cena=" + cena + ", statusPrijave=" + statusPrijave + '}';
    }

    @Override
    public String getTableName() {
        return "prijava";
    }

    @Override
    public String getColumnNamesForInsert() {
        return "paketID, klijentID, zaposleniID, datumPrijave, cena, statusPrijave";
    }

    @Override
    public String getInsertValues() {
        StringBuilder sb = new StringBuilder();
        java.sql.Date datumPrijaveSql=new java.sql.Date(datumPrijave.getTime());
        //java.sql.Date datumOdjaveSql=new java.sql.Date(new Date().getTime());
        sb.append(paket.getPaketID()).append(", ").append(klijent.getKlijentID()).append(", ").append(zaposleni.getZaposleniID())
                .append(", '").append(datumPrijaveSql).append("', ").append(cena)
                .append(", ").append("'").append(String.valueOf(statusPrijave)).append("' ");
        return sb.toString();
    }

    @Override
    public void setID(int id) {
        this.prijavaID = id;
    }

    @Override
    public String getColumns() {
        return "pr.prijavaID, p.paketID, p.naziv, p.broj_sati, p.cena, p.uredjajID, "
                + "k.klijentID, k.ime, k.prezime, k.jmbg, k.e_mail, k.telefon, k.adresa, "
                + "z.zaposleniID, z.ime, z.prezime, z.username, z.password, "
                + "pr.datumPrijave, pr.cena, pr.statusPrijave, "
                + "u.uredjajID, u.vrstaUredjaja, u.model, u.opis ";
    }

    @Override
    public String tableNameForGetAll() {
        return "prijava pr inner join paket p on (pr.paketID=p.paketID) inner join klijent k on (pr.klijentID=k.klijentID) "
                + "inner join zaposleni z on (pr.zaposleniID=z.zaposleniID) inner join uredjaj u on (p.uredjajID=u.uredjajID)";
    }

    @Override
    public List<GenericEntity> getList(ResultSet rs) throws Exception {
        List<GenericEntity> lista=new ArrayList<>();
        while(rs.next()){
            Prijava prijava = new Prijava();
            prijava.setPrijavaID(rs.getInt("pr.prijavaID"));
            prijava.setCena(rs.getDouble("pr.cena"));
            //prijava.setDatumOdjave(rs.getDate("pr.datumOdjave"));
            prijava.setDatumPrijave(rs.getDate("pr.datumPrijave"));
            prijava.setStatusPrijave(StatusPrijave.valueOf(rs.getString("pr.statusPrijave")));
            Uredjaj uredjaj = new Uredjaj(rs.getInt("u.uredjajID"), rs.getString("u.vrstaUredjaja"), rs.getString("u.model"), rs.getString("u.opis"));
            Paket paket = new Paket(rs.getInt("p.paketID"), rs.getString("p.naziv"), rs.getInt("p.broj_sati"), rs.getDouble("p.cena"), uredjaj);
            Klijent klijent = new Klijent(rs.getInt("k.klijentID"), rs.getString("k.ime"), rs.getString("k.prezime"), rs.getString("k.jmbg"), rs.getString("k.e_mail"), rs.getString("k.telefon"), rs.getString("k.adresa"));
            Zaposleni zaposleni=new Zaposleni(rs.getInt("z.zaposleniID"), rs.getString("z.username"), rs.getString("z.password"), rs.getString("z.ime"), rs.getString("z.prezime"));
            prijava.setPaket(paket);
            prijava.setKlijent(klijent);
            prijava.setZaposleni(zaposleni);
            lista.add(prijava);
        }return lista;
    }

    @Override
    public String getUslovBrisanja() {
        return " prijavaID=" + prijavaID;
    }

    @Override
    public String getPoljaIZmene() {
        java.sql.Date datumOdjaveSql=new java.sql.Date(new Date().getTime());
        return " datumOdjave='" + datumOdjaveSql + "', statusPrijave='" + statusPrijave.ODJAVLJEN + "' ";
    }

    @Override
    public GenericEntity getEntity(ResultSet rs) throws Exception {
        if (rs.next()) {
            Prijava prijava = new Prijava();
            prijava.setPrijavaID(rs.getInt("pr.prijavaID"));
            prijava.setCena(rs.getDouble("pr.cena"));
            //prijava.setDatumOdjave(rs.getDate("pr.datumOdjave"));
            prijava.setDatumPrijave(rs.getDate("pr.datumPrijave"));
            prijava.setStatusPrijave(StatusPrijave.valueOf(rs.getString("pr.statusPrijave")));
            Uredjaj uredjaj = new Uredjaj(rs.getInt("u.uredjajID"), rs.getString("u.vrstaUredjaja"), rs.getString("u.model"), rs.getString("u.opis"));
            Paket paket = new Paket(rs.getInt("p.paketID"), rs.getString("p.naziv"), rs.getInt("p.broj_sati"), rs.getDouble("p.cena"), uredjaj);
            Klijent klijent = new Klijent(rs.getInt("k.klijentID"), rs.getString("k.ime"), rs.getString("k.prezime"), rs.getString("k.jmbg"), rs.getString("k.e_mail"), rs.getString("k.telefon"), rs.getString("k.adresa"));
            Zaposleni zaposleni=new Zaposleni(rs.getInt("z.zaposleniID"), rs.getString("z.username"), rs.getString("z.password"), rs.getString("z.ime"), rs.getString("z.prezime"));
            prijava.setPaket(paket);
            prijava.setKlijent(klijent);
            prijava.setZaposleni(zaposleni);
            return prijava;
        }
        throw new Exception("Nije pronadjena prijava na osnovu unetih vrednosti!");
    }

    @Override
    public String getUslovPretrage() {
        return "pr.statusPrijave='"+String.valueOf(StatusPrijave.PRIJAVLJEN)+"' AND k.prezime like '%"+klijent.getPrezime()+"%' AND k.ime like '%"+klijent.getIme()+"%'";
    }

}
