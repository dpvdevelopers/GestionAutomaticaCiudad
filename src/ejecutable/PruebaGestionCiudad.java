package ejecutable;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedList;

import datos.GestionDatosAverias;
import datos.GestionDatosDispositivos;
import datos.GestionDatosIncidencias;
import equipos.Averia;
import equipos.Camara;
import equipos.Dispositivo;
import equipos.Incidencia;
import equipos.Semaforo;

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
	}

}
