/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package OnlineJudge.ProblemSet;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author MAHDI
 */
public class EditProblemFXMLController implements Initializable {

    @FXML
    private AnchorPane EditProblemNode;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        System.out.println("edit problemset ini");
    }    

    @FXML
    private void AddProblemButtonClicked(ActionEvent event) {
        System.out.println("AddProblemButtonClicked button clicked");
        try
        {
            Parent root = FXMLLoader.load(getClass().getResource("/OnlineJudge/ProblemSet/AddProblemFXML.fxml"));
            EditProblemNode.getChildren().removeAll(EditProblemNode.getChildren());
            EditProblemNode.getChildren().add(root);
        }
        catch(Exception e)
        {
            System.out.println(e.getMessage());
            e.printStackTrace();
            
        }
        
    }

    @FXML
    private void DeleteProblemButtonClicked(ActionEvent event) {
    }
    
}
