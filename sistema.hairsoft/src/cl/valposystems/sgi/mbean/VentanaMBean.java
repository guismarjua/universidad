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
import org.primefaces.event.TransferEvent;

import cl.valposystems.sgi.business.sbean.VentanaSessionBean;
import cl.valposystems.sgi.exception.ServicioNoDisponibleException;
import cl.valposystems.sgi.to.ActividadTO;
import cl.valposystems.sgi.to.VentanaActividadTO;
import cl.valposystems.sgi.to.VentanaTO;

@Named
@SessionScoped
public class VentanaMBean implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 7341522423047756009L;

	final static Logger logger = Logger.getLogger(VentanaMBean.class);
			
	@EJB VentanaSessionBean servicio;
	
	@Inject
	LoginMBean loginMBean;
	
	private VentanaTO ventanaSelected;
	private List<VentanaTO> listaTO;
	private VentanaTO ventana;
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
        	logger.error(ex);
        }
		listaTO = new ArrayList<VentanaTO>();
		this.ventana = new VentanaTO();
		this.cargaVentanas();
	}
	
	
	public void inicializar() {
		this.cargaVentanas();
	}
	
	/**
	 * Se obtienen el listado de las ventanas existentes
	 */
	public void cargaVentanas() {
		FacesContext context = FacesContext.getCurrentInstance();
		try {
			if (listaTO != null && !listaTO.isEmpty()) {
				listaTO.clear();
			} 
			listaTO.addAll(servicio.obtenerVentana());
		} catch (ServicioNoDisponibleException e) {
			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", prop.getProperty("mensaje.ventana.error.carga.ventanas")));
		}
	}
	
	/**
	 * 
	 * Se realiza la eliminación lógica del usuario
	 */
	public void eliminarVentana(VentanaTO ventana) {
		FacesContext context = FacesContext.getCurrentInstance();
		try {
			servicio.actualizarActividadVentana(ventana);
			servicio.eliminarVentana(ventana);
			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Success", prop.getProperty("mensaje.ventana.eliminar")));
		}catch(ServicioNoDisponibleException e) {
			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", prop.getProperty("mensaje.ventana.error.eliminar")));
		}
		this.cargaVentanas();
		
	}
	
	/**
	 * 
	 * Se realiza la inserción del usuario
	 */
	public void crearVentana() {
		FacesContext context = FacesContext.getCurrentInstance();
		try {
			int userIDint = loginMBean.getIdUsuario();
			this.ventana.setIdUsuario(userIDint);
			servicio.insertarVentana(this.ventana);
			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Success", prop.getProperty("mensaje.ventana.creada")));
		}catch(ServicioNoDisponibleException e) {
			logger.error(e);
			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", prop.getProperty("mensaje.ventana.error.crear")));
		}
		this.setVentana(new VentanaTO());
		this.cargaVentanas();
	}
	
	/**
	 * Se cargan el dual list para pick list
	 */
	
	public void cargarActividadesAsignadas(Integer idVentana) {
		FacesContext context = FacesContext.getCurrentInstance();
		List<ActividadTO> catalogoActividades = new ArrayList<ActividadTO>();
		List<ActividadTO> actividadesAsociadas = new ArrayList<ActividadTO>();
		try {
			catalogoActividades.addAll(servicio.obtenerListadoActividades());
			actividadesAsociadas.addAll(servicio.obtenerListadoActividadesAsignadas(idVentana));
			ventana.getListaDualActividades().setSource(catalogoActividades);
			ventana.getListaDualActividades().setTarget(actividadesAsociadas);
		}catch(ServicioNoDisponibleException e) {
			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "No logra obtener a los sostenedores."));
		}
	}
	
	public void actualizarActividadVentana(TransferEvent event){
		FacesContext context = FacesContext.getCurrentInstance();
		String mensaje = null;
		try {
			if (event.isAdd()) {
				for(Object item : event.getItems()) {
					VentanaActividadTO to = new VentanaActividadTO();
					to.setIdVentana(ventanaSelected.getIdVentana());
					to.setIdActividad(Integer.parseInt((String)item));
					to.setVigencia(1);
					servicio.agregarVentanaActividad(to);
					servicio.updateFechaModificacionVentana(ventanaSelected.getIdVentana());
				}
				mensaje = prop.getProperty("mensaje.agregar.actividad");
			}else {
				for(Object item : event.getItems()) {
					VentanaActividadTO to = new VentanaActividadTO();
					to.setIdVentana(ventanaSelected.getIdVentana());
					to.setIdActividad(Integer.parseInt((String)item));
					to.setVigencia(0);
					servicio.modificarVentanaActividad(to);
					servicio.updateFechaModificacionVentana(ventanaSelected.getIdVentana());
				}
				mensaje = prop.getProperty("mensaje.remover.actividad");
			}
			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", mensaje));
			this.cargaVentanas();
		}catch(ServicioNoDisponibleException e) {
			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "No logra obtener a los sostenedores."));
		}
	}
	
	public List<VentanaTO> getListaVentanas() {
		return listaTO;
	}

	public void setListaVentanas(List<VentanaTO> VentanaTO) {
		this.listaTO = VentanaTO;
	}

	public VentanaTO getVentana() {
		return ventana;
	}

	public void setVentana(VentanaTO ventana) {
		this.ventana = ventana;
	}

	public VentanaTO getVentanaSelected() {
		return ventanaSelected;
	}

	public void setVentanaSelected(VentanaTO ventanaSelected) {
		this.ventanaSelected = ventanaSelected;
	}
}
