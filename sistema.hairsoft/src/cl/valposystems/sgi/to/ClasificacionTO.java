package cl.valposystems.sgi.to;

import java.io.Serializable;

public class ClasificacionTO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -5091521054633141031L;
	
	private int idClasificacion;
	private String clasificacion;
	private int vigencia;
	
	public ClasificacionTO(int idClasificacion, String clasificacion, int vigencia) {
		super();
		this.idClasificacion = idClasificacion;
		this.clasificacion = clasificacion;
		this.vigencia = vigencia;
	}
	
	public int getIdClasificacion() {
		return idClasificacion;
	}
	public void setIdClasificacion(int idClasificacion) {
		this.idClasificacion = idClasificacion;
	}
	public String getClasificacion() {
		return clasificacion;
	}
	public void setClasificacion(String clasificacion) {
		this.clasificacion = clasificacion;
	}
	public int getVigencia() {
		return vigencia;
	}
	public void setVigencia(int vigencia) {
		this.vigencia = vigencia;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	

}
