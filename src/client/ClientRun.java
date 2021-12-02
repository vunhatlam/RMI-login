/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client;

/**
 *
 * @author kami
 */
public class ClientRun {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        ClientView view = new ClientView();
        ClientControl control = new ClientControl(view);
        view.setLocationRelativeTo(null);
        view.setVisible(true);
    }
    
}
