package cl.valposystems.sgi.to;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class ProyectoTO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -6827120716054801822L;
	private int idProyecto;
	private String nombreProyecto;
	private String resumenProyecto;
	private Date fechaInicio;
	private Date fechaTermino;
	private String nombreUsuario;
	private String apellidoUsuario;
	private String nombreSitio;
	private String sitioSinergia;
	private int vigencia;
	private int identUsuario;
	private int identSitio;
	private String fecIni;
	private String fecTerm;
	
	private List<UsuarioTO> usuarios;
	private int usuarioSelected;
	private List<SitioTO> sitios;
	private int sitioSelected;
	
	public ProyectoTO() {
		super();
	}
	
	public ProyectoTO(int idProyecto, String nombreProyecto, String resumenProyecto, Date fechaInicio,
			Date fechaTermino, String nombreUsuario, String nombreSitio, int vigencia, int identUsuario, int identSitio, int usuarioSelected, int sitioSelected) {
		super();
		this.idProyecto = idProyecto;
		this.nombreProyecto = nombreProyecto;
		this.resumenProyecto = resumenProyecto;
		this.fechaInicio = fechaInicio;
		this.fechaTermino = fechaTermino;
		this.nombreUsuario = nombreUsuario;
		this.nombreSitio = nombreSitio;
		this.vigencia = vigencia;
		this.identUsuario = identUsuario;
		this.identSitio = identSitio;
		this.usuarioSelected = usuarioSelected;
		this.sitioSelected = sitioSelected;
	}




	public int getIdProyecto() {
		return idProyecto;
	}

	public void setIdProyecto(int idProyecto) {
		this.idProyecto = idProyecto;
	}

	public String getNombreProyecto() {
		return nombreProyecto;
	}

	public void setNombreProyecto(String nombreProyecto) {
		this.nombreProyecto = nombreProyecto;
	}

	public String getResumenProyecto() {
		return resumenProyecto;
	}

	public void setResumenProyecto(String resumenProyecto) {
		this.resumenProyecto = resumenProyecto;
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

	public String getNombreUsuario() {
		return nombreUsuario;
	}

	public void setNombreUsuario(String nombreUsuario) {
		this.nombreUsuario = nombreUsuario;
	}

	public String getNombreSitio() {
		return nombreSitio;
	}

	public void setNombreSitio(String nombreSitio) {
		this.nombreSitio = nombreSitio;
	}

	public int getVigencia() {
		return vigencia;
	}

	public void setVigencia(int vigencia) {
		this.vigencia = vigencia;
	}


	public int getIdentUsuario() {
		return identUsuario;
	}


	public void setIdentUsuario(int identUsuario) {
		this.identUsuario = identUsuario;
	}


	public int getIdentSitio() {
		return identSitio;
	}


	public void setIdentSitio(int identSitio) {
		this.identSitio = identSitio;
	}


	public List<UsuarioTO> getUsuarios() {
		return usuarios;
	}


	public void setUsuarios(List<UsuarioTO> usuarios) {
		this.usuarios = usuarios;
	}


	public int getUsuarioSelected() {
		return usuarioSelected;
	}


	public void setUsuarioSelected(int usuarioSelected) {
		this.usuarioSelected = usuarioSelected;
	}


	public List<SitioTO> getSitios() {
		return sitios;
	}


	public void setSitios(List<SitioTO> sitios) {
		this.sitios = sitios;
	}


	public int getSitioSelected() {
		return sitioSelected;
	}


	public void setSitioSelected(int sitioSelected) {
		this.sitioSelected = sitioSelected;
	}

	public String getApellidoUsuario() {
		return apellidoUsuario;
	}

	public void setApellidoUsuario(String apellidoUsuario) {
		this.apellidoUsuario = apellidoUsuario;
	}

	public String getFecIni() {
		return fecIni;
	}

	public void setFecIni(String fecIni) {
		this.fecIni = fecIni;
	}

	public String getFecTerm() {
		return fecTerm;
	}

	public void setFecTerm(String fecTerm) {
		this.fecTerm = fecTerm;
	}

	public String getSitioSinergia() {
		return sitioSinergia;
	}

	public void setSitioSinergia(String sitioSinergia) {
		this.sitioSinergia = sitioSinergia;
	}
	
	

}
