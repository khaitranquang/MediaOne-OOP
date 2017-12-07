package mediaone.test;

import java.awt.EventQueue;

import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import mediaone.controller.LoginController;
import mediaone.view.MainUI;

public class MainTest {
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				 try {
					 UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
					 MainUI mainUI = new  MainUI();
					 LoginController loginController = new LoginController(mainUI);
				 } 
				 catch (ClassNotFoundException | InstantiationException | 
						IllegalAccessException | UnsupportedLookAndFeelException ex) {
					 System.out.println("Failed to load UI");
					 MainUI mainUI = new  MainUI();
					 LoginController loginController = new LoginController(mainUI);
				 }
			}
		});
	} 
		
}
