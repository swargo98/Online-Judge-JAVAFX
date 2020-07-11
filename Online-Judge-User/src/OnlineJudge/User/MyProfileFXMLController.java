/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package OnlineJudge.User;

import OnlineJudge.Submission.Submission;
import OnlineJudge.Submission.SubmissionSet;
import java.net.URL;
import java.util.HashMap;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

/**
 * FXML Controller class
 *
 * @author MAHDI
 */
public class MyProfileFXMLController implements Initializable {

    @FXML
    private Label NameLabel;
    @FXML
    private Label HandelLabel;
    @FXML
    private Label TotalTriedLabel;
    @FXML
    private Label TotalAcceptedLabel;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        String Name,Handle,Verdict;
        int TotalTried=0, TotalAC=0;
        Verdict="Accepted";
        Name=LocalUser.user.getName();
        Handle=LocalUser.user.getHandle();
        NameLabel.setText(Name);
        HandelLabel.setText(Handle);
         HashMap< Integer, Submission> Submissions = SubmissionSet.getSubmissions();
        System.out.println(Submissions.size());
        int k=Submissions.size();
        for(int i=0; i<=k; i++)
        {
            System.out.println(i);
            //System.out.println(SubmissionSet.Submissions.get(i).getHandle());
            Submission n=Submissions.get(i);
            try {
                System.out.println(n.getHandle());
                String tmp=n.getHandle();
                String sverdict=n.getVerdict();
                if(tmp.equals(Handle))
                {
                    TotalTried++;
                    System.out.println("Vitore Gelo");
                    if(sverdict.equals(Verdict)) TotalAC++;
                }
            } catch (Exception e) {
                System.out.println("Bal Amar");
            }
            
            
        }
        TotalTriedLabel.setText(Integer.toString(TotalTried));
        TotalAcceptedLabel.setText(Integer.toString(TotalAC));
    }    
    
}
