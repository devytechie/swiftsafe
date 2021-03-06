package GUIs;
import Handling.*;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import java.util.ArrayList;

public class CreateNewUserGui extends JPanel implements ActionListener {
    private static final long serialVersionUID = 1L;
    ArrayList<JTextField> fields;
    ArrayList<JPasswordField> fields1;
    ArrayList<JButton> bttn;
    JFrame frame;
    JFrame old;
    public int count = 1;
    public CreateNewUserGui(JFrame a){
	fields = new ArrayList<JTextField>();
	fields1 = new ArrayList<JPasswordField>();
	bttn = new ArrayList<JButton>();
	old = a;
	createAndShowGUI();
    }
    
    public void createAndShowGUI() {
        //Create and set up the window.
        frame = new JFrame("Create New User Wizard");
        frame.setPreferredSize(new Dimension(400, 800));
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.addWindowListener( new WindowAdapter()
	    {
		public void windowClosing(WindowEvent e) { 
		    Welcome.resetCount(); 
		    old.setVisible(true);
		}
	    });
	
        //Set up the content pane.
        addComponentsToPane(frame.getContentPane());
        
        //Display the window.
        frame.pack();
        frame.setVisible(true);
    }
    
    public void addComponentsToPane(Container pane) {
	pane.setLayout(new BoxLayout(pane, BoxLayout.Y_AXIS));
	
	gui.addemptyrows(5, pane);
	
	gui.addLabel("Welcome to Create New User Wizard.", pane);
	gui.addLabel("First, please read the instructions below.", pane);
	gui.addLabel("Then provide the necessary information:", pane);
	fields.add(gui.addTextField("Username", pane));
	fields1.add(gui.addPassField("Password", pane));
	fields1.add(gui.addPassField("Retype Password", pane));
	fields.add(gui.addTextField("Sequirity Question", pane));
        fields1.add(gui.addPassField("Answer to Sequirity Question", pane));
        
        gui.addemptyrows(3, pane);
        bttn.add(gui.bttn("Create User"));
        
        bttn.get(0).addActionListener(new ActionListener(){
		public void actionPerformed(ActionEvent e){
		    try {
			CreateNewUser.CreateUser(pane, frame, fields, fields1, old);
		    } catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		    }
		}
            });
        pane.add(bttn.get(0));
        gui.addemptyrows(1, pane);
        gui.addLabel("Important: Your username must be between 5 and 30 characters,", pane);
        gui.addLabel("and must be unique. Your password and sequrity answer must be", pane);
        gui.addLabel("between 8 and 25 characters, and contain:", pane);
        gui.addLabel(" - one capital letter", pane);
        gui.addLabel(" - one lowercase letter", pane);
        gui.addLabel(" - one special character", pane);
        gui.addLabel(" - one number.", pane);
        gui.addLabel("Your sequrity question must be between 10 and 30 characters.", pane);
        bttn.add(gui.bttn("Back"));
        bttn.get(1).addActionListener(new ActionListener(){
		public void actionPerformed(ActionEvent e){
		    Welcome.resetCount();
		    frame.dispose();
		    old.setVisible(true);
		}
            });
        pane.add(bttn.get(1));
        gui.addemptyrows(1, pane);
        gui.addLabel("Thank you!", pane);
        gui.addLabel("?Ilija & Ray SSS?", pane);
    }
    
    @Override
    public void actionPerformed(ActionEvent arg0) {
	// TODO Auto-generated method stub
	
    }
}
