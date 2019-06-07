package cl.valposystems.sgi.to;

import java.io.Serializable;

public class SupervisionTO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -9162126419546860173L;
	
	private Integer idSupervision;
	private String supervision;
	private int vigencia;

	public SupervisionTO() {
		super();
	}

	public Integer getIdSupervision() {
		return idSupervision;
	}

	public void setIdSupervision(Integer idSupervision) {
		this.idSupervision = idSupervision;
	}

	public String getSupervision() {
		return supervision;
	}

	public void setSupervision(String supervision) {
		this.supervision = supervision;
	}

	public int getVigencia() {
		return vigencia;
	}

	public void setVigencia(int vigencia) {
		this.vigencia = vigencia;
	}
	
	
}
