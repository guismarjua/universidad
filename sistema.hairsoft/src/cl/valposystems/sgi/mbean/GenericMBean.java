package cl.valposystems.sgi.mbean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;

import cl.valposystems.sgi.business.sbean.GenericSessionBean;
import cl.valposystems.sgi.exception.ServicioNoDisponibleException;
import cl.valposystems.sgi.to.AdjuntoTO;
import cl.valposystems.sgi.to.ClasificacionTO;
import cl.valposystems.sgi.to.ComunaTO;
import cl.valposystems.sgi.to.EscalamientoTO;
import cl.valposystems.sgi.to.ProveedorTO;
import cl.valposystems.sgi.to.ProyectoTO;
import cl.valposystems.sgi.to.RegionTO;
import cl.valposystems.sgi.to.RolTO;
import cl.valposystems.sgi.to.SitioTO;
import cl.valposystems.sgi.to.StatusEspecificosTO;
import cl.valposystems.sgi.to.StatusTO;
import cl.valposystems.sgi.to.SupervisionTO;
import cl.valposystems.sgi.to.UsuarioTO;


@Named
@SessionScoped
public class GenericMBean implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -1393290490460026295L;
	
	@EJB GenericSessionBean servicio;
	
	private RolTO rol;
	private SitioTO sitio;
	private UsuarioTO usuario;
	
	@PostConstruct
	public void init() {
		setRol(new RolTO());
		setSitio(new SitioTO());
		setUsuario(new UsuarioTO());
	}
	
	/**
	 * Se obtienen el listado de los roles existentes
	 */
	public List<RolTO> obtenerRoles() {
		FacesContext context = FacesContext.getCurrentInstance();
		List<RolTO> result = new ArrayList<RolTO>();
		try {
			result = servicio.findAllRoles();
		}catch(ServicioNoDisponibleException e) {
			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "No logra obtener a los sostenedores."));
		}
		return result;
	}
	
	/**
	 * Se obtienen el listado de las regiones existentes
	 */
	public List<RegionTO> obtenerRegiones() {
		FacesContext context = FacesContext.getCurrentInstance();
		List<RegionTO> result = new ArrayList<RegionTO>();
		try {
			result = servicio.findAllRegiones();
		}catch(ServicioNoDisponibleException e) {
			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "No logra obtener las Regiones."));
		}
		return result;
	}
	
	/**
	 * Se obtienen el listado de las comunas existentes
	 */
	public List<ComunaTO> obtenerComunas(Integer id) {
		FacesContext context = FacesContext.getCurrentInstance();
		List<ComunaTO> result = new ArrayList<ComunaTO>();
		try {
			result = servicio.findAllComunas(id);
		}catch(ServicioNoDisponibleException e) {
			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "No logra obtener las Comunas."));
		}
		return result;
	}
	
	/**
	 * Se obtienen el listado de las comunas existentes
	 */
	public List<ComunaTO> precargarComunas(Integer id) {
		FacesContext context = FacesContext.getCurrentInstance();
		List<ComunaTO> result = new ArrayList<ComunaTO>();
		try {
			result = servicio.preloadComunas(id);
		}catch(ServicioNoDisponibleException e) {
			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "No logra obtener las Comunas."));
		}
		return result;
	}
	
	/**
	 * Se obtienen el listado de las Clasificaciones existentes
	 */
	public List<ClasificacionTO> obtenerClasificaciones() {
		FacesContext context = FacesContext.getCurrentInstance();
		List<ClasificacionTO> result = new ArrayList<ClasificacionTO>();
		try {
			result = servicio.findAllClasificaciones();
		}catch(ServicioNoDisponibleException e) {
			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "No logra obtener las Clasificaciones."));
		}
		return result;
	}
	
	/**
	 * Se obtienen el listado de los usuarios existentes
	 */
	public List<UsuarioTO> obtenerUsuarios() {
		FacesContext context = FacesContext.getCurrentInstance();
		List<UsuarioTO> result = new ArrayList<UsuarioTO>();
		try {
			result = servicio.findAllUsuarios();
		}catch(ServicioNoDisponibleException e) {
			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "No logra obtener los usuarios."));
		}
		return result;
	}
	
	/**
	 * Se obtienen el listado de las Sitio existentes
	 */
	public List<SitioTO> obtenerSitio() {
		FacesContext context = FacesContext.getCurrentInstance();
		List<SitioTO> result = new ArrayList<SitioTO>();
		try {
			result = servicio.findAllSitios();
		}catch(ServicioNoDisponibleException e) {
			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "No logra obtener los sitios."));
		}
		return result;
	}
	
	/**
	 * Se obtienen el listado de las Sitio existentes
	 */
	public List<SitioTO> obtenerSitioModificar(Integer idSitio) {
		FacesContext context = FacesContext.getCurrentInstance();
		List<SitioTO> result = new ArrayList<SitioTO>();
		try {
			result = servicio.findAllSitios();
			SitioTO to = servicio.findSitioActividad(idSitio);
			if(to != null) {
				result.add(to);
			}
		}catch(ServicioNoDisponibleException e) {
			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "No logra obtener los sitios."));
		}
		return result;
	}
	
	/**
	 * Se obtienen el listado de los proyectos existentes
	 */
	
	public List<ProyectoTO> obtenerProyecto(){
		FacesContext context = FacesContext.getCurrentInstance();
		List<ProyectoTO> result = new ArrayList<ProyectoTO>();
		try {
			result = servicio.findAllProyectos();
		}catch(ServicioNoDisponibleException e) {
			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "No logra obtener los sitios."));
		}
		return result;
	}
	
	/**
	 * Se obtienen el listado de los proyectos existentes
	 */
	
	public List<ProyectoTO> obtenerProyectoModificar(Integer idProyecto){
		FacesContext context = FacesContext.getCurrentInstance();
		List<ProyectoTO> result = new ArrayList<ProyectoTO>();
		try {
			result = servicio.findAllProyectos();
			ProyectoTO to = servicio.findProyectosActividad(idProyecto);
			if(to != null) {
				result.add(to);
			}
		}catch(ServicioNoDisponibleException e) {
			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "No logra obtener los sitios."));
		}
		return result;
	}
	
	/**
	 * Se obtienen el listado de los proyectos existentes
	 */
	
	public List<SupervisionTO> obtenerSupervisiones(){
		FacesContext context = FacesContext.getCurrentInstance();
		List<SupervisionTO> result = new ArrayList<SupervisionTO>();
		try {
			result = servicio.findAllSupervisiones();
		}catch(ServicioNoDisponibleException e) {
			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "No logra obtener los sitios."));
		}
		return result;
	}
	
	/**
	 * Se obtienen el listado de los proyectos existentes
	 */
	
	public List<StatusTO> obtenerStatus(){
		FacesContext context = FacesContext.getCurrentInstance();
		List<StatusTO> result = new ArrayList<StatusTO>();
		try {
			result = servicio.findAllStatus();
		}catch(ServicioNoDisponibleException e) {
			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "No logra obtener los sitios."));
		}
		return result;
	}
	
	/**
	 * Se obtiene el listado de Status Especificos
	 */
	
	public List<StatusEspecificosTO> obtenerStatusEspecifico(int valor){
		FacesContext context = FacesContext.getCurrentInstance();
		List<StatusEspecificosTO> result = new ArrayList<StatusEspecificosTO>();
		try {
			result = servicio.findAllStatusEspecificos(valor);
		}catch(ServicioNoDisponibleException e) {
			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "No logra obtener los status especificos."));
		}
		return result;
	}
	
	/**
	 * Se obtienen el listado de los proyectos existentes
	 */
	
	public List<UsuarioTO> obtenerUsuariosEscalamiento(){
		FacesContext context = FacesContext.getCurrentInstance();
		List<UsuarioTO> result = new ArrayList<UsuarioTO>();
		try {
			result = servicio.findUsuariosEscalamiento();
		}catch(ServicioNoDisponibleException e) {
			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "No logra obtener los sitios."));
		}
		return result;
	}
	
	/**
	 * Se obtienen el listado de los usuarios candidatos para escalamiento
	 */
	
	public List<UsuarioTO> obtenerClientes(){
		FacesContext context = FacesContext.getCurrentInstance();
		List<UsuarioTO> result = new ArrayList<UsuarioTO>();
		try {
			result = servicio.findAllClientes();
		}catch(ServicioNoDisponibleException e) {
			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "No logra obtener los sitios."));
		}
		return result;
	}
	
	/**
	 * Se obtienen el listado de los usuarios candidatos para escalamiento
	 */
	
	public List<UsuarioTO> obtenerClientesModificar(Integer idUsuario){
		FacesContext context = FacesContext.getCurrentInstance();
		List<UsuarioTO> result = new ArrayList<UsuarioTO>();
		try {
			result = servicio.findAllClientes();
			UsuarioTO to = servicio.findClienteActividad(idUsuario);
			if(to != null) {
				result.add(to);
			}
		}catch(ServicioNoDisponibleException e) {
			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "No logra obtener los sitios."));
		}
		return result;
	}
	
	/**
	 * Se obtienen el listado de los usuarios candidatos para escalamiento
	 */
	
	public UsuarioTO obtenerDataUsuario(Integer idUsuario){
		FacesContext context = FacesContext.getCurrentInstance();
		UsuarioTO result = new UsuarioTO();
		try {
			result = servicio.findDataClientes(idUsuario);
		}catch(ServicioNoDisponibleException e) {
			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "No logra obtener los sitios."));
		}
		return result;
	}
	
	public UsuarioTO obtenerDataUsuarioSuperv(Integer idUsuario){
		FacesContext context = FacesContext.getCurrentInstance();
		UsuarioTO result = new UsuarioTO();
		try {
			result = servicio.findDataClientes(idUsuario);
		}catch(ServicioNoDisponibleException e) {
			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "No logra obtener los sitios."));
		}
		return result;
	}
	
	public List<EscalamientoTO> buscarEscalamiento(Integer idActividad){
		FacesContext context = FacesContext.getCurrentInstance();
		List<EscalamientoTO> result = new ArrayList<EscalamientoTO>();
		try {
			result = servicio.findEscalamiento(idActividad);
		}catch(ServicioNoDisponibleException e) {
			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "No logra obtener los sitios."));
		}
		return result;
	}
	
	public UsuarioTO obtenerDataUsuarioEsc1(Integer idActividad){
		FacesContext context = FacesContext.getCurrentInstance();
		UsuarioTO result = new UsuarioTO();
		try {
			result = servicio.findDataClientes(idActividad);
		}catch(ServicioNoDisponibleException e) {
			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "No logra obtener los sitios."));
		}
		return result;
	}
	
	public UsuarioTO obtenerDataUsuarioEsc2(Integer idActividad){
		FacesContext context = FacesContext.getCurrentInstance();
		UsuarioTO result = new UsuarioTO();
		try {
			result = servicio.findDataClientes(idActividad);
		}catch(ServicioNoDisponibleException e) {
			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "No logra obtener los sitios."));
		}
		return result;
	}
	
	public UsuarioTO obtenerDataUsuarioEsc3(Integer idActividad){
		FacesContext context = FacesContext.getCurrentInstance();
		UsuarioTO result = new UsuarioTO();
		try {
			result = servicio.findDataClientes(idActividad);
		}catch(ServicioNoDisponibleException e) {
			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "No logra obtener los sitios."));
		}
		return result;
	}
	
	public List<ProveedorTO> buscarProveedores(Integer idActividad){
		FacesContext context = FacesContext.getCurrentInstance();
		List<ProveedorTO> result = new ArrayList<ProveedorTO>();
		try {
			result = servicio.findProveedor(idActividad);
		}catch(ServicioNoDisponibleException e) {
			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "No logra obtener los proveedores."));
		}
		return result;
	}
	
	public List<AdjuntoTO> buscarAdjuntos(Integer idActividad){
		FacesContext context = FacesContext.getCurrentInstance();
		List<AdjuntoTO> result = new ArrayList<AdjuntoTO>();
		try {
			result = servicio.findAdjunto(idActividad);
		}catch(ServicioNoDisponibleException e) {
			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "No logra obtener los adjuntos."));
		}
		return result;
	}
	
	public ProyectoTO buscarProyectoSeleccionado(Integer idProyecto) {
		FacesContext context = FacesContext.getCurrentInstance();
		ProyectoTO result = null;
		try {
			result = servicio.findProyectoSeleccionado(idProyecto);
		}catch(ServicioNoDisponibleException e) {
			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "No logra obtener los adjuntos."));
		}
		return result;
	}
	
	public UsuarioTO buscarUsuarioSeleccionado(Integer idUsuario) {
		FacesContext context = FacesContext.getCurrentInstance();
		UsuarioTO result = null;
		try {
			result = servicio.findUsuarioSeleccionado(idUsuario);
		}catch(ServicioNoDisponibleException e) {
			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "No logra obtener los adjuntos."));
		}
		return result;
	}
	
	public SitioTO buscarSitioSeleccionado(Integer idSitio) {
		FacesContext context = FacesContext.getCurrentInstance();
		SitioTO result = null;
		try {
			result = servicio.findSitioSeleccionado(idSitio);
		}catch(ServicioNoDisponibleException e) {
			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "No logra obtener los adjuntos."));
		}
		return result;
	}

	public RolTO getRol() {
		return rol;
	}

	public void setRol(RolTO rol) {
		this.rol = rol;
	}

	public SitioTO getSitio() {
		return sitio;
	}

	public void setSitio(SitioTO sitio) {
		this.sitio = sitio;
	}

	public UsuarioTO getUsuario() {
		return usuario;
	}

	public void setUsuario(UsuarioTO usuario) {
		this.usuario = usuario;
	}
	
}
