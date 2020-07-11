/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package OnlineJudge.ProblemSet;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author MAHDI
 */
public class ProblemSetFXMLController implements Initializable {

    @FXML
    private TableView<Problem> ProblemsTable;
    @FXML
    private TableColumn<Problem, String> ProblemId;
    @FXML
    private TableColumn<Problem, String> ProblemName;
    @FXML
    private TableColumn<Problem, Integer> TimeLimit;
    @FXML
    private TableColumn<Problem, Integer> MemoryLimit;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        System.out.println("Table View ini");
        //ProblemsTable.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        ObservableList<Problem> data = FXCollections.observableArrayList();
        //ProblemSet.LoadProblemSet();
        data.addAll(ProblemSet.Problems.values());
        
        ProblemId.setCellValueFactory(new PropertyValueFactory<Problem,String>("Id"));
        ProblemName.setCellValueFactory(new PropertyValueFactory<Problem,String>("Name"));
        TimeLimit.setCellValueFactory(new PropertyValueFactory<Problem, Integer>("TimeLimit"));
        MemoryLimit.setCellValueFactory(new PropertyValueFactory<Problem, Integer>("MemoryLimit"));
        ProblemsTable.setItems(data);
        
    }    
/*
    @FXML
    private void ShowProblem(KeyEvent event) {
        System.out.println("ShowProblem in keyboard ");
        }
*/

    @FXML
    private void ShowProblem(MouseEvent event) throws IOException {
        System.out.println("ShowProblem in mouse ");
        Problem SelectedProblem= ProblemsTable.getSelectionModel().getSelectedItem();
        System.out.println("Selected problm "+SelectedProblem);
        ProblemShowFXMLController.problem= SelectedProblem;
        
         String s = ProblemsTable.getSelectionModel().getSelectedItem().getId();
        ProblemShow ps = new ProblemShow(s);
        ps.ShowProblem();
    
    }

    @FXML
    private void ShowProblem(KeyEvent event) {
    }
    
}
