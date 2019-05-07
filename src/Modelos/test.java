package Modelos;

import java.sql.SQLException;

import mySQL.MysqlBBDD;
import mySQL.MysqlEquipo;
import mySQL.MysqlJugadores;
import mySQL.MysqlLiga;

public class test {

	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		MysqlBBDD mb= new MysqlBBDD();
		mb.CrearBBDD2("prueba");

}

}
