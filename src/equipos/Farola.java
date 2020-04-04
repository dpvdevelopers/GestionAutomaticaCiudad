package equipos;

import java.util.LinkedList;

public class Farola extends Dispositivo {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -9035793492944284776L;
	// ------------------------------------------------ Atributos -----------------------------------------------------
	private int potencia;
	private String tipoLampara;
	// ------------------------------------------------ Constructor ---------------------------------------------------
	public Farola(double precio, String descripcion, String coordenadas, String fabricante,
			String horaEnc, String horaApag, boolean operativo, LinkedList<Averia> averias, int potencia, String tipoLampara) {
		super(precio, descripcion, coordenadas, fabricante, horaEnc, horaApag, operativo, averias);
		this.potencia = potencia;
		this.tipoLampara = tipoLampara;
	}
	public Farola() {
		super();
		this.potencia = 220;
		this.tipoLampara = "normal";
	}
	public Farola(Farola f) {
		super(f);
		this.potencia = f.potencia;
		this.tipoLampara = f.tipoLampara;
	}
	// ----------------------------------------------- getters and setters ----------------------------------------------
	public final int getPotencia() {
		return potencia;
	}
	public final void setPotencia(int potencia) {
		this.potencia = potencia;
	}
	public final String getTipoLampara() {
		return tipoLampara;
	}
	public final void setTipoLampara(String tipoLampara) {
		this.tipoLampara = tipoLampara;
	}
	// ------------------------------------------------ toString ---------------------------------------------------------
	
	@Override
	public String toString() {
		return "Farola [potencia=" + potencia + ", tipoLampara=" + tipoLampara + "]";
	}
	@Override
	public void enviarReparacion() {
		Averia a = new Averia();
		this.getAverias().addFirst(a);
		this.setOperativo(false);
	}

}
