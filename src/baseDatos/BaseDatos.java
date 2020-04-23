package baseDatos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.JOptionPane;


/**
 * Gestiona todas las peticiones a la base de datos MySql.
 * @see java.sql.Connection
 * @author Daniel Pulido
 *
 */
public class BaseDatos {

	/**
	 * Método estático que crea una conexión con la base de datos con los datos pasados en los parámetros y devuelve un objeto Connection.
	 * @param ruta Establece la ruta del servidor de la base de datos, con valor null se establece la siguiente cadena: "jdbc:mysql://localhost:3306?serverTimezone=UTC"
	 * @param usuario Nombre de usuario con acceso a la base de datos.
	 * @param pass Contraseña del usuario
	 * @return Devuelve un objeto Connection con la conexión creada utilizando los parámetros pasados.

	
	
	 */
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
	/**

	 * Crea un statement utilizando la conexión pasada en el parámetro y ejecuta la consulta establecida, devuelve un objeto ResultSet que contiene los resultados de la consulta.
	 * @param consulta String que contiene la consulta a realizar a la base de datos.
	 * @param conexion objeto de tipo Connection creado con los datos de conexión.
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
	public static boolean ejecuta(String sentencia, Connection conexion) {
		boolean ejecutada = false;
		String[] sentencias = sentencia.split(";");
		
		try {
			Statement miStatement = conexion.createStatement();
			for(String s:sentencias) {
				miStatement.addBatch(s);
			}
			miStatement.executeBatch();
			ejecutada = true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ejecutada;
	}
	public static boolean verificarBD(Connection conexion) {
		boolean existe = false;
		ResultSet resultado = consulta("show databases like 'gestionciudad';", conexion);
		try {
			// Si la base de datos existe, se devuelve true
			if(resultado.next()) {
				System.out.println("Conexión correcta a la base de datos.");
				existe = true;
			}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			System.out.println("Ha ocurrido un error al conectar a la base de datos.");
			e1.printStackTrace();
		}
		return existe;
	}
	public static boolean crearBD(Connection conexion) {
		boolean bdCreada = false;
		String scriptCreacionBD = "create database if not exists GestionCiudad; " + 
				"use gestionciudad; " + 
				"create table dispositivos(" + 
				"codigo int primary key not null," + 
				"descripcion varchar(75)," + 
				"encendido boolean," + 
				"hora_enc time," + 
				"hora_apag time," + 
				"fabricante varchar(50)," + 
				"precio double," + 
				"coordenadas varchar(25)" + 
				");" + 
				"create table farolas(" + 
				"codigo int primary key not null," + 
				"potencia int," + 
				"tipo_lampara varchar(25)," + 
				"constraint FK_FAROLAS_DISPOSITIVOS foreign key (codigo) references dispositivos (codigo)" + 
				");" + 
				"create table semaforos(" + 
				"codigo int primary key not null," + 
				"ambar boolean," + 
				"seg_am smallint," + 
				"seg_verde smallint," + 
				"seg_rojo smallint," + 
				"constraint FK_SEMAFOROS_DISPOSITIVOS foreign key (codigo) references dispositivos (codigo)" + 
				");" + 
				"create table camaras(" + 
				"codigo int primary key not null," + 
				"ang_horizontal smallint," + 
				"ang_vertical smallint," + 
				"movil boolean," + 
				"constraint FK_CAMARAS_DISPOSITIVOS foreign key (codigo) references dispositivos (codigo)" + 
				");" + 
				"create table usuarios(" + 
				"usuario varchar(25)," + 
				"pass varchar(25)," + 
				"nivel smallint" + 
				");" + 
				"create table personas(" + 
				"codigo int primary key not null," + 
				"nombre varchar(30)," + 
				"apellido1 varchar(30)," + 
				"apellido2 varchar(30)," + 
				"dni varchar(10)," + 
				"direccion varchar(75)," + 
				"fecha_nac date," + 
				"fecha_alta date," + 
				"telefono varchar(15)," + 
				"sueldo double," + 
				"cod_sup int," + 
				"cod_jefe int, " + 
				"activo boolean," + 
				"categoria varchar(20)," + 
				"departamento varchar(20)," + 
				"objetivo varchar(20)," + 
				"constraint FK_PERSONAS_SUPERVISOR foreign key (cod_sup) references personas (codigo)," + 
				"constraint FK_PERSONAS_JEFE foreign key (cod_jefe) references personas (codigo)" + 
				");" + 
				"create table incidencias(" + 
				"codigo int primary key not null," + 
				"descripcion varchar(75), " + 
				"fecha_alta date," + 
				"fecha_rep date," + 
				"prioridad smallint," + 
				"resuelta boolean," + 
				"coste double," + 
				"cod_encargado int," + 
				"constraint FK_INCIDENCIAS_PERSONAS foreign key (cod_encargado) references personas (codigo)" + 
				");" + 
				"create table averias(" + 
				"codigo int primary key not null," + 
				"descripcion varchar(75)," + 
				"fecha_alta date," + 
				"fecha_rep date," + 
				"gravedad smallint," + 
				"resuelta boolean," + 
				"coste double," + 
				"cod_incidencia int," + 
				"cod_tecnico int," + 
				"cod_dispositivo int," + 
				"constraint FK_AVERIA_INCIDENCIAS foreign key (cod_incidencia) references incidencias(codigo)," + 
				"constraint FK_AVERIA_PERSONAS foreign key (cod_tecnico) references personas (codigo)," + 
				"constraint FK_AVERIA_DISPOSITIVOS foreign key (cod_dispositivo) references dispositivos (codigo)" + 
				");"
				;
		ejecuta(scriptCreacionBD, conexion);
		return bdCreada;
	}
	public static String[][] recuperarDispositivos(Connection conexion) {
		String[][] datosDispositivos = null;
		ResultSet resultado = consulta("select d.codigo, 'Farola' as Tipo, d.descripcion, d.fabricante, d.encendido from gestionciudad.dispositivos d, " + 
				"gestionciudad.farolas f where d.codigo = f.codigo " + 
				" union" + 
				" select d.codigo, 'Semáforo' as Tipo, d.descripcion, d.fabricante, d.encendido from gestionciudad.dispositivos d," + 
				"  gestionciudad.semaforos s where d.codigo = s.codigo" + 
				" union" + 
				" select d.codigo, 'Cámara' as Tipo, d.descripcion, d.fabricante, d.encendido from  gestionciudad.dispositivos d," + 
				"  gestionciudad.camaras c where d.codigo = c.codigo ;", conexion);
		int i = 0;
		ArrayList<ArrayList> arrayRegistros = new ArrayList<ArrayList>();
		
		try {
			while(resultado.next()) {
				ArrayList<String> arrayResultados = new ArrayList<String>();
				for(int x=1;x<=5;x++) {
					
					arrayResultados.add(resultado.getString(x));
				}
				arrayRegistros.add(arrayResultados);
				
			}
			datosDispositivos = new String[arrayRegistros.size()][5];
			for(int z =0;z<arrayRegistros.size();z++) {
				
				for(int a = 0;a<5;a++) {
					datosDispositivos[z][a]= (String) arrayRegistros.get(z).get(a);
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return datosDispositivos;
	}
	public static String[][] recuperarNucleos(Connection conexion) {
		String[][] datosNucleos = null;
		ResultSet resultado = consulta("select codigo, tipo, nombre, time_format(h_enc,\"%H:%m\") \"H. Encendido\", time_format(h_apag,\"%H:%m\") \"H. Apagado\" from gestionciudad.nucleos where \r\n" + 
				"clase like \"via\"\r\n" + 
				"union\r\n" + 
				"select codigo, clase, nombre, time_format(h_enc,\"%H:%m\") \"H. Encendido\", time_format(h_apag,\"%H:%m\") \"H. Apagado\" from gestionciudad.nucleos where \r\n" + 
				"clase not like \"via\";", conexion);
		int i = 0;
		ArrayList<ArrayList> arrayRegistros = new ArrayList<ArrayList>();
		
		try {
			while(resultado.next()) {
				ArrayList<String> arrayResultados = new ArrayList<String>();
				for(int x=1;x<=5;x++) {
					
					arrayResultados.add(resultado.getString(x));
				}
				arrayRegistros.add(arrayResultados);
				
			}
			datosNucleos = new String[arrayRegistros.size()][5];
			for(int z =0;z<arrayRegistros.size();z++) {
				
				for(int a = 0;a<5;a++) {
					datosNucleos[z][a]= (String) arrayRegistros.get(z).get(a);
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		/*
		 * 
		 */
		return datosNucleos;
	}
	public static String[][] recuperarPersonas(Connection conexion) {
		String[][] datosPersonas = null;
		ResultSet resultado = consulta("select nombre, apellido1, apellido2, cod_sup, cod_jefe from gestionciudad.personas;", conexion);
		int i = 0;
		ArrayList<ArrayList> arrayRegistros = new ArrayList<ArrayList>();
		
		try {
			while(resultado.next()) {
				ArrayList<String> arrayResultados = new ArrayList<String>();
				for(int x=1;x<=5;x++) {
					
					arrayResultados.add(resultado.getString(x));
				}
				arrayRegistros.add(arrayResultados);
				
			}
			datosPersonas = new String[arrayRegistros.size()][5];
			for(int z =0;z<arrayRegistros.size();z++) {
				
				for(int a = 0;a<5;a++) {
					datosPersonas[z][a]= (String) arrayRegistros.get(z).get(a);
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return datosPersonas;
	}
	public static void main(String args[]) {
		Connection conexion = conexion(null,"root", "1234");
		if(!verificarBD(conexion)) {
			System.out.println("Como no existe, se crea la base de datos.");
			crearBD(conexion);
		}
		
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
