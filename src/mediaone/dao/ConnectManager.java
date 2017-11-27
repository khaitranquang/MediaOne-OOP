package mediaone.dao;

import java.sql.Connection;
import java.sql.SQLException;

import mediaone.utils.ConnectionUtils;

public class ConnectManager {
	protected static Connection connect;
	public void setConnect() {
		ConnectManager.connect = ConnectionUtils.getConnection();
	}
	public void closeConnect() {
		try {
			ConnectManager.connect.close();
			ConnectionUtils.closeConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
