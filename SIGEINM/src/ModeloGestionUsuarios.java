import java.sql.*;
public class ModeloGestionUsuarios extends DataBaseConnection{
	public ModeloGestionUsuarios() {
		super();
	}
	boolean verificarUsuario(String user,String password){
		try{
			PreparedStatement statement=getConnection().prepareStatement("select count(*) as total from usuarios where usuario='"+user+"' and password=SHA('"+password+"')");
			ResultSet result=statement.executeQuery();
			result.next();
			return (result.getInt("total")==1);
		}catch(Exception ex){
			System.out.println(ex);
			return false;
		}
	}
}
