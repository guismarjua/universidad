package cl.valposystems.sgi.to;

import java.io.Serializable;
public class RegionTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4776716582366911448L;
	private int idRegion;
	private String nombreRegion;
	private int numeroRegion;
	private int vigencia;
		
	public RegionTO() {
		super();
	}
	
	public RegionTO(int idRegion, String nombreRegion, int numeroRegion, int vigencia) {
		super();
		this.idRegion = idRegion;
		this.nombreRegion = nombreRegion;
		this.numeroRegion = numeroRegion;
		this.vigencia = vigencia;
	}
	public int getId() {
		return idRegion;
	}
	public void setId(int id) {
		this.idRegion = id;
	}
	public String getNombreRegion() {
		return nombreRegion;
	}
	public void setNombreRegion(String nombreRegion) {
		this.nombreRegion = nombreRegion;
	}
	public int getNumeroRegion() {
		return numeroRegion;
	}
	public void setNumeroRegion(int numeroRegion) {
		this.numeroRegion = numeroRegion;
	}
	public int getVigencia() {
		return vigencia;
	}
	public void setVigencia(int vigencia) {
		this.vigencia = vigencia;
	}

	public int getIdRegion() {
		return idRegion;
	}

	public void setIdRegion(int idRegion) {
		this.idRegion = idRegion;
	}
	
}
