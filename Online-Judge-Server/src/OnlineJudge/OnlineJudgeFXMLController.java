/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package OnlineJudge;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.stage.Stage;

/**
 *
 * @author MAHDI
 */
public class OnlineJudgeFXMLController implements Initializable {

    @FXML
    private MenuButton Option;
    @FXML
    private MenuItem OptionUser;
    @FXML
    private MenuItem OptionServer;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void OptionUserSelected(ActionEvent event) throws IOException {
        System.out.println("User selected");
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/OnlineJudge/User/LogInFXML.fxml"));

            Scene scene = new Scene(root, 720, 600);

            OnlineJudge.PrimaryStage.setScene(scene);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            
        }
    }

    @FXML
    private void OptionServerSelected(ActionEvent event) {
        System.out.println("Server selected");
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/OnlineJudge/Admin/AdminFXML.fxml"));
            System.out.println("FXML loaded");
            //Scene scene = new Scene(root);
            //scene.getStylesheets().addAll(this.getClass().getResource("/OnlineJudge/Admin/AdminImage.css").toExternalForm());
            OnlineJudge.PrimaryStage.setScene(new Scene(root));
        } catch (Exception e) {
            System.out.println(e.getMessage());
            
        }
    }

}
