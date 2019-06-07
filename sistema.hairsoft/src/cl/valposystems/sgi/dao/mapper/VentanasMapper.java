package cl.valposystems.sgi.dao.mapper;

import java.util.List;

import cl.valposystems.sgi.exception.ServicioNoDisponibleException;
import cl.valposystems.sgi.to.ActividadTO;
import cl.valposystems.sgi.to.VentanaActividadTO;
import cl.valposystems.sgi.to.VentanaTO;

public interface VentanasMapper {

	public List<VentanaTO> findAllVentanas() throws ServicioNoDisponibleException;
	
	public boolean deleteVentana(VentanaTO ventana) throws ServicioNoDisponibleException;

	public boolean insertVentana(VentanaTO ventana) throws ServicioNoDisponibleException;
	
	public List<ActividadTO> findActividadesSinAsignar() throws ServicioNoDisponibleException;
	
	public List<ActividadTO> findActividadesAsignadas(int idVentana) throws ServicioNoDisponibleException;
	
	public boolean insertVentanaActividad(VentanaActividadTO to) throws ServicioNoDisponibleException;
	
	public boolean updateVentanaActividad(VentanaActividadTO to) throws ServicioNoDisponibleException;
	
	public void updateFechaModificacionVentana(Integer idVentana) throws ServicioNoDisponibleException;
	
	public List<VentanaActividadTO> findAllActividadesPorVentana() throws ServicioNoDisponibleException;
	
	public void deleteVentanaActividad(VentanaTO ventana) throws ServicioNoDisponibleException;
}
