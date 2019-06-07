package cl.valposystems.sgi.dao.mapper;

import java.util.List;

import cl.valposystems.sgi.exception.ServicioNoDisponibleException;
import cl.valposystems.sgi.to.VentanaItoTO;

public interface VentanaItoMapper {

	public List<VentanaItoTO> findAllVentanaIto(VentanaItoTO to) throws ServicioNoDisponibleException;
}
