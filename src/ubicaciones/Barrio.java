package ubicaciones;

import java.util.LinkedList;

import equipos.Dispositivo;

public class Barrio extends Nucleo {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -1723557991146435719L;
	// -------------------------------------------- ATRIBUTOS ---------------------------------------
	LinkedList<Via> vias;
	// -------------------------------------------- CONSTRUCTORES -----------------------------------
	public Barrio(String nombre, LinkedList<Via> vias) {
		super(nombre);
		if(vias!=null) {
			this.vias = vias;
		}else {
			this.vias =  new LinkedList<Via>();
		}
	}
	
	public Barrio(String nombre, String horaEnc, String horaApag, LinkedList<Via> vias) {
		super(nombre, horaEnc, horaApag);
		if(vias!=null) {
			this.vias = vias;
		}else {
			this.vias =  new LinkedList<Via>();
		}
	}
	public Barrio(Barrio b) {
		super(b);
		if(b.vias!=null) {
			this.vias = b.vias;
		}
	}
	public Barrio() {
		super();
		this.vias = new LinkedList<Via>();
	}
	// ----------------------------------------- getters and setters -------------------------
	
	public final LinkedList<Via> getVias() {
		return vias;
	}

	public final void setVias(LinkedList<Via> vias) {
		this.vias = vias;
	}
	// ------------------------------------------ toString ---------------------------------------
	
	@Override
	public String toString() {
		return "Barrio [vias=" + vias + "]";
	}
	// ------------------------------------------ METODOS ---------------------------------------
	@Override
	public void mostrarDispositivos() {
		if(!this.vias.isEmpty()) {
			for(Via v: this.vias) {
				if(!v.getDispositivos().isEmpty()) {
					System.out.println("La vía "+v.getCodigo()+" tiene los siguientes dispositivos:");
					for(Dispositivo d: v.getDispositivos()) {
						d.mostrarDispositivo();
					}
					System.out.println("");
				}else {
					System.out.println("La vía "+v.getCodigo()+" no tiene dispositivos aún.\n");
				}
			}
		}else {
			System.out.println("El barrio "+ this.getNombre()+ " aún no tiene vías asignadas");
		}
	}
	@Override
	public void encender() {
		if(!this.vias.isEmpty()) {
			for(Via v: this.vias) {
				if(!v.getDispositivos().isEmpty()) {
					v.encender();
				}else {
					System.out.println("La vía "+v.getCodigo()+" no tiene dispositivos aún.\n");
				}
				System.out.println("Se han encendido los dispositivos de la vía "+ v.getCodigo());
			}
		}else {
			System.out.println("El barrio "+ this.getNombre()+ " aún no tiene vías asignadas");
		}
	}

	@Override
	public void apagar() {
		if(!this.vias.isEmpty()) {
			for(Via v: this.vias) {
				if(!v.getDispositivos().isEmpty()) {
					v.apagar();
				}else {
					System.out.println("La vía "+v.getCodigo()+" no tiene dispositivos aún.\n");
				}
				System.out.println("Se han encendido los dispositivos de la vía "+ v.getCodigo());
			}
		}else {
			System.out.println("El barrio "+ this.getNombre()+ " aún no tiene vías asignadas");
		}
	}
	@Override
	public void programarEncendido(String horaEnc, String horaApag) {
		if(!this.vias.isEmpty()) {
			for(Via v: this.vias) {
				v.programarEncendido(horaEnc, horaApag);
			}
		}else {
			System.out.println("El barrio "+ this.getNombre()+ " aún no tiene vías asignadas");
		}
	}
	@Override
	public void mostrarAverias() {
		if(!this.vias.isEmpty()) {
			for(Via v: this.vias) {
				v.mostrarAverias();
			}
		}else {
			System.out.println("El barrio "+ this.getNombre()+ " aún no tiene vías asignadas");
		}
	}
	@Override
	public void mostrarReparaciones() {
		if(!this.vias.isEmpty()) {
			for(Via v: this.vias) {
				v.mostrarReparaciones();
			}
		}else {
			System.out.println("El barrio "+ this.getNombre()+ " aún no tiene vías asignadas");
		}
	}	
}
