import java.sql.*;
public class DBConnection {
	private Connection con;
	private Statement st;
	private ResultSet rs;
	public DBConnection(){
		try{
			Class.forName("com.mysql.jdbc.Driver");
			con=DriverManager.getConnection("jdbc:mysql://192.168.250.235:3306/SIGEINM","SIGEINM","SIGEINM");
			st=con.createStatement();
		}catch(Exception ex){
			System.out.println("Error: conexion");
		} 
	}
	public Statement getStatement(){
		return st;
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
	public boolean auteticar(String user,String password){
		try{
			String query="select * from usuarios where usuario='"+user+"' and password=SHA('"+password+"')";
			rs=st.executeQuery(query);
			rs.last();
			if(rs.getRow()==1){
				return true;
			}
			else{
				return false;
			}
		}catch(Exception ex){
			System.out.println(ex);
			return false;
		}
	}
}
