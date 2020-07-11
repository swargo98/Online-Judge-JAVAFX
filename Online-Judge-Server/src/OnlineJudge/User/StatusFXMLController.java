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
import javafx.util.Callback;

/**
 * FXML Controller class
 *
 * @author WNL
 */
public class StatusFXMLController implements Initializable {

    @FXML
    private TableColumn<Submission, Integer > SubmissionId;
    @FXML
    private TableColumn<Submission, String > SubmissionTime;
    @FXML
    private TableColumn<Submission, String > UserHandle;
    @FXML
    private TableColumn<Submission, String > ProblemName;
    @FXML
    private TableColumn<Submission, String > Language;
    @FXML
    private TableColumn<Submission, String > Verdict;
    @FXML
    private TableColumn<Submission, String > TimeTaken;
    @FXML
    private TableColumn<Submission, String > MemoryTaken;
    @FXML
    private TableView<Submission> StatusTable;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       // System.out.println("Table View ini");
        
        
        SubmissionId.setCellValueFactory(new PropertyValueFactory<Submission,Integer>("Id"));
        
        SubmissionTime.setCellValueFactory(new PropertyValueFactory<Submission,String>("Time"));
        
        UserHandle.setCellValueFactory(new PropertyValueFactory<Submission,String>("Handle"));
        
        ProblemName.setCellValueFactory(new PropertyValueFactory<Submission,String>("ProblemName"));
        
        Language.setCellValueFactory(new PropertyValueFactory<Submission,String>("Language"));
        
        Verdict.setCellValueFactory(new PropertyValueFactory<Submission,String>("Verdict"));
        
        TimeTaken.setCellValueFactory(new PropertyValueFactory<Submission,String>("TimeTaken"));
        
        MemoryTaken.setCellValueFactory(new PropertyValueFactory<Submission,String>("MemoryTaken"));
        
        
/*
        for(Submission s:SubmissionSet.Submissions.values())
        {
            System.out.println(s);
        }
*/     
        ObservableList<Submission > data = FXCollections.observableArrayList();
        
        data.addAll(SubmissionSet.Submissions.values());
        
        //System.out.println(SubmissionSet.Submissions.values());
        
        StatusTable.setItems(data);
        //StatusTable.getSortOrder().add(SubmissionId);
        
    }    
    
}
