/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package OnlineJudge;

import FileUtil.Folder;
import OnlineJudge.ProblemSet.Problem;
import OnlineJudge.ProblemSet.ProblemSet;
import OnlineJudge.Submission.Submission;
import OnlineJudge.Submission.SubmissionSet;
import java.net.*;
import java.io.*;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author MAHDI
 */
public class Network {

    public static synchronized void sendObject(Socket s, Object obj) {
        ObjectOutputStream oos = null;
        try {
            oos = new ObjectOutputStream(s.getOutputStream());
            oos.writeObject(obj);
            oos.flush();
        } catch (IOException ex) {
            Logger.getLogger(Network.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static synchronized void sendObject(ObjectOutputStream oos, Object obj) {
        try {
            oos.writeObject(obj);
            oos.flush();
        } catch (IOException ex) {
            Logger.getLogger(Network.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}

class UpdateFromServer extends Thread {
    private static final File root= new File(new File("1").getAbsoluteFile().getParent());
    private Socket connection;

    public UpdateFromServer(Socket connection) {
        this.connection = connection;
        start();
    }

    @Override
    public void run() {
        System.out.println("UpdateFromServer started port:" + connection.getLocalPort());
        try {
            ObjectInputStream ois = new ObjectInputStream(connection.getInputStream());
            ObjectOutputStream oos= new ObjectOutputStream(connection.getOutputStream());
            
            while (true) {
                boolean we = (boolean) ois.readObject();
                if (we) {
                    Object obj = ois.readObject();
                    if (obj == null) {
                        System.out.println("obj is null");
                        continue;
                    }
                    ProblemSet.setProblems((HashMap<String, Problem>) obj);
                    /// prob folder 
                    
                    Folder ps =(Folder) ois.readObject();
                    ps.write(root);
                    
                    
                    
                    
                    
                } else {
                    Object obj = ois.readObject();
                    if (obj == null) {
                        System.out.println("obj is null");
                        continue;
                    }
                    
                    SubmissionSet.setSubmissions((HashMap<Integer, Submission>) obj);
                    
                    Folder ss =(Folder) ois.readObject();
                    ss.write(root);
                    
                    
                    //System.out.println("submission updated");
                    
                    oos.writeObject(true);
                    oos.flush();
                }
                
            }

        } catch (Exception e) {
            System.out.println(e.getCause());
            e.printStackTrace();
        } finally {
            System.out.println("UpdateFromServer stoped");
        }
    }

}
