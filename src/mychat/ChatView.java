/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mychat;

import java.awt.Color;
import java.awt.PopupMenu;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ButtonGroup;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.LayoutStyle;

public class ChatView{
    
    // variables for the main chat
    private JFrame chatFrame;
    private JPanel chatPanel;
    private JScrollPane chatScrollPane;
    private JTextPane chatTextPane;
    private JScrollPane messageScrollPane;
    private JTextPane messageTextPane;
    private JButton sendButton;
    private JButton signOutButton;
    private JButton settingsButton;

    // variables for sign in window
    private JFrame signInFrame;
    private JPanel signInPanel;
    private JRadioButton clientRadioButton;
    private JTextField nameTextField;
    private JTextField portTextField;
    private JLabel nameLabel;
    private JRadioButton serverRadioButton;
    private JTextField ipTextField;
    private JLabel portLabel;
    private JLabel ipLabel;
    private JButton connectButton;
    
    // variables fpr settings window
    private JPanel settingsPanel;
    private JColorChooser messageColorChooser;
    
    public ChatView() {
        initSignInComponents();
        initChatComponents();
        signInWindowFrame();
        chatWindowFrame();
        
    }
    // initializes the sign in components
    private void initSignInComponents(){
        signInPanel = new JPanel();
        clientRadioButton = new JRadioButton("Client", false);
        nameTextField = new JTextField();
        portTextField = new JTextField("5555");
        nameLabel = new JLabel();
        serverRadioButton = new JRadioButton("Server", true);
        ipTextField = new JTextField("You are the server");
        portLabel = new JLabel();
        ipLabel = new JLabel();
        connectButton = new JButton("Connect");

        ipTextField.setEditable(false);
        
        nameLabel.setText("Name");

        portLabel.setText("Port");

        ipLabel.setText("IP");

        GroupLayout signInPanelLayout = new GroupLayout(signInPanel);
        signInPanel.setLayout(signInPanelLayout);
        signInPanelLayout.setHorizontalGroup(
            signInPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(signInPanelLayout.createSequentialGroup()
                .addGap(114, 114, 114)
                .addGroup(signInPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addGroup(GroupLayout.Alignment.TRAILING, signInPanelLayout.createSequentialGroup()
                        .addGroup(signInPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                            .addComponent(nameLabel)
                            .addComponent(portLabel, GroupLayout.PREFERRED_SIZE, 41, GroupLayout.PREFERRED_SIZE)
                            .addComponent(ipLabel))
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(signInPanelLayout.createParallelGroup(GroupLayout.Alignment.TRAILING, false)
                            .addComponent(ipTextField)
                            .addComponent(portTextField, GroupLayout.Alignment.LEADING)
                            .addComponent(nameTextField, GroupLayout.Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 161, GroupLayout.PREFERRED_SIZE)))
                    .addGroup(GroupLayout.Alignment.TRAILING, signInPanelLayout.createSequentialGroup()
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 37, GroupLayout.PREFERRED_SIZE)
                        .addGroup(signInPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                            .addGroup(GroupLayout.Alignment.TRAILING, signInPanelLayout.createSequentialGroup()
                                .addComponent(connectButton)
                                .addGap(76, 76, 76))
                            .addGroup(GroupLayout.Alignment.TRAILING, signInPanelLayout.createSequentialGroup()
                                .addComponent(serverRadioButton)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(clientRadioButton)
                                .addGap(33, 33, 33)))))
                .addGap(112, 112, 112))
        );
        signInPanelLayout.setVerticalGroup(
            signInPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(signInPanelLayout.createSequentialGroup()
                .addGap(36, 36, 36)
                .addGroup(signInPanelLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(serverRadioButton)
                    .addComponent(clientRadioButton))
                .addGap(33, 33, 33)
                .addGroup(signInPanelLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(nameTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addComponent(nameLabel))
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(signInPanelLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(portTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addComponent(portLabel))
                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(signInPanelLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(ipTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addComponent(ipLabel))
                .addGap(18, 18, 18)
                .addComponent(connectButton)
                .addContainerGap(56, Short.MAX_VALUE))
        );

    }
                        
    //initializes the chat window components
    private void initChatComponents() {
        
        chatPanel = new JPanel();
        chatScrollPane = new JScrollPane();
        chatTextPane = new JTextPane();
        messageScrollPane = new JScrollPane();
        messageTextPane = new JTextPane();
        sendButton = new JButton("Send");
        signOutButton = new JButton("Sign Out");
        settingsButton = new JButton("Settings");
        
        chatTextPane.setEditable(false);
        chatScrollPane.setViewportView(chatTextPane);

        messageScrollPane.setViewportView(messageTextPane);
        
        GroupLayout chatPanelLayout = new GroupLayout(chatPanel);
        chatPanel.setLayout(chatPanelLayout);
        chatPanelLayout.setHorizontalGroup(
            chatPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(chatPanelLayout.createSequentialGroup()
                .addGap(79, 79, 79)
                .addGroup(chatPanelLayout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                    .addComponent(chatScrollPane, GroupLayout.PREFERRED_SIZE, 398, GroupLayout.PREFERRED_SIZE)
                    .addGroup(chatPanelLayout.createParallelGroup(GroupLayout.Alignment.TRAILING, false)
                        .addGroup(GroupLayout.Alignment.LEADING, chatPanelLayout.createSequentialGroup()
                            .addComponent(settingsButton)
                            .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(signOutButton))
                        .addGroup(GroupLayout.Alignment.LEADING, chatPanelLayout.createSequentialGroup()
                            .addComponent(messageScrollPane, GroupLayout.PREFERRED_SIZE, 304, GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(sendButton, GroupLayout.PREFERRED_SIZE, 88, GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(79, Short.MAX_VALUE))
        );
        
        chatPanelLayout.setVerticalGroup(
            chatPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(GroupLayout.Alignment.TRAILING, chatPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(chatPanelLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(signOutButton)
                    .addComponent(settingsButton))
                .addGap(5, 5, 5)
                .addComponent(chatScrollPane, GroupLayout.PREFERRED_SIZE, 153, GroupLayout.PREFERRED_SIZE)
                .addGroup(chatPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addGroup(chatPanelLayout.createSequentialGroup()
                        .addGap(5, 5, 5)
                        .addComponent(messageScrollPane, GroupLayout.PREFERRED_SIZE, 100, GroupLayout.PREFERRED_SIZE))
                    .addGroup(chatPanelLayout.createSequentialGroup()
                        .addGap(37, 37, 37)
                        .addComponent(sendButton)))
                .addContainerGap(24, Short.MAX_VALUE))
        );

    }// </editor-fold>               
    
    // sets up the sign in window frame
    public void signInWindowFrame(){
        signInFrame = new JFrame();
        signInFrame.add(signInPanel);
        
        GroupLayout layout = new GroupLayout(signInFrame.getContentPane());
        signInFrame.getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addComponent(signInPanel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(signInPanel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
        );
        
        signInFrame.setVisible(true);
        signInFrame.pack();
        signInFrame.setLocationRelativeTo(null);
        signInFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
    }
    
    //sets up the chat window frame
    public void chatWindowFrame(){
        chatFrame = new JFrame();
        chatFrame.add(chatPanel);
        
        GroupLayout layout = new GroupLayout(chatFrame.getContentPane());
        chatFrame.getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addComponent(chatPanel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        
        layout.setVerticalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(chatPanel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        
        chatFrame.setVisible(false);
        chatFrame.pack();
        chatFrame.setLocationRelativeTo(null);
        chatFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  
    }

    // a bunch of getter for the sign in window
    
    public JFrame getSignInFrame(){
        return signInFrame;
    }
    
    public JRadioButton getServerRadioButton(){
        return serverRadioButton;
    }
    
    public JRadioButton getClientRadioButton(){
        return clientRadioButton;
    }
    
    public JTextField getNameTextField(){
        return nameTextField;
    }
    
    public JTextField getIPTextField(){
        return ipTextField;
    }
    
    public JTextField getPortTextField(){
        return portTextField;
    }
    
    public JButton getConnectButton(){
        return connectButton;
    }
    
    // a bunch of getters for the chat-window
    
    JFrame getChatFrame(){
        return chatFrame;
    }
    
    JPanel getChatPanel(){
        return chatPanel;
    }
    
    public JScrollPane getChatScrollPane(){
        return chatScrollPane;
    }
    
    public JButton getSendButton(){
        return sendButton;
    }
    
    public JButton getSignOutButton(){
        return signOutButton;
    }
    
    JButton getSettingButton(){
        return settingsButton;
    }
    
    public JTextPane getChatTextPane(){
        return chatTextPane;
    }
    
    public JTextPane getMessageTextPane(){
        return messageTextPane;
    }
    
    public JColorChooser getMessageColorChooser(){
        return messageColorChooser;
    }
    
    // for the settings window
    
    public void colorChooser(){
        JColorChooser.showDialog(chatPanel,"Choose",Color.BLACK);  
    }
    
    
}
