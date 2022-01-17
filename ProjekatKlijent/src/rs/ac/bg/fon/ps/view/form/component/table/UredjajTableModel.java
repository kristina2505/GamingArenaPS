/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.ac.bg.fon.ps.view.form.component.table;

import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;
import rs.ac.bg.fon.ps.domain.Uredjaj;

/**
 *
 * @author Lenovo
 */
public class UredjajTableModel extends AbstractTableModel{
    private List<Uredjaj> listaUredjaja;
    private String[] kolone={"Vrsta uredjaja", "Model", "Opis"};

    public UredjajTableModel() {
        listaUredjaja=new ArrayList<>();
        
    }

    public void setListaUredjaja(List<Uredjaj> listaUredjaja) {
        this.listaUredjaja = listaUredjaja;
        fireTableDataChanged();
    }
    

    public UredjajTableModel(List<Uredjaj> listaUredjaja) {
        this.listaUredjaja = listaUredjaja;
    }

    @Override
    public String getColumnName(int column) {
        return kolone[column];
    }
    
    

    @Override
    public int getRowCount() {
        return listaUredjaja.size();
    }

    @Override
    public int getColumnCount() {
        return kolone.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Uredjaj uredjaj = listaUredjaja.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return uredjaj.getVrstaUredjaja();
            case 1:
                return uredjaj.getModel();
            case 2:
                return uredjaj.getOpis();
            default:
                return "n/a";
        }
    }
    
    public void obrisiUredjaj(int red) {
        listaUredjaja.remove(red);
        fireTableDataChanged();
    }

    public void dodajUredjaj(Uredjaj uredjaj) {
        listaUredjaja.add(uredjaj);
        fireTableDataChanged();
    }

    public List<Uredjaj> getListaUredjaja() {
        return listaUredjaja;
    }
}
