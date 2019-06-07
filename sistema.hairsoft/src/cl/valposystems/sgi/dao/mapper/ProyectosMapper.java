package cl.valposystems.sgi.dao.mapper;

import java.util.List;

import cl.valposystems.sgi.exception.ServicioNoDisponibleException;
import cl.valposystems.sgi.to.ProyectoTO;

public interface ProyectosMapper {

	public List<ProyectoTO> findAllProyectos() throws ServicioNoDisponibleException;
	
	public boolean deleteProyecto(ProyectoTO proyecto) throws ServicioNoDisponibleException;
	public boolean insertProyecto(ProyectoTO proyecto) throws ServicioNoDisponibleException;
	public void updateProyecto(ProyectoTO proyecto) throws ServicioNoDisponibleException;
}
