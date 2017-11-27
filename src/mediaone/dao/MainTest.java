package mediaone.dao;

import java.util.List;

import javax.swing.JOptionPane;

import mediaone.model.Book;
import mediaone.model.MusicCD;

public class MainTest {
	public static void main(String[] args) {
//		QBook qbook = new QBook();
		QMusic qmusic = new QMusic();
		qmusic.setConnect();
//		qbook.setConnect();
		
		//TEST HERE
		MusicCD mCd = new MusicCD("M0002", "Fireflies", 55, 55000, 50000, "Own City", "Rock");
		JOptionPane.showMessageDialog(null, qmusic.addProduct(mCd));
	
		//FINISH TEST
//		qbook.closeConnect();
		qmusic.closeConnect();
	}
}
