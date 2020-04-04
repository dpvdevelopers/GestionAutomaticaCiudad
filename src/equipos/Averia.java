package equipos;

import java.io.Serializable;
import java.util.Calendar;

public class Averia implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -6724168153861138362L;
	// --------------------------------------- ATRIBUTOS --------------------------------------
	private int codigo;
	private static int codSig = 1;
	private int gravedad;
	private double coste;
	private String fechaAlta;
	private String fechaRep;
	private String descripcion;
	private boolean resuelta;
	// ---------------------------------------- CONSTRUCTORES ---------------------------------
	public Averia(int gravedad, double coste, String fechaAlta, String fechaRep, String descripcion,
			boolean resuelta) {
		this.codigo = Averia.codSig;
		Averia.codSig++;
		this.gravedad = gravedad;
		this.coste = coste;
		this.fechaAlta = fechaAlta;
		this.fechaRep = fechaRep;
		this.descripcion = descripcion;
		this.resuelta = resuelta;
	}
	public Averia(int gravedad) {
		this.codigo = Averia.codSig;
		Averia.codSig++;
		this.gravedad = gravedad;
		this.coste = 0.0;
		this.fechaAlta =Calendar.getInstance().toString();
		this.fechaRep = "Pendiente";
		this.descripcion = "";
		this.resuelta = false;
	}
	public Averia() {
		this.codigo = Averia.codSig;
		Averia.codSig++;
		this.gravedad = 0;
		this.coste = 0.0;
		this.fechaAlta = Calendar.getInstance().toString();
		this.fechaRep = "Pendiente";
		this.descripcion = "";
		this.resuelta = false;
	}
	public Averia(Averia a) {
		this.codigo = a.codigo;
		this.gravedad = a.gravedad;
		this.coste = a.coste;
		this.fechaAlta = a.fechaAlta;
		this.fechaRep = a.fechaRep;
		this.descripcion = a.descripcion;
		this.resuelta = a.resuelta;
	}
	// ----------------------------------------- METODOS -----------------------------------------
	public final int getGravedad() {
		return gravedad;
	}
	public final void setGravedad(int gravedad) {
		this.gravedad = gravedad;
	}
	public final double getCoste() {
		return coste;
	}
	public final void setCoste(double coste) {
		this.coste = coste;
	}
	public final String getFechaRep() {
		return fechaRep;
	}
	public final void setFechaRep(String fechaRep) {
		this.fechaRep = fechaRep;
	}
	public final String getDescripcion() {
		return descripcion;
	}
	public final void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public final boolean isResuelta() {
		return resuelta;
	}
	public final void setResuelta(boolean resuelta) {
		this.resuelta = resuelta;
	}
	public final int getCodigo() {
		return codigo;
	}
	public final String getFechaAlta() {
		return fechaAlta;
	}
	// ------------------------------------------------ hashCode & equals ------------------------------
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
		Averia other = (Averia) obj;
		if (codigo != other.codigo)
			return false;
		return true;
	}
	// -------------------------------------------------- toString -----------------------------
	@Override
	public String toString() {
		return "Averia [codigo=" + codigo + ", descripcion=" + descripcion + ", fechaAlta=" + fechaAlta + ", fechaRep="
				+ fechaRep + ", gravedad=" + gravedad + ", coste=" + coste + ", resuelta=" + resuelta + "]";
	}
	// -------------------------------------------------- marcarReparada -----------------------
	public void marcarReparada() {
		this.resuelta = true;
	}
	public void subirCoste(int prima) {
		this.coste += prima;
	}
	public void bajarCoste(int descuento) {
		this.coste-=descuento;
	}
	public void mostrarCoste() {
		System.out.println("El coste de reparación de la avería " + this.codigo + " es de: "+ this.coste+ "€");
	}
	public void mostrarFechaRep() {
		if(this.resuelta) {
			System.out.println("La avería "+this.codigo+" fue reparada el: "+ this.fechaRep);
		}else {
			System.out.println("La avería "+this.codigo+" está pendiente de reparación");
		}
	}
	public void mostrarAveria() {
		System.out.println("Código averia: "+ this.codigo);
		System.out.println("Descripción: "+this.descripcion);
		System.out.println("Fecha: "+ this.fechaAlta);
		System.out.println("Gravedad: "+ this.gravedad);
		System.out.println("Coste reparación: "+ this.coste);
		if(this.resuelta) {
			System.out.println("Se reparó el: " + this.fechaRep);
		}else {
			System.out.println("Está pendiente de reparación");
		}
	}

}
