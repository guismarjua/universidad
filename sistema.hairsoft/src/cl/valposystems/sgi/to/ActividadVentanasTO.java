package cl.valposystems.sgi.to;

import java.io.Serializable;
import java.util.Date;

public class ActividadVentanasTO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 6750544709231521455L;

	private Integer idVentana;
	private Integer idActividad;
	private Integer idIto;
	private String ito;
	private Integer idCliente;
	private String cliente;
	private String nombreProyecto;
	private Long crq;
	private String detalleAct;
	private Integer nivel;
	private String sitio;
	private Date fechaInicio;
	private Date fechaTermino;
	private Date fechaCreacion;
	private String statusNoc;
	private Integer idObservaciones;
	private String observaciones;
	private Date fechaDesde;
	private Date fechaHasta;
	private Integer cantidadVentanaActv;
	private String annio;
	private String parametros;
	
	public ActividadVentanasTO() {
		
	}

	public Integer getIdVentana() {
		return idVentana;
	}

	public void setIdVentana(Integer idVentana) {
		this.idVentana = idVentana;
	}

	public Integer getIdActividad() {
		return idActividad;
	}

	public void setIdActividad(Integer idActividad) {
		this.idActividad = idActividad;
	}

	public String getIto() {
		return ito;
	}

	public void setIto(String ito) {
		this.ito = ito;
	}

	public String getCliente() {
		return cliente;
	}

	public void setCliente(String cliente) {
		this.cliente = cliente;
	}

	public String getNombreProyecto() {
		return nombreProyecto;
	}

	public void setNombreProyecto(String proyecto) {
		this.nombreProyecto = proyecto;
	}

	public Long getCrq() {
		return crq;
	}

	public void setCrq(Long crq) {
		this.crq = crq;
	}

	public String getDetalleAct() {
		return detalleAct;
	}

	public void setDetalleAct(String detalleAct) {
		this.detalleAct = detalleAct;
	}

	public Integer getNivel() {
		return nivel;
	}

	public void setNivel(Integer nivel) {
		this.nivel = nivel;
	}

	public String getSitio() {
		return sitio;
	}

	public void setSitio(String sitio) {
		this.sitio = sitio;
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

	public String getStatusNoc() {
		return statusNoc;
	}

	public void setStatusNoc(String statusNoc) {
		this.statusNoc = statusNoc;
	}

	public String getObservaciones() {
		return observaciones;
	}

	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}

	public Integer getIdIto() {
		return idIto;
	}

	public void setIdIto(Integer idIto) {
		this.idIto = idIto;
	}

	public Integer getIdCliente() {
		return idCliente;
	}

	public void setIdCliente(Integer idCliente) {
		this.idCliente = idCliente;
	}

	public Integer getIdObservaciones() {
		return idObservaciones;
	}

	public void setIdObservaciones(Integer idObservaciones) {
		this.idObservaciones = idObservaciones;
	}

	public Date getFechaDesde() {
		return fechaDesde;
	}

	public void setFechaDesde(Date fechaDesde) {
		this.fechaDesde = fechaDesde;
	}

	public Date getFechaHasta() {
		return fechaHasta;
	}

	public void setFechaHasta(Date fechaHasta) {
		this.fechaHasta = fechaHasta;
	}

		public Integer getCantidadVentanaActv() {
		return cantidadVentanaActv;
	}

	public void setCantidadVentanaActv(Integer cantidadVentanaActv) {
		this.cantidadVentanaActv = cantidadVentanaActv;
	}

	public Date getFechaCreacion() {
		return fechaCreacion;
	}

	public void setFechaCreacion(Date fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
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
