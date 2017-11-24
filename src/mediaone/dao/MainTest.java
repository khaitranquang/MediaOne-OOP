package mediaone.dao;

import java.sql.Connection;
import java.util.List;

import mediaone.model.Book;
import mediaone.utils.ConnectionUtils;

public class MainTest {
	public static void main(String[] args) {
		QBook qbook = new QBook();
		Connection con = ConnectionUtils.getConnection();
		qbook.setConnect(con);
		
		//TEST HERE

//		String notif = qbook.delProductID("B9964");
//		System.out.println(notif);
		
		Book book = new Book("B0003", "Một ngày đẹp trời", 90, 72000, 65000, "NXB Trẻ - VN", "Japanese");
		System.out.println(qbook.addProduct(book));
		
		List<Book> lsBook = qbook.loadProduct();
		for (Book book1 : lsBook) {
			System.out.println(book1.getIdProduct() + " - " + book1.getNameProduct());
		}
		//FINISH TEST
		qbook.closeConnect();
		ConnectionUtils.closeConnection();
	}
}
