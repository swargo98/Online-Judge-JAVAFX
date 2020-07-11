/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Server;

import FileUtil.Folder;
import OnlineJudge.ProblemSet.ProblemSet;
import OnlineJudge.Submission.Submission;
import OnlineJudge.Submission.SubmissionSet;
import OnlineJudge.User.User;
import OnlineJudge.User.SubmissionHistory;
import OnlineJudge.User.UserSet;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Student06
 */
public class Server extends Thread {

    private int port;

    public Server(int port) {
        this.port = port;
        start();
    }

    @Override
    public void run() {
        try {
            ServerSocket welcomeSocket = new ServerSocket(port);
            System.out.println("Server started port: " + port);
            while (true) {
                Socket connectionSocket = welcomeSocket.accept();
                if (port == 11111) {
                    new InputFromClient(connectionSocket);
                } else {
                    new UpdateClient(connectionSocket);
                }
                System.out.println("cleint connected int port: " + port);
            }
        } catch (Exception ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}

class UpdateClient extends Thread {

    private Socket connectionSocket;

    public UpdateClient(Socket s) {
        this.connectionSocket = s;
        start();
    }

    @Override
    public void run() {
        System.out.println("Update client running port: " + connectionSocket.getLocalPort());
        try {
            
            ObjectOutputStream oos = new ObjectOutputStream(connectionSocket.getOutputStream());
            ObjectInputStream ois =  new ObjectInputStream(connectionSocket.getInputStream());
            while (true) {
                oos.writeObject(true);
                oos.flush();
                oos.writeObject(ProblemSet.Problems);
                oos.flush();
                /// now problemset er folder 
                Folder ps= new Folder(new File("ProblemSet"));
                oos.writeObject(ps);
                oos.flush();
                
                oos.writeObject(false);
                oos.flush();
                
                
                oos.writeObject(SubmissionSet.Submissions);
                oos.flush();
                
                
                /// now SubmissionSet er folder 
                Folder ss= new Folder(new File("SubmissionSet"));
                oos.writeObject(ss);
                oos.flush();
                
                ois.readObject();
                oos.reset();
            }
        } catch (Exception e) {
            System.out.println(e.getCause());
            e.printStackTrace();
        } finally {
            System.out.println("Update client exit");
        }
    }
}

class InputFromClient extends Thread {

    private Socket connectionSocket;
    private ObjectOutputStream oos;
    public InputFromClient(Socket s) {
        try {
            this.connectionSocket = s;
            s.setTcpNoDelay(true);
            start();
        } catch (SocketException ex) {
            Logger.getLogger(InputFromClient.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    void sendObject(Object o) throws IOException
    {
        oos.writeObject(o); 
        oos.flush();
    }
    @Override
    public void run() {
        System.out.println("InputFromClient started port:" + connectionSocket.getLocalPort());
        
        try {
            oos = new ObjectOutputStream(connectionSocket.getOutputStream());
            ObjectInputStream ois = new ObjectInputStream(connectionSocket.getInputStream());
            while (true) {
                
                Object req = ois.readObject();
                if (req == null) {
                    System.out.println("null");
                    continue;
                }
                if (req instanceof User) {
                    System.out.println("I'm Here");  
                String email = ((User) req).getEmail();
                String name = ((User) req).getName();
                String handle = ((User) req).getHandle();
                String password = ((User) req).getPassword();
                Object b = SignUp(email, name, handle, password);
                sendObject(b);
                try {
                    File file = new File(handle+".txt");
                    SubmissionHistory submissionHistory = new SubmissionHistory(file);
                    if(submissionHistory==null) System.out.println("null");
                    else System.out.println("null na");
                    sendObject(submissionHistory);
                } catch (Exception e){
                    e.printStackTrace();
                }
                    System.out.println("registar req");
                    User usr = (User) req;
                    System.out.println(usr);
                    UserSet.LoadUserSet();
                    if (UserSet.Users.containsKey(usr.getHandle())) {
                        System.out.println("User already registered with same handle");
                    } else {
                        UserSet.Users.put(usr.getHandle(), usr);
                        UserSet.SaveUserSet();
                   }
                }
                
                else if (req instanceof LoginRequest) {
                    System.out.println("login req paise");
                    LoginRequest lir = (LoginRequest) req;
                    
                    System.out.println(lir);
                    UserSet.LoadUserSet();

                    if (UserSet.Users.containsKey(lir.getUserName())) {
                        if (lir.getPassword().equals(UserSet.Users.get(lir.getUserName()).getPassword())) {
                            User usr = UserSet.Users.get(lir.getUserName());
                            sendObject(true);
                            sendObject(usr);
                            
                            System.out.println("log in ok");
                        } else {
                            System.out.println("log in false");
                            sendObject(false);
                            
                        }
                    } else {
                        System.out.println("log in false");
                        sendObject(false);
                        
                    }

                }
                else if(req instanceof Submission )
                {
                    Submission sm= (Submission) req;
                    //String handle = sm.getHandle();
                //String lang = sm.getLanguage();
                //String verdict = null;
                    sm.setId(SubmissionSet.TotalSubmissions++);
                    SubmissionSet.Submissions.put(sm.getId(), sm);
                    SubmissionSet.SaveSubmissionSet();
                    new ProcessExecutor(sm);
                    //Thread.sleep(3000);
                    //verdict=sm.getVerdict();
                    //System.out.println(verdict);
                    //SubmissionHistory submissionHistory = update(handle,sm,verdict);
                    //oos.writeObject(submissionHistory);
                    //oos.writeObject(sm);
                    
                }
                System.out.println("checking ");
                
            }
        } catch (Exception e) {
            e.printStackTrace();
            
        }
        finally
        {
            System.out.println("Input client exit ");
        }

    }
    
    private static String SignIn(String loginNameText, String pass) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new FileReader("userDetails.txt"));
        try{
            while (true){
                String data = bufferedReader.readLine();
                if (data == null)
                    break;

                String[] s = data.split("\t\t");
                if((loginNameText.equals(s[1]) || loginNameText.equals(s[2])) && pass.equals(s[3])){
                    return data;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        bufferedReader.close();

        return null;
    }

    private static Object SignUp(String email, String name, String handle, String password){
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader("userDetails.txt"));
            BufferedWriter bufferedWriter= new BufferedWriter(new FileWriter("tmp.txt"));
            while (true){
                String data = bufferedReader.readLine();
                if (data == null || data.length()<4)
                    break;

                String s[] = data.split("\t\t");
                if(s[1].equals(handle) || s[2].equals(email)){
                    bufferedReader.close();
                    bufferedWriter.close();
                    return null;
                }
                bufferedWriter.write(data+"\n");
            }
            bufferedWriter.write(name+"\t\t"+handle+"\t\t"+email+"\t\t"+password+"\n");
            bufferedReader.close();
            bufferedWriter.close();

            BufferedReader bufferedReader1 = new BufferedReader(new FileReader("tmp.txt"));
            BufferedWriter bufferedWriter1= new BufferedWriter(new FileWriter("userDetails.txt"));
            while (true){
                String data = bufferedReader1.readLine();
                if(data == null)
                    break;
                bufferedWriter1.write(data+"\n");
            }
            bufferedReader1.close();
            bufferedWriter1.close();

        } catch (Exception e){
            e.printStackTrace();
        }

        try {
            handle = handle + ".txt";
            BufferedWriter bw = new BufferedWriter(new FileWriter(handle));
            bw.write("");
            bw.close();
        } catch (Exception e){
            e.printStackTrace();
        }

        return "Ok";
    }

    private SubmissionHistory update(String handle, Submission temp, String verdict){
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(handle+".txt"));
            BufferedWriter bufferedWriter= new BufferedWriter(new FileWriter("tmp.txt"));
            while (true){
                String data = bufferedReader.readLine();
                if (data == null || data.length()<4)
                    break;
                bufferedWriter.write(data+"\n");
            }
            bufferedWriter.write(temp.getLanguage()+"\t\t"+temp.getProblemName()+"\t\t"+verdict+"\n");
            bufferedReader.close();
            bufferedWriter.close();

            BufferedReader bufferedReader1 = new BufferedReader(new FileReader("tmp.txt"));
            BufferedWriter bufferedWriter1= new BufferedWriter(new FileWriter(handle+".txt"));
            while (true){
                String data = bufferedReader1.readLine();
                if(data == null)
                    break;
                bufferedWriter1.write(data+"\n");
            }
            bufferedReader1.close();
            bufferedWriter1.close();

        } catch (Exception e){
            e.printStackTrace();
        }

        SubmissionHistory submissionHistory = new SubmissionHistory(new File(handle+".txt"));
        return submissionHistory;
    }

    private void AddFile(String s1, String s2){
        try {
            BufferedWriter bw1 = new BufferedWriter(new FileWriter("tmp.txt"));
            BufferedWriter bw2 = new BufferedWriter(new FileWriter("tmp1.txt"));
            BufferedReader br1 = new BufferedReader(new FileReader("title.txt"));
            BufferedReader br2 = new BufferedReader(new FileReader("dis.txt"));

            while (true) {
                String x1 = br1.readLine();
                String x2 = br2.readLine();

                if (x1 == null)
                    break;

                bw1.write(x1+"\n");
                bw2.write(x2+"\n");
            }
            bw1.write(s1+"\n");
            bw2.write(s2+"\n");

            br1.close();
            br2.close();
            bw1.close();
            bw2.close();

            BufferedWriter ebw1 = new BufferedWriter(new FileWriter("title.txt"));
            BufferedWriter ebw2 = new BufferedWriter(new FileWriter("dis.txt"));
            BufferedReader ebr1 = new BufferedReader(new FileReader("tmp.txt"));
            BufferedReader ebr2 = new BufferedReader(new FileReader("tmp1.txt"));

            while (true) {
                String x1 = ebr1.readLine();
                String x2 = ebr2.readLine();

                if (x1 == null)
                    break;

                ebw1.write(x1+"\n");
                ebw2.write(x2+"\n");
            }
            ebr1.close();
            ebr2.close();
            ebw1.close();
            ebw2.close();
        } catch (Exception e){
            e.printStackTrace();
        }
    }
}


