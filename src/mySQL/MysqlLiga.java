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
	private String insertar="insert into liga( nombre) values(?);";
	private String modificar="update liga set nombre=? where idLiga=?";
	private String eliminar= "delete from liga where idLiga=?;";
	private String buscarTodos="select * from liga";
	private String buscarEquipo="select * from liga where idLiga=?";
	Connection con;
	ConexionBBDD bbdd= new ConexionBBDD();
	public MysqlLiga() throws ClassNotFoundException, SQLException {
	con=bbdd.getConexion();
}
	@Override
	public void insertar(Liga objeto) {
		try (PreparedStatement ps= con.prepareStatement(insertar);){
			ps.setString(1, objeto.getNombre());
			ps.executeUpdate();
			
		} catch (Exception e) {
			// TODO: handle eSxception
		}		
	}

	@Override
	public void modificar(Liga objeto) {
		try (PreparedStatement ps= con.prepareStatement(modificar);){
			
			ps.setString(1, objeto.getNombre());
			
			
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
		equi= new Liga();
		equi.setId(rset.getInt("idLiga"));
		equi.setNombre(rset.getString("nombre"));
		
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
				jugado=new Liga();
				jugado.setId(rset.getInt("idliga"));
				jugado.setNombre(rset.getString("nombre"));
		
				equipos.add(jugado);
			}
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		return equipos;
	}

	@Override
	public Liga buscar(Integer id) {
		Liga jugado=null;
		PreparedStatement ps= null;
		ResultSet rset= null;
		try{
			 ps= con.prepareStatement(buscarEquipo);
			 ps.setInt(1, id);
			rset=ps.executeQuery();
			if(rset.next()) {

				jugado= new Liga();
				jugado.setId(rset.getInt("idLiga"));
				jugado.setNombre(rset.getString("nombre"));
				
			}
			} catch (Exception e) {
			// TODO: handle exception
		}
		return jugado;
	}


}
