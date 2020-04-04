package ubicaciones;

import java.util.LinkedList;

public class Distrito extends Nucleo {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 6300189012286397420L;
	// -------------------------------------------- ATRIBUTOS ---------------------------------------
	LinkedList<Barrio> barrios ;
	// -------------------------------------------- CONSTRUCTORES -----------------------------------
	public Distrito(String nombre, LinkedList<Barrio> barrios) {
		super(nombre);
		if(barrios!=null) {
			this.barrios = barrios;
		}else {
			this.barrios =  new LinkedList<Barrio>();
		}
	}
	
	public Distrito(String nombre, String horaEnc, String horaApag, LinkedList<Barrio> barrios) {
		super(nombre, horaEnc, horaApag);
		if(barrios!=null) {
			this.barrios = barrios;
		}else {
			this.barrios =  new LinkedList<Barrio>();
		}
	}
	public Distrito(Distrito d) {
		super(d);
		if(d.barrios!=null) {
			this.barrios = d.barrios;
		}
	}
	public Distrito() {
		super();
		this.barrios = new LinkedList<Barrio>();
	}
	// ------------------------------------- getters and setters ----------------------------------------
	
	public final LinkedList<Barrio> getBarrios() {
		return barrios;
	}

	public final void setBarrios(LinkedList<Barrio> barrios) {
		this.barrios = barrios;
	}
	// -------------------------------------- toString ---------------------------------------------------
	
	@Override
	public String toString() {
		return "Localidad [barrios=" + barrios + "]";
	}
	// --------------------------------------- METODOS --------------------------------------------
	@Override
	public void mostrarDispositivos() {
		for(Barrio b: this.barrios) {
			b.mostrarDispositivos();
		}

	}

	@Override
	public void encender() {
		for(Barrio b: this.barrios) {
			b.encender();
		}

	}

	@Override
	public void apagar() {
		for(Barrio b: this.barrios) {
			b.apagar();
		}

	}

	@Override
	public void programarEncendido(String horaEnc, String horaApag) {
		for(Barrio b: this.barrios) {
			b.programarEncendido(horaEnc, horaApag);
		}

	}

	@Override
	public void mostrarAverias() {
		for(Barrio b: this.barrios) {
			b.mostrarAverias();
		}

	}

	@Override
	public void mostrarReparaciones() {
		for(Barrio b: this.barrios) {
			b.mostrarReparaciones();
		}
	}
}
