/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package OnlineJudge.ProblemSet;

import OnlineJudge.OnlineJudge;
import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;

/**
 * FXML Controller class
 *
 * @author MAHDI
 */
public class AddProblemFXMLController implements Initializable {

    public String pId;
    public File pStatement;// pdf type statement 
    public String pType;// "static" ,"dynamic","interactive"
    public File pVerifierCode;// if dynamic then verifier cpp 
    public ArrayList< File> pInputs;
    public ArrayList< File> pOutputs;
    public int pTotalAccepted;
    public int pTotalAttempted;
    public String pName;
    public Integer pTimeLimit;/// always millisec
    public Integer pMemoryLimit;

    @FXML
    private TextField Name;
    @FXML
    private TextField Id;
    @FXML
    private TextField TimeLimit;
    @FXML
    private TextField MemoryLimit;
    @FXML
    private Label ErrorMessege;
    @FXML
    private MenuButton Type;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        pInputs = new ArrayList< File>();
        pOutputs = new ArrayList< File>();

        // TODO
    }

    @FXML
    private void ChooseProblemButtonClicked(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Upload Problem Statement File");
        fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Txt File", "*.txt"), new FileChooser.ExtensionFilter("pdf File", "*.pdf"));
        File SourceFile = fileChooser.showOpenDialog(OnlineJudge.PrimaryStage);
        if (SourceFile != null) {
            pStatement = SourceFile;
        } else {
            System.out.println("File not choosen");
            ErrorMessege.setText("No File Choosen");
        }
    }

    @FXML
    private void StaticSelected(ActionEvent event) {
        Type.setText("Static");
        pType = "Static";

    }

    @FXML
    private void DynamicSelected(ActionEvent event) {
        Type.setText("Dynamic");
        pType = "Dynamic";
        
    }

    @FXML
    private void SelectVarifierCode(ActionEvent event) {
        if (pType == "") {
            ErrorMessege.setText("Select type first");
        } else if (pType == "Static") {
            ErrorMessege.setText("No need varifier");
        } else {
            FileChooser fileChooser = new FileChooser();

            fileChooser.setTitle("Upload Problem Statement File");
            fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("C++ File", "*.cpp"), new FileChooser.ExtensionFilter("java File", "*.java"));
            File SourceFile = fileChooser.showOpenDialog(OnlineJudge.PrimaryStage);
            if (SourceFile != null) {
                pVerifierCode = SourceFile;
            } else {
                System.out.println("File not choosen");
                ErrorMessege.setText("No File Choosen");
            }
        }

    }
    
    @FXML
    private void SubmitButtonClicked(ActionEvent event) {
        pName=Name.getText();
        pId=Id.getText();
        pMemoryLimit=Integer.parseInt(MemoryLimit.getText());
        pTimeLimit=Integer.parseInt(TimeLimit.getText());
        if(pName.equals(""))
        {
            ErrorMessege.setText("Select name first");
            return ;
        }
        if(pMemoryLimit==0)
        {
            ErrorMessege.setText("Select MemoryLimit first");
            return ;
        }
        if(pTimeLimit==0)
        {
            ErrorMessege.setText("Select TimeLimit first");
            return ;
        }
        ErrorMessege.setText("Problem added");
        /// save problem 
        Problem p;
        if(pType.equals("Static"))
        p=new Problem(pId,pStatement,pType,pInputs,pOutputs,pName,pTimeLimit,pMemoryLimit);
        else 
          p=  new Problem(pId,pStatement,pType,pVerifierCode,pInputs,pName,pTimeLimit,pMemoryLimit);
        ProblemSet.Problems.put(pId, p);
        ProblemSet.SaveProblemSet();
    }

    @FXML
    private void AddInput(ActionEvent event) {
        if (pType == "") {
            ErrorMessege.setText("Select type first");
        } else if (pType == "Static") {

            FileChooser fileChooser = new FileChooser();

            fileChooser.setTitle("Upload Problem Input File");
            fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("text File", "*.txt"));
            File SourceFile = fileChooser.showOpenDialog(OnlineJudge.PrimaryStage);
            if (SourceFile != null) {
                FileChooser fileChooser1 = new FileChooser();

                fileChooser1.setTitle("Upload Problem OutPut File");
                fileChooser1.getExtensionFilters().add(new FileChooser.ExtensionFilter("text File", "*.txt"));
                File SourceFile1 = fileChooser1.showOpenDialog(OnlineJudge.PrimaryStage);
                if (SourceFile1 != null) {
                    pInputs.add(SourceFile);
                    pOutputs.add(SourceFile1);
                    return;
                }
            }
            System.out.println("File not choosen");
            ErrorMessege.setText("No File Choosen");
        } else {
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Upload Problem Input File");
            fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("text File", "*.txt"));
            File SourceFile = fileChooser.showOpenDialog(OnlineJudge.PrimaryStage);
            if (SourceFile != null) {
                pInputs.add(  SourceFile);
            } else {
                System.out.println("File not choosen");
                ErrorMessege.setText("No File Choosen");
            }
        }

    }

}
