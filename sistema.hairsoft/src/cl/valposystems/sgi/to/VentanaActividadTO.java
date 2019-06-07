package cl.valposystems.sgi.to;

import java.io.Serializable;

public class VentanaActividadTO implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -597965956268226441L;
	private int idVentanaActividad;
	private int idVentana;
	private int idActividad;
	private int vigencia;
	private String actividad;
	
	public VentanaActividadTO() {
		super();
	}

	public int getIdVentanaActividad() {
		return idVentanaActividad;
	}

	public void setIdVentanaActividad(int idVentanaActividad) {
		this.idVentanaActividad = idVentanaActividad;
	}

	public int getIdVentana() {
		return idVentana;
	}

	public void setIdVentana(int idVentana) {
		this.idVentana = idVentana;
	}

	public int getIdActividad() {
		return idActividad;
	}

	public void setIdActividad(int idActividad) {
		this.idActividad = idActividad;
	}

	public int getVigencia() {
		return vigencia;
	}

	public void setVigencia(int vigencia) {
		this.vigencia = vigencia;
	}

	public String getActividad() {
		return actividad;
	}

	public void setActividad(String actividad) {
		this.actividad = actividad;
	}
	
}
