/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mychat;

/**
 *
 * @author andreas
 */


import java.io.Serializable;
import javax.xml.parsers.DocumentBuilder; 
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class Message implements Serializable{
    
    private Element message;
    private Attr sender;
    private String userName;
    private Element text;
    private String messageText;
    private Attr textColorAttr;
    private String textColorHex;    
    private Document document;
    
    private boolean isDisconnect;
    
    public Message(String userName, String messageText, String textColorHex, boolean disconnect){
        
        this.userName = userName;
        this.messageText = messageText;
        this.textColorHex = textColorHex;
        isDisconnect = disconnect;
        
        if(!disconnect){
            initMessageComponents(userName, messageText, textColorHex);
            
        }
        else{
            initDisconnectMessageComponents(userName);
            
        }
    }
    
    private void initMessageComponents(String userName, String messageText, String textColorHex){
              
        try{
            DocumentBuilderFactory documentFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder = documentFactory.newDocumentBuilder();
            document = documentBuilder.newDocument();
            
            // Message (root) element
            message = document.createElement("Message");
            document.appendChild(message);
            
            // message element attribute
            sender = document.createAttribute("Sender");
            sender.setValue(userName);
            message.setAttributeNode(sender);

            // text element
            text = document.createElement("text");
            text.appendChild(document.createTextNode(messageText));
            message.appendChild(text);

            // text element attribute
            textColorAttr = document.createAttribute("color");
            textColorAttr.setValue(textColorHex);
            text.setAttributeNode(textColorAttr);
            
            /*
            Element italic = document.createElement("italic");
            text.appendChild(italic);
            
            Element bold = document.createElement("bold");
            italic.appendChild(bold);
            
            bold.appendChild(document.createTextNode(messageText));
            */
            
        } catch(ParserConfigurationException pce){}
    }
    
    private void initDisconnectMessageComponents(String userName){
              
        try{
            DocumentBuilderFactory documentFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder = documentFactory.newDocumentBuilder();
            document = documentBuilder.newDocument();
            
            // Message (root) element
            message = document.createElement("Message");
            document.appendChild(message);
            
            // message element attribute
            sender = document.createAttribute("Sender");
            sender.setValue(userName);
            message.setAttributeNode(sender);

            // text element
            text = document.createElement("Disconnected");
            text.appendChild(document.createTextNode(""));
            message.appendChild(text);

        } catch(ParserConfigurationException pce){}
    }
    
    public String getUserName(){
        return userName;
    }
    
    public String getMessageText(){
        return messageText;
    }
    
    public String getTextColorHex(){
        return textColorHex;
    }
    
    public Document getDocument(){
        return document;
    }
    
    public Element getText(){
        return text;
    }
    
    public Element getMessage(){
        return message;
    }
    
    boolean getIsDisconnect(){
        return isDisconnect;
    }
    void setUsername(String userName){
        this.userName = userName;
    }
    
    void setMessageText(String messageText){
        this.messageText = messageText;
    }
    
    void setTextColorHex(String textColorHex){
        this.textColorHex = textColorHex;
    }
}
