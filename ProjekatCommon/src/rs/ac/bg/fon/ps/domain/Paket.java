/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.ac.bg.fon.ps.domain;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author Lenovo
 */
public class Paket implements GenericEntity {

    private int paketID;
    private String nazivPaketa;
    private int brojSati;
    private double cena;
    private Uredjaj uredjaj;

    public Paket() {
    }

    public Paket(int paketID, String nazivPaketa, int brojSati, double cena, Uredjaj uredjaj) {
        this.paketID = paketID;
        this.nazivPaketa = nazivPaketa;
        this.brojSati = brojSati;
        this.cena = cena;

        this.uredjaj = uredjaj;
    }

    public int getPaketID() {
        return paketID;
    }

    public void setPaketID(int paketID) {
        this.paketID = paketID;
    }

    public String getNazivPaketa() {
        return nazivPaketa;
    }

    public void setNazivPaketa(String nazivPaketa) {
        this.nazivPaketa = nazivPaketa;
    }

    public int getBrojSati() {
        return brojSati;
    }

    public void setBrojSati(int brojSati) {
        this.brojSati = brojSati;
    }

    public double getCena() {
        return cena;
    }

    public void setCena(double cena) {
        this.cena = cena;
    }



    public Uredjaj getUredjaj() {
        return uredjaj;
    }

    public void setUredjaj(Uredjaj uredjaj) {
        this.uredjaj = uredjaj;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 67 * hash + this.paketID;
        hash = 67 * hash + Objects.hashCode(this.nazivPaketa);
        hash = 67 * hash + this.brojSati;
        hash = 67 * hash + (int) (Double.doubleToLongBits(this.cena) ^ (Double.doubleToLongBits(this.cena) >>> 32));
        hash = 67 * hash + Objects.hashCode(this.uredjaj);
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
        final Paket other = (Paket) obj;
        if (this.paketID != other.paketID) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return nazivPaketa;
    }

    @Override
    public String getTableName() {
        return "paket";
    }

    @Override
    public String getColumnNamesForInsert() {
        return "paketID, naziv, broj_sati, cena, uredjajID";
    }

    @Override
    public String getInsertValues() {
        StringBuilder sb = new StringBuilder();
        sb.append(paketID).append(", ").append("'").append(nazivPaketa).append("', ").append(brojSati)
                .append(", ").append(cena).append(", ")
                .append(uredjaj.getUredjajID());
        return sb.toString();
    }

    @Override
    public void setID(int id) {
        paketID = id;
    }

    @Override
    public String getColumns() {
        return "p.paketID as paketID,p.naziv as naziv, p.broj_sati, p.cena, "
                + " u.uredjajID as uredjajID, u.vrstaUredjaja as vrstaUredjaja, u.model, u.opis";
    }

    @Override
    public String tableNameForGetAll() {
        return " paket p inner join uredjaj u on (p.uredjajID = u.uredjajID)";
    }

    @Override
    public List<GenericEntity> getList(ResultSet rs) throws Exception {
        List<GenericEntity> listaPaketa = new ArrayList<>();
        while (rs.next()) {
            Paket paket = new Paket();
            paket.setPaketID(rs.getInt("paketID"));
            paket.setNazivPaketa(rs.getString("naziv"));
            paket.setBrojSati(rs.getInt("broj_sati"));
            paket.setCena(rs.getDouble("cena"));
            Uredjaj uredjaj = new Uredjaj(rs.getInt("uredjajID"), rs.getString("vrstaUredjaja"), rs.getString("model"), rs.getString("opis"));
            paket.setUredjaj(uredjaj);
            listaPaketa.add(paket);
        }
        return listaPaketa;
    }

    @Override
    public String getUslovBrisanja() {
        return "paketID=" + paketID;
    }

    @Override
    public String getPoljaIZmene() {
        return "naziv= '" + getNazivPaketa() + "' ,"
                + "broj_sati=" + getBrojSati() + ", "
                + "cena=" + getCena() + ", "
                + "uredjajID=" + uredjaj.getUredjajID();
    }

    @Override
    public GenericEntity getEntity(ResultSet rs) throws SQLException {
        Paket paket = new Paket();
        while (rs.next()) {
            paket.setPaketID(rs.getInt("paketID"));
            paket.setNazivPaketa(rs.getString("naziv"));
            paket.setBrojSati(rs.getInt("broj_sati"));
            paket.setCena(rs.getDouble("cena"));
            Uredjaj uredjaj = new Uredjaj(rs.getInt("uredjajID"), rs.getString("vrstaUredjaja"), rs.getString("model"), rs.getString("opis"));
            paket.setUredjaj(uredjaj);
        }
        return paket;
    }

    @Override
    public String getUslovPretrage() {
        return " p.uredjajID="+uredjaj.getUredjajID();
    }

}
