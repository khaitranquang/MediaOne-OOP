package mediaone.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import mediaone.model.Bill;
import mediaone.model.DetailBill;
import mediaone.model.Product;
import mediaone.utils.ConnectionUtils;

public class BillRepositoryImpl implements BillRepository {

	@Override
	public Bill add(Bill bill) {
		try {
			Connection conn = ConnectionUtils.getConnection();
			
			String billSql = "insert into hoadon(MaNhanVien, NgayXuat) values (?,?);";
			PreparedStatement billPstm = conn.prepareStatement(billSql, PreparedStatement.RETURN_GENERATED_KEYS);
			billPstm.setString(1, bill.getIdStaff());
			billPstm.setString(2, bill.getExportDate().toString());
			billPstm.executeUpdate();
			ResultSet rs = billPstm.getGeneratedKeys();
		    if (rs.next()){
		            bill.setIdBill(rs.getInt(1));
		    }		
			
			String detailBillSql = "insert into chitiethoadon values (?,?,?);";	
			PreparedStatement detailBillPstm = conn.prepareStatement(detailBillSql);
			List<Product> products = bill.getDetailBill().getProducts();
			for (Product product : products) {
				detailBillPstm.setInt(1, bill.getIdBill());
				detailBillPstm.setString(2, product.getIdProduct());
				detailBillPstm.setInt(3, product.getQuantity());
				detailBillPstm.executeUpdate();

			}
			
			conn.close();
			billPstm.close();
			detailBillPstm.close();
		}catch (Exception e) {
			e.printStackTrace();
		}
		return bill;
	}
	
	@Override
	public List<Bill> findAll() {
		List<Bill> bills = new ArrayList<>();
		try {
			Connection conn = ConnectionUtils.getConnection();
			String billSql = "select * from hoadon;";
			String detailSql = "select * from chitiethoadon inner join sanpham on chitiethoadon.MaSanPham = sanpham.MaSP "
							+ " where chitiethoadon.MaHoaDon = ?;";

			Statement statement = conn.createStatement();
			ResultSet rs = statement.executeQuery(billSql);
			while(rs.next()) {
				bills.add(new Bill(rs.getInt("MaHoaDon"), 
								   rs.getString("MaNhanVien"), 
								   LocalDate.parse(rs.getString("NgayXuat"))));
				
			}
			for (Bill bill : bills) {
				PreparedStatement pstm = conn.prepareStatement(detailSql);
				pstm.setInt(1, bill.getIdBill());
				rs = pstm.executeQuery();
				List<Product> products = new ArrayList<>();
				while(rs.next()) {
					products.add(new Product(rs.getString("MaSP"), 
											 rs.getString("Ten"), 
											 rs.getInt("SoLuong"), 
											 rs.getDouble("GiaBan"), 
											 rs.getDouble("GiaMua")));
					
				}
				DetailBill detailBill = new DetailBill();
				detailBill.setProducts(products);
				bill.setDetailBill(detailBill);
			}
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		return bills;
	}
	
	@Override
	public List<Bill> findByExportDayBetween(LocalDate startDate, LocalDate endDate) {
		return findAll().stream()
						.filter(e -> checkBetween(e.getExportDate(), startDate, endDate))
						.collect(Collectors.toList());
	}
	
	private boolean checkBetween(LocalDate date, LocalDate startDate, LocalDate endDate) {
		if (date.isEqual(startDate) || date.isEqual(endDate)) {
			return true;
		}
		return date.isAfter(startDate) && date.isBefore(endDate);
	}

	@Override
	public boolean removeByID(int id) {
		boolean flag = false;
		try {
			Connection conn = ConnectionUtils.getConnection();
			String billSql = "delete from hoadon where MaHoaDon = ?;";
			PreparedStatement billPstm = conn.prepareStatement(billSql);
			billPstm.setInt(1, id);
			billPstm.executeUpdate();
			
			String detailBill = "delete from chitiethoadon where MaHoaDon = ?;";
			PreparedStatement detailPstm = conn.prepareStatement(detailBill);
			detailPstm.setInt(1, id);
			detailPstm.executeUpdate();
			
			flag = true;
			conn.close();
			detailPstm.close();
			billPstm.close();
		}catch (Exception e) {
			e.printStackTrace();
		}
		return flag;
	}
}
