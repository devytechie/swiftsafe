package GUIs;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.ArrayList;

import Handling.AES;
import Handling.Welcome;

public class WelcomeGui extends JPanel implements ActionListener{
    ArrayList<JTextField> fields;
    ArrayList<JPasswordField> fields1;
    ArrayList<JButton> bttn;
    JFrame frame;
    private static final long serialVersionUID = 1L;
    public WelcomeGui(){
	fields = new ArrayList<JTextField>();
	fields1 = new ArrayList<JPasswordField>();
	bttn = new ArrayList<JButton>();
	createAndShowGUI();
    }
    public void createAndShowGUI() {
        //Create and set up the window.
	frame = new JFrame("Swift Safe Store SSS?");
        frame.setPreferredSize(new Dimension(350, 800));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	
        //Set up the content pane.
        addComponentsToPane(frame.getContentPane());
	
        //Display the window.
        frame.pack();
        frame.setVisible(true);
        
        
    }
    
    public void addComponentsToPane(Container pane) {
	pane.setLayout(new BoxLayout(pane, BoxLayout.Y_AXIS));
	gui.addLabel("Welcome to SSS?", pane);
	gui.addemptyrows(9, pane);
	gui.addLabel("Existing Users Login", pane);
	fields.add(gui.addTextField("Username", pane));
        fields1.add(gui.addPassField("Password", pane));
        bttn.add(gui.bttn("Login"));
        bttn.get(0).addActionListener(new ActionListener(){
		public void actionPerformed(ActionEvent e){
		    String username = fields.get(0).getText();
		    
		    try {
			if(Welcome.login(pane, username, fields1.get(0).getPassword())){
			    frame.dispose();
			    Welcome.in(fields1.get(0).getPassword(), username);
			}
		    } catch (NoSuchAlgorithmException | InvalidKeySpecException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		    } catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		    }
		}
	    });
        pane.add(bttn.get(0));
        
        gui.addemptyrows(1, pane);
        bttn.add(gui.bttn("Password Reset"));
        bttn.get(1).addActionListener(new ActionListener(){
		public void actionPerformed(ActionEvent e){
		    Welcome.passReset(frame);
		}
	    });
        pane.add(bttn.get(1));
        
        gui.addemptyrows(13, pane);
        gui.addLabel("New Users", pane);
        
        bttn.add(gui.bttn("Create New User"));
        bttn.get(2).addActionListener(new ActionListener(){
		public void actionPerformed(ActionEvent e){
		    Welcome.CreateUser(frame);
		}
	    });
        pane.add(bttn.get(2));
        gui.addLabel("?Ilija & Ray SSS?", pane);
	
    }
    
    @Override
    public void actionPerformed(ActionEvent arg0) {
	// TODO Auto-generated method stub
	
    }
    
}
