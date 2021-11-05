package com.group.window.window.frame;

import java.awt.Color;
import java.awt.event.*;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import com.group.data.Constant;
import com.group.data.Employee;
import com.group.editor.Reader;
import com.group.window.component.Template;
import com.group.window.window.panel.Left;

public class Login extends JFrame implements ActionListener{
    private Reader rd = new Reader();
    private Constant c = new Constant();

    private Left left = new Left();
    private JPanel right = new JPanel();

    private JLabel heading = new JLabel("SOME RANDOM CLINIC EMS");
    private JButton loginButton = new JButton("LOGIN");

    private JLabel usernameLabel = new JLabel("ENTER USERNAME");
    private JLabel passwordLabel = new JLabel("ENTER PASSWORD");

    private JTextField usernameField = new JTextField(30);
    private JPasswordField passwordField = new JPasswordField(30);

    private static JLabel alertLabel = new JLabel();

    public Login() {

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


        //Setting up user input field
        right.add(usernameLabel);
        Template.setFontSize(usernameLabel, 15);
        Template.setDefault(usernameLabel);
        Template.setYCoordinate(usernameLabel, heading.getLocation().y + heading.getHeight() + 75);

        right.add(usernameField);
        Template.setFontSize(usernameField, 15);
        Template.setDefault(usernameField);
        Template.setBorder(usernameField, 2);
        Template.setCenter(usernameField, right);
        Template.setYCoordinate(usernameField, usernameLabel.getLocation().y + usernameLabel.getHeight() + 10);
        Template.setXCoordinate(usernameLabel, usernameField.getLocation().x);
        usernameField.setCaretColor(Color.WHITE);

        right.add(passwordLabel);
        Template.setFontSize(passwordLabel, 15);
        Template.setDefault(passwordLabel);
        passwordLabel.setLocation(usernameLabel.getLocation().x, usernameField.getLocation().y + usernameField.getHeight() + 50);

        right.add(passwordField);
        Template.setFontSize(passwordField, 15);
        Template.setDefault(passwordField);
        Template.setBorder(passwordField, 2);
        Template.setCenter(passwordField, right);
        passwordField.setLocation(passwordLabel.getLocation().x, passwordLabel.getLocation().y + passwordLabel.getHeight() + 10);
        passwordField.setCaretColor(Color.WHITE);
        //Setting up user input field


        //Setting up alert when user enters invalid username/password
        right.add(alertLabel);
        Template.setFontSize(alertLabel, 15);


        //Setting up login button
        right.add(loginButton);
        Template.setFontSize(loginButton, 15);
        Template.setDefault(loginButton);
        Template.setBorder(loginButton, 2);
        Template.setCenter(loginButton, right);
        Template.setYCoordinate(loginButton, passwordField.getLocation().y + passwordField.getHeight() + 75);
        loginButton.setFocusable(false);

        loginButton.addActionListener(this);
    }


    //check for the type of username: 1 for ID, 2 for Email, 0 if it's an valid ID/Email i.e not in the database
    //validData method is originally meant to be used when appending or replacing data so it is used to check if a data is not in the database -> valid data
    private int validUsername(String username) {
        if (username.equals("")) {
            return 0;
        }
        if(rd.validData(username, c.ID)) {
            return 1;
        } else if(rd.validData(username, c.EMAIL)) {
            return 2;
        } else {
            return 0;
        }
    }

    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == loginButton) {
            String username = usernameField.getText();
            String password = String.valueOf(passwordField.getPassword());
            Employee employee;
            switch(validUsername(username)) {
                case 0:
                    break;
                case 1:
                    employee = rd.search(username, c.ID).get(0);
                    if(employee.validPassword(password)) {
                        this.dispose();
                        new Home(employee);
                    }
                    break;
                case 2:
                    employee = rd.search(username, c.EMAIL).get(0);
                    System.out.println(employee.getBirthdate());
                    if(employee.validPassword(password)) {
                        this.dispose();
                        new Home(employee);
                    }
                    break;
            }

            right.add(alertLabel);
            alertLabel.setText("Invalid Username or Password");
            Template.setDefault(alertLabel);
            Template.setCenter(alertLabel, right);
            Template.setYCoordinate(alertLabel, 350);

        }
    }
}
