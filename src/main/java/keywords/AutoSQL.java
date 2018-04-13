//=====================================================================================================
//	Name: Charlie Newcomer
//	Emp#: 102576
//	Contact: Newcomercr1@gmail.com
//=====================================================================================================

package keywords;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AutoSQL {
	private Statement stmt;
	
	/**
	 * This function connects to a database.
	 * 
	 * @throws Exception
	 *             on any errors.
	 * @example initializeDB("jdbc:mysql://localhost/pcparts", "scott", "tiger");
	 */
	 public void initializeDB(String localHost, String userName, String Password) {
		    try {
		      Class.forName("com.mysql.jdbc.Driver");
		      System.out.println("Driver loaded");
		      Connection connection = DriverManager.getConnection
		        (localHost, userName, Password);

		      System.out.println("Database connected");

		      // Create a statement
		      stmt = connection.createStatement();
		    }
		    catch (Exception ex) {
		      ex.printStackTrace();
		    }
		  }
	 /**
		 * This function connects to a database.
		 * 
		 * @throws Exception
		 *             on any errors.
		 * @example 
		 * String query = "SELECT * FROM table_name;"
		 * List<Object> scrape = sql.getQueryResult(query);
		 */
	 public ArrayList<ArrayList<String>> getQueryResult(String query){

         ArrayList<ArrayList<String>> feedback = new ArrayList<ArrayList<String>>();
         ArrayList<String> feed = null;

         try {
             ResultSet rs = stmt.executeQuery(query);

             ResultSetMetaData rsm = rs.getMetaData();
                 feed = new ArrayList<String>();

                 for(int y = 1;y<rsm.getColumnCount();y++){

                     feed.add(rsm.getColumnName(y));
                 }
                 feedback.add(feed);

             while(rs.next()){
                 feed = new ArrayList<String>();
             for(int i=1;i<=rsm.getColumnCount();i++){

                     feed.add(rs.getString(i));
             }
             feedback.add(feed);
         }

         } catch (SQLException e) {
        	 
         }
         return feedback;

     }
	 
}
