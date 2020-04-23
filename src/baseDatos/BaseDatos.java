package baseDatos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JOptionPane;


/**
 * Gestiona todas las peticiones a la base de datos MySql.
 * @see java.sql.Connection
 * @author Daniel Pulido
 *
 */
public class BaseDatos {
	/**
	 * M�todo est�tico que crea una conexi�n con la base de datos con los datos pasados en los par�metros y devuelve un objeto Connection.
	 * @param ruta Establece la ruta del servidor de la base de datos, con valor null se establece la siguiente cadena: "jdbc:mysql://localhost:3306?serverTimezone=UTC"
	 * @param usuario Nombre de usuario con acceso a la base de datos.
	 * @param pass Contrase�a del usuario
	 * @return Devuelve un objeto Connection con la conexi�n creada utilizando los par�metros pasados.
	 */
	public static Connection conexion(String ruta, String usuario, String pass) {
		if(ruta == null) {
			ruta = "jdbc:mysql://localhost:3306?serverTimezone=UTC";
		}
		Connection conector = null;
		try {
			conector = DriverManager.getConnection("jdbc:mysql://localhost:3306?serverTimezone=UTC", usuario, pass);
			System.out.println("Conexi�n correcta al servidor");
			
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "No se ha podido conectar al servidor de base de datos, compruebe el usuario y la contrase�a");
			e.printStackTrace();
		}
		return conector;
	}
	/**
	 * Crea un statement utilizando la conexi�n pasada en el par�metro y ejecuta la consulta establecida, devuelve un objeto ResultSet que contiene los resultados de la consulta.
	 * @param consulta String que contiene la consulta a realizar a la base de datos.
	 * @param conexion objeto de tipo Connection creado con los datos de conexi�n.
	 * @see baseDatos#conexion()
	 * @see ResultSet
	 * @return Objeto de tipo ResultSet que contiene los datos devueltos por la base de datos
	 */
	public static ResultSet consulta(String consulta, Connection conexion) {
		ResultSet resultado = null;
		try {
			Statement miStatement = conexion.createStatement();
			resultado = miStatement.executeQuery(consulta);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return resultado;
	}
	public static void main(String args[]) {
		Connection conexion = conexion(null,"root", "1234");
		ResultSet resultado = consulta("show databases;", conexion);
		try {
			while(resultado.next()) {
				System.out.println(resultado.getString(1));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
