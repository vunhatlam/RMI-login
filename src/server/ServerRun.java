/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server;

/**
 *
 * @author kami
 */
public class ServerRun {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        ServerView view = new ServerView();
        try{
            ServerControl control = new ServerControl(view); 
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
}
