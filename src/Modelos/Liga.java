package Modelos;

public class Liga {
private Integer id;
private String nombre;
private Integer IdEquipo;
public Liga(String nombre, Integer idEquipo) {
	
	this.nombre = nombre;
	IdEquipo = idEquipo;
}

@Override
public String toString() {
	return "Liga [id=" + id + ", nombre=" + nombre + ", IdEquipo=" + IdEquipo + "]";
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
public Integer getIdEquipo() {
	return IdEquipo;
}
public void setIdEquipo(Integer idEquipo) {
	IdEquipo = idEquipo;
}

}
