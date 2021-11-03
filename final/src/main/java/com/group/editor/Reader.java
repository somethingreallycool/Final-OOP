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

    //apply for ID and Email to check if sampleData is in the database, return true if no --> this function is used to work with writer to append new data
    //A user/employee must have unique ID and Email
    //ID is a must for every employee thus, it cannot be empty
    public boolean validData(String sampleData, int dataType){
        if (sampleData == "") {
            if (dataType == c.ID) {
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
