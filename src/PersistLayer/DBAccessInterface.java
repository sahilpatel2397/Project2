package PersistLayer;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

class DBAccessInterface {

	/**
	 * @author sahilpatel
	 * interface mehtod used to connect to database
	 * @return
	 */
	public static Connection connect() {
		 
		Connection con = DatabaseAccess.connect();
		return con;
	}
	
	/**
	 * @author sahilpatel
	 * returns a resultset of the query result
	 * @param query
	 * @return
	 * @throws SQLException
	 */
	public static ResultSet retrieve (String query) throws SQLException {
			
		Connection con = DatabaseAccess.connect();
		Statement stmt = null;
		stmt = con.createStatement();
			
		ResultSet rs = stmt.executeQuery(query);
		return rs;
	}

	/**
	 * @author sahilpatel
	 * Creates a database or anything additional in the ddatabase
	 * @param query
	 * @return
	 * @throws SQLException
	 */
	public static int create (String query) throws SQLException {

	     java.sql.PreparedStatement preparedStmt = null;

	     try
	     {
	    	 Connection con = DatabaseAccess.connect();
	    	 preparedStmt = con.prepareStatement(query);
	    	 
	    	 
	    	 
	    	 preparedStmt.executeUpdate();
	     }
	     catch (SQLException e)
	     {
	    	 
	    	 System.out.println(e.getMessage());
	    	 
	     }
	     finally
	     {
	    	 if (preparedStmt != null)
	    	 {
	    		 preparedStmt.close();
	    	 }	
	    	 
	     }	
	     return 0;
		}	
		
		/**
		 * @author sahilpatel
		 * Updates the database given the query
		 * @param query
		 * @return
		 */
		public static int update (String query) {
			return 0;
		}
		
		/**
		 * @author sahilpatel
		 * Deletes a specific thing from the database
		 * @param query
		 * @return
		 * @throws SQLException
		 */
		public static int delete (String query) throws SQLException {

        java.sql.PreparedStatement preparedStmt = null;

	        try
	        {
	            Connection con = DatabaseAccess.connect();
	            preparedStmt = con.prepareStatement(query);	            
	            preparedStmt.executeUpdate();
	        }
	        catch (SQLException e)
	        {	

	        	System.out.println(e.getMessage());

	        }
	        finally
	        {
	            if (preparedStmt != null)
	            {
	                preparedStmt.close();
	            }
	           
	        }
	        return 0;
		}
		
		public static void disconnect(Connection con) {
		}
	
	
	
}
