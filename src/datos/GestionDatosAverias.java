package datos;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedList;

import equipos.*;
/**
 * Clase para gestionar los datos de la aplicaci�n, contiene m�todos est�ticos para guardar y recuperar los datos utilizando archivos
 * serializados. Incluye tambi�n m�todos para exportar e importar datos utilizando archivos CSV.
 * 
 * @version 0.5
 * @see ejecutable.PruebaGestionCiudad
 * @see equipos.Averia
 * @see equipos.Incidencia
 * @see equipos.Dispositivo
 * @see equipos.Farola
 * @see equipos.Camara
 * @see equipos.Semaforo
 * @author Daniel Pulido
 *
 */
public class GestionDatosAverias implements Serializable{
	/**
	 * Define el serialVerionUID, controla la compatibilidad entre versiones de los datos almacenados.
	 */
	private static final long serialVersionUID = 2983989609821545701L;
	
	private String rutaCompleta;
	private Averia[] averias;
	private Averia averia;
	
	/**
	 * @see equipo.Averia
	 * @param averia Objeto de tipo Averia que se almacenar�
	 * @param rutaCompleta Ruta completa, incluyendo el nombre de archivo, donde se guardar�n los datos 
	 */
	public GestionDatosAverias(Averia averia, String rutaCompleta) {
		this.averia = averia;
		this.rutaCompleta = rutaCompleta;
		this.averias = new Averia[0];
	}
	/**
	 * 
	 * @param averia Objeto de tipo Averia que se almacenar�
	 * @param rutaArchivo Ruta del directorio donde se almacenar�n los datos, no debe incluir el nombre de archivo
	 * @param nombreArchivo Nombre del archivo donde se almacenar�n los datos
	 */
	public GestionDatosAverias(Averia averia, String rutaArchivo, String nombreArchivo) {
		this.averia = averia;
		this.rutaCompleta = rutaArchivo+File.pathSeparator+nombreArchivo;
		this.averias = new Averia[0];
	}
	/**
	 * 
	 * @param averias Objeto de tipo Averia que se almacenar�
	 * @param rutaCompleta Ruta completa, incluyendo el nombre de archivo, donde se almacenar�n los datos.
	 */
	public GestionDatosAverias(Averia[] averias, String rutaCompleta) {
		if(averias!=null) {
			this.averias = averias;
		}else {
			this.averias = new Averia[0];
			System.out.println("La lista de aver�as es nula");
			}
		this.rutaCompleta = rutaCompleta;
	}
	/**
	 * 
	 * @param averias array de objetos tipo Averia que se guardar�.
	 * @param rutaArchivo Ruta del directorio donde se almacenar�n los datos, no debe incluir el nombre de archivo
	 * @param nombreArchivo Nombre del archivo donde se almacenar�n los datos
	 */
	public GestionDatosAverias(Averia[] averias, String rutaArchivo, String nombreArchivo) {
		if(averias!=null) {
			this.averias = averias;
		}else {
			this.averias = new Averia[0];
			System.out.println("La lista de aver�as es nula");		
		}
		this.rutaCompleta = rutaArchivo+File.pathSeparator+nombreArchivo;
	}
	// -------------------------------------------------- GETTERS AND SETTERS -----------------------------------
	
