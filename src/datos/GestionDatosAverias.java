package datos;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.LinkedList;

import equipos.*;
/**
 * Clase para gestionar los datos de la aplicaci�n, tiene m�todos para guardar y recuperar los datos utilizando archivos
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
	/*private LinkedList<Incidencia> incidencias;
	private Incidencia incidencia;
	private LinkedList<Dispositivo> dispositivos;
	private Dispositivo dispositivo;
	private LinkedList<Persona> personas;
	private Persona persona;
	private LinkedList<Nucleo> nucleos;
	private Nucleo nucleo;
	int tipoDato; // Se modifica seg�n el tipo de dato que recibe el constructor como par�metro, seg�n el tipo de dato los m�todos utilizar�n un algoritmo diferente.
	*/
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
	 * Recupera las aver�as de un archivo serializado 
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
	 *  Recupera las aver�as guardadas en el archivo pasado en el par�metro, si existe, y le a�ade las pasadas en el 
	 *  par�metro aver�as. Si el archivo de destino no existe, crea uno nuevo.
	 * @param averias
	 * @param rutaCompleta
	 * @return
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
}
