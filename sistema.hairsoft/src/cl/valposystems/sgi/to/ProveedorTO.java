package cl.valposystems.sgi.to;

import java.io.Serializable;

public class ProveedorTO implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 3393025082805046175L;
	private String nombreProveedor;
	private String empresaProveedor;
	private String cargoProveedor;
	private String fonoProveedor;
	private String correoProveedor;

	public ProveedorTO() {
		super();
	}

	public String getNombreProveedor() {
		return nombreProveedor;
	}

	public void setNombreProveedor(String nombreProveedor) {
		this.nombreProveedor = nombreProveedor;
	}

	public String getEmpresaProveedor() {
		return empresaProveedor;
	}

	public void setEmpresaProveedor(String empresaProveedor) {
		this.empresaProveedor = empresaProveedor;
	}

	public String getCargoProveedor() {
		return cargoProveedor;
	}

	public void setCargoProveedor(String cargoProveedor) {
		this.cargoProveedor = cargoProveedor;
	}

	public String getFonoProveedor() {
		return fonoProveedor;
	}

	public void setFonoProveedor(String fonoProveedor) {
		this.fonoProveedor = fonoProveedor;
	}

	public String getCorreoProveedor() {
		return correoProveedor;
	}

	public void setCorreoProveedor(String correoProveedor) {
		this.correoProveedor = correoProveedor;
	}
	
}
