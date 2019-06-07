package cl.valposystems.sgi.to;

import java.io.Serializable;

public class ServicioTO implements Serializable{


	/**
	 * 
	 */
	private static final long serialVersionUID = 1404463881695102436L;
	private int idServicio;
	private String nombreServicio;
	private String descripServicio;
	private int monto;
	
	public ServicioTO() {
		super();
	}

	public int getIdServicio() {
		return idServicio;
	}

	public void setIdServicio(int idServicio) {
		this.idServicio = idServicio;
	}

	public String getNombreServicio() {
		return nombreServicio;
	}

	public void setNombreServicio(String nombreServicio) {
		this.nombreServicio = nombreServicio;
	}

	public String getDescripServicio() {
		return descripServicio;
	}

	public void setDescripServicio(String descripServicio) {
		this.descripServicio = descripServicio;
	}

	public int getMonto() {
		return monto;
	}

	public void setMonto(int monto) {
		this.monto = monto;
	}



	
}
