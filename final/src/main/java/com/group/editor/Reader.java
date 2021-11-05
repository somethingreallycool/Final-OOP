package com.group.editor;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import com.group.data.Constant;
import com.group.data.Employee;



public class Reader {
    private Constant c = new Constant();


    //Return all of the employees
    public ArrayList<Employee> print(){
        ArrayList<Employee> list = new ArrayList<Employee>();
        try {
            BufferedReader br = new BufferedReader(new FileReader(c.PATH));
            String line;

            while((line = br.readLine()) != null){
                String[] info = line.split(", ");
                list.add(new Employee(info));
            }
            br.close();
        } catch(FileNotFoundException e) {
            e.getStackTrace();
        } catch(IOException e){
            e.getStackTrace();
        }
        return list;
    }


    //Search for employee that match sample data
    //Return a Array List of employees that match the criteria
    public ArrayList<Employee> search(String sampleData, int dataType){
        ArrayList<Employee> matches = new ArrayList<Employee>();
        try {
            BufferedReader br = new BufferedReader(new FileReader(c.PATH));
            String line;
            while((line = br.readLine()) != null){
                String[] info = line.split(", ");

                //Temporary code, waiting for GUI implementation
                if (info[dataType].equals(sampleData)){
                    matches.add(new Employee(info));
                }
            }
            br.close();
        } catch(FileNotFoundException e) {
            e.getStackTrace();
        } catch(IOException e){
            e.getStackTrace();
        }
        return matches;
    }

    //Check if ID or Email is valid
    //ID is valid if it is in the database and not empty i.e "" --> ID is used to identify user, thus cannot be missing
    //Email is valid if it is in the database or it's empty ""
    public boolean validData(String sampleData, int dataType){
        if (sampleData.equals("")) {
            if (dataType == c.ID) {
                return true;
            } else {
                return false;
            }
        }
        try {
            BufferedReader br = new BufferedReader(new FileReader(c.PATH));
            String line;
            while((line = br.readLine()) != null){
                String[] info = line.split(", ");
                if (info[dataType].equals(sampleData)){
                    br.close();
                    return true;
                }
            }
            br.close();
            return false;
        } catch(FileNotFoundException e) {
            e.getStackTrace();
        } catch(IOException e){
            e.getStackTrace();
        }
        return false;
    }

}
