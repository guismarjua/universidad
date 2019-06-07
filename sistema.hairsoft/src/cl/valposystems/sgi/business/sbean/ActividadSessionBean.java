package cl.valposystems.sgi.business.sbean;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;

import org.apache.log4j.Logger;

import cl.valposystems.sgi.dao.impl.DAOActividad;
import cl.valposystems.sgi.exception.ServicioNoDisponibleException;
import cl.valposystems.sgi.to.ActividadTO;
import cl.valposystems.sgi.to.AdjuntoTO;
import cl.valposystems.sgi.to.EscalamientoTO;
import cl.valposystems.sgi.to.ProveedorTO;

@Stateless
@LocalBean
@TransactionManagement(TransactionManagementType.BEAN)
public class ActividadSessionBean {
	final static Logger logger = Logger.getLogger(ActividadSessionBean.class);

	private DAOActividad dao;

	@PostConstruct
	public void init() {
		try {
			dao = new DAOActividad();
		} catch (ServicioNoDisponibleException e) {
			System.err.println(e);
		}
	}

	public ActividadSessionBean() {

	}

	public List<ActividadTO> obtenerActividades() throws ServicioNoDisponibleException {
		List<ActividadTO> listaActividadesTO = null;
		try {
			listaActividadesTO = dao.findAllActividades();
			if(!listaActividadesTO.isEmpty()) {
				for(ActividadTO to : listaActividadesTO) {
					String date = to.getFechaCreacion();
					Date date1= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(date); 
					DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
					String strDate = dateFormat.format(date1); 
					to.setFechaCreacion(strDate);
					if(to.getFechaModificacion() != null ) {
						String date2 = to.getFechaModificacion();
						Date date3= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(date2); 
						DateFormat dateFormat1 = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
						String strDate1 = dateFormat1.format(date3); 
						to.setFechaModificacion(strDate1);
					}
				}
			}
		} catch (ServicioNoDisponibleException | ParseException e) {
			throw new ServicioNoDisponibleException(e);
		}
		return listaActividadesTO;
	}
	
	public List<ActividadTO> obtenerActividadesITO(Integer idUsuario) throws ServicioNoDisponibleException {
		List<ActividadTO> listaActividadesTO = null;
		try {
			listaActividadesTO = dao.findAllActividadesIto(idUsuario);
			if(!listaActividadesTO.isEmpty()) {
				for(ActividadTO to : listaActividadesTO) {
					String date = to.getFechaCreacion();
					Date date1= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(date); 
					DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
					String strDate = dateFormat.format(date1); 
					to.setFechaCreacion(strDate);
					if(to.getFechaModificacion() != null ) {
						String date2 = to.getFechaModificacion();
						Date date3= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(date2); 
						DateFormat dateFormat1 = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
						String strDate1 = dateFormat1.format(date3); 
						to.setFechaModificacion(strDate1);
					}
				}
			}
		} catch (ServicioNoDisponibleException | ParseException e) {
			throw new ServicioNoDisponibleException(e);
		}
		return listaActividadesTO;
	}

	public boolean insertarActividad(ActividadTO actividad) throws ServicioNoDisponibleException {
		boolean result = false;
		try {
			logger.debug("insertarActividad -> antes de dao.insertActividad");
			dao.insertActividad(actividad);
			logger.debug("insertarActividad -> despues de dao.insertActividad");
		} catch (ServicioNoDisponibleException e) {
			logger.error("insertarActividad ERROR : "+e.getMessage());
			throw new ServicioNoDisponibleException(e);
		}
		return result;
	}

	public boolean editarActividad(ActividadTO actividad) throws ServicioNoDisponibleException {
		boolean result = false;
		try {
			logger.debug("editarActividad -> antes de dao.editarActividad");
			dao.editActividad(actividad);
			logger.debug("editarActividad -> despues de dao.editarActividad");
		} catch (ServicioNoDisponibleException e) {
			logger.error("editarActividad ERROR : "+e.getMessage());
			throw new ServicioNoDisponibleException(e);
		}
		return result;
	}
	
