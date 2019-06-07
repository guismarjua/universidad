package cl.valposystems.sgi.business.sbean;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;

import cl.valposystems.sgi.dao.impl.DAOActividadProyecto;
import cl.valposystems.sgi.exception.ServicioNoDisponibleException;
import cl.valposystems.sgi.to.ActividadTO;
@Stateless
@LocalBean
@TransactionManagement(TransactionManagementType.BEAN)
public class ActividadProyectoSessionBean {

	private DAOActividadProyecto dao;
	
	@PostConstruct
	public void init() {
		try {
			dao = new DAOActividadProyecto();
		} catch (ServicioNoDisponibleException e) {
			System.err.println(e);
		}
	}
	
	public ActividadProyectoSessionBean() {}
	
	public List<ActividadTO> obtenerActividadProyectos(ActividadTO to) throws ServicioNoDisponibleException {
		List<ActividadTO> listaActividadProyectoTO = null;
		try {
			Date date = to.getFechaTermino(); 
			DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");  
			String strDate = dateFormat.format(date); 
			String sDate1= strDate+" 23:59:59";  
		    Date date1= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(sDate1);  
		    to.setFechaTermino(date1);
			listaActividadProyectoTO = dao.findAllActividadProyecto(to);
			
		} catch (ServicioNoDisponibleException | ParseException e) {
			throw new ServicioNoDisponibleException(e);
		}
		return listaActividadProyectoTO;
	}
	
	public List<ActividadTO> findAllActividadProyectoFecha(Date fechaInicio, Date fechaTermino) throws ServicioNoDisponibleException {
		
		List<ActividadTO> lista = new ArrayList<ActividadTO>();
		
		try {

			ActividadTO to = new ActividadTO();
			to.setFechaInicio(fechaInicio);
			to.setFechaTermino(fechaTermino);
			List<ActividadTO> listaTO = dao.findAllActividadProyectoFecha(to);
			if (listaTO != null && !listaTO.isEmpty()) {
				ActividadTO indicador = null;
				for (ActividadTO im : listaTO) {
					indicador = new ActividadTO();
					indicador.setNombreProyecto(im.getNombreProyecto());
					indicador.setCantidadActv(im.getCantidadActv());
					lista.add(indicador);
				}
			}
		} catch (ServicioNoDisponibleException e) {
			throw new ServicioNoDisponibleException(e);
		}
		
		return lista;
	}
	
	
	
}
