package Interfaz;

import java.util.ArrayList;
import java.util.List;

import javax.swing.DefaultComboBoxModel;

import Dao.DaoEquipo;
import Dao.DaoJugador;
import Modelos.Equipo;
import Modelos.Jugadores;

public class ComboboxEquipo extends DefaultComboBoxModel<EquipoComboVista> {

	private DaoEquipo equipo;
	private List<Equipo> equipos;
	public ComboboxEquipo(DaoEquipo equipo) {
		this.equipo=equipo;
		equipos= new ArrayList<Equipo>();
	}
	public void update() {
		if(equipo!=null) {
			equipos=equipo.BuscarTodos();
			removeAllElements();
			for (Equipo equipo : equipos) {
				addElement(new EquipoComboVista(equipo));
			}
		}
		
	}
}
