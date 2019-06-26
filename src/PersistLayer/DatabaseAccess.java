package PersistLayer;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseAccess {
	
	 static final String DRIVE_NAME = "com.mysql.jdbc.Driver";
	 static final String CONNECTION_URL = "jdbc:mysql://localhost:3306/employees";
	 static final String DB_CONNECTION_USERNAME = "";
	 static final String DB_CONNECTION_PASSWORD = "";
	
	 /**
	  * @author sahilpatel
	  * connects to mysql database
	  * @return
	  */
	 public static Connection connect()
	    {
	        Connection con = null;
	        try
	        {
	            Class.forName(DRIVE_NAME);
	            con = DriverManager.getConnection(CONNECTION_URL, DB_CONNECTION_USERNAME, DB_CONNECTION_PASSWORD);
	        }
	        catch (ClassNotFoundException e)
	        {
	            e.printStackTrace();
	        }
	        catch (SQLException e)
	        {
	            e.printStackTrace();
	        }
	        return con;
	    }
	 
	 /**
	  * @author sahilpatel
	  * returns a ResultSet with the result of sql query
	  * @param con
	  * @param query
	  * @return
	  */
	 public static ResultSet retrieve(Connection con, String query)
	    {
	        ResultSet rset = null;
	        try
	        {
	            Statement stmt = con.createStatement();
	            rset = stmt.executeQuery(query);
	            return rset;
	        }
	        catch (SQLException e)
	        {
	            e.printStackTrace();
	        }
	        return rset;
	    }
	 
	 /**
	  * @author sahilpatel
	  * closes connection of mysql database
	  * @param con
	  */
	 public static void closeConnection(Connection con)
	    {
	        try
	        {
	            if (con != null)
	            {
	                con.close();
	            }
	        }
	        catch (SQLException e)
	        {
	            e.printStackTrace();
	        }
	    }
	

}
