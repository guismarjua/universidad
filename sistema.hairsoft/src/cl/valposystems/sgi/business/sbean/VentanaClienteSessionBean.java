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

import cl.valposystems.sgi.dao.impl.DAOVentanaCliente;
import cl.valposystems.sgi.exception.ServicioNoDisponibleException;
import cl.valposystems.sgi.to.VentanaClienteTO;

@Stateless
@LocalBean
@TransactionManagement(TransactionManagementType.BEAN)
public class VentanaClienteSessionBean {
	
	private DAOVentanaCliente dao;
	
	@PostConstruct
	public void init() {
		try {
			dao = new DAOVentanaCliente();
		} catch (ServicioNoDisponibleException e) {
			System.err.println(e);
		}
	}
	
	public VentanaClienteSessionBean() {}
	
	public List<VentanaClienteTO> obtenerVentanaClientes(VentanaClienteTO to) throws ServicioNoDisponibleException {
		List<VentanaClienteTO> listaVentanaClienteTO = null;
		try {
			Date date = to.getFechaTermino(); 
			DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");  
			String strDate = dateFormat.format(date); 
			String sDate1= strDate+" 23:59:59";  
		    Date date1= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(sDate1);  
		    to.setFechaTermino(date1);
			listaVentanaClienteTO = dao.findAllVentanaCliente(to);
			
		} catch (ServicioNoDisponibleException | ParseException e) {
			throw new ServicioNoDisponibleException(e);
		}
		return listaVentanaClienteTO;
	}

}
