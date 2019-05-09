package Modelos;

public class Liga {
private Integer id=null;
private String nombre;

public Liga(String nombre) {
	
	this.nombre = nombre;
	
}
public Liga() {
	
	
}
@Override
public String toString() {
	return getId()+" "+getNombre();
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

}
