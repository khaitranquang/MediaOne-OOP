package mediaone.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import mediaone.model.FilmCD;
import mediaone.utils.ConnectionUtils;

public class FilmCDRepository implements ProductRepository<FilmCD>{
	
	@Override
	public List<FilmCD> findAll() {
		List<FilmCD> products = new ArrayList<>();
		
		try {
			Connection conn = ConnectionUtils.getConnection();
			String sql = "select * from diaphim inner join sanpham on diaphim.MaDiaPhim =  sanpham.MaSP;";
			Statement statement = conn.createStatement();
			ResultSet rs = statement.executeQuery(sql);
			while(rs.next()) {
				products.add(new FilmCD(rs.getString("MaDiaPhim"), 
										 rs.getString("Ten"), 
										 rs.getInt("SoLuong"), 
										 rs.getDouble("GiaBan"), 
										 rs.getDouble("GiaMua"),
										 rs.getString("TenDaoDien"),
										 rs.getString("TheLoai")));
				
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
	public FilmCD add(FilmCD filmCD) {
		try {
			Connection conn = ConnectionUtils.getConnection();
			String productSql = "insert into sanpham values (?,?,?,?,?);";
			String filmCDSql = "insert into diaphim values (?,?,?);";
			PreparedStatement productPstm = conn.prepareStatement(productSql);
			PreparedStatement filmCDPstm = conn.prepareStatement(filmCDSql);
			
			productPstm.setString(1, filmCD.getIdProduct());
			productPstm.setString(2, filmCD.getNameProduct());
			productPstm.setInt(3, filmCD.getQuantity());
			productPstm.setDouble(4, filmCD.getOutPrice());
			productPstm.setDouble(5, filmCD.getInPrice());
			
			filmCDPstm.setString(1, filmCD.getIdProduct());
			filmCDPstm.setString(2, filmCD.getDirector());
			filmCDPstm.setString(3, filmCD.getType());
			
			productPstm.executeUpdate();
			filmCDPstm.executeUpdate();
			
			conn.close();
			productPstm.close();
			filmCDPstm.close();
		}catch (Exception e) {
			e.printStackTrace();
		}
		return filmCD;
	}

	@Override
	public boolean removeByID(String id) {
		boolean flag = false;
		try {
			Connection conn = ConnectionUtils.getConnection();
			String productSql = "delete from sanpham where MaSP = ?";
			String filmCDSql = "delete from diaphim where MaDiaPhim = ?;";
			PreparedStatement productPstm = conn.prepareStatement(productSql);
			PreparedStatement filmCDPstm = conn.prepareStatement(filmCDSql);
			
			productPstm.setString(1, id);
			filmCDPstm.setString(1, id);
			
			productPstm.executeUpdate();
			filmCDPstm.executeUpdate();
			flag = true;
			
			conn.close();
			productPstm.close();
			filmCDPstm.close();
		}catch (Exception e) {
			e.printStackTrace();
		}
		return flag;
	}

	@Override
	public FilmCD update(FilmCD filmCD) {
		try {
			Connection conn = ConnectionUtils.getConnection();
			String productSql = "update sanpham set MaSp = ?, Ten = ?, SoLuong = ?, GiaBan = ?, GiaMua = ? " +
							    "where MaSp = ?;";
			String filmCDSql = "	update diaphim set MaDiaPhim = ?, TenDaoDien = ?, TheLoai = ? " + 
							   "where MaDiaPhim = ?"
							   ;
			PreparedStatement productPstm = conn.prepareStatement(productSql);
			PreparedStatement filmCDPstm = conn.prepareStatement(filmCDSql);
			
			productPstm.setString(1, filmCD.getIdProduct());
			productPstm.setString(2, filmCD.getNameProduct());
			productPstm.setInt(3, filmCD.getQuantity());
			productPstm.setDouble(4, filmCD.getOutPrice());
			productPstm.setDouble(5, filmCD.getInPrice());
			productPstm.setString(6, filmCD.getIdProduct());
			
			filmCDPstm.setString(1, filmCD.getIdProduct());
			filmCDPstm.setString(2, filmCD.getDirector());
			filmCDPstm.setString(3, filmCD.getType());
			filmCDPstm.setString(4, filmCD.getIdProduct());
			
			productPstm.executeUpdate();
			filmCDPstm.executeUpdate();
		}catch (Exception e) {
			e.printStackTrace();
		}
		return filmCD;
	}

	@Override
	public FilmCD findOne(String id) {
		FilmCD product = null;
		
		try {
			Connection conn = ConnectionUtils.getConnection();
			String sql = "select * from diaphim inner join sanpham on diaphim.MaDiaPhim =  sanpham.MaSP "+
						 "where diaphim.MaDiaPhim = ?;";
			PreparedStatement pstm = conn.prepareStatement(sql);
			
			pstm.setString(1, id);
			ResultSet rs = pstm.executeQuery();
			while(rs.next()) {
				product = new FilmCD(rs.getString("MaSP"), 
										 rs.getString("Ten"), 
										 rs.getInt("SoLuong"), 
										 rs.getDouble("GiaBan"), 
										 rs.getDouble("GiaMua"),
										 rs.getString("TenDaoDien"),
										 rs.getString("TheLoai"));
				
			}
			conn.close();
			pstm.close();
			rs.close();
		}catch (Exception e) {
			e.printStackTrace();
		}
		return product;
	}	
	
	public static void main(String[] args) {
		FilmCDRepository filmCDRepository = new FilmCDRepository();
		FilmCD filmCD = new FilmCD("FD01", "Pokemon", 40, 20000, 10000, "YUJI ASADA", "Hoạt hình");
		filmCDRepository.update(filmCD);
	}
}


