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
import recursosHumanos.Jefe;
import recursosHumanos.Persona;
import recursosHumanos.Supervisor;
import recursosHumanos.Tecnico;
import ubicaciones.Barrio;
import ubicaciones.Distrito;
import ubicaciones.Nucleo;

public class GestionDatosPersonas implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -2235026101483221621L;

	public static Persona[] recuperarPersonas(String rutaCompleta){
		Persona[] personasRecuperadas= new Persona[0];
		try {
			FileInputStream archivo = new FileInputStream(rutaCompleta);
			ObjectInputStream entrada = new ObjectInputStream(archivo);			
			personasRecuperadas = (Persona[]) entrada.readObject();
			entrada.close();
			archivo.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return personasRecuperadas;
	}
	
	public static boolean guardarPersonas(ArrayList<Persona> personas, String rutaCompleta) {
		boolean guardado = false;
		try {
			
			File archivo = new File(rutaCompleta);
			if(archivo.exists()) {
				Persona[] personasRecuperadas = recuperarPersonas(rutaCompleta);			
				Persona[] personasAGuardar = new Persona[personas.size()+personasRecuperadas.length];
				// guardamos en el array las averias recuperadas del archivo de destino
				for(int i=0;i<personasRecuperadas.length;i++) {
					personasAGuardar[i]=personasRecuperadas[i];
				}
				// guardamos en el array las averias pasadas en el parametro de entrada
				for(Persona p: personas) {
					personasAGuardar[personas.indexOf(p)+personasRecuperadas.length] = p;
				}
				ObjectOutputStream salida = new ObjectOutputStream(new FileOutputStream(rutaCompleta));
				salida.writeObject(personasAGuardar);
				salida.close();
				guardado=true;
			}else {
				Persona[] personasAGuardar = new Persona[personas.size()];
				for(Persona p:personas) {
					personasAGuardar[personas.indexOf(p)] = p;
				}
				ObjectOutputStream salida = new ObjectOutputStream(new FileOutputStream(rutaCompleta));
				salida.writeObject(personasAGuardar);
				salida.close();
				guardado=true;
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return guardado;
	}
	
	public static boolean guardarPersonas(Persona persona, String rutaCompleta) {
		boolean guardado = false;
		try {
			
			File archivo = new File(rutaCompleta);
			if(archivo.exists()) {
				Persona[] personasRecuperadas = recuperarPersonas(rutaCompleta);			
				Persona[] personasAGuardar = new Persona[1+personasRecuperadas.length];
				// guardamos en el array las personas recuperadas del archivo de destino
				for(int i=0;i<personasRecuperadas.length;i++) {
					personasAGuardar[i]=personasRecuperadas[i];
				}
				// guardamos en el array la persona pasada en el parametro de entrada
				
				personasAGuardar[personasRecuperadas.length] = persona;
				
				ObjectOutputStream salida = new ObjectOutputStream(new FileOutputStream(rutaCompleta));
				salida.writeObject(personasAGuardar);
				salida.close();
				guardado=true;
			}else {
				Persona[] personasAGuardar = new Persona[1];
				personasAGuardar[0] = persona;
				
				ObjectOutputStream salida = new ObjectOutputStream(new FileOutputStream(rutaCompleta));
				salida.writeObject(personasAGuardar);
				salida.close();
				guardado=true;
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return guardado;
	}
	
	public static String exportarPersonas(Persona[] personas, String ruta) {
		String rutaCompleta="";
		if(new File(ruta).isDirectory()) {
			rutaCompleta = ruta+File.separator+"personas.csv";
		}else {
			rutaCompleta = ruta;
		}
		try {
			if(personas==null) {
				System.out.println("Ha ocurrido un error al recibir las personas");
			}else {
				String datos = "";
				LinkedList<Persona> personasAGuardar = new LinkedList<Persona>();
				if(new File(rutaCompleta).exists()) {
					personasAGuardar = importarPersonas(rutaCompleta);
				}
				for(int i=0;i<personas.length;i++) {
					personasAGuardar.addLast(personas[i]);
				}
				FileWriter archivo = new FileWriter(rutaCompleta);
				BufferedWriter buffer = new BufferedWriter(archivo);
				for(Persona p: personas) {
					datos = Integer.toString(p.getCodigo())+";"+p.getNombre()+";"+p.getApellido1()+";"+p.getApellido2()+";"
							+ p.getDni()+";"+ p.getDireccion()+";"+ p.getTelefono()+";"+p.getFechaNac()+";"+
							p.getFechaAlta()+";"+Double.valueOf(p.getSueldo());
					switch (p.getClass().getName()) {
						case "recursosHumanos.Tecnico":
							LinkedList<Averia> averiasPendientes = new LinkedList<Averia>();
							Tecnico t = new Tecnico((Tecnico) p);
							datos=datos+";"+t.getCategoria()+";"+Boolean.valueOf(t.isActivo())+";"+ Integer.valueOf(t.getSupervisor());
							if(!t.getAvPendientes().isEmpty()) {
								for(Averia a:t.getAvPendientes()) {
									datos=datos+";"+a.getCodigo();
									averiasPendientes.add(a);
								}
								Averia[] averias2 = new Averia[averiasPendientes.size()];
								for(int i=0;i<averiasPendientes.size();i++) {
									averias2[i] = averiasPendientes.get(i);
								}
								File rutaDestino = new File(ruta+File.separator+t.getCodigo());
								if(!rutaDestino.exists()) {
									rutaDestino.mkdir();
								}
								datos= datos+";"+GestionDatosAverias.exportarAverias(averias2, (ruta+File.separator+t.getCodigo()));
							}
							LinkedList<Averia> averiasTerminadas = new LinkedList<Averia>();
							if(!t.getAvTerminadas().isEmpty()) {
								for(Averia a:t.getAvTerminadas()) {
									datos=datos+";"+a.getCodigo();
									averiasTerminadas.add(a);
								}
								Averia[] averias3 = new Averia[averiasTerminadas.size()];
								for(int i=0;i<averiasTerminadas.size();i++) {
									averias3[i] = averiasTerminadas.get(i);
								}
								File rutaDestino = new File(ruta+File.separator+t.getCodigo());
								if(!rutaDestino.exists()) {
									rutaDestino.mkdir();
								}else {
									File rutaTerminadas = new File(ruta+File.separator+t.getCodigo()+File.separator+"Terminadas");
									if(!rutaTerminadas.exists()) {
										rutaTerminadas.mkdir();
									}
								}
								datos= datos+";"+GestionDatosAverias.exportarAverias(averias3, (ruta+File.separator+t.getCodigo()+File.separator+"Terminadas"));
							}
							datos = datos +";"+p.getClass().getName()+"\n";
							buffer.write(datos);
							break;
						case "recursosHumanos.Supervisor":
							LinkedList<Incidencia> incidenciasPendientes = new LinkedList<Incidencia>();
							Supervisor s = new Supervisor((Supervisor) p);
							datos=datos+";"+s.getDepartamento()+";"+Boolean.valueOf(s.isActivo())+";"+s.getObjetivo();
							if(!s.getPendientes().isEmpty()) {
								for(Incidencia i:s.getPendientes()) {
									datos=datos+";"+i.getCodigo();
									incidenciasPendientes.add(i);
								}
								Incidencia[] incidencias2 = new Incidencia[incidenciasPendientes.size()];
								for(int i=0;i<incidenciasPendientes.size();i++) {
									incidencias2[i] = incidenciasPendientes.get(i);
								}
								File rutaDestino = new File(ruta+File.separator+s.getCodigo());
								if(!rutaDestino.exists()) {
									rutaDestino.mkdir();
								}
								datos= datos+";"+GestionDatosIncidencias.exportarIncidencias(incidencias2, (ruta+File.separator+s.getCodigo()));
							}
							LinkedList<Incidencia> incidenciasTerminadas = new LinkedList<Incidencia>();
							if(!s.getTerminadas().isEmpty()) {
								for(Incidencia i:s.getTerminadas()) {
									datos=datos+";"+i.getCodigo();
									incidenciasTerminadas.add(i);
								}
								Incidencia[] incidencias3 = new Incidencia[incidenciasTerminadas.size()];
								for(int i=0;i<incidenciasTerminadas.size();i++) {
									incidencias3[i] = incidenciasTerminadas.get(i);
								}
								File rutaDestino = new File(ruta+File.separator+s.getCodigo());
								if(!rutaDestino.exists()) {
									rutaDestino.mkdir();
								}else {
									File rutaTerminadas = new File(ruta+File.separator+s.getCodigo()+File.separator+"Terminadas");
									if(!rutaTerminadas.exists()) {
										rutaTerminadas.mkdir();
									}
								}
								datos= datos+";"+GestionDatosIncidencias.exportarIncidencias(incidencias3, (ruta+File.separator+s.getCodigo()+File.separator+"Terminadas"));
							}
							
							if(!s.getTecnicos().isEmpty()) {
								Tecnico[] tecnicos = new Tecnico[s.getTecnicos().size()];
								for(Tecnico tec:s.getTecnicos()) {
									datos = datos+";"+tec.getCodigo();
									tecnicos[s.getTecnicos().indexOf(tec)]=tec;
								}
								File rutaDestino = new File(ruta+File.separator+s.getCodigo());
								if(!rutaDestino.exists()) {
									rutaDestino.mkdir();
								}else {
									File rutaTecnicos = new File(ruta+File.separator+s.getCodigo()+File.separator+"Tecnicos");
									if(!rutaTecnicos.exists()) {
										rutaTecnicos.mkdir();
									}
								}
								datos = datos+";"+GestionDatosPersonas.exportarPersonas(tecnicos, ruta+File.separator+s.getCodigo()
									+File.separator+"Tecnicos");
							}
							if(!s.getBarrios().isEmpty()) {
								Barrio[] barrios = new Barrio[s.getBarrios().size()];
								for(Barrio b: s.getBarrios()) {
									datos=datos+";"+b.getCodigo();
									barrios[s.getBarrios().indexOf(b)]=b;
								}
								File rutaDestino = new File(ruta+File.separator+s.getCodigo());
								if(!rutaDestino.exists()) {
									rutaDestino.mkdir();
								}else {
									File rutaTecnicos = new File(ruta+File.separator+s.getCodigo()+File.separator+"Barrios");
									if(!rutaTecnicos.exists()) {
										rutaTecnicos.mkdir();
									}
								}
								datos = datos+";"+ GestionDatosNucleos.exportarNucleos(barrios, ruta+File.separator+s.getCodigo()+File.separator+"Barrios");
							}
							datos = datos +";"+p.getClass().getName()+"\n";
							buffer.write(datos);
							break;
						case "recursosHumanos.Jefe":
							LinkedList<Incidencia> incidenciasPendientes2 = new LinkedList<Incidencia>();
							Jefe j = new Jefe((Jefe) p);
							datos=datos+";"+j.getDepartamento()+";"+Boolean.valueOf(j.isActivo())+";"+j.getObjetivo();
							if(!j.getPendientes().isEmpty()) {
								for(Incidencia i:j.getPendientes()) {
									datos=datos+";"+i.getCodigo();
									incidenciasPendientes2.add(i);
								}
								Incidencia[] incidencias2 = new Incidencia[incidenciasPendientes2.size()];
								for(int i=0;i<incidenciasPendientes2.size();i++) {
									incidencias2[i] = incidenciasPendientes2.get(i);
								}
								File rutaDestino = new File(ruta+File.separator+j.getCodigo());
								if(!rutaDestino.exists()) {
									rutaDestino.mkdir();
								}
								datos= datos+";"+GestionDatosIncidencias.exportarIncidencias(incidencias2, (ruta+File.separator+j.getCodigo()));
							}
							LinkedList<Incidencia> incidenciasTerminadas2 = new LinkedList<Incidencia>();
							if(!j.getTerminadas().isEmpty()) {
								for(Incidencia i:j.getTerminadas()) {
									datos=datos+";"+i.getCodigo();
									incidenciasTerminadas2.add(i);
								}
								Incidencia[] incidencias3 = new Incidencia[incidenciasTerminadas2.size()];
								for(int i=0;i<incidenciasTerminadas2.size();i++) {
									incidencias3[i] = incidenciasTerminadas2.get(i);
								}
								File rutaDestino = new File(ruta+File.separator+j.getCodigo());
								if(!rutaDestino.exists()) {
									rutaDestino.mkdir();
								}else {
									File rutaTerminadas = new File(ruta+File.separator+j.getCodigo()+File.separator+"Terminadas");
									if(!rutaTerminadas.exists()) {
										rutaTerminadas.mkdir();
									}
								}
								datos= datos+";"+GestionDatosIncidencias.exportarIncidencias(incidencias3, (ruta+File.separator+j.getCodigo()+File.separator+"Terminadas"));
							}
							
							if(!j.getSupervisores().isEmpty()) {
								Supervisor[] supervisores = new Supervisor[j.getSupervisores().size()];
								for(Supervisor sup:j.getSupervisores()) {
									datos = datos+";"+sup.getCodigo();
									supervisores[j.getSupervisores().indexOf(sup)]=sup;
								}
								File rutaDestino = new File(ruta+File.separator+j.getCodigo());
								if(!rutaDestino.exists()) {
									rutaDestino.mkdir();
								}else {
									File rutaSupervisores = new File(ruta+File.separator+j.getCodigo()+File.separator+"Supervisores");
									if(!rutaSupervisores.exists()) {
										rutaSupervisores.mkdir();
									}
								}
								datos = datos+";"+GestionDatosPersonas.exportarPersonas(supervisores, ruta+File.separator+j.getCodigo()
									+File.separator+"Tecnicos");
							}
							if(!j.getDistritos().isEmpty()) {
								Distrito[] distritos = new Distrito[j.getDistritos().size()];
								for(Distrito d: j.getDistritos()) {
									datos=datos+";"+d.getCodigo();
									distritos[j.getDistritos().indexOf(d)]=d;
								}
								File rutaDestino = new File(ruta+File.separator+j.getCodigo());
								if(!rutaDestino.exists()) {
									rutaDestino.mkdir();
								}else {
									File rutaDistritos = new File(ruta+File.separator+j.getCodigo()+File.separator+"Distritos");
									if(!rutaDistritos.exists()) {
										rutaDistritos.mkdir();
									}
								}
								datos = datos+";"+ GestionDatosNucleos.exportarNucleos(distritos, ruta+File.separator+j.getCodigo()+File.separator+"Distritos");
							}
							datos = datos +";"+p.getClass().getName()+"\n";
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

	public static LinkedList<Persona> importarPersonas(String ruta) {
		LinkedList<Persona> personasImportadas = new LinkedList<Persona>();
		String rutaCompleta="";
		if(new File(ruta).isDirectory()) {
			rutaCompleta = ruta+File.separator+"personas.csv";
		}else {
			rutaCompleta = ruta;
		}
		if(!new File(ruta).exists()) {
			System.out.println("No se ha encontrado el archivo, verifique la ruta");
		}else {
			try {
				FileReader archivo = new FileReader(rutaCompleta);
				BufferedReader buffer = new BufferedReader(archivo);
				String datos = "";
				while((datos = buffer.readLine())!=null) {
					String[] datosFormateados = datos.split(";");
					switch(datosFormateados[datosFormateados.length-1]) {
					case "recursosHumanos.Tecnico":
						if(datosFormateados.length>14) {
							ArrayList<Averia> avPendientes = GestionDatosAverias.importarAverias(datosFormateados[datosFormateados.length-3]);
							LinkedList<Averia>pendientes = new LinkedList<Averia>();
							for(Averia a: avPendientes) {
								pendientes.add(a);
							}
							ArrayList<Averia> avTerminadas = GestionDatosAverias.importarAverias(datosFormateados[datosFormateados.length-2]);
							LinkedList<Averia>terminadas = new LinkedList<Averia>();
							for(Averia b: avTerminadas) {
								terminadas.add(b);
							}
							Tecnico t = new Tecnico(Integer.valueOf(datosFormateados[0]), datosFormateados[1], 
									datosFormateados[2], datosFormateados[3], datosFormateados[4], datosFormateados[5],
									datosFormateados[6], datosFormateados[7], datosFormateados[8], Double.valueOf(datosFormateados[9]),
									datosFormateados[10], Boolean.valueOf(datosFormateados[11]),
											pendientes, terminadas, Integer.valueOf(datosFormateados[12]));
							personasImportadas.add(t);
						}else {
							LinkedList<Averia>pendientes = new LinkedList<Averia>();
							LinkedList<Averia>terminadas = new LinkedList<Averia>();
							Tecnico t = new Tecnico(Integer.valueOf(datosFormateados[0]), datosFormateados[1], 
									datosFormateados[2], datosFormateados[3], datosFormateados[4], datosFormateados[5],
									datosFormateados[6], datosFormateados[7], datosFormateados[8], Double.valueOf(datosFormateados[9]),
									datosFormateados[10], Boolean.valueOf(datosFormateados[11]),
											pendientes, terminadas, Integer.valueOf(datosFormateados[12]));
							personasImportadas.add(t);
						}
						break;
						
					case "recursosHumanos.Supervisor":
						if(datosFormateados.length>14) {
							ArrayList<Incidencia> incidenciasPendientes = GestionDatosIncidencias.importarIncidencias(datosFormateados[datosFormateados.length-5]);
							LinkedList<Incidencia>incidenciasPendientesRecuperadas = new LinkedList<Incidencia>();
							for(Incidencia i:incidenciasPendientes) {
								incidenciasPendientesRecuperadas.add(i);
							}
							ArrayList<Incidencia> incidenciasTerminadas = GestionDatosIncidencias.importarIncidencias(datosFormateados[datosFormateados.length-4]);
							LinkedList<Incidencia> incidenciasTerminadasRecuperadas = new LinkedList<Incidencia>();
							for(Incidencia i:incidenciasTerminadas) {
								incidenciasTerminadasRecuperadas.add(i);
							}
							LinkedList<Nucleo> barriosRecuperados = GestionDatosNucleos.importarNucleos(datosFormateados[datosFormateados.length-2]);
							LinkedList<Barrio> barriosDefinitivos = new LinkedList<Barrio>();
							for(Nucleo n: barriosRecuperados) {
								Barrio b = (Barrio)n;
								barriosDefinitivos.add(b);
							}
							LinkedList<Persona> tecnicosRecuperados = GestionDatosPersonas.importarPersonas(datosFormateados[datosFormateados.length-3]);
							LinkedList<Tecnico> tecnicosDefinitivos = new LinkedList<Tecnico>();
							for(Persona p:tecnicosRecuperados) {
								Tecnico tec = (Tecnico)p;
								tecnicosDefinitivos.add(tec);
							}
							Supervisor s = new Supervisor(Integer.valueOf(datosFormateados[0]), datosFormateados[1],
									datosFormateados[2], datosFormateados[3], datosFormateados[4], datosFormateados[5], 
									datosFormateados[6], datosFormateados[7], datosFormateados[8], Double.valueOf(datosFormateados[9]), 
									datosFormateados[10], Boolean.valueOf(datosFormateados[11]), tecnicosDefinitivos, 
									incidenciasPendientesRecuperadas, incidenciasTerminadasRecuperadas, barriosDefinitivos, 
									Integer.valueOf(datosFormateados[12]));
							personasImportadas.add(s);
						}else {
							LinkedList<Incidencia>incidenciasPendientesRecuperadas = new LinkedList<Incidencia>();
							LinkedList<Incidencia> incidenciasTerminadasRecuperadas = new LinkedList<Incidencia>();
							LinkedList<Tecnico> tecnicosDefinitivos = new LinkedList<Tecnico>();
							LinkedList<Barrio> barriosDefinitivos = new LinkedList<Barrio>();
							Supervisor s = new Supervisor(Integer.valueOf(datosFormateados[0]), datosFormateados[1],
									datosFormateados[2], datosFormateados[3], datosFormateados[4], datosFormateados[5], 
									datosFormateados[6], datosFormateados[7], datosFormateados[8], Double.valueOf(datosFormateados[9]), 
									datosFormateados[10], Boolean.valueOf(datosFormateados[11]), tecnicosDefinitivos, 
									incidenciasPendientesRecuperadas, incidenciasTerminadasRecuperadas, barriosDefinitivos, 
									Integer.valueOf(datosFormateados[12]));
							personasImportadas.add(s);
						}
						break;
					case "recursosHumanos.Jefe":
						if(datosFormateados.length>14) {
							ArrayList<Incidencia> incidenciasPendientesJefe = GestionDatosIncidencias.importarIncidencias(datosFormateados[datosFormateados.length-5]);
							LinkedList<Incidencia>incidenciasPendientesRecuperadasJefe = new LinkedList<Incidencia>();
							for(Incidencia i:incidenciasPendientesJefe) {
								incidenciasPendientesRecuperadasJefe.add(i);
							}
							ArrayList<Incidencia> incidenciasTerminadasJefe = GestionDatosIncidencias.importarIncidencias(datosFormateados[datosFormateados.length-4]);
							LinkedList<Incidencia> incidenciasTerminadasRecuperadasJefe = new LinkedList<Incidencia>();
							for(Incidencia i:incidenciasTerminadasJefe) {
								incidenciasTerminadasRecuperadasJefe.add(i);
							}
							LinkedList<Nucleo> distritosRecuperados = GestionDatosNucleos.importarNucleos(datosFormateados[datosFormateados.length-2]);
							LinkedList<Distrito> distritosDefinitivos = new LinkedList<Distrito>();
							for(Nucleo n: distritosRecuperados) {
								Distrito d = (Distrito)n;
								distritosDefinitivos.add(d);
							}
							LinkedList<Persona> supervisoresRecuperados = GestionDatosPersonas.importarPersonas(datosFormateados[datosFormateados.length-3]);
							LinkedList<Supervisor> supervisoresDefinitivos = new LinkedList<Supervisor>();
							for(Persona p:supervisoresRecuperados) {
								Supervisor sup = (Supervisor)p;
								supervisoresDefinitivos.add(sup);
							}
	
							Jefe j = new Jefe(Integer.valueOf(datosFormateados[0]), datosFormateados[1],
									datosFormateados[2], datosFormateados[3], datosFormateados[4], datosFormateados[5], 
									datosFormateados[6], datosFormateados[7], datosFormateados[8], Double.valueOf(datosFormateados[9]), 
									datosFormateados[10], Boolean.valueOf(datosFormateados[11]), supervisoresDefinitivos, 
									incidenciasPendientesRecuperadasJefe, incidenciasTerminadasRecuperadasJefe, distritosDefinitivos, 
									Integer.valueOf(datosFormateados[12]));
							personasImportadas.add(j);
						}else {
							LinkedList<Supervisor> supervisoresDefinitivos = new LinkedList<Supervisor>();
							LinkedList<Distrito> distritosDefinitivos = new LinkedList<Distrito>();
							LinkedList<Incidencia> incidenciasTerminadasRecuperadasJefe = new LinkedList<Incidencia>();
							LinkedList<Incidencia>incidenciasPendientesRecuperadasJefe = new LinkedList<Incidencia>();
							Jefe j = new Jefe(Integer.valueOf(datosFormateados[0]), datosFormateados[1],
									datosFormateados[2], datosFormateados[3], datosFormateados[4], datosFormateados[5], 
									datosFormateados[6], datosFormateados[7], datosFormateados[8], Double.valueOf(datosFormateados[9]), 
									datosFormateados[10], Boolean.valueOf(datosFormateados[11]), supervisoresDefinitivos, 
									incidenciasPendientesRecuperadasJefe, incidenciasTerminadasRecuperadasJefe, distritosDefinitivos, 
									Integer.valueOf(datosFormateados[12]));
							personasImportadas.add(j);
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
		return personasImportadas;
	}
}