	public final String getRutaCompleta() {
		return rutaCompleta;
	}
	public final void setRutaCompleta(String rutaCompleta) {
		this.rutaCompleta = rutaCompleta;
	}
	public final Averia[] getAverias() {
		return averias;
	}
	public final void setAverias(Averia[] averias) {
		this.averias = averias;
	}
	public final Averia getAveria() {
		return averia;
	}
	public final void setAveria(Averia averia) {
		this.averia = averia;
	}
	public static final long getSerialversionuid() {
		return serialVersionUID;
	}
	// ---------------------------------------------- METODOS ----------------------------------------------
	/**
	 * Recupera las aver�as del archivo serializado que se le indica y las devuelve en un array Averia[] 
	 * @param rutaCompleta Ruta del archivo de donde se recuperan los datos, incluyendo el nombre del archivo.
	 * @return Devuelve un array <Averia> que contiene todas las aver�as almacenadas en el archivo de origen.
	 */
	public static Averia[] recuperarAverias(String rutaCompleta){
		Averia[] averiasRecuperadas= new Averia[0];
		try {
			FileInputStream archivo = new FileInputStream(rutaCompleta);
			ObjectInputStream entrada = new ObjectInputStream(archivo);			
			averiasRecuperadas = (Averia[]) entrada.readObject();
			entrada.close();
			archivo.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return averiasRecuperadas;
	}
	/**
	 *  Recupera las aver�as guardadas en el archivo serializado pasado en el par�metro, si existe, y le a�ade las pasadas en el 
	 *  par�metro aver�as. Si el archivo de destino no existe, crea un nuevo archivo donde se guardar�n los objetos serializados.
	 * @param averias par�metro tipo LinkedList<Averia> que contiene las aver�as a guardar.
	 * @param rutaCompleta String que contiene la ruta completa, incluyendo el nombre del archivo donde se guardar�n los datos
	 * @return Devuelve un valor true si la operaci�n se realiza correctamente, en caso contrario devuelve false
	 */
	public static boolean guardarAverias(LinkedList<Averia> averias, String rutaCompleta) {
		boolean guardado = false;
		try {
			
			File archivo = new File(rutaCompleta);
			if(archivo.exists()) {
				Averia[] averiasRecuperadas = recuperarAverias(rutaCompleta);			
				Averia[] averiasAGuardar = new Averia[averias.size()+averiasRecuperadas.length];
				// guardamos en el array las averias recuperadas del archivo de destino
				for(int i=0;i<averiasRecuperadas.length;i++) {
					averiasAGuardar[i]=averiasRecuperadas[i];
				}
				// guardamos en el array las averias pasadas en el parametro de entrada
				for(Averia a:averias) {
					averiasAGuardar[averias.indexOf(a)+averiasRecuperadas.length] = a;
				}
				ObjectOutputStream salida = new ObjectOutputStream(new FileOutputStream(rutaCompleta));
				salida.writeObject(averiasAGuardar);
				salida.close();
				guardado=true;
			}else {
				Averia[] averiasAGuardar = new Averia[averias.size()];
				for(Averia a:averias) {
					averiasAGuardar[averias.indexOf(a)] = a;
				}
				ObjectOutputStream salida = new ObjectOutputStream(new FileOutputStream(rutaCompleta));
				salida.writeObject(averiasAGuardar);
				salida.close();
				guardado=true;
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return guardado;
	}
	/**
	 * Recupera las aver�as guardadas en el archivo serializado pasado en el par�metro, si existe, y le a�ade la pasada en el 
	 *  par�metro aver�a. Si el archivo de destino no existe, crea un nuevo archivo donde se guardar�n los objetos serializados.
	 * @param averia Objeto de tipo aver�a que se a�adir� al archivo
	 * @param rutaCompleta Ruta completa, incluyendo el nombre de archivo donde se guardar�n los datos
	 * @return Devuelve un valor true si la operaci�n se realiza correctamente, en caso contrario devuelve false
	 */
	public static boolean guardarAverias(Averia averia, String rutaCompleta) {
		boolean guardado = false;
		try {
			
			File archivo = new File(rutaCompleta);
			if(archivo.exists()) {
				Averia[] averiasRecuperadas = recuperarAverias(rutaCompleta);			
				Averia[] averiasAGuardar = new Averia[1+averiasRecuperadas.length];
				// guardamos en el array las averias recuperadas del archivo de destino
				for(int i=0;i<averiasRecuperadas.length;i++) {
					averiasAGuardar[i]=averiasRecuperadas[i];
				}
				// guardamos en el array la averia pasadas en el parametro de entrada
				
				averiasAGuardar[averiasRecuperadas.length] = averia;
				
				ObjectOutputStream salida = new ObjectOutputStream(new FileOutputStream(rutaCompleta));
				salida.writeObject(averiasAGuardar);
				salida.close();
				guardado=true;
			}else {
				Averia[] averiasAGuardar = new Averia[1];
				averiasAGuardar[0] = averia;
				
				ObjectOutputStream salida = new ObjectOutputStream(new FileOutputStream(rutaCompleta));
				salida.writeObject(averiasAGuardar);
				salida.close();
				guardado=true;
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return guardado;
	}
	/**
	 * Guarda los datos de las averias pasadas en el array en un archivo csv llamado "datosAverias.csv" que se 
	 * crear� en la ruta indicada en el par�metro.
	 * (cada fila un registro con los campos separados por el car�cter ";"
	 * @param averias Array de objetos Averia a guardar.
	 * @param ruta Ruta del directorio donde se crear� el archivo, sin incluir el nombre del archivo ni el car�cter separador
	 * @return Devuelve la ruta del archivo de guardado si se lleva a cabo la operaci�n sin errores, en caso contrario devuelve 
	 * un String con el texto "false".
	 */
	public static String exportarAverias(Averia[] averias, String ruta) {
		String rutaCompleta = ruta+File.separator+"datosAverias.csv";
		String exportado="false";
		for(int i=2;new File(rutaCompleta).exists();i++) {
			rutaCompleta=ruta+File.separator+"datosAverias"+i+".csv";
		}
		try {
			FileWriter archivo = new FileWriter(rutaCompleta);
			BufferedWriter buffer = new BufferedWriter(archivo);
		
			if(averias == null) {
				System.out.println("Ha ocurrido un error al recibir las aver�as");
			}else {
				for(Averia a:averias) {
					String datos = Integer.toString(a.getCodigo())+";"+a.getCoste()+";"+a.getDescripcion()+";"+a.getFechaAlta()+";"+
						a.getFechaRep()+";"+Integer.toString(a.getGravedad())+";"+a.isResuelta()+"\n";
					buffer.write(datos);
				}
				buffer.flush();
				buffer.close();
				archivo.close();
				exportado = rutaCompleta;
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return exportado;
	}
	/**
	 * Importa las aver�as contenidas en un fichero csv, cada objeto Aver�a debe ser una fila, con los atributos separados por 
	 * el car�cter ";". Una vez importadas devuelve un ArrayList<Averia> que contiene todos los datos ya estructurados
	 * @param ruta ruta del directorio que contiene el archivo datosAverias.csv
	 * @return ArrayList<Averia> con los datos importados.
	 */
	public static ArrayList<Averia> importarAverias(String ruta) {
		ArrayList<Averia> averiasImportadas = new ArrayList<Averia>();
		String datosAveria;

		try {
			FileReader archivo = new FileReader(ruta+File.separator+"datosAverias.csv");
			BufferedReader buffer = new BufferedReader(archivo);
			while((datosAveria = buffer.readLine())!= null) {
				String[] datosFormateados = datosAveria.split(";");
				Averia a = new Averia(Integer.valueOf(datosFormateados[0]),Integer.valueOf(datosFormateados[5]), Double.valueOf(datosFormateados[1]), datosFormateados[3], datosFormateados[4],
						datosFormateados[2],Boolean.valueOf(datosFormateados[6]));
				averiasImportadas.add(a);
			}
			buffer.close();
			archivo.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return averiasImportadas;
	}
}
