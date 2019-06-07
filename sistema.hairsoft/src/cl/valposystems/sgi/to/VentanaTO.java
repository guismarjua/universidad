package cl.valposystems.sgi.to;

import java.io.Serializable;
import java.util.Date;

import org.primefaces.model.DualListModel;

public class VentanaTO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 2278961364289108301L;

	private int idVentana;
	private Date fechaCreacion;
	private Date fechaModificacion;
	private String fecCre;
	private String fecMod;
	private int vigencia;
	private String usuario;
	private String listadoActividades;
	private int idUsuario;
	private DualListModel<ActividadTO> listaDualActividades = new DualListModel<ActividadTO>();
	
	public VentanaTO(){
		super();
	}
	
	public VentanaTO(int idVentana, Date fechaCreacion, Date fechaModificacion, int vigencia, String usuario, String listadoActividades){
		super();
		this.idVentana = idVentana;
		this.fechaCreacion = fechaCreacion;
		this.fechaModificacion = fechaModificacion;
		this.vigencia = vigencia;
		this.usuario = usuario;
		this.listadoActividades = listadoActividades;
	}

	public int getIdVentana() {
		return idVentana;
	}

	public void setIdVentana(int idVentana) {
		this.idVentana = idVentana;
	}

	public Date getFechaCreacion() {
		return fechaCreacion;
	}

	public void setFechaCreacion(Date fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}

	public Date getFechaModificacion() {
		return fechaModificacion;
	}

	public void setFechaModificacion(Date fechaModificacion) {
		this.fechaModificacion = fechaModificacion;
	}

	public int getVigencia() {
		return vigencia;
	}

	public void setVigencia(int vigencia) {
		this.vigencia = vigencia;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getListadoActividades() {
		return listadoActividades;
	}

	public void setListadoActividades(String listadoActividades) {
		this.listadoActividades = listadoActividades;
	}

	public DualListModel<ActividadTO> getListaDualActividades() {
		return listaDualActividades;
	}

	public void setListaDualActividades(DualListModel<ActividadTO> listaDualActividades) {
		this.listaDualActividades = listaDualActividades;
	}

	public String getFecCre() {
		return fecCre;
	}

	public void setFecCre(String fecCre) {
		this.fecCre = fecCre;
	}

	public String getFecMod() {
		return fecMod;
	}

	public void setFecMod(String fecMod) {
		this.fecMod = fecMod;
	}

	public int getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(int idUsuario) {
		this.idUsuario = idUsuario;
	}

	
	
}
