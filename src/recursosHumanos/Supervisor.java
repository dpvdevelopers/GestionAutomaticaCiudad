package recursosHumanos;

import java.util.LinkedList;
import equipos.Incidencia;
import ubicaciones.Barrio;

public class Supervisor extends Persona {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 7947268217818426162L;
	// ----------------------------- ATRIBUTOS ----------------------------
	String departamento;
	boolean activo;
	LinkedList<Tecnico> tecnicos;
	LinkedList<Incidencia> pendientes;
	LinkedList<Incidencia> terminadas;
	LinkedList<Barrio> barrios;
	int objetivo;
	// ----------------------------- CONSTRUCTORES --------------------------
	public Supervisor(String nombre, String apellido1, String apellido2, String dni, String direccion, String telefono,
			String fechaNac, String fechaAlta, double sueldo, String departamento, boolean activo,
			LinkedList<Tecnico> tecnicos, LinkedList<Incidencia> pendientes, LinkedList<Incidencia> terminadas,
			LinkedList<Barrio> barrios, int objetivo) {
		super(nombre, apellido1, apellido2, dni, direccion, telefono, fechaNac, fechaAlta, sueldo);
		this.departamento = departamento;
		this.activo = activo;
		if(tecnicos !=null) {
			this.tecnicos = tecnicos;
		}else {
			this.tecnicos = new LinkedList<Tecnico>();
		}
		if(pendientes !=null) {
			this.pendientes = pendientes;
		}else {
			this.pendientes = new LinkedList<Incidencia>();
		}
		if(terminadas !=null) {
			this.terminadas = terminadas;
		}else {
			this.terminadas = new LinkedList<Incidencia>();
		}
		if(barrios !=null) {
			this.barrios = barrios;
		}else {
			this.barrios = new LinkedList<Barrio>();
		}
		this.objetivo = objetivo;
	}
	public Supervisor(int codigo, String nombre, String apellido1, String apellido2, String dni, String direccion, String telefono,
			String fechaNac, String fechaAlta, double sueldo, String departamento, boolean activo,
			LinkedList<Tecnico> tecnicos, LinkedList<Incidencia> pendientes, LinkedList<Incidencia> terminadas,
			LinkedList<Barrio> barrios, int objetivo) {
		super(codigo, nombre, apellido1, apellido2, dni, direccion, telefono, fechaNac, fechaAlta, sueldo);
		this.departamento = departamento;
		this.activo = activo;
		if(tecnicos !=null) {
			this.tecnicos = tecnicos;
		}else {
			this.tecnicos = new LinkedList<Tecnico>();
		}
		if(pendientes !=null) {
			this.pendientes = pendientes;
		}else {
			this.pendientes = new LinkedList<Incidencia>();
		}
		if(terminadas !=null) {
			this.terminadas = terminadas;
		}else {
			this.terminadas = new LinkedList<Incidencia>();
		}
		if(barrios !=null) {
			this.barrios = barrios;
		}else {
			this.barrios = new LinkedList<Barrio>();
		}
		this.objetivo = objetivo;
	}
	public Supervisor(String nombre, String apellido1,  String dni,  String telefono) {
		super(nombre, apellido1);
		this.departamento = "No asignado";
		this.activo = false;
		this.tecnicos = new LinkedList<Tecnico>();
		this.pendientes = new LinkedList<Incidencia>();
		this.terminadas = new LinkedList<Incidencia>();
		this.barrios = new LinkedList<Barrio>();
		this.objetivo = 25;
	}
	public Supervisor() {
		super();
		this.departamento = "No asignado";
		this.activo = false;
		this.tecnicos = new LinkedList<Tecnico>();
		this.pendientes = new LinkedList<Incidencia>();
		this.terminadas = new LinkedList<Incidencia>();
		this.barrios = new LinkedList<Barrio>();
		this.objetivo = 25;
	}
	public Supervisor(Supervisor s) {
		super(s);
		this.departamento = s.departamento;
		this.activo = s.activo;
		if(tecnicos !=null) {
			this.tecnicos = s.tecnicos;
		}else {
			this.tecnicos = new LinkedList<Tecnico>();
		}
		if(pendientes !=null) {
			this.pendientes = s.pendientes;
		}else {
			this.pendientes = new LinkedList<Incidencia>();
		}
		if(terminadas !=null) {
			this.terminadas = s.terminadas;
		}else {
			this.terminadas = new LinkedList<Incidencia>();
		}
		if(barrios !=null) {
			this.barrios = s.barrios;
		}else {
			this.barrios = new LinkedList<Barrio>();
		}
		this.objetivo = s.objetivo;
	}
	// -------------------------- GETTERS AND SETTERS -------------------------
	public final String getDepartamento() {
		return departamento;
	}
	public final void setDepartamento(String departamento) {
		this.departamento = departamento;
	}
	public final boolean isActivo() {
		return activo;
	}
	public final void setActivo(boolean activo) {
		this.activo = activo;
	}
	public final LinkedList<Tecnico> getTecnicos() {
		return tecnicos;
	}
	public final void setTecnicos(LinkedList<Tecnico> tecnicos) {
		this.tecnicos = tecnicos;
	}
	public final LinkedList<Incidencia> getPendientes() {
		return pendientes;
	}
	public final void setPendientes(LinkedList<Incidencia> pendientes) {
		this.pendientes = pendientes;
	}
	public final LinkedList<Incidencia> getTerminadas() {
		return terminadas;
	}
	public final void setTerminadas(LinkedList<Incidencia> terminadas) {
		this.terminadas = terminadas;
	}
	public final LinkedList<Barrio> getBarrios() {
		return barrios;
	}
	public final void setBarrios(LinkedList<Barrio> barrios) {
		this.barrios = barrios;
	}
	public final int getObjetivo() {
		return objetivo;
	}
	public final void setObjetivo(int objetivo) {
		this.objetivo = objetivo;
	}
	// ----------------------------- METODOS --------------------------
	public void mostrarPendientes() {
		for(Incidencia i:this.pendientes) {
			i.mostrarIncidencia();
		}
	}
	public void mostrarTerminadas() {
		for(Incidencia i:this.terminadas) {
			i.mostrarIncidencia();
		}
	}
	public void mostrarActual() {
		this.pendientes.getLast().mostrarIncidencia();
	}
	public void sumarIncidencia(Incidencia i) {
		this.pendientes.addFirst(i);
	}
	public void quitarIncidencia(Incidencia i) {
		if(this.pendientes.contains(i)) {
			int indice = this.pendientes.indexOf(i);
			this.pendientes.remove(indice);
			this.terminadas.addFirst(i);
		}
	}
	public void asignarBarrio(Barrio b) {
		this.barrios.add(b);
	}
	public void quitarBarrio(Barrio b) {
		if(this.barrios.contains(b)) {
			this.barrios.remove(b);
		}else {
			System.out.println("Ese barrio no está asignado a este supervisor.");
		}
	}
	@Override
	public void generarInforme() {
		System.out.println("Informe de supervisor:");
		System.out.println("Datos personales:");
		super.mostrarPersona();
		System.out.println("Datos supervisor:");
		System.out.println("Departamento: "+ this.departamento);
		System.out.println("Ha resuelto "+ this.terminadas.size()+" incidencias.");
		System.out.println("Tiene "+ this.pendientes.size()+" incidencias pendientes");
		System.out.println("Tiene asignados "+this.barrios.size()+" barrios y "+ this.tecnicos.size()+" técnicos.");
		System.out.println("Su objetivo es: "+this.objetivo+".\n");
	}

}
