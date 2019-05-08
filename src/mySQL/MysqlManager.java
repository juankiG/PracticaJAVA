package mySQL;

import java.sql.SQLException;

import Dao.DaoManager;

public class MysqlManager implements DaoManager {
MysqlBBDD bbdd;
MysqlJugadores jugadores;
MysqlLiga liga;
MysqlEquipo equipo;
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
