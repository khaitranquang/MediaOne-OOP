package mediaone.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import mediaone.model.Staff;
import mediaone.utils.ConnectionUtils;

public class StaffRepositoryImpl implements StaffRepository{
	
	@Override
	public List<Staff> findAll() {
		Connection conn = ConnectionUtils.getConnection();
		PreparedStatement preStatement;
		List<Staff> list = new ArrayList<Staff>();
		
		try {
			String sql = "SELECT * FROM nhanvien";
			Statement statement = conn.createStatement();
			ResultSet result = statement.executeQuery(sql);
			
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
			statement.close();
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
	public boolean removeByID(String id) {
		boolean flag = false;
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
			flag = true;
			//Close connection
			preStatement.close();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return flag;
	}
	
	@Override
	public Staff update(Staff staff) {
		String newNameStaff = staff.getNameStaff();
		String newPass = staff.getPass();
		Double newSalary = staff.getSalary();
		int newDays = staff.getDays();
		
		Connection conn = ConnectionUtils.getConnection();
		PreparedStatement preStatement = null;
		
		try {
			String sql = "UPDATE nhanvien SET TenNhanVien=?, PassWord=?, LuongCB=?, "
					+ "SoNgay=? WHERE MaNhanVien=?";
			preStatement = conn.prepareStatement(sql);
			preStatement.setString(1, newNameStaff);
			preStatement.setString(2, newPass);
			preStatement.setDouble(3, newSalary);
			preStatement.setInt(4, newDays);
			preStatement.setString(5, staff.getIdStaff());
			
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
	
	@Override
	public boolean changePass(String id, String newpass) {
		Connection conn = ConnectionUtils.getConnection();
		PreparedStatement preStatement = null;
		
		try {
			String sql = "UPDATE nhanvien SET PassWord=? WHERE MaNhanVien=?";
			preStatement= conn.prepareStatement(sql);
			preStatement.setString(1, newpass);
			preStatement.setString(2, id);
			
			int rows = preStatement.executeUpdate();
			if (rows > 0) System.out.println("Updated");
			preStatement.close();
			conn.close();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	@Override
	public String getPassAdmin() {
		Connection conn = ConnectionUtils.getConnection();
		Statement statement = null;
		String passAdmin = "";
		
		try {
			String sql = "SELECT * FROM admin";
			statement = conn.createStatement();
			ResultSet result =statement.executeQuery(sql);
			
			while (result.next()) {
				passAdmin = result.getString("password");
			}
			System.out.println(passAdmin);
			result.close();
			statement.close();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			ConnectionUtils.closeConnection();
		}
		
		return passAdmin;
	}
	
	@Override
	public String getPassEmpl (String idStaff) {
		Connection conn = ConnectionUtils.getConnection();
		String passEmpl = "";
		PreparedStatement preStatement = null;
		String sql = "SELECT * FROM nhanvien WHERE MaNhanVien=?";
		
		try {
			preStatement = conn.prepareStatement(sql);
			preStatement.setString(1, idStaff);
			ResultSet result =preStatement.executeQuery();
			
			while (result.next()) {
				passEmpl = result.getString("PassWord");
			}
			
			result.close();
			preStatement.close();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			ConnectionUtils.closeConnection();
		}
		
		return passEmpl;
	}
	
	@Override
	public void changePassAdmin(String newpass) {
		Connection conn = ConnectionUtils.getConnection();
		PreparedStatement preStatement = null;
		
		try {
			String sql = "UPDATE admin SET password=?";
			preStatement = conn.prepareStatement(sql);
			preStatement.setString(1, newpass);
			
			int rows = preStatement.executeUpdate();
			if (rows > 0) System.out.println("Changed");
			
			preStatement.close();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			ConnectionUtils.closeConnection();
		}
	}

	@Override
	public void initAdmin() {
		Connection conn = ConnectionUtils.getConnection();
		PreparedStatement preStatement = null;
		
		try {
			String sql = "INSERT INTO admin (account, password) VALUES (?, ?)";
			preStatement = conn.prepareStatement(sql);
			preStatement.setString(1, "admin");
			preStatement.setString(2, "1234");
			
			int rows = preStatement.executeUpdate();
			if (rows > 0) System.out.println("Init admin");
			
			preStatement.close();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			ConnectionUtils.closeConnection();
		}
	}
}
