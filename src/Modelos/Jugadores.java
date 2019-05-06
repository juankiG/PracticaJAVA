package Modelos;

public class Jugadores {
private Integer id;
private String  nombre;
private Integer dorsal;
public Jugadores(String nombre, Integer dorsal) {
	super();
	this.nombre = nombre;
	this.dorsal = dorsal;
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
public Integer getDorsal() {
	return dorsal;
}
public void setDorsal(Integer dorsal) {
	this.dorsal = dorsal;
}
@Override
public String toString() {
	return "Jugadores [id=" + id + ", nombre=" + nombre + ", dorsal=" + dorsal + "]";
}

}
