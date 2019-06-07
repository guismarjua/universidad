package cl.valposystems.sgi.business.sbean;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;

import cl.valposystems.sgi.dao.impl.DAOVentanaProyecto;
import cl.valposystems.sgi.exception.ServicioNoDisponibleException;
import cl.valposystems.sgi.to.VentanaProyectoTO;

@Stateless
@LocalBean
@TransactionManagement(TransactionManagementType.BEAN)
public class VentanaProyectoSessionBean {


	private DAOVentanaProyecto dao;
	
	@PostConstruct
	public void init() {
		try {
			dao = new DAOVentanaProyecto();
		} catch (ServicioNoDisponibleException e) {
			System.err.println(e);
		}
	}
	
	public VentanaProyectoSessionBean() {}
	
	public List<VentanaProyectoTO> obtenerVentanaProyectos(VentanaProyectoTO ventanaProyecto) throws ServicioNoDisponibleException {
		List<VentanaProyectoTO> listaVentanaProyectoTO = null;
		try {
			Date date = ventanaProyecto.getFechaBusquedaFin(); 
			DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");  
			String strDate = dateFormat.format(date); 
			String sDate1= strDate+" 23:59:59";  
		    Date date1= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(sDate1);  
		    ventanaProyecto.setFechaBusquedaFin(date1);
			listaVentanaProyectoTO = dao.findAllVentanaProyecto(ventanaProyecto);
			
		} catch (ServicioNoDisponibleException | ParseException e) {
			throw new ServicioNoDisponibleException(e);
		}
		return listaVentanaProyectoTO;
	}
}
