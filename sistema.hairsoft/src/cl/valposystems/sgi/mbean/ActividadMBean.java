package cl.valposystems.sgi.mbean;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.Properties;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.apache.commons.io.IOUtils;
import org.apache.log4j.Logger;
import org.primefaces.event.CellEditEvent;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.event.RowEditEvent;
import org.primefaces.event.SelectEvent;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.UploadedFile;

import cl.valposystems.sgi.business.sbean.ActividadSessionBean;
import cl.valposystems.sgi.exception.ServicioNoDisponibleException;
import cl.valposystems.sgi.to.ActividadTO;
import cl.valposystems.sgi.to.AdjuntoTO;
import cl.valposystems.sgi.to.EscalamientoTO;
import cl.valposystems.sgi.to.ProveedorTO;
import cl.valposystems.sgi.to.ProyectoTO;
import cl.valposystems.sgi.to.SitioTO;
import cl.valposystems.sgi.to.StatusEspecificosTO;
import cl.valposystems.sgi.to.StatusTO;
import cl.valposystems.sgi.to.SupervisionTO;
import cl.valposystems.sgi.to.UsuarioTO;

@Named
@ViewScoped
public class ActividadMBean implements Serializable {
	final static Logger logger = Logger.getLogger(ActividadMBean.class);
	/**
	 * 
	 */
	private static final long serialVersionUID = 8115573665111334505L;

	@Inject
	GenericMBean genericMBean;
	
	@Inject
	LoginMBean loginMBean;
	
	@EJB
	ActividadSessionBean servicio;

	private AdjuntoTO adjuntoSelected;
	private ActividadTO actividadSelected;
	private List<ActividadTO> listaTO;
	private ActividadTO actividad;
	private ActividadTO actividadEditar;
	private ActividadTO actividadMostrar;
	private ActividadTO actividadMostrar2;
	private ProveedorTO proveedor;
	private ProveedorTO proveedorEditar;
	private AdjuntoTO adjunto;
	private UploadedFile file;
	private boolean habilitar = true;
	List<ProveedorTO> proveedores = new ArrayList<>();
	List<ProveedorTO> proveedoresEditar;
	List<AdjuntoTO> adjuntos;
	private EscalamientoTO escalamiento1;
	private EscalamientoTO escalamiento2;
	private EscalamientoTO escalamiento3;
	List<String> adjuntosBorrados;
	RouterMBean routerMBean = new RouterMBean();
	Properties prop = new Properties();

