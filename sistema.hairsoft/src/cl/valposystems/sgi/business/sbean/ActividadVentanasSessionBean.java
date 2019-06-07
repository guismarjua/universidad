package cl.valposystems.sgi.business.sbean;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;

import org.apache.log4j.Logger;

import cl.valposystems.sgi.dao.impl.DAOActividadVentanas;
import cl.valposystems.sgi.exception.ServicioNoDisponibleException;
import cl.valposystems.sgi.to.ActividadVentanasTO;


@Stateless
@LocalBean
@TransactionManagement(TransactionManagementType.BEAN)
public class ActividadVentanasSessionBean {

	final static Logger logger = Logger.getLogger(ActividadVentanasSessionBean.class);

	@EJB GenericSessionBean generic;
	private DAOActividadVentanas dao;
	
	@PostConstruct
	public void init() {
		try {
			dao = new DAOActividadVentanas();
		} catch (ServicioNoDisponibleException e) {
			System.err.println(e);
		}
	}

	public ActividadVentanasSessionBean() {

	}
	
	public List<ActividadVentanasTO> obtenerActividadesPorVentana(ActividadVentanasTO actVentana) throws ServicioNoDisponibleException{
		List<ActividadVentanasTO> resultado = null;
		try {
			resultado = dao.findAllActividadesPorVentana(actVentana);
			for(ActividadVentanasTO to : resultado) {
				to.setCliente(generic.findAlias(to.getIdCliente()));
				to.setIto(generic.findAlias(to.getIdIto()));
			}
		} catch (ServicioNoDisponibleException e) {
			throw new ServicioNoDisponibleException(e);
		}
		return resultado;
	}
	
	public List<ActividadVentanasTO> obtenerTotalVentanaRango(ActividadVentanasTO to) throws ServicioNoDisponibleException {
		List<ActividadVentanasTO> listaTotalVentanatoTO = null;
		try {
			listaTotalVentanatoTO = dao.findAllTotalVentanaRango(to);
			
		} catch (ServicioNoDisponibleException e) {
			throw new ServicioNoDisponibleException(e);
		}
		return listaTotalVentanatoTO;
	}
	
}
