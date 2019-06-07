package cl.valposystems.sgi.business.sbean;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;

import cl.valposystems.sgi.dao.impl.DAOSitio;
import cl.valposystems.sgi.exception.ServicioNoDisponibleException;
import cl.valposystems.sgi.to.SitioTO;

@Stateless
@LocalBean
@TransactionManagement(TransactionManagementType.BEAN)
public class SitioSessionBean {
	
	private DAOSitio dao; 
	
	@PostConstruct
	public void init() {
		try {
			dao = new DAOSitio();
		} catch (ServicioNoDisponibleException e) {
			System.err.println(e);
		}
	
	}
	
	public SitioSessionBean() {}
	
	public List<SitioTO> obtenerSitios() throws ServicioNoDisponibleException {
		List<SitioTO> listaSitiosTO = null;
		try {
			listaSitiosTO = dao.findAllSitios();
			
		} catch (ServicioNoDisponibleException e) {
			throw new ServicioNoDisponibleException(e);
		}
		return listaSitiosTO;
	}
	
	public boolean eliminarSitio(SitioTO sitio) throws ServicioNoDisponibleException {
		boolean result = false;
		sitio.setVigencia(0);
		try {
			dao.deleteSitio(sitio);
		}catch(ServicioNoDisponibleException e) {
			throw new ServicioNoDisponibleException(e);
		}
		return result;
	}
	
	public boolean insertarSitio(SitioTO sitio) throws ServicioNoDisponibleException{
		boolean result = false;
		try {
			dao.insertSitio(sitio);
		}catch(ServicioNoDisponibleException e) {
			throw new ServicioNoDisponibleException(e);
		}
		return result;
	}

	public void modificarSitio(SitioTO sitio) throws ServicioNoDisponibleException{
		try {
			dao.updateSitio(sitio);
		}catch(ServicioNoDisponibleException e) {
			throw new ServicioNoDisponibleException(e);
		}
	}
	
	public Integer buscaSitio(SitioTO sitio) throws ServicioNoDisponibleException {
		Integer result = 0;
		try {
			result = dao.findSitioBySitioSinergia(sitio);
		}catch(ServicioNoDisponibleException e) {
			throw new ServicioNoDisponibleException(e);
		}
		return result;
	}
	
	public SitioTO seleccionarSitioPorId(Integer id) throws ServicioNoDisponibleException{
		SitioTO result = null;
		try {
			result = dao.findSitioById(id);
		}catch(ServicioNoDisponibleException e){
			throw new ServicioNoDisponibleException(e);
		}
		return result;
	}
}
