package com.capgemini.go.utility;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import com.capgemini.go.exception.DatabaseException;

public class DbConnection {

	private static Properties props = null;
	private static Properties exceptionProps = null;
	private static Connection connection = null;

	private static DbConnection instance = null;
	private static final String DATABASE_PROPERTIES_FILE = "jdbc.properties";
	private static final String EXCEPTION_PROPERTIES_FILE = "exceptionStatement.properties";

	public static Connection getConnection() {
		Connection conn = null;
		try {
			conn = createConnectionToDatabase();
		} catch (DatabaseException e) {
			
			System.out.println(Constants.connErr);
		}
		return conn;
	}

	public static void setConnection(Connection connection) {
		DbConnection.connection = connection;
	}

	/*************************************************************************************
	 * - @throws DatabaseException - Private Constructor - Author : CAPGEMINI -
	 * Creation Date : 23/09/2019 - Desc:Loads the jdbc.properties file, exception
	 * properties file and gets the connection
	 ***************************************************************************************/

	private DbConnection() throws DatabaseException {
		try {
			props = PropertiesLoader.loadProperties(DATABASE_PROPERTIES_FILE);
			exceptionProps = PropertiesLoader.loadProperties(EXCEPTION_PROPERTIES_FILE);
			connection = createConnectionToDatabase();
		} catch (IOException e) {
			// throw new
			// DatabaseException(exceptionProps.getProperty("database_properties_reading_failure"));
			GoLog.logger.error(e.getMessage());
		}

	}

	/*****************************************************************
	 * - Method Name:getInstance() - Input Parameters : - Return Type :DbConnection
	 * instance - Throws : Database Exception - Author : CAPGEMINI - Creation Date :
	 * - Description : Singleton and Thread safe class
	 *******************************************************************/

	public static DbConnection getInstance() throws DatabaseException {
		synchronized (DbConnection.class) {
			if (instance == null) {
				instance = new DbConnection();
			}
		}
		
		return instance;
	}

	/*****************************************************************
	 * - Method Name:createConnectionToDatabase - Input Parameters : - Return Type
	 * :Connection connection - Throws : DatabaseException - Author : CAPGEMINI -
	 * Creation Date : 23/09/2019 - Description : Establishing a connection with
	 * database
	 *******************************************************************/

	public static Connection createConnectionToDatabase() throws DatabaseException {
		try {
		props = PropertiesLoader.loadProperties(DATABASE_PROPERTIES_FILE);
		String url = props.getProperty("dburl");
		String username = props.getProperty("username");
		String password = props.getProperty("password");
		String driver = props.getProperty("driver");
			
			Class.forName(driver);
			Connection conn = DriverManager.getConnection(url, username, password);
			if (conn == null) {
				throw new DatabaseException(exceptionProps.getProperty("database_connection_failure"));
			}
			
			return conn;
		} catch (SQLException | IOException | ClassNotFoundException e) {

			throw new DatabaseException(exceptionProps.getProperty("database_access_error") + " >>> " + e.getMessage());
		}
	}

}
