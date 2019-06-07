package cl.valposystems.sgi.business.sbean;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import org.apache.log4j.Logger;

import cl.valposystems.sgi.dao.impl.DAOGeneric;
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

@Stateless
@LocalBean
@TransactionManagement(TransactionManagementType.BEAN)

public class GenericSessionBean {
	final static Logger logger = Logger.getLogger(GenericSessionBean.class);

	private DAOGeneric dao; 
	
	@PostConstruct
	public void init() {
		try {
			dao = new DAOGeneric();
		} catch (ServicioNoDisponibleException e) {
			System.err.println(e);
		}
	
	}
	
	public GenericSessionBean() {}
	
	public List<RolTO> findAllRoles() throws ServicioNoDisponibleException {
		List<RolTO> listaRolesTO = null;
		try {
			listaRolesTO = dao.findAllRoles();
			
		} catch (ServicioNoDisponibleException e) {
			throw new ServicioNoDisponibleException(e);
		}
		return listaRolesTO;
	}
	
	public List<RegionTO> findAllRegiones() throws ServicioNoDisponibleException {
		List<RegionTO> listaRegionesTO = null;
		try {
			listaRegionesTO = dao.findAllRegiones();
			
		} catch (ServicioNoDisponibleException e) {
			throw new ServicioNoDisponibleException(e);
		}
		return listaRegionesTO;
	}
	
	public List<ComunaTO> findAllComunas(Integer id) throws ServicioNoDisponibleException {
		List<ComunaTO> listaComunasTO = null;
		try {
			listaComunasTO = dao.findAllComunas(id);
			
		} catch (ServicioNoDisponibleException e) {
			throw new ServicioNoDisponibleException(e);
		}
		return listaComunasTO;
	}
	
	public List<ComunaTO> preloadComunas(Integer id) throws ServicioNoDisponibleException {
		List<ComunaTO> listaComunasTO = null;
		try {
			listaComunasTO = dao.preloadComunas(id);
			
		} catch (ServicioNoDisponibleException e) {
			throw new ServicioNoDisponibleException(e);
		}
		return listaComunasTO;
	}
	
	public List<ClasificacionTO> findAllClasificaciones() throws ServicioNoDisponibleException {
		List<ClasificacionTO> listaClasificacionesTO = null;
		try {
			listaClasificacionesTO = dao.findAllClasificaciones();
			
		} catch (ServicioNoDisponibleException e) {
			throw new ServicioNoDisponibleException(e);
		}
		return listaClasificacionesTO;
	}
	
	public List<UsuarioTO> findAllUsuarios() throws ServicioNoDisponibleException {
		List<UsuarioTO> listaUsuarioTOs = null;
		try {
			listaUsuarioTOs = dao.findAllUsuarios();
			
		} catch (ServicioNoDisponibleException e) {
			throw new ServicioNoDisponibleException(e);
		}
		return listaUsuarioTOs;
	}
	
	public List<SitioTO> findAllSitios() throws ServicioNoDisponibleException {
		List<SitioTO> listaSISitioTOs = null;
		try {
			listaSISitioTOs = dao.findAllSitios();
			
		} catch (ServicioNoDisponibleException e) {
			throw new ServicioNoDisponibleException(e);
		}
		return listaSISitioTOs;
	}
	
	public SitioTO findSitioActividad(Integer idSitio) throws ServicioNoDisponibleException {
		SitioTO listaSitio = new SitioTO();
		try {
			listaSitio = dao.findSitioActividad(idSitio);
			
		} catch (ServicioNoDisponibleException e) {
			throw new ServicioNoDisponibleException(e);
		}
		return listaSitio;
	}
	
	public List<ProyectoTO> findAllProyectos() throws ServicioNoDisponibleException {
		List<ProyectoTO> listaProyectosTO = null;
		try {
			listaProyectosTO = dao.findAllProyectos();
			
		} catch (ServicioNoDisponibleException e) {
			throw new ServicioNoDisponibleException(e);
		}
		return listaProyectosTO;
	}
	
	public ProyectoTO findProyectosActividad(Integer idProyecto) throws ServicioNoDisponibleException {
		ProyectoTO listaProyectosTO = new ProyectoTO();
		try {
			listaProyectosTO = dao.findProyectoActividad(idProyecto);
			
		} catch (ServicioNoDisponibleException e) {
			throw new ServicioNoDisponibleException(e);
		}
		return listaProyectosTO;
	}
	
	
	
	public List<SupervisionTO> findAllSupervisiones() throws ServicioNoDisponibleException {
		List<SupervisionTO> listaSupervisionTO = null;
		try {
			listaSupervisionTO = dao.findAllSupervisiones();
			
		} catch (ServicioNoDisponibleException e) {
			throw new ServicioNoDisponibleException(e);
		}
		return listaSupervisionTO;
	}
	
	public List<StatusTO> findAllStatus() throws ServicioNoDisponibleException {
		List<StatusTO> listaStatusTO = null;
		try {
			listaStatusTO = dao.findAllStatus();
			
		} catch (ServicioNoDisponibleException e) {
			throw new ServicioNoDisponibleException(e);
		}
		return listaStatusTO;
	}
	
