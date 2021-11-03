package com.group.user.window.frame;

import java.awt.Color;
import java.awt.event.*;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.group.data.Constant;
import com.group.data.Employee;
import com.group.editor.Reader;
import com.group.user.component.Template;
import com.group.user.window.panel.Left;

public class Search extends JFrame implements ActionListener{
    private Employee user;
    private Reader rd = new Reader();
    private Constant c = new Constant();

    private Left left = new Left();
    private JPanel right = new JPanel();

    private JLabel heading = new JLabel("SOME RANDOM CLINIC EMS");

    private JButton searchButton = new JButton("SEARCH");
    private JButton returnButton = new JButton("RETURN");

    private JTextField searchField = new JTextField(30);

    private static JLabel alertLabel = new JLabel();

    public Search(Employee user) {
        this.user = user;

        //Setting up the frame
        this.setSize(750, 500);
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.setTitle("Some Random Clinic EMS");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
        this.setLayout(null);

        this.add(left);
        left.setLocation(0, 0);

        this.add(right);
        right.setBounds(250, 0, 500, 500);
        right.setBackground(Template.BLUE);
        right.setLayout(null);
        //Setting up the frame


        //Setting up heading
        right.add(heading);
        Template.setFontSize(heading, 25);
        Template.setDefault(heading);
        Template.setCenter(heading, right);
        Template.setYCoordinate(heading, 25);
        //Setting up heading


        //Setting up search field
        right.add(searchField);
        Template.setFontSize(searchField, 15);
        Template.setDefault(searchField);
        Template.setBorder(searchField, 2);
        Template.setCenter(searchField, right);
        Template.setYCoordinate(searchField, heading.getLocation().y + heading.getHeight() + 150);
        searchField.setCaretColor(Color.WHITE);
        //Setting up search field


        //Setting up alert when user enters invalid username/password
        right.add(alertLabel);
        Template.setFontSize(alertLabel, 15);


        //Setting up login button
        right.add(searchButton);
        Template.setFontSize(searchButton, 15);
        Template.setDefault(searchButton);
        Template.setBorder(searchButton, 2);
        Template.setCenter(searchButton, right);
        Template.setYCoordinate(searchButton, searchField.getLocation().y + searchField.getHeight() + 100);
        searchButton.setFocusable(false);

        searchButton.addActionListener(this);

        //Setting up return button
        right.add(returnButton);
        Template.setFontSize(returnButton, 15);
        Template.setDefault(returnButton);
        Template.setBorder(returnButton, 2);
        returnButton.setSize(returnButton.getWidth(), searchButton.getHeight());
        Template.setCenter(returnButton, right);
        Template.setYCoordinate(returnButton, searchButton.getLocation().y + searchButton.getHeight() + 15);
        returnButton.setFocusable(false);

        returnButton.addActionListener(this);
    }


    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == searchButton) {
            String data = searchField.getText();
            ArrayList<Employee> matchList = new ArrayList<Employee>();
            for (int dataType = 0; dataType < c.HEADERS.length - 1; dataType++) {
                matchList = rd.search(data, dataType);
                if (matchList.size() != 0){
                    for (Employee match: matchList) {
                        new Profile(match);
                    }
                    this.dispose();
                }
            }
            if (matchList.size() == 0) {
                right.add(alertLabel);
                alertLabel.setText("Cannot find any match !!!!");
                Template.setDefault(alertLabel);
                Template.setCenter(alertLabel, right);
                Template.setYCoordinate(alertLabel, searchField.getLocation().y + searchField.getHeight() + 15);
            }
        }

        if(e.getSource() == returnButton) {
            this.dispose();
            new Home(user);
        }
    }
}
