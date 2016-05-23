import java.sql.*;
public class MueblesModelo extends DBConnection {
	public MueblesModelo() {
		super();
	}
	String [][] obtenerInmuebles(){
		try{
			String query="select count(*) as total from inmuebles";
			ResultSet rs;
			rs=getStatement().executeQuery(query);
			rs.next();
			int registros=rs.getInt("total");
			rs.close();
			String [][] respuesta=new String[registros][4];
			
			try{
				query="select * from inmuebles";
				rs=getStatement().executeQuery(query);
				int i=0;
				while(rs.next()){
					respuesta[i][0]=rs.getString("id");
					respuesta[i][1]=rs.getString("direccion");
					respuesta[i][2]=rs.getString("precio");
					respuesta[i][3]=rs.getString("otro");
					i++;
				}
				return respuesta;
				
			}catch (Exception ex){
				return null;
			}
		}catch(Exception ex){
			System.out.println(ex);
			return null;
		}
	}

}
