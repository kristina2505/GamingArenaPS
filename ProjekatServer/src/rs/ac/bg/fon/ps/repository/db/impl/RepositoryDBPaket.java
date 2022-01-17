/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.ac.bg.fon.ps.repository.db.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import rs.ac.bg.fon.ps.domain.Paket;
import rs.ac.bg.fon.ps.domain.Uredjaj;
import rs.ac.bg.fon.ps.repository.db.DbConnectionFactory;
import rs.ac.bg.fon.ps.repository.db.DbRepository;

/**
 *
 * @author Lenovo
 */
public class RepositoryDBPaket implements DbRepository<Paket> {

    @Override
    public List<Paket> getAll() throws Exception {
        try {
            String sql = "select p.paketID as paketID,p.naziv as naziv, p.broj_sati, p.cena, "
                    + " u.uredjajID as uredjajID, u.vrstaUredjaja as vrstaUredjaja, u.model, u.opis "
                    + " from paket p inner join uredjaj u on (p.uredjajID = u.uredjajID)";
            List<Paket> listaPaketa = new ArrayList<>();
            Connection connection = DbConnectionFactory.getInstance().getConnection();
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            while (rs.next()) {
                Paket paket = new Paket();
                paket.setPaketID(rs.getInt("paketID"));
                paket.setNazivPaketa(rs.getString("naziv"));
                paket.setBrojSati(rs.getInt("broj_sati"));
                paket.setCena(rs.getDouble("cena"));
                Uredjaj uredjaj = new Uredjaj(rs.getInt("uredjajID"), rs.getString("vrstaUredjaja"), rs.getString("model"), rs.getString("opis"));
                paket.setUredjaj(uredjaj);
                listaPaketa.add(paket);
            }
            rs.close();
            statement.close();
            return listaPaketa;

        } catch (SQLException e) {
            e.printStackTrace();
            throw new Exception("Greska prilikom ucitavanja paketa: \n" + e.getMessage(), e);

        }
    }

    @Override
    public void add(Paket paket) throws Exception {
        try {
            String sql = "INSERT INTO paket (paketID, naziv, broj_sati, cena, uredjajID) VALUES(?,?,?,?,?)";
            Connection connection = DbConnectionFactory.getInstance().getConnection();
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.setInt(1, paket.getPaketID());
                preparedStatement.setString(2, paket.getNazivPaketa());
                preparedStatement.setInt(3, paket.getBrojSati());
                preparedStatement.setDouble(4, paket.getCena());
                preparedStatement.setInt(5, paket.getUredjaj().getUredjajID());
                preparedStatement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new Exception("Greska prilikom dodavanja paketa: \n" + e.getMessage(), e);
        }

    }

    @Override
    public void edit(Paket param) throws Exception {
        try {
            String sql = "UPDATE paket SET "
                    + "naziv= '" + param.getNazivPaketa() + "' ,"
                    + "broj_sati=" + param.getBrojSati() + ", "
                    + "cena=" + param.getCena() + ", "
                    + "uredjajID=" + param.getUredjaj().getUredjajID() + " "
                    + "WHERE paketID=" + param.getPaketID();
            System.out.println(sql);
            Connection connection = DbConnectionFactory.getInstance().getConnection();
            Statement statement = connection.createStatement();
            statement.executeUpdate(sql);
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new Exception("Greska prilikom dodavanja paketa: \n" + e.getMessage(), e);
        }
    }

    @Override
    public void delete(Paket param) throws Exception {
        try {
            String sql = "DELETE FROM paket WHERE paketID="+param.getPaketID();
            Connection connection = DbConnectionFactory.getInstance().getConnection();
            Statement statement = connection.createStatement();
            statement.executeUpdate(sql);
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new Exception("Greska prilikom brisanja paketa: \n" + e.getMessage(), e);
        }
    }


    public Paket get(int id) throws Exception {

        try {
            String sql = "select p.paketID as paketID,p.naziv as naziv, p.broj_sati, p.cena, "
                    + " u.uredjajID as uredjajID, u.vrstaUredjaja as vrstaUredjaja, u.model, u.opis "
                    + " from paket p inner join uredjaj u on (p.uredjajID = u.uredjajID)"
                    + "WHERE p.paketID=" + id;

            Connection connection = DbConnectionFactory.getInstance().getConnection();
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            Paket paket = new Paket();
            while (rs.next()) {

                paket.setPaketID(rs.getInt("paketID"));
                paket.setNazivPaketa(rs.getString("naziv"));
                paket.setBrojSati(rs.getInt("broj_sati"));
                paket.setCena(rs.getDouble("cena"));
                Uredjaj uredjaj = new Uredjaj(rs.getInt("uredjajID"), rs.getString("vrstaUredjaja"), rs.getString("model"), rs.getString("opis"));
                paket.setUredjaj(uredjaj);
            }
            rs.close();
            statement.close();
            return paket;

        } catch (SQLException e) {
            e.printStackTrace();
            throw new Exception("Greska prilikom ucitavanja paketa: \n" + e.getMessage(), e);

        }
    }

    @Override
    public List<Paket> getAll(Paket param) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Paket get(Paket param) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Paket> getAllPoUslovu(Paket param) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
