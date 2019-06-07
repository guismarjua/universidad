package cl.valposystems.sgi.dao.mapper;

import java.util.List;

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

public interface GenericMapper {
	
	public List<RolTO> findAllRoles() throws ServicioNoDisponibleException;
	public List<RegionTO> findAllRegiones() throws ServicioNoDisponibleException;
	public List<ComunaTO> findAllComunas(Integer id) throws ServicioNoDisponibleException;
	public List<ClasificacionTO> findAllClasificaciones() throws ServicioNoDisponibleException;
	
	public List<UsuarioTO> findAllUsuarios() throws ServicioNoDisponibleException;
	public List<SitioTO> findAllSitios() throws ServicioNoDisponibleException;
	public List<ProyectoTO> findAllProyectos() throws ServicioNoDisponibleException;
	public List<SupervisionTO> findAllSupervisiones() throws ServicioNoDisponibleException;
	public List<StatusTO> findAllStatus() throws ServicioNoDisponibleException;
	public List<StatusEspecificosTO> findAllStatusEspecificos(Integer valor) throws ServicioNoDisponibleException;
	public List<UsuarioTO> findAllClientes() throws ServicioNoDisponibleException;
	public List<UsuarioTO> findUsuariosEscalamiento() throws ServicioNoDisponibleException;
	public UsuarioTO findDataClientes(Integer idUsuario) throws ServicioNoDisponibleException;
	public List<EscalamientoTO> findEscalamiento(Integer idActividad) throws ServicioNoDisponibleException;
	public List<ProveedorTO> findProveedor(Integer idActividad) throws ServicioNoDisponibleException;
	public List<AdjuntoTO> findAdjunto(Integer idActividad) throws ServicioNoDisponibleException;
	
	public String findAlias(Integer idUsuario) throws ServicioNoDisponibleException;
	public String findStatusEspecifico(Integer idStatusEspecifico) throws ServicioNoDisponibleException;
	
	public UsuarioTO findClienteActividad(Integer idUsuario) throws ServicioNoDisponibleException;
	public ProyectoTO findProyectoActividad(Integer idProyecto) throws ServicioNoDisponibleException;
	public SitioTO findSitioActividad(Integer idSitio) throws ServicioNoDisponibleException;
	
	public UsuarioTO findUsuarioSeleccionado(Integer idUsuario) throws ServicioNoDisponibleException;
	public ProyectoTO findProyectoSeleccionado(Integer idProyecto) throws ServicioNoDisponibleException;
	public SitioTO findSitioSeleccionado(Integer idSitio) throws ServicioNoDisponibleException;
	
	public List<ComunaTO> preloadComunas(Integer idRegion) throws ServicioNoDisponibleException;
}
