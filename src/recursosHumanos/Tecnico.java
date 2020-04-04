package recursosHumanos;

import java.util.LinkedList;

import equipos.Averia;

public class Tecnico extends Persona {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 125620599270454938L;
	// ------------------------------------------ ATRIBUTOS -----------------------------------------------
	private String categoria;
	private boolean activo;
	private LinkedList<Averia>avPendientes;
	private LinkedList<Averia>avTerminadas;
	private int supervisor;
	// ------------------------------------------ CONSTRUCTORES -------------------------------------------
	

	public Tecnico(String nombre, String apellido1, String apellido2, String dni, String direccion, String telefono,
			String fechaNac, String fechaAlta, double sueldo, String categoria, boolean activo,
			LinkedList<Averia> avPendientes, LinkedList<Averia> avTerminadas, int supervisor) {
		super(nombre, apellido1, apellido2, dni, direccion, telefono, fechaNac, fechaAlta, sueldo);
		this.categoria = categoria;
		this.activo = activo;
		if(avPendientes!=null) {
			this.avPendientes = avPendientes;
		}else {
			this.avPendientes = new LinkedList<Averia>();
		}
		if(avTerminadas != null) {
			this.avTerminadas = avTerminadas;
		}else {
			this.avTerminadas = new LinkedList<Averia>();
		}
			this.supervisor = supervisor;
	}
	public Tecnico(String nombre, String apellido1, String categoria, boolean activo,
			LinkedList<Averia> avPendientes, LinkedList<Averia> avTerminadas, int supervisor) {
		super(nombre, apellido1);
		this.categoria = categoria;
		this.activo = activo;
		if(avPendientes!=null) {
			this.avPendientes = avPendientes;
		}else {
			this.avPendientes = new LinkedList<Averia>();
		}
		if(avTerminadas != null) {
			this.avTerminadas = avTerminadas;
		}else {
			this.avTerminadas = new LinkedList<Averia>();
		}
		this.supervisor = supervisor;
	}
	public Tecnico() {
		super();
		this.categoria = "";
		this.activo = false;
		this.avPendientes = new LinkedList<Averia>();
		this.avTerminadas =  new LinkedList<Averia>();
		this.supervisor = 0;
	}

	// ----------------------------------------- getters and setters -------------------------------------
	
	public final String getCategoria() {
		return categoria;
	}
	public final void setCategoria(String categoria) {
		this.categoria = categoria;
	}
	public final boolean isActivo() {
		return activo;
	}
	public final void setActivo(boolean activo) {
		this.activo = activo;
	}
	public final LinkedList<Averia> getAvPendientes() {
		return avPendientes;
	}
	public final void setAvPendientes(LinkedList<Averia> avPendientes) {
		this.avPendientes = avPendientes;
	}
	public final LinkedList<Averia> getAvTerminadas() {
		return avTerminadas;
	}
	public final void setAvTerminadas(LinkedList<Averia> avTerminadas) {
		this.avTerminadas = avTerminadas;
	}
	public final int getSupervisor() {
		return supervisor;
	}
	public final void setSupervisor(int supervisor) {
		this.supervisor = supervisor;
	}
	// ------------------------------------------ toString --------------------------------------------
	@Override
	public String toString() {
		return "Tecnico [categoria=" + categoria + ", activo=" + activo + ", avPendientes=" + avPendientes
				+ ", avTerminadas=" + avTerminadas + ", supervisor=" + supervisor + "]";
	}
	// ------------------------------------------ METODOS ---------------------------------------------
	public void mostrarPendientes() {
		if(!this.avPendientes.isEmpty()) {
			for(Averia a: this.avPendientes) {
				a.mostrarAveria();
			}
		}else {
			System.out.println("No hay averías pendientes.");
		}
	}
	public void mostrarTerminadas() {
		if(!this.avTerminadas.isEmpty()) {
			for(Averia a: this.avTerminadas) {
				a.mostrarAveria();
			}
		}else {
			System.out.println("No hay averias resueltas.");
		}
	}
	public void mostrarActual() {
		if(!this.avPendientes.isEmpty()) {
			this.avPendientes.getLast().mostrarAveria();
		}else {
			System.out.println("Actualmente el técnico "+ this.getNombre()+" "+this.getApellido1()+
					" no tiene ninguna avería asignada.");
		}
	}
	public void asignarAveria(Averia a) {
		if(a!= null) {
			this.avPendientes.addFirst(a);
		}else {
			System.out.println("Ha ocurrido un error al asignar la avería, pruebe otra vez.");
		}
	}
	public void quitarAveria(Averia a) {
		if(a!=null) {
			if(this.avPendientes.remove(a)) {
				this.avTerminadas.addLast(a);
			}else {
				System.out.println("No se ha localizado la avería "+ a.getCodigo()+ " en el listado.");
			}			
		}else {
			System.out.println("Ha ocurrido un error al recibir la avería, pruebe otra vez.");
		}
	}
	@Override
	public void generarInforme() {
		System.out.println("Informe del técnico "+ this.getNombre()+" "+this.getApellido1()+":");
		System.out.println("Averías asignadas: "+ (this.avPendientes.size()+this.avTerminadas.size()));
		System.out.println("Averías resueltas: "+ this.avTerminadas.size());
		System.out.println("Última avería resuelta: "+ this.avTerminadas.getLast().getCodigo()+ 
				" el día "+this.avTerminadas.getLast().getFechaRep());
		System.out.println("Avería actual: "); 
		this.avPendientes.getLast().mostrarAveria();
	}

}
