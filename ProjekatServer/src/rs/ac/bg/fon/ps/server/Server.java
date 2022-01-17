/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.ac.bg.fon.ps.server;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import rs.ac.bg.fon.ps.controller.Controller;
import rs.ac.bg.fon.ps.thread.ProcessClientsRequests;

/**
 *
 * @author Lenovo
 */
public class Server extends Thread{

    ServerSocket serverSocket;
    private boolean state=false;
    
    

    @Override
    public void run() {
        try {
            Properties properties=new Properties();
            properties.load(new FileInputStream("config/dbconfig.properties"));
            serverSocket = new ServerSocket(Integer.parseInt(properties.getProperty("port")));
            while (!state) {
                
                System.out.println("Waiting for connection...");
                Socket socket = serverSocket.accept();
                System.out.println("Connected!");
                handleClient(socket);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    


    private void handleClient(Socket socket) throws Exception {
        ProcessClientsRequests processClientsRequests = new ProcessClientsRequests(socket);
        processClientsRequests.start();
        
    }
    
    public void zaustaviServer(){
        this.state=true;
        Controller.getInstance().ugasiNiti();
        try {
            this.serverSocket.close();
        } catch (IOException ex) {
            System.out.println("Server se gasi");
        }
    }
}
