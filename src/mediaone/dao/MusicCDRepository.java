package mediaone.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import mediaone.model.MusicCD;
import mediaone.utils.ConnectionUtils;

public class MusicCDRepository implements ProductRepository<MusicCD>{

	@Override
	public List<MusicCD> findAll() {
		List<MusicCD> products = new ArrayList<>();
		
		try {
			Connection conn = ConnectionUtils.getConnection();
			String sql = "select * from dianhac inner join sanpham on dianhac.MaDiaNhac =  sanpham.MaSP;";
			Statement statement = conn.createStatement();
			ResultSet rs = statement.executeQuery(sql);
			while(rs.next()) {
				products.add(new MusicCD(rs.getString("MaDiaNhac"), 
										 rs.getString("Ten"), 
										 rs.getInt("SoLuong"), 
										 rs.getDouble("GiaBan"), 
										 rs.getDouble("GiaMua"),
										 rs.getString("TenCaSi"),
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
	public MusicCD add(MusicCD musicCD) {
		try {
			Connection conn = ConnectionUtils.getConnection();
			String productSql = "insert into sanpham values (?,?,?,?,?);";
			String musicCDSql = "insert into dianhac values (?,?,?);";
			PreparedStatement productPstm = conn.prepareStatement(productSql);
			PreparedStatement musicCDPstm = conn.prepareStatement(musicCDSql);
			
			productPstm.setString(1, musicCD.getIdProduct());
			productPstm.setString(2, musicCD.getNameProduct());
			productPstm.setInt(3, musicCD.getQuantity());
			productPstm.setDouble(4, musicCD.getOutPrice());
			productPstm.setDouble(5, musicCD.getInPrice());
			
			musicCDPstm.setString(1, musicCD.getIdProduct());
			musicCDPstm.setString(2, musicCD.getSingerName());
			musicCDPstm.setString(3, musicCD.getType());
			
			productPstm.executeUpdate();
			musicCDPstm.executeUpdate();
			
			conn.close();
			productPstm.close();
			musicCDPstm.close();
		}catch (Exception e) {
			e.printStackTrace();
		}
		return musicCD;
	}

	@Override
	public boolean removeByID(String id) {
		boolean flag = false;
		try {
			Connection conn = ConnectionUtils.getConnection();
			String productSql = "delete from sanpham where MaSP = ?";
			String musicCDSql = "delete from dianhac where MaDiaNhac = ?;";
			PreparedStatement productPstm = conn.prepareStatement(productSql);
			PreparedStatement musicCDPstm = conn.prepareStatement(musicCDSql);
			
			productPstm.setString(1, id);
			musicCDPstm.setString(1, id);
			
			productPstm.executeUpdate();
			musicCDPstm.executeUpdate();
			flag = true;
			
			conn.close();
			productPstm.close();
			musicCDPstm.close();
		}catch (Exception e) {
			e.printStackTrace();
		}
		return flag;
	}


	@Override
	public MusicCD update(MusicCD musicCD) {
		try {
			Connection conn = ConnectionUtils.getConnection();
			String productSql = "update sanpham set MaSp = ?, Ten = ?, SoLuong = ?, GiaBan = ?, GiaMua = ? " +
							    "where MaSp = ?;";
			String musicCDSql = "	update dianhac set MaDiaNhac = ?, TenCaSi = ?, TheLoai = ? " + 
							   "where MaDiaNhac = ?"
							   ;
			PreparedStatement productPstm = conn.prepareStatement(productSql);
			PreparedStatement musicCDPstm = conn.prepareStatement(musicCDSql);
			
			productPstm.setString(1, musicCD.getIdProduct());
			productPstm.setString(2, musicCD.getNameProduct());
			productPstm.setInt(3, musicCD.getQuantity());
			productPstm.setDouble(4, musicCD.getOutPrice());
			productPstm.setDouble(5, musicCD.getInPrice());
			productPstm.setString(6, musicCD.getIdProduct());
			
			musicCDPstm.setString(1, musicCD.getIdProduct());
			musicCDPstm.setString(2, musicCD.getSingerName());
			musicCDPstm.setString(3, musicCD.getType());
			musicCDPstm.setString(4, musicCD.getIdProduct());
			
			productPstm.executeUpdate();
			musicCDPstm.executeUpdate();
		}catch (Exception e) {
			e.printStackTrace();
		}
		return musicCD;
	}


	@Override
	public MusicCD findOne(String id) {
		MusicCD product = null;
		
		try {
			Connection conn = ConnectionUtils.getConnection();
			String sql = "select * from dianhac inner join sanpham on dianhac.MaDiaNhac =  sanpham.MaSP "+
						 "where dianhac.MaDiaNhac = ?;";
			PreparedStatement pstm = conn.prepareStatement(sql);
			
			pstm.setString(1, id);
			ResultSet rs = pstm.executeQuery();
			while(rs.next()) {
				product = new MusicCD(rs.getString("MaSP"), 
										 rs.getString("Ten"), 
										 rs.getInt("SoLuong"), 
										 rs.getDouble("GiaBan"), 
										 rs.getDouble("GiaMua"),
										 rs.getString("TenCaSi"),
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
}
