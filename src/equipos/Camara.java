package equipos;

import java.util.LinkedList;
/**
 * Clase que hereda de Dispositivo, añade las funcionalidades para un dispositivo tipo Cámara, incluye el ángulo vertical, ángulo horizontal, y si es móvil.
 * @author Daniel Pulido
 * @see equipos.Dispositivo
 */
public class Camara extends Dispositivo {
	/**
	 * 
	 */
	private static final long serialVersionUID = -7292336054721516713L;
	// ------------------------------------------------- ATRIBUTOS ----------------------------------------------
	private boolean movil;
	private int angVertical;
	private int angHorizontal;
	// ------------------------------------------------- CONSTRUCTORES ------------------------------------------
	public Camara(double precio, String descripcion, String coordenadas, String fabricante,
			String horaEnc, String horaApag, boolean operativo,	LinkedList<Averia> averias, boolean movil, int angVertical, int angHorizontal) {
		super(precio, descripcion, coordenadas, fabricante, horaEnc, horaApag, operativo, averias);
		this.movil = movil;
		this.angVertical = angVertical;
		this.angHorizontal = angHorizontal;
	}
	/**
	 * Constructor completo, establece todos los valores según los parámetros recibidos.
	 * @param codigo
	 * @param precio
	 * @param descripcion
	 * @param coordenadas
	 * @param fabricante
	 * @param horaEnc
	 * @param horaApag
	 * @param operativo
	 * @param averias
	 * @param movil
	 * @param angVertical
	 * @param angHorizontal
	 */
	public Camara(int codigo, double precio, String descripcion, String coordenadas, String fabricante,
			String horaEnc, String horaApag, boolean operativo,	LinkedList<Averia> averias, boolean movil, int angVertical, int angHorizontal) {
		super(codigo, precio, descripcion, coordenadas, fabricante, horaEnc, horaApag, operativo, averias);
		this.movil = movil;
		this.angVertical = angVertical;
		this.angHorizontal = angHorizontal;
	}
	public Camara() {
		super();
		this.movil = false;
		this.angVertical = 0;
		this.angHorizontal = 0;
	}
	public Camara(Camara c) {
		super(c);
		this.movil = c.movil;
		this.angVertical = c.angVertical;
		this.angHorizontal = c.angHorizontal;
	}
	// --------------------------------------------------- GETTERS AND SETTERS -------------------------------------
	public final boolean isMovil() {
		return movil;
	}
	public final void setMovil(boolean movil) {
		this.movil = movil;
	}
	public final int getAngVertical() {
		return angVertical;
	}
	public final void setAngVertical(int angVertical) {
		this.angVertical = angVertical;
	}
	public final int getAngHorizontal() {
		return angHorizontal;
	}
	public final void setAngHorizontal(int angHorizontal) {
		this.angHorizontal = angHorizontal;
	}
	// --------------------------------------------------- METODOS --------------------------------------------
	
	@Override
	public void enviarReparacion() {
		Averia a = new Averia();
		this.getAverias().addFirst(a);
		this.setOperativo(false);
	}
	public void moverDer(int mov) {
		this.angHorizontal += mov;
	}
	public void moverIzq(int mov) {
		this.angHorizontal -= mov;
	}
	/**
	 * Aumenta la inclinación vertical en los grados marcados en el parámetro mov, siendo el punto más alto el punto 0 y el más bajo el 180.
	 * @param mov Desplazamiento vertical de la cámara en grados.
	 */
	public void subir(int mov) {
		if(this.angVertical+mov <= 0) {
			this.angVertical = 0;
			System.out.println("La cámara ya está en el punto más alto.");
		}else {
			this.angVertical -= mov;
		}
	}
	/**
	 * Reduce la inclinación vertical en los grados marcados en el parámetro mov, siendo el punto mas bajo el 180 y el más alto el 0.
	 * @param mov Grados de desplazamiento hacia abajo de la cámara.
	 */
	public void bajar(int mov) {
		if(this.angVertical>=180) {
			this.angVertical = 180;
			System.out.println("La cámara ya está en el punto más bajo");
		}else {
			this.angVertical += mov;
		}
	}
}
