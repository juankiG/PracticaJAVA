package mySQL;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Conexion.ConexionBBDD;
import Dao.DaoJugador;
import Modelos.Equipo;
import Modelos.Jugadores;

public class MysqlJugadores implements DaoJugador{
	private String insertar="insert into jugador( nombreJugador,dorsal,idequipo) values(?,?,?);";
	private String modificar="update jugador set nombreJugador=?,dorsal=?,idEquipo=? where id=?";
	private String eliminar= "delete from jugador where idJugador=?;";
	private String buscarTodos="select * from jugador";
	private String buscarEquipo="select * from jugador where idJugador=?";
	Connection con;
	ConexionBBDD bbdd= new ConexionBBDD();
	public MysqlJugadores() throws ClassNotFoundException, SQLException {
	con=bbdd.getConexion();
}
	@Override
	public void insertar(Jugadores objeto) {
		try (PreparedStatement ps= con.prepareStatement(insertar);){
			
			ps.setString(1, objeto.getNombre());
			ps.setInt(2, objeto.getDorsal());
			ps.setInt(3, objeto.getIdEquipo());
			ps.executeUpdate();
			
		} catch (Exception e) {
			// TODO: handle eSxception
		}		
	}

	@Override
	public void modificar(Jugadores objeto) {
		try (PreparedStatement ps= con.prepareStatement(modificar);){
			
			ps.setString(1, objeto.getNombre());
			ps.setInt(2, objeto.getDorsal());
			ps.setInt(3, objeto.getIdEquipo());
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

	
private Jugadores encontrado(ResultSet rset) throws SQLException {
	Jugadores equi=null;
	
	return equi;
}
	@Override
	public List<Jugadores> BuscarTodos() {
		List<Jugadores>equipos= new ArrayList<Jugadores>();
		Jugadores jugado=null;
		PreparedStatement ps= null;
		ResultSet rset= null;
		try{
			 ps= con.prepareStatement(buscarTodos);
			rset=ps.executeQuery();
			while(rset.next()) {
				jugado= new Jugadores();
				jugado.setId(rset.getInt("idJugador"));
				jugado.setNombre(rset.getString("nombreJugador"));
				jugado.setDorsal(rset.getInt("dorsal"));
				jugado.setIdEquipo(rset.getInt("idEquipo"));
				equipos.add(jugado);
			}
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		return equipos;
	}

	@Override
	public Jugadores buscar(Integer id) {
		Jugadores equi=null;
		PreparedStatement ps=null;
		ResultSet rset= null;
		try {
			ps=con.prepareStatement(buscarEquipo);
			ps.setInt(1, id);
			 rset =ps.executeQuery();
			
			 if(rset.next()) {
					equi=new Jugadores();
					equi.setId(rset.getInt("idJugador"));
					equi.setNombre(rset.getString("nombreJugador"));
					equi.setDorsal(rset.getInt("dosal"));
					equi.setIdEquipo(rset.getInt("idEquipo"));
				}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return equi;
	}

}
