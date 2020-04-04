package recursosHumanos;

import java.util.LinkedList;
import equipos.Incidencia;
import ubicaciones.Distrito;

public class Jefe extends Persona {
	/**
	 * 
	 */
	private static final long serialVersionUID = 8578627461745841704L;
		// ----------------------------- ATRIBUTOS ----------------------------
		String departamento;
		boolean activo;
		LinkedList<Supervisor> supervisores;
		LinkedList<Incidencia> pendientes;
		LinkedList<Incidencia> terminadas;
		LinkedList<Distrito> distritos;
		int objetivo;
		// ----------------------------- CONSTRUCTORES --------------------------
		public Jefe(String nombre, String apellido1, String apellido2, String dni, String direccion, String telefono,
				String fechaNac, String fechaAlta, double sueldo, String departamento, boolean activo,
				LinkedList<Supervisor> supervisores, LinkedList<Incidencia> pendientes, LinkedList<Incidencia> terminadas,
				LinkedList<Distrito> distritos, int objetivo) {
			super(nombre, apellido1, apellido2, dni, direccion, telefono, fechaNac, fechaAlta, sueldo);
			this.departamento = departamento;
			this.activo = activo;
			if(supervisores !=null) {
				this.supervisores = supervisores;
			}else {
				this.supervisores = new LinkedList<Supervisor>();
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
			if(distritos !=null) {
				this.distritos = distritos;
			}else {
				this.distritos = new LinkedList<Distrito>();
			}
			this.objetivo = objetivo;
		}
		public Jefe(String nombre, String apellido1,  String dni,  String telefono) {
			super(nombre, apellido1);
			this.departamento = "No asignado";
			this.activo = false;
			this.supervisores = new LinkedList<Supervisor>();
			this.pendientes = new LinkedList<Incidencia>();
			this.terminadas = new LinkedList<Incidencia>();
			this.distritos = new LinkedList<Distrito>();
			this.objetivo = 25;
		}
		public Jefe() {
			super();
			this.departamento = "No asignado";
			this.activo = false;
			this.supervisores = new LinkedList<Supervisor>();
			this.pendientes = new LinkedList<Incidencia>();
			this.terminadas = new LinkedList<Incidencia>();
			this.distritos = new LinkedList<Distrito>();
			this.objetivo = 25;
		}
		public Jefe(Jefe s) {
			super(s);
			this.departamento = s.departamento;
			this.activo = s.activo;
			if(supervisores !=null) {
				this.supervisores = s.supervisores;
			}else {
				this.supervisores = new LinkedList<Supervisor>();
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
			if(distritos !=null) {
				this.distritos = s.distritos;
			}else {
				this.distritos = new LinkedList<Distrito>();
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
		public final LinkedList<Supervisor> getSupervisores() {
			return supervisores;
		}
		public final void setSupervisores(LinkedList<Supervisor> supervisor) {
			this.supervisores = supervisor;
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
		public final LinkedList<Distrito> getDistritos() {
			return distritos;
		}
		public final void setDistritos(LinkedList<Distrito> distritos) {
			this.distritos = distritos;
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
		public void asignarDistrito(Distrito d) {
			this.distritos.add(d);
		}
		public void quitarDistrito(Distrito d) {
			if(this.distritos.contains(d)) {
				this.distritos.remove(d);
			}else {
				System.out.println("Ese distrito no está asignado a este jefe.");
			}
		}
		@Override
		public void generarInforme() {
			System.out.println("Informe de supervisor:");
			System.out.println("Datos personales:");
			super.mostrarPersona();
			System.out.println("Datos jefe:");
			System.out.println("Departamento: "+ this.departamento);
			System.out.println("Ha resuelto "+ this.terminadas.size()+" incidencias.");
			System.out.println("Tiene "+ this.pendientes.size()+" incidencias pendientes");
			System.out.println("Tiene asignados "+this.distritos.size()+" distritos y "+ this.supervisores.size()+" supervisores.");
			System.out.println("Su objetivo es: "+this.objetivo+".\n");
		}


}
