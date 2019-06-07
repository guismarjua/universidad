package cl.valposystems.sgi.dao.mapper;

import java.util.List;
import java.util.Map;

import cl.valposystems.sgi.exception.ServicioNoDisponibleException;
import cl.valposystems.sgi.to.ActividadTO;
import cl.valposystems.sgi.to.EscalamientoTO;

public interface ActividadMapper {
	
	public List<ActividadTO> findAllActividades() throws ServicioNoDisponibleException;
	
	public List<ActividadTO> findAllActividadesIto(Integer idUsuario) throws ServicioNoDisponibleException;
	
	public List<ActividadTO> findAllActividadRango(ActividadTO to) throws ServicioNoDisponibleException;
	
	public boolean insertActividad(ActividadTO actividad) throws ServicioNoDisponibleException;
	
	public boolean editActividad(ActividadTO actividad) throws ServicioNoDisponibleException;
	
	public Integer findIdActividad(ActividadTO actividad) throws ServicioNoDisponibleException;
	
	public boolean insertPersonalPM (Map<String,Object> parametros) throws ServicioNoDisponibleException;
	
	public boolean modificarPersonalPM (Map<String,Object> parametros) throws ServicioNoDisponibleException;
	
	public boolean insertPersonalSupervisor (Map<String,Object> parametros) throws ServicioNoDisponibleException;
	
	public boolean modificarPersonalSupervisor (Map<String,Object> parametros) throws ServicioNoDisponibleException;
	
	public boolean insertEscalamiento(Map<String,Object> parametros) throws ServicioNoDisponibleException;
	
	public boolean insertObservaciones(Map<String,Object> parametros) throws ServicioNoDisponibleException;
	
	public boolean insertProveedores(Map<String,Object> parametros) throws ServicioNoDisponibleException;
	
	public boolean insertAdjuntos(Map<String,Object> parametros) throws ServicioNoDisponibleException;
	
	public ActividadTO findDataActividad(ActividadTO actividad) throws ServicioNoDisponibleException;

	public boolean modificarVigenciaProveedores(Map<String,Object> parametro) throws ServicioNoDisponibleException;
	
	public boolean modificarVigenciaAdjuntos(Map<String,Object> parametro) throws ServicioNoDisponibleException;
	
	public boolean modificarObservaciones(Map<String,Object> parametros) throws ServicioNoDisponibleException;

	public boolean modificarEscalamiento(Map<String,Object> parametros) throws ServicioNoDisponibleException;

	public List<EscalamientoTO> buscarIdEscalamiento(Integer idActividad) throws ServicioNoDisponibleException;
}
