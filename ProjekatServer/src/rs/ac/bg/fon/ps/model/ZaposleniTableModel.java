/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.ac.bg.fon.ps.model;

import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;
import rs.ac.bg.fon.ps.domain.Zaposleni;

/**
 *
 * @author Lenovo
 */
public class ZaposleniTableModel extends AbstractTableModel {

    List<Zaposleni> listaZaposlenih;
    String[] kolone = {"ID", "Ime", "Prezime"};

    public ZaposleniTableModel() {
        listaZaposlenih = new ArrayList<>();
    }

    public ZaposleniTableModel(List<Zaposleni> listaZaposlenih) {
        this.listaZaposlenih = listaZaposlenih;
    }

    @Override
    public String getColumnName(int column) {
        return kolone[column];
    }

    @Override
    public int getRowCount() {
        return listaZaposlenih.size();
    }

    @Override
    public int getColumnCount() {
        return kolone.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Zaposleni zaposleni=listaZaposlenih.get(rowIndex);
        switch(columnIndex){
            case 0:
                return zaposleni.getZaposleniID();
            case 1:
                return zaposleni.getIme();
            case 2:
                return zaposleni.getPrezime();
            default:
                return "n/a";
        }
    }

    public void setListaZaposlenih(List<Zaposleni> listaZaposlenih) {
        this.listaZaposlenih = listaZaposlenih;
        fireTableDataChanged();
    }

    public List<Zaposleni> getListaZaposlenih() {
        return listaZaposlenih;
    }
    
    public void dodajZaposlenog(Zaposleni zaposleni){
        listaZaposlenih.add(zaposleni);
        fireTableDataChanged();
    }
    
    public void izbaciZaposlenog(Zaposleni zaposleni){
        listaZaposlenih.remove(zaposleni);
        fireTableDataChanged();
    }

}
