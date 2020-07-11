/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package OnlineJudge.User;

import java.io.Serializable;

/**
 *
 * @author MAHDI
 */
public class User implements Serializable{
    String Name;
    String Handle;
    String Email;
    String Country;
    String University;
    String Password;
    transient Thread myThread;
    private static final long serialVersionUID = -7578030344100503357L;
    public User(String Name, String Handle, String Email, String Country, String University, String Password) {
        if(Country.equals("")) Country="Bangladesh";
        if(University.equals("")) University="BUET";
        this.Name = Name;
        this.Handle = Handle;
        this.Email = Email;
        this.Country = Country;
        this.University = University;
        this.Password = Password;
    }
    public User(String Name)
    {
        this.Name = Name;
    }
    
    // solaimon 123
    // mahdi 

    public String getName() {
        return Name;
    }

    public void setName(String Name) {
        this.Name = Name;
    }

    public String getHandle() {
        return Handle;
    }

    public void setHandle(String Handle) {
        this.Handle = Handle;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String Email) {
        this.Email = Email;
    }

    public String getCountry() {
        return Country;
    }

    public void setCountry(String Country) {
        this.Country = Country;
    }

    public String getUniversity() {
        return University;
    }

    public void setUniversity(String University) {
        this.University = University;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String Password) {
        this.Password = Password;
    }

    @Override
    public String toString() {
        return "User{" + "Name=" + Name + ", Handle=" + Handle + ", Email=" + Email + ", Country=" + Country + ", University=" + University + ", Password=" + Password + ", myThread=" + myThread + '}';
    }
    
}
