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
	String getTipoUsuario(String user){
		try{
			PreparedStatement statement=getConnection().prepareStatement("select tipo from usuarios where usuario='"+user+"'");
			ResultSet result=statement.executeQuery();
			result.next();
			return result.getString("tipo");
		}catch(Exception ex){
			return "N";
		}
	}
}
