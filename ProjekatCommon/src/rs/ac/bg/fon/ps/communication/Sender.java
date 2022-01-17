/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.ac.bg.fon.ps.communication;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Lenovo
 */
public class Sender implements Serializable{
    private Socket socket;

    public Sender(Socket socket) {
        this.socket = socket;
    }
    public void send(Object object) throws Exception{
        try {
            ObjectOutputStream out=new ObjectOutputStream(socket.getOutputStream());
            out.writeObject(object);
            out.flush();
        } catch (IOException ex) {
            ex.printStackTrace();
            throw new Exception("Greska pri slanju objeka! \n"+ex.getMessage());
        }
    }
}
