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
		MysqlJugadores my= new MysqlJugadores();
		System.out.println(my.buscar(1)  );
		MysqlLiga ml= new MysqlLiga();
		System.out.println(ml.buscar(1));
		MysqlEquipo me= new MysqlEquipo();
		System.out.println(me.buscar(1));
	}

}
