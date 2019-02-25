/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mychat;

import clientserver.ChatClient;
import clientserver.ChatServer;
import javax.swing.GroupLayout;
import javax.swing.JFrame;

/**
 *
 * @author andreas
 */
public class MyChat{
    
    private ChatServer server;
    private ChatClient client;
    private ChatView chat;
    private ChatController controller;
      
    public static void main(String[] args) {
        MyChat chat = new MyChat();
    }
    
    public MyChat(){
        //chat = new ChatView();
        //server = new ChatServer(Integer.parseInt(chat.getPortTextField().getText()));
        //controller = new ChatController(chat);
        
        controller = new ChatController();
        while(controller.getChat().getSignInFrame().isDisplayable()){
            try{
                //empty while did not work
                Thread.sleep(10);
            }catch(InterruptedException ie){}   
        }
        
        if(controller.isServer()){
            server = new ChatServer(controller.getPort(), controller);
            controller.setServer(server);
            controller.setIsServer(true);
            server.start();
        }
        else{
            client = new ChatClient(controller.getIPAddress(), 
                                        controller.getPort(), controller);
            controller.setClient(client);
            client.start();
        }
    } 
}
