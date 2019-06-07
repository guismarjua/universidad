package cl.valposystems.sgi.to;

import java.io.Serializable;
import java.util.List;

public class ProfesionalTO implements Serializable{


	
	/**
	 * 
	 */
	private static final long serialVersionUID = 5977688785417556129L;

	private int idProfesional;
	private String nombreProfesional;
	private String rutProfesional;
	private int telefonoProfesional;
	private String profesionProfesional;
	
	private List<ServicioTO> iServicio;
	
	public ProfesionalTO() {
		super();
	}

	public int getIdProfesional() {
		return idProfesional;
	}

	public void setIdProfesional(int idProfesional) {
		this.idProfesional = idProfesional;
	}

	public String getNombreProfesional() {
		return nombreProfesional;
	}

	public void setNombreProfesional(String nombreProfesional) {
		this.nombreProfesional = nombreProfesional;
	}

	public String getRutProfesional() {
		return rutProfesional;
	}

	public void setRutProfesional(String rutProfesional) {
		this.rutProfesional = rutProfesional;
	}

	public int getTelefonoProfesional() {
		return telefonoProfesional;
	}

	public void setTelefonoProfesional(int telefonoProfesional) {
		this.telefonoProfesional = telefonoProfesional;
	}

	public String getProfesionProfesional() {
		return profesionProfesional;
	}

	public void setProfesionProfesional(String profesionProfesional) {
		this.profesionProfesional = profesionProfesional;
	}

	public List<ServicioTO> getiServicio() {
		return iServicio;
	}

	public void setiServicio(List<ServicioTO> iServicio) {
		this.iServicio = iServicio;
	}



	
}
