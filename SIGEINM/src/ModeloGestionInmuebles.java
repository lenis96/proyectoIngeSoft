/*************************************************

Nombre de la clase: ModeloInmuebles.java

Última modificación: 06/06/2016

Descripción: Administra toda la lógica que rodea la 
	consulta, creación y modificación de los inmuebles.

*************************************************/
import java.sql.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class ModeloGestionInmuebles extends DataBaseConnection{
	
	public ModeloGestionInmuebles() {
		super();
	}
	public DefaultTableModel obtenerInmuebles(String minPrecio,String maxPrecio,String direccion,String lugarReferencia){
		/*
		 * Funcion que retorna un modelo de tabla de los inmuebles que son de interes para el usuario
		 */
		DefaultTableModel tableModel=new DefaultTableModel();
		int registros=0;
		String[] columNames={"ID","DIRECCION","PRECIO","USUARIO"};
		try{
			PreparedStatement statement=getConnection().prepareStatement("select count(*) as total from inmuebles where precio between "+minPrecio+" and "+maxPrecio+" and direccion like '%"+direccion+"%' and lugarReferencia like '%"+lugarReferencia+"%' ");
			
			ResultSet respuesta=statement.executeQuery();
			respuesta.next();
			registros=respuesta.getInt("total");
			respuesta.close();
		}catch(SQLException exception){
			System.err.println(exception.getMessage());
		}
		Object [][] data=new String[registros][5];
		try{
			PreparedStatement statement=getConnection().prepareStatement("select id,direccion,precio,idUsuario from inmuebles where precio between "+minPrecio+" and "+maxPrecio+" and direccion like ? and lugarReferencia like ? ");
			statement.setString(1, "%"+direccion+"%");
			statement.setString(2, "%"+lugarReferencia+"%");
			ResultSet respuesta=statement.executeQuery();
			int i=0;
			while(respuesta.next()){
				data[i][0]=respuesta.getString("id");
				data[i][1]=respuesta.getString("direccion");
				data[i][2]=respuesta.getString("precio");
				data[i][3]=respuesta.getString("idUsuario");
				i++;
			}
			respuesta.close();
			tableModel.setDataVector(data, columNames);
		}catch(SQLException exception){
			System.err.println(exception.getMessage());
		}
		return tableModel;
	}
	public String[] getDataInmueble(int idInmueble){
		/*
		 * Funcion que entrega un arreglo de string de la información del inmueble dado el id del inmueble.
		 */
		String [] datos=new String[9];
		try{
		PreparedStatement statement=getConnection().prepareStatement("select * from inmuebles where id="+String.valueOf(idInmueble));
		ResultSet respuesta=statement.executeQuery();
		respuesta.next();
		int i=0;
		while(i<respuesta.getMetaData().getColumnCount()){
			datos[i]=respuesta.getString(++i);
		}
		if(datos[5].equals("C")){
			datos[5]="Casa";
		}
		else if(datos[5].equals("A")){
			datos[5]="Apartamento";
		}
		}catch(SQLException exception){
			System.err.println(exception.getMessage());
		}
		return datos;
	}
	public boolean ActualizarInformacionInmueble(String id,String direccion,String lugarReferencia,String tamano, String estrato,String tipo,String habitaciones,String usuario,String precio){
		/*
		 * Funcion que actualiza la informacion del inmueble dado los parametros ingresados.
		 */
		try{
			PreparedStatement statement=getConnection().prepareStatement("update inmuebles set direccion=?,lugarReferencia=?,tamano=?,estrato=?,tipo=?,habitaciones=?,idUsuario=?,precio=? where id="+id);
			statement.setString(1, direccion);
			statement.setString(2, lugarReferencia);
			statement.setInt(3, Integer.parseInt(tamano));
			statement.setInt(4, Integer.parseInt(estrato));
			statement.setString(5, tipo);
			statement.setInt(6, Integer.parseInt(habitaciones));
			statement.setString(7, usuario);
			statement.setInt(8, Integer.parseInt(precio));
			statement.execute();
			statement.close();
			return true;
		}catch(SQLException exception){
			System.err.println(exception);
			return false;
		}
	}
	public boolean InsertarInformacionInmueble(String id,String direccion,String lugarReferencia,String tamano, String estrato,String tipo,String habitaciones,String usuario,String precio){
		/*
		 * Funcion que agrega la informacion del inmueble para crearlo.
		 */
		try{
			PreparedStatement statement=getConnection().prepareStatement("insert into inmuebles (direccion,lugarReferencia,tamano,estrato,tipo,habitaciones,idUsuario,precio) values(?,?,?,?,?,?,?,?)");
			statement.setString(1, direccion);
			statement.setString(2, lugarReferencia);
			statement.setInt(3, Integer.parseInt(tamano));
			statement.setInt(4, Integer.parseInt(estrato));
			statement.setString(5, tipo);
			statement.setInt(6, Integer.parseInt(habitaciones));
			statement.setString(7, usuario);
			statement.setInt(8, Integer.parseInt(precio));
			statement.execute();
			statement.close();
			return true;
		}catch(SQLException exception){
			System.err.println(exception);
			return false;
		}
	}
	public String[] getUsuariosInmueble(){
		/*
		 * Funcion que entrega los usuarios disponible para ser asignados al inmueble
		 */
		int registros=0;
		try{
			PreparedStatement statemenet=getConnection().prepareStatement("select count(*) as total from usuarios where tipo='A' ");
			ResultSet respuesta=statemenet.executeQuery();
			respuesta.next();
			registros=respuesta.getInt("total");
			respuesta.close();
			
		}catch(SQLException exception){
			System.err.println(exception.getMessage());
		}
		String[] usuarios=new String[registros+1];
		try{
			PreparedStatement statement=getConnection().prepareStatement("select usuario from usuarios where tipo='R' ");
			ResultSet respuesta=statement.executeQuery();
			respuesta.next();
			usuarios[0]=respuesta.getString(1);
			statement=getConnection().prepareStatement("select usuario from usuarios where tipo='A' ");
			respuesta=statement.executeQuery();
			int i=1;
			while(respuesta.next()){
				usuarios[i]=respuesta.getString(1);
				i++;
			
			}
			statement.close();
		}catch(SQLException exception){
			System.err.println(exception.getMessage());
		}
		return usuarios;
	}
}
