package Handling;

import java.awt.BorderLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedReader;
import java.io.FileReader;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import GUIs.DocumentSafetyGUI;
import GUIs.FinancesGUI;

public class Program {
    public static void doc(String user, JFrame frame) {
	frame.setVisible(false);
    	DocumentSafetyGUI doc = new DocumentSafetyGUI(user, frame);
    }
    public static void fin(String user, JFrame frame) {
	frame.setVisible(false);
    	FinancesGUI finance = new FinancesGUI(user, frame);
    }
    public static void text(JFrame old) {
	old.setVisible(false);
	String fileName = "Help.txt";
        JTextArea help = new JTextArea(45,60);
        help.setLineWrap(true);
        help.setWrapStyleWord(true);
        help.setEditable(false);
        try {
	    FileReader reader = new FileReader("Help.txt");
            BufferedReader br = new BufferedReader(reader);
            help.read( br, null );
            br.close();
            help.requestFocus();
        }catch(Exception e2) {
	    //
        }
        JFrame frame = new JFrame("Help");
        frame.getContentPane().add( new JScrollPane(help), BorderLayout.NORTH );
        
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.addWindowListener( new WindowAdapter()
	    {
		public void windowClosing(WindowEvent e) { 
		    old.setVisible(true);
		}
	    });
        frame.pack();
        frame.setVisible(true);
    }
}
