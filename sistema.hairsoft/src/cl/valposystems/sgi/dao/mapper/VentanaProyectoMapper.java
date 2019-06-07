package cl.valposystems.sgi.dao.mapper;

import java.util.List;

import cl.valposystems.sgi.exception.ServicioNoDisponibleException;
import cl.valposystems.sgi.to.VentanaProyectoTO;

public interface VentanaProyectoMapper {

	public List<VentanaProyectoTO> findAllVentanaProyecto(VentanaProyectoTO to) throws ServicioNoDisponibleException;
}
