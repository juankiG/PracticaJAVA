package Interfaz;

import java.util.ArrayList;
import java.util.List;

import javax.swing.DefaultComboBoxModel;

import Dao.DaoLiga;
import Modelos.Equipo;
import Modelos.Liga;

public class ComboboxLiga extends DefaultComboBoxModel<Liga> {
	private DaoLiga liga;
	private List<Liga>listaLiga;
	public ComboboxLiga(DaoLiga liga) {
		this.liga=liga;
		listaLiga= new ArrayList<Liga>();
	}
	
	public void update() {
		if(liga!=null) {
			listaLiga=liga.BuscarTodos();
			removeAllElements();
			for (Liga liga : listaLiga) {
				addElement(liga);
			}
		}
	}

}
