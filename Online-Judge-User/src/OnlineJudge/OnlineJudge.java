/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package OnlineJudge;

import OnlineJudge.ProblemSet.ProblemSet;
import OnlineJudge.Submission.SubmissionSet;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 *
 * @author MAHDI
 */
public class OnlineJudge  extends Application  {
    public static Stage PrimaryStage;
    public static Parent PrimaryRoot;
    public static AnchorPane Nodes ;
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/OnlineJudge/OnlineJudgeFXML.fxml"));
        
        Scene scene = new Scene(root,720,600);
        stage.getIcons().add(new Image("file:icon.png"));
        stage.setScene(scene);
        stage.show();
        PrimaryStage=stage;
        PrimaryRoot=root;
        
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                
                
           }
        }).start();
        
        launch(args);
        
    }
    
}
