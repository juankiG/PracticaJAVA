package Modelos;

import java.sql.SQLException;

import mySQL.MysqlEquipo;
import mySQL.MysqlJugadores;
import mySQL.MysqlLiga;

public class test {

	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		Equipo e= new Equipo("getafe");
		Jugadores j= new Jugadores("pep", 1, 1);
		Liga l= new Liga("españonla", 1);
		//MysqlJugadores my= new MysqlJugadores();
		//my.insertar(j);
		MysqlLiga ml= new MysqlLiga();
		ml.insertar(l);
		//MysqlEquipo me= new MysqlEquipo();
		//me.insertar(e);
	}

}
