package Modelos;

public class Equipo {
private Integer id=null;
private String nombre;
private int idLiga;
public Equipo() {
	
}
public Equipo(String nombre, int idLiga) {
	this.nombre = nombre;
	this.idLiga=idLiga;
}
public int getIdLiga() {
	return idLiga;
}
public void setIdLiga(int idLiga) {
	this.idLiga = idLiga;
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

@Override
public String toString() {
	return "Equipo [id=" + id + ", nombre=" + nombre + "]";
}

}
