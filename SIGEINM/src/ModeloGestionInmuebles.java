import java.sql.*;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
public class ModeloGestionInmuebles extends DataBaseConnection{
	
	public ModeloGestionInmuebles() {
		super();
	}
	public DefaultTableModel obtenerInmuebles(){
		DefaultTableModel tableModel=new DefaultTableModel();
		int registros=0;
		String[] columNames={"ID","DIRECCION","PRECIO","USUARIO"};
		try{
			PreparedStatement statement=getConnection().prepareStatement("select count(*) as total from inmuebles");
			
			ResultSet respuesta=statement.executeQuery();
			respuesta.next();
			registros=respuesta.getInt("total");
			respuesta.close();
		}catch(SQLException exception){
			System.err.println(exception.getMessage());
		}
		Object [][] data=new String[registros][4];
		try{
			PreparedStatement statement=getConnection().prepareStatement("select id,direccion,precio,idUsuario from inmuebles");
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
	
}
