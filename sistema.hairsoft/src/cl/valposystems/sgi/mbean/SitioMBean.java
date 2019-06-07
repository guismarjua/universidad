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

import cl.valposystems.sgi.business.sbean.SitioSessionBean;
import cl.valposystems.sgi.exception.ServicioNoDisponibleException;
import cl.valposystems.sgi.to.SitioTO;

@Named
@SessionScoped
public class SitioMBean implements Serializable{

	final static Logger logger = Logger.getLogger(SitioMBean.class);
	/**
	 * 
	 */
	private static final long serialVersionUID = -8127270141868638292L;
	
	@Inject	GenericMBean genericMBean;
	@EJB SitioSessionBean servicio;
	
	private SitioTO sitioSelected;
	private List<SitioTO> listaTO;
	private List<SitioTO> sitiosFiltrados;
	private SitioTO sitio;
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
		listaTO = new ArrayList<SitioTO>();
		this.sitio = new SitioTO();
		this.sitioSelected = new SitioTO();
		this.cargaSitios();
		this.cargarRegiones();
		this.cargarClasificaciones();
		
	}
	
	public void cargarRegiones() {
		this.sitio.setRegiones(genericMBean.obtenerRegiones());
	}
	
	public void findComunas() {
		if(sitio.getRegionSelected() == 0) {
			this.sitio.getComunas().clear();
			this.sitio.setComunas(genericMBean.obtenerComunas(sitio.getRegionSelected()));
		}else {
			this.sitio.setComunas(genericMBean.obtenerComunas(sitio.getRegionSelected()));
		}
	}
	
	public void precargarComunas(SitioTO sit) {
		this.sitioSelected = sit;
		this.sitioSelected.setComunas(genericMBean.precargarComunas(sit.getIdentRegion()));
		PrimeFaces.current().executeScript("PF('dlg-modificar-sitio').show()");
		
	}
	
	public void findComunasModificar() {
		if(sitioSelected.getIdentRegion() == 0) {
			this.sitioSelected.getComunas().clear();
			this.sitioSelected.setComunas(genericMBean.obtenerComunas(sitioSelected.getIdentRegion()));
		}else {
			this.sitioSelected.setComunas(genericMBean.obtenerComunas(sitioSelected.getIdentRegion()));
		}
	}
	
	public void cargarMantenedores() {
		this.sitio = new SitioTO();
		this.cargarClasificaciones();
		this.cargarRegiones();
	}
	
	public void cargarClasificaciones() {
		this.sitio.setClasificaciones(genericMBean.obtenerClasificaciones());
	}
	
	/**
	 * Se obtienen el listado de los sitios existentes
	 */
	public void cargaSitios() {
		FacesContext context = FacesContext.getCurrentInstance();
		sitiosFiltrados = new ArrayList<SitioTO>();
		try {
			if (listaTO != null && !listaTO.isEmpty()) {
				listaTO.clear();
			} 
			listaTO.addAll(servicio.obtenerSitios());
			sitiosFiltrados.addAll(listaTO);
		} catch (ServicioNoDisponibleException e) {
			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", prop.getProperty("mensaje.sitio.carga")));
		}
	}
	
	/**
	 * 
	 * Se realiza la eliminaci�n l�gica del usuario
	 */
	public void eliminarSitio(SitioTO sitio) {
		FacesContext context = FacesContext.getCurrentInstance();
		try {
			servicio.eliminarSitio(sitio);
			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Success", prop.getProperty("mensaje.sitio.eliminado")));
		}catch(ServicioNoDisponibleException e) {
			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", prop.getProperty("mensaje.sitio.error.eliminado")));
		}
		this.cargaSitios();
		
	}
	
	public void insertarSitio() {
		FacesContext context = FacesContext.getCurrentInstance();
		Integer existeSitio = 0;
		try {
			existeSitio = servicio.buscaSitio(this.sitio);
			if(existeSitio == 0 || existeSitio == null) {
				this.sitio.setIdentClasificacion(sitio.getClasificacionSelected());
				this.sitio.setIdentRegion(sitio.getRegionSelected());
				this.sitio.setIdentComuna(sitio.getComunaSelected());
				servicio.insertarSitio(this.sitio);
				context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Success", prop.getProperty("mensaje.sitio.success")));
			}else {
				context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", prop.getProperty("mensaje.sitio.existente")));
			}
		}catch(ServicioNoDisponibleException e) {
			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", prop.getProperty("mensaje.sitio.error")));
		}
		this.setSitio(new SitioTO());
		this.cargaSitios();
		this.cargarRegiones();
		this.cargarClasificaciones();
	}
	
	public void modificarSitio() {
		FacesContext context = FacesContext.getCurrentInstance();
		Integer existeSitio = 0;
		try {
			this.setSitio(servicio.seleccionarSitioPorId(sitioSelected.getIdSitio()));
			if(!this.getSitio().getSitioSinergia().equalsIgnoreCase(sitioSelected.getSitioSinergia())){
				existeSitio = servicio.buscaSitio(this.sitioSelected);
				if(existeSitio == 0 || existeSitio == null) {
					servicio.modificarSitio(sitioSelected);
					context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Success", prop.getProperty("mensaje.sitio.modificado")));
				}else {
					context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", prop.getProperty("mensaje.sitio.existente")));
				}
			}else {
				servicio.modificarSitio(sitioSelected);
				context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Success", prop.getProperty("mensaje.sitio.modificado")));
			}
		}catch(ServicioNoDisponibleException e) {
			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", prop.getProperty("mensaje.sitio.error.modificado")));
		}
		this.setSitioSelected(new SitioTO());
		this.cargaSitios();
		this.cargarRegiones();
		this.cargarClasificaciones();
		PrimeFaces.current().executeScript("PF('dlg-modificar-sitio').hide()");
	}
	
	public List<SitioTO> getSitios() {
		return listaTO;
	}

	public void setSitio(List<SitioTO> SitioTO) {
		this.listaTO = SitioTO;
	}
	
	public List<SitioTO> getSitiosFiltrados(){
		return sitiosFiltrados;
	}
	
	public void setSitiosFiltrados(List<SitioTO> SitioTO) {
		this.sitiosFiltrados = SitioTO;
	}
	
	public SitioTO getSitio() {
		return sitio;
	}
	
	public void setSitio(SitioTO sitio) {
		this.sitio = sitio;
	}

	public SitioTO getSitioSelected() {
		return sitioSelected;
	}

	public void setSitioSelected(SitioTO sitioSelected) {
		this.sitioSelected = sitioSelected;
	}
	
	

}
