package cl.valposystems.sgi.to;

import java.io.Serializable;

public class StatusTO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 3837823005377105229L;
	
	private int idStatus;
	private String status;
	private int vigencia;
	
	public StatusTO() {
		
	}

	public int getIdStatus() {
		return idStatus;
	}

	public void setIdStatus(int idStatus) {
		this.idStatus = idStatus;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public int getVigencia() {
		return vigencia;
	}

	public void setVigencia(int vigencia) {
		this.vigencia = vigencia;
	}

}
