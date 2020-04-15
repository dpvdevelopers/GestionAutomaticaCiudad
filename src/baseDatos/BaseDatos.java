package baseDatos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JOptionPane;



public class BaseDatos {
	
	public static Connection conexion(String ruta, String usuario, String pass) {
		if(ruta == null) {
			ruta = "jdbc:mysql://localhost:3306?serverTimezone=UTC";
		}
		Connection conector = null;
		try {
			conector = DriverManager.getConnection("jdbc:mysql://localhost:3306?serverTimezone=UTC", usuario, pass);
			System.out.println("Conexión correcta al servidor");
			
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "No se ha podido conectar al servidor de base de datos, compruebe el usuario y la contraseña");
			e.printStackTrace();
		}
		return conector;
	}
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
