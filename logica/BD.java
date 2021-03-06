package logica;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class BD {

	private String usuario;
	private String clave;
	private String url;
	private String driverClassName;
	private Connection conn = null;
	private Statement sentencia;
	public static BD conex;

	private BD(){
		/**conexion MySQL*/
		this.usuario = "usuarioAPI"; //usuario creado en MySQL y con permiso en la Base de
		this.clave = "mtYc4yzXTDEbK6VF"; //Password de mysql del usuario
		this.url = "jdbc:mysql://localhost:3306/auditorias"; //Forma de "encontrar" la Base de Datos en
		this.driverClassName = "com.mysql.jdbc.Driver"; //driver para trabajar con MySQL   	
		try {
			AbrirConexion();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static BD getInstance() {
		if(conex == null) {

			conex = new BD();
		}
		return conex;
	}

	//El m�todo que nos permite abrir la conexi�n
	private void AbrirConexion() throws SQLException {
		try {
			//en MySQL con usuario y clave
			Class.forName(this.driverClassName).newInstance();
			this.conn = DriverManager.getConnection(this.url, this.usuario, this.clave);
		} catch (Exception err) {
			System.out.println("Error " + err.getMessage());
		}
	}

	//Cerrar la conexion
	public void CerrarConexion() throws SQLException {
		this.conn.close();
	}

	/**M�todos para trabajar con la Base de Datos. En este caso he puesto cuatro m�todos
  y los cuatro trabajan con el objeto ResulSet. Hay m�s opciones, las pod�is consultar
  en la documentaci�n de java.sql y usarlas en funci�n de vuestras necesidades
	 **/
	public ResultSet consulta(String consulta) throws SQLException {
		this.sentencia = (Statement) conn.createStatement();
		return this.sentencia.executeQuery(consulta);
	}

	public void cerrarConsulta (ResultSet resul)throws SQLException {
		resul.close();
	}

	public void actualizar(String actualiza) throws SQLException {
		this.sentencia = (Statement) conn.createStatement();
		sentencia.executeUpdate(actualiza);
	}

	public void borrar(String borra) throws SQLException {
		this.sentencia = (Statement) conn.createStatement();
		sentencia.executeUpdate(borra);
	}

	public int insertar(String inserta) throws SQLException {
		Statement st = (Statement) this.conn.createStatement();
		return st.executeUpdate(inserta);
	}

}
