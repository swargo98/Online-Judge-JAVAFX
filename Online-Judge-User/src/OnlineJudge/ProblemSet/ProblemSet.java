/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package OnlineJudge.ProblemSet;

import static OnlineJudge.ProblemSet.Problem.path;
import static OnlineJudge.ProblemSet.ProblemSet.Problems;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 *
 * @author MAHDI
 */
public class ProblemSet  {

    public static HashMap< String, Problem> Problems = new HashMap< String, Problem>();


    public static HashMap<String, Problem> getProblems() {
        return Problems;
    }

    public static void setProblems(HashMap<String, Problem> Problems) {
        ProblemSet.Problems = Problems;
    }

}
