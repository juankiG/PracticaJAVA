package Modelos;

import java.sql.SQLException;

import mySQL.MysqlJugadores;

public class test {

	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		Equipo e= new Equipo("getafe");
		Jugadores j= new Jugadores("pep", 1, 1);
		Liga l= new Liga("españonla", 1);
		MysqlJugadores my= new MysqlJugadores();
		my.insertar(j);
	}

}
