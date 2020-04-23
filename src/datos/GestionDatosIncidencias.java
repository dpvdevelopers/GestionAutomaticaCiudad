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

import equipos.Averia;
import equipos.Incidencia;
/**
 * Clase para gestionar los datos de las incidencias, contiene métodos estáticos para guardar y recuperar de archivos serializados y para importar y exportar de archivos CSV
 * @author Daniel Pulido
 * @see equipos.Incidencia
 *
 */
public class GestionDatosIncidencias implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 5705796219400783971L;
/**
 * Método estático, guarda un objeto de tipo Incidencia en el archivo serializado establecido en la ruta
 * @param incidencia Objeto Incidencia que se va a guardar
 * @param rutaCompleta Ruta, incluyendo el nombre del archivo, donde se va a guardar el objeto, si el archivo de destino ya existe, la nueva Incidencia se almacena al final.
 * @see GestionDatosIncidencias#recuperarIncidencias()
 * @return Devuelve true si se ha completado correctamente y false en caso contrario.
 */
	public static boolean guardarIncidencia(Incidencia incidencia, String rutaCompleta) {
		boolean guardado = false;
		
		try {
			
			File archivo = new File(rutaCompleta);
			if(archivo.exists()) {
				Incidencia[] averiasRecuperadas = recuperarIncidencias(rutaCompleta);			
				Incidencia[] incidenciasAGuardar = new Incidencia[1+averiasRecuperadas.length];
				// guardamos en el array las averias recuperadas del archivo de destino
				for(int i=0;i<averiasRecuperadas.length;i++) {
					incidenciasAGuardar[i]=averiasRecuperadas[i];
				}
				// guardamos en el array la averia pasadas en el parametro de entrada
				
				incidenciasAGuardar[averiasRecuperadas.length] = incidencia;
				
				ObjectOutputStream salida = new ObjectOutputStream(new FileOutputStream(rutaCompleta));
				salida.writeObject(incidenciasAGuardar);
				salida.close();
				guardado=true;
			}else {
				Incidencia[] incidenciasAGuardar = new Incidencia[1];
				incidenciasAGuardar[0] = incidencia;
				
				ObjectOutputStream salida = new ObjectOutputStream(new FileOutputStream(rutaCompleta));
				salida.writeObject(incidenciasAGuardar);
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
	 * Recupera los objetos de tipo Incidencia de un archivo y los devuelve en un array Incidencia[]
	 * @param rutaCompleta Ruta del archivo de origen de datos, incluyendo el nombre del propio archivo.
	 * @return Devuelve un array de objetos de tipo Incidencia
	 */
	public static Incidencia[] recuperarIncidencias(String rutaCompleta) {
		Incidencia[] incidenciasRecuperadas= new Incidencia[0];
		try {
			FileInputStream archivo = new FileInputStream(rutaCompleta);
			ObjectInputStream entrada = new ObjectInputStream(archivo);			
			incidenciasRecuperadas = (Incidencia[]) entrada.readObject();
			entrada.close();
			archivo.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return incidenciasRecuperadas;
	}
	/**
	 * Método estático para guardar varios objetos de tipo Incidencia, recibe un LinkedList<Incidencia> y lo añade al contenido del archivo.
	 * @param incidencias LinkedList<Incidencia> que contiene los objetos a guardar
	 * @param rutaCompleta Ruta completa del archivo, si existe los objetos se añadirán, si no existe se creará uno nuevo
	 * @return Devuelve true si la operación se realiza correctamente y false en caso contrario.
	 */
	public static boolean guardarIncidencias(LinkedList<Incidencia> incidencias, String rutaCompleta) {
		boolean guardado = false;
		try {
			
			File archivo = new File(rutaCompleta);
			if(archivo.exists()) {
				Incidencia[] incidenciasRecuperadas = recuperarIncidencias(rutaCompleta);			
				Incidencia[] incidenciasAGuardar = new Incidencia[incidencias.size()+incidenciasRecuperadas.length];
				// guardamos en el array las averias recuperadas del archivo de destino
				for(int i=0;i<incidenciasRecuperadas.length;i++) {
					incidenciasAGuardar[i]=incidenciasRecuperadas[i];
				}
				// guardamos en el array las averias pasadas en el parametro de entrada
				for(Incidencia a:incidencias) {
					incidenciasAGuardar[incidencias.indexOf(a)+incidenciasRecuperadas.length] = a;
				}
				ObjectOutputStream salida = new ObjectOutputStream(new FileOutputStream(rutaCompleta));
				salida.writeObject(incidenciasAGuardar);
				salida.close();
				guardado=true;
			}else {
				Incidencia[] incidenciasAGuardar = new Incidencia[incidencias.size()];
				for(Incidencia a:incidencias) {
					incidenciasAGuardar[incidencias.indexOf(a)] = a;
				}
				ObjectOutputStream salida = new ObjectOutputStream(new FileOutputStream(rutaCompleta));
				salida.writeObject(incidenciasAGuardar);
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
	 * Método estático para exportar objetos de tipo Incidencia a un archivo "incidencias.csv", si el archivo ya existe en la ruta, los datos se añadirán al final.
	 * @param incidencias Array de objetos Incidencia que contiene los datos que se almacenarán.
	 * @param ruta Ruta del directorio que contiene o contendrá el archivo "incidencias.csv", si el directorio o el archivo no existen, se crearán.
	 * @return Devuelve una cadena String con la ruta completa, incluyendo el nombre de archivo, donde se han guardado los datos.
	 */
	public static String exportarIncidencias(Incidencia[] incidencias, String ruta) {
		File rutaDirectorio = new File(ruta);
		if(!rutaDirectorio.exists()) {
			rutaDirectorio.mkdir();
		}
		String rutaCompleta = ruta+File.separator+"incidencias.csv";
		
		if(incidencias == null) {
			System.out.println("Ha ocurrido un error al recibir las incidencias a exportar.");
		}else {
			
			if(new File(rutaCompleta).exists()) {
				ArrayList<Incidencia> incidenciasRecuperadas = importarIncidencias(ruta);
				Incidencia[] incidenciasAGuardar = new Incidencia[incidenciasRecuperadas.size()+incidencias.length];
				for(int i=0;i<incidenciasAGuardar.length;i++) {
					if(i<incidenciasRecuperadas.size()) {
						incidenciasAGuardar[i]=incidenciasRecuperadas.get(i);
					}else {
						incidenciasAGuardar[i]=incidencias[i-incidenciasRecuperadas.size()];
					}
				}
				try {
					FileWriter archivo = new FileWriter(rutaCompleta);
					BufferedWriter buffer = new BufferedWriter(archivo);
					for(Incidencia i:incidenciasAGuardar) {
						String datos = Integer.toString(i.getCodigo())+";"+Double.toString(i.getCoste())+";"+i.getDescripcion()+
								";"+i.getFechaAlta()+";"+i.getFechaRep()+";"+Integer.toString(i.getPrioridad())+";"+
								Boolean.toString(i.isResuelta());
						for(Averia a: i.getAverias()) {
							datos = datos+";"+ Integer.toString(a.getCodigo())+";"+a.getCoste()+";"+a.getDescripcion()+";"+a.getFechaAlta()+";"+
									a.getFechaRep()+";"+Integer.toString(a.getGravedad())+";"+a.isResuelta();
						}
						datos = datos+"\n";
						buffer.write(datos);
					}
					buffer.flush();
					buffer.close();
					archivo.close();
				}catch(IOException e) {
					e.printStackTrace();
				}
			}else {
				Incidencia[] incidenciasAGuardar = new Incidencia[incidencias.length];
				for(int i=0;i<incidenciasAGuardar.length;i++) {
					
						incidenciasAGuardar[i]=incidencias[i];
					
				}
				try {
					FileWriter archivo = new FileWriter(rutaCompleta);
					BufferedWriter buffer = new BufferedWriter(archivo);
					for(Incidencia i:incidenciasAGuardar) {
						String datos = Integer.toString(i.getCodigo())+";"+Double.toString(i.getCoste())+";"+i.getDescripcion()+
								";"+i.getFechaAlta()+";"+i.getFechaRep()+";"+Integer.toString(i.getPrioridad())+";"+
								Boolean.toString(i.isResuelta());
						if(!i.getAverias().isEmpty()) {
							for(Averia a: i.getAverias()) {
								datos = datos+ Integer.toString(a.getCodigo())+";"+a.getCoste()+";"+a.getDescripcion()+";"+a.getFechaAlta()+";"+
										a.getFechaRep()+";"+Integer.toString(a.getGravedad())+";"+a.isResuelta();
							}
						}
						datos = datos+"\n";
						buffer.write(datos);
					}
					buffer.flush();
					buffer.close();
					archivo.close();
				}catch(IOException e) {
					e.printStackTrace();
				}
			}
		}
		return rutaCompleta;
	}
	/**
	 * Método para recuperar objetos de tipo Incidencia de un archivo "incidencias.csv" del directorio establecido en la ruta, el archivo debe haber sido creado con el método exportarIncidencias, o seguir la misma estructura de datos.
	 * @param ruta Ruta del directorio que contiene el archivo "incidencias.csv".
	 * @see GestionDatosIncidencias#exportarIncidencias(Incidencia[], String)
	 * @return Devuelve un ArrayList<Incidencia> que contiene los objetos recuperados.
	 */
	public static ArrayList<Incidencia> importarIncidencias(String ruta) {
		ArrayList<Incidencia> incidenciasImportadas = new ArrayList<Incidencia>();
		String rutaCompleta = ruta + File.separator+"incidencias.csv";
		
		String datos = "";
		try {
			FileReader archivo = new FileReader(rutaCompleta);
			BufferedReader buffer = new BufferedReader(archivo);
			while((datos=buffer.readLine())!=null) {
				String[] datosFormateados = datos.split(";");
				LinkedList<Averia> averias = new LinkedList<Averia>();
				if(datosFormateados.length>7) {
					int numAverias = (datosFormateados.length-7)/7;
					
					for(int i=0;i<numAverias;i++) {
						Averia a = new Averia(Integer.valueOf(datosFormateados[7+(i*7)]),Integer.valueOf(datosFormateados[7+5+(i*7)]),
							Double.valueOf(datosFormateados[7+1+(i*7)]),datosFormateados[7+3+(i*7)], datosFormateados[7+4+(i*7)],
							datosFormateados[7+2+(i*7)], Boolean.valueOf(datosFormateados[7+6+(i*7)]));
						averias.add(a);
					}					
				}
				Incidencia incidencia = new Incidencia(Integer.valueOf(datosFormateados[0]),Double.valueOf(datosFormateados[1]),
						datosFormateados[2],datosFormateados[3],datosFormateados[4], Integer.valueOf(datosFormateados[5]),
						Boolean.valueOf(datosFormateados[6]), averias);
				incidenciasImportadas.add(incidencia);
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
		
		return incidenciasImportadas;
	}
}
