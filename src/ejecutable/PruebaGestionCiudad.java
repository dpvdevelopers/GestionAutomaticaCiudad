package ejecutable;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedList;

import datos.GestionDatosAverias;
import datos.GestionDatosDispositivos;
import datos.GestionDatosIncidencias;
import datos.GestionDatosNucleos;
import datos.GestionDatosPersonas;
import equipos.Averia;
import equipos.Camara;
import equipos.Dispositivo;
import equipos.Incidencia;
import equipos.Semaforo;
import recursosHumanos.Persona;
import recursosHumanos.Tecnico;
import ubicaciones.Barrio;
import ubicaciones.Nucleo;
import ubicaciones.TipoVia;
import ubicaciones.Via;

public class PruebaGestionCiudad implements Serializable {
	

	/**
	 * 
	 */
	private static final long serialVersionUID = -3841168125302852682L;

	public static void main(String[] args) {
		Averia a = new Averia(2, 52.99, "03-04-2020", "pendiente", "transformador  de carga", false);
		LinkedList<Averia> averias = new LinkedList<Averia>();
		averias.add(a);
		GestionDatosAverias.guardarAverias(averias, "c:/Users/Usuario/Desktop/pruebas/averias.dat");
		Averia[] averias2 = GestionDatosAverias.recuperarAverias("c:/Users/Usuario/Desktop/pruebas/averias.dat");
		for(Averia b:averias2) {
			b.mostrarAveria();
		}
		GestionDatosAverias.exportarAverias(averias2, "c:/Users/Usuario/Desktop/pruebas");
		ArrayList<Averia> averias3 = GestionDatosAverias.importarAverias("c:/Users/Usuario/Desktop/pruebas");
		for(Averia c:averias3) {
			c.mostrarAveria();
		}
		Semaforo s = new Semaforo(1,155.25, "semaforo 2 colores", "25.254,45.548", "jhonsons control",
				"10:00", "22:00", true, averias, false, 50, 3, 30);
		Camara c= new Camara(25, 69.95, "Camara ip", "24.545,54.254", "Samsung",
			"19:00", "21:30", true,	averias, true, 25, 50);
		Dispositivo[] dispos = new Dispositivo[2];
		dispos[0]=s;
		dispos[1]=c;
		LinkedList<Dispositivo> dispositivos= new LinkedList<Dispositivo>();
		dispositivos.add(s);
		dispositivos.add(c);
		GestionDatosDispositivos.exportarDatosDispositivos(dispos, "c:/Users/Usuario/Desktop/pruebas");
		Incidencia i = new Incidencia(0025, 165.58, "Averia general", "Fecha alta", "pendiente", 5,
				false, averias);
		GestionDatosIncidencias.guardarIncidencia(i, "c:/Users/Usuario/Desktop/pruebas/incidencias.dat");
		LinkedList<Incidencia> incidencias = new LinkedList<Incidencia>();
		incidencias.add(i);
		Incidencia[] incidencias2 = new Incidencia[1];
		incidencias2[0]= i;
		GestionDatosIncidencias.guardarIncidencias(incidencias, "c:/Users/Usuario/Desktop/pruebas/incidencias2.dat");
		GestionDatosIncidencias.exportarIncidencias(incidencias2, "c:/Users/Usuario/Desktop/pruebas");
		Via via1 = new Via(25,"Alcazaba","20:00","7:00", TipoVia.CALLE, dispositivos);
		Via via2 = new Via(26, "Almena", "19:30", "6:30", TipoVia.AVENIDA, dispositivos);
		LinkedList<Via> vias = new LinkedList<Via>();
		vias.add(via1);
		vias.add(via2);
		Barrio barrio1 = new Barrio(27,"TorreCastillo", "18:00", "8:00", vias);
		Nucleo[] nucleos = new Nucleo[3];
		nucleos[0]=via1;
		nucleos[1]=via2;
		nucleos[2]=barrio1;
		GestionDatosNucleos.exportarNucleos(nucleos, "c:/Users/Usuario/Desktop/pruebas");
		LinkedList<Nucleo> nucleosImportados = GestionDatosNucleos.importarNucleos("c:/Users/Usuario/Desktop/pruebas");
		for(Nucleo n: nucleosImportados) {
			System.out.println(n.getClass());
		}
		nucleosImportados.get(1).mostrarDispositivos();
		
		LinkedList<Averia> averiasTer = new LinkedList<Averia>();
		averiasTer.add(a);
		Tecnico t = new Tecnico(54, "daniel","Pulido","del Valle", "54545454","alcazaba 41","659189945","12/07/1980",
				"15/12/2003", 25000, "oficial", true, averias, averiasTer, 25 );
		Persona[] personas = new Persona[1];
		personas[0]= t;
		GestionDatosPersonas.exportarPersonas(personas,  "c:/Users/Usuario/Desktop/pruebas");
		LinkedList<Persona> personasImportadas = GestionDatosPersonas.importarPersonas("c:/Users/Usuario/Desktop/pruebas");
		for(Persona p: personasImportadas) {
			p.mostrarPersona();
		}
		
	}

}
