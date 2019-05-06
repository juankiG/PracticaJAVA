package Modelos;

public class Equipo {
private Integer id;
private String nombre;
private Integer idJugador;
public Equipo(String nombre, Integer idJugador) {
	this.nombre = nombre;
	this.idJugador = idJugador;
}
public Integer getId() {
	return id;
}
public void setId(Integer id) {
	this.id = id;
}
public String getNombre() {
	return nombre;
}
public void setNombre(String nombre) {
	this.nombre = nombre;
}
public Integer getIdJugador() {
	return idJugador;
}
public void setIdJugador(Integer idJugador) {
	this.idJugador = idJugador;
}
@Override
public String toString() {
	return "Equipo [id=" + id + ", nombre=" + nombre + ", idJugador=" + idJugador + "]";
}

}
