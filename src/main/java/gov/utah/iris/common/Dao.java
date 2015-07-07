package gov.utah.iris.common;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

/**
 * Database Access Object.
 * @author HNGUYEN
 *
 */
public class Dao {

	private Connection connection;
	private Statement statement;
	
	public Dao() throws Exception {
		getConnection();
		statement = connection.createStatement();		
	}
	
	public ResultSet executeQuery(String sql) throws Exception {
		
		return statement.executeQuery(sql);
	}
	
	public int executeUpdate(String sql) throws Exception {
		
		return statement.executeUpdate(sql);
	}
	
	public void addBatch(String sql) throws Exception {
		
		statement.addBatch(sql);
	}
	
	public void executeBatch() throws Exception {
		
		statement.executeBatch();
	}
	
	public void closeConnection() throws Exception {
		connection.close();
		connection = null;
	}
	
	private void getConnection() throws Exception {
		
        Context ctx = new InitialContext();
        DataSource ds = (DataSource) ctx.lookup(Constants.DATASOURCE);
        connection = ds.getConnection();
	}
	
	public Connection getPostalConnection() throws Exception{
		Context ctx = new InitialContext();
		DataSource ds = (DataSource) ctx.lookup(Constants.POSTAL_DATASOURCE);
        Connection conn = ds.getConnection();
        
        
        return conn;
        
	}
}