	public Integer obtenerIdActividad(ActividadTO actividad) throws ServicioNoDisponibleException {
		Integer result = 0;
		try {
			result = dao.findIdActividad(actividad);
		} catch (ServicioNoDisponibleException e) {
			throw new ServicioNoDisponibleException(e);
		}
		return result;
	}

	public boolean insertarPersonalPM(Integer idActividad, Integer idUsuario) throws ServicioNoDisponibleException {
		boolean result = false;
		try {
			Map<String, Object> parametros = new HashMap<String, Object>();
			parametros.put("idActividad", idActividad);
			parametros.put("idUsuario", idUsuario);
			dao.insertPersonalPM(parametros);
		} catch (ServicioNoDisponibleException e) {
			throw new ServicioNoDisponibleException(e);
		}
		return result;
	}
	
	public boolean modificarPersonalPM(Integer idActividad, Integer idUsuario) throws ServicioNoDisponibleException {
		boolean result = false;
		try {
			logger.debug("El valor de idActividad es: "+idActividad);
			Map<String, Object> parametros = new HashMap<String, Object>();
			parametros.put("idActividad", idActividad);
			parametros.put("idUsuario", idUsuario);
			dao.modificarPersonalPM(parametros);
		} catch (ServicioNoDisponibleException e) {
			throw new ServicioNoDisponibleException(e);
		}
		return result;
	}

	public boolean insertarPersonalSupervisor(Integer idActividad, Integer idUsuario)
			throws ServicioNoDisponibleException {
		boolean result = false;
		try {
			Map<String, Object> parametros = new HashMap<String, Object>();
			parametros.put("idActividad", idActividad);
			parametros.put("idUsuario", idUsuario);
			dao.insertPersonalSupervisor(parametros);
		} catch (ServicioNoDisponibleException e) {
			throw new ServicioNoDisponibleException(e);
		}
		return result;
	}
	
	public boolean modificarPersonalSupervisor(Integer idActividad, Integer idUsuario)
			throws ServicioNoDisponibleException {
		boolean result = false;
		try {
			Map<String, Object> parametros = new HashMap<String, Object>();
			parametros.put("idActividad", idActividad);
			parametros.put("idUsuario", idUsuario);
			dao.modificarPersonalSupervisor(parametros);
		} catch (ServicioNoDisponibleException e) {
			throw new ServicioNoDisponibleException(e);
		}
		return result;
	}

	public boolean insertarEscalamiento(Integer identificadorActividad, EscalamientoTO escalamiento1,
			EscalamientoTO escalamiento2, EscalamientoTO escalamiento3) throws ServicioNoDisponibleException {
		boolean result = false;
		try {
			List<Map<String, Object>> parametros = new ArrayList<Map<String, Object>>();
			Map<String, Object> parametros1 = new HashMap<String, Object>();
			Map<String, Object> parametros2 = new HashMap<String, Object>();
			Map<String, Object> parametros3 = new HashMap<String, Object>();
			parametros1.put("idActividad", identificadorActividad);
			parametros1.put("nivel", escalamiento1.getNivel());
			parametros1.put("nombre", escalamiento1.getNombre());
			parametros1.put("telefono", escalamiento1.getTelefono());
			parametros1.put("correo", escalamiento1.getCorreo());
			parametros2.put("idActividad", identificadorActividad);
			parametros2.put("nivel", escalamiento2.getNivel());
			parametros2.put("nombre", escalamiento2.getNombre());
			parametros2.put("telefono", escalamiento2.getTelefono());
			parametros2.put("correo", escalamiento2.getCorreo());			
			parametros3.put("idActividad", identificadorActividad);
			parametros3.put("nivel", escalamiento3.getNivel());
			parametros3.put("nombre", escalamiento3.getNombre());
			parametros3.put("telefono", escalamiento3.getTelefono());
			parametros3.put("correo", escalamiento3.getCorreo());			
			parametros.add(parametros1);
			parametros.add(parametros2);
			parametros.add(parametros3);
			for (Map<String, Object> param : parametros) {
				dao.insertEscalamiento(param);
			}

		} catch (ServicioNoDisponibleException e) {
			throw new ServicioNoDisponibleException(e);
		}
		return result;
	}

