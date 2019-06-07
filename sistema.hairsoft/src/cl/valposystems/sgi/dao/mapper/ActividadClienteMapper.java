package cl.valposystems.sgi.dao.mapper;

import java.util.List;

import cl.valposystems.sgi.exception.ServicioNoDisponibleException;
import cl.valposystems.sgi.to.ActividadClienteTO;

public interface ActividadClienteMapper {
	
	public List<ActividadClienteTO> findAllActividadCliente(ActividadClienteTO to) throws ServicioNoDisponibleException;

}
