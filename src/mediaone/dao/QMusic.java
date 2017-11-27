package mediaone.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import mediaone.model.MusicCD;
import mediaone.model.Product;

public class QMusic extends ConnectManager implements IDataAccess <MusicCD>{
	private static List<Product> lsP;
	private static List<MusicCD> lsM;
	
	@Override
	public List<MusicCD> loadProduct() {
		lsM = new ArrayList<>();
		String query;
		Statement stmt;
		try {
			query = "SELECT * FROM dianhac";
			stmt = connect.createStatement();
			List<Product> lsP = listProduct();
			ResultSet rs = stmt.executeQuery(query);
			
			String idM;
			while(rs.next()) {
				idM = rs.getString(1);
				for (Product product : lsP) {
					if(product.getIdProduct().equals(idM)) {
						MusicCD mCD = new MusicCD(idM, product.getNameProduct(), product.getQuantity(), product.getOutPrice(),
								product.getInPrice(), rs.getString(2), rs.getString(3));
						lsM.add(mCD);
					}
				}
			}
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, e);
		}
		return lsM;
	}

	@Override
	public String addProduct(MusicCD musicCD) {
		String query, query1, result = "";
		int row = 0, row1 = 0;
		PreparedStatement pstmt = null, pstmt1 = null;
		query = "INSERT INTO sanpham values(?,?,?,?,?)";
		query1 = "INSERT INTO dianhac values(?,?,?)";
		try {
			pstmt = connect.prepareStatement(query);
			pstmt.setString(1, musicCD.getIdProduct());
			pstmt.setString(2, musicCD.getNameProduct());
			pstmt.setInt(3, musicCD.getQuantity());
			pstmt.setDouble(4, musicCD.getOutPrice());
			pstmt.setDouble(5, musicCD.getInPrice());
			
			row = pstmt.executeUpdate();
			if(row != 0) {
				pstmt1 = connect.prepareStatement(query1);
				pstmt1.setString(1, musicCD.getIdProduct());
				pstmt1.setString(2, musicCD.getSingerName());
				pstmt1.setString(3, musicCD.getType());
				
				row1 = pstmt1.executeUpdate();
				if(row1 != 0) {
					result = "Added music CD successfull";
				} else {
					query = "DELETE FROM sanpham WHERE MaSP = \'" + musicCD.getIdProduct() +"\'";
					pstmt.execute(query);
					result = "Cannot add this music CD, try again";
				}
			}
		} catch (SQLException e) {
			String msg = e.getMessage();
			if(msg.substring(0, 9).toLowerCase().equals("duplicate")) {
				JOptionPane.showMessageDialog(null, "The Music CD have just been existed already");
			} else {
				JOptionPane.showMessageDialog(null, e);
			}
		} finally {
			if(pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					JOptionPane.showMessageDialog(null, e);
				}
			}
			if(pstmt1 != null) {
				try {
					pstmt1.close();
				} catch (SQLException e) {
					JOptionPane.showMessageDialog(null, e);
				}
			}
		}
		return result;
	}

	@Override
	public String updateProduct(MusicCD product) {
		String result = "";
		List<MusicCD> lsMusic = loadProduct();
		int flag = 0;
		for (MusicCD musicCD : lsMusic) {
			if(musicCD.getIdProduct().equals(product.getIdProduct())){
				delProductID(product.getIdProduct());
				addProduct(product);
				flag = 1;
				break;
			}
		}
		if(flag == 0) {
			result = "The Music CD cannot update";
		} else {
			result = "The Music CD have been update already";
		}
		return result;
	}

	@Override
	public List<Product> listProduct() {
		lsP = new ArrayList<>();
		String query = "SELECT * FROM sanpham WHERE MaSP LIKE \'M%\'";
		Statement stmt = null;
		try {
			stmt = connect.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			while(rs.next()) {
				Product p = new Product(rs.getString(1), rs.getString(2), rs.getInt(3), rs.getDouble(4), rs.getDouble(5));
				lsP.add(p);
			}
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, e);
		} finally {
			if(stmt != null) {
				try {
					stmt.close();
				} catch (SQLException e) {
					JOptionPane.showMessageDialog(null, e);
				}
			}
		}
		return lsP;
	}

	@Override
	public String delProductID(String idMusic) {
		String result = "";
		int row = 0;
		Statement stmt = null;
		if(hasProductID(idMusic)) {
			String query = "DELETE FROM dianhac WHERE MaDiaNhac = \'" + idMusic +"\'";
			String query1 = "DELETE FROM sanpham WHERE MaSP = \'" + idMusic +"\'";
			try {
				stmt = connect.createStatement();
				row = stmt.executeUpdate(query);
				if(row != 0) {
					stmt.executeUpdate(query1);
					result = "1 .MusicCD hava just been removed out of database";
				}
			} catch (SQLException e) {
				JOptionPane.showMessageDialog(null, e);
			} finally {
				if(stmt != null ) {
					try {
						stmt.close();
					} catch (SQLException e) {
						JOptionPane.showMessageDialog(null, e);
					}
				}
			}
		} else {
			result = "0 .This Music CD that you input is not exist!";
		}
		return result;
	}

	@Override
	public boolean hasProductID(String idProduct) {
		Boolean result = false;
		List<MusicCD> lsMusic = loadProduct();
		for (MusicCD musicCD : lsMusic) {
			if(musicCD.getIdProduct().equals(idProduct)) {
				result = true;
				break;
			}
		}
		return result;
	}

}
