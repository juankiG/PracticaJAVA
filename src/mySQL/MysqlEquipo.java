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
import Modelos.Jugadores;

public class MysqlEquipo implements DaoEquipo{
	private String insertar="insert into equipo(nombre,idLiga) values(?,?);";
	private String modificar="update equipo set nombre=?,idLiga=? where idequipo=?";
	private String eliminar= "delete from equipo where idEquipo=?;";
	private String buscarTodos="select * from equipo";
	private String buscarEquipo="select * from equipo where idEquipo=?";
	Connection con;
	ConexionBBDD bbdd= new ConexionBBDD();
	public MysqlEquipo() throws ClassNotFoundException, SQLException {
	con=bbdd.getConexion();
}
	@Override
	public void insertar(Equipo objeto) {
		try (PreparedStatement ps= con.prepareStatement(insertar);){
			ps.setString(1, objeto.getNombre());
			ps.setInt(2, objeto.getIdLiga());
			ps.executeUpdate();
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		
	}

	@Override
	public void modificar(Equipo objeto) {
		try (PreparedStatement ps= con.prepareStatement(modificar);){
			ps.setString(1, objeto.getNombre());
			ps.setInt(2, objeto.getIdLiga());
			ps.setInt(3, objeto.getId());
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
				equi=new Equipo();
				equi.setId(rset.getInt("idEquipo"));
				equi.setNombre(rset.getString("nombre"));
				equi.setIdLiga(rset.getInt("idLiga"));
				
				equipos.add(equi);
			}
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		return equipos;
	}

	@Override
	public Equipo buscar(Integer id) {
		
		PreparedStatement ps=null;
		ResultSet rset= null;
		Equipo equi=null;
		try {
			ps=con.prepareStatement(buscarEquipo);
			ps.setInt(1, id);
			 rset =ps.executeQuery();
			
			if(rset.next()) {
				equi=new Equipo();
				equi.setId(rset.getInt("idEquipo"));
				equi.setNombre(rset.getString("nombre"));
				equi.setIdLiga(rset.getInt("idLiga"));
			}
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		return equi;
	}

}
