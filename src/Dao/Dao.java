package Dao;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public interface Dao<k,t> {
void insertar(k objeto);
void modificar(k objeto);
void eliminar(t t);
List<k> BuscarTodos();
ResultSet BuscarTodosRSUL() throws SQLException;
ResultSet buscarRset(Integer id) throws SQLException ;
k buscar(t id);
}
