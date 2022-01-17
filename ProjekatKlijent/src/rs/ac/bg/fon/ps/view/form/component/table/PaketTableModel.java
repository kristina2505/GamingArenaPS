/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.ac.bg.fon.ps.view.form.component.table;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.AbstractTableModel;
import rs.ac.bg.fon.ps.domain.Paket;
import rs.ac.bg.fon.ps.domain.Uredjaj;

/**
 *
 * @author Lenovo
 */
public class PaketTableModel extends AbstractTableModel {

    private List<Paket> listaPaketa;
    private String[] kolone = {"ID paketa", "Naziv paketa", "Broj sati", "Cena", "Uredjaji"};
    private Class[] klaseKolona={Integer.class, String.class, Integer.class, Double.class, Uredjaj.class};

    public PaketTableModel() {
        listaPaketa = new ArrayList<>();
    }

    public PaketTableModel(List<Paket> listaPaketa) {
        this.listaPaketa = listaPaketa;
    }

    public void setListaPaketa(List<Paket> listaPaketa) {
        this.listaPaketa = listaPaketa;
        fireTableDataChanged();
    }

    @Override
    public String getColumnName(int column) {
        return kolone[column];
    }

    @Override
    public int getRowCount() {
        if (listaPaketa == null) {
            return 0;
        }
        return listaPaketa.size();
    }

    @Override
    public int getColumnCount() {
        return kolone.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Paket paket = listaPaketa.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return paket.getPaketID();
            case 1:
                return paket.getNazivPaketa();
            case 2:
                return paket.getBrojSati();
            case 3:
                return paket.getCena();
            case 4:
                return paket.getUredjaj();
            default:
                return "n/a";
        }
    }

    public void izbrisiPaket(int red) {
        listaPaketa.remove(red);
        fireTableDataChanged();
    }

    public void dodajPaket(Paket paket) {
        listaPaketa.add(paket);
        fireTableDataChanged();
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        try {
            Paket paket = listaPaketa.get(rowIndex);
            switch (columnIndex) {
                case 0:
                    paket.setPaketID((int) aValue);
                    break;
                case 1:
                    paket.setNazivPaketa((String) aValue);
                    break;
                case 2:
                    paket.setBrojSati((int) aValue);
                    break;
                case 3:
                    paket.setCena((double) aValue);
                    break;
                case 4:
                    paket.setUredjaj((Uredjaj) aValue);
                    break;
                    
            }
            //Controller.getInstance().addPaket(paket);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        return klaseKolona[columnIndex];
    }
    
    


    
   public Paket getPaket(int red){
       return listaPaketa.get(red);
   }

    public void osveziPakete(List<Paket> listaPaketa) {
        this.listaPaketa=listaPaketa;
        System.out.println("paketi:" + listaPaketa);
        fireTableDataChanged();
    }

}
