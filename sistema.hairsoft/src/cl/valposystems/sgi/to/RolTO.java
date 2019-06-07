package cl.valposystems.sgi.to;

import java.io.Serializable;

public class RolTO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -1604833234781723275L;
	private int rolId;
	private String rol;
	private int vigencia;
	
	public RolTO() {
		super();
	}
	
	public RolTO(int rolId, String rol, int vigencia) {
		super();
		this.rolId = rolId;
		this.rol = rol;
		this.vigencia = vigencia;
	}

	public int getRolId() {
		return rolId;
	}

	public void setRolId(int rolId) {
		this.rolId = rolId;
	}

	public String getRol() {
		return rol;
	}

	public void setRol(String rol) {
		this.rol = rol;
	}

	public int getVigencia() {
		return vigencia;
	}

	public void setVigencia(int vigencia) {
		this.vigencia = vigencia;
	}

}
