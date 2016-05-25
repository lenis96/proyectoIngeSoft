import java.sql.*;
public class DataBaseConnection {
	private String user="SIGEINM";
	private String password="SIGEINM";
	private String db="sigeinm";
	private String host="127.0.0.1";
	private Connection connection;
	public DataBaseConnection() {
		try{
	         Class.forName("com.mysql.jdbc.Driver");
	         connection = DriverManager.getConnection( "jdbc:mysql://"+host+"/"+db,user,password);         
	      }catch(SQLException e){
	         System.err.println( e.getMessage() );
	      }catch(ClassNotFoundException e){
	         System.err.println( e.getMessage() );
	      }
	}
	public Connection getConnection(){
		return connection;
	}
}
