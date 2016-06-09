/*************************************************

Nombre de la clase: DataBaseConnection.java

Última modificación: 06/06/2016

Descripción: Permite la conexión entre la aplicación
	y la base de datos sobre la que se va a trabajar.

*************************************************/
import java.sql.*;
import javax.swing.JOptionPane;

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
	         MostrarError(e);
	      }catch(ClassNotFoundException e){
	         System.err.println( e.getMessage() );
	      }
	}
	public Connection getConnection(){
		//Metodo que retorna la conexion con la base de datos.
		return connection;
	}
	public void MostrarError(SQLException exception){
		JOptionPane.showMessageDialog(null,exception.getMessage());
	}
}
