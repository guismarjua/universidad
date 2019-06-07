package cl.valposystems.sgi.dao.mapper;

import java.util.List;

import cl.valposystems.sgi.exception.ServicioNoDisponibleException;
import cl.valposystems.sgi.to.ActividadItoTO;

public interface ActividadItoMapper {
	
	public List<ActividadItoTO> findAllActividadIto(ActividadItoTO to) throws ServicioNoDisponibleException;

}
