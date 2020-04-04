package ubicaciones;

import java.util.LinkedList;

import equipos.Averia;
import equipos.Dispositivo;

public class Via extends Nucleo {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -2263660891049300802L;
	// ---------------------------------------- ATRIBUTOS ------------------------------------------
	private TipoVia tipo;
	private LinkedList<Dispositivo> dispositivos;
	// ---------------------------------------- CONSTRUCTORES --------------------------------------	
	public Via(String nombre, TipoVia tipo, LinkedList<Dispositivo> dispositivos) {
		super(nombre);
		if(dispositivos!= null) {
			this.tipo = tipo;
			this.dispositivos = dispositivos;
		}else {
			this.dispositivos = new LinkedList<Dispositivo>();
		}
	}
	public Via() {
		super();
		this.tipo = TipoVia.CALLE;
		this.dispositivos = new LinkedList<Dispositivo>(); 
	}
	// ------------------------------------- getters and setters ------------------------------------
	public final TipoVia getTipo() {
		return tipo;
	}
	public final void setTipo(TipoVia tipo) {
		this.tipo = tipo;
	}
	public final LinkedList<Dispositivo> getDispositivos() {
		return dispositivos;
	}
	public final void setDispositivos(LinkedList<Dispositivo> dispositivos) {
		this.dispositivos = dispositivos;
	}
	// ------------------------------------- toString ------------------------------------------------
	
	@Override
	public String toString() {
		return "Via [tipo=" + tipo + ", dispositivos=" + dispositivos + "]";
	}
	// ------------------------------------- METODOS --------------------------------------------------
	
	@Override
	public void mostrarDispositivos() {
		if(this.dispositivos.isEmpty()) {
			System.out.println("La vía "+ this.tipo + " "+ this.getNombre()+" no tiene ningún dispositivo");
		}else {
			System.out.println("La lista de dispositivos de la vía "+ this.tipo +
					" "+ this.getNombre()+" es la siguiente:");
			for(Dispositivo d: this.dispositivos) {
				d.mostrarDispositivo();
			}
		}
	}

	@Override
	public void encender() {
		if(this.dispositivos.isEmpty()) {
			System.out.println("La vía "+ this.tipo + " "+ this.getNombre()+" no tiene ningún dispositivo");
		}else {
			for(Dispositivo d: this.dispositivos) {
				if(d.getAverias().isEmpty()|| d.getAverias().getFirst().isResuelta()) {
					d.encenderDispositivo();
				}else {
					System.out.println("El dispositivo "+ d.getCodigo() +" está averiado, si desea forzar"
							+ " el encendido debe hacerlo de forma manual.");
				}				
			}
			System.out.println("Se han encendido todos los dispositivos disponibles de la vía: "+
					this.tipo+ " "+ this.getNombre());
		}
	}

	@Override
	public void apagar() {
		if(this.dispositivos.isEmpty()) {
			System.out.println("La vía "+ this.tipo + " "+ this.getNombre()+" no tiene ningún dispositivo");
		}else {
			for(Dispositivo d: this.dispositivos) {
				d.apagarDispositivo();
			}
			System.out.println("Se han apagado todos los dispositivos de la vía: "+
					this.tipo+ " "+ this.getNombre()+".");
		}

	}

	@Override
	public void programarEncendido(String horaEnc, String horaApag) {
		for(Dispositivo d: this.dispositivos) {
			d.programarEncendido(horaEnc, horaApag);
		}
		System.out.println("Se ha modificado el horario en todos los dispositivos solicitados.");
	}

	@Override
	public void mostrarAverias() {
		System.out.println("La vía "+this.tipo +" "+this.getNombre()+
				" tiene las siguientes averías pendientes:");
		for(Dispositivo d: this.dispositivos) {
			if(!d.getAverias().isEmpty()) {
				for(Averia a: d.getAverias()) {
					if(!a.isResuelta()) {
						System.out.println("Dispositivo nº: "+ d.getCodigo());
						a.mostrarAveria();
					}
				}
			}
		}
	}

	@Override
	public void mostrarReparaciones() {
		System.out.println("En la vía "+this.tipo +" "+this.getNombre()+
				" se han realizado las siguientes reparaciones:");
		for(Dispositivo d: this.dispositivos) {
			if(!d.getAverias().isEmpty()) {
				for(Averia a: d.getAverias()) {
					if(a.isResuelta()) {
						System.out.println("Dispositivo nº: "+ d.getCodigo());
						a.mostrarAveria();
					}
				}
			}
		}
	}
}
