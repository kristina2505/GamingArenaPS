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
import java.util.List;
import rs.ac.bg.fon.ps.domain.GenericEntity;
import rs.ac.bg.fon.ps.domain.Paket;
import rs.ac.bg.fon.ps.repository.db.DbConnectionFactory;
import rs.ac.bg.fon.ps.repository.db.DbRepository;

/**
 *
 * @author Lenovo
 */
public class RepositoryDBGeneric implements DbRepository<GenericEntity> {

    @Override
    public List<GenericEntity> getAll() throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void add(GenericEntity entity) throws Exception {
        try {
            Connection connection = DbConnectionFactory.getInstance().getConnection();
            StringBuilder sb = new StringBuilder();
            sb.append("INSERT INTO ").append(entity.getTableName()).append(" (").
                    append(entity.getColumnNamesForInsert()).append(") ")
                    .append("VALUES (").append(entity.getInsertValues()).append(") ");
            String query = sb.toString();
            System.out.println(query);
            Statement statement = connection.createStatement();
            statement.executeUpdate(query, Statement.RETURN_GENERATED_KEYS);
            ResultSet rsKey = statement.getGeneratedKeys();
            if (rsKey.next()) {
                int id = rsKey.getInt(1);
                entity.setID(id);
            }
            statement.close();
            rsKey.close();
        } catch (SQLException e) {
            throw e;
        }
    }

    @Override
    public GenericEntity get(GenericEntity entity) throws Exception {
        try {
            
            Connection connection = DbConnectionFactory.getInstance().getConnection();
            StringBuilder sb = new StringBuilder();
            sb.append("SELECT ").append(entity.getColumns()).append(" FROM ").
                    append(entity.tableNameForGetAll()).append(" WHERE ").append(entity.getUslovBrisanja());
            String query = sb.toString();
            System.out.println(query);
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(query);
            GenericEntity genericEntity = entity.getEntity(rs);
            rs.close();
            statement.close();
            return genericEntity;
        } catch (SQLException e) {
            throw e;
        }
    }

    @Override
    public void edit(GenericEntity entity) throws Exception {
        try {
            Connection connection = DbConnectionFactory.getInstance().getConnection();
            StringBuilder sb = new StringBuilder();
            sb.append("UPDATE ").append(entity.getTableName()).append(" SET ").
                    append(entity.getPoljaIZmene()).append(" WHERE ").append(entity.getUslovBrisanja());
            String query = sb.toString();
            System.out.println(query);
            Statement statement = connection.createStatement();
            statement.executeUpdate(query);
            statement.close();
        } catch (SQLException e) {
            throw e;
        }
    }

    @Override
    public void delete(GenericEntity entity) throws Exception {
        try {
            Connection connection = DbConnectionFactory.getInstance().getConnection();
            StringBuilder sb = new StringBuilder();
            sb.append("DELETE FROM ").append(entity.getTableName()).append(" WHERE ").
                    append(entity.getUslovBrisanja());
            String query = sb.toString();
            System.out.println(query);
            Statement statement = connection.createStatement();
            statement.executeUpdate(query);
            statement.close();
        } catch (SQLException e) {
            throw e;
        }
    }

    @Override
    public List<GenericEntity> getAll(GenericEntity entity) throws Exception {
        try {
            Connection connection = DbConnectionFactory.getInstance().getConnection();
            StringBuilder sb = new StringBuilder();
            sb.append("SELECT ").append(entity.getColumns()).append(" FROM ").
                    append(entity.tableNameForGetAll());
            String query = sb.toString();
            System.out.println(query);
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(query);
            List<GenericEntity> lista = entity.getList(rs);
            rs.close();
            statement.close();
            return lista;
        } catch (SQLException e) {
            throw e;
        }

    }

    @Override
    public List<GenericEntity> getAllPoUslovu(GenericEntity entity) throws Exception {
        try {
            Connection connection = DbConnectionFactory.getInstance().getConnection();
            StringBuilder sb = new StringBuilder();
            sb.append("SELECT ").append(entity.getColumns()).append(" FROM ").
                    append(entity.tableNameForGetAll()).append(" WHERE ").append(entity.getUslovPretrage());
            String query = sb.toString();
            System.out.println(query);
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(query);
            List<GenericEntity> lista = entity.getList(rs);
            rs.close();
            statement.close();
            return lista;
        } catch (SQLException e) {
            throw e;
        }
    }

}
