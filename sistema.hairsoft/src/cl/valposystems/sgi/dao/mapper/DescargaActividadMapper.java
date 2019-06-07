package cl.valposystems.sgi.dao.mapper;

import java.util.List;

import cl.valposystems.sgi.exception.ServicioNoDisponibleException;
import cl.valposystems.sgi.to.ActividadTO;
import cl.valposystems.sgi.to.AdjuntoTO;
import cl.valposystems.sgi.to.EscalamientoTO;
import cl.valposystems.sgi.to.ProveedorTO;
import cl.valposystems.sgi.to.UsuarioTO;

public interface DescargaActividadMapper {
	
	public ActividadTO findActividad(ActividadTO act) throws ServicioNoDisponibleException;
	public UsuarioTO findUsuarioCliente(Integer idActividad) throws ServicioNoDisponibleException;
	public UsuarioTO findUsuarioSupervisor(Integer idActividad) throws ServicioNoDisponibleException;
	public List<EscalamientoTO> findEscalamientos (Integer idActividad) throws ServicioNoDisponibleException;
	public List<ProveedorTO> findProveedores (Integer idActividad) throws ServicioNoDisponibleException;
	public List<AdjuntoTO> findAdjuntos(Integer idActividad) throws ServicioNoDisponibleException;
}
