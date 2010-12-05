import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import sun.audio.*; 
import java.applet.*;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JFrame;

public class MainWindow extends JFrame implements ActionListener {
	public MainWindow() {
		createAndShowGUI();
	}
	
	ArrayList<AudioStream> soundClipArray2 = new ArrayList<AudioStream>();	
	ArrayList<AudioClip> soundClipArray = new ArrayList<AudioClip>();
	
	private void createAndShowGUI() {
	    //Create and set up the window.
	    frame = new JFrame("Lolz Sounds - By Grant J. Gumina");
	    buttonContainer = new JScrollPane();
	    buttonPane = new JPanel();
	    pathToFolderField = new JTextField("/home/grant/Music", 20);
	    
	    Box box = Box.createVerticalBox();
	    JPanel topPane = new JPanel();
	    
	    JButton setPathButton = new JButton("Enter");
	    
	    // Configure objects
	    box.add(Box.createVerticalGlue());
	    JFrame.setDefaultLookAndFeelDecorated(false);
	    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    
	    buttonPane.setLayout(new GridLayout(getWidth(), 3)); 	    
	    
	    setPathButton.addActionListener(new ActionListener() {
	    	public void actionPerformed (ActionEvent evt) {
	    		setPathButtonActionPerformed(evt);
	    	}
	    });

	    // Add to topPane
	    topPane.add(pathToFolderField);
	    topPane.add(setPathButton);
	    
	    // Add to buttonContainer
	    buttonContainer.getViewport().add(buttonPane);
	    
	    // Add to box
	    box.add(topPane);
	    box.add(buttonContainer);
	    
	    // Add to frame
	    frame.add(box, BorderLayout.NORTH);
	    
	    // Display the window.
	    frame.setPreferredSize(new Dimension(700, 400));
	    frame.pack();
	    frame.setVisible(true);
	}
	
	// Create sound buttons
	private JButton createSoundButton(final AudioClip sound, File file) {		
		JButton latestSound = new JButton(file.getName());
		latestSound.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				sound.play();
				//AudioPlayer.player.start(sound);
			}
		});
		return latestSound;
	}
	
	// Events
	@SuppressWarnings("deprecation")
	private void setPathButtonActionPerformed(ActionEvent evt) {
		buttonPane.removeAll();
	    File directory = new File(pathToFolderField.getText());
		File[] folder = directory.listFiles();
		for (int i = 0; i < folder.length; i++) {
			try {
				AudioClip as = Applet.newAudioClip(folder[i].toURL());
				soundClipArray.add(as);
				buttonPane.add(createSoundButton(as, folder[i]));
				frame.validate();
				
			} catch (IOException ex) {
				Logger.getLogger(MainWindow.class.getName()).log(Level.SEVERE, null, ex);
			}
		}
	}
	
	
	public static void main(String args[]) {
		java.awt.EventQueue.invokeLater(new Runnable() {
			public void run() {
				new MainWindow();
			}
		});
    }

	private JTextField pathToFolderField;
	private JPanel buttonPane;
	private JScrollPane buttonContainer;
	private JFrame frame;

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		
	}
}
