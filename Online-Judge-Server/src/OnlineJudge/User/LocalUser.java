/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package OnlineJudge.User;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author MAHDI
 */
public class LocalUser {
    public static User user;
    public static void setAdmin()
    {
        try {
            System.out.println("Admin set");
            String address= InetAddress.getLocalHost().getHostAddress();
            
            user = new User(address,"admin","Admin@admin.com","BD","BUET","admin");
        } catch (UnknownHostException ex) {
            Logger.getLogger(LocalUser.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static User getUser() {
        return user;
    }

    public static void setUser(User user) {
        LocalUser.user = user;
    }
    
    
}
