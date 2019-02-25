/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mychat;

import clientserver.ChatClient;
import clientserver.ChatServer;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ButtonGroup;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

/**
 *
 * @author andreas
 */
public class ChatController implements ActionListener{
    
    private ChatView chat;
    private Message message;
    private boolean isServer;
    
    private ChatClient client;
    private ChatServer server;
    
    public ChatController(){
        chat = new ChatView();
        message = new Message("", "", colorToHex(Color.BLACK), true);
        
        ButtonGroup clientServerRadioButtonGroup = new ButtonGroup();
        clientServerRadioButtonGroup.add(chat.getClientRadioButton());
        clientServerRadioButtonGroup.add(chat.getServerRadioButton());
        
        
        // tight coupling. Could be resolved by having a ChatWindow Interface.        
        // for sign in window
        chat.getServerRadioButton().addActionListener(this);
        chat.getConnectButton().addActionListener(this);
        chat.getClientRadioButton().addActionListener(this);
        
        //for chat window
        chat.getSendButton().addActionListener(this);
        chat.getSignOutButton().addActionListener(this);
        chat.getSettingButton().addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {   
        
        //sign in window actions
        if (e.getSource() == chat.getServerRadioButton()){
            serverRadioButtonActionPerformed();
        }
        
        if (e.getSource() == chat.getClientRadioButton()){
            clientRadioButtonActionPerformed();
        }
        if (e.getSource() == chat.getConnectButton()){
            connectButtonActionPerformed();
        }
        
        //chat window actions 
        if (e.getSource() == chat.getSendButton()){
            sendButtonActionPerformed();
        }
        
        if (e.getSource() == chat.getSignOutButton()){
            signOutButtonActionPerformed();
        }
        
        if (e.getSource() == chat.getSettingButton()){
            settingsButtonActionPerformed();
        }
         
    }
    
    //methods for action performed
    
    //
    private void connectButtonActionPerformed(){
        // could handle non int port inputs
        chat.getSignInFrame().dispose();
        chat.getChatFrame().setVisible(true);
    }
    
    private void serverRadioButtonActionPerformed(){
        chat.getIPTextField().setText("You are the server");
        chat.getIPTextField().setEditable(false);
    }
    
    private void clientRadioButtonActionPerformed(){
        chat.getIPTextField().setText("localhost");
        chat.getIPTextField().setEditable(true);
    }
    
    // action performed methods for chat frame
    
    private void sendButtonActionPerformed(){
        if (!chat.getMessageTextPane().getText().equals("")){
                
                message.setMessageText(chat.getMessageTextPane().getText());
                message.setUsername(chat.getNameTextField().getText());
                
                
                message = new Message(message.getUserName(), 
                        message.getMessageText(),
                        message.getTextColorHex(), false
                );
                
                getChat().getChatScrollPane().
                        getVerticalScrollBar().setValue(
                        getChat().getChatScrollPane().
                        getVerticalScrollBar().getMaximum());
                
                if (isServer){
                    server.sendMessage((Object) message);
                }
                else{
                    client.sendMessage((Object) message);
                }
                
                StyledDocument doc = chat.getChatTextPane().getStyledDocument();

            //  To add color
            SimpleAttributeSet keyWord = new SimpleAttributeSet();
            StyleConstants.setForeground(keyWord, 
                    Color.decode(message.getTextColorHex()));

            //  Append the text
            try{
                doc.insertString(doc.getLength(), 
                        message.getUserName() + 
                        ": " + 
                        message.getMessageText() + 
                        "\n", keyWord 
                );
                printXMLMessage(message);
            }catch(Exception err) { System.out.println(err); }
            
            chat.getMessageTextPane().setText("");
            }
    }
    
    private void settingsButtonActionPerformed(){
        Color color = chat.getMessageColorChooser().showDialog(chat.getChatPanel(),
                                                        "Choose",
                                                            Color.BLACK
        );
        message.setTextColorHex(colorToHex(color));
        
    }
    
    private void signOutButtonActionPerformed(){
                
        message.setMessageText(chat.getMessageTextPane().getText());
        message.setUsername(chat.getNameTextField().getText());


        message = new Message(message.getUserName(), 
                message.getMessageText(),
                message.getTextColorHex(), true
        );

        if (isServer){
            server.sendMessage((Object) message);
        }
        else{
            client.sendMessage((Object) message);
        }
        
        System.exit(0);
    }
    
    //for updating chatView
    
    public void displayReceivedMessage(Object obj){
        
        Message msg = (Message) obj;
        
        String chatMessage = msg.getMessageText();
        String messageSender = msg.getUserName();
        String messageColor = msg.getTextColorHex();
        boolean isDisconnect = msg.getIsDisconnect();
        
        //to access the message even if italic and bold tags are present.
        /*
        Node iterNode = msg.getText();
        while(iterNode.hasChildNodes()){
            iterNode = iterNode.getFirstChild(); 
        }
        */
        
        
        getChat().getChatScrollPane().
                        getVerticalScrollBar().setValue(
                        getChat().getChatScrollPane().
                        getVerticalScrollBar().getMaximum());
        
        //normal messages
        if (!isDisconnect){
        
            StyledDocument doc = chat.getChatTextPane().getStyledDocument();

            //  To add color
            SimpleAttributeSet keyWord = new SimpleAttributeSet();
            StyleConstants.setForeground(keyWord, Color.decode(messageColor));

            //  Append the text
            try{
                doc.insertString(doc.getLength(), 
                        messageSender + 
                        ": " + 
                        chatMessage + 
                        "\n", keyWord 
                );
                printXMLMessage(msg);
            }catch(Exception err) { System.out.println(err); }
        }
        //if a user disconnects
        else{
            StyledDocument doc = chat.getChatTextPane().getStyledDocument();

            //  To add color
            SimpleAttributeSet keyWord = new SimpleAttributeSet();
            StyleConstants.setForeground(keyWord, Color.RED);

            //  Append the text
            try{
                doc.insertString(doc.getLength(), 
                        messageSender + 
                        " has signed out." 
                         + 
                        "\n", keyWord 
                );
                printXMLMessage(msg);
            }catch(Exception err) { System.out.println(err); }
        }
    }
    
    //this method is only to verify the XML 
    private void printXMLMessage(Message msg) throws TransformerException{
        // create the xml file
            //transform the DOM Object to an XML File
            
            
            Message msgPrint = new Message(msg.getUserName(), 
                    msg.getMessageText(), msg.getTextColorHex(), 
                    msg.getIsDisconnect()
            );
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource domSource = new DOMSource(msgPrint.getDocument());

            // print the created XML document
            StreamResult result = new StreamResult(System.out);

            transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "no");
            transformer.setOutputProperty(OutputKeys.METHOD, "xml");
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
            transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");
            transformer.transform(domSource, result);

    }
    
    //getters to instantiate the client/server
    
    public String getIPAddress(){
        return chat.getIPTextField().getText();
    }
    
    public int getPort(){
        return Integer.parseInt(chat.getPortTextField().getText());
    }
    
    public boolean isServer(){
        return chat.getServerRadioButton().isSelected();
    }
    
    //getter for chatView
    
    public ChatView getChat(){
        return chat;
    }
    
    //getter for Message
    //cast to object so client/server can handle it.
    public Object getMessage(){
        return (Object) message;
    }
    
    //set client/server
    public void setClient(ChatClient client){
        this.client = client;
    }
    
    public void setServer(ChatServer server){
        this.server = server;
    }
    
    
    public void setIsServer(boolean isServer){
        this.isServer = isServer;
    }
    
    //transforming color to hex string 
    private String colorToHex(Color col){
        String hex = String.format("#%02X%02X%02X", col.getRed(), 
                col.getGreen(), col.getBlue()
        );
        return hex;
    }
}


