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
import rs.ac.bg.fon.ps.domain.Zaposleni;
import rs.ac.bg.fon.ps.repository.db.DbConnectionFactory;
import rs.ac.bg.fon.ps.repository.db.DbRepository;

/**
 *
 * @author Lenovo
 */
public class RepositoryDBZaposleni implements DbRepository<Zaposleni>{

    @Override
    public List<Zaposleni> getAll() throws Exception {
        try {
            String sql="SELECT * FROM zaposleni";
            List<Zaposleni> listaZaposlenih=new ArrayList<>();
            Connection connection=DbConnectionFactory.getInstance().getConnection();
            Statement statement=connection.createStatement();
            ResultSet rs=statement.executeQuery(sql);
            while(rs.next()){
                Zaposleni zaposleni=new Zaposleni(rs.getInt("zaposleniID") , rs.getString("username"), rs.getString("password"), rs.getString("ime"), rs.getString("prezime"));
                listaZaposlenih.add(zaposleni);
            }
            rs.close();
            statement.close();
            return listaZaposlenih;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new Exception("Greska prilikom ucitavanja zaposlenih! \n"+e.getMessage(),e);
        }
    }

    @Override
    public void add(Zaposleni param) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }


    public Zaposleni get(int id) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void edit(Zaposleni param) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void delete(Zaposleni param) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Zaposleni> getAll(Zaposleni param) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Zaposleni get(Zaposleni param) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Zaposleni> getAllPoUslovu(Zaposleni param) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
