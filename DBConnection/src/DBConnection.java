import java.sql.*;
public class DBConnection {
	private Connection con;
	private Statement st;
	private ResultSet rs;
	public DBConnection(){
		try{
			Class.forName("com.mysql.jdbc.Driver");
			con=DriverManager.getConnection("jdbc:mysql://192.168.0.106:3306/SIGEINM","SIGEINM","SIGEINM");
			st=con.createStatement();
		}catch(Exception ex){
			System.out.println("Error: "+ex);
		} 
	}
}
