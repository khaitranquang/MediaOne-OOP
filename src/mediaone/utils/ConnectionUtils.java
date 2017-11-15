package mediaone.utils;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 * Class de lay ket noi toi database va dong ket noi
 * @author Chu lun Kute
 *
 */
public class ConnectionUtils {
private static Connection connection;
	
	/**
	 * method de lay ket noi toi database
	 * @return mot connection
	 */
	public static Connection getConnection() {
		String url;
		String username;
		String password;
		Properties properties = new Properties();

		try {
			properties.load(Thread.currentThread().getContextClassLoader()
                    		.getResourceAsStream("dataconfig.properties"));
			url = properties.getProperty("url");
			username = properties.getProperty("username");
			password = properties.getProperty("password");
			
			DriverManager.registerDriver(new com.mysql.jdbc.Driver());
			connection = DriverManager.getConnection(url, username, password);
		} catch (IOException | SQLException e) {
			e.printStackTrace();
		}
		return connection;
	}
	
	/**
	 * dong ket noi toi database
	 */
	public static void closeConnection() {
		try {
			if (connection != null) {
				connection.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
