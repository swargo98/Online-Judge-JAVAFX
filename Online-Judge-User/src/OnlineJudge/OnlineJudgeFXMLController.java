/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package OnlineJudge;

import OnlineJudge.User.LocalUser;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.regex.Pattern;
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
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 *
 * @author MAHDI
 */
public class OnlineJudgeFXMLController implements Initializable {

    @FXML
    private TextField ServerIP;
    @FXML
    private Text Message;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    public static boolean validateIP(String ipStr) {
        String regex = "\\b((25[0–5]|2[0–4]\\d|[01]?\\d\\d?)(\\.)){3}(25[0–5]|2[0–4]\\d|[01]?\\d\\d?)\\b";
        return Pattern.matches(regex, ipStr);
    }

    @FXML
    private void ConnectServer(ActionEvent event) {
        OnlineJudge.PrimaryStage.setTitle("CODEWAR Online Judge");
        String ServerIp = ServerIP.getText();
        if (validateIP(ServerIp) || ServerIp.equals("localhost")) {
            Message.setText("Connecting .. .. ..");
            try {
                Socket sc = new Socket(ServerIp, 11111);
                Socket sc2 = new Socket(ServerIp, 22222);
                new UpdateFromServer(sc2);
                LocalUser.setConnection(sc);
                sc.setTcpNoDelay(true);
                sc2.setTcpNoDelay(true);

                try {
                    Parent root = FXMLLoader.load(getClass().getResource("/OnlineJudge/User/LogInFXML.fxml"));

                    Scene scene = new Scene(root, 720, 600);

                    OnlineJudge.PrimaryStage.setScene(scene);
                } catch (Exception e) {
                    System.out.println("error khaise");
                    System.out.println(e.getMessage());
                    e.printStackTrace();

                }

            } catch (Exception e) {
                Message.setText("Connection failed");
            }

        } else {
            Message.setText("Enter valid ip!");
        }
    }

}
