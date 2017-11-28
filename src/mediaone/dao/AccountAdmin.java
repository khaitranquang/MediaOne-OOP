package mediaone.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;

import mediaone.utils.ConnectionUtils;

public class AccountAdmin {
	
	public boolean updatePass(String newpass) {
		Connection conn = ConnectionUtils.getConnection();
		PreparedStatement preStatement = null;
		
		try {
			String sql = "UPDATE admin SET password=? WHERE account=?";
			preStatement= conn.prepareStatement(sql);
			preStatement.setString(1, newpass);
			preStatement.setString(2, "admin");
			
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
}
