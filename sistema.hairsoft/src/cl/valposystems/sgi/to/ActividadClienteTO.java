package cl.valposystems.sgi.to;

import java.io.Serializable;
import java.util.Date;

public class ActividadClienteTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3766821710824448215L;
	
	private String nombreCliente;
	private int cantActivClie;
	private Date fechaInicio;
	private Date fechaTermino;
	
	
	public ActividadClienteTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	public String getNombreCliente() {
		return nombreCliente;
	}
	public void setNombreCliente(String nombreCliente) {
		this.nombreCliente = nombreCliente;
	}
	public int getCantActivClie() {
		return cantActivClie;
	}
	public void setCantActivClie(int cantActivClie) {
		this.cantActivClie = cantActivClie;
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
