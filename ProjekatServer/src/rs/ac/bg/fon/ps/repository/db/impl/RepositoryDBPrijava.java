/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.ac.bg.fon.ps.repository.db.impl;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import rs.ac.bg.fon.ps.domain.Prijava;
import rs.ac.bg.fon.ps.repository.db.DbConnectionFactory;
import rs.ac.bg.fon.ps.repository.db.DbRepository;

/**
 *
 * @author Lenovo
 */
public class RepositoryDBPrijava implements DbRepository<Prijava>{

    @Override
    public List<Prijava> getAll() throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void add(Prijava param) throws Exception {
        try {
            Connection connection=DbConnectionFactory.getInstance().getConnection();
            String query="INSERT INTO prijava (paketID, klijentID, zaposleniID, datumPrijave, cena, odjavljen) VALUES(?, ?, ?, ?, ?, ?)";
            PreparedStatement ps=connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, param.getPaket().getPaketID());
            ps.setInt(2, param.getKlijent().getKlijentID());
            ps.setInt(3, param.getZaposleni().getZaposleniID());
            ps.setDate(4, new Date(param.getDatumPrijave().getTime()));
            ps.setDouble(5, param.getCena());
            
            ps.executeUpdate();
            
            ResultSet rsKey=ps.getGeneratedKeys();
            if(rsKey.next()){
                int prijavaID=rsKey.getInt(1);
                param.setPrijavaID(prijavaID);
            }else{
                throw new Exception("ID prijave nije sacuvan!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }
    }


    public Prijava get(int id) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void edit(Prijava param) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void delete(Prijava param) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Prijava> getAll(Prijava param) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Prijava get(Prijava param) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Prijava> getAllPoUslovu(Prijava param) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
