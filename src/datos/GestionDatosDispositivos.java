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
import equipos.Camara;
import equipos.Dispositivo;
import equipos.Farola;
import equipos.Semaforo;

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
	 * @return Array de objetos "Dispositivo" que contiene la información recuperada del archivo
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
	 * Almacena en el archivo especificado en el parámetro los objetos "Dispositivo" que recibe en el array,
	 * si el archivo ya existe, primero recupera los datos y añade los nuevos al final, después lo guarda.
	 * @param dispositivos Array que contiene los dispositivos a guardar
	 * @param rutaCompleta Ruta completa, incluyendo el nombre de archivo del fichero que contiene los datos
	 * @return Devuelve "true" si la operación se realiza correctamente, en caso contrario devuelve "false"
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
	 * Recupera los dispositivos guardados en el archivo serializado pasado en el parámetro, si existe, y le añade el pasado en el 
	 *  parámetro dispositivo. Si el archivo de destino no existe, crea un nuevo archivo donde se guardarán los objetos serializados.
	 * @param dispositivo Objeto de tipo "Dispositivo" que se añadirá al archivo
	 * @param rutaCompleta Ruta completa, incluyendo el nombre de archivo donde se guardarán los datos
	 * @return Devuelve un valor true si la operación se realiza correctamente, en caso contrario devuelve false
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
				
				dispositivossAGuardar[dispositivosRecuperados.length] = dispositivo;
				
				ObjectOutputStream salida = new ObjectOutputStream(new FileOutputStream(rutaCompleta));
				salida.writeObject(dispositivossAGuardar);
				salida.close();
				guardado=true;
			}else {
				Dispositivo[] dispositivosAGuardar = new Dispositivo[1];
				dispositivosAGuardar[0] = dispositivo;
				
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
	 * creará en la ruta indicada en el parámetro.
	 * (cada fila un registro con los campos separados por el carácter ";". Las averías del dispositivo se almacenan después
	 * de los campos, solo se almacena el código de cada avería, por lo que al guardar los datos se llama al método "exportarAveria"
	 * almacenando las averías de los dispositivos en un archivo independiente. La ruta completa de ese archivo será el último
	 * campo almacenado en los registros.
	 * @see GestionDatosAverias#exportarAverias(Averia[], String)
	 * @param dispositivos Array de objetos Dispositivo a guardar.
	 * @param ruta Ruta del directorio donde se creará el archivo, sin incluir el nombre del archivo ni el carácter separador
	 * @return Devuelve un valor true si se lleva a cabo la operación sin errores, en caso contrario devuelve false.
	 */
	public static String exportarDatosDispositivos(Dispositivo[] dispositivos, String ruta) {
		String rutaCompleta="";
		if(new File(ruta).isDirectory()) {
				rutaCompleta = ruta+File.separator+"datosDispositivos.csv";
		}else {
			rutaCompleta = ruta;
		}
		ArrayList<Dispositivo> dispositivosAGuardar= new ArrayList<Dispositivo>();
		if(new File(rutaCompleta).exists()) {
			dispositivosAGuardar = importarDispositivos(ruta);
		}
		for(int i=2;new File(rutaCompleta).exists();i++) {
			rutaCompleta=ruta+File.separator+"datosDispositivos"+i+".csv";
		}
		try {
			if(dispositivos!=null) {
				for(Dispositivo d:dispositivos) {
					dispositivosAGuardar.add(d);
				}
			}
			FileWriter archivo = new FileWriter(rutaCompleta);
			BufferedWriter buffer = new BufferedWriter(archivo);
			//int totalAverias = 0;
			if(dispositivos == null) {
				System.out.println("Ha ocurrido un error al recibir los dispositivos");
			}else {
				String datos="";
				for(Dispositivo d:dispositivosAGuardar) {
					switch (d.getClass().getName()) {
						case "equipos.Semaforo":
							Semaforo s = (Semaforo) d;
							datos = Integer.toString(d.getCodigo())+";"+d.getCoordenadas()+";"+d.getDescripcion()+";"+d.isOperativo()+";"+
									d.getFabricante()+";"+Double.toString(d.getPrecio())+";"+d.getHoraEnc()+";"+d.getHoraApag()+";"+s.isAmbar()+";"+
									Integer.toString(s.getSegAmarillo())+";"+Integer.toString(s.getSegRojo())+";"+Integer.toString(s.getSegVerde());
								for(Averia a:d.getAverias()) {
									datos = datos +";"+ Integer.toString(a.getCodigo())+";"+a.getCoste()+";"+a.getDescripcion()+";"+a.getFechaAlta()+";"+
											a.getFechaRep()+";"+Integer.toString(a.getGravedad())+";"+a.isResuelta();
								}
							break;
						case "equipos.Farola":
							Farola f = (Farola) d;
							datos = Integer.toString(d.getCodigo())+";"+d.getCoordenadas()+";"+d.getDescripcion()+";"+d.isOperativo()+";"+
									d.getFabricante()+";"+Double.toString(d.getPrecio())+";"+d.getHoraEnc()+";"+d.getHoraApag()+";"+
									Integer.toString(f.getPotencia())+";"+f.getTipoLampara();
							break;
						case "equipos.Camara":
							Camara c = (Camara) d;
							datos = Integer.toString(d.getCodigo())+";"+d.getCoordenadas()+";"+d.getDescripcion()+";"+d.isOperativo()+";"+
									d.getFabricante()+";"+Double.toString(d.getPrecio())+";"+d.getHoraEnc()+";"+d.getHoraApag()+";"+
									Integer.toString(c.getAngHorizontal())+";"+c.getAngVertical()+";"+Boolean.toString(c.isMovil());
							break;
					}
				
					
					for(Averia a:d.getAverias()) {
						datos = datos +";"+ Integer.toString(a.getCodigo())+";"+a.getCoste()+";"+a.getDescripcion()+";"+a.getFechaAlta()+";"+
								a.getFechaRep()+";"+Integer.toString(a.getGravedad())+";"+a.isResuelta();
						//totalAverias++;
					}
					
					datos = datos + ";"+d.getClass().getName()+"\n";
					buffer.write(datos);
				}
				
				buffer.flush();
				buffer.close();
				archivo.close();
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return rutaCompleta;
	}
	/**
	 * Importa los dispositivos contenidos en un fichero csv, cada objeto Dispositivo debe ser una fila, con los atributos separados por 
	 * el carácter ";". Una vez importados devuelve un ArrayList<Dispositivo> que contiene todos los datos ya estructurados
	 * @param ruta ruta del directorio que contiene el archivo datosDispositivos.csv
	 * @return ArrayList<Dispositivo> con los datos importados.
	 */
	public static ArrayList<Dispositivo> importarDispositivos(String ruta) {
		ArrayList<Dispositivo> dispositivosImportados = new ArrayList<Dispositivo>();
		String datosDispositivo;
		String rutaCompleta="";
		if(new File(ruta).isDirectory()) {
				rutaCompleta = ruta+File.separator+"datosDispositivos.csv";
		}else {
			rutaCompleta = ruta;
		}

		try {
			FileReader archivo = new FileReader(rutaCompleta);
			BufferedReader buffer = new BufferedReader(archivo);
			while((datosDispositivo = buffer.readLine())!= null) {
				String[] datosFormateados = datosDispositivo.split(";");
				int numeroAverias = 0;
				
				
				switch (datosFormateados[datosFormateados.length-1]) {
					case "equipos.Semaforo":
						if((datosFormateados.length - 12)>0) {
							numeroAverias = (datosFormateados.length - 12)/7;
						}
						LinkedList<Averia> averias = new LinkedList<Averia>();
						for(int i=0;i<numeroAverias;i++) {
							Averia a = new Averia(Integer.valueOf(datosFormateados[(i*7)+12]),Integer.valueOf(datosFormateados[12+5+(7*i)]), 
									Double.valueOf(datosFormateados[12+1+(i*7)]), datosFormateados[12+3+(i*7)], datosFormateados[12+4+(i*7)],
									datosFormateados[12+2+(i*7)],Boolean.valueOf(datosFormateados[12+6+(i*7)]));
							averias.add(a);
						}
						Semaforo s = new Semaforo(Integer.valueOf(datosFormateados[0]),Double.valueOf(datosFormateados[5]),
							datosFormateados[2], datosFormateados[1], datosFormateados[4], datosFormateados[6], datosFormateados[7],
							Boolean.valueOf(datosFormateados[3]),averias, Boolean.valueOf(datosFormateados[8]), Integer.valueOf(datosFormateados[11]),
							Integer.valueOf(datosFormateados[9]),Integer.valueOf(datosFormateados[10]));
						dispositivosImportados.add(s);
						break;
					case "equipos.Farola":
						if((datosFormateados.length - 10)>0) {
							numeroAverias = (datosFormateados.length - 10)/7;
						}
						LinkedList<Averia> averias2 = new LinkedList<Averia>();
						for(int i=0;i<numeroAverias;i++) {
							Averia a = new Averia(Integer.valueOf(datosFormateados[(i*7)+10]),Integer.valueOf(datosFormateados[10+5+(7*i)]), 
									Double.valueOf(datosFormateados[10+1+(i*7)]), datosFormateados[10+3+(i*7)], datosFormateados[10+4+(i*7)],
									datosFormateados[10+2+(i*7)],Boolean.valueOf(datosFormateados[10+6+(i*7)]));
							averias2.add(a);
						}
						Farola f = new Farola(Integer.valueOf(datosFormateados[0]),Double.valueOf(datosFormateados[5]),
							datosFormateados[2], datosFormateados[1], datosFormateados[4], datosFormateados[6], datosFormateados[7],
							Boolean.valueOf(datosFormateados[3]),averias2, Integer.valueOf(datosFormateados[8]), datosFormateados[9]);
						dispositivosImportados.add(f);
						break;
					case "equipos.Camara":
						if((datosFormateados.length - 11)>0) {
							numeroAverias = (datosFormateados.length - 11)/7;
						}
						LinkedList<Averia> averias3 = new LinkedList<Averia>();
						for(int i=0;i<numeroAverias;i++) {
							Averia a = new Averia(Integer.valueOf(datosFormateados[(i*7)+11]),Integer.valueOf(datosFormateados[11+5+(7*i)]), 
									Double.valueOf(datosFormateados[11+1+(i*7)]), datosFormateados[11+3+(i*7)], datosFormateados[11+4+(i*7)],
									datosFormateados[11+2+(i*7)],Boolean.valueOf(datosFormateados[11+6+(i*7)]));
							averias3.add(a);
						}
						Camara c = new Camara(Integer.valueOf(datosFormateados[0]),Double.valueOf(datosFormateados[5]),
							datosFormateados[2], datosFormateados[1], datosFormateados[4], datosFormateados[6], datosFormateados[7],
							Boolean.valueOf(datosFormateados[3]),averias3, Boolean.valueOf(datosFormateados[10]), Integer.valueOf(datosFormateados[8]), 
							Integer.valueOf(datosFormateados[9]));
						dispositivosImportados.add(c);
						break;
				}
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
		return dispositivosImportados;
	}
}



