package cl.valposystems.sgi.dao.mapper;

import java.util.List;

import cl.valposystems.sgi.exception.ServicioNoDisponibleException;
import cl.valposystems.sgi.to.ActividadTO;

public interface ActividadProyectoMapper {
	
	public List<ActividadTO> findAllActividadProyecto(ActividadTO to) throws ServicioNoDisponibleException;
	public List<ActividadTO> findAllActividadProyectoFecha(ActividadTO im) throws ServicioNoDisponibleException;

}
