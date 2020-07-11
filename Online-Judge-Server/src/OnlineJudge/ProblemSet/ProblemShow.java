/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package OnlineJudge.ProblemSet;

/**
 *
 * @author Rasman Swargo
 */
import java.lang.*;
import java.awt.Desktop;
import java.io.File;
import java.io.IOException;

/**
 *
 * @author HP
 */
public class ProblemShow {
    String S;
    
    public ProblemShow(String S){
        this.S = S;
        System.out.println(S);
    }
    
    public void ShowProblem() throws IOException{
         File file = new File("/C:/Users/Rasman Swargo/Documents/NetBeansProjects/Online-Judge-Server-master (3)/Online-Judge-Server-master/ProblemSet/"+S+"/"+S+".pdf");
        
        //first check if Desktop is supported by Platform or not
        if(!Desktop.isDesktopSupported()){
            System.out.println("Desktop is not supported");
            return;
        }
        System.out.println("hi");
        System.out.println(file.getAbsolutePath());
        Desktop desktop = Desktop.getDesktop();
        if(file.exists()) desktop.open(file);
    }
}