	public boolean insertarProveedores(Integer identificadorActividad, List<ProveedorTO> proveedores)
			throws ServicioNoDisponibleException {
		boolean result = false;
		try {
			if (proveedores.size() > 0) {
				List<Map<String, Object>> parametros = new ArrayList<Map<String, Object>>();
				for(int i = 0;i<proveedores.size();i++) {
				Map<String, Object> parametro = new HashMap<String, Object>();
				
				parametro.put("idActividad", identificadorActividad);
				parametro.put("nombreProveedor", proveedores.get(i).getNombreProveedor());
				parametro.put("empresaProveedor", proveedores.get(i).getEmpresaProveedor());
				parametro.put("cargoProveedor", proveedores.get(i).getCargoProveedor());
				parametro.put("fonoProveedor", proveedores.get(i).getFonoProveedor());
				parametro.put("correoProveedor", proveedores.get(i).getCorreoProveedor());
				parametro.put("vigencia", 1);
				
				parametros.add(parametro);
				}
				for (Map<String, Object> param : parametros) {
					dao.insertProveedores(param);
				}
			}
			else {
				System.out.println("No hay proveedores para hacer insercion.");
			}
		} catch (ServicioNoDisponibleException e) {
			throw new ServicioNoDisponibleException(e);
		}
		return result;
	}

	public boolean insertarObservaciones(Integer idActividad, String observaciones)
			throws ServicioNoDisponibleException {
		boolean result = false;
		try {
			Map<String, Object> parametros = new HashMap<String, Object>();
			parametros.put("idActividad", idActividad);
			parametros.put("observaciones", observaciones);
			dao.insertObservaciones(parametros);
		} catch (ServicioNoDisponibleException e) {
			throw new ServicioNoDisponibleException(e);
		}
		return result;

	}

	public boolean insertarAdjuntos(Integer identificadorActividad, List<AdjuntoTO> adjuntos) throws ServicioNoDisponibleException {
		boolean result = false;
		try {
			if (adjuntos.size() > 0) {
				List<Map<String, Object>> parametros = new ArrayList<Map<String, Object>>();
				for(int i = 0;i<adjuntos.size();i++) {
				Map<String, Object> parametro = new HashMap<String, Object>();
				
				parametro.put("idActividad", identificadorActividad);
				parametro.put("nombreAdjunto", adjuntos.get(i).getNombreAdjunto());
				parametro.put("descripcionAdjunto", adjuntos.get(i).getDescripcionAdjunto());
				parametro.put("urlAdjunto", adjuntos.get(i).getUrlAdjunto());
				parametro.put("vigencia", 1);
				
				parametros.add(parametro);
				}
				for (Map<String, Object> param : parametros) {
					dao.insertAdjuntos(param);
				}
			}
			else {
				System.out.println("No hay adjuntos para hacer insercion.");
			}
		} catch (ServicioNoDisponibleException e) {
			throw new ServicioNoDisponibleException(e);
		}
		return result;
	}
	
	public ActividadTO buscarDatosActividad(ActividadTO actividad) throws ServicioNoDisponibleException {
		ActividadTO result = new ActividadTO();
		try {
			result = dao.findDataActividad(actividad);
		} catch (ServicioNoDisponibleException e) {
			throw new ServicioNoDisponibleException(e);
		}
		return result;
	}
	
	public boolean modificarVigenciaProveedores(int idActividad) throws ServicioNoDisponibleException {
		boolean result = true;
		try {
			Map<String, Object> parametro = new HashMap<String, Object>();
			
			parametro.put("idActividad", idActividad);
			parametro.put("vigencia", 0);
			
			result = dao.modificarVigenciaProveedores(parametro);
		} catch (ServicioNoDisponibleException e) {
			result = false;
			throw new ServicioNoDisponibleException(e);
		}
		return result;
	}
	
