package Interfaz;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import Dao.DaoLiga;
import Modelos.Liga;

public class LigaTableModel extends AbstractTableModel{
	private DaoLiga liga;
	private List<Liga> listaLiga;
public LigaTableModel(DaoLiga liga) {
	this.liga=liga;
	this.listaLiga= new ArrayList<Liga>();
}
public void ActualizarModelo() {
	listaLiga=liga.BuscarTodos();
}
@Override
public String getColumnName(int column) {
	switch (column) {
	case 0:return"idLiga";
	case 1:return"NombreLiga";
	default:
		return "0";
	}
}
	@Override
	public int getColumnCount() {
		// TODO Auto-generated method stub
		return 2;
	}

	@Override
	public int getRowCount() {
		// TODO Auto-generated method stub
		return listaLiga.size();
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		Liga liga= listaLiga.get(rowIndex);
		switch (columnIndex) {
		case 0:return liga.getId();
		case 1:return liga.getNombre();
		default:
			break;
		}
		return null;
	}

}
