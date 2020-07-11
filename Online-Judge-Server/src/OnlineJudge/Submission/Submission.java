/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package OnlineJudge.Submission;

import OnlineJudge.ProblemSet.ProblemSet;
import Server.ProcessExecutor;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 *
 * @author MAHDI
 */
public class Submission implements Serializable {
    public String ProbmemId;
    public String Handle;
    public String Language;
    public String Code;
    public String Verdict;
    public String Time;
    public String TimeTaken;
    public String MemoryTaken;
    public Integer Id;
    public String ProblemName;
    public String Comment;
    transient Thread myThread;
    public Submission(String ProbmemId, String Handle, String Lang, String Code,Integer Id) {
        this.ProbmemId = ProbmemId;
        
        this.ProblemName=ProblemSet.Problems.get(ProbmemId).getName();
        this.Handle = Handle;
        this.Language = Lang;
        this.Code = Code;
        Time=LocalDateTime.now().toString();
        Verdict= "Not Judged Yet";
        TimeTaken="";
        MemoryTaken="";
        this.Id=Id;
        SubmissionSet.TotalSubmissions++;
    }

    public void setProbmemId(String ProbmemId) {
        this.ProbmemId = ProbmemId;
    }

    public void setHandle(String Handle) {
        this.Handle = Handle;
    }

    public void setLanguage(String Language) {
        this.Language = Language;
    }

    public void setCode(String Code) {
        this.Code = Code;
    }

    public void setVerdict(String Verdict) {
        this.Verdict = Verdict;
    }

    public void setTime(String Time) {
        this.Time = Time;
    }

    public void setTimeTaken(String TimeTaken) {
        this.TimeTaken = TimeTaken;
    }

    public void setMemoryTaken(String MemoryTaken) {
        this.MemoryTaken = MemoryTaken;
    }

    public void setId(Integer Id) {
        this.Id = Id;
    }

    public void setProblemName(String ProblemName) {
        this.ProblemName = ProblemName;
    }

    public String getProbmemId() {
        return ProbmemId;
    }

    public String getHandle() {
        return Handle;
    }

    public String getLanguage() {
        return Language;
    }

    public String getCode() {
        return Code;
    }

    public String getVerdict() {
        return Verdict;
    }

    public String getTime() {
        return Time;
    }

    public String getTimeTaken() {
        return TimeTaken;
    }

    public String getMemoryTaken() {
        return MemoryTaken;
    }

    public Integer getId() {
        return Id;
    }

    public String getProblemName() {
        return ProblemName;
    }

    public String getComment() {
        return Comment;
    }

    public void setComment(String Comment) {
        this.Comment = Comment;
    }

    @Override
    public String toString() {
        return "Submission{" + "ProbmemId=" + ProbmemId + ", Handle=" + Handle + ", Language=" + Language + ", Code=" + Code + ", Verdict=" + Verdict + ", Time=" + Time + ", TimeTaken=" + TimeTaken + ", MemoryTaken=" + MemoryTaken + ", Id=" + Id + ", ProblemName=" + ProblemName + ", Comment=" + Comment + '}';
    }

    
}
