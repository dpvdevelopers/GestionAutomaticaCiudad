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

import equipos.Dispositivo;
import ubicaciones.Barrio;
import ubicaciones.Distrito;
import ubicaciones.Municipio;
import ubicaciones.Nucleo;
import ubicaciones.TipoVia;
import ubicaciones.Via;
/**
 * Clase para almacenar y recuperar objetos de tipo Nucleo de archivos serializados o "csv".
 * @author Daniel Pulido
 *
 */
public class GestionDatosNucleos implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 2548389507846028757L;
/**
 * Recupera los objetos tipo Nucleo contenidos en el archivo serializado establecido en el parámetro rutaCompleta.
 * @param rutaCompleta Ruta, incluyendo el nombre de archivo, del origen de datos.
 * @return Devuelve un Array Nucleo[] que contiene todos los objetos recuperados.
 */
	public static Nucleo[] recuperarNucleos(String rutaCompleta){
		Nucleo[] nucleosRecuperados= new Nucleo[0];
		try {
			FileInputStream archivo = new FileInputStream(rutaCompleta);
			ObjectInputStream entrada = new ObjectInputStream(archivo);			
			nucleosRecuperados = (Nucleo[]) entrada.readObject();
			entrada.close();
			archivo.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return nucleosRecuperados;
	}
	/**
	 * Método para almacenar objetos de tipo Nucleo en el archivo serializado indicado en el parámetro rutaCompleta, si el archivo ya existe, los datos se añaden al final.
	 * @param nucleos LinkedList<Nucleo> que contiene los objetos Nucleo a guardar
	 * @param rutaCompleta Ruta, incluyendo el nombre de archivo, donde se guardarán los objetos
	 * @return Devuelve true si la operación se ejecuta correctamente, false en caso contrario.
	 */
	public static boolean guardarNucleos(LinkedList<Nucleo> nucleos, String rutaCompleta) {
		boolean guardado = false;
		try {
			
			File archivo = new File(rutaCompleta);
			if(archivo.exists()) {
				Nucleo[] nucleosRecuperados = recuperarNucleos(rutaCompleta);			
				Nucleo[] nucleosAGuardar = new Nucleo[nucleos.size()+nucleosRecuperados.length];
				// guardamos en el array las averias recuperadas del archivo de destino
				for(int i=0;i<nucleosRecuperados.length;i++) {
					nucleosAGuardar[i]=nucleosRecuperados[i];
				}
				// guardamos en el array las averias pasadas en el parametro de entrada
				for(Nucleo a:nucleos) {
					nucleosAGuardar[nucleos.indexOf(a)+nucleosRecuperados.length] = a;
				}
				ObjectOutputStream salida = new ObjectOutputStream(new FileOutputStream(rutaCompleta));
				salida.writeObject(nucleosAGuardar);
				salida.close();
				guardado=true;
			}else {
				Nucleo[] nucleosAGuardar = new Nucleo[nucleos.size()];
				for(Nucleo a:nucleos) {
					nucleosAGuardar[nucleos.indexOf(a)] = a;
				}
				ObjectOutputStream salida = new ObjectOutputStream(new FileOutputStream(rutaCompleta));
				salida.writeObject(nucleosAGuardar);
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
	 * Guarda un único objeto tipo Nucleo en el archivo indicado en rutaCompleta, si el archivo existe, el núcleo se añade al final.
	 * @param nucleos Un único objeto de tipo Nucleo que se añadirá al archivo.
	 * @param rutaCompleta Ruta completa, incluyendo el nombre de archivo, donde se guardará el objeto.
	 * @return Devuelve true si la operación es correcta, false en caso contrario.
	 */
	public static boolean guardarNucleos(Nucleo nucleos, String rutaCompleta) {
		boolean guardado = false;
		try {
			
			File archivo = new File(rutaCompleta);
			if(archivo.exists()) {
				Nucleo[] nucleosRecuperados = recuperarNucleos(rutaCompleta);			
				Nucleo[] nucleosAGuardar = new Nucleo[1+nucleosRecuperados.length];
				// guardamos en el array las averias recuperadas del archivo de destino
				for(int i=0;i<nucleosRecuperados.length;i++) {
					nucleosAGuardar[i]=nucleosRecuperados[i];
				}
				// guardamos en el array la averia pasadas en el parametro de entrada
				
				nucleosAGuardar[nucleosRecuperados.length] = nucleos;
				
				ObjectOutputStream salida = new ObjectOutputStream(new FileOutputStream(rutaCompleta));
				salida.writeObject(nucleosAGuardar);
				salida.close();
				guardado=true;
			}else {
				Nucleo[] nucleosAGuardar = new Nucleo[1];
				nucleosAGuardar[0] = nucleos;
				
				ObjectOutputStream salida = new ObjectOutputStream(new FileOutputStream(rutaCompleta));
				salida.writeObject(nucleosAGuardar);
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
	 * Exporta los objetos tipo Nucleo contenidos en el array a un archivo "nucleos.csv" en la ruta incluida en el parámetro ruta, si la ruta no existe, se crea.
	 * @param nucleos Array que contiene los objetos Nucleo que se guardarán. 
	 * @param ruta Ruta del directorio que contendrá el archivo nucleos.csv con los datos almacenados.
	 * @return Devuelve la ruta completa (incluyendo el nombre de archivo) donde se han almacenado los datos.
	 */
	public static String exportarNucleos(Nucleo[] nucleos, String ruta) {
		File directorio = new File(ruta);
		if(!directorio.exists()) {
			directorio.mkdir();
		}
		String rutaCompleta="";
		if(new File(ruta).isDirectory()) {
			rutaCompleta = ruta+File.separator+"nucleos.csv";
		}else {
			rutaCompleta = ruta;
		}
		try {
			if(nucleos==null) {
				System.out.println("Ha ocurrido un error al recibir los nucleos");
			}else {
				String datos = "";
				LinkedList<Nucleo> nucleosAGuardar = new LinkedList<Nucleo>();
				if(new File(rutaCompleta).exists()) {
					nucleosAGuardar = importarNucleos(ruta);
				}
				for(int i=0;i<nucleos.length;i++) {
					nucleosAGuardar.addLast(nucleos[i]);
				}
				FileWriter archivo = new FileWriter(rutaCompleta);
				BufferedWriter buffer = new BufferedWriter(archivo);
				for(Nucleo n: nucleos) {
					datos = Integer.toString(n.getCodigo())+";"+n.getNombre()+";"+n.getHoraEnc()+";"+n.getHoraApag();
					switch (n.getClass().getName()) {
						
						case "ubicaciones.Via":
							LinkedList<Dispositivo> dispositivos = new LinkedList<Dispositivo>();
							Via v = new Via((Via) n);
							datos=datos+";"+v.getTipo();
							if(!v.getDispositivos().isEmpty()) {
								for(Dispositivo d:v.getDispositivos()) {
									datos=datos+";"+d.getCodigo();
									dispositivos.add(d);
								}
								Dispositivo[] dispositivos2 = new Dispositivo[dispositivos.size()];
								for(int i=0;i<dispositivos.size();i++) {
									dispositivos2[i] = dispositivos.get(i);
								}
								File rutaDestino = new File(ruta+File.separator+v.getCodigo());
								if(!rutaDestino.exists()) {
									rutaDestino.mkdir();
								}
								datos= datos+";"+GestionDatosDispositivos.exportarDatosDispositivos(dispositivos2, (ruta+File.separator+v.getCodigo()));
							}
							datos = datos +";"+n.getClass().getName()+"\n";
							buffer.write(datos);
							break;
						case "ubicaciones.Barrio":
							LinkedList<Via> vias = new LinkedList<Via>();
							Barrio b = new Barrio((Barrio) n);
							if(!b.getVias().isEmpty()) {
								for(Via via:b.getVias()) {
									datos=datos+";"+via.getCodigo();
									vias.add(via);
								}
								Via[] vias2 = new Via[vias.size()];
								for(int i=0;i<vias.size();i++) {
									vias2[i] = vias.get(i);
								}
								File rutaDestino = new File(ruta+File.separator+b.getCodigo());
								if(!rutaDestino.exists()) {
									rutaDestino.mkdir();
								}
								datos= datos+";"+GestionDatosNucleos.exportarNucleos(vias2, ruta+File.separator+b.getCodigo());
							}
							datos = datos +";"+n.getClass().getName()+"\n";
							buffer.write(datos);
							
							break;
						case "ubicaciones.Distrito":
							LinkedList<Barrio> barrios = new LinkedList<Barrio>();
							Distrito d = new Distrito((Distrito) n);
							if(!d.getBarrios().isEmpty()) {
								for(Barrio barrio:d.getBarrios()) {
									datos=datos+";"+barrio.getCodigo();
									barrios.add(barrio);
								}
								Barrio[] barrio2 = new Barrio[barrios.size()];
								for(int i=0;i<barrios.size();i++) {
									barrio2[i] = barrios.get(i);
								}
								File rutaDestino = new File(ruta+File.separator+d.getCodigo());
								if(!rutaDestino.exists()) {
									rutaDestino.mkdir();
								}
								datos= datos+";"+GestionDatosNucleos.exportarNucleos(barrio2, ruta+File.separator+d.getCodigo());
							}
							datos = datos +";"+n.getClass().getName()+"\n";
							buffer.write(datos);
							
							break;
						case "ubicaciones.Municipio":
							LinkedList<Distrito> distritos = new LinkedList<Distrito>();
							Municipio m = new Municipio((Municipio) n);
							if(!m.getDistritos().isEmpty()) {
								for(Distrito distrito:m.getDistritos()) {
									datos=datos+";"+distrito.getCodigo();
									distritos.add(distrito);
								}
								Distrito[] distritos2 = new Distrito[distritos.size()];
								for(int i=0;i<distritos.size();i++) {
									distritos2[i] = distritos.get(i);
								}
								File rutaDestino = new File(ruta+File.separator+m.getCodigo());
								if(!rutaDestino.exists()) {
									rutaDestino.mkdir();
								}
								datos= datos+";"+GestionDatosNucleos.exportarNucleos(distritos2, ruta+File.separator+m.getCodigo());
							}
							
							datos = datos +";"+n.getClass().getName()+"\n";
							buffer.write(datos);
							break;
					}
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
	 * Importa objetos tipo Nucleo de un archivo nucleos.csv y lo devuelve dentro de un objeto LinkedList<Nucleo>
	 * @param ruta Ruta que contiene el archivo nucleos.csv.
	 * @return Devuelve los datos recuperados dentro de un objeto LinkedList<Nucleo>
	 */
	public static LinkedList<Nucleo> importarNucleos(String ruta){
		LinkedList<Nucleo> nucleosImportados = new LinkedList<Nucleo>();
		String rutaCompleta="";
		if(new File(ruta).isDirectory()) {
			rutaCompleta = ruta+File.separator+"nucleos.csv";
		}else {
			rutaCompleta = ruta;
		}
		if(!new File(rutaCompleta).exists()) {
			System.out.println("No se ha encontrado el archivo, por favor, revise la ruta");
		}else {
			try {
				FileReader archivo = new FileReader(rutaCompleta);
				BufferedReader buffer = new BufferedReader(archivo);
				String datos="";
				while((datos = buffer.readLine())!=null) {
					String[] datosFormateados = datos.split(";");
					
						switch(datosFormateados[datosFormateados.length-1]) {
							case "ubicaciones.Via":
								// Integer.toString(n.getCodigo())+";"+n.getNombre()+";"+n.getHoraEnc()+";"+n.getHoraApag(),
								//TipoVia tipo, LinkedList<Dispositivo> dispositivos
								LinkedList<Dispositivo> dispositivos=new LinkedList<Dispositivo>();
								if(datosFormateados.length>6) {
									ArrayList<Dispositivo>dispositivosVia = GestionDatosDispositivos.importarDispositivos(datosFormateados[datosFormateados.length-2]);
									for(Dispositivo d: dispositivosVia) {
										dispositivos.add(d);
									}
									Via via = new Via(Integer.valueOf(datosFormateados[0]), datosFormateados[1],datosFormateados[2], datosFormateados[3],
											TipoVia.valueOf(datosFormateados[4]), dispositivos);
									nucleosImportados.add(via);
								}else {
									Via via = new Via(Integer.valueOf(datosFormateados[0]), datosFormateados[1],datosFormateados[2], datosFormateados[3],
											TipoVia.valueOf(datosFormateados[4]), dispositivos);
									nucleosImportados.add(via);
								}								
								break;
							case "ubicaciones.Barrio":
								LinkedList<Via> vias = new LinkedList<Via>();
								if(datosFormateados.length>5) {
									LinkedList<Nucleo>viasBarrio = importarNucleos(datosFormateados[datosFormateados.length-2]);
									for(Nucleo n:viasBarrio) {
										vias.add((Via)n);
									}
									Barrio b = new Barrio(Integer.valueOf(datosFormateados[0]), datosFormateados[1],datosFormateados[2], datosFormateados[3],
											 vias);
									nucleosImportados.add(b);
								}else {
									Barrio b = new Barrio(Integer.valueOf(datosFormateados[0]), datosFormateados[1],datosFormateados[2], datosFormateados[3],
											 vias);
									nucleosImportados.add(b);
								}
								break;
							case "ubicaciones.Distrito":
								LinkedList<Barrio> barrios = new LinkedList<Barrio>();
								if(datosFormateados.length>5) {
									LinkedList<Nucleo>barriosDistrito = importarNucleos(datosFormateados[datosFormateados.length-2]);
									for(Nucleo n:barriosDistrito) {
										barrios.add((Barrio)n);
									}
									Distrito d = new Distrito(Integer.valueOf(datosFormateados[0]), datosFormateados[1],datosFormateados[2], datosFormateados[3],
											 barrios);
									nucleosImportados.add(d);
								}else {
									Distrito d = new Distrito(Integer.valueOf(datosFormateados[0]), datosFormateados[1],datosFormateados[2], datosFormateados[3],
											 barrios);
									nucleosImportados.add(d);
								}
								break;
							case "ubicaciones.Municipio":
								LinkedList<Distrito> distritos = new LinkedList<Distrito>();
								if(datosFormateados.length>5) {
									LinkedList<Nucleo>distritosMunicipio = importarNucleos(datosFormateados[datosFormateados.length-2]);
									for(Nucleo n:distritosMunicipio) {
										distritos.add((Distrito)n);
									}
									Municipio m = new Municipio(Integer.valueOf(datosFormateados[0]), datosFormateados[1],datosFormateados[2], datosFormateados[3],
											 distritos);
									nucleosImportados.add(m);
								}else {
									Municipio m = new Municipio(Integer.valueOf(datosFormateados[0]), datosFormateados[1],datosFormateados[2], datosFormateados[3],
											 distritos);
									nucleosImportados.add(m);
								}
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
		}
		
		return nucleosImportados;
	}
}