	public List<StatusEspecificosTO> findAllStatusEspecificos(int valor) throws ServicioNoDisponibleException {
		List<StatusEspecificosTO> listaStatusEspecificosTO = null;
		try {
			listaStatusEspecificosTO = dao.findAllStatusEspecificos(valor);
			
		} catch (ServicioNoDisponibleException e) {
			throw new ServicioNoDisponibleException(e);
		}
		return listaStatusEspecificosTO;
	}
	
	public List<UsuarioTO> findAllClientes() throws ServicioNoDisponibleException{
		List<UsuarioTO> listaUsuarioTO = null;
		try {
			listaUsuarioTO = dao.findAllClientes();
		}catch(ServicioNoDisponibleException e) {
			throw new ServicioNoDisponibleException(e);
		}
		return listaUsuarioTO;
	}
	
	
	public UsuarioTO findClienteActividad(Integer idUsuario) throws ServicioNoDisponibleException{
		UsuarioTO listaUsuarioTO = new UsuarioTO();
		try {
			listaUsuarioTO = dao.findClienteActividad(idUsuario);
		}catch(ServicioNoDisponibleException e) {
			throw new ServicioNoDisponibleException(e);
		}
		return listaUsuarioTO;
	}
	
	public List<UsuarioTO> findAllClientesModificar(Integer idUsuario) throws ServicioNoDisponibleException{
		List<UsuarioTO> listaUsuarioTO = null;
		try {
			listaUsuarioTO = dao.findAllClientes();
		}catch(ServicioNoDisponibleException e) {
			throw new ServicioNoDisponibleException(e);
		}
		return listaUsuarioTO;
	}
	
	
	public List<UsuarioTO> findUsuariosEscalamiento() throws ServicioNoDisponibleException{
		List<UsuarioTO> listaUsuarioTO = null;
		try {
			listaUsuarioTO = dao.findUsuariosEscalamiento();
		}catch(ServicioNoDisponibleException e) {
			throw new ServicioNoDisponibleException(e);
		}
		return listaUsuarioTO;
	}
	
	public UsuarioTO findDataClientes(Integer idUsuario) throws ServicioNoDisponibleException{
		UsuarioTO UsuarioTO = null;
		try {
			UsuarioTO = dao.findDataClientes(idUsuario);
		}catch(ServicioNoDisponibleException e) {
			throw new ServicioNoDisponibleException(e);
		}
		return UsuarioTO;
	}
	
	public List<EscalamientoTO> findEscalamiento(Integer idActividad) throws ServicioNoDisponibleException{
		List<EscalamientoTO> escalamientoTO = new ArrayList<EscalamientoTO>();
		try {
			escalamientoTO = dao.findEscalamiento(idActividad);
		}catch(ServicioNoDisponibleException e) {
			throw new ServicioNoDisponibleException(e);
		}
		return escalamientoTO;
	}
	
	public List<ProveedorTO> findProveedor(Integer idActividad) throws ServicioNoDisponibleException{
		List<ProveedorTO> proveedorTO = new ArrayList<ProveedorTO>();
		try {
			proveedorTO = dao.findProveedor(idActividad);
		}catch(ServicioNoDisponibleException e) {
			throw new ServicioNoDisponibleException(e);
		}
		return proveedorTO;
	}
	
	public List<AdjuntoTO> findAdjunto(Integer idActividad) throws ServicioNoDisponibleException{
		List<AdjuntoTO> proveedorTO = new ArrayList<AdjuntoTO>();
		try {
			proveedorTO = dao.findAdjunto(idActividad);
		}catch(ServicioNoDisponibleException e) {
			throw new ServicioNoDisponibleException(e);
		}
		return proveedorTO;
	}
	
	public String findAlias(Integer idUsuario) throws ServicioNoDisponibleException{
		String UsuarioTO = null;
		try {
			UsuarioTO = dao.findAlias(idUsuario);
		}catch(ServicioNoDisponibleException e) {
			throw new ServicioNoDisponibleException(e);
		}
		return UsuarioTO;
	}
	
	public String findStatusEspecifico(Integer idStatusEspecifico) throws ServicioNoDisponibleException{
		String observacion = null;
		try {
			observacion = dao.findStatusEspecifico(idStatusEspecifico);
		}catch(ServicioNoDisponibleException e){
			throw new ServicioNoDisponibleException(e);
		}
		return observacion;
	}
	
	public ProyectoTO findProyectoSeleccionado(Integer idProyecto) throws ServicioNoDisponibleException{
		ProyectoTO result = null;
		try {
			result = dao.findProyectoSeleccionado(idProyecto);
		}catch(ServicioNoDisponibleException e) {
			throw new ServicioNoDisponibleException(e);
		}
		return result;
	}
	
	public UsuarioTO findUsuarioSeleccionado(Integer idUsuario) throws ServicioNoDisponibleException{
		UsuarioTO result = null;
		try {
			result = dao.findUsuarioSeleccionado(idUsuario);
		}catch(ServicioNoDisponibleException e) {
			throw new ServicioNoDisponibleException(e);
		}
		return result;
	}
	
	public SitioTO findSitioSeleccionado(Integer idSitio) throws ServicioNoDisponibleException{
		SitioTO result = null;
		try {
			result = dao.findSitioSeleccionado(idSitio);
		}catch(ServicioNoDisponibleException e) {
			throw new ServicioNoDisponibleException(e);
		}
		return result;
	}
}
