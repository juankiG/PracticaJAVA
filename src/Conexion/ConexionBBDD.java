package Conexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionBBDD {
private static  String url="jdbc:mysql://localhost:3306/";
private static String bd="bd_equipos";
static String user="root";
static String password="";
 Connection conexion;
public ConexionBBDD() throws ClassNotFoundException, SQLException {
	this(url,bd,user,password);
	
	//java.sql
	//con esto se crea la conexion
	//Dao para cada POJO
	
}
public ConexionBBDD(String url,String bd,String user, String password) throws ClassNotFoundException, SQLException {
this.url=url;
this.user=user;
this.bd=bd;
this.password=password;
Class.forName("com.mysql.jdbc.Driver");
//java.sql
//con esto se crea la conexion
//conexion via get
//con= DriverManager.getConnection(url+bd+"?user"+user+"& password="+password);
conexion= DriverManager.getConnection(this.url+this.bd, this.user, this.password);
//state=con.createStatement();
System.out.println("conexion establecida");
}
public ConexionBBDD(String user, String password) throws ClassNotFoundException, SQLException {

this.user=user;
this.password=password;
Class.forName("com.mysql.jdbc.Driver");
//java.sql
//con esto se crea la conexion
//conexion via get
//con= DriverManager.getConnection(url+bd+"?user"+user+"& password="+password);
conexion= DriverManager.getConnection(this.url, this.user, this.password);
//state=con.createStatement();
System.out.println("conexion establecida");
}
public  Connection getConexion() throws ClassNotFoundException, SQLException {
	if(this.conexion==null) {
	new ConexionBBDD();
		
	}
	return conexion;
}
public  Connection getConexion2() throws ClassNotFoundException, SQLException {
	if(this.conexion==null) {
	new ConexionBBDD(user,password);
		
	}
	return conexion;
}
public void cerrar(){
	if(this.conexion!=null) {
		try {
			this.conexion.close();
		} catch (Exception e) {
			// TODO: handle exception
		}
		
	}
}
}
