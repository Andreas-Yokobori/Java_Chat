/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clientserver;

import java.net.*;
import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import mychat.ChatController;

/**
 *
 * @author andreas
 */
public class ChatServer extends Thread{
    
    private Socket clientSocket;
    private ServerSocket serverSocket;
    
    private InputStream streamIn;
    private ObjectInputStream objStreamIn;
    private ObjectOutputStream objStreamOut;
    private ChatController controller;

    public ChatServer(int port, ChatController controller){  
        this.controller = controller;
        try{
            serverSocket = new ServerSocket(port);  
            System.out.println("Server started: " + serverSocket);
        }catch(IOException ioe){
            System.out.println(ioe); 
        }
        
        try{
            clientSocket = serverSocket.accept();    
            System.out.println("Connected");
            controller.getChat().getChatTextPane().setText("connected\n");
        }catch(IOException e){    
            System.out.println("Accept error");
        }
        
        try{
	    objStreamOut = new ObjectOutputStream(clientSocket.getOutputStream());
	}catch(IOException e){
	    System.out.println("getOutputStream failed: " + e);
	 }
	try{
	    streamIn = clientSocket.getInputStream();
            objStreamIn = new ObjectInputStream(streamIn);
	}catch(IOException e){
	    System.out.println("getInputStream failed: " + e);
	}
    }
    
    public void run(){  
        
        while(true){
            try{
                
                
                Object msg = objStreamIn.readObject();
                if (objStreamIn != null){  
                    controller.displayReceivedMessage(msg);
                }
            }catch(IOException ie){  
                stop();} 
            catch (ClassNotFoundException ex) {
                Logger.getLogger(ChatServer.class.getName()).log(Level.SEVERE, null, ex);
            }
            /*finally{
            try{
            streamIn.close();
            objStreamIn.close();
            objStreamOut.close();
            }
            catch(IOException ie){}
            }*/
        }
    }
           
       
    public void sendMessage(Object obj){   
        try {            
            objStreamOut.writeObject(obj);
            objStreamOut.flush();
        } catch (IOException ex) {
            Logger.getLogger(ChatServer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
