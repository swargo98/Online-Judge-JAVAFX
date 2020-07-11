/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package OnlineJudge.User;

/**
 *
 * @author Rasman Swargo
 */
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.Serializable;


public class SubmissionHistory implements Serializable {
    String all;

    public SubmissionHistory(File file) {
        all = "";
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
            while (true){
                String s = bufferedReader.readLine();
                if (s == null)
                    break;
                all = all + s + "\n";
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String getAll() {
        return all;
    }

    public void setAll(File file) {
        all = "";
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
            while (true){
                String s = bufferedReader.readLine();
                if (s == null)
                    break;
                all = all + s;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
