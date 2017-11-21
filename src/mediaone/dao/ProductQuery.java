package mediaone.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import mediaone.model.Product;
import mediaone.utils.ConnectionUtils;
/**
 * This is ProductQuery class, which will use for access into
 * product table ("sanpham" table) in database
 * @author Jenda
 *
 */
public class ProductQuery {
	private static List<Product> lsProduct;
	private Statement stmt;
	private PreparedStatement pstmt;
	private String query;
	private static Connection con;
	
	public static void setConnection(Connection con) {
		ProductQuery.con = con;
	}

	public List<Product> readProduct(){
		lsProduct = new ArrayList<>();
		try {
			stmt = con.createStatement();
			query =  "Select * from sanpham";
			ResultSet result = stmt.executeQuery(query);
			while(result.next()) {
				String id,name;
				int qtt;
				Double inP, outP;
				id = result.getString(1);
				name = result.getString(2);
				qtt = result.getInt(3);
				outP = result.getDouble(4);
				inP = result.getDouble(5);
				Product product = new Product(id, name, qtt, outP, inP) {};
				lsProduct.add(product);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if(stmt != null)
				try {
					stmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
		}
		return lsProduct;
	}
	
	public void addProduct(List<Product> listP) {
		query = "INSERT INTO sanpham values(?," // idProduct
										 + "?," // nameProduct
										 + "?," // quantity
										 + "?," // outPrice
										 + "?)";// inPrice
		try {
			pstmt = con.prepareStatement(query);
			for (Product product : listP) {
				pstmt.setString(1, product.getIdProduct());
				pstmt.setString(2, product.getNameProduct());
				pstmt.setInt(   3, product.getQuantity());
				pstmt.setDouble(4, product.getOutPrice());
				pstmt.setDouble(5, product.getInPrice());
				
				// Run
				int row = pstmt.executeUpdate();
				System.out.println("Added product already into row " + row);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if(pstmt != null)
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
		}
	}
	
	/**
	 * updateProduct will delete old product where its id
	 * equals new id's product and then add new product to
	 * database
	 * @param product
	 */
	public void updateProduct(Product product) {
	}
	
	public static void main(String[] args) {
		Connection connect = ConnectionUtils.getConnection();
		ProductQuery pQuery = new ProductQuery();
		ProductQuery.setConnection(connect);
		
		List<Product> lsP = new ArrayList<>();
//		Product p1 = new Product("M0001", "Bản Tình Ca Đầu Tiên", 20 , 30000, 27500) {};
//		Product p2 = new Product("M0002", "Bản Tình Ca Thứ Hai", 25 , 35000, 32000) {};
//		lsP.add(p1);lsP.add(p2);
//		pQuery.addProduct(lsP);
//		lsP.clear();
		
		lsP = pQuery.readProduct();
		System.out.printf("%10s%30s%5s%10s","Mã SP", "Tên SP", "SL", "Gia Ban", "Gia Mua");
		for (Product product : lsP) {
			System.out.println();
			System.out.printf("%10s%30s%5s%10s", product.getIdProduct(),
												 product.getNameProduct(),
												 product.getQuantity(),
												 product.getOutPrice(),
												 product.getInPrice());
		}
		ConnectionUtils.closeConnection();
	}
}
