package ejecutable;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedList;

import datos.GestionDatosAverias;
import equipos.Averia;

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
	}

}
