/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.ac.bg.fon.ps.domain;

import java.io.Serializable;
import java.sql.ResultSet;
import java.util.List;

/**
 *
 * @author Lenovo
 */
public interface GenericEntity extends Serializable{

    String getTableName();

    String getColumnNamesForInsert();

    String getInsertValues();

    void setID(int id);

    String getColumns();

    String tableNameForGetAll();

    public List<GenericEntity> getList(ResultSet rs) throws Exception;

    String getUslovBrisanja();

    String getPoljaIZmene();

    GenericEntity getEntity(ResultSet rs) throws Exception;

    String getUslovPretrage();
    
}
