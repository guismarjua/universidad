package cl.valposystems.sgi.mbean;

import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import org.apache.log4j.Logger;
import org.primefaces.PrimeFaces;

import cl.valposystems.sgi.business.sbean.ProyectoSessionBean;
import cl.valposystems.sgi.exception.ServicioNoDisponibleException;
import cl.valposystems.sgi.to.ProyectoTO;
import cl.valposystems.sgi.to.SitioTO;
import cl.valposystems.sgi.to.UsuarioTO;

@Named
@SessionScoped
public class ProyectoMBean implements Serializable{
	
	final static Logger logger = Logger.getLogger(ProyectoMBean.class);
	/**
	 * 
	 */
	private static final long serialVersionUID = -8955710629390702206L;
	
	@Inject	GenericMBean genericMBean;
	@EJB ProyectoSessionBean servicio;
	
	private ProyectoTO proyectoSelected;

	private List<ProyectoTO> listaTO;
	private ProyectoTO proyecto;
	private Properties prop = new Properties();
	
	@PostConstruct
	public void init() {
		try (InputStream input = UsuarioMBean.class.getClassLoader().getResourceAsStream("config.properties")) {
            if (input == null) {
            	logger.error("No se ha podido encontrar el archivo properties");
                return;
            }
            prop.load(input);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
		listaTO = new ArrayList<ProyectoTO>();
		this.proyecto = new ProyectoTO();
		this.proyectoSelected = new ProyectoTO();
		this.cargaProyectos();
		cargarUsuarios();
		cargarSitios();
		
	}
	
	public void cargarMantenedores() {
		this.proyecto = new ProyectoTO();
		this.cargarUsuarios();
		this.cargarSitios();
	}
	
	public void cargarUsuarios() {
		this.proyecto.setUsuarios(genericMBean.obtenerUsuarios());
	}
	
	public void cargarSitios() {
		this.proyecto.setSitios(genericMBean.obtenerSitio());
	}
	
	public void cargarMantenedoresModificar(ProyectoTO proyecto) {
		this.setProyectoSelected(new ProyectoTO());
		this.proyectoSelected = proyecto;
		this.proyecto.getUsuarios().clear();
		this.proyecto.setUsuarios(genericMBean.obtenerClientesModificar(proyecto.getIdentUsuario()));
		this.proyecto.getSitios().clear();
		this.proyecto.setSitios(genericMBean.obtenerSitioModificar(proyecto.getIdentSitio()));
		
	}
	
	
	/**
	 * Se obtienen el listado de los proyectos existentes
	 */
	public void cargaProyectos() {
		FacesContext context = FacesContext.getCurrentInstance();
		try {
			if (listaTO != null && !listaTO.isEmpty()) {
				listaTO.clear();
			} 
			listaTO.addAll(servicio.obtenerProyectos());
		} catch (ServicioNoDisponibleException e) {
			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", prop.getProperty("mensaje.proyecto.carga")));
		}
	}
	
	public void insertarProyecto() {
		FacesContext context = FacesContext.getCurrentInstance();
		try {
			this.proyecto.setIdentUsuario(proyecto.getUsuarioSelected());
			this.proyecto.setIdentSitio(proyecto.getSitioSelected());
			servicio.insertarProyecto(this.proyecto);
			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Success", prop.getProperty("mensaje.proyecto.success")));
		}catch(ServicioNoDisponibleException e) {
			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", prop.getProperty("mensaje.proyecto.error")));
		}
		this.setProyecto(new ProyectoTO());
		this.cargaProyectos();
		cargarUsuarios();
		cargarSitios();
	}
	
	/**
	 * 
	 * Se realiza la eliminaci�n l�gica del usuario
	 */
	public void eliminarProyecto(ProyectoTO proyecto) {
		FacesContext context = FacesContext.getCurrentInstance();
		try {
			servicio.eliminarProyecto(proyecto);
			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Success", prop.getProperty("mensaje.proyecto.eliminado")));
		}catch(ServicioNoDisponibleException e) {
			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", prop.getProperty("mensaje.proyecto.error.eliminado")));
		}
		this.cargaProyectos();
		
	}
	
	public void modificarProyecto() {
		FacesContext context = FacesContext.getCurrentInstance();
		try {
			SitioTO sit = genericMBean.buscarSitioSeleccionado(this.proyectoSelected.getSitioSelected());
			UsuarioTO usu = genericMBean.buscarUsuarioSeleccionado(this.proyectoSelected.getUsuarioSelected());
			if(sit == null && usu == null) {
				servicio.modificarProyecto(proyectoSelected);
				context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Success", prop.getProperty("mensaje.proyecto.modificado")));
			}else {
				logger.debug("EXISTEN VALORES REPETIDOS");
				context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "El sitio o el usuario ya no existe en el sistema. Favor seleccionar otro usuario"));
			}
		}catch(ServicioNoDisponibleException e) {
			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", prop.getProperty("mensaje.proyecto.error.modificado")));
		}
		this.setProyectoSelected(new ProyectoTO());
		this.cargaProyectos();
		cargarUsuarios();
		cargarSitios();
		PrimeFaces.current().executeScript("PF('dlg-modificar-proyecto').hide()");
	}
	
	public List<ProyectoTO> getProyectos() {
		return listaTO;
	}

	public void setProyecto(List<ProyectoTO> ProyectoTO) {
		this.listaTO = ProyectoTO;
	}
	
	public void setProyecto(ProyectoTO proyecto) {
		this.proyecto = proyecto;
	}
	
	public ProyectoTO getProyecto() {
		return proyecto;
	}
	
	public ProyectoTO getProyectoSelected() {
		return proyectoSelected;
	}

	public void setProyectoSelected(ProyectoTO proyectoSelected) {
		this.proyectoSelected = proyectoSelected;
	}
	
	

}
