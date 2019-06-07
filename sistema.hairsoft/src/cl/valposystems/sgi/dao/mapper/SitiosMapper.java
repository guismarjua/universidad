package cl.valposystems.sgi.dao.mapper;

import java.util.List;

import cl.valposystems.sgi.exception.ServicioNoDisponibleException;
import cl.valposystems.sgi.to.SitioTO;

public interface SitiosMapper {

	public List<SitioTO> findAllSitios() throws ServicioNoDisponibleException;
	public boolean deleteSitio(SitioTO sitio) throws ServicioNoDisponibleException;
	public boolean insertSitio(SitioTO sitio) throws ServicioNoDisponibleException;
	public void updateSitio(SitioTO sitio) throws ServicioNoDisponibleException;
	public Integer findSitioBySitioSinergia(SitioTO sitio) throws ServicioNoDisponibleException;
	public SitioTO findSitioById(Integer id) throws ServicioNoDisponibleException;
}

