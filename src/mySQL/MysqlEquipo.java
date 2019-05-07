package mySQL;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Conexion.ConexionBBDD;
import Dao.DaoEquipo;
import Modelos.Equipo;

public class MysqlEquipo implements DaoEquipo{
	private String insertar="insert into equipo(nombre) values(?);";
	private String modificar="update equipo set nombre=? where id=?";
	private String eliminar= "delete from equipo where id=?;";
	private String buscarTodos="select * from equipo";
	private String buscarEquipo="select * from equipo where id=?";
	Connection con;
	ConexionBBDD bbdd= new ConexionBBDD();
	public MysqlEquipo() throws ClassNotFoundException, SQLException {
	con=bbdd.getConexion();
}
	@Override
	public void insertar(Equipo objeto) {
		try (PreparedStatement ps= con.prepareStatement(insertar);){
			ps.executeUpdate();
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		
	}

	@Override
	public void modificar(Equipo objeto) {
		try (PreparedStatement ps= con.prepareStatement(modificar);){
			ps.executeUpdate();
			
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	@Override
	public void eliminar(Integer t) {
		try (PreparedStatement ps= con.prepareStatement(eliminar);){
			ps.executeUpdate();
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		
	}
	
private Equipo encontrado(ResultSet rset) throws SQLException {
	Equipo equi=null;
	if(rset.next()) {
		equi.setId(rset.getInt("id"));
		equi.setNombre(rset.getString("nombre"));
	
	}
	return equi;
}
	@Override
	public List<Equipo> BuscarTodos() {
		List<Equipo>equipos= new ArrayList<Equipo>();
		Equipo equi=null;
		PreparedStatement ps= null;
		ResultSet rset= null;
		try{
			 ps= con.prepareStatement(buscarTodos);
			rset=ps.executeQuery();
			while(rset.next()) {
				equi.setId(rset.getInt("id"));
				equi.setNombre(rset.getString("nombre"));
				
				equipos.add(equi);
			}
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		return equipos;
	}

	@Override
	public Equipo buscar() {
		Equipo equi=null;
		try(PreparedStatement ps= con.prepareStatement(buscarEquipo);ResultSet rset =ps.executeQuery()) {
			equi=encontrado(rset);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return equi;
	}

}
