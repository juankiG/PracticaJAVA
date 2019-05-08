package Dao;

import java.sql.SQLException;

import mySQL.MysqlBBDD;
import mySQL.MysqlEquipo;
import mySQL.MysqlJugadores;
import mySQL.MysqlLiga;

public interface DaoManager {
MysqlEquipo getEquipo() throws ClassNotFoundException, SQLException;
MysqlJugadores getJugador() throws ClassNotFoundException, SQLException;
MysqlLiga getliga() throws ClassNotFoundException, SQLException;
MysqlBBDD getBBDD() throws ClassNotFoundException, SQLException;
}
