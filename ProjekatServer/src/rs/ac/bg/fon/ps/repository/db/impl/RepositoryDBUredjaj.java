/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.ac.bg.fon.ps.repository.db.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import rs.ac.bg.fon.ps.domain.Uredjaj;
import rs.ac.bg.fon.ps.repository.db.DbConnectionFactory;
import rs.ac.bg.fon.ps.repository.db.DbRepository;

/**
 *
 * @author Lenovo
 */
public class RepositoryDBUredjaj implements DbRepository<Uredjaj>{

    @Override
    public List<Uredjaj> getAll() throws Exception {
        try {
            String sql="SELECT * FROM uredjaj";
            List<Uredjaj> listaUredjaja=new ArrayList<>();
            Connection connection=DbConnectionFactory.getInstance().getConnection();
            Statement statement=connection.createStatement();
            ResultSet rs=statement.executeQuery(sql);
            while(rs.next()){
                Uredjaj uredjaj=new Uredjaj(rs.getInt("uredjajID"), rs.getString("vrstaUredjaja"), rs.getString("model"), rs.getString("opis"));
                listaUredjaja.add(uredjaj);
            }
            rs.close();
            statement.close();
            return listaUredjaja;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new Exception("Greska prilikom ucitavanja uredjaja! \n"+e.getMessage(),e);
        }
        
    }

    @Override
    public void add(Uredjaj param) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public Uredjaj get(int id) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void edit(Uredjaj param) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void delete(Uredjaj param) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Uredjaj> getAll(Uredjaj param) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Uredjaj get(Uredjaj param) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Uredjaj> getAllPoUslovu(Uredjaj param) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
