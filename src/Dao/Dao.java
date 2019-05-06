package Dao;
import java.util.List;

public interface Dao<k,t> {
void insertar(k objeto);
void modificar(t t);
void eliminar(t t);
List<k> BuscarTodos();
k buscar();
}