	public boolean modificarVigenciaAdjuntos(int idActividad) throws ServicioNoDisponibleException {
		boolean result = true;
		try {
			Map<String, Object> parametro = new HashMap<String, Object>();
			
			parametro.put("idActividad", idActividad);
			parametro.put("vigencia", 0);
			
			result = dao.modificarVigenciaAdjuntos(parametro);
		} catch (ServicioNoDisponibleException e) {
			result = false;
			throw new ServicioNoDisponibleException(e);
		}
		return result;
	}
	
	public boolean modificarObservaciones(Integer idActividad, String observaciones)
			throws ServicioNoDisponibleException {
		boolean result = false;
		try {
			Map<String, Object> parametros = new HashMap<String, Object>();
			parametros.put("idActividad", idActividad);
			parametros.put("observaciones", observaciones);
			dao.modificarObservaciones(parametros);
		} catch (ServicioNoDisponibleException e) {
			throw new ServicioNoDisponibleException(e);
		}
		return result;

	}
	
	public boolean modificarEscalamiento(Integer identificadorActividad, EscalamientoTO escalamiento1,
			EscalamientoTO escalamiento2, EscalamientoTO escalamiento3) throws ServicioNoDisponibleException {
		boolean result = false;
		try {
			List<Map<String, Object>> parametros = new ArrayList<Map<String, Object>>();
			Map<String, Object> parametros1 = new HashMap<String, Object>();
			Map<String, Object> parametros2 = new HashMap<String, Object>();
			Map<String, Object> parametros3 = new HashMap<String, Object>();
			parametros1.put("idEscalamiento", escalamiento1.getIdEscalamiento());
			parametros1.put("idActividad", identificadorActividad);
			parametros1.put("nivel", escalamiento1.getNivel());
			parametros1.put("nombre", escalamiento1.getNombre());
			parametros1.put("telefono", escalamiento1.getTelefono());
			parametros1.put("correo", escalamiento1.getCorreo());
			parametros2.put("idEscalamiento", escalamiento2.getIdEscalamiento());
			parametros2.put("idActividad", identificadorActividad);
			parametros2.put("nivel", escalamiento2.getNivel());
			parametros2.put("nombre", escalamiento2.getNombre());
			parametros2.put("telefono", escalamiento2.getTelefono());
			parametros2.put("correo", escalamiento2.getCorreo());		
			parametros3.put("idEscalamiento", escalamiento3.getIdEscalamiento());
			parametros3.put("idActividad", identificadorActividad);
			parametros3.put("nivel", escalamiento3.getNivel());
			parametros3.put("nombre", escalamiento3.getNombre());
			parametros3.put("telefono", escalamiento3.getTelefono());
			parametros3.put("correo", escalamiento3.getCorreo());			
			parametros.add(parametros1);
			parametros.add(parametros2);
			parametros.add(parametros3);
			for (Map<String, Object> param : parametros) {
				dao.modificarEscalamiento(param);
			}

		} catch (ServicioNoDisponibleException e) {
			throw new ServicioNoDisponibleException(e);
		}
		return result;
	}
	
	public List<EscalamientoTO> buscarIdEscalamiento(Integer identificadorActividad) throws ServicioNoDisponibleException {
		List<EscalamientoTO> result = new ArrayList<EscalamientoTO>();
		try {
			result = dao.buscarIdEscalamiento(identificadorActividad);
		} catch (ServicioNoDisponibleException e) {
			throw new ServicioNoDisponibleException(e);
		}
		return result;
	}
	
		public List<ActividadTO> obtenerActividadRango(ActividadTO to) throws ServicioNoDisponibleException {
		List<ActividadTO> listaActividadRangoTO = null;
		try {
			listaActividadRangoTO = dao.findAllActividadRango(to);
		} catch (ServicioNoDisponibleException e) {
			throw new ServicioNoDisponibleException(e);
		}
		return listaActividadRangoTO;
	}
	

}
