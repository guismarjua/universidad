package cl.valposystems.sgi.to;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class ActividadTO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer idActividad;
	private Long crqActividad;
	private Long idAct;
	private String sintesisActividad;
	private Integer impactoActividad;
	private Date fechaInicio;
	private Date fechaTermino;
	private String estadoInicio;
	private String estadoTermino;
	private String operadorInicio;
	private String operadorTermino;
	private String observacionActividad;
	private Integer idStatus;
	private Integer idStatusEspecifico;
	private Integer idProyecto;
	private Integer idSitio;
	private Integer idUsuario;
	private Integer idSupervision;
	private String nombreProyecto;
	private String fechaCreacion;
	private String fechaModificacion;
	private int cantidadActv;
	
	private int proyectoSelected;
	private List<ProyectoTO> proyectos;
	
	private int supervisionSelected;
	private List<SupervisionTO> supervisiones;
	
	private int sitioSelected;
	private List<SitioTO> sitios;
	
	private int statusSelected;
	private int statusSelectedEspecifico;
	private List<StatusTO> status;
	
	private List<StatusEspecificosTO> statusEspecificos;
	
	private int usuarioPMSelected;
	private List<UsuarioTO> usuariosPM;
	private UsuarioTO dataUsuario;
	
	private int usuarioSupervisorSelected;
	private List<UsuarioTO> usuariosSupervisor;
	private UsuarioTO dataUsuarioSuperv;
	
	public EscalamientoTO escalamiento1;
	public EscalamientoTO escalamiento2;
	public EscalamientoTO escalamiento3;
	
	private Integer usuarioEscalamiento1;
	private Integer usuarioEscalamiento2;
	private Integer usuarioEscalamiento3;
	private List<UsuarioTO> usuariosEscalamiento;
	
	private UsuarioTO dataUsuarioEsc1;
	private UsuarioTO dataUsuarioEsc2;
	private UsuarioTO dataUsuarioEsc3;
	
	private String nombreProveedor;
	private String empresaProveedor;
	private String cargoProveedor;
	private String fonoProveedor;
	private String correoProveedor;

	private String annio;
	private String parametros;
	
	private List<ProveedorTO> proveedores;
	
	private List<AdjuntoTO> adjuntos; 
	
	//
	private String nombreSitio;
	private String statusActividad;
	private String supervisionActividad;
	private String detalleTareas;
	
	public ActividadTO() {
		
	}

	public Integer getIdActividad() {
		return idActividad;
	}

	public void setIdActividad(Integer idActividad) {
		this.idActividad = idActividad;
	}

	public Long getCrqActividad() {
		return crqActividad;
	}

	public void setCrqActividad(Long crqActividad) {
		this.crqActividad = crqActividad;
	}

	public Long getIdAct() {
		return idAct;
	}

	public void setIdAct(Long idAct) {
		this.idAct = idAct;
	}

	public Integer getImpactoActividad() {
		return impactoActividad;
	}

	public void setImpactoActividad(Integer impactoActividad) {
		this.impactoActividad = impactoActividad;
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

	public String getEstadoInicio() {
		return estadoInicio;
	}

	public void setEstadoInicio(String estadoInicio) {
		this.estadoInicio = estadoInicio;
	}

	public String getEstadoTermino() {
		return estadoTermino;
	}

	public void setEstadoTermino(String estadoTermino) {
		this.estadoTermino = estadoTermino;
	}

	public String getOperadorInicio() {
		return operadorInicio;
	}

	public void setOperadorInicio(String operadorInicio) {
		this.operadorInicio = operadorInicio;
	}

	public String getOperadorTermino() {
		return operadorTermino;
	}

	public void setOperadorTermino(String operadorTermino) {
		this.operadorTermino = operadorTermino;
	}

	public String getObservacionActividad() {
		return observacionActividad;
	}

	public void setObservacionActividad(String observacionActividad) {
		this.observacionActividad = observacionActividad;
	}

	public Integer getIdStatus() {
		return idStatus;
	}

	public void setIdStatus(Integer idStatus) {
		this.idStatus = idStatus;
	}

	public Integer getIdProyecto() {
		return idProyecto;
	}

	public void setIdProyecto(Integer idProyecto) {
		this.idProyecto = idProyecto;
	}

	public Integer getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(Integer idUsuario) {
		this.idUsuario = idUsuario;
	}

	public Integer getIdSupervision() {
		return idSupervision;
	}

	public void setIdSupervision(Integer idSupervision) {
		this.idSupervision = idSupervision;
	}

	public String getNombreProyecto() {
		return nombreProyecto;
	}

	public void setNombreProyecto(String nombreProyecto) {
		this.nombreProyecto = nombreProyecto;
	}

	public int getProyectoSelected() {
		return proyectoSelected;
	}

	public void setProyectoSelected(int proyectoSelected) {
		this.proyectoSelected = proyectoSelected;
	}

	public List<ProyectoTO> getProyectos() {
		return proyectos;
	}

	public void setProyectos(List<ProyectoTO> proyectos) {
		this.proyectos = proyectos;
	}

	public String getSintesisActividad() {
		return sintesisActividad;
	}

	public void setSintesisActividad(String sintesisActividad) {
		this.sintesisActividad = sintesisActividad;
	}

	public int getSupervisionSelected() {
		return supervisionSelected;
	}

	public void setSupervisionSelected(int supervisionSelected) {
		this.supervisionSelected = supervisionSelected;
	}

	public List<SupervisionTO> getSupervisiones() {
		return supervisiones;
	}

	public void setSupervisiones(List<SupervisionTO> supervisiones) {
		this.supervisiones = supervisiones;
	}

	public int getSitioSelected() {
		return sitioSelected;
	}

	public void setSitioSelected(int sitioSelected) {
		this.sitioSelected = sitioSelected;
	}

	public List<SitioTO> getSitios() {
		return sitios;
	}

	public void setSitios(List<SitioTO> sitios) {
		this.sitios = sitios;
	}

	public int getStatusSelected() {
		return statusSelected;
	}

	public void setStatusSelected(int statusSelected) {
		this.statusSelected = statusSelected;
	}

	public List<StatusTO> getStatus() {
		return status;
	}

	public void setStatus(List<StatusTO> status) {
		this.status = status;
	}

	

	public int getUsuarioPMSelected() {
		return usuarioPMSelected;
	}

	public void setUsuarioPMSelected(int usuarioPMSelected) {
		this.usuarioPMSelected = usuarioPMSelected;
	}

	public List<UsuarioTO> getUsuariosPM() {
		return usuariosPM;
	}

	public void setUsuariosPM(List<UsuarioTO> usuariosPM) {
		this.usuariosPM = usuariosPM;
	}

	public int getUsuarioSupervisorSelected() {
		return usuarioSupervisorSelected;
	}

	public void setUsuarioSupervisorSelected(int usuarioSupervisorSelected) {
		this.usuarioSupervisorSelected = usuarioSupervisorSelected;
	}

	public List<UsuarioTO> getUsuariosSupervisor() {
		return usuariosSupervisor;
	}

	public void setUsuariosSupervisor(List<UsuarioTO> usuariosSupervisor) {
		this.usuariosSupervisor = usuariosSupervisor;
	}

	public EscalamientoTO getEscalamiento1() {
		return escalamiento1;
	}

	public void setEscalamiento1(EscalamientoTO escalamiento1) {
		this.escalamiento1 = escalamiento1;
	}

	public EscalamientoTO getEscalamiento2() {
		return escalamiento2;
	}

	public void setEscalamiento2(EscalamientoTO escalamiento2) {
		this.escalamiento2 = escalamiento2;
	}

	public EscalamientoTO getEscalamiento3() {
		return escalamiento3;
	}

	public void setEscalamiento3(EscalamientoTO escalamiento3) {
		this.escalamiento3 = escalamiento3;
	}

	public List<UsuarioTO> getUsuariosEscalamiento() {
		return usuariosEscalamiento;
	}

	public void setUsuariosEscalamiento(List<UsuarioTO> usuariosEscalamiento) {
		this.usuariosEscalamiento = usuariosEscalamiento;
	}

	public Integer getUsuarioEscalamiento1() {
		return usuarioEscalamiento1;
	}

	public void setUsuarioEscalamiento1(Integer usuarioEscalamiento1) {
		this.usuarioEscalamiento1 = usuarioEscalamiento1;
	}

	public Integer getUsuarioEscalamiento2() {
		return usuarioEscalamiento2;
	}

	public void setUsuarioEscalamiento2(Integer usuarioEscalamiento2) {
		this.usuarioEscalamiento2 = usuarioEscalamiento2;
	}

	public Integer getUsuarioEscalamiento3() {
		return usuarioEscalamiento3;
	}

	public void setUsuarioEscalamiento3(Integer usuarioEscalamiento3) {
		this.usuarioEscalamiento3 = usuarioEscalamiento3;
	}

	public List<ProveedorTO> getProveedores() {
		return proveedores;
	}

	public void setProveedores(List<ProveedorTO> proveedores) {
		this.proveedores = proveedores;
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

	public String getFechaCreacion() {
		return fechaCreacion;
	}

	public void setFechaCreacion(String fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}

	public String getFechaModificacion() {
		return fechaModificacion;
	}

	public void setFechaModificacion(String fechaModificacion) {
		this.fechaModificacion = fechaModificacion;
	}

	public List<AdjuntoTO> getAdjuntos() {
		return adjuntos;
	}

	public void setAdjuntos(List<AdjuntoTO> adjuntos) {
		this.adjuntos = adjuntos;
	}

	public UsuarioTO getDataUsuario() {
		return dataUsuario;
	}

	public void setDataUsuario(UsuarioTO dataUsuario) {
		this.dataUsuario = dataUsuario;
	}

	public Integer getIdSitio() {
		return idSitio;
	}

	public void setIdSitio(Integer idSitio) {
		this.idSitio = idSitio;
	}


	public int getStatusSelectedEspecifico() {
		return statusSelectedEspecifico;
	}

	public void setStatusSelectedEspecifico(int statusSelectedEspecifico) {
		this.statusSelectedEspecifico = statusSelectedEspecifico;
	}

	public List<StatusEspecificosTO> getStatusEspecificos() {
		return statusEspecificos;
	}

	public void setStatusEspecificos(List<StatusEspecificosTO> statusEspecificos) {
		this.statusEspecificos = statusEspecificos;
	}

	public Integer getIdStatusEspecifico() {
		return idStatusEspecifico;
	}

	public void setIdStatusEspecifico(Integer idStatusEspecifico) {
		this.idStatusEspecifico = idStatusEspecifico;
	}

	public UsuarioTO getDataUsuarioSuperv() {
		return dataUsuarioSuperv;
	}

	public void setDataUsuarioSuperv(UsuarioTO dataUsuarioSuperv) {
		this.dataUsuarioSuperv = dataUsuarioSuperv;
	}

	public UsuarioTO getDataUsuarioEsc1() {
		return dataUsuarioEsc1;
	}

	public void setDataUsuarioEsc1(UsuarioTO dataUsuarioEsc1) {
		this.dataUsuarioEsc1 = dataUsuarioEsc1;
	}

	public UsuarioTO getDataUsuarioEsc2() {
		return dataUsuarioEsc2;
	}

	public void setDataUsuarioEsc2(UsuarioTO dataUsuarioEsc2) {
		this.dataUsuarioEsc2 = dataUsuarioEsc2;
	}

	public UsuarioTO getDataUsuarioEsc3() {
		return dataUsuarioEsc3;
	}

	public void setDataUsuarioEsc3(UsuarioTO dataUsuarioEsc3) {
		this.dataUsuarioEsc3 = dataUsuarioEsc3;
	}

	public int getCantidadActv() {
		return cantidadActv;
	}

	public void setCantidadActv(int cantidadActv) {
		this.cantidadActv = cantidadActv;
	}

	public String getNombreSitio() {
		return nombreSitio;
	}

	public void setNombreSitio(String nombreSitio) {
		this.nombreSitio = nombreSitio;
	}

	public String getStatusActividad() {
		return statusActividad;
	}

	public void setStatusActividad(String statusActividad) {
		this.statusActividad = statusActividad;
	}

	public String getSupervisionActividad() {
		return supervisionActividad;
	}

	public void setSupervisionActividad(String supervisionActividad) {
		this.supervisionActividad = supervisionActividad;
	}

	public String getDetalleTareas() {
		return detalleTareas;
	}

	public void setDetalleTareas(String detalleTareas) {
		this.detalleTareas = detalleTareas;
	}

	public String getAnnio() {
		return annio;
	}

	public void setAnnio(String annio) {
		this.annio = annio;
	}

	public String getParametros() {
		return parametros;
	}

	public void setParametros(String parametros) {
		this.parametros = parametros;
	}
	
}
