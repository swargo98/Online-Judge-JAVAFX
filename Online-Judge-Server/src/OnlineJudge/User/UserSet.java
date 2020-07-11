/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package OnlineJudge.User;

import OnlineJudge.User.User;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;

/**
 *
 * @author Student06
 */
public class UserSet {
    public static HashMap < String , User > Users = new HashMap < String , User >();
    
    static final File path=new File("UserSet");
    static final String FileSeparator=System.getProperty("file.separator");
    
    public static void SaveUserSet() {
        try {
            System.out.println("save User");
            if (!path.exists()) {
                if (!path.mkdirs()) {
                    System.out.println("Userse dir not created");
                }
            }
            File Dest = new File(path.getAbsolutePath()+FileSeparator+"Users.dat");
            FileOutputStream fos = new FileOutputStream(Dest);
            ObjectOutputStream ous = new ObjectOutputStream(fos);
            ous.writeObject(Users);
            ous.close();
            fos.close();
        } catch (Exception e) {
            System.out.println(e.getCause());
        }
        
    }
    public static void LoadUserSet()
    {
        try {
            System.out.println("load User");
            if (!path.exists()) {
                if (!path.mkdirs()) {
                    System.out.println("Userse dir not created");
                }
            }
            File Dest = new File(path.getAbsolutePath()+FileSeparator+"Users.dat");
            if(!Dest.exists()) return ;
            FileInputStream fis = new FileInputStream(Dest);
            ObjectInputStream ois = new ObjectInputStream(fis);
            Users= (HashMap< String, User>)ois.readObject();
            ois.close();
            fis.close();
        } catch (Exception e) {
            System.out.println(e.getCause());
        }
    }
    
    
}
