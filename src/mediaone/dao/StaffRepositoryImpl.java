package mediaone.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

import mediaone.model.Staff;
import mediaone.utils.ConnectionUtils;

public class StaffRepositoryImpl implements StaffRepository{
	
	@Override
	public List<Staff> findAll() {
		Connection conn = ConnectionUtils.getConnection();
		PreparedStatement preStatement;
		List<Staff> list= null;
		
		try {
			String sql = "SELECT * FROM nhanvien";
			preStatement = conn.prepareStatement(sql);
			ResultSet result = preStatement.executeQuery();
			
			while (result.next()) {
				list.add(new Staff(
						result.getString("MaNhanVien"),
						result.getString("TenNhanVien"),
						result.getString("PassWord"),
						result.getDouble("LuongCB"),
						result.getInt("SoNgay")
					));
			}
			
			result.close();
			preStatement.close();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return list;
	}
	
	@Override
	public Staff findOne(String id) {
		Staff staff = null;
		Connection conn = ConnectionUtils.getConnection();
		PreparedStatement preStatement = null;
		String sql = "SELECT * FROM nhanvien WHERE MaNhanVien = ?";
		
		try {
			preStatement= conn.prepareStatement(sql);
			preStatement.setString(1, id);
			ResultSet result = preStatement.executeQuery();
			
			while (result.next()) {
				String nameStaff = result.getString("TenNhanVien");
				String pass = result.getString("PassWord");
				Double salary = result.getDouble("LuongCB");
				int days = result.getInt("SoNgay");
				
				staff= new Staff(id, nameStaff, pass, salary, days);
			}
			
			//Close connection
			result.close();
			preStatement.close();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return staff;
	}
	
	public Staff add(Staff staff) {
		String idStaff = staff.getIdStaff();
		String nameStaff = staff.getNameStaff();
		String pass = staff.getPass();
		Double salary = staff.getSalary();
		int days = staff.getDays();
		Connection conn = ConnectionUtils.getConnection();
		PreparedStatement preStatement = null;
		
		try {
			String sql = "INSERT INTO nhanvien (MaNhanVien, TenNhanVien, PassWord, LuongCB, SoNgay)"
					+ " VALUES (?,?,?,?,?)";
			preStatement = conn.prepareStatement(sql);
			preStatement.setString(1, idStaff);
			preStatement.setString(2, nameStaff);
			preStatement.setString(3, pass);
			preStatement.setDouble(4, salary);
			preStatement.setInt(5, days);
			
			int rows = preStatement.executeUpdate();
			if (rows > 0) System.out.println("Inserted");
			
			// Close connection
			preStatement.close();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return staff;
	}
	
	@Override
	public Staff removebyid(String id) {
		Connection conn = ConnectionUtils.getConnection();
		PreparedStatement preStatement = null;
		Staff staff = null;
		staff = findOne(id);
		
		try {
			String sql = "DELETE FROM nhanvien WHERE MaNhanVien = ?";
			preStatement = (PreparedStatement) conn.prepareStatement(sql);
			preStatement.setString(1, id);
			
			int rows = preStatement.executeUpdate();
			if (rows > 0) System.out.println("Deleted");
			
			//Close connection
			preStatement.close();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return staff;
	}
	
	@Override
	public Staff update(Staff staff) {
		String idStaff = staff.getIdStaff();
		String newnameStaff = staff.getNameStaff();
		String newpass = staff.getPass();
		Double newsalary = staff.getSalary();
		int newdays = staff.getDays();
		
		Connection conn = ConnectionUtils.getConnection();
		PreparedStatement preStatement = null;
		
		try {
			String sql = "UPDATE nhanvien SET TenNhanVien=?, PassWord=?, LuongCB=?, "
					+ "SoNgay=? WHERE MaNhanVien=?";
			preStatement = conn.prepareStatement(sql);
			preStatement.setString(1, newnameStaff);
			preStatement.setString(2, newpass);
			preStatement.setDouble(3, newsalary);
			preStatement.setInt(4, newdays);
			preStatement.setString(5, idStaff);
			
			int rows = preStatement.executeUpdate();
			if (rows > 0) System.out.println("Updated");
			preStatement.close();
			conn.close();
			return staff;
			
			
		} catch (Exception e) {
			e.printStackTrace();
			return null;	
		}
	}
}
