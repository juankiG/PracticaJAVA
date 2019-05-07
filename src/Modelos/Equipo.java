package Modelos;

public class Equipo {
private Integer id;
private String nombre;

public Equipo(String nombre) {
	this.nombre = nombre;

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
