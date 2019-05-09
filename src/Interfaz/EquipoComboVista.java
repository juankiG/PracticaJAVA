package Interfaz;

import Modelos.Equipo;
import Modelos.Jugadores;

public class EquipoComboVista {
private Equipo equipo;

public EquipoComboVista(Equipo equipo) {
	
	this.equipo = equipo;
}

public Equipo getEquipo() {
	return equipo;
}

public void setEquipo(Equipo equipo) {
	this.equipo = equipo;
}

@Override
public String toString() {
	return equipo.getId()+" "+equipo.getNombre();
}

}
