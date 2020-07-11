/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package OnlineJudge.User;

import OnlineJudge.OnlineJudge;
import OnlineJudge.Submission.Submission;
import OnlineJudge.Submission.SubmissionSet;
import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;

/**
 * FXML Controller class
 *
 * @author MAHDI
 */
public class UserFXMLController implements Initializable {

    @FXML
    private Text UserName;
    @FXML
    private AnchorPane Node;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        OnlineJudge.Nodes=Node;
        UserName.setText(LocalUser.user.Name);
        // TODO
    }    

    @FXML
    private void HomeButtonClicked(ActionEvent event) throws IOException {
        try
        {
            Parent root = FXMLLoader.load(getClass().getResource("/OnlineJudge/User/HomeFXML.fxml"));
            Node.getChildren().removeAll(Node.getChildren());
            Node.getChildren().add(root);
        }
        catch(Exception e)
        {
            System.out.println(e.getMessage());
            e.printStackTrace();
            
        }
        
            
        
        
        
    }

    @FXML
    private void ContestButtonClicked(ActionEvent event) {
    }

    @FXML
    private void ProblemsetButtonClicked(ActionEvent event) {
        //System.out.println("Problemset button clicked");
        try
        {
            Parent root = FXMLLoader.load(getClass().getResource("/OnlineJudge/ProblemSet/ProblemSetFXML.fxml"));
            Node.getChildren().removeAll(Node.getChildren());
            Node.getChildren().add(root);
            //Scene scene = new Scene(root,720,600);
        
            //OnlineJudge.PrimaryStage.setScene(scene);
        }
        catch(Exception e)
        {
            System.out.println(e.getMessage());
            e.printStackTrace();
            
        }
    }

    @FXML
    private void SubmitSolutionButtonClicked(ActionEvent event) {
        //System.out.println("Submit button clicked");
        try
        {
            Parent root = FXMLLoader.load(getClass().getResource("/OnlineJudge/User/SubmitFXML.fxml"));
            Node.getChildren().removeAll(Node.getChildren());
            Node.getChildren().add(root);
        }
        catch(Exception e)
        {
            System.out.println(e.getMessage());
            e.printStackTrace();
            
        }
    }

    @FXML
    private void StatusButtonClicked(ActionEvent event) {
        //System.out.println("Status button clicked");
        try
        {
            Parent root = FXMLLoader.load(getClass().getResource("/OnlineJudge/User/StatusFXML.fxml"));
            Node.getChildren().removeAll(Node.getChildren());
            Node.getChildren().add(root);
        }
        catch(Exception e)
        {
            System.out.println(e.getMessage());
            e.printStackTrace();
            
        }
    }

    @FXML
    private void MySubmissionButtonClicked(ActionEvent event) {
         System.out.println("MySubmission button clicked");
        String Handle=LocalUser.user.getHandle();
        System.out.println(Handle+"UserFXML");
        try
        {
            Parent root = FXMLLoader.load(getClass().getResource("/OnlineJudge/User/MySubmissionFXML.fxml"));
            Node.getChildren().removeAll(Node.getChildren());
            Node.getChildren().add(root);
        }
        catch(Exception e)
        {
            System.out.println(e.getMessage());
            e.printStackTrace();
            
        }
    }

    @FXML
    private void MyProfileButtonClicked(ActionEvent event) {
        System.out.println("MyProfile button clicked");
        try
        {
            Parent root = FXMLLoader.load(getClass().getResource("/OnlineJudge/User/MyProfileFXML.fxml"));
            Node.getChildren().removeAll(Node.getChildren());
            Node.getChildren().add(root);
        }
        catch(Exception e)
        {
            System.out.println(e.getMessage());
            e.printStackTrace();
            
        }
    }

    @FXML
    private void LogOutButtonClicked(ActionEvent event) {
        
        //System.out.println("LogOut button clicked");
        try
        {
            Parent root = FXMLLoader.load(getClass().getResource("/OnlineJudge/OnlineJudgeFXML.fxml"));
            OnlineJudge.PrimaryStage.setScene(new Scene(root));
        }
        catch(Exception e)
        {
            System.out.println(e.getMessage());
            e.printStackTrace();
            
        }
    }
    
}
