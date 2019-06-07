package cl.valposystems.sgi.to;

import java.io.Serializable;

public class StatusEspecificosTO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 3837823005377105229L;
	
	private int idStatusEspecificos;
	private String statusEspecificos;
	private int vigencia;
	private int idStatus;
	
	public StatusEspecificosTO() {
		
	}


	public int getIdStatusEspecificos() {
		return idStatusEspecificos;
	}




	public void setIdStatusEspecificos(int idStatusEspecificos) {
		this.idStatusEspecificos = idStatusEspecificos;
	}




	public String getStatusEspecificos() {
		return statusEspecificos;
	}




	public void setStatusEspecificos(String statusEspecificos) {
		this.statusEspecificos = statusEspecificos;
	}




	public int getIdStatus() {
		return idStatus;
	}

	public void setIdStatus(int idStatus) {
		this.idStatus = idStatus;
	}

	public int getVigencia() {
		return vigencia;
	}

	public void setVigencia(int vigencia) {
		this.vigencia = vigencia;
	}

}
