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

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.log4j.Logger;
import org.primefaces.PrimeFaces;

import cl.valposystems.sgi.business.sbean.UsuarioSessionBean;
import cl.valposystems.sgi.exception.ServicioNoDisponibleException;
import cl.valposystems.sgi.to.UsuarioTO;

@Named
@SessionScoped
public class UsuarioMBean implements Serializable{

	final static Logger logger = Logger.getLogger(UsuarioMBean.class);
	/**
	 * 
	 */
	private static final long serialVersionUID = -1103851702864520494L;

	@Inject	GenericMBean genericMBean;
	@EJB UsuarioSessionBean servicio;
	
	private UsuarioTO usuarioSelected;
	private List<UsuarioTO> listaTO;
	private UsuarioTO usuario;
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
		
		listaTO = new ArrayList<UsuarioTO>();
		this.usuario = new UsuarioTO();
		this.usuarioSelected = new UsuarioTO();
		this.cargaUsuarios();
		this.cargarRoles();
	}
	
	public void inicializador() {
		this.setUsuario(new UsuarioTO());
	}	
	
	/**
	 * Se cargan los roles
	 */
	
	public void cargarRoles() {
		this.usuario.setRoles(genericMBean.obtenerRoles());
	}
	
	/**
	 * Se obtienen el listado de los convenios existentes
	 */
	public void cargaUsuarios() {
		FacesContext context = FacesContext.getCurrentInstance();
		try {
			if (listaTO != null && !listaTO.isEmpty()) {
				listaTO.clear();
			} 
			listaTO.addAll(servicio.obtenerUsuarios());
		} catch (ServicioNoDisponibleException e) {
			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", prop.getProperty("mensaje.usuario.carga")));
		}
	}
	
	/**
	 * 
	 * Se realiza la eliminaci�n l�gica del usuario
	 */
	public void eliminarUsuario(UsuarioTO usuario) {
		FacesContext context = FacesContext.getCurrentInstance();
		try {
			servicio.eliminarUsuario(usuario);
			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Success", prop.getProperty("mensaje.usuario.eliminado")));
		}catch(ServicioNoDisponibleException e) {
			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", prop.getProperty("mensaje.usuario.error.eliminado")));
		}
		this.cargaUsuarios();
		
	}
	
	/**
	 * 
	 * Se realiza la inserci�n del usuario
	 */
	public void insertarUsuario() {
		FacesContext context = FacesContext.getCurrentInstance();
		Integer existeUsuario = 0;
		try {
			if(servicio.validarRut(usuario.getRutUsuario()+"-"+usuario.getDvUsuario())){
				existeUsuario = servicio.buscarUsuario(this.usuario);
				if(existeUsuario == 0 || existeUsuario == null) {
					this.usuario.setIdentRol(usuario.getRolSelected());
					String contrEncript = DigestUtils.md5Hex(this.usuario.getContrasenaUsuario());
					this.usuario.setContrasenaUsuario(contrEncript);
					servicio.insertarUsuario(this.usuario);
					context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Success",  prop.getProperty("mensaje.usuario.success")));
				}else {
					context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", prop.getProperty("mensaje.usuario.existente")));
				}
			}else {
				context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", prop.getProperty("mensaje.usuario.rut.invalido")));
			}
		}catch(ServicioNoDisponibleException e) {
			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", prop.getProperty("mensaje.usuario.error")));
		}
		this.setUsuario(new UsuarioTO());
		this.cargaUsuarios();
		this.cargarRoles();	
	}
	
	/**
	 * 
	 * Se realiza la modificaci�n del usuario
	 */
	public void modificarUsuario() {
		FacesContext context = FacesContext.getCurrentInstance();
		Integer existeUsuario = 0;
		try {
			//this.usuarioSelected.setIdentRol(usuario.getRolSelected());
			//Busco los datos del usuario previa modificacion
			this.setUsuario(servicio.seleccionarUsuarioPorId(usuarioSelected.getIdUsuario()));
			//Confirmo si los datos existentes coinciden con los ingresados en el formulario
			if(!(this.getUsuario().getRutUsuario()+"-"+this.getUsuario().getDvUsuario()).equals(usuarioSelected.getRutUsuario()+"-"+usuarioSelected.getDvUsuario())){
				if(servicio.validarRut(usuarioSelected.getRutUsuario()+"-"+usuarioSelected.getDvUsuario())) {
					existeUsuario = servicio.buscarUsuario(this.usuarioSelected);
					if(existeUsuario == 0 || existeUsuario == null) {
						if(usuarioSelected.getContrasenaUsuario() != null && usuarioSelected.getContrasenaUsuario() != "") {
							String contrEncript = DigestUtils.md5Hex(this.usuarioSelected.getContrasenaUsuario());
							this.usuarioSelected.setContrasenaUsuario(contrEncript);
						}
						servicio.modificarUsuario(usuarioSelected);
						context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Success", prop.getProperty("mensaje.usuario.modificado")));
						PrimeFaces.current().executeScript("PF('dlg-modificar-usuario').hide()");
						
					}else {
						context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", prop.getProperty("mensaje.usuario.existente")));
					}
				}else {
					context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", prop.getProperty("mensaje.usuario.rut.invalido")));
				}
			}else {
				if(usuarioSelected.getContrasenaUsuario() != null && !usuarioSelected.getContrasenaUsuario().isEmpty()) {
					String contrEncript = DigestUtils.md5Hex(this.usuarioSelected.getContrasenaUsuario());
					this.usuarioSelected.setContrasenaUsuario(contrEncript);
				}
				servicio.modificarUsuario(usuarioSelected);
				context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Success", prop.getProperty("mensaje.usuario.modificado")));
				PrimeFaces.current().executeScript("PF('dlg-modificar-usuario').hide()");
				this.setUsuarioSelected(new UsuarioTO());
			}
			this.setUsuarioSelected(new UsuarioTO());
			this.cargaUsuarios();
			this.cargarRoles();
		}catch(ServicioNoDisponibleException e) {
			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", prop.getProperty("mensaje.usuario.error.modificado")));
		}
		
	}
	
	public List<UsuarioTO> getListaUsuarios() {
		return listaTO;
	}

	public void setListaUsuarios(List<UsuarioTO> UsuarioTO) {
		this.listaTO = UsuarioTO;
	}

	public UsuarioTO getUsuario() {
		return usuario;
	}

	public void setUsuario(UsuarioTO usuario) {
		this.usuario = usuario;
	}

	public UsuarioTO getUsuarioSelected() {
		return usuarioSelected;
	}

	public void setUsuarioSelected(UsuarioTO usuarioSelected) {
		this.usuarioSelected = usuarioSelected;
	}
	
}
