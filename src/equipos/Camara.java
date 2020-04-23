package equipos;

import java.util.LinkedList;
/**
 * Clase que hereda de Dispositivo, a�ade las funcionalidades para un dispositivo tipo C�mara, incluye el �ngulo vertical, �ngulo horizontal, y si es m�vil.
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
	 * Constructor completo, establece todos los valores seg�n los par�metros recibidos.
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
	 * Aumenta la inclinaci�n vertical en los grados marcados en el par�metro mov, siendo el punto m�s alto el punto 0 y el m�s bajo el 180.
	 * @param mov Desplazamiento vertical de la c�mara en grados.
	 */
	public void subir(int mov) {
		if(this.angVertical+mov <= 0) {
			this.angVertical = 0;
			System.out.println("La c�mara ya est� en el punto m�s alto.");
		}else {
			this.angVertical -= mov;
		}
	}
	/**
	 * Reduce la inclinaci�n vertical en los grados marcados en el par�metro mov, siendo el punto mas bajo el 180 y el m�s alto el 0.
	 * @param mov Grados de desplazamiento hacia abajo de la c�mara.
	 */
	public void bajar(int mov) {
		if(this.angVertical>=180) {
			this.angVertical = 180;
			System.out.println("La c�mara ya est� en el punto m�s bajo");
		}else {
			this.angVertical += mov;
		}
	}
}
