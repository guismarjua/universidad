package cl.valposystems.sgi.to;

import java.io.Serializable;

public class EscalamientoTO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 4173288029152328338L;
	
	private Integer idEscalamiento; 
	private String nivel;
	private String nombre;
	private String telefono;
	private String correo;
	private Integer idActividad;
	
	public EscalamientoTO() {
		
	}

	public Integer getIdEscalamiento() {
		return idEscalamiento;
	}

	public void setIdEscalamiento(Integer idEscalamiento) {
		this.idEscalamiento = idEscalamiento;
	}

	public String getNivel() {
		return nivel;
	}

	public void setNivel(String nivel) {
		this.nivel = nivel;
	}

	public Integer getIdActividad() {
		return idActividad;
	}

	public void setIdActividad(Integer idActividad) {
		this.idActividad = idActividad;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getCorreo() {
		return correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}
	
	

}
