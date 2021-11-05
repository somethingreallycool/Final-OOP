package com.group.data;

public class Employee {
    private String ID;  // ID is unique to every employee and can only be set with constructor
    private String Email;
    private String Name;
    private String Title;
    private String Birthdate;
    private String Compensation;
    private String Password;

    private Constant c = new Constant();
    public Employee(String ID,String Email,String Name,String Title,String Birthdate,String Compensation,String Password){
        this.ID = ID;
        this.Email = Email;
        this.Name = Name;
        this.Title = Title;
        this.Birthdate = Birthdate;
        this.Compensation = Compensation;
        this.Password = Password;
    }
    public Employee(String[] staffInfo){
        this.ID = staffInfo[c.ID];
        this.Email = staffInfo[c.EMAIL];
        this.Name = staffInfo[c.NAME];
        this.Title = staffInfo[c.TITLE];
        this.Birthdate = staffInfo[c.BIRTHDATE];
        this.Compensation = staffInfo[c.COMPENSATION];
        this.Password = staffInfo[c.PASSWORD];
    }

    //Return all user data, except Password
    public String[] getAll(){
        String[] staffInfo = {ID,Email,Name,Title,Birthdate,Compensation, Password};
        return staffInfo;
    }
    public String getID(){
        return ID;
    }
    public void setEmail(String newEmail){
        Email = newEmail;
    }
    public String getEmail(){
        return Email;
    }
    public void setName(String newName){
        Name = newName;
    }
    public String getName(){
        return Name;
    }
    public void setTitle(String newTitle){
        Title = newTitle;
    }
    public String getTitle(){
        return Title;
    }
    public void setBirthdate(String newBirthdate){
        Birthdate = newBirthdate;
    }
    public String getBirthdate(){
        return Birthdate;
    }
    public void setCompensation(String newCompensation){
        Compensation = newCompensation;
    }
    public String getCompensation(){
        return Compensation;
    }
    public void setPassword(String newPassword){
        Password = newPassword;
    }
    public boolean validPassword(String enteredPassword){
        if(Password.equals("NA")) {
            return false;
        }
        return Password.equals(enteredPassword);
    }
}
