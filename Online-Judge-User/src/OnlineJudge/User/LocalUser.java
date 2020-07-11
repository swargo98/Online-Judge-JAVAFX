/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package OnlineJudge.User;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author MAHDI
 */
public class LocalUser {
    private static Socket connection;
    public static User user;
    private static ObjectOutputStream oos;
    private static ObjectInputStream ois;
    public static void setAdmin()
    {
        System.out.println("Admin set");
        user = new User("Admin","admin","Admin@admin.com","BD","BUET","admin");
    }
    
    public static User getUser() {
        return user;
    }

    public static void setUser(User user) {
        LocalUser.user = user;
    }

    public static Socket getConnection() {
        return connection;
    }

    public static void setConnection(Socket connection) {
        System.out.println("Setting connection socket open : "+connection.isConnected());
        LocalUser.connection = connection;
        try {
            connection.setTcpNoDelay(true);
            oos= new ObjectOutputStream(connection.getOutputStream());
            ois= new ObjectInputStream(connection.getInputStream());
        } catch (IOException ex) {
            System.out.println("error khaise");
            Logger.getLogger(LocalUser.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println("Setting connection socket open : "+connection.isConnected());
        
    }

    public static ObjectOutputStream getOos() {
        return oos;
    }

    public static synchronized void setOos(ObjectOutputStream oos) {
        LocalUser.oos = oos;
    }

    public static ObjectInputStream getOis() {
        return ois;
    }

    public static void setOis(ObjectInputStream ois) {
        LocalUser.ois = ois;
    }
    public static void sendServer(Object o) {
        try {
            oos.writeObject(o);
            oos.flush();
        } catch (IOException e) {
            System.out.println("Writing  Error in network : " + e.toString());
        }
    }
    
    public  static Object read() {
        Object o = null;
        try {
            o = ois.readObject();
        } catch (Exception e) {
            System.out.println("Reading Error in network : " + e.toString());

        }
        return o;
    }
}
