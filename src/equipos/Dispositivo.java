package equipos;

import java.io.Serializable;
import java.util.LinkedList;

public abstract class Dispositivo implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 2696376357139153894L;

	// --------------------------------------- ATRIBUTOS -------------------------------------
	private static int codSig;
	
	private int codigo;
	private double precio;
	private String descripcion;
	private String coordenadas;
	private String fabricante;
	private String horaEnc;
	private String horaApag;
	private boolean encendido;
	private LinkedList<Averia> averias;
	// --------------------------------------- CONSTRUCTORES ---------------------------------
	public Dispositivo(double precio, String descripcion, String coordenadas, String fabricante,
				String horaEnc, String horaApag, boolean operativo,	LinkedList<Averia> averias) {
		this.codigo = Dispositivo.codSig;
		Dispositivo.codSig++;
		this.precio = precio;
		this.descripcion = descripcion;
		this.coordenadas = coordenadas;
		this.fabricante = fabricante;
		this.horaEnc = horaEnc;
		this.horaApag = horaApag;
		this.encendido = operativo;
		if(averias != null) {
			this.averias = averias;
		}else {
			this.averias = new LinkedList<Averia>(); 
		}
	}
	public Dispositivo(int codigo, double precio, String descripcion, String coordenadas, String fabricante,
			String horaEnc, String horaApag, boolean operativo,	LinkedList<Averia> averias) {
	this.codigo = codigo;
	this.precio = precio;
	this.descripcion = descripcion;
	this.coordenadas = coordenadas;
	this.fabricante = fabricante;
	this.horaEnc = horaEnc;
	this.horaApag = horaApag;
	this.encendido = operativo;
	if(averias != null) {
		this.averias = averias;
	}else {
		this.averias = new LinkedList<Averia>(); 
	}
}
	public Dispositivo(Dispositivo d) {
		if(d!=null) {
			this.codigo = Dispositivo.codSig;
			Dispositivo.codSig++;
			this.precio = d.precio;
			this.descripcion = d.descripcion;
			this.coordenadas = d.coordenadas;
			this.fabricante = d.fabricante;
			this.horaEnc = d.horaEnc;
			this.horaApag = d.horaApag;
			this.encendido = d.encendido;
			if(d.averias != null) {
				this.averias = d.averias;
			}else {
				this.averias = new LinkedList<Averia>(); 
			}
		}else {
			this.codigo = Dispositivo.codSig;
			Dispositivo.codSig++;
			this.precio = 0.0;
			this.descripcion = "Sin descripcion";
			this.coordenadas = "";
			this.fabricante = "";
			this.horaEnc = "No asignado";
			this.horaApag = "No asignado";
			this.encendido = true;
			this.averias = new LinkedList<Averia>(); 
		}
	}
	public Dispositivo() {
		this.codigo = Dispositivo.codSig;
		Dispositivo.codSig++;
		this.precio = 0.0;
		this.descripcion = "Sin descripcion";
		this.coordenadas = "";
		this.fabricante = "";
		this.horaEnc = "No asignado";
		this.horaApag = "No asignado";
		this.encendido = true;
		this.averias = new LinkedList<Averia>(); 
	}
	// ---------------------------------- getters and setters ---------------------------------
	public final double getPrecio() {
		return precio;
	}
	public final void setPrecio(double precio) {
		this.precio = precio;
	}
	public final String getDescripcion() {
		return descripcion;
	}
	public final void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public final String getCoordenadas() {
		return coordenadas;
	}
	public final void setCoordenadas(String coordenadas) {
		this.coordenadas = coordenadas;
	}
	public final String getFabricante() {
		return fabricante;
	}
	public final void setFabricante(String fabricante) {
		this.fabricante = fabricante;
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
	public final boolean isOperativo() {
		return encendido;
	}
	public final void setOperativo(boolean operativo) {
		this.encendido = operativo;
	}
	public final LinkedList<Averia> getAverias() {
		return averias;
	}
	public final void setAverias(LinkedList<Averia> averias) {
		this.averias = averias;
	}
	public final int getCodigo() {
		return codigo;
	}
	// ------------------------------------------ hash code & equals -------------------------
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
		Dispositivo other = (Dispositivo) obj;
		if (codigo != other.codigo)
			return false;
		return true;
	}
	// -------------------------------------------- toString ---------------------------------
	@Override
	public String toString() {
		return "Dispositivo [codigo=" + codigo + ", descripcion=" + descripcion + ", coordenadas=" + coordenadas
				+ ", fabricante=" + fabricante + ", operativo=" + encendido + ", precio=" + precio + ", averias="
				+ averias + "]";
	}
	// --------------------------------------------- metodos ---------------------------------
	public void mostrarDispositivo(){
		System.out.println("Dispositivo con código: "+ this.codigo);
		System.out.println("Fabricante: "+ this.fabricante);
		System.out.println("Descripción: "+ this.descripcion);
		System.out.println("Precio: "+ this.precio+ "€");
		System.out.println("Ubicado en: " + this.coordenadas);
		System.out.println("Ha tenido las siguientes averías:");
		this.mostrarAverias();
	}
	public void encenderDispositivo() {
		this.encendido = true;
	}
	public void apagarDispositivo() {
		this.encendido=false;
	}
	public void mostrarEstado() {
		if(encendido) {
			System.out.println("El dispositivo está encendido y funcionando correctamente.");
		}else {
			if(averias.isEmpty()) {
				System.out.println("El dispositivo está apagado, pero funciona correctamente.");
			}else {
				if(!averias.getLast().isResuelta()) {
					System.out.println("El dispositivo está averiado.");
				}
			}
		}
	}
	public void mostrarAverias() {
		if(this.averias.isEmpty()) {
			System.out.println("Este dispositivo no se ha averiado nunca.");
		}else {
			for(Averia a: averias) {
				a.mostrarAveria();
			}
		}
	}
	public void mostrarReparaciones() {
		boolean reparado = false;
		if(this.averias.isEmpty()) {
			System.out.println("Este dispositivo no se ha reparado nunca.");
		}else {
			for(Averia a: averias) {
				if(a.isResuelta()) {
					reparado = true;
					break;
				}
			}
		}
		if(reparado) {
			for(Averia a: averias) {
				if(a.isResuelta()) {
					a.mostrarAveria();
				}
			}
		}
	}
	public void programarEncendido(String horaEnc, String horaApag) {
		this.setHoraEnc(horaEnc);
		this.setHoraApag(horaApag);
	}
	public abstract void enviarReparacion();
	
}
	