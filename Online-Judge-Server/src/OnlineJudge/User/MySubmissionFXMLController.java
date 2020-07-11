/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package OnlineJudge.User;

import OnlineJudge.Submission.Submission;
import OnlineJudge.Submission.SubmissionSet;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * FXML Controller class
 *
 * @author Rasman Swargo
 */
public class MySubmissionFXMLController implements Initializable {

    @FXML
    private TableColumn<Submission, String> SubmissionTime;
    @FXML
    private TableColumn<Submission, String> UserHandle;
    @FXML
    private TableColumn<Submission, String> ProblemName;
    @FXML
    private TableColumn<Submission, String> Language;
    @FXML
    private TableColumn<Submission, String> Verdict;
    @FXML
    private TableColumn<Submission, String> TimeTaken;
    @FXML
    private TableColumn<Submission, String> MemoryTaken;
    @FXML
    private TableView<Submission> MySubmissionTable;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
       
        SubmissionTime.setCellValueFactory(new PropertyValueFactory<Submission,String>("Time"));
        
        UserHandle.setCellValueFactory(new PropertyValueFactory<Submission,String>("Handle"));
        
        ProblemName.setCellValueFactory(new PropertyValueFactory<Submission,String>("ProblemName"));
        
        Language.setCellValueFactory(new PropertyValueFactory<Submission,String>("Language"));
        
        Verdict.setCellValueFactory(new PropertyValueFactory<Submission,String>("Verdict"));
        
        TimeTaken.setCellValueFactory(new PropertyValueFactory<Submission,String>("TimeTaken"));
        
        MemoryTaken.setCellValueFactory(new PropertyValueFactory<Submission,String>("MemoryTaken"));
        
        ObservableList<Submission > data = FXCollections.observableArrayList();
        //data.addAll(SubmissionSet.Submissions.values());
        String Handle=LocalUser.user.getHandle();
        System.out.println(Handle+"new");
        for(int i=0; i<=SubmissionSet.TotalSubmissions; i++)
        {
            //System.out.println(SubmissionSet.Submissions.get(i).getHandle());
            Submission n=SubmissionSet.Submissions.get(i);
            try {
                System.out.println(n.getHandle());
                String tmp=n.getHandle();
                if(tmp.equals(Handle))
                {
                    data.add(n);
                }
            } catch (Exception e) {
                System.out.println("Dhur Jhamela");
            }
            
            
        }
        MySubmissionTable.setItems(data);
    }    
    
}
