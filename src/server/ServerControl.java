/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import client.User;
import java.sql.SQLException;
/**
 *
 * @author kami
 */
public class ServerControl extends UnicastRemoteObject implements LoginInterface{
    private int serverPort = 7899;
    private Registry registry;
    private Connection con;
    private ServerView view;
    private String rmiService = "rmiLoginServer";

    public ServerControl(ServerView view) throws RemoteException{
        this.view = view;
        getDBConnection("users", "root", "06011999");
        view.showMessage("RMI server is running...");
        
        try{
            registry = LocateRegistry.createRegistry(serverPort);
            registry.rebind(rmiService, this);
        }catch(RemoteException e){
            throw e;
        }
    }
    
    @Override
    public String checkLogin(User user) throws RemoteException {
        String result = "";
        if(checkUser(user)){
            result = "OK";
        }
        else{
            result = "false";
        }
        return result;
    }
    
    private void getDBConnection(String dbName, String username, String password){
        String dbUrl = "jdbc:mysql://localhost:3306/" + dbName;
        try{
            con = DriverManager.getConnection(dbUrl, username, password);           
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
    private boolean checkUser(User user){
        String query = "SELECT * FROM users.users WHERE username = '" + user.getUsername() + "' AND password = '" + user.getPassword() + "'";
        try{
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(query);
            if(rs.next()){
                return true;
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
        return false;
    }
}
