package com.group.editor;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import com.group.data.Constant;
import com.group.data.Employee; 

public class Writer {
    Constant c = new Constant();
    Reader rd = new Reader();


    //Append new employee, return if employee is successfully appended
    //New employee must have a ID or Email that does not match any other existing ones
    //Else, return false
    public boolean append(Employee employee){
        String[] info = employee.getAll();
        System.out.println(rd.validData(info[c.ID], c.ID));
        System.out.println(rd.validData(info[c.EMAIL], c.EMAIL));
        if(!rd.validData(info[c.ID], c.ID) & !rd.validData(info[c.EMAIL], c.EMAIL)){
            
            try {
                BufferedWriter bw = new BufferedWriter(new FileWriter(c.PATH, true));
                bw.write("\n");
                int dataType = 0;
                while(dataType < info.length-1){
                    bw.write(info[dataType] + ", ");
                    dataType += 1;
                }
                bw.write(info[dataType]);
                bw.close();
            } catch(IOException e){
                e.getStackTrace();
            }
            return true;
        } else {
            System.out.println("This ID or Email has already been registered under a different account");
            return false;
        }
    }


    //Replace/Update data of an employee
    //First, the code check for ID of the employee
    //Then, it check if the email is a valid one (either email is unedited or email is edited to a new email to the database)
    public boolean replace(Employee employee){
        String[] newInfo = employee.getAll();
        File DB = new File(c.PATH);
        File newDB = new File("src/main/java/com/group/database"+"/temporary.csv");
        try {
            newDB.createNewFile();
            try {
                BufferedWriter bw = new BufferedWriter(new FileWriter(newDB, true));
                BufferedReader br = new BufferedReader(new FileReader(DB));
                String line;
                bw.write(br.readLine());

                Boolean isReplacable = false;
                while((line = br.readLine()) != null){
            
                    String[] info = line.split(", ");
                    if(info[c.ID].equals(newInfo[c.ID]) & (info[c.EMAIL].equals(newInfo[c.EMAIL]) | !rd.validData(employee.getEmail(), c.EMAIL))){
                        bw.write("\n");
                        int dataType = 0;
                        while(dataType < newInfo.length-1){
                            bw.write(newInfo[dataType] + ", ");
                            dataType += 1;
                        }
                        bw.write(newInfo[dataType]); 
                        isReplacable = true;
                    } else {
                        bw.write("\n"+line);
                    }
                }
                bw.close();
                br.close();
                DB.delete();
                newDB.renameTo(DB);
                return isReplacable;
            } catch(IOException e){
                e.getStackTrace();
            } 
        } catch(IOException e){
            e.getStackTrace();
        }
        return false;
    }

    //delete employee from the database
    //use ID to check for the employee
    public boolean delete(Employee employee) {
        String ID = employee.getID();
        File DB = new File(c.PATH);
        File newDB = new File("src/main/java/com/group/database"+"/temporary.csv");
        try {
            newDB.createNewFile();
            try {
                BufferedWriter bw = new BufferedWriter(new FileWriter(newDB, true));
                BufferedReader br = new BufferedReader(new FileReader(DB));
                String line;
                bw.write(br.readLine());
                while((line = br.readLine()) != null){
                    String[] info = line.split(", ");
                    if(!info[c.ID].equals(ID)){
                        bw.write("\n"+line);
                    }
                }
                bw.close();
                br.close();
                DB.delete();
                newDB.renameTo(DB);
                return true;
            } catch(IOException e){
                e.getStackTrace();
            } 
        } catch(IOException e){
            e.getStackTrace();
        }
        return false;
    }

    
}
