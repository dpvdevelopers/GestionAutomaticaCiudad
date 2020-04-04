package ubicaciones;

import java.util.LinkedList;

public class Municipio extends Nucleo {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -3973016864665523050L;
	// -------------------------------------------- ATRIBUTOS ---------------------------------------
	LinkedList<Distrito> distritos ;
	// -------------------------------------------- CONSTRUCTORES -----------------------------------
	public Municipio(String nombre, LinkedList<Distrito> distritos) {
		super(nombre);
		if(distritos!=null) {
			this.distritos = distritos;
		}else {
			this.distritos =  new LinkedList<Distrito>();
		}
	}
		
	public Municipio(String nombre, String horaEnc, String horaApag, LinkedList<Distrito> distritos) {
		super(nombre, horaEnc, horaApag);
		if(distritos!=null) {
			this.distritos = distritos;
		}else {
			this.distritos =  new LinkedList<Distrito>();
		}
	}
	public Municipio(Municipio m) {
		super(m);
		if(m.distritos!=null) {
			this.distritos = m.distritos;
		}
	}
	public Municipio() {
		super();
		this.distritos = new LinkedList<Distrito>();
	}
	// ----------------------------------------- getters and setters -------------------------------
	
	public final LinkedList<Distrito> getLocalidades() {
		return distritos;
	}

	public final void setLocalidades(LinkedList<Distrito> distritos) {
		this.distritos = distritos;
	}
	// ----------------------------------------- toString ------------------------------------------
	
	@Override
	public String toString() {
		return "Municipio [distritos=" + distritos + "]";
	}
	// ------------------------------------------ METODOS ------------------------------------------
	
	@Override
	public void mostrarDispositivos() {
		for(Distrito d: distritos) {
			d.mostrarDispositivos();
		}

	}

	@Override
	public void encender() {
		for(Distrito d: distritos) {
			d.encender();
		}

	}

	@Override
	public void apagar() {
		for(Distrito d: distritos) {
			d.apagar();
		}

	}

	@Override
	public void programarEncendido(String horaEnc, String horaApag) {
		for(Distrito d: distritos) {
			d.programarEncendido(horaEnc, horaApag);
		}

	}

	@Override
	public void mostrarAverias() {
		for(Distrito d: distritos) {
			d.mostrarAverias();
		}

	}

	@Override
	public void mostrarReparaciones() {
		for(Distrito d: distritos) {
			d.mostrarReparaciones();
		}

	}

}
