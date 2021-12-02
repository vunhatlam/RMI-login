/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowAdapter;

import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
/**
 *
 * @author kami
 */
public class ClientView extends JFrame implements ActionListener{
    private JTextField txtUsername;
    private JPasswordField txtPassword;
    private JButton btnLogin;

    public ClientView() {
        super("RMI Login");
        
        txtUsername = new JTextField(15);
        txtPassword = new JPasswordField(15);
        txtPassword.setEchoChar('*');
        btnLogin = new JButton("LOGIN");
        
        JPanel content = new JPanel();
        content.setLayout(new FlowLayout());
        content.add(new JLabel("Username: "));
        content.add(txtUsername);
        content.add(new JLabel("Password: "));
        content.add(txtPassword);
        content.add(btnLogin);
        //btnLogin.addActionListener(this);
        this.setContentPane(content);
        this.pack();
        this.addWindowListener(new WindowAdapter(){
            @Override
            public void windowClosing(WindowEvent e){
                System.exit(0);
            }
        });
    }  
    
    @Override
    public void actionPerformed(ActionEvent e) {
    }
    
    public User getUser(){
        User model = new User(txtUsername.getText(), txtPassword.getText());
        return model;
    }
    
    public void showMessage(String s){
        JOptionPane.showMessageDialog(this, s);
    }
    
    public void addLoginListener(ActionListener log){
        btnLogin.addActionListener(log);
    }
}
