package equipos;

import java.io.Serializable;
import java.util.Calendar;
/**
 * Clase que implementa la interfaz serializable, para manejar las posibles aver�as de los dispositivos
 * @author Daniel Pulido
 *
 */
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
	/**
	 * Constructor normal, asigna al objeto el siguiente codigo disponible adem�s de los atributos establecidos en los par�metros.
	 * @param gravedad Dato de tipo entero que establece la gravedad de la aver�a.
	 * @param coste Coste de la reparaci�n en un dato de tipo double.
	 * @param fechaAlta Cadena de tipo String que contiene la fecha de alta de la aver�a.
	 * @param fechaRep Cadena de tipo String que contiene la fecha de reparaci�n de la aver�a.
	 * @param descripcion Cadena de tipo String que contiene la descripci�n completa de la aver�a.
	 * @param resuelta Boolean que contiene true si la aver�a ya ha sido resuelta, en caso contrario contiene false.
	 */
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
	/**
	 * Constructor completo, asigna al objeto el  codigo pasado por par�metro adem�s de todos los dem�s atributos.
	 * @param codigo Dato de tipo int que establece el codigo �nico de la aver�a.
	 * @param gravedad Dato de tipo entero que establece la gravedad de la aver�a.
	 * @param coste Coste de la reparaci�n en un dato de tipo double.
	 * @param fechaAlta Cadena de tipo String que contiene la fecha de alta de la aver�a.
	 * @param fechaRep Cadena de tipo String que contiene la fecha de reparaci�n de la aver�a.
	 * @param descripcion Cadena de tipo String que contiene la descripci�n completa de la aver�a.
	 * @param resuelta Boolean que contiene true si la aver�a ya ha sido resuelta, en caso contrario contiene false.
	 */
	public Averia(int codigo, int gravedad, double coste, String fechaAlta, String fechaRep, String descripcion,
			boolean resuelta) {
		this.codigo = codigo;
		this.gravedad = gravedad;
		this.coste = coste;
		this.fechaAlta = fechaAlta;
		this.fechaRep = fechaRep;
		this.descripcion = descripcion;
		this.resuelta = resuelta;
	}
	/**
	 * Constructor b�sico, �nicamente asigna el c�digo autom�tico y la gravedad pasada por par�metro, establece como fecha de alta la de ejecuci�n del constructor.
	 * @param gravedad Entero que establece la gravedad de la aver�a.
	 */
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
	/**
	 * Constructor por defecto, solo establece el c�digo y la fecha de alta, los dem�s campos quedan con los valores por defecto.
	 */
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
	/**
	 * Constructor copia, realiza una copia exacta de una aver�a.
	 * @param a Objeto de tipo Averia que se copiar� en la nueva instancia.
	 */
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
	public static final int getCodSig() {
		return codSig;
	}
	public static final void setCodSig(int codSig) {
		Averia.codSig = codSig;
	}
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
	public final void setCodigo(int codigo) {
		this.codigo = codigo;
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
	/**
	 * aumenta el coste de la reparaci�n, sumandole el importe pasado en el par�metro.
	 * @param prima double que se sumar� al coste.
	 */
	public void subirCoste(double prima) {
		this.coste += prima;
	}
	/**
	 * reduce el coste de la reparaci�n restandole el importe pasado en el par�metro.
	 * @param descuento Double que se restar� del coste.
	 */
	public void bajarCoste(double descuento) {
		this.coste-=descuento;
	}
	public void mostrarCoste() {
		System.out.println("El coste de reparaci�n de la aver�a " + this.codigo + " es de: "+ this.coste+ "�");
	}
	public void mostrarFechaRep() {
		if(this.resuelta) {
			System.out.println("La aver�a "+this.codigo+" fue reparada el: "+ this.fechaRep);
		}else {
			System.out.println("La aver�a "+this.codigo+" est� pendiente de reparaci�n");
		}
	}
	/**
	 * Muestra por consola los datos de la aver�a
	 */
	public void mostrarAveria() {
		System.out.println("C�digo averia: "+ this.codigo);
		System.out.println("Descripci�n: "+this.descripcion);
		System.out.println("Fecha: "+ this.fechaAlta);
		System.out.println("Gravedad: "+ this.gravedad);
		System.out.println("Coste reparaci�n: "+ this.coste);
		if(this.resuelta) {
			System.out.println("Se repar� el: " + this.fechaRep);
		}else {
			System.out.println("Est� pendiente de reparaci�n");
		}
	}

}
