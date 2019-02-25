package clientserver;

import clientserver.ChatServer;
import java.net.*;
import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import mychat.ChatController;


public class ChatClient extends Thread{  
   
    private Socket clientSocket;
    private InputStream streamIn;
    private ObjectInputStream objStreamIn;
    private ObjectOutputStream objStreamOut;

    private ChatController controller;

    public ChatClient(String serverName, int serverPort, ChatController controller){
        System.out.println("Establishing connection. Please wait ...");
        this.controller = controller; 
        try{
            clientSocket = new Socket(serverName, serverPort);
            System.out.println("Connected: " + clientSocket);
            controller.getChat().getChatTextPane().setText("connected\n");
            objStreamOut = new ObjectOutputStream(clientSocket.getOutputStream());
            streamIn = clientSocket.getInputStream();
            objStreamIn = new ObjectInputStream(streamIn);
        }catch(UnknownHostException uhe){
            System.out.println("Host unknown: " + uhe.getMessage());
        }
        catch(IOException ioe){ 
            System.out.println("Unexpected exception: " + ioe.getMessage());
        }
    }
    
    @Override
    public void run() {
        while(true){
            try{
 
                Object msg = objStreamIn.readObject();
                if (objStreamIn != null){  
                    controller.displayReceivedMessage(msg);
                }
            }
            catch(IOException ie){  
                stop(); } 
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

