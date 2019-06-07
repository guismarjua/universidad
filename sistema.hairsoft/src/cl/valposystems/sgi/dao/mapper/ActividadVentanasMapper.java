package cl.valposystems.sgi.dao.mapper;

import java.util.List;

import cl.valposystems.sgi.exception.ServicioNoDisponibleException;
import cl.valposystems.sgi.to.ActividadVentanasTO;

public interface ActividadVentanasMapper {
	
	public List<ActividadVentanasTO> findAllActividadesPorVentana(ActividadVentanasTO actVentana) throws ServicioNoDisponibleException;
	public List<ActividadVentanasTO> findAllTotalVentanaRango(ActividadVentanasTO to) throws ServicioNoDisponibleException;
}
