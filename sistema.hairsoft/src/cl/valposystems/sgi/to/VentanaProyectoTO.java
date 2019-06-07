package cl.valposystems.sgi.to;

import java.io.Serializable;
import java.util.Date;

public class VentanaProyectoTO implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 2114328997820694929L;
	
	private String nombreProyecto;
	private int cantidadVentana;
	private Date fechaBusquedaIni;
	private Date fechaBusquedaFin;
	
	
	public VentanaProyectoTO() {
		super();
	}
	
	public String getNombreProyecto() {
		return nombreProyecto;
	}
	public void setNombreProyecto(String nombreProyecto) {
		this.nombreProyecto = nombreProyecto;
	}
	public int getCantidadVentana() {
		return cantidadVentana;
	}
	public void setCantidadVentana(int cantidadVentana) {
		this.cantidadVentana = cantidadVentana;
	}

	public Date getFechaBusquedaIni() {
		return fechaBusquedaIni;
	}

	public void setFechaBusquedaIni(Date fechaBusquedaIni) {
		this.fechaBusquedaIni = fechaBusquedaIni;
	}

	public Date getFechaBusquedaFin() {
		return fechaBusquedaFin;
	}

	public void setFechaBusquedaFin(Date fechaBusquedaFin) {
		this.fechaBusquedaFin = fechaBusquedaFin;
	}

	
	
}
