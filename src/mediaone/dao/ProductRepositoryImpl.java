package mediaone.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import mediaone.model.Product;
import mediaone.utils.ConnectionUtils;

public class ProductRepositoryImpl implements ProductRepository<Product> {

	@Override
	public List<Product> findAll() {
		List<Product> products = new ArrayList<>();
		try {
			Connection conn = ConnectionUtils.getConnection();
			String sql = "select * from sanpham;";
			Statement statement = conn.createStatement();
			ResultSet rs = statement.executeQuery(sql);
			while (rs.next()) {
				products.add(new Product(rs.getString("MaSP"), 
										 rs.getString("Ten"), 
										 rs.getInt("SoLuong"), 
										 rs.getDouble("GiaBan"),
										 rs.getDouble("GiaMua")));
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
	public Product add(Product product) {
		try {
			Connection conn = ConnectionUtils.getConnection();
			String productSql = "insert into sanpham values (?,?,?,?,?);";
			PreparedStatement productPstm = conn.prepareStatement(productSql);

			productPstm.setString(1, product.getIdProduct());
			productPstm.setString(2, product.getNameProduct());
			productPstm.setInt(3, product.getQuantity());
			productPstm.setDouble(4, product.getOutPrice());
			productPstm.setDouble(5, product.getInPrice());
			
			productPstm.executeUpdate();
			
			conn.close();
			productPstm.close();
		}catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public boolean removeByID(String id) {
		boolean flag = false;
		try {
			Connection conn = ConnectionUtils.getConnection();
			String productSql = "delete from sanpham where MaSP = ?";
			PreparedStatement productPstm = conn.prepareStatement(productSql);
			
			productPstm.setString(1, id);
			productPstm.executeUpdate();
			
			conn.close();
			productPstm.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
		return flag;
	}

	@Override
	public Product update(Product product) {
		try {
			Connection conn = ConnectionUtils.getConnection();
			String productSql = "update sanpham set MaSp = ?, Ten = ?, SoLuong = ?, GiaBan = ?, GiaMua = ? " +
							    "where MaSp = ?;";
			PreparedStatement productPstm = conn.prepareStatement(productSql);
		
			productPstm.setString(1, product.getIdProduct());
			productPstm.setString(2, product.getNameProduct());
			productPstm.setInt(3, product.getQuantity());
			productPstm.setDouble(4, product.getOutPrice());
			productPstm.setDouble(5, product.getInPrice());
			productPstm.setString(6, product.getIdProduct());
					
			productPstm.executeUpdate();
					
			conn.close();
			productPstm.close();
		}catch (Exception e) {
			e.printStackTrace();
		}
		return product;
	}

	@Override
	public Product findOne(String id) {
		Product product = new Product();
		
		try {
			Connection conn = ConnectionUtils.getConnection();
			String sql = "select * from sanpham where MaSP = ?";
			PreparedStatement pstm = conn.prepareStatement(sql);
			pstm.setString(1, id);
			
			ResultSet rs = pstm.executeQuery();
			while(rs.next()) {
				product = new Product(rs.getString("MaSP"), 
										 rs.getString("Ten"), 
										 rs.getInt("SoLuong"), 
										 rs.getDouble("GiaBan"), 
										 rs.getDouble("GiaMua"));
				
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
