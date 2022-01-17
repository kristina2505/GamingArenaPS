/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.ac.bg.fon.ps.view.form.component.table;

import java.text.SimpleDateFormat;
import java.util.List;
import javax.swing.table.AbstractTableModel;
import rs.ac.bg.fon.ps.domain.Prijava;

/**
 *
 * @author Lenovo
 */
public class PrijavaTableModel extends AbstractTableModel{
    
    List<Prijava> listapriPrijava;
    String[] kolone={"Paket", "Ime", "Prezime", "Cena", "Datum prijave", "Vreme prijave"};
    SimpleDateFormat datumS=new SimpleDateFormat("dd.MM.yyyy.");
    SimpleDateFormat vremeS=new SimpleDateFormat("hh:mm");
    
    
    public PrijavaTableModel() {
    }

    public PrijavaTableModel(List<Prijava> listapriPrijava) {
        this.listapriPrijava = listapriPrijava;
    }

    @Override
    public String getColumnName(int column) {
        return kolone[column];
    }
    
    

    @Override
    public int getRowCount() {
        return listapriPrijava.size();
    }

    @Override
    public int getColumnCount() {
        return kolone.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Prijava prijava=listapriPrijava.get(rowIndex);
        switch(columnIndex){
            case 0:
                return prijava.getPaket().getNazivPaketa();
            case 1:
                return prijava.getKlijent().getIme();
            case 2:
                return prijava.getKlijent().getPrezime();
            case 3:
                return prijava.getCena();
            case 4:
                return datumS.format(prijava.getDatumPrijave());
            case 5:
                return vremeS.format(prijava.getDatumPrijave());
            default:
                return "n/a";
        }
    }

    public void setListapriPrijava(List<Prijava> listapriPrijava) {
        this.listapriPrijava = listapriPrijava;
        fireTableDataChanged();
    }

    public Prijava vratiPrijavu(int row) {
        return listapriPrijava.get(row);
    }

    public List<Prijava> getListapriPrijava() {
        return listapriPrijava;
    }
    
    
    
    
}
