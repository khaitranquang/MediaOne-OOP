package mediaone.dao;

import java.sql.Connection;
import java.sql.SQLException;

public class ConnectManager {
	protected static Connection connect;
	public void setConnect(Connection connect) {
		ConnectManager.connect = connect;
	}
	public void closeConnect() {
		try {
			ConnectManager.connect.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
