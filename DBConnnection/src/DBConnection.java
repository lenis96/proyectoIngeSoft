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
	public void getData(){
		try{
			String query="select * from usuarios";
			rs=st.executeQuery(query);
			System.out.println("Records from Database");
			while(rs.next()){
				String user=rs.getString("usuario");
				String password=rs.getString("password");
				String tipo=rs.getString("tipo");
				System.out.println(user+"\t"+password+"\t"+tipo);
			}
		}catch(Exception ex){
			System.out.println(ex);
		}
	}
}
