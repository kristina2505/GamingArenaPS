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
public class Zaposleni implements GenericEntity {

    private int zaposleniID;
    private String username;
    private String password;
    private String ime;
    private String prezime;

    public Zaposleni() {
    }

    public Zaposleni(int zaposleniID, String username, String password, String ime, String prezime) {
        this.zaposleniID = zaposleniID;
        this.username = username;
        this.password = password;
        this.ime = ime;
        this.prezime = prezime;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 47 * hash + Objects.hashCode(this.zaposleniID);
        hash = 47 * hash + Objects.hashCode(this.username);
        hash = 47 * hash + Objects.hashCode(this.password);
        hash = 47 * hash + Objects.hashCode(this.ime);
        hash = 47 * hash + Objects.hashCode(this.prezime);
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
        final Zaposleni other = (Zaposleni) obj;
        if (!Objects.equals(this.username, other.username)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Zaposleni{" + "zaposleniID=" + zaposleniID + ", username=" + username + ", password=" + password + ", ime=" + ime + ", prezime=" + prezime + '}';
    }

    public int getZaposleniID() {
        return zaposleniID;
    }

    public void setZaposleniID(int zaposleniID) {
        this.zaposleniID = zaposleniID;
    }

    @Override
    public String getTableName() {
        return "zaposleni";
    }

    @Override
    public String getColumnNamesForInsert() {
        return "";
    }

    @Override
    public String getInsertValues() {
        return "";
    }

    @Override
    public void setID(int id) {
    }

    @Override
    public String getColumns() {
        return "*";
    }

    @Override
    public String tableNameForGetAll() {
        return "zaposleni";
    }

    @Override
    public List<GenericEntity> getList(ResultSet rs) throws Exception {
        List<GenericEntity> listaZaposlenih = new ArrayList<>();
        while (rs.next()) {
            Zaposleni zaposleni = new Zaposleni(rs.getInt("zaposleniID"), rs.getString("username"), rs.getString("password"), rs.getString("ime"), rs.getString("prezime"));
            listaZaposlenih.add(zaposleni);
        }
        return listaZaposlenih;
    }

    @Override
    public String getUslovBrisanja() {
        return "username='"+username+"' AND password='"+password+"'";
    }

    @Override
    public String getPoljaIZmene() {
        return "";
    }

    @Override
    public GenericEntity getEntity(ResultSet rs) throws Exception {
        if(rs.next()){
            return new Zaposleni(rs.getInt("zaposleniID"), rs.getString("username"), rs.getString("password"), rs.getString("ime"), rs.getString("prezime"));
        }
        throw new Exception("Sistem ne moze da pronadje zaposlenog na osnovu ucitanih vrednosti!");
    }

    @Override
    public String getUslovPretrage() {
        return "";
    }

}
