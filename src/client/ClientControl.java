/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Arrays;

import server.LoginInterface;
/**
 *
 * @author kami
 */
public class ClientControl {
    private ClientView view;
    private String serverHost = "localhost";
    private int serverPort = 7899;
    private LoginInterface rmiServer;
    private Registry registry;
    private String rmiService = "rmiLoginServer";

    public ClientControl(ClientView view) {
        this.view = view;
        view.addLoginListener(new LoginListener());
        
        try{
            registry = LocateRegistry.getRegistry(serverHost, serverPort);
            rmiServer = (LoginInterface) (registry.lookup(rmiService));
        }catch(RemoteException e){
            view.showMessage(Arrays.toString(e.getStackTrace()));
            e.printStackTrace();
        }catch(NotBoundException e){
            view.showMessage(Arrays.toString(e.getStackTrace()));
            e.printStackTrace();
        }
    }

    private class LoginListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            try{
                User model = view.getUser();
                if(rmiServer.checkLogin(model).equals("OK")){
                    view.showMessage("Login sucessfully!");
                }
                else{
                    view.showMessage("Invalid username and/or password! Please try again.");
                }
            }catch(RemoteException ex){
                view.showMessage(ex.getStackTrace().toString());
                ex.printStackTrace();
            }
        }
    }     
}
