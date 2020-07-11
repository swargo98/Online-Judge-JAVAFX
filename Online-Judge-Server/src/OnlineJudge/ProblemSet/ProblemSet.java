/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package OnlineJudge.ProblemSet;

import static OnlineJudge.ProblemSet.Problem.path;
import static OnlineJudge.ProblemSet.ProblemSet.Problems;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 *
 * @author MAHDI
 */
public class ProblemSet extends Application {

    public static HashMap< String, Problem> Problems = new HashMap< String, Problem>();

    @Override
    public void start(Stage primaryStage) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("ProblemSetFXML.fxml"));

        Scene scene = new Scene(root);

        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    static final File path=new File("ProblemSet");
    static final String FileSeparator=System.getProperty("file.separator");
    
    public static void SaveProblemSet() {
        try {
            System.out.println("save problem");
            if (!path.exists()) {
                if (!path.mkdirs()) {
                    System.out.println("Problemse dir not created");
                }
            }
            File Dest = new File(path.getAbsolutePath()+FileSeparator+"problems.dat");
            FileOutputStream fos = new FileOutputStream(Dest);
            ObjectOutputStream ous = new ObjectOutputStream(fos);
            ous.writeObject(Problems);
            ous.close();
            fos.close();
        } catch (Exception e) {
            System.out.println(e.getCause());
        }
        
    }
    public static void LoadProblemSet()
    {
        try {
            System.out.println("load problem");
            if (!path.exists()) {
                if (!path.mkdirs()) {
                    System.out.println("Problemse dir not created");
                }
            }
            File Dest = new File(path.getAbsolutePath()+FileSeparator+"problems.dat");
            if(!Dest.exists()) return ;
            FileInputStream fis = new FileInputStream(Dest);
            ObjectInputStream ois = new ObjectInputStream(fis);
            Problems= (HashMap< String, Problem>)ois.readObject();
            ois.close();
            fis.close();
        } catch (Exception e) {
            System.out.println(e.getCause());
        }
    }

}
