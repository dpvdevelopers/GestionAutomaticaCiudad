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

import equipos.Averia;
import equipos.Dispositivo;

/**
 * Clase para gestionar los datos de dispositivos, guardandolos o recuperandolos desde archivo y para importar/exportar a csv
 * @author Daniel Pulido
 *
 */
public class GestionDatosDispositivos implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -2885317546753642828L;
	
	/**
	 * Recupera los dispositivos almacenados en el archivo pasado por parametro, y los devuelve en un array
	 * @param rutaCompleta Ruta completa, incluyendo nombre de archivo de donde queremos recuperar los datos.
	 * @return Array de objetos "Dispositivo" que contiene la informaci�n recuperada del archivo
	 */
	public static Dispositivo[] recuperarDispositivos(String rutaCompleta){
		Dispositivo[] dispositivosRecuperados= new Dispositivo[0];
		try {
			FileInputStream archivo = new FileInputStream(rutaCompleta);
			ObjectInputStream entrada = new ObjectInputStream(archivo);			
			dispositivosRecuperados = (Dispositivo[]) entrada.readObject();
			entrada.close();
			archivo.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return dispositivosRecuperados;
	}
	/**
	 * Almacena en el archivo especificado en el par�metro los objetos "Dispositivo" que recibe en el array,
	 * si el archivo ya existe, primero recupera los datos y a�ade los nuevos al final, despu�s lo guarda.
	 * @param dispositivos Array que contiene los dispositivos a guardar
	 * @param rutaCompleta Ruta completa, incluyendo el nombre de archivo del fichero que contiene los datos
	 * @return Devuelve "true" si la operaci�n se realiza correctamente, en caso contrario devuelve "false"
	 */
	public static boolean guardarDispositivos(ArrayList<Dispositivo> dispositivos, String rutaCompleta) {
		boolean guardado = false;
		try {
			
			File archivo = new File(rutaCompleta);
			if(archivo.exists()) {
				Dispositivo[] dispositivosRecuperados = recuperarDispositivos(rutaCompleta);			
				Dispositivo[] dispositivosAGuardar = new Dispositivo[dispositivos.size()+dispositivosRecuperados.length];
				// guardamos en el array las averias recuperadas del archivo de destino
				for(int i=0;i<dispositivosRecuperados.length;i++) {
					dispositivosAGuardar[i]=dispositivosRecuperados[i];
				}
				// guardamos en el array las averias pasadas en el parametro de entrada
				for(Dispositivo d: dispositivos) {
					dispositivosAGuardar[dispositivos.indexOf(d)+dispositivosRecuperados.length] = d;
				}
				ObjectOutputStream salida = new ObjectOutputStream(new FileOutputStream(rutaCompleta));
				salida.writeObject(dispositivosAGuardar);
				salida.close();
				guardado=true;
			}else {
				Dispositivo[] dispositivosAGuardar = new Dispositivo[dispositivos.size()];
				for(Dispositivo d:dispositivos) {
					dispositivosAGuardar[dispositivos.indexOf(d)] = d;
				}
				ObjectOutputStream salida = new ObjectOutputStream(new FileOutputStream(rutaCompleta));
				salida.writeObject(dispositivosAGuardar);
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
	 * Recupera los dispositivos guardados en el archivo serializado pasado en el par�metro, si existe, y le a�ade el pasado en el 
	 *  par�metro dispositivo. Si el archivo de destino no existe, crea un nuevo archivo donde se guardar�n los objetos serializados.
	 * @param dispositivo Objeto de tipo "Dispositivo" que se a�adir� al archivo
	 * @param rutaCompleta Ruta completa, incluyendo el nombre de archivo donde se guardar�n los datos
	 * @return Devuelve un valor true si la operaci�n se realiza correctamente, en caso contrario devuelve false
	 */
	public static boolean guardarDispositivos(Dispositivo dispositivo, String rutaCompleta) {
		boolean guardado = false;
		try {
			
			File archivo = new File(rutaCompleta);
			if(archivo.exists()) {
				Dispositivo[] dispositivosRecuperados = recuperarDispositivos(rutaCompleta);			
				Dispositivo[] dispositivossAGuardar = new Dispositivo[1+dispositivosRecuperados.length];
				// guardamos en el array los dispositivos recuperados del archivo de destino
				for(int i=0;i<dispositivosRecuperados.length;i++) {
					dispositivossAGuardar[i]=dispositivosRecuperados[i];
				}
				// guardamos en el array el dispositivo pasado en el parametro de entrada
				
				dispositivossAGuardar[1+dispositivosRecuperados.length] = dispositivo;
				
				ObjectOutputStream salida = new ObjectOutputStream(new FileOutputStream(rutaCompleta));
				salida.writeObject(dispositivossAGuardar);
				salida.close();
				guardado=true;
			}else {
				Dispositivo[] dispositivosAGuardar = new Dispositivo[1];
				dispositivosAGuardar[1] = dispositivo;
				
				ObjectOutputStream salida = new ObjectOutputStream(new FileOutputStream(rutaCompleta));
				salida.writeObject(dispositivosAGuardar);
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
	 * Guarda los datos de los dispositivos pasados en el array en un archivo csv llamado "datosDispositivos.csv" que se 
	 * crear� en la ruta indicada en el par�metro.
	 * (cada fila un registro con los campos separados por el car�cter ";". Las aver�as del dispositivo se almacenan despu�s
	 * de los campos, solo se almacena el c�digo de cada aver�a, por lo que al guardar los datos se llama al m�todo "exportarAveria"
	 * almacenando las aver�as de cada dispositivo en un archivo independiente. La ruta completa de ese archivo ser� el �ltimo
	 * campo almacenado en los registros.
	 * @param dispositivos Array de objetos Dispositivo a guardar.
	 * @param ruta Ruta del directorio donde se crear� el archivo, sin incluir el nombre del archivo ni el car�cter separador
	 * @return Devuelve un valor true si se lleva a cabo la operaci�n sin errores, en caso contrario devuelve false.
	 */
	public static boolean exportarDatosDispositivos(Dispositivo[] dispositivos, String ruta) {
		String rutaCompleta = ruta+File.separator+"datosDispositivos.csv";
		boolean exportado=false;
		try {
			FileWriter archivo = new FileWriter(rutaCompleta);
			BufferedWriter buffer = new BufferedWriter(archivo);
		
			if(dispositivos == null) {
				System.out.println("Ha ocurrido un error al recibir los dispositivos");
			}else {
				for(Dispositivo d:dispositivos) {
					String averiasDispositivo;
					
					String datos = Integer.toString(d.getCodigo())+";"+d.getCoordenadas()+";"+d.getDescripcion()+";"+d.isOperativo()+";"+
						d.getFabricante()+";"+Double.toString(d.getPrecio())+";"+d.getHoraEnc()+";"+d.getHoraApag()+"\n";
					buffer.write(datos);
				}
				buffer.flush();
				buffer.close();
				archivo.close();
				exportado = true;
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return exportado;
	}
	/**
	 * Importa los dispositivos contenidos en un fichero csv, cada objeto Dispositivo debe ser una fila, con los atributos separados por 
	 * el car�cter ";". Una vez importados devuelve un ArrayList<Dispositivo> que contiene todos los datos ya estructurados
	 * @param ruta ruta del directorio que contiene el archivo datosDispositivos.csv
	 * @return ArrayList<Dispositivo> con los datos importados.
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



