package mediaone.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import mediaone.model.Book;
import mediaone.utils.ConnectionUtils;

public class BookRepository implements ProductRepository<Book>{

	@Override
	public List<Book> findAll() {
		List<Book> products = new ArrayList<>();
		
		try {
			Connection conn = ConnectionUtils.getConnection();
			String sql = "select * from sach inner join sanpham on sach.MaSach =  sanpham.MaSP;";
			Statement statement = conn.createStatement();
			ResultSet rs = statement.executeQuery(sql);
			while(rs.next()) {
				products.add(new Book(rs.getString("MaSach"), 
										 rs.getString("Ten"), 
										 rs.getInt("SoLuong"), 
										 rs.getDouble("GiaBan"), 
										 rs.getDouble("GiaMua"),
										 rs.getString("NhaXB"),
										 rs.getString("TacGia")));
				
			}
			conn.close();
			statement.close();
			rs.close();
		}catch (Exception e) {
			e.printStackTrace();
		}
		return products;
	}
	
	@Override
	public Book add(Book book) {
		try {
			Connection conn = ConnectionUtils.getConnection();
			String productSql = "insert into sanpham values (?,?,?,?,?);";
			String bookSql = "insert into sach values (?,?,?);";
			PreparedStatement productPstm = conn.prepareStatement(productSql);
			PreparedStatement bookPstm = conn.prepareStatement(bookSql);
			
			productPstm.setString(1, book.getIdProduct());
			productPstm.setString(2, book.getNameProduct());
			productPstm.setInt(3, book.getQuantity());
			productPstm.setDouble(4, book.getOutPrice());
			productPstm.setDouble(5, book.getInPrice());
			
			bookPstm.setString(1, book.getIdProduct());
			bookPstm.setString(2, book.getPublisher());
			bookPstm.setString(3, book.getAuthor());
			
			productPstm.executeUpdate();
			bookPstm.executeUpdate();
			
			conn.close();
			productPstm.close();
			bookPstm.close();
		}catch (Exception e) {
			e.printStackTrace();
		}
		return book;
	}

	@Override
	public boolean removeByID(String id) {
		boolean flag = false;
		try {
			Connection conn = ConnectionUtils.getConnection();
			String productSql = "delete from sanpham where MaSP = ?";
			String bookSql = "delete from sach where MaSach = ?;";
			PreparedStatement productPstm = conn.prepareStatement(productSql);
			PreparedStatement bookPstm = conn.prepareStatement(bookSql);
			
			productPstm.setString(1, id);
			bookPstm.setString(1, id);
			
			productPstm.executeUpdate();
			bookPstm.executeUpdate();
			flag = true;
			
			conn.close();
			productPstm.close();
			bookPstm.close();
		}catch (Exception e) {
			e.printStackTrace();
		}
		return flag;
	}

	@Override
	public Book update(Book book) {
		try {
			Connection conn = ConnectionUtils.getConnection();
			String productSql = "update sanpham set MaSp = ?, Ten = ?, SoLuong = ?, GiaBan = ?, GiaMua = ? " +
							    "where MaSp = ?;";
			String bookSql = "	update sach set MaSach = ?, NhaXB = ?, TacGia = ? " + 
							   "where MaSach = ?"
							   ;
			PreparedStatement productPstm = conn.prepareStatement(productSql);
			PreparedStatement bookPstm = conn.prepareStatement(bookSql);
			
			productPstm.setString(1, book.getIdProduct());
			productPstm.setString(2, book.getNameProduct());
			productPstm.setInt(3, book.getQuantity());
			productPstm.setDouble(4, book.getOutPrice());
			productPstm.setDouble(5, book.getInPrice());
			productPstm.setString(6, book.getIdProduct());
			
			bookPstm.setString(1, book.getIdProduct());
			bookPstm.setString(2, book.getPublisher());
			bookPstm.setString(3, book.getAuthor());
			bookPstm.setString(4, book.getIdProduct());
			
			productPstm.executeUpdate();
			bookPstm.executeUpdate();
			
			conn.close();
			productPstm.close();
			bookPstm.close();
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		return book;
	}

	@Override
	public Book findOne(String id) {
		Book book = null;
		
		try {
			Connection conn = ConnectionUtils.getConnection();
			String sql = "select * from sach inner join sanpham on sach.MaSach =  sanpham.MaSP where sach.MaSach = ?;";
			PreparedStatement pstm = conn.prepareStatement(sql);
			
			pstm.setString(1, id);
			ResultSet rs = pstm.executeQuery();
			while(rs.next()) {
				book = new Book(rs.getString("MaSP"), 
										 rs.getString("Ten"), 
										 rs.getInt("SoLuong"), 
										 rs.getDouble("GiaBan"), 
										 rs.getDouble("GiaMua"),
										 rs.getString("NhaXB"),
										 rs.getString("TacGia"));
				
			}
			conn.close();
			pstm.close();
			rs.close();
		}catch (Exception e) {
			e.printStackTrace();
		}
		return book;
	}


	
//	public static void main(String[] args) {
//		BookRepository bookRepository = new BookRepository();
//		Book book = new Book("SA01", "Vật lý đại cương", 10, 30000, 10000, "Bách Khoa", " PGS. TS. Đỗ Ngọc Uấn");
//		bookRepository.remove(book);
//		
//	}
}

