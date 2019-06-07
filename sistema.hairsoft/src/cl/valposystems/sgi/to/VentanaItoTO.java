package cl.valposystems.sgi.to;

import java.io.Serializable;
import java.util.Date;

public class VentanaItoTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8332104984969129527L;
	
	private String nombreUsuario;
	private int idVentana;
	private int idActividad;
	private int idProyecto;
	private int vigencia;
	private String actividad;
	private int cantidadVentana;
	private Date fechaInicio;
	private Date fechaTermino;
	
	
	
	public VentanaItoTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	public VentanaItoTO(String nombreUsuario, int idVentana, int idActividad, int idProyecto, int vigencia,
			String actividad, int cantidadVentana) {
		super();
		this.nombreUsuario = nombreUsuario;
		this.idVentana = idVentana;
		this.idActividad = idActividad;
		this.idProyecto = idProyecto;
		this.vigencia = vigencia;
		this.actividad = actividad;
		this.cantidadVentana = cantidadVentana;
	}
	public String getNombreUsuario() {
		return nombreUsuario;
	}
	public void setNombreUsuario(String nombreUsuario) {
		this.nombreUsuario = nombreUsuario;
	}
	public int getIdVentana() {
		return idVentana;
	}
	public void setIdVentana(int idVentana) {
		this.idVentana = idVentana;
	}
	public int getIdActividad() {
		return idActividad;
	}
	public void setIdActividad(int idActividad) {
		this.idActividad = idActividad;
	}
	public int getIdProyecto() {
		return idProyecto;
	}
	public void setIdProyecto(int idProyecto) {
		this.idProyecto = idProyecto;
	}
	public int getVigencia() {
		return vigencia;
	}
	public void setVigencia(int vigencia) {
		this.vigencia = vigencia;
	}
	public String getActividad() {
		return actividad;
	}
	public void setActividad(String actividad) {
		this.actividad = actividad;
	}
	public int getCantidadVentana() {
		return cantidadVentana;
	}
	public void setCantidadVentana(int cantidadVentana) {
		this.cantidadVentana = cantidadVentana;
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
