package Interfaz;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;

import java.sql.ResultSetMetaData;
import java.sql.SQLException;

import Dao.DaoEquipo;
import Dao.DaoManager;
import Modelos.Equipo;
import Modelos.Jugadores;
import mySQL.MysqlManager;

public class TableModel extends DefaultTableModel{
//private DaoEquipo equipo;
//private List<Equipo> ListaEquipos= new ArrayList<Equipo>();
private ResultSet rset;
private ResultSetMetaData rsetm;


public TableModel(ResultSet rset) throws SQLException  {
	if(rset!=null) {
		this.rset=rset;
		this.rsetm= rset.getMetaData();
	}
	
}
/*public void ActualizarModelo() {
	ListaEquipos=equipo.BuscarTodos();
}*/
public void ActualizarModelo() throws ClassNotFoundException, SQLException {

}
@Override
	public String getColumnName(int column) {
	String nombre= null;
	try {
		nombre=  rsetm.getColumnName(column+1);
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	/*switch (column) {
	case 0:return "IdEquipo";
	case 1:return "nombre";
	case 2:return "idLiga";
	default: return "0";
		
	}*/
	return nombre;
	}
@Override
public int getColumnCount() {
	int columnas=0;
	try {
		if(rsetm!=null) {
			columnas= rsetm.getColumnCount();
		}else {
			return 0;
		}
		
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return columnas;
}
@Override
public int getRowCount() {
	
	int filas=0;
	try {
		if(rset!=null) {
			rset.last();
			filas=rset.getRow();

		}
		
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
		return filas;
}
@Override
public Object getValueAt(int rowIndex, int columnIndex) {
	Object valor=null;
	try {
		rset.absolute(rowIndex+1);
		valor=rset.getObject(columnIndex+1);
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return valor;
	

}
}
