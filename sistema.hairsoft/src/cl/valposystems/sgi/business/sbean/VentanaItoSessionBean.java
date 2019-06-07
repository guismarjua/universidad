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

import cl.valposystems.sgi.dao.impl.DAOVentanaIto;
import cl.valposystems.sgi.exception.ServicioNoDisponibleException;
import cl.valposystems.sgi.to.VentanaItoTO;

@Stateless
@LocalBean
@TransactionManagement(TransactionManagementType.BEAN)

public class VentanaItoSessionBean {
	private DAOVentanaIto dao;
	
	@PostConstruct
	public void init() {
		try {
			dao = new DAOVentanaIto();
		} catch (ServicioNoDisponibleException e) {
			System.err.println(e);
		}
	}
	
	public VentanaItoSessionBean() {}
	
	public List<VentanaItoTO> obtenerVetanaItos(VentanaItoTO to) throws ServicioNoDisponibleException {
		List<VentanaItoTO> listaVentanaItoTO = null;
		try {
			Date date = to.getFechaTermino(); 
			DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");  
			String strDate = dateFormat.format(date); 
			String sDate1= strDate+" 23:59:59";  
		    Date date1= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(sDate1);  
		    to.setFechaTermino(date1);
			listaVentanaItoTO = dao.findAllVentanaIto(to);
			
		} catch (ServicioNoDisponibleException | ParseException e) {
			throw new ServicioNoDisponibleException(e);
		}
		return listaVentanaItoTO;
	}

}
