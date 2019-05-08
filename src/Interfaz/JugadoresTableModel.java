package Interfaz;

import java.util.ArrayList;
import java.util.List;

import javax.swing.event.TableModelListener;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import Dao.DaoJugador;
import Modelos.Jugadores;

public class JugadoresTableModel extends AbstractTableModel {
	private DaoJugador jugadores;
	private List<Jugadores> lista = new ArrayList<Jugadores>();

	public JugadoresTableModel(DaoJugador jugadores) {
		this.jugadores = jugadores;
	}
	public void updateModel() {
		lista= jugadores.BuscarTodos();
	}
@Override
public String getColumnName(int column) {
	switch (column) {
	case 0:return "id";
	case 1:return "nombre";
	case 2:return "dorsal";
	case 3:return "idEquipo";
	default:return "no";
	}
}
	@Override
	public int getColumnCount() {
		// TODO Auto-generated method stub
		return 4;
	}

	@Override
	public int getRowCount() {
		// TODO Auto-generated method stub
		return lista.size();
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		Jugadores j= lista.get(rowIndex);
		switch (columnIndex) {
		case 0:return j.getId();
		case 1:return j.getNombre();
		case 2:return j.getDorsal();
		case 3:return j.getIdEquipo();
			
		

		default:
			break;
		}
		return null;
	}

	
}
