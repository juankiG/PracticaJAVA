package Dao;

import mySQL.MysqlBBDD;
import mySQL.MysqlEquipo;
import mySQL.MysqlJugadores;
import mySQL.MysqlLiga;

public interface DaoManager {
MysqlEquipo getEquipo();
MysqlJugadores getJugador();
MysqlLiga getliga();
MysqlBBDD getBBDD();
}
