package mediaone.test;

import mediaone.controller.ProductController;
import mediaone.view.MainUI;

public class MainTest {
	public static void main(String[] args) {
		MainUI mainUI = new MainUI();
		new ProductController(mainUI);
	} 
		
}
