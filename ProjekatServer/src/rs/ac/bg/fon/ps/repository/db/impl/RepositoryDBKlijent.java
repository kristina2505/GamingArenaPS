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
import java.util.ArrayList;
import java.util.List;
import rs.ac.bg.fon.ps.domain.Klijent;
import rs.ac.bg.fon.ps.domain.Zaposleni;
import rs.ac.bg.fon.ps.repository.db.DbConnectionFactory;
import rs.ac.bg.fon.ps.repository.db.DbRepository;

/**
 *
 * @author Lenovo
 */
public class RepositoryDBKlijent implements DbRepository<Klijent>{

    @Override
    public List<Klijent> getAll() throws Exception {
        try {
            String sql="SELECT * FROM klijent";
            List<Klijent> listaKlijenata=new ArrayList<>();
            Connection connection=DbConnectionFactory.getInstance().getConnection();
            Statement statement=connection.createStatement();
            ResultSet rs=statement.executeQuery(sql);
            while(rs.next()){
                Klijent klijent=new Klijent(rs.getInt("klijentID") , rs.getString("ime"), rs.getString("prezime"), rs.getString("jmbg"), rs.getString("e_mail"), rs.getString("telefon"), rs.getString("adresa"));
                listaKlijenata.add(klijent);
            }
            rs.close();
            statement.close();
            return listaKlijenata;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new Exception("Greska prilikom ucitavanja klijenata! \n"+e.getMessage(),e);
        }
    }

    @Override
    public void add(Klijent param) throws Exception {
        try {
            Connection connection=DbConnectionFactory.getInstance().getConnection();
            String query="INSERT INTO prijava (ime, prezime, jmbg, e_mail, telefon, adresa) VALUES(?, ?, ?, ?, ?, ?)";
            PreparedStatement ps=connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, param.getIme());
            ps.setString(2, param.getPrezime());
            ps.setString(3, param.getPrezime());
            ps.setString(4, param.geteMail());
            ps.setString(5, param.getTelefon());
            ps.setString(6, param.getAdresa());
            ps.executeUpdate();
            
            ResultSet rsKey=ps.getGeneratedKeys();
            if(rsKey.next()){
                int klijentID=rsKey.getInt(1);
                param.setKlijentID(klijentID);
            }else{
                throw new Exception("ID klijenta nije sacuvan!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }
    }



    @Override
    public void edit(Klijent param) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void delete(Klijent param) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Klijent> getAll(Klijent param) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Klijent get(Klijent param) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Klijent> getAllPoUslovu(Klijent param) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
