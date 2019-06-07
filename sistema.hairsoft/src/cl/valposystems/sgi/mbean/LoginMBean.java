package cl.valposystems.sgi.mbean;

import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.util.Properties;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpSession;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.log4j.Logger;

import cl.valposystems.sgi.business.sbean.LoginSessionBean;
import cl.valposystems.sgi.exception.ServicioNoDisponibleException;
import cl.valposystems.sgi.to.UsuarioTO;

@Named
@SessionScoped
public class LoginMBean implements Serializable {

	final static Logger logger = Logger.getLogger(LoginMBean.class);
	/**
	 * 
	 */
	private static final long serialVersionUID = 5568989266417843174L;
	private static final String ADMINISTRADOR = "ADMIN";
	private static final String ITO = "ITO";
	private static final String CLIENTE = "CLIENTE";
	private static final String MANAGER = "MANAGER";
	
	private UsuarioTO usuario;
	private boolean logeado;
	private String role;
	private String usuarioLogueado;
	private Integer idUsuario;
	Properties prop = new Properties();
	
	
	@Inject RouterMBean router;
	@EJB LoginSessionBean servicio;
	
	@PostConstruct
	public void inicializador() {
		try (InputStream input = UsuarioMBean.class.getClassLoader().getResourceAsStream("config.properties")) {
            if (input == null) {
            	logger.error("No se ha podido encontrar el archivo properties");
                return;
            }
            prop.load(input);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
		usuario = new UsuarioTO();
		logeado = false;
		
	}
	

	/**
	 * Metodo encargado de redireccionar al usuario dependiendo las credenciales ingresadas
	 * @return String
	 */
	
	public String login() {	
		logeado = false;
		StringBuilder page = new StringBuilder();
		logger.debug("Entrando al login");
		try {
			logger.debug("Entrando al try");
			UsuarioTO usuarioValido = servicio.obtenerInformacionUsuario(usuario.getRutUsuario(), DigestUtils.md5Hex((usuario.getContrasenaUsuario())));
			logger.debug("Despues de servicio.obtenerInformacionUsuario");
			if (usuarioValido != null && usuarioValido.getIdRol().compareTo(ADMINISTRADOR) == 0) {
				logger.debug("Admin");
				page.append(this.setIndexAdmin(usuarioValido));
				logger.debug("Page: "+page);
			}else if(usuarioValido != null && usuarioValido.getIdRol().compareTo(CLIENTE) == 0) {
				logger.debug("Cliente");
				page.append(this.setIndexCliente(usuarioValido));
				logger.debug("Page: "+page);
			}else if(usuarioValido != null && usuarioValido.getIdRol().compareTo(ITO) == 0) {
				logger.debug("IndexITO");
				page.append(this.setIndexITO(usuarioValido));
				logger.debug("Page: "+page);
			}else if(usuarioValido != null && usuarioValido.getIdRol().compareTo(MANAGER) == 0) {
				logger.debug("MANAGER");
				page.append(this.setIndexManager(usuarioValido));
				logger.debug("Page: "+page);	
			} else {
				logger.debug("Redireccionar por defecto");
				page.append("login.xhtml?faces-redirect=true");
				logger.debug("Page: "+page);
				setearMensaje("Error", prop.getProperty("mensaje.login.error"), FacesMessage.SEVERITY_ERROR);
			}
		}catch(ServicioNoDisponibleException e) {
			logger.error("Error en el login: "+e.getMessage());
			page.append("login.xhtml?faces-redirect=true");
			setearMensaje("Error", prop.getProperty("mensaje.login.error.sistema"), FacesMessage.SEVERITY_ERROR);
		}
		return page.toString();
	}


	private String setIndexAdmin(UsuarioTO usuario) {
		logeado = true;
		role = ADMINISTRADOR;
		usuario.setIdRol(ADMINISTRADOR);
		logger.debug("setIndexAdmin antes del modulo principal");
		router.moduloPrincipal();
		FacesContext facesContext = FacesContext.getCurrentInstance();
		HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(true);
		session.setAttribute("usuario", usuario.getNombreUsuario() + " " + usuario.getApellidoUsuario());
		session.setAttribute("rol", ADMINISTRADOR);		
		this.idUsuario = usuario.getIdUsuario();		
		this.usuarioLogueado = (String)session.getAttribute("usuario");
		logger.debug("setIndexAdmin antes del redirect");
		return "index.xhtml?faces-redirect=true";
	}
	
	private String setIndexCliente(UsuarioTO usuario) {
		logeado = true;
		role = CLIENTE;
		usuario.setIdRol(CLIENTE);
		router.moduloPrincipalCliente();
		FacesContext facesContext = FacesContext.getCurrentInstance();
		HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(true);
		session.setAttribute("usuario", usuario.getNombreUsuario() + " " + usuario.getApellidoUsuario());
		session.setAttribute("rol", CLIENTE);	
		this.idUsuario = usuario.getIdUsuario();	
		this.usuarioLogueado = (String)session.getAttribute("usuario");
		logger.debug("setIndexAdmin antes del redirect");
		return "cliente.xhtml?faces-redirect=true";
		
	}
	
	private String setIndexITO(UsuarioTO usuario) {
		logeado = true;
		role = ITO;
		usuario.setIdRol(ITO);
		router.moduloPrincipalITO();
		FacesContext facesContext = FacesContext.getCurrentInstance();
		HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(true);
		session.setAttribute("usuario", usuario.getNombreUsuario() + " " + usuario.getApellidoUsuario());
		session.setAttribute("rol", ITO);	
		this.idUsuario = usuario.getIdUsuario();	
		this.usuarioLogueado = (String)session.getAttribute("usuario");
		logger.debug("setIndexAdmin antes del redirect");
		return "ito.xhtml?faces-redirect=true";
		
	}
	
	private String setIndexManager(UsuarioTO usuario) {
		logeado = true;
		role = MANAGER;
		usuario.setIdRol(MANAGER);
		router.moduloPrincipalManager();
		FacesContext facesContext = FacesContext.getCurrentInstance();
		HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(true);
		session.setAttribute("usuario", usuario.getNombreUsuario() + " " + usuario.getApellidoUsuario());
		session.setAttribute("rol", MANAGER);	
		this.idUsuario = usuario.getIdUsuario();	
		this.usuarioLogueado = (String)session.getAttribute("usuario");
		logger.debug("setIndexAdmin antes del redirect");
		return "manager.xhtml?faces-redirect=true";
		
	}
	
	public String salir() {
		logeado = false;
		FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
		return "login.xhtml?faces-redirect=true";
	}
	
	public String volverLogin() {
		logeado = false;
		return "login.xhtml?faces-redirect=true";
	}
	

	private void setearMensaje(String titulo, String detalle, FacesMessage.Severity severity) {
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(severity, titulo, detalle));
		FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);
	}


	public UsuarioTO getUsuario() {
		return usuario;
	}


	public void setUsuario(UsuarioTO usuario) {
		this.usuario = usuario;
	}


	public boolean isLogeado() {
		return logeado;
	}


	public void setLogeado(boolean logeado) {
		this.logeado = logeado;
	}


	public String getRole() {
		return role;
	}


	public void setRole(String role) {
		this.role = role;
	}


	public String getUsuarioLogueado() {
		return usuarioLogueado;
	}


	public void setUsuarioLogueado(String usuarioLogueado) {
		this.usuarioLogueado = usuarioLogueado;
	}


	public Integer getIdUsuario() {
		return idUsuario;
	}


	public void setIdUsuario(Integer idUsuario) {
		this.idUsuario = idUsuario;
	}

}
