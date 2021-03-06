import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.table.DefaultTableModel;

public class ModeloInventarios extends DataBaseConnection{
	String usuario;
	public ModeloInventarios(String usuario) {
		super();
		this.usuario=usuario;
	}
	public DefaultTableModel obtenerInventarios(){
		DefaultTableModel tableModel=new DefaultTableModel();
		int registros=0;
		String[] columNames={"ID INVENTARIO","ID INMUEBLE","DIRECCION"};
		try{
			PreparedStatement statement=getConnection().prepareStatement("select count(*) as total from inmuebles join inventarios on(inmuebles.id=inventarios.idInmueble and inmuebles.idUsuario=?)");
			statement.setString(1, usuario);
			
			ResultSet respuesta=statement.executeQuery();
			respuesta.next();
			registros=respuesta.getInt("total");
			respuesta.close();
		}catch(SQLException exception){
			System.err.println(exception.getMessage());
		}
		Object [][] data=new String[registros][3];
		try{
			PreparedStatement statement=getConnection().prepareStatement("select inventarios.id,inmuebles.id,inmuebles.direccion as total from inmuebles join inventarios on(inmuebles.id=inventarios.idInmueble and inmuebles.idUsuario=?)");
			statement.setString(1, usuario);
			ResultSet respuesta=statement.executeQuery();
			int i=0;
			while(respuesta.next()){
				data[i][0]=respuesta.getString("inventarios.id");
				data[i][1]=respuesta.getString("inmuebles.id");
				data[i][2]=respuesta.getString(3);
				i++;
			}
			respuesta.close();
			tableModel.setDataVector(data, columNames);
		}catch(SQLException exception){
			System.err.println(exception.getMessage());
		}
		return tableModel;
	}
	String getDireccionInmueble(int idInmueble){
		String direccion="";
		try{
			PreparedStatement statement=getConnection().prepareStatement("select direccion from inmuebles where id=?");
			statement.setInt(1, idInmueble);
			ResultSet respuesta=statement.executeQuery();
			respuesta.next();
			direccion=respuesta.getString("direccion");
		}catch(SQLException exception){
			MostrarError(exception);
		}
		return direccion;
	}
	String getNumHabitaciones(int idInmueble){
		String habitaciones="";
		try{
			PreparedStatement statement=getConnection().prepareStatement("select habitaciones from inmuebles where id=?");
			statement.setInt(1, idInmueble);
			ResultSet respuesta=statement.executeQuery();
			respuesta.next();
			habitaciones=respuesta.getString("habitaciones");
		}catch(SQLException exception){
			System.err.println(exception.getMessage());
		}
		return habitaciones;
	}
	String [] obtenerEspaciosDefault(){
		String[] espacios;
		int registros=0;
		try{
			PreparedStatement statemenet=getConnection().prepareStatement("select count(*) as total from espaciosdefault");
			ResultSet respuesta=statemenet.executeQuery();
			respuesta.next();
			registros=respuesta.getInt("total");
			respuesta.close();
			
		}catch(SQLException exception){
			System.err.println(exception.getMessage());
		}
		espacios=new String[registros];
		try{
			PreparedStatement statement=getConnection().prepareStatement("select prural from espaciosdefault");
			ResultSet respuesta=statement.executeQuery();
			int i=0;
			while(respuesta.next()){
				espacios[i]=respuesta.getString(1);
				i++;
			
			}
			statement.close();
		}catch(SQLException exception){
			System.err.println(exception.getMessage());
		}
		return espacios;
	}
	public DefaultTableModel obtenerInstanciasInventarios(int idInmueble){
		DefaultTableModel tableModel=new DefaultTableModel();
		int registros=0;
		String[] columNames={"ESPACIO","NUMERO"};
		try{
			PreparedStatement statement=getConnection().prepareStatement("select count(*) as total from inmuebles join inventarios on(inmuebles.id=inventarios.idInmueble and inmuebles.id=?) join espaciosInventario on(espaciosInventario.idInventario=inventarios.id)");
			statement.setInt(1, idInmueble);
			
			ResultSet respuesta=statement.executeQuery();
			respuesta.next();
			registros=respuesta.getInt("total");
			respuesta.close();
		}catch(SQLException exception){
			System.err.println(exception.getMessage());
		}
		Object [][] data=new String[registros][2];
		try{
			PreparedStatement statement=getConnection().prepareStatement("select espaciosdefault.descripcion,espaciosinventario.instancia from inmuebles join inventarios on(inmuebles.id=inventarios.idInmueble and inmuebles.id=?) join espaciosInventario on(espaciosInventario.idInventario=inventarios.id) join espaciosdefault on(espaciosInventario.idEspacio=espaciosdefault.id)");
			statement.setInt(1, idInmueble);
			ResultSet respuesta=statement.executeQuery();
			int i=0;
			while(respuesta.next()){
				data[i][0]=respuesta.getString(1);
				data[i][1]=respuesta.getString(2);
				i++;
			}
			respuesta.close();
			tableModel.setDataVector(data, columNames);
		}catch(SQLException exception){
			System.err.println(exception.getMessage());
		}
		return tableModel;
	}
	String getEspacio(int idEspacio){
		String espacio="";
		try{
			PreparedStatement statement=getConnection().prepareStatement("select descripcion from espaciosdefault where id=?");
			statement.setInt(1, idEspacio);
			ResultSet respuesta=statement.executeQuery();
			respuesta.next();
			espacio=respuesta.getString(1);
		}catch(SQLException exception){
			MostrarError(exception);
		}
		return espacio;
	}
	String [] obtenerObjetosEspacio(int idEspacio){
		String[] objetos;
		int registros=0;
		try{
			PreparedStatement statemenet=getConnection().prepareStatement("select count(*)as total from objetos join objetosdefault on(objetos.id=objetosdefault.idObjeto and objetosdefault.idEspacio=?)");
			statemenet.setInt(1,idEspacio);
			ResultSet respuesta=statemenet.executeQuery();
			respuesta.next();
			registros=respuesta.getInt("total");
			respuesta.close();
			
		}catch(SQLException exception){
			System.err.println(exception.getMessage());
		}
		objetos=new String[registros];
		try{
			PreparedStatement statement=getConnection().prepareStatement("select objetos.descripcion from objetos join objetosdefault on(objetos.id=objetosdefault.idObjeto and objetosdefault.idEspacio=?) order by id");
			statement.setInt(1, idEspacio);
			ResultSet respuesta=statement.executeQuery();
			int i=0;
			while(respuesta.next()){
				objetos[i]=respuesta.getString(1);
				i++;
			
			}
			statement.close();
		}catch(SQLException exception){
			System.err.println(exception.getMessage());
		}
		return objetos;
	}
	int [] obtenerIds(int idEspacio){
		int[] indices;
		int registros=0;
		try{
			PreparedStatement statemenet=getConnection().prepareStatement("select count(*)as total from objetos join objetosdefault on(objetos.id=objetosdefault.idObjeto and objetosdefault.idEspacio=1)");
			ResultSet respuesta=statemenet.executeQuery();
			respuesta.next();
			registros=respuesta.getInt("total");
			respuesta.close();
			
		}catch(SQLException exception){
			System.err.println(exception.getMessage());
		}
		indices=new int[registros];
		try{
			PreparedStatement statement=getConnection().prepareStatement("select objetos.id from objetos join objetosdefault on(objetos.id=objetosdefault.idObjeto and objetosdefault.idEspacio=?) order by id");
			statement.setInt(1, idEspacio);
			ResultSet respuesta=statement.executeQuery();
			int i=0;
			while(respuesta.next()){
				indices[i]=respuesta.getInt(1);
				i++;
			
			}
			statement.close();
		}catch(SQLException exception){
			System.err.println(exception.getMessage());
		}
		return indices;
	}
	String [] obtenerMaterialesObjeto(int idObjeto){
		String[] materiales;
		int registros=0;
		try{
			PreparedStatement statement=getConnection().prepareStatement("select count(*) as total from materiales join materialesobjetos on(materiales.id=materialesobjetos.idMaterial) join objetos on (materialesobjetos.idObjeto=objetos.id and objetos.id=?)");
			statement.setInt(1,idObjeto);
			ResultSet respuesta=statement.executeQuery();
			respuesta.next();
			registros=respuesta.getInt("total");
			respuesta.close();
			
		}catch(SQLException exception){
			System.err.println(exception.getMessage());
		}
		materiales=new String[registros+1];
		try{
			PreparedStatement statement=getConnection().prepareStatement("select materiales.descripcion from materiales join materialesobjetos on(materiales.id=materialesobjetos.idMaterial) join objetos on (materialesobjetos.idObjeto=objetos.id and objetos.id=?)");
			statement.setInt(1, idObjeto);
			ResultSet respuesta=statement.executeQuery();
			int i=0;
			while(respuesta.next()){
				materiales[i]=respuesta.getString(1);
				i++;
			}
			materiales[materiales.length-1]="otro";
			statement.close();
		}catch(SQLException exception){
			System.err.println(exception.getMessage());
		}
		return materiales;
	}
	int[] getIndicesMateriales(String[] materiales,int[] indicesObjetos){
		int[] indices=new int[materiales.length];
		try{
		PreparedStatement statement=getConnection().prepareStatement("select id from materiales where descripcion=?");
		ResultSet respuesta;
		for(int i=0;i<materiales.length;i++){
			statement.setString(1, materiales[i]);
			respuesta=statement.executeQuery();
			if(respuesta.next()){
				indices[i]=respuesta.getInt(1);
			}
			else{
					if(!materiales[i].equals("")){
					PreparedStatement insertion=getConnection().prepareStatement("insert into materiales (descripcion) values (?)");
					insertion.setString(1, materiales[i]);
					insertion.execute();
					insertion=getConnection().prepareStatement("select id from materiales where descripcion=?");
					insertion.setString(1, materiales[i]);
					ResultSet res=insertion.executeQuery();
					res.next();
					int idMaterial=res.getInt(1);
					insertion=getConnection().prepareStatement("insert into materialesobjetos values(?,?)");
					insertion.setInt(1, idMaterial);
					insertion.setInt(2, indicesObjetos[i]);
					insertion.execute();
				}
			}
		}
		}catch(SQLException exception){
			MostrarError(exception);
		}
		return indices;
	}
	boolean crearEspacio(int idInmueble,int idEspacio,int instancia){
		try{
			PreparedStatement statement=getConnection().prepareStatement("insert into espaciosInventario (idInventario,idEspacio,instancia) values(?,?,?)");
			statement.setInt(1, idInmueble);
			statement.setInt(2, idEspacio);
			statement.setInt(3, instancia);
			statement.execute();
			return true;
		}catch(SQLException exception){
			MostrarError(exception);
			return false;
		}
	}
	boolean guardarEspacio(int[] indicesObjetos,String[] estados,int[] indicesMateriales,int[] cantidades,String[] observaciones,int idEspacioInventario){
		try{
			PreparedStatement statement=getConnection().prepareStatement("insert into objetosEspacio values(?,?,?,?,?,?)");
			statement.setInt(1, idEspacioInventario);
			for(int i=0;i<indicesObjetos.length;i++){
				statement.setInt(2, indicesObjetos[i]);
				statement.setInt(3, cantidades[i]);
				statement.setString(4, estados[i]);
				statement.setString(5, observaciones[i]);
				statement.setInt(6, indicesMateriales[i]);
				statement.execute();
			}
		}catch(SQLException exception){
			MostrarError(exception);
			return false;
		}
		return true;
	}
	boolean Actualizar(int[] indicesObjetos,String[] estados,int[] indicesMateriales,int[] cantidades,String[] observaciones,int idEspacioInventario){
		try{
			PreparedStatement statement=getConnection().prepareStatement("update objetosEspacio set numero=?,calificacion=?,observacion=?,idMaterial=? where idEspacioInventario=? and idObjeto=?");
			statement.setInt(5, idEspacioInventario);
			for(int i=0;i<indicesObjetos.length;i++){
				statement.setInt(6, indicesObjetos[i]);
				statement.setInt(1, cantidades[i]);
				statement.setString(2, estados[i]);
				statement.setString(3, observaciones[i]);
				statement.setInt(4, indicesMateriales[i]);
				statement.execute();
			}
		}catch(SQLException exception){
			MostrarError(exception);
			return false;
		}
		return true;
	}
	int[] obtenerIndicesObjetos(int idInventarioEspacioInventario){
		int[] indices;
		int registros=0;
		try{
			PreparedStatement statement=getConnection().prepareStatement("select idObjeto from objetosEspacio where idEspacioInventario=? order by idObjeto");
			statement.setInt(1, idInventarioEspacioInventario);
			ResultSet respuesta=statement.executeQuery();
			if(respuesta.last()){
				registros=respuesta.getRow();
			}
			indices=new int[registros];
			respuesta.first();
			indices[0]=respuesta.getInt(1);
			int i=1;
			while(respuesta.next()){
				System.out.println(i);
				indices[i]=respuesta.getInt(1);
			}
		}catch(SQLException exception){
			MostrarError(exception);
			indices=new int[0];
		}		
		return indices;
	}
	String[][] obtenerInformacionObjetos(int idInventarioEspacioInventario){
		String[][] informacion;
		int registros=0;
		try{
			PreparedStatement statement=getConnection().prepareStatement("select objetos.descripcion,calificacion,numero,observacion,materiales.descripcion from objetosEspacio join objetos on(objetos.id=objetosEspacio.idObjeto) join materiales on(objetosEspacio.idMaterial=materiales.id) where idEspacioInventario=? order by idObjeto");
			statement.setInt(1, idInventarioEspacioInventario);
			ResultSet respuesta=statement.executeQuery();
			if(respuesta.last()){
				registros=respuesta.getRow();
			}
			System.out.println(registros);
			informacion=new String[registros][5];
			if(registros>0){
				respuesta.first();
				informacion[0][0]=respuesta.getString(1);
				informacion[0][1]=respuesta.getString(2);
				informacion[0][2]=respuesta.getString(3);
				informacion[0][3]=respuesta.getString(4);
				informacion[0][4]=respuesta.getString(5);
			
			int i=1;
			while(respuesta.next()){
				System.out.println(i);
				informacion[i][0]=respuesta.getString(1);
				informacion[i][1]=respuesta.getString(2);
				informacion[i][2]=respuesta.getString(3);
				informacion[i][3]=respuesta.getString(4);
				informacion[i][4]=respuesta.getString(5);
			}
			}
		}catch(SQLException exception){
			MostrarError(exception);
			informacion=new String[0][0];
		}		
		return informacion;
	}
	int getEspaciosInventarioId(int idInmueble,int idEspacio,int instancia){
		try{
			PreparedStatement statement=getConnection().prepareStatement("select id from espaciosInventario where idInventario=? and idEspacio=? and instancia=?");
			statement.setInt(1,idInmueble);
			statement.setInt(2,idEspacio);
			statement.setInt(3,instancia);
			ResultSet respuesta=statement.executeQuery();
			if(respuesta.next()){
				return respuesta.getInt(1);
			}
			else{
				return 0;
			}
		}catch(SQLException exception){
			MostrarError(exception);
			return 0;
		}
	}
}
