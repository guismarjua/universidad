package cl.valposystems.sgi.to;

import java.io.Serializable;
import java.util.Date;

public class ActividadItoTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3372338119279789952L;
	
	private String nombreIto;
	private int cantActivIto;
	private Date fechaInicio;
	private Date fechaTermino;
	
	
	public ActividadItoTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	public String getNombreIto() {
		return nombreIto;
	}
	public void setNombreIto(String nombreIto) {
		this.nombreIto = nombreIto;
	}
	public int getCantActivIto() {
		return cantActivIto;
	}
	public void setCantActivIto(int cantActivIto) {
		this.cantActivIto = cantActivIto;
	}
	public Date getFechaInicio() {
		return fechaInicio;
	}
	public void setFechaInicio(Date fechaInicio) {
		this.fechaInicio = fechaInicio;
	}
	public Date getFechaTermino() {
		return fechaTermino;
	}
	public void setFechaTermino(Date fechaTermino) {
		this.fechaTermino = fechaTermino;
	}
	
	
	
}
