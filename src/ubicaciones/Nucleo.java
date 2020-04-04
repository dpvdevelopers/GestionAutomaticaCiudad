package ubicaciones;

import java.io.Serializable;

public abstract class Nucleo implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -3965830958524304451L;
	// -------------------------------------- ATRIBUTOS --------------------------------------------
	private int codigo;
	private static int codSig;
	private String nombre;
	private String horaEnc;
	private String horaApag;
	// -------------------------------------- CONSTRUCTORES ----------------------------------------
	public Nucleo(String nombre) {
		this.codigo = Nucleo.codSig;
		Nucleo.codSig ++;
		this.nombre = nombre;
		this.horaEnc = "No asignado";
		this.horaApag = "No asignado";
	}
	public Nucleo(String nombre, String horaEnc, String horaApag) {
		this.codigo = Nucleo.codSig;
		Nucleo.codSig ++;
		this.nombre = nombre;
		this.horaEnc = horaEnc;
		this.horaApag = horaApag;
	}
	public Nucleo() {
		this.codigo = Nucleo.codSig;
		Nucleo.codSig ++;
		this.nombre = "Sin nombre";
		this.horaEnc = "No asignado";
		this.horaApag = "No asignado";
	}
	public Nucleo(Nucleo n) {
		this.codigo = Nucleo.codSig;
		Nucleo.codSig ++;
		this.nombre = n.nombre;
		this.horaEnc = n.horaEnc;
		this.horaApag = n.horaApag;
	}
	// -------------------------------------- GETTERS AND SETTERS -----------------------------------
	public final String getNombre() {
		return nombre;
	}
	public final void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public final int getCodigo() {
		return codigo;
	}
	public final String getHoraEnc() {
		return horaEnc;
	}
	public final void setHoraEnc(String horaEnc) {
		this.horaEnc = horaEnc;
	}
	public final String getHoraApag() {
		return horaApag;
	}
	public final void setHoraApag(String horaApag) {
		this.horaApag = horaApag;
	}
	// -------------------------------------- hash code and equals -----------------------------------
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
		Nucleo other = (Nucleo) obj;
		if (codigo != other.codigo)
			return false;
		return true;
	}
	// ------------------------------------------ toString -------------------------------------------
	
	@Override
	public String toString() {
		return "Nucleo [codigo=" + codigo + ", nombre=" + nombre + "]";
	}
	// -------------------------------------- METODOS ABSTRACTOS --------------------------------------
	public abstract void mostrarDispositivos();
	public abstract void encender();
	public abstract void apagar();
	public abstract void programarEncendido(String horaEnc, String horaApag);
	public abstract void mostrarAverias();
	public abstract void mostrarReparaciones();	
}
