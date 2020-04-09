package equipos;

import java.io.Serializable;
import java.util.Calendar;
import java.util.LinkedList;

public class Incidencia implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -2014205144194785277L;
	// -------------------------------- ATRIBUTOS ----------------------------------------
	private static int codSig= 1;
	private int codigo;
	private double coste;
	private String descripcion;
	private String fechaAlta;
	private String fechaRep;
	private int prioridad;
	private boolean resuelta;
	private LinkedList<Averia> averias;
	// -------------------------------- CONSTRUCTORES -------------------------------------
	public Incidencia(double coste, String descripcion, String fechaAlta, String fechaRep, int prioridad,
			boolean resuelta, LinkedList<Averia> averias) {
		this.codigo = Incidencia.codSig;
		Incidencia.codSig++;
		this.coste = coste;
		this.descripcion = descripcion;
		this.fechaAlta = fechaAlta;
		this.fechaRep = fechaRep;
		this.prioridad = prioridad;
		this.resuelta = resuelta;
		if(averias!=null) {
			this.averias = averias;
		}else {
			this.averias = new LinkedList<Averia>();
		}
	}
	public Incidencia(int codigo, double coste, String descripcion, String fechaAlta, String fechaRep, int prioridad,
			boolean resuelta, LinkedList<Averia> averias) {
		this.codigo = codigo;
		this.coste = coste;
		this.descripcion = descripcion;
		this.fechaAlta = fechaAlta;
		this.fechaRep = fechaRep;
		this.prioridad = prioridad;
		this.resuelta = resuelta;
		if(averias!=null) {
			this.averias = averias;
		}else {
			this.averias = new LinkedList<Averia>();
		}
	}
	public Incidencia(String descripcion, String fechaAlta, int prioridad, LinkedList<Averia> averias) {
		this.codigo = Incidencia.codSig;
		Incidencia.codSig++;
		this.coste = 0.0;
		this.descripcion = descripcion;
		this.fechaAlta = fechaAlta;
		this.fechaRep = "Pendiente";
		this.prioridad = prioridad;
		this.resuelta = false;
		if(averias!=null) {
			this.averias = averias;
		}else {
			this.averias = new LinkedList<Averia>();
		}
	}
	public Incidencia() {
		this.codigo = Incidencia.codSig;
		Incidencia.codSig++;
		this.coste = 0.0;
		this.descripcion = "Sin descripcion";
		this.fechaAlta = Calendar.getInstance().toString();
		this.fechaRep = "Pendiente";
		this.prioridad = 0;
		this.resuelta = false;
		this.averias = new LinkedList<Averia>();
	}
	public Incidencia(Incidencia i) {
		this.codigo = Incidencia.codSig;
		Incidencia.codSig++;
		this.coste = i.coste;
		this.descripcion = i.descripcion;
		this.fechaAlta = i.fechaAlta;
		this.fechaRep = i.fechaRep;
		this.prioridad = i.prioridad;
		this.resuelta = i.resuelta;
		if(i.averias!=null) {
			this.averias = i.averias;
		}else {
			this.averias = new LinkedList<Averia>();
		}
	}
	// ------------------------------------------ getters and setters ------------------------------
	public final double getCoste() {
		return coste;
	}
	public final void setCoste(double coste) {
		this.coste = coste;
	}
	public final String getDescripcion() {
		return descripcion;
	}
	public final void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public final String getFechaAlta() {
		return fechaAlta;
	}
	public final void setFechaAlta(String fechaAlta) {
		this.fechaAlta = fechaAlta;
	}
	public final String getFechaRep() {
		return fechaRep;
	}
	public final void setFechaRep(String fechaRep) {
		this.fechaRep = fechaRep;
	}
	public final int getPrioridad() {
		return prioridad;
	}
	public final void setPrioridad(int prioridad) {
		this.prioridad = prioridad;
	}
	public final boolean isResuelta() {
		return resuelta;
	}
	public final void setResuelta(boolean resuelta) {
		this.resuelta = resuelta;
	}
	public final LinkedList<Averia> getAverias() {
		return averias;
	}
	public final void setAverias(LinkedList<Averia> averias) {
		this.averias = averias;
	}
	public static final int getCodSig() {
		return codSig;
	}
	public final int getCodigo() {
		return codigo;
	}
	// ----------------------------------- hash code and equals -------------------------------
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + codigo;
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Incidencia other = (Incidencia) obj;
		if (codigo != other.codigo)
			return false;
		return true;
	}
	// ------------------------------------ toString ---------------------------------------------
	@Override
	public String toString() {
		return "Incidencia [codigo=" + codigo + ", coste=" + coste + ", descripcion=" + descripcion + ", fechaAlta="
				+ fechaAlta + ", fechaRep=" + fechaRep + ", prioridad=" + prioridad + ", resuelta=" + resuelta
				+ ", averias=" + averias + "]";
	}
	// ----------------------------------------- METODOS -------------------------------------------
	public void mostrarIncidencia() {
		System.out.print("Incidencia nº: "+ this.codigo+ "\t");
		System.out.println("Fecha de alta: "+ this.fechaAlta);
		System.out.println("Descripción: "+this.descripcion);
		System.out.println("Coste: "+this.coste+"€\t");
		if(this.resuelta) {
			System.out.println("Esta incidencia se resolvió el: "+this.fechaRep+" y constaba de las siguientes averías:");
			for(Averia a: this.averias) {
				a.mostrarAveria();
			}
		}else {
			System.out.println("La incidencia sigue abierta, se han resuelto las siguientes averías:");
			for(Averia a:this.averias) {
				if(a.isResuelta())
					a.mostrarAveria();
			}
			System.out.println("Quedan por resolver las siguientes averías:");
			for(Averia a:this.averias) {
				if(!a.isResuelta())
					a.mostrarAveria();
			}
		}
	}
	public void marcarReparada() {
		for(Averia a:this.averias) {
			if(!a.isResuelta())
				a.marcarReparada();
				a.setFechaRep(Calendar.getInstance().toString());
		}
		this.setFechaRep(Calendar.getInstance().toString());
	}
	public void sumarAveria(Averia a) {
		this.averias.addFirst(a);
	}
}
