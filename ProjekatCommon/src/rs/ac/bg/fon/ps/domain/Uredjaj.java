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
public class Uredjaj implements GenericEntity {

    private int uredjajID;
    private String vrstaUredjaja;
    private String model;
    private String opis;

    public Uredjaj() {
    }

    public Uredjaj(int uredjajID, String vrstaUredjaja, String model, String opis) {
        this.uredjajID = uredjajID;
        this.vrstaUredjaja = vrstaUredjaja;
        this.model = model;
        this.opis = opis;
    }

    public String getOpis() {
        return opis;
    }

    public void setOpis(String opis) {
        this.opis = opis;
    }

    public int getUredjajID() {
        return uredjajID;
    }

    public void setUredjajID(int uredjajID) {
        this.uredjajID = uredjajID;
    }

    public String getVrstaUredjaja() {
        return vrstaUredjaja;
    }

    public void setVrstaUredjaja(String vrstaUredjaja) {
        this.vrstaUredjaja = vrstaUredjaja;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 59 * hash + this.uredjajID;
        hash = 59 * hash + Objects.hashCode(this.vrstaUredjaja);
        hash = 59 * hash + Objects.hashCode(this.model);
        hash = 59 * hash + Objects.hashCode(this.opis);
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
        final Uredjaj other = (Uredjaj) obj;
        if (this.uredjajID != other.uredjajID) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return vrstaUredjaja + ": " + model;
    }

    @Override
    public String getTableName() {
        return "uredjaj";
    }

    @Override
    public String getColumnNamesForInsert() {
        return "*";
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
        return "uredjaj";
    }

    @Override
    public List<GenericEntity> getList(ResultSet rs) throws Exception {
        List<GenericEntity> listaUredjaja = new ArrayList<>();
        while (rs.next()) {
            Uredjaj uredjaj = new Uredjaj(rs.getInt("uredjajID"), rs.getString("vrstaUredjaja"), rs.getString("model"), rs.getString("opis"));
            listaUredjaja.add(uredjaj);
        }
        return listaUredjaja;
    }

    @Override
    public String getUslovBrisanja() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getPoljaIZmene() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public GenericEntity getEntity(ResultSet rs) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getUslovPretrage() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
