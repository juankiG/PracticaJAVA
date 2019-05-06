package mySQL;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Conexion.ConexionBBDD;
import Dao.DaoLiga;
import Modelos.Jugadores;
import Modelos.Liga;

public class MysqlLiga implements DaoLiga {
	private String insertar="insert into liga(id, nombre,idequipo) values(?,?,?);";
	private String modificar="update liga set nombre=? where id=?";
	private String eliminar= "delete from liga where id=?;";
	private String buscarTodos="select * from liga";
	private String buscarEquipo="select * from liga where id=?";
	Connection con;
	ConexionBBDD bbdd;
	public MysqlLiga() throws ClassNotFoundException, SQLException {
	con=bbdd.getConexion();
}
	@Override
	public void insertar(Liga objeto) {
		try (PreparedStatement ps= con.prepareStatement(insertar);){
			ps.setInt(1, objeto.getId());
			ps.setString(2, objeto.getNombre());
			ps.setInt(3, objeto.getIdEquipo());
			
			ps.executeUpdate();
			
		} catch (Exception e) {
			// TODO: handle eSxception
		}		
	}

	@Override
	public void modificar(Liga objeto) {
		try (PreparedStatement ps= con.prepareStatement(modificar);){
			
			ps.setString(1, objeto.getNombre());
			
			ps.setInt(2, objeto.getId());
			ps.executeUpdate();
		} catch (Exception e) {
			// TODO: handle exception
		}		
	}

	@Override
	public void eliminar(Integer t) {
		try (PreparedStatement ps= con.prepareStatement(eliminar);){
			ps.setInt(1, t);
			ps.executeUpdate();
			
		} catch (Exception e) {
			// TODO: handle exception
		}		
	}

	
private Liga encontrado(ResultSet rset) throws SQLException {
	Liga equi=null;
	if(rset.next()) {
		equi.setId(rset.getInt("id"));
		equi.setNombre(rset.getString("nombre"));
		equi.setIdEquipo(rset.getInt("idequipo"));
	}
	return equi;
}
	@Override
	public List<Liga> BuscarTodos() {
		List<Liga>equipos= new ArrayList<Liga>();
		Liga jugado=null;
		PreparedStatement ps= null;
		ResultSet rset= null;
		try{
			 ps= con.prepareStatement(buscarTodos);
			rset=ps.executeQuery();
			while(rset.next()) {
				jugado.setId(rset.getInt("id"));
				jugado.setNombre(rset.getString("nombre"));
				jugado.setIdEquipo(rset.getInt("idequipo"));
				equipos.add(jugado);
			}
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		return equipos;
	}

	@Override
	public Liga buscar() {
		Liga equi=null;
		try(PreparedStatement ps= con.prepareStatement(buscarEquipo);ResultSet rset =ps.executeQuery()) {
			equi=encontrado(rset);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return equi;
	}


}
