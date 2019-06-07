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

import cl.valposystems.sgi.dao.impl.DAOActividadCliente;
import cl.valposystems.sgi.exception.ServicioNoDisponibleException;
import cl.valposystems.sgi.to.ActividadClienteTO;
@Stateless
@LocalBean
@TransactionManagement(TransactionManagementType.BEAN)
public class ActividadClienteSessionBean {

	private DAOActividadCliente dao;
	
	@PostConstruct
	public void init() {
		try {
			dao = new DAOActividadCliente();
		} catch (ServicioNoDisponibleException e) {
			System.err.println(e);
		}
	}
	
	public ActividadClienteSessionBean() {}
	
	public List<ActividadClienteTO> obtenerActividadClientes(ActividadClienteTO to) throws ServicioNoDisponibleException {
		List<ActividadClienteTO> listaActividadClienteTO = null;
		try {
			Date date = to.getFechaTermino(); 
			DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");  
			String strDate = dateFormat.format(date); 
			String sDate1= strDate+" 23:59:59";  
		    Date date1= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(sDate1);  
		    to.setFechaTermino(date1);
			listaActividadClienteTO = dao.findAllActividadCliente(to);
			
		} catch (ServicioNoDisponibleException | ParseException e) {
			throw new ServicioNoDisponibleException(e);
		}
		return listaActividadClienteTO;
	}
	
}
