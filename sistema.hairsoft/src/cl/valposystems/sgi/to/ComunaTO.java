package cl.valposystems.sgi.to;

import java.io.Serializable;

public class ComunaTO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 5878231989611144649L;

	private int idComuna;
	private String nombreComuna;
	private int idRegion;
	private int vigencia;
	
	public ComunaTO() {
		super();
	}
	
	public ComunaTO(int idComuna, String nombreComuna, int idRegion, int vigencia) {
		super();
		this.idComuna = idComuna;
		this.nombreComuna = nombreComuna;
		this.idRegion = idRegion;
		this.vigencia = vigencia;
	}
	public int getIdComuna() {
		return idComuna;
	}
	public void setIdComuna(int idComuna) {
		this.idComuna = idComuna;
	}
	public String getNombreComuna() {
		return nombreComuna;
	}
	public void setNombreComuna(String nombreComuna) {
		this.nombreComuna = nombreComuna;
	}
	public int getIdRegion() {
		return idRegion;
	}
	public void setIdRegion(int idRegion) {
		this.idRegion = idRegion;
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
