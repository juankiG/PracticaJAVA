package mySQL;

import java.sql.Connection;
import java.sql.SQLException;

import Conexion.ConexionBBDD;
import Dao.DaoManager;

public class MysqlManager implements DaoManager {
private Connection con;
MysqlBBDD bbdd;
MysqlJugadores jugadores;
MysqlLiga liga;
MysqlEquipo equipo;
ConexionBBDD conexion;
//sirve para el login, obliga a meter una contraseña
 public MysqlManager(String user, String password) throws ClassNotFoundException, SQLException {
	 conexion=new ConexionBBDD( user, password);
	 
	con=  conexion.getConexion2();
}
 //generico, con este se crea ya con la bbdd usuario  contraseña predefinidos 
  public MysqlManager() throws ClassNotFoundException, SQLException {
	  conexion= new ConexionBBDD();
	con= conexion.getConexion();
}
	@Override
	public MysqlEquipo getEquipo() throws ClassNotFoundException, SQLException {
		if(equipo==null)
		equipo= new MysqlEquipo();
		return equipo;
	}

	@Override
	public MysqlJugadores getJugador() throws ClassNotFoundException, SQLException {
		if(jugadores==null)
jugadores= new MysqlJugadores();
return jugadores;
	}

	@Override
	public MysqlLiga getliga() throws ClassNotFoundException, SQLException {
		if(liga==null)
liga= new  MysqlLiga();
return liga;
	}

	@Override
	public MysqlBBDD getBBDD() throws ClassNotFoundException, SQLException {
		if(bbdd==null)
		bbdd= new MysqlBBDD();
		return bbdd;
	}

}
