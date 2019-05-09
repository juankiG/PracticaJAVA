package Modelos;

import java.sql.SQLException;

import mySQL.MysqlBBDD;
import mySQL.MysqlEquipo;
import mySQL.MysqlJugadores;
import mySQL.MysqlLiga;
import mySQL.MysqlManager;

public class test {

	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		Equipo equipo = new Equipo("FC", 1);
		Jugadores j= new Jugadores("van", 9, 1);
		Liga liga= new Liga("español");
		MysqlManager mg= new MysqlManager();
		//mg.getBBDD().CrearBBDD("bd_equipos");
		
		
		mg.getEquipo().modificar((equipo));
}

}
