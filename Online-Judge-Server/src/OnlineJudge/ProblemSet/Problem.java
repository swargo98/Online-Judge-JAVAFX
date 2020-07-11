/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package OnlineJudge.ProblemSet;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import static jdk.nashorn.internal.objects.NativeRegExp.source;

/**
 *
 * @author MAHDI
 */
public class Problem implements Serializable {

    private String Id;
    private String Name;
    private String Statement;// pdf type statement 
    private String Type;// "static" ,"dynamic","interactive"
    private String VerifierCode;// if dynamic then verifier cpp 
    private Integer TotalInputs;
    private int TotalAccepted;
    private int TotalAttempted;
    private Integer TimeLimit;/// always millisec
    private Integer MemoryLimit;

    public static final File path = new File("ProblemSet");
    static final String FileSeparator = System.getProperty("file.separator");
    
    public Problem(String Id, File Statement, String Type, ArrayList<File> Inputs, ArrayList<File> Outputs, String Name, Integer TimeLimit, Integer MemoryLimit) {
        try {
            System.out.println("Problem constructor");
            if (!path.exists()) {
                if (!path.mkdirs()) {
                    System.out.println("Problemse dir not created");
                }
            }
            File Path = new File(path.getAbsolutePath() + FileSeparator + Id);
            if (!Path.exists()) {
                if (!Path.mkdirs()) {
                    System.out.println("Problems folder dir not created");
                }
            }
            this.Id = Id;
            this.Statement = Statement.getName();
            CopyFile(new File(Path.getAbsolutePath() + FileSeparator + this.Statement), Statement);

            System.out.println("Statement copied");

            this.Type = Type;

            this.TotalInputs = Inputs.size();
            int n = 1;
            for (File f : Inputs) {
                CopyFile(new File(Path.getAbsolutePath() + FileSeparator + "Input" + n+".txt"), f);
                n++;
            }

            n = 1;
            for (File f : Outputs) {
                CopyFile(new File(Path.getAbsolutePath() + FileSeparator + "Output" + n+".txt"), f);
                n++;
            }

            this.Name = Name;
            this.TimeLimit = TimeLimit;
            this.MemoryLimit = MemoryLimit;
        } catch (Exception e) {
            System.out.println(e.getCause());
            e.printStackTrace();
        }
    }
    
    
    public Problem(String Id, File Statement, String Type,File VerifierCode, ArrayList<File> Inputs, String Name, Integer TimeLimit, Integer MemoryLimit) {
        try {
            System.out.println("Problem constructor");
            if (!path.exists()) {
                if (!path.mkdirs()) {
                    System.out.println("Problemse dir not created");
                }
            }
            File Path = new File(path.getAbsolutePath() + FileSeparator + Id);
            if (!Path.exists()) {
                if (!Path.mkdirs()) {
                    System.out.println("Problems folder dir not created");
                }
            }
            this.Id = Id;
            this.Statement = Statement.getName();
            CopyFile(new File(Path.getAbsolutePath() + FileSeparator + this.Statement), Statement);

            System.out.println("Statement copied");

            this.Type = Type;

            this.TotalInputs = Inputs.size();
            int n = 1;
            for (File f : Inputs) {
                CopyFile(new File(Path.getAbsolutePath() + FileSeparator + "Input" + n+".txt"), f);
            }

            this.VerifierCode=VerifierCode.getName();
            CopyFile(new File (Path.getAbsoluteFile()+FileSeparator+this.VerifierCode), VerifierCode);
            this.Name = Name;
            this.TimeLimit = TimeLimit;
            this.MemoryLimit = MemoryLimit;
        } catch (Exception e) {
            System.out.println(e.getCause());
            e.printStackTrace();
        }
    }

    
    static void CopyFile(File to, File from) {
        try {
            if (!to.exists()) {
                try {
                    to.createNewFile();
                } catch (IOException ex) {
                    Logger.getLogger(Problem.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            Files.copy(from.toPath(), to.toPath(), StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException ex) {
            Logger.getLogger(Problem.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    static String GetExtension(String s) {
        int id = s.lastIndexOf('.');
        String ex = "";
        for (int i = id; i < s.length(); i++) {
            ex += s.charAt(i);
        }
        return ex;
    }

    public String getId() {
        return Id;
    }

    public void setId(String Id) {
        this.Id = Id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String Name) {
        this.Name = Name;
    }

    public String getStatement() {
        return Statement;
    }

    public void setStatement(String Statement) {
        this.Statement = Statement;
    }

    public String getType() {
        return Type;
    }

    public void setType(String Type) {
        this.Type = Type;
    }

    public String getVerifierCode() {
        return VerifierCode;
    }

    public void setVerifierCode(String VerifierCode) {
        this.VerifierCode = VerifierCode;
    }

    public Integer getTotalInputs() {
        return TotalInputs;
    }

    public void setTotalInputs(Integer TotalInputs) {
        this.TotalInputs = TotalInputs;
    }

    public int getTotalAccepted() {
        return TotalAccepted;
    }

    public void setTotalAccepted(int TotalAccepted) {
        this.TotalAccepted = TotalAccepted;
    }

    public int getTotalAttempted() {
        return TotalAttempted;
    }

    public void setTotalAttempted(int TotalAttempted) {
        this.TotalAttempted = TotalAttempted;
    }

    public Integer getTimeLimit() {
        return TimeLimit;
    }

    public void setTimeLimit(Integer TimeLimit) {
        this.TimeLimit = TimeLimit;
    }

    public Integer getMemoryLimit() {
        return MemoryLimit;
    }

    public void setMemoryLimit(Integer MemoryLimit) {
        this.MemoryLimit = MemoryLimit;
    }


}
