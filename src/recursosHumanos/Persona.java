package recursosHumanos;

import java.io.Serializable;

public abstract class Persona implements Informes, Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -8596387195605321444L;
	// --------------------------------------- ATRIBUTOS --------------------------------------
	private int codigo;
	private static int codSig = 1;
	private String nombre;
	private String apellido1;
	private String apellido2;
	private String dni;
	private String direccion;
	private String telefono;
	private String fechaNac;
	private String fechaAlta;
	private double sueldo;
	// ---------------------------------------- CONSTRUCTORES ----------------------------------
	
	public Persona(String nombre, String apellido1, String apellido2, String dni, String direccion,
			String telefono, String fechaNac, String fechaAlta, double sueldo) {
		this.codigo = Persona.codSig;
		Persona.codSig++;
		this.nombre = nombre;
		this.apellido1 = apellido1;
		this.apellido2 = apellido2;
		this.dni = dni;
		this.direccion = direccion;
		this.telefono = telefono;
		this.fechaNac = fechaNac;
		this.fechaAlta = fechaAlta;
		this.sueldo = sueldo;
	}
	public Persona(int codigo, String nombre, String apellido1, String apellido2, String dni, String direccion,
			String telefono, String fechaNac, String fechaAlta, double sueldo) {
		this.codigo = codigo;
		this.nombre = nombre;
		this.apellido1 = apellido1;
		this.apellido2 = apellido2;
		this.dni = dni;
		this.direccion = direccion;
		this.telefono = telefono;
		this.fechaNac = fechaNac;
		this.fechaAlta = fechaAlta;
		this.sueldo = sueldo;
	}
	public Persona(String nombre, String apellido1) {
		this.codigo = Persona.codSig;
		Persona.codSig++;
		this.nombre = nombre;
		this.apellido1 = apellido1;
		this.apellido2 = "";
		this.dni = "";
		this.direccion = "";
		this.telefono = "";
		this.fechaNac = "";
		this.fechaAlta = "";
		this.sueldo = 0.0;
	}
	public Persona() {
		this.codigo = Persona.codSig;
		Persona.codSig++;
		this.nombre = "";
		this.apellido1 = "";
		this.apellido2 = "";
		this.dni = "";
		this.direccion = "";
		this.telefono = "";
		this.fechaNac = "";
		this.fechaAlta = "";
		this.sueldo = 0.0;
	}
	public Persona(Persona p) {
		this.codigo = p.codigo;
		this.nombre = p.nombre;
		this.apellido1 = p.apellido1;
		this.apellido2 = p.apellido2;
		this.dni = p.dni;
		this.direccion = p.direccion;
		this.telefono = p.telefono;
		this.fechaNac = p.fechaNac;
		this.fechaAlta = p.fechaAlta;
		this.sueldo = p.sueldo;
	}
	// ----------------------------------------- getters and setters ------------------------------------
	public final String getNombre() {
		return nombre;
	}
	public final void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public final String getApellido1() {
		return apellido1;
	}
	public final void setApellido1(String apellido1) {
		this.apellido1 = apellido1;
	}
	public final String getApellido2() {
		return apellido2;
	}
	public final void setApellido2(String apellido2) {
		this.apellido2 = apellido2;
	}
	public final String getDni() {
		return dni;
	}
	public final void setDni(String dni) {
		this.dni = dni;
	}
	public final String getDireccion() {
		return direccion;
	}
	public final void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	public final String getTelefono() {
		return telefono;
	}
	public final void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	public final String getFechaNac() {
		return fechaNac;
	}
	public final void setFechaNac(String fechaNac) {
		this.fechaNac = fechaNac;
	}
	public final String getFechaAlta() {
		return fechaAlta;
	}
	public final void setFechaAlta(String fechaAlta) {
		this.fechaAlta = fechaAlta;
	}
	public final double getSueldo() {
		return sueldo;
	}
	public final void setSueldo(double sueldo) {
		this.sueldo = sueldo;
	}
	public final int getCodigo() {
		return codigo;
	}
	public static final int getCodSig() {
		return codSig;
	}
	// ------------------------------------------------- hash code and equals --------------------------------
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
		Persona other = (Persona) obj;
		if (codigo != other.codigo)
			return false;
		return true;
	}
	// ----------------------------------------------- toString -------------------------------------------
	@Override
	public String toString() {
		return "Persona [codigo=" + codigo + ", nombre=" + nombre + ", apellido1=" + apellido1 + ", apellido2="
				+ apellido2 + ", dni=" + dni + ", direccion=" + direccion + ", telefono=" + telefono + ", fechaNac="
				+ fechaNac + ", fechaAlta=" + fechaAlta + ", sueldo=" + sueldo + "]";
	}
	// ----------------------------------------------- METODOS --------------------------------------------
	public void mostrarPersona() {
		System.out.println("Código: "+ this.codigo);
		System.out.println("Nombre completo: "+ this.nombre+" "+ this.apellido1+" "+ this.apellido2+".");
		System.out.println("Teléfono: "+ this.telefono+ ".");
		System.out.println("Dirección: "+ this.direccion+".");
		System.out.println("DNI: "+ this.dni);
		System.out.println("Fecha de nacimiento: "+ this.fechaNac);
		System.out.println("Se incorporó el día: "+this.fechaAlta);
		System.out.println("Su sueldo base es de: "+this.sueldo+" €.");
	}
	@Override
	public abstract void generarInforme();
	

}
