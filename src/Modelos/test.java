package Modelos;

import java.sql.SQLException;

import mySQL.MysqlEquipo;
import mySQL.MysqlJugadores;
import mySQL.MysqlLiga;

public class test {

	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		Equipo e= new Equipo("leganes",2);
		Jugadores j= new Jugadores("pep", 1, 1);
		Liga l= new Liga("españonla");
		
		MysqlLiga ml= new MysqlLiga();
System.out.println(ml.BuscarTodos());		

}

}
