package cl.valposystems.sgi.business.sbean;

import java.text.SimpleDateFormat;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;

import cl.valposystems.sgi.dao.impl.DAOProyecto;
import cl.valposystems.sgi.exception.ServicioNoDisponibleException;
import cl.valposystems.sgi.to.ProyectoTO;
@Stateless
@LocalBean
@TransactionManagement(TransactionManagementType.BEAN)
public class ProyectoSessionBean {
	
	private DAOProyecto dao;
	
	@PostConstruct
	public void init() {
		try {
			dao = new DAOProyecto();
		} catch (ServicioNoDisponibleException e) {
			System.err.println(e);
		}
	
	}
	
	public ProyectoSessionBean() {}
	
	public List<ProyectoTO> obtenerProyectos() throws ServicioNoDisponibleException {
		List<ProyectoTO> listaProyectosTO = null;
		try {
			listaProyectosTO = dao.findAllProyectos();
			for(ProyectoTO p : listaProyectosTO) {
				SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
				String dateString = format.format(p.getFechaInicio());
				String dateString2 = format.format(p.getFechaTermino());
				p.setFecIni(dateString);
				p.setFecTerm(dateString2);
			}
		} catch (ServicioNoDisponibleException e) {
			throw new ServicioNoDisponibleException(e);
		}
		return listaProyectosTO;
	}
	
	public boolean eliminarProyecto(ProyectoTO proyecto) throws ServicioNoDisponibleException {
		boolean result = false;
		proyecto.setVigencia(0);
		try {
			dao.deleteProyecto(proyecto);
		}catch(ServicioNoDisponibleException e) {
			throw new ServicioNoDisponibleException(e);
		}
		return result;
	}
	
	public boolean insertarProyecto(ProyectoTO proyecto) throws ServicioNoDisponibleException{
		boolean result = false;
		try {
			dao.insertProyecto(proyecto);
		}catch(ServicioNoDisponibleException e) {
			throw new ServicioNoDisponibleException(e);
		}
		return result;
	}
	
	public void modificarProyecto(ProyectoTO proyecto) throws ServicioNoDisponibleException{
		try {
			dao.updateProyecto(proyecto);
		}catch(ServicioNoDisponibleException e) {
			throw new ServicioNoDisponibleException(e);
		}
	}

}
