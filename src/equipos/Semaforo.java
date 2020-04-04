package equipos;

import java.util.LinkedList;

public class Semaforo extends Dispositivo {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 2043314222300845924L;
	// ----------------------------------------- ATRIBUTOS --------------------------------------------
	private boolean ambar;
	private int segVerde;
	private int segAmarillo;
	private int segRojo;
	// ----------------------------------------- CONSTRUCTORES ----------------------------------------
	public Semaforo(double precio, String descripcion, String coordenadas, String fabricante,
			String horaEnc, String horaApag, boolean operativo,	LinkedList<Averia> averias, boolean ambar, int segVerde, int segAmarillo, int segRojo) {
		super(precio, descripcion, coordenadas, fabricante,horaEnc, horaApag, operativo, averias);
		this.ambar = ambar;
		this.segVerde = segVerde;
		this.segAmarillo = segAmarillo;
		this.segRojo = segRojo;
	}
	public Semaforo() {
		super();
		this.ambar = true;
		this.segVerde = 0;
		this.segAmarillo = 0;
		this.segRojo = 0;
	}
	public Semaforo(Semaforo s) {
		super(s);
		this.ambar = s.ambar;
		this.segVerde = s.segVerde;
		this.segAmarillo = s.segAmarillo;
		this.segRojo = s.segRojo;
	}
	// --------------------------------------------- GETTERS AND SETTERS -----------------------------------------
	public final boolean isAmbar() {
		return ambar;
	}
	public final void setAmbar(boolean ambar) {
		if(ambar) {
			this.segVerde = 0;
			this.segAmarillo = 0;
			this.segRojo = 0;
			this.ambar= ambar;
		}else {
			this.ambar= ambar;
		}
	}
	public final int getSegVerde() {
		return segVerde;
	}
	public final void setSegVerde(int segVerde) {
		this.segVerde = segVerde;
	}
	public final int getSegAmarillo() {
		return segAmarillo;
	}
	public final void setSegAmarillo(int segAmarillo) {
		this.segAmarillo = segAmarillo;
	}
	public final int getSegRojo() {
		return segRojo;
	}
	public final void setSegRojo(int segRojo) {
		this.segRojo = segRojo;
	}
	// -------------------------------------------------- toString ---------------------------------------------
	@Override
	public String toString() {
		return "Semaforo [ambar=" + ambar + ", segVerde=" + segVerde + ", segAmarillo=" + segAmarillo + ", segRojo="
				+ segRojo + "]";
	}
	// -------------------------------------------------- METODOS -----------------------------------------------
	@Override
	public void enviarReparacion() {
		Averia a = new Averia();
		this.getAverias().addFirst(a);
		this.setAmbar(true);
		this.setOperativo(false);
	}
	public void mostrarTiempos(){
		if(this.isOperativo()) {
			if(ambar) {
				System.out.println("El semáforo está actualmente parpadeando en ambar");
			}else {
				System.out.println("El semáforo tiene asignados los siguientes tiempos: \nVerde:"
						+ " "+ this.segVerde+"s, amarillo: "+this.segAmarillo+"s, rojo: "+ this.segRojo+"s.");
			}
		}else {
			System.out.println("El semáforo no está operativo.");
		}
	}
	public void fijarColor(int color) {
		switch (color){
			case 0:
				this.setAmbar(true);
				break;
			case 1:
				this.setAmbar(false);
				this.setSegRojo(3600);
				this.setSegAmarillo(0);
				this.setSegVerde(0);
				break;
			case 2:
				this.setAmbar(false);
				this.setSegRojo(0);
				this.setSegAmarillo(3600);
				this.setSegVerde(0);
				break;
			case 3:
				this.setAmbar(false);
				this.setSegRojo(0);
				this.setSegAmarillo(0);
				this.setSegVerde(3600);
				break;
			default:
				System.out.println("Las opciones validas son: 0->Ambar parpadeando, 1->Rojo, 2->Ambar, 3->Verde.");
				break;
		}
	}
	public void liberarColor() {
		this.ambar=false;
		this.setSegRojo(50);
		this.setSegAmarillo(4);
		this.setSegVerde(60);
	}
}