	@PostConstruct
	public void init() {
		try (InputStream input = ActividadMBean.class.getClassLoader().getResourceAsStream("config.properties")) {
            if (input == null) {
            	logger.error("No se ha podido encontrar el archivo properties");
                return;
            }
            prop.load(input);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
		listaTO = new ArrayList<ActividadTO>();
		this.actividad = new ActividadTO();
		this.actividadSelected = new ActividadTO();
		this.actividadMostrar = new ActividadTO();
		this.actividadMostrar2 = new ActividadTO();
		this.actividadEditar = new ActividadTO();
		this.cargaActividades();
		this.cargarMantenedores();
		this.proveedor = new ProveedorTO();
		this.proveedorEditar = new ProveedorTO();
		this.adjunto = new AdjuntoTO();
		this.habilitar = true;
		this.proveedoresEditar = new ArrayList<>();
		this.adjuntos = new ArrayList<>();
		this.escalamiento1 = new EscalamientoTO();
		this.escalamiento2 = new EscalamientoTO();
		this.escalamiento3 = new EscalamientoTO();
		adjuntosBorrados = new ArrayList<String>();
	}
	
	public void inicializador() {
		this.cargaActividades();
	}
	
	
	public void findDataUsuarioPM() {
		logger.info("EJECUTANDO EL findDataUsuarioPM: "+this.actividad.getUsuarioPMSelected());
		this.actividadMostrar.setDataUsuario(genericMBean.obtenerDataUsuario(this.actividad.getUsuarioPMSelected()));
		logger.info("Usuario PM, Email: "+this.actividadMostrar.getDataUsuario().getEmailUsuario());
		logger.info("Usuario PM, Telefono: "+this.actividadMostrar.getDataUsuario().getTelefonoUsuario());
		logger.info("Usuario PM, Cargo: "+this.actividadMostrar.getDataUsuario().getCargoUsuario());
	}

	public void findDataUsuarioSuperv() {
		logger.info("EJECUTANDO EL findDataUsuarioSuperv: "+this.actividad.getUsuarioSupervisorSelected());
		this.actividadMostrar.setDataUsuarioSuperv(
				genericMBean.obtenerDataUsuarioSuperv(this.actividad.getUsuarioSupervisorSelected()));
		logger.info("Usuario Supervisor, Email: "+this.actividadMostrar.getDataUsuarioSuperv().getEmailUsuario());
		logger.info("Usuario Supervisor, Telefono: "+this.actividadMostrar.getDataUsuarioSuperv().getTelefonoUsuario());
		logger.info("Usuario Supervisor, Cargo: "+this.actividadMostrar.getDataUsuarioSuperv().getCargoUsuario());
	}
	
	public void findDataUsuarioPMEditar() {
		logger.info("EJECUTANDO EL findDataUsuarioPMEditar: "+this.actividadSelected.getUsuarioPMSelected());
		this.actividadMostrar2.setDataUsuario(genericMBean.obtenerDataUsuario(this.actividadSelected.getUsuarioPMSelected()));
		logger.info("Usuario PM, Email: "+this.actividadMostrar2.getDataUsuario().getEmailUsuario());
		logger.info("Usuario PM, Telefono: "+this.actividadMostrar2.getDataUsuario().getTelefonoUsuario());
		logger.info("Usuario PM, Cargo: "+this.actividadMostrar2.getDataUsuario().getCargoUsuario());
	}

	public void findDataUsuarioSupervEditar() {
		logger.info("EJECUTANDO EL findDataUsuarioSupervEditar: "+this.actividadSelected.getUsuarioSupervisorSelected());
		this.actividadMostrar2.setDataUsuarioSuperv(
				genericMBean.obtenerDataUsuarioSuperv(this.actividadSelected.getUsuarioSupervisorSelected()));
		logger.info("Usuario Supervisor, Email: "+this.actividadMostrar2.getDataUsuarioSuperv().getEmailUsuario());
		logger.info("Usuario Supervisor, Telefono: "+this.actividadMostrar2.getDataUsuarioSuperv().getTelefonoUsuario());
		logger.info("Usuario Supervisor, Cargo: "+this.actividadMostrar2.getDataUsuarioSuperv().getCargoUsuario());
	}

	public void cargarMantenedores() {
		this.actividad.setProyectos(genericMBean.obtenerProyecto());
		this.actividad.setSupervisiones(genericMBean.obtenerSupervisiones());
		this.actividad.setSitios(genericMBean.obtenerSitio());
		this.actividad.setStatus(genericMBean.obtenerStatus());
		this.actividad.setUsuariosPM(genericMBean.obtenerClientes());
		this.actividad.setUsuariosSupervisor(genericMBean.obtenerClientes());
	}
	
	public void cargarMantenedoresModificar(ActividadTO actividad) {
		this.actividad.setProyectos(new ArrayList<ProyectoTO>());
		this.actividad.setProyectos(genericMBean.obtenerProyectoModificar(actividad.getSitioSelected()));
		this.actividad.setSupervisiones(new ArrayList<SupervisionTO>());
		this.actividad.setSupervisiones(genericMBean.obtenerSupervisiones());
		this.actividad.setSitios(new ArrayList<SitioTO>());
		this.actividad.setSitios(genericMBean.obtenerSitioModificar(actividad.getSitioSelected()));
		this.actividad.setStatus(new ArrayList<StatusTO>());
		this.actividad.setStatus(genericMBean.obtenerStatus());
		this.actividad.setUsuariosPM(new ArrayList<UsuarioTO>());
		this.actividad.setUsuariosPM(genericMBean.obtenerClientesModificar(actividad.getUsuarioPMSelected()));
		this.actividad.setUsuariosSupervisor(new ArrayList<UsuarioTO>());
		this.actividad.setUsuariosSupervisor(genericMBean.obtenerClientesModificar(actividad.getUsuarioSupervisorSelected()));
	}

	/**
	 * Se obtienen el listado de las ventanas existentes
	 */
	public void cargaActividades() {
		FacesContext context = FacesContext.getCurrentInstance();
		try {
			if (listaTO != null && !listaTO.isEmpty()) {
				listaTO.clear();
			}
			if(loginMBean.getRole().equals("ITO")) {
				listaTO.addAll(servicio.obtenerActividadesITO(loginMBean.getIdUsuario()));
			}else {
				listaTO.addAll(servicio.obtenerActividades());
			}
		} catch (ServicioNoDisponibleException e) {
			context.addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "No logra obtener a los sostenedores."));
		}
	}

	/**
	 * 
	 * Se realiza la inserción de la actividad
	 */
	public void crearActividad() {
		FacesContext context = FacesContext.getCurrentInstance();
		Integer identificadorActividad = 0;
		try {
	        	logger.info("fechaInicio es antes fechaTermino, por lo tanto se crea actividad.");
	        	// 1) Se genera la insersión en tabla actividad
				logger.info("This is info : " + this.actividad.getStatusSelected());
				actividad.setIdStatus(this.actividad.getStatusSelected());
				actividad.setIdProyecto(this.actividad.getProyectoSelected());
				actividad.setIdSupervision(this.actividad.getSupervisionSelected());
				actividad.setIdSitio(this.actividad.getSitioSelected());
				if (this.actividad.getStatusSelected() == 3) {
					actividad.setIdStatusEspecifico(null);
				} else {
					actividad.setIdStatusEspecifico(this.actividad.getStatusSelectedEspecifico());
				}
				// Cambiar cuando esté listo el login
				logger.debug("Cambiar cuando esté listo el login");
				
				int userIDint = loginMBean.getIdUsuario();
				logger.info("USERID INTEGER: "+userIDint);
				actividad.setIdUsuario(userIDint);
				logger.info("INFO DE getIdStatusEspecifico ANTES DE INSERTAR ACTIVIDAD : "
						+ this.actividad.getIdStatusEspecifico());
				servicio.insertarActividad(this.actividad);
				// 2)Se carga la informacion Personal Claro PM y Personal Claro Supervisor
				logger.debug("2)Se carga la informacion Personal Claro PM y Personal Claro Supervisor");
				identificadorActividad = servicio.obtenerIdActividad(actividad);
				servicio.insertarPersonalPM(identificadorActividad, actividad.getUsuarioPMSelected());
				servicio.insertarPersonalSupervisor(identificadorActividad, actividad.getUsuarioSupervisorSelected());
				// 3)Se carga el nivel de escalamiento
				logger.debug("3)Se carga el nivel de escalamiento");
				escalamiento1.setNivel("Primer Nivel");
				escalamiento2.setNivel("Segundo Nivel");
				escalamiento3.setNivel("Tercer Nivel");
				servicio.insertarEscalamiento(identificadorActividad, escalamiento1, escalamiento2, escalamiento3);
				// 4)Se cargan los proveedores
				logger.debug("4)Se cargan los proveedores");
				servicio.insertarProveedores(identificadorActividad, proveedores);

				// 5)Se cargan las observaciones
				logger.debug("5)Se cargan las observaciones");
				servicio.insertarObservaciones(identificadorActividad, actividad.getObservacionActividad());
				// 6)Se cargan las imagenes
				logger.debug("6)Se cargan las imagenes");
				logger.debug("----------- SE CARGAN LAS IMAGENES -----------");
				boolean existeAdjunto = false;
				OutputStream outputStream = null;
				if (actividad.getAdjuntos() != null && actividad.getAdjuntos().size() > 0) {
					logger.debug("----------- IF de ADJUNTOS -----------");
					for (int i = 0; i < actividad.getAdjuntos().size(); i++) {
						logger.debug("----------- FOR de ADJUNTOS -----------");
						String nombreArchivo = actividad.getAdjuntos().get(i).getNombreAdjunto();
						String pathFolder = prop.getProperty("url.files") + identificadorActividad;
						String path = prop.getProperty("url.files") + identificadorActividad + "\\" + nombreArchivo;
						logger.debug("Path archivo (" + i + "): " + path);
						actividad.getAdjuntos().get(i).setUrlAdjunto(path);
						try {
							File dir = new File(pathFolder);
							dir.mkdir();
							File file = new File(path);
							UploadedFile input = actividad.getAdjuntos().get(i).getArchivo();

							InputStream inputStream = input.getInputstream();

							outputStream = new FileOutputStream(file);

							int read = 0;
							byte[] bytes = new byte[1024];
							while ((read = inputStream.read(bytes)) != -1) {
								outputStream.write(bytes, 0, read);
							}
						} catch (Exception e) {
							existeAdjunto = false;
							logger.debug("There is a Exception thrown, description: " + e.getMessage());
						}
					}
					existeAdjunto = true;
				} else {
					existeAdjunto = false;
					logger.debug("No hay adjunto para guardar.");
				}
				logger.debug("Se llama servicio de Insertar Adjuntos");
				if(existeAdjunto) {
					servicio.insertarAdjuntos(identificadorActividad, actividad.getAdjuntos());
				}
				// Actualizo los mantenedores
				context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Success", prop.getProperty("mensaje.crear.actividad")));
				this.cargarMantenedores();
				
				
	        
		} catch (Exception e) {
			logger.error("Error en el crear datos: " + e.getMessage());
			context.addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", prop.getProperty("mensaje.error.crear.actividad")));
		}
		this.setActividad(new ActividadTO());
		this.setActividadMostrar(new ActividadTO());
		this.setEscalamiento1(new EscalamientoTO());
		this.setEscalamiento2(new EscalamientoTO());
		this.setEscalamiento3(new EscalamientoTO());
		this.setProveedor(new ProveedorTO());
		this.proveedores.clear();
		
		this.cargaActividades();
		
	}

	/**
	 * 
	 * Se realiza la modificación de la actividad
	 * 
	 * @throws Exception
	 */
	public void modificarActividad() throws Exception {
		boolean isValid = true;
		FacesContext context = FacesContext.getCurrentInstance();
		try {
			// 1) Se genera la insersión en tabla actividad
			actividadSelected.setIdStatus(this.actividadSelected.getStatusSelected());
			actividadSelected.setIdProyecto(this.actividadSelected.getProyectoSelected());
			actividadSelected.setIdSupervision(this.actividadSelected.getSupervisionSelected());
			actividadSelected.setIdSitio(this.actividadSelected.getSitioSelected());
			//Busco si el proyecto, sitio, y los PM y Clientes existen
			ProyectoTO pro = genericMBean.buscarProyectoSeleccionado(this.actividadSelected.getProyectoSelected());
			SitioTO sit = genericMBean.buscarSitioSeleccionado(this.actividadSelected.getSitioSelected());
			UsuarioTO usuPM = genericMBean.buscarUsuarioSeleccionado(this.actividadSelected.getUsuarioPMSelected());
			UsuarioTO usuSup = genericMBean.buscarUsuarioSeleccionado(this.actividadSelected.getUsuarioSupervisorSelected());
			if(pro == null && sit == null && usuPM == null && usuSup == null) {
				// Cambiar cuando esté listo el login
				logger.debug("Cambiar cuando esté listo el login editar");
				int userIDint = loginMBean.getIdUsuario();
				actividad.setIdUsuario(userIDint);
				logger.debug("actividadEditar (IdActividad) = " + actividadSelected.getIdActividad());
				logger.debug("actividadEditar (IdStatus) = " + actividadSelected.getIdStatus());
				logger.debug("actividadEditar (IdProyecto) = " + actividadSelected.getIdProyecto());
				logger.debug("actividadEditar (IdSupervision) = " + actividadSelected.getIdSupervision());
				logger.debug("actividadEditar (IdSitio) = " + actividadSelected.getIdSitio());
				logger.debug("actividadEditar (IdAct) = " + actividadSelected.getIdAct());
				logger.debug("actividadEditar (CrqActividad) = " + actividadSelected.getCrqActividad());
				logger.debug("actividadEditar (ProyectoSelected) = " + actividadSelected.getProyectoSelected());
				logger.debug("actividadEditar (SintesisActividad) = " + actividadSelected.getSintesisActividad());
				logger.debug("actividadEditar (SupervisionSelected) = " + actividadSelected.getSupervisionSelected());
				logger.debug("actividadEditar (SitioSelected) = " + actividadSelected.getSitioSelected());
				logger.debug("actividadEditar (impactoActividad) = " + actividadSelected.getImpactoActividad());
				logger.debug("actividadEditar (fechaInicio) = " + actividadSelected.getFechaInicio());
				logger.debug("actividadEditar (estadoInicio) = " + actividadSelected.getEstadoInicio());
				logger.debug("actividadEditar (operadorInicio) = " + actividadSelected.getOperadorInicio());
				logger.debug("actividadEditar (UsuarioPM) = " + actividadSelected.getUsuarioPMSelected());
				logger.debug("actividadEditar (UsuarioSupervisorSelected) = "
						+ actividadSelected.getUsuarioSupervisorSelected());
				logger.debug("actividadEditar (statusSelected) = " + actividadSelected.getStatusSelected());
				logger.debug("actividadEditar (statusSelectedEspecifico) = " + actividadSelected.getStatusSelectedEspecifico());
	
				servicio.editarActividad(this.actividadSelected);
	
				// EDITAR EL PersonalPM
				logger.debug("Editando modificarPersonalPM (UsuarioPM) = " + actividadSelected.getUsuarioPMSelected());
				servicio.modificarPersonalPM(this.actividadSelected.getIdActividad(),
						actividadSelected.getUsuarioPMSelected());
	
				// EDITAR EL getUsuarioSupervisorSelected
				logger.debug("Editando UsuarioSupervisorSelected (UsuarioSupervisorSelected) = "
						+ actividadSelected.getUsuarioSupervisorSelected());
				servicio.modificarPersonalSupervisor(this.actividadSelected.getIdActividad(),
						actividadSelected.getUsuarioSupervisorSelected());
	
				// EDITAR EL Escalamiento
				logger.debug("Editando el Escalamiento");
				List<EscalamientoTO> escalamientos = servicio.buscarIdEscalamiento(this.actividadSelected.getIdActividad());
	
				for (int i = 0; i < escalamientos.size(); i++) {
					switch (i) {
					case 0:
						this.actividadSelected.escalamiento1.setIdEscalamiento(escalamientos.get(i).getIdEscalamiento());
						break;
					case 1:
						this.actividadSelected.escalamiento2.setIdEscalamiento(escalamientos.get(i).getIdEscalamiento());
						break;
					case 2:
						this.actividadSelected.escalamiento3.setIdEscalamiento(escalamientos.get(i).getIdEscalamiento());
						break;
					default:
	
					}
				}
				logger.info("escalamiento1 id: " + this.actividadSelected.escalamiento1.getIdEscalamiento());
				logger.info("escalamiento2 id: " + this.actividadSelected.escalamiento2.getIdEscalamiento());
				logger.info("escalamiento3 id: " + this.actividadSelected.escalamiento3.getIdEscalamiento());
	
				servicio.modificarEscalamiento(this.actividadSelected.getIdActividad(),
						this.actividadSelected.escalamiento1, this.actividadSelected.escalamiento2,
						this.actividadSelected.escalamiento3);
	
				// EDITAR LAS Observaciones
				logger.debug("Editando el Comentario = ");
				servicio.modificarObservaciones(this.actividadSelected.getIdActividad(),
						actividadSelected.getObservacionActividad());
	
				// EDITAR LOS PROVEEDORES
				if (actividadSelected.getProveedores() != null && !actividadSelected.getProveedores().isEmpty()) {
					logger.debug("EXISTEN PROVEEDORES A MODIFICAR");
					boolean resultado = servicio.modificarVigenciaProveedores(this.actividadSelected.getIdActividad());
					logger.debug("Respuesta del modificarVigenciaProveedores : " + resultado);
					servicio.insertarProveedores(this.actividadSelected.getIdActividad(),
							actividadSelected.getProveedores());
				} else {
					logger.debug(
							"NO EXISTEN PROVEEDORES O SE ELIMINARON LOS QUE EXISTIAN Y HAY QUE SETEAR LA VIGENCIA EN 0 DE LOS YA EXISTENTES");
					boolean resultado = servicio.modificarVigenciaProveedores(this.actividadSelected.getIdActividad());
					logger.debug("Respuesta del modificarVigenciaProveedores : " + resultado);
				}
	
				// EDITAR ADJUNTOS
				if (actividadSelected.getAdjuntos() != null && !actividadSelected.getAdjuntos().isEmpty()) {
					logger.debug("EXISTEN ADJUNTOS A MODIFICAR");
					boolean resultado = servicio.modificarVigenciaAdjuntos(this.actividadSelected.getIdActividad());
					logger.debug("Respuesta del modificarVigenciaAdjuntos : " + resultado);
	
					// SE ELIMINAN ARCHIVOS ADJUNTOS ELIMINADOS DE LA LISTA
					if (!adjuntosBorrados.isEmpty()) {
						for (int x = 0; x < adjuntosBorrados.size(); x++) {
							logger.info("Adjunto a borrar ( " + (x + 1) + "), url es: " + adjuntosBorrados.get(x));
							String urlArchivoBorrar = adjuntosBorrados.get(x);
							File file = new File(urlArchivoBorrar);
	
							if (file.delete()) {
								logger.debug("File deleted successfully url = " + urlArchivoBorrar);
							} else {
								logger.debug("Failed to delete the file url = " + urlArchivoBorrar);
							}
						}
					} else {
						logger.debug("NO EXISTEN ADJUNTOS BORRADOS PARA ELIMINAR");
					}
	
					logger.debug("----------- SE CARGAN LAS IMAGENES -----------");
					if (actividadSelected.getAdjuntos() != null && actividadSelected.getAdjuntos().size() > 0) {
						logger.debug("----------- IF de ADJUNTOS -----------");
						String pathFiles = prop.getProperty("url.files") + this.actividadSelected.getIdActividad();
						for (int i = 0; i < actividadSelected.getAdjuntos().size(); i++) {
							logger.debug("----------- FOR de ADJUNTOS -----------");
							String nombreArchivo = actividadSelected.getAdjuntos().get(i).getNombreAdjunto();
							String[] parts = nombreArchivo.split("\\.");
	
							String path = prop.getProperty("url.files") + this.actividadSelected.getIdActividad() + "\\"
									+ nombreArchivo;
							logger.debug("Path archivo (" + i + "): " + path);
							actividadSelected.getAdjuntos().get(i).setUrlAdjunto(path);
							try {
								File dir = new File(pathFiles);
								File[] files = dir
										.listFiles((dir1, name) -> name.startsWith(parts[0]) && name.endsWith(parts[1]));
	
								if (files.length > 0) {
									logger.debug("Archivo " + nombreArchivo + " ya existe.");
									throw new Exception("Archivo " + nombreArchivo + " ya existe.");
								} else {
									logger.debug("No hay archivo para borrar");
									logger.debug("Antes del New File");
									File file = new File(path);
									logger.debug("Despues del New File");
									UploadedFile input = actividadSelected.getAdjuntos().get(i).getArchivo();
									InputStream inputStream = input.getInputstream();
									logger.debug("Despues del input.getInputstream()");
									OutputStream outputStream = new FileOutputStream(file);
									int read = 0;
									byte[] bytes = new byte[1024];
									while ((read = inputStream.read(bytes)) != -1) {
										outputStream.write(bytes, 0, read);
									}
									outputStream.close();
								}
	
							} catch (Exception e) {
								logger.error("There is a Exception thrown, description: " + e.getMessage());
							}
						}
					} else {
						logger.debug("No hay adjunto para guardar.");
					}
					logger.debug("Se llama servicio de Insertar Adjuntos");
					servicio.insertarAdjuntos(this.actividadSelected.getIdActividad(), actividadSelected.getAdjuntos());
	
				} else {
					logger.debug("NO EXISTEN ADJUNTOS A MODIFICAR O SE ELIMINARON LOS QUE HABIAN");
					boolean resultado = servicio.modificarVigenciaAdjuntos(this.actividadSelected.getIdActividad());
					logger.debug("Respuesta del modificarVigenciaAdjuntos : " + resultado);
					// SE ELIMINAN ARCHIVOS ADJUNTOS ELIMINADOS DE LA LISTA
					if (!adjuntosBorrados.isEmpty()) {
						for (int x = 0; x < adjuntosBorrados.size(); x++) {
							logger.info("Adjunto a borrar ( " + (x + 1) + "), url es: " + adjuntosBorrados.get(x));
							String urlArchivoBorrar = adjuntosBorrados.get(x);
							File file = new File(urlArchivoBorrar);
	
							if (file.delete()) {
								logger.debug("File deleted successfully url = " + urlArchivoBorrar);
							} else {
								logger.debug("Failed to delete the file url = " + urlArchivoBorrar);
							}
						}
					} else {
						logger.debug("NO EXISTEN ADJUNTOS BORRADOS PARA ELIMINAR");
					}
				}
			}else {
				logger.debug("EXISTEN VALORES REPETIDOS");
				isValid = false;
			}
		} catch (Exception e) {
			isValid = false;
			logger.error("Exception : " + e.getMessage());
			context.addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", prop.getProperty("mensaje.error.modificar.actividad")));
		}
		logger.info("IS VALID: "+isValid);
		if(isValid) context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Success",  prop.getProperty("mensaje.modificar.actividad")));
		
	}

	public void cargarProveedores(int valor) {
		// try {
		FacesContext context = FacesContext.getCurrentInstance();
		if (valor == 1) {
			logger.debug("CargarProveedores valor 1");
			ProveedorTO prov = new ProveedorTO();
			if(this.proveedorEditar.getCorreoProveedor().matches("^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$")) {
				
				logger.debug("Valor del nombreProveedorEditar: " + this.proveedorEditar.getNombreProveedor());
				logger.debug("Valor del empresaProveedorEditar: " + this.proveedorEditar.getEmpresaProveedor());
				logger.debug("Valor del cargoProveedorEditar: " + this.proveedorEditar.getCargoProveedor());
				logger.debug("Valor del fonoProveedorEditar: " + this.proveedorEditar.getFonoProveedor());
				logger.debug("Valor del correoProveedorEditar: " + this.proveedorEditar.getCorreoProveedor());
				prov.setNombreProveedor(this.proveedorEditar.getNombreProveedor());
				prov.setEmpresaProveedor(this.proveedorEditar.getEmpresaProveedor());
				prov.setCargoProveedor(this.proveedorEditar.getCargoProveedor());
				prov.setFonoProveedor(this.proveedorEditar.getFonoProveedor());
				prov.setCorreoProveedor(this.proveedorEditar.getCorreoProveedor());

				actividadSelected.getProveedores().add(prov);
				this.setProveedorEditar(new ProveedorTO());
			}else if(this.proveedorEditar.getCorreoProveedor() == null || this.proveedorEditar.getCorreoProveedor().isEmpty()){
				if((this.proveedorEditar.getNombreProveedor() != null && !this.proveedorEditar.getNombreProveedor().isEmpty()) || 
				   (this.proveedorEditar.getEmpresaProveedor() != null && !this.proveedorEditar.getEmpresaProveedor().isEmpty()) ||
				   (this.proveedorEditar.getCargoProveedor() != null && !this.proveedorEditar.getCargoProveedor().isEmpty()) ||
				   (this.proveedorEditar.getFonoProveedor() != null && !this.proveedorEditar.getFonoProveedor().isEmpty())) {
						prov.setNombreProveedor(this.proveedorEditar.getNombreProveedor());
						prov.setEmpresaProveedor(this.proveedorEditar.getEmpresaProveedor());
						prov.setCargoProveedor(this.proveedorEditar.getCargoProveedor());
						prov.setFonoProveedor(this.proveedorEditar.getFonoProveedor());
						prov.setCorreoProveedor(this.proveedorEditar.getCorreoProveedor());
		
						actividadSelected.getProveedores().add(prov);
						this.setProveedorEditar(new ProveedorTO());
				}
			}else{
				context.addMessage(null,
						new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "El formato del email es incorrecto. Favor Revisar"));
			}	
		} else {
			logger.debug("CargarProveedores distinto a 1");
			ProveedorTO prov = new ProveedorTO();
			if(this.proveedor.getCorreoProveedor().matches("^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$")) {
				logger.debug("Valor del nombreProveedor: " + this.proveedor.getNombreProveedor());
				logger.debug("Valor del empresaProveedor: " + this.proveedor.getEmpresaProveedor());
				logger.debug("Valor del cargoProveedor: " + this.proveedor.getCargoProveedor());
				logger.debug("Valor del fonoProveedor: " + this.proveedor.getFonoProveedor());
				logger.debug("Valor del correoProveedor: " + this.proveedor.getCorreoProveedor());
				prov.setNombreProveedor(this.proveedor.getNombreProveedor());
				prov.setEmpresaProveedor(this.proveedor.getEmpresaProveedor());
				prov.setCargoProveedor(this.proveedor.getCargoProveedor());
				prov.setFonoProveedor(this.proveedor.getFonoProveedor());
				prov.setCorreoProveedor(this.proveedor.getCorreoProveedor());
	
				proveedores.add(prov);
	
				actividad.setProveedores(proveedores);
				this.setProveedor(new ProveedorTO());
			}else if(this.proveedor.getCorreoProveedor() == null || this.proveedor.getCorreoProveedor().isEmpty()) {
				if((this.proveedor.getNombreProveedor() != null && !this.proveedor.getNombreProveedor().isEmpty()) || 
				   (this.proveedor.getEmpresaProveedor() != null && !this.proveedor.getEmpresaProveedor().isEmpty()) ||
				   (this.proveedor.getCargoProveedor() != null && !this.proveedor.getCargoProveedor().isEmpty()) ||
				   (this.proveedor.getFonoProveedor() != null && !this.proveedor.getFonoProveedor().isEmpty())) {
						prov.setNombreProveedor(this.proveedor.getNombreProveedor());
						prov.setEmpresaProveedor(this.proveedor.getEmpresaProveedor());
						prov.setCargoProveedor(this.proveedor.getCargoProveedor());
						prov.setFonoProveedor(this.proveedor.getFonoProveedor());
						prov.setCorreoProveedor(this.proveedor.getCorreoProveedor());
			
						proveedores.add(prov);
						this.setProveedor(new ProveedorTO());
						actividad.setProveedores(proveedores);
				}
			}else {
				context.addMessage(null,
						new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "El formato del email es incorrecto. Favor Revisar"));
			}
		}

	}

	public void eliminarProveedores(int valor) {
		// try {
		if (valor == 0) {
			logger.debug("Eliminar Proveedores Agregar");
			int cantidadDatosArreglo = proveedores.size();
			if (cantidadDatosArreglo > 0) {
				logger.debug("Cantidad de valores del arreglo de proveedores: " + cantidadDatosArreglo);
				for (int i = 0; i < cantidadDatosArreglo; i++) {
					logger.debug("Nombre " + i + " proveedor : " + proveedores.get(i).getNombreProveedor());
					logger.debug("Empresa " + i + " proveedor : " + proveedores.get(i).getEmpresaProveedor());
					logger.debug("Cargo " + i + " proveedor : " + proveedores.get(i).getCargoProveedor());
					logger.debug("Fono " + i + " proveedor : " + proveedores.get(i).getFonoProveedor());
					logger.debug("Correo " + i + " proveedor : " + proveedores.get(i).getCorreoProveedor());
				}

				proveedores.remove(cantidadDatosArreglo - 1);
				actividad.setProveedores(proveedores);

			} else {
				logger.debug("Cantidad de valores del arreglo de proveedores: " + proveedores.size()
						+ ". Por lo que no se puede eliminar elemento.");
			}
		} else {
			logger.debug("Eliminar Proveedores Modificar");
			int cantidadDatosArreglo = actividadSelected.getProveedores().size();
			if (cantidadDatosArreglo > 0) {
				logger.debug("Cantidad de valores del arreglo de proveedores: " + cantidadDatosArreglo);
				for (int i = 0; i < cantidadDatosArreglo; i++) {
					logger.debug("Nombre " + i + " proveedor : "
							+ actividadSelected.getProveedores().get(i).getNombreProveedor());
					logger.debug("Empresa " + i + " proveedor : "
							+ actividadSelected.getProveedores().get(i).getEmpresaProveedor());
					logger.debug("Cargo " + i + " proveedor : "
							+ actividadSelected.getProveedores().get(i).getCargoProveedor());
					logger.debug("Fono " + i + " proveedor : "
							+ actividadSelected.getProveedores().get(i).getFonoProveedor());
					logger.debug("Correo " + i + " proveedor : "
							+ actividadSelected.getProveedores().get(i).getCorreoProveedor());
				}
				actividadSelected.getProveedores().remove(cantidadDatosArreglo - 1);
			} else {
				logger.debug("Cantidad de valores del arreglo de proveedores modificar: " + cantidadDatosArreglo
						+ ". Por lo que no se puede eliminar elemento.");
			}
		}
	}

	public void cargarAdjuntos(FileUploadEvent event) {
		FacesContext context = FacesContext.getCurrentInstance();
		logger.debug("Succesful: " + event.getFile().getFileName() + " is uploaded.");
		FacesMessage msg = new FacesMessage("Succesful", event.getFile().getFileName() + " is uploaded.");
		FacesContext.getCurrentInstance().addMessage(null, msg);
		// try {
		logger.debug("Iniciando cargarAdjuntos");
		if (event.getFile() != null) {
			logger.debug("getFile es : " + event.getFile());
			logger.debug("getFileName es: " + event.getFile().getFileName());
			AdjuntoTO adjun = new AdjuntoTO();
			logger.debug("Valor del nombreAdjunto: " + event.getFile().getFileName());
			logger.debug("Valor del descripcionAdjunto: " + event.getFile().getFileName());
			int id = actividad.getAdjuntos() == null ? 0 : actividad.getAdjuntos().size();
			adjun.setIdAdjunto(id);
			adjun.setNombreAdjunto(event.getFile().getFileName());
			adjun.setDescripcionAdjunto(event.getFile().getFileName());
			adjun.setArchivo(event.getFile());
			logger.debug("Try 0");
			try {
				byte[] fileContent = IOUtils.toByteArray(event.getFile().getInputstream());
				String encodedString = Base64.getEncoder().encodeToString(fileContent);
				adjun.setFileString(encodedString);
				adjun.setFileContent(fileContent);
				logger.debug("Try 1");
				String fileName[] = event.getFile().getFileName().split("\\.");
				String tipoArchivo = fileName[fileName.length - 1];
				InputStream targetStream = new ByteArrayInputStream(adjun.getFileContent());
				logger.debug("Try 2");
				logger.debug("idAdjunto: " + adjun.getIdAdjunto());
				logger.debug("NombreAdjunto: " + adjun.getNombreAdjunto());
				logger.debug("DescripcionAdjunto: " + adjun.getDescripcionAdjunto());
				logger.debug("Tipo de Archivo: " + tipoArchivo);
				logger.debug("StreamedContent: " + adjun.getStreamedContent().toString());
				logger.debug("Try 3");
			} catch (Exception e) {
				logger.debug("No se pudo hacer el cast y el seteo del streamContent del adjunto, Exception : "
						+ e.getMessage());
				e.printStackTrace();
			}

			List<AdjuntoTO> listaAdjuntos = new ArrayList<AdjuntoTO>();
			listaAdjuntos = actividad.getAdjuntos();

			if (listaAdjuntos != null && listaAdjuntos.size() > 0) {
				boolean isAttach = false;
				for (int i = 0; i < listaAdjuntos.size(); i++) {
					if (listaAdjuntos.get(i).getNombreAdjunto().equals(adjun.getNombreAdjunto())) {
						isAttach = true;
						break;
					}
				}
				if (!isAttach) {
					logger.debug("Se agrega el elemento a la lista porque no hay iguales");
					actividad.getAdjuntos().add(adjun);

				} else {
					logger.debug("No se agrega el elemento a la lista");
				}
			} else {
				logger.debug("Se agrega el elemento a la lista porque no hay elementos");
				List<AdjuntoTO> listA = new ArrayList<AdjuntoTO>();
				listA.add(adjun);
				actividad.setAdjuntos(listA);
			}
			logger.debug("Antes del for que muestra la lista de adjuntos");

			for (int i = 0; i < actividad.getAdjuntos().size(); i++) {

				logger.debug("NombreAdjunto " + i + " : " + actividad.getAdjuntos().get(i).getNombreAdjunto());
				logger.debug(
						"DescripcionAdjunto " + i + " : " + actividad.getAdjuntos().get(i).getDescripcionAdjunto());
				logger.debug("StreamedContent " + i + " : "
						+ actividad.getAdjuntos().get(i).getStreamedContent().toString());
				logger.debug("ByteArray " + i + " : " + actividad.getAdjuntos().get(i).getFileContent().toString());
			}

		} else {
			logger.debug("getFile es NULL");
			context.addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Error al obtener archivo."));
		}
	}

	public void cargarAdjuntosEditar(FileUploadEvent event) {
		FacesContext context = FacesContext.getCurrentInstance();
		logger.debug("Succesful: " + event.getFile().getFileName() + " is uploaded in Uploading Attaches Edit.");
		FacesMessage msg = new FacesMessage("Succesful", event.getFile().getFileName() + " is uploaded.");
		FacesContext.getCurrentInstance().addMessage(null, msg);
		// try {
		logger.debug("Iniciando cargarAdjuntos editar");
		if (event.getFile() != null) {
			logger.debug("getFile es : " + event.getFile());
			logger.debug("getFileName es: " + event.getFile().getFileName());
			AdjuntoTO adjun = new AdjuntoTO();
			logger.debug("Valor del nombreAdjunto editar : " + event.getFile().getFileName());
			logger.debug("Valor del descripcionAdjunto editar : " + event.getFile().getFileName());

			adjun.setNombreAdjunto(event.getFile().getFileName());
			adjun.setDescripcionAdjunto(event.getFile().getFileName());
			adjun.setArchivo(event.getFile());

			logger.debug("Try 0");
			try {
				byte[] fileContent = IOUtils.toByteArray(event.getFile().getInputstream());
				String encodedString = Base64.getEncoder().encodeToString(fileContent);
				adjun.setFileString(encodedString);
				adjun.setFileContent(fileContent);
				logger.debug("Try 1");
				String fileName[] = event.getFile().getFileName().split("\\.");
				String tipoArchivo = fileName[fileName.length - 1];
				InputStream targetStream = new ByteArrayInputStream(adjun.getFileContent());
				logger.debug("Try 2");
				logger.debug("idAdjunto: " + adjun.getIdAdjunto());
				logger.debug("NombreAdjunto: " + adjun.getNombreAdjunto());
				logger.debug("DescripcionAdjunto: " + adjun.getDescripcionAdjunto());
				logger.debug("Tipo de Archivo: " + tipoArchivo);
				adjun.setStreamedContent(
						new DefaultStreamedContent(event.getFile().getInputstream(), "image/" + tipoArchivo));
				logger.debug("StreamedContent: " + adjun.getStreamedContent().toString());
				logger.debug("Try 3");
			} catch (Exception e) {
				logger.debug("No se pudo hacer el cast y el seteo del streamContent del adjunto, Exception : "
						+ e.getMessage());
				e.printStackTrace();
			}

			List<AdjuntoTO> listaAdjuntos = new ArrayList<AdjuntoTO>();
			listaAdjuntos = actividadSelected.getAdjuntos();

			if (listaAdjuntos != null && listaAdjuntos.size() > 0) {
				boolean isAttach = false;
				for (int i = 0; i < listaAdjuntos.size(); i++) {
					if (listaAdjuntos.get(i).getNombreAdjunto().equals(adjun.getNombreAdjunto())) {
						isAttach = true;
						break;
					}
				}
				if (!isAttach) {
					logger.debug("Se agrega el elemento a la lista porque no hay iguales");
					actividadSelected.getAdjuntos().add(adjun);
				} else {
					logger.debug("No se agrega el elemento a la lista");
				}
			} else {
				listaAdjuntos = new ArrayList<AdjuntoTO>();
				logger.debug("Se agrega el elemento a la lista porque no hay elementos");
				listaAdjuntos.add(adjun);
				actividadSelected.setAdjuntos(listaAdjuntos);
			}

		} else {
			logger.debug("getFile es NULL");
			context.addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Error al obtener archivo."));
		}

	}

	public void cargarAdjuntosEditarBoton() {
		logger.debug("6)Se cargan las imagenes al editar");
		logger.debug("----------- SE CARGAN LAS IMAGENES EDITAR -----------");
		OutputStream outputStream = null;
		if (this.actividadSelected.getAdjuntos() != null && this.actividadSelected.getAdjuntos().size() > 0) {
			List<AdjuntoTO> listaAdjuntosEditar = new ArrayList<AdjuntoTO>();
			listaAdjuntosEditar = this.actividadSelected.getAdjuntos();
			logger.debug("----------- IF de ADJUNTOS AL EDITAR -----------");
			for (int i = 0; i < listaAdjuntosEditar.size(); i++) {
				logger.debug("----------- FOR de ADJUNTOS AL EDITAR -----------");
				String nombreArchivoEditar = listaAdjuntosEditar.get(i).getNombreAdjunto();
				String path = "C:\\Users\\Alumno\\Desktop\\Proyectos\\SEPHUR\\Proyecto\\sistema-gestion-itos\\src\\cl\\valposystems\\sgi\\files\\"
						+ nombreArchivoEditar;
				logger.debug("Path archivo (" + i + "): " + path);
				File file = new File(path);
				UploadedFile input = listaAdjuntosEditar.get(i).getArchivo();
				try {
					InputStream inputStream = input.getInputstream();

					outputStream = new FileOutputStream(file);

					int read = 0;
					byte[] bytes = new byte[1024];
					while ((read = inputStream.read(bytes)) != -1) {
						outputStream.write(bytes, 0, read);
					}
				} catch (Exception e) {
					logger.debug("There is a Exception thrown, description: " + e.getMessage());
				}
			}
		} else {
			logger.debug("No hay adjunto para guardar.");
		}
	}

	public void eliminarAdjuntos(int valor) {
		// try {
		if (valor == 0) {
			logger.debug("Eliminar adjuntos");
			int cantidadDatosArregloAdjuntos = actividad.getAdjuntos().size();
			if (cantidadDatosArregloAdjuntos > 0) {
				logger.debug("Cantidad de valores del arreglo de adjuntos: " + cantidadDatosArregloAdjuntos);
				for (int i = 0; i < cantidadDatosArregloAdjuntos; i++) {
					logger.debug("Archivo " + i + " adjuntos : " + actividad.getAdjuntos().get(i).getNombreAdjunto());
					logger.debug("Descripcion " + i + " adjuntos : "
							+ actividad.getAdjuntos().get(i).getDescripcionAdjunto());
				}
				actividad.getAdjuntos().remove(cantidadDatosArregloAdjuntos - 1);
			} else {
				logger.debug("Cantidad de valores del arreglo de adjuntos: " + actividad.getAdjuntos().size()
						+ ". Por lo que no se puede eliminar elemento.");
			}
		} else {
			logger.debug("Eliminar adjuntos Editar");
			int cantidadDatosArregloAdjuntos = actividadSelected.getAdjuntos().size();
			if (cantidadDatosArregloAdjuntos > 0) {
				logger.debug("Cantidad de valores del arreglo de adjuntos: " + cantidadDatosArregloAdjuntos);
				for (int i = 0; i < cantidadDatosArregloAdjuntos; i++) {
					logger.debug("Archivo " + i + " adjuntos : "
							+ actividadSelected.getAdjuntos().get(i).getNombreAdjunto());
					logger.debug("Descripcion " + i + " adjuntos : "
							+ actividadSelected.getAdjuntos().get(i).getDescripcionAdjunto());
				}
				if (actividadSelected.getAdjuntos().get(cantidadDatosArregloAdjuntos - 1).getUrlAdjunto() != null) {
					logger.debug("Se adjunta la url del archivo eliminado a la lista: "
							+ actividadSelected.getAdjuntos().get(cantidadDatosArregloAdjuntos - 1).getUrlAdjunto());
					adjuntosBorrados
							.add(actividadSelected.getAdjuntos().get(cantidadDatosArregloAdjuntos - 1).getUrlAdjunto());
				} else {
					logger.debug("No se agrega a la lista de url de archivos borrados porque no hay URL que borrar.");
				}
				actividadSelected.getAdjuntos().remove(cantidadDatosArregloAdjuntos - 1);
			} else {
				logger.debug("Cantidad de valores del arreglo de adjuntos: " + adjuntos.size()
						+ ". Por lo que no se puede eliminar elemento.");
			}
		}
	}

	public void cargarInfoActividad(ActividadTO actividad) {
		FacesContext context = FacesContext.getCurrentInstance();
		List<EscalamientoTO> list = new ArrayList<EscalamientoTO>();
		List<ProveedorTO> listProv = new ArrayList<ProveedorTO>();
		List<AdjuntoTO> listadjun = new ArrayList<AdjuntoTO>();
		logger.info("actividadSelected idActividad al cargarInfoActividad = " + actividad.getIdActividad());
		try {
			actividadSelected = servicio.buscarDatosActividad(actividad);
			// 1) Se genera la búsqueda de la información de la actividad
			logger.info("Inicio Seteo del actividadMostrar2");
			logger.info("Usuario PM : "+actividadSelected.getUsuarioPMSelected());
			this.actividadMostrar2.setDataUsuario(genericMBean.obtenerDataUsuario(actividadSelected.getUsuarioPMSelected()));
			this.actividadMostrar2.setDataUsuarioSuperv(
					genericMBean.obtenerDataUsuarioSuperv(actividadSelected.getUsuarioSupervisorSelected()));
			logger.info("Email DataUsuario: "+this.actividadMostrar2.getDataUsuario().getEmailUsuario());
			logger.info("Email DataUsuarioSuperv: "+this.actividadMostrar2.getDataUsuarioSuperv().getEmailUsuario());
			logger.info("Fin Seteo del actividadMostrar2");
			actividadSelected = servicio.buscarDatosActividad(actividad);
			if(actividadSelected==null) logger.error("actividadSelected es null");
			this.actividadSelected.setStatus(genericMBean.obtenerStatus());
			this.actividadSelected.setIdActividad(actividadSelected.getIdActividad());
			this.actividadSelected.setStatusSelected(actividadSelected.getIdStatus());
			if(actividadSelected.getIdStatus() != 3) {
				this.actividadSelected.setStatusSelectedEspecifico(actividadSelected.getIdStatusEspecifico());
			}
			this.actividadSelected.setProyectoSelected(actividadSelected.getIdProyecto());
			this.actividadSelected.setSupervisionSelected(actividadSelected.getIdSupervision());
			this.actividadSelected.setSitioSelected(actividadSelected.getIdSitio());
			this.actividadSelected.setUsuarioPMSelected(actividadSelected.getUsuarioPMSelected());
			this.actividadSelected.setUsuarioSupervisorSelected(actividadSelected.getUsuarioSupervisorSelected());
			list = genericMBean.buscarEscalamiento(actividadSelected.getIdActividad());
			int i = 1;
			
			if (actividadSelected.getIdStatus() == 1) {
				logger.debug("Actividad Exitosa");
				List<StatusEspecificosTO> listx = genericMBean.obtenerStatusEspecifico(1);
				for (int x = 0; x < listx.size(); x++) {
					StatusEspecificosTO statusTO = new StatusEspecificosTO();
					statusTO = listx.get(x);
					logger.info("StatusEspecificosTO id = " + statusTO.getIdStatusEspecificos());
					logger.info("StatusEspecificosTO descripcion = " + statusTO.getStatusEspecificos());
				}
				logger.info("antes del setStatusEspecifico 1");
				this.actividadSelected.setStatusEspecificos(listx);
				this.habilitar = false;
			} else if (actividadSelected.getIdStatus() == 2) {
				logger.debug("Actividad No Exitosa");
				List<StatusEspecificosTO> list2 = genericMBean.obtenerStatusEspecifico(2);
				for (int x = 0; x < list2.size(); x++) {
					StatusEspecificosTO statusTO = new StatusEspecificosTO();
					statusTO = list2.get(x);
					logger.info("statusTO id = " + statusTO.getIdStatusEspecificos());
					logger.info("statusTO descripcion = " + statusTO.getStatusEspecificos());
				}
				logger.info("antes del setStatusEspecifico 2");
				this.actividadSelected.setStatusEspecificos(list2);
				this.habilitar = false;
			} else {
				logger.debug("Actividad Cancelada");
				List<StatusEspecificosTO> list3 = genericMBean.obtenerStatusEspecifico(0);
				for (int x = 0; x < list3.size(); x++) {
					StatusEspecificosTO statusTO = new StatusEspecificosTO();
					statusTO = list3.get(x);
					logger.info("statusTO id = " + statusTO.getIdStatusEspecificos());
					logger.info("statusTO descripcion = " + statusTO.getStatusEspecificos());
				}
				logger.info("antes del setStatusEspecifico 0");
				this.actividadSelected.setStatusEspecificos(list3);
				this.habilitar = true;
			}
			
			if (!list.isEmpty()) {
				logger.debug("Lista de escalamiento no es vacia");
				for (EscalamientoTO escalamiento : list) {
					if (i == 1) {
						logger.debug("Escalamiento getNivel 1 = " + escalamiento.getNivel());
						this.actividadSelected.setEscalamiento1(escalamiento);
					} else if (i == 2) {
						logger.debug("Escalamiento getNivel 2 = " + escalamiento.getNivel());
						this.actividadSelected.setEscalamiento2(escalamiento);
					} else {
						logger.debug("Escalamiento getNivel 3 = " + escalamiento.getNivel());
						this.actividadSelected.setEscalamiento3(escalamiento);
					}
					i++;
				}
			} else {
				logger.debug("Lista de escalamiento es vacia");
			}
			// BUSCAR PROVEEDORES Y CARGARLO EN LA TABLA
			String datoTabla = "";
			logger.debug("antes de genericMBean.buscarProveedores");
			listProv = genericMBean.buscarProveedores(actividad.getIdActividad());
			logger.debug("despues de genericMBean.buscarProveedores");
			i = 1;

			if (listProv.size() > 0) {
				logger.debug("hay datos en la lista de proveedores");
				for (ProveedorTO proveedor : listProv) {
					logger.debug("Proveedor " + i + " : " + proveedor.getNombreProveedor());
					datoTabla = proveedor.getNombreProveedor();
					i++;
				}
			} else {
				logger.debug("no hay datos en la lista de proveedores");
			}
			actividadSelected.setProveedores(listProv);
			logger.debug("dato Tabla = " + datoTabla);
			// CARGAR ADJUNTOS EN LA TABLA
			logger.debug("antes de genericMBean.buscarProveedores");
			listadjun = genericMBean.buscarAdjuntos(actividad.getIdActividad());
			logger.debug("despues de genericMBean.buscarProveedores");
			i = 1;

			if (listadjun.size() > 0) {
				logger.debug("hay datos en la lista de adjuntos");
				for (AdjuntoTO adjunto : listadjun) {
					logger.debug("getNombreAdjunto " + i + " : " + adjunto.getNombreAdjunto());
					File targetFile = new File(adjunto.getUrlAdjunto());

					i++;
				}
			} else {
				logger.debug("no hay datos en la lista de adjuntos");
			}

			actividadSelected.setAdjuntos(listadjun);
		} catch (ServicioNoDisponibleException e) {
			logger.info("FALLO EN cargarInfoActividad : " + e.getMessage());
			context.addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "No logra obtener a los sostenedores."));
		}
	}

	public List<ActividadTO> getListaActividades() {
		return listaTO;
	}

	public void setListaActividades(List<ActividadTO> actividadTO) {
		this.listaTO = actividadTO;
	}

	public ActividadTO getActividad() {
		return actividad;
	}

	public void setActividad(ActividadTO actividad) {
		this.actividad = actividad;
	}

	public ActividadTO getActividadSelected() {
		return actividadSelected;
	}

	public void setActividadSelected(ActividadTO actividadSelected) {
		this.actividadSelected = actividadSelected;
	}

	public ProveedorTO getProveedorEditar() {
		return proveedorEditar;
	}

	public void setProveedorEditar(ProveedorTO proveedorEditar) {
		this.proveedorEditar = proveedorEditar;
	}

	public UploadedFile getFile() {
		return file;
	}

	public void setFile(UploadedFile file) {
		this.file = file;
	}

	public ProveedorTO getProveedor() {
		return proveedor;
	}

	public void setProveedor(ProveedorTO proveedor) {
		this.proveedor = proveedor;
	}

	public AdjuntoTO getAdjunto() {
		return adjunto;
	}

	public void setAdjunto(AdjuntoTO adjunto) {
		this.adjunto = adjunto;
	}

	public void upload() {
		if (file != null) {
			FacesMessage message = new FacesMessage("Succesful", file.getFileName() + " is uploaded.");
			FacesContext.getCurrentInstance().addMessage(null, message);
			logger.debug("Succesful: " + file.getFileName() + " is uploaded.");
		}
	}

	public void handleFileUpload(FileUploadEvent event) {
		logger.debug("Succesful: " + event.getFile().getFileName() + " is uploaded.");
		FacesMessage msg = new FacesMessage("Succesful", event.getFile().getFileName() + " is uploaded.");
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}

	public void onRowEdit(RowEditEvent event) {
		FacesMessage msg = new FacesMessage("Car Edited", ((AdjuntoTO) event.getObject()).getNombreAdjunto());
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}

	public void onRowCancel(RowEditEvent event) {
		FacesMessage msg = new FacesMessage("Edit Cancelled", ((AdjuntoTO) event.getObject()).getNombreAdjunto());
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}

	public void onCellEdit(CellEditEvent event) {
		Object oldValue = event.getOldValue();
		Object newValue = event.getNewValue();

		if (newValue != null && !newValue.equals(oldValue)) {
			FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Cell Changed",
					"Old: " + oldValue + ", New:" + newValue);
			FacesContext.getCurrentInstance().addMessage(null, msg);
		}
	}

	public boolean isHabilitar() {
		return habilitar;
	}

	public void setHabilitar(boolean habilitar) {
		this.habilitar = habilitar;
	}

	public ActividadTO getActividadEditar() {
		return actividadEditar;
	}

	public void setActividadEditar(ActividadTO actividadEditar) {
		this.actividadEditar = actividadEditar;
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

	public AdjuntoTO getAdjuntoSelected() {
		return adjuntoSelected;
	}

	public void setAdjuntoSelected(AdjuntoTO adjuntoSelected) {
		this.adjuntoSelected = adjuntoSelected;
	}

	public ActividadTO getActividadMostrar() {
		return actividadMostrar;
	}

	public void setActividadMostrar(ActividadTO actividadMostrar) {
		this.actividadMostrar = actividadMostrar;
	}

	public ActividadTO getActividadMostrar2() {
		return actividadMostrar2;
	}


	public void setActividadMostrar2(ActividadTO actividadMostrar2) {
		this.actividadMostrar2 = actividadMostrar2;
	}
	

	public void cambioItemStatus(int valor) {
		if (valor == 0) {
			logger.debug("El valor es: " + this.actividad.getStatusSelected());
			if (this.actividad.getStatusSelected() == 1) {
				logger.debug("Actividad Exitosa");
				List<StatusEspecificosTO> list = genericMBean.obtenerStatusEspecifico(1);
				for (int i = 0; i < list.size(); i++) {
					StatusEspecificosTO statusTO = new StatusEspecificosTO();
					statusTO = list.get(i);
					logger.info("StatusEspecificosTO id = " + statusTO.getIdStatusEspecificos());
					logger.info("StatusEspecificosTO descripcion = " + statusTO.getStatusEspecificos());
				}
				logger.info("antes del setStatusEspecifico 1");
				this.actividad.setStatusEspecificos(list);
				this.habilitar = false;
			} else if (this.actividad.getStatusSelected() == 2) {
				logger.debug("Actividad No Exitosa");
				List<StatusEspecificosTO> list2 = genericMBean.obtenerStatusEspecifico(2);
				for (int i = 0; i < list2.size(); i++) {
					StatusEspecificosTO statusTO = new StatusEspecificosTO();
					statusTO = list2.get(i);
					logger.info("statusTO id = " + statusTO.getIdStatusEspecificos());
					logger.info("statusTO descripcion = " + statusTO.getStatusEspecificos());
				}
				logger.info("antes del setStatusEspecifico 2");
				this.actividad.setStatusEspecificos(list2);
				this.habilitar = false;
			} else {
				logger.debug("Actividad Cancelada");
				List<StatusEspecificosTO> list = genericMBean.obtenerStatusEspecifico(0);
				for (int i = 0; i < list.size(); i++) {
					StatusEspecificosTO statusTO = new StatusEspecificosTO();
					statusTO = list.get(i);
					logger.info("statusTO id = " + statusTO.getIdStatusEspecificos());
					logger.info("statusTO descripcion = " + statusTO.getStatusEspecificos());
				}
				logger.info("antes del setStatusEspecifico 0");
				this.actividad.setStatusEspecificos(list);
				this.habilitar = true;
			}
		} 
		else {
			logger.debug("El valor es: " + this.actividadSelected.getStatusSelected());
			if (this.actividadSelected.getStatusSelected() == 1) {
				logger.debug("Actividad Exitosa");
				List<StatusEspecificosTO> list = genericMBean.obtenerStatusEspecifico(1);
				for (int i = 0; i < list.size(); i++) {
					StatusEspecificosTO statusTO = new StatusEspecificosTO();
					statusTO = list.get(i);
					logger.info("StatusEspecificosTO id = " + statusTO.getIdStatusEspecificos());
					logger.info("StatusEspecificosTO descripcion = " + statusTO.getStatusEspecificos());
				}
				logger.info("antes del setStatusEspecifico 1");
				this.actividadSelected.setStatusEspecificos(list);
				this.habilitar = false;
			} else if (this.actividadSelected.getStatusSelected() == 2) {
				logger.debug("Actividad No Exitosa");
				List<StatusEspecificosTO> list2 = genericMBean.obtenerStatusEspecifico(2);
				for (int i = 0; i < list2.size(); i++) {
					StatusEspecificosTO statusTO = new StatusEspecificosTO();
					statusTO = list2.get(i);
					logger.info("statusTO id = " + statusTO.getIdStatusEspecificos());
					logger.info("statusTO descripcion = " + statusTO.getStatusEspecificos());
				}
				logger.info("antes del setStatusEspecifico 2");
				this.actividadSelected.setStatusEspecificos(list2);
				this.habilitar = false;
			} else {
				logger.debug("Actividad Cancelada");
				List<StatusEspecificosTO> list = genericMBean.obtenerStatusEspecifico(0);
				for (int i = 0; i < list.size(); i++) {
					StatusEspecificosTO statusTO = new StatusEspecificosTO();
					statusTO = list.get(i);
					logger.info("statusTO id = " + statusTO.getIdStatusEspecificos());
					logger.info("statusTO descripcion = " + statusTO.getStatusEspecificos());
				}
				logger.info("antes del setStatusEspecifico 0");
				this.actividadSelected.setStatusEspecificos(list);
				this.habilitar = true;
			}
		}
	}
	
	public void setFinalDate(SelectEvent event) {
	    SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
	    logger.info("Selected Date: "+format.format(event.getObject()));
	    this.actividad.setFechaTermino(null);
	}
	
	public void setFinalDateEdit(SelectEvent event) {
	    SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
	    logger.info("Selected Date: "+format.format(event.getObject()));
	    this.actividadSelected.setFechaTermino(null);
	}

	/**
	 * This method uses java.io.FileInputStream to read file content into a byte
	 * array
	 * 
	 * @param file
	 * @return
	 */
	private static byte[] readFileToByteArray(File file) {
		FileInputStream fis = null;
		// Creating a byte array using the length of the file
		// file.length returns long which is cast to int
		byte[] bArray = new byte[(int) file.length()];
		try {
			fis = new FileInputStream(file);
			fis.read(bArray);
			fis.close();

		} catch (IOException ioExp) {
			ioExp.printStackTrace();
		}
		return bArray;
	}

}
