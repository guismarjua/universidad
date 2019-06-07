package cl.valposystems.sgi.dao.mapper;

import java.util.List;

import cl.valposystems.sgi.exception.ServicioNoDisponibleException;
import cl.valposystems.sgi.to.VentanaClienteTO;

public interface VentanaClienteMapper {
	
	public List<VentanaClienteTO> findAllVentanaCliente(VentanaClienteTO to) throws ServicioNoDisponibleException;

}
