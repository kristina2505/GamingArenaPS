/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.ac.bg.fon.ps.view.form.component.table;

import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;
import rs.ac.bg.fon.ps.domain.Klijent;

/**
 *
 * @author Lenovo
 */
public class KlijentTableModel extends AbstractTableModel{
    List<Klijent> listaKlijenata;
    private String[] kolone = {"ID klijenta", "Ime", "Prezime", "JMBG", "E_MAIL", "Telefon", "Adresa"};



    public KlijentTableModel(List<Klijent> listaKlijenata) {
        this.listaKlijenata = listaKlijenata;
    }

    public KlijentTableModel() {
        listaKlijenata=new ArrayList<>();
    }

    @Override
    public String getColumnName(int column) {
        return kolone[column];
    }

    public void setListaKlijenata(List<Klijent> listaKlijenata) {
        this.listaKlijenata = listaKlijenata;
        fireTableDataChanged();
    }

    public List<Klijent> getListaKlijenata() {
        return listaKlijenata;
    }

    @Override
    public int getRowCount() {
        if (listaKlijenata == null) {
            return 0;
        }
        return listaKlijenata.size();
    }

    @Override
    public int getColumnCount() {
        return kolone.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Klijent klijent=listaKlijenata.get(rowIndex);
        switch(columnIndex){
            case 0:
                return klijent.getKlijentID();
            case 1:
                return klijent.getIme();
            case 2:
                return klijent.getPrezime();
            case 3:
                return klijent.getJmbg();
            case 4:
                return klijent.geteMail();
            case 5:
                return klijent.getTelefon();
            case 6:
                return klijent.getAdresa();
            default:
                return "n/a";
        }
    }
    
    public Klijent vratiKlijenta(int red){
        return listaKlijenata.get(red);
    }
    
}
