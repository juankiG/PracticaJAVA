package Interfaz;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import Dao.DaoEquipo;
import Modelos.Equipo;
import Modelos.Jugadores;

public class EquiposTableModel extends AbstractTableModel{
private DaoEquipo equipo;
private List<Equipo> ListaEquipos= new ArrayList<Equipo>();

public EquiposTableModel(DaoEquipo equipo)  {
	this.equipo=equipo;
	
}
public void ActualizarModelo() {
	ListaEquipos=equipo.BuscarTodos();
}
@Override
	public String getColumnName(int column) {
	switch (column) {
	case 0:return "IdEquipo";
	case 1:return "nombre";
	case 2:return "idLiga";
	default: return "0";
		
	}
	
	}
@Override
public int getColumnCount() {
	return 3;
}
@Override
public int getRowCount() {
	// TODO Auto-generated method stub
	return ListaEquipos.size();
}
@Override
public Object getValueAt(int rowIndex, int columnIndex) {
	Equipo e= ListaEquipos.get(rowIndex);
	switch (columnIndex) {
	case 0:return e.getId();
	case 1:return e.getNombre();
	case 2:return e.getIdLiga();
	default:
		break;
	}
	return null;
}
}
