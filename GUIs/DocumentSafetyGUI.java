package GUIs;

import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import Handling.AES;
import Handling.doc;

public class DocumentSafetyGUI {
    ArrayList<JButton> bttn;
    JFrame old;
    JFrame frame;
    String user;
    public DocumentSafetyGUI(String u, JFrame a){
	bttn = new ArrayList<JButton>();
	user = u;
	old = a;
	createAndShowGUI();
    }
    public void createAndShowGUI() {
        //Create and set up the window.
        frame = new JFrame("Document Safety for "+user);
        frame.setPreferredSize(new Dimension(350,800));
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.addWindowListener( new WindowAdapter()
	    {
		public void windowClosing(WindowEvent e) { 
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
	gui.addLabel("Welcome to the Document Safety Unit!", pane);
	gui.addLabel("You may either safely encrypt or decrypt your document.", pane);
	gui.addLabel("Your documents can be imported from your local drive.", pane);
	gui.addemptyrows(1, pane);
	gui.addLabel("Encrypted documents can be found in", pane);
	gui.addLabel("SSS' dir/Program Fles/"+user+"'s folder", pane);
	
	gui.addemptyrows(5, pane);
	bttn.add(gui.bttn("Choose document to encrypt"));
        bttn.get(0).addActionListener(new ActionListener(){
		public void actionPerformed(ActionEvent e){
		    File file = doc.FileChooser(frame, "doc");
		    if(file != null) {
            		AES.toEncrypt(file, ("Program Files/"+user+"/DocSafety/"));
            		JOptionPane.showMessageDialog(frame, "File Successfully imported and encrypted", "Success", JOptionPane.INFORMATION_MESSAGE);
		    }
		}
	    });
        pane.add(bttn.get(0));
        
        gui.addemptyrows(2, pane);
        bttn.add(gui.bttn("Choose document to decrypt"));
        bttn.get(1).addActionListener(new ActionListener(){
		public void actionPerformed(ActionEvent e){
		    File file = doc.FileChooser(frame, user, "DocSafety", "doc");
		    if(file != null && !file.getName().toLowerCase().contains("decrypted!_")) {
            		AES.toDecrypt(file,"Program Files/"+user+"/DocSafety/");
            		JOptionPane.showMessageDialog(frame, "File Successfully decrypted", "Success", JOptionPane.INFORMATION_MESSAGE);
		    }
		}
	    });
        pane.add(bttn.get(1));
        
        gui.addemptyrows(1, pane);
        JLabel Image = new JLabel(new ImageIcon("Images/document.png"));
        Image.setAlignmentX(Component.CENTER_ALIGNMENT);
        pane.add(Image);
        gui.addLabel("?pvhc.net",pane);
        gui.addemptyrows(1, pane);
        bttn.add(gui.bttn("Back"));
        bttn.get(2).addActionListener(new ActionListener(){
		public void actionPerformed(ActionEvent e){
		    frame.dispose();
		    old.setVisible(true);
		}
	    });
        pane.add(bttn.get(2));
        gui.addemptyrows(3, pane);
        gui.addLabel("Thank you for your trust.", pane);
        gui.addLabel("?Ilija & Ray SSS?", pane);
    }
}
