package cl.valposystems.sgi.business.sbean;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;

import cl.valposystems.sgi.dao.impl.DAOVentana;
import cl.valposystems.sgi.exception.ServicioNoDisponibleException;
import cl.valposystems.sgi.to.ActividadTO;
import cl.valposystems.sgi.to.VentanaActividadTO;
import cl.valposystems.sgi.to.VentanaTO;

@Stateless
@LocalBean
@TransactionManagement(TransactionManagementType.BEAN)
public class VentanaSessionBean {

	private DAOVentana dao; 

	@PostConstruct
	public void init() {
		try {
			dao = new DAOVentana();
		} catch (ServicioNoDisponibleException e) {
			System.err.println(e);
		}
	}
	
	public VentanaSessionBean(){
		
	}
	
	public List<VentanaTO> obtenerVentana() throws ServicioNoDisponibleException {
		List<VentanaTO> listaVentanasTO = null;
		List<VentanaActividadTO> listadoActividadesPorVentana = null;
		try {
			//BUSCO LA INFORMACION DE LAS VENTANAS Y DE LAS ACTIVIDADES ASOCIADAS A LAS VENTANAS
			listaVentanasTO = dao.findAllVentanas();
			listadoActividadesPorVentana = dao.findAllActividadesPorVentana();
			for(VentanaTO v : listaVentanasTO) {
				SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy HH:mm");
				String dateString = format.format(v.getFechaCreacion());
				if(v.getFechaModificacion() != null) {
					String dateString2 = format.format(v.getFechaModificacion());
					v.setFecCre(dateString);
					v.setFecMod(dateString2);
				}else {
					v.setFecCre(dateString);
				}	
			}
			//ORDENO LAS VENTANAS EN UN MAP CON KEY ID_VENTANA Y VALUE LA CONCATENACIÓN DEL NOMBRE DE LAS ACTIVIDADES
			Map<Integer,String> ordenActividades = new HashMap<Integer,String>();
			for(VentanaActividadTO to :listadoActividadesPorVentana) {
				if(ordenActividades.containsKey(to.getIdVentana())) {
					String actividades = ordenActividades.get(to.getIdVentana()) + " ; " + to.getActividad();
					ordenActividades.put(to.getIdVentana(), actividades);
				}else {
					ordenActividades.put(to.getIdVentana(), to.getActividad());
				}
			}
			//INSERTO EN EL ARREGLO LAS ACTIVIDADES ASOCIADAS A LA VENTANA
			for (Integer key : ordenActividades.keySet()) {
				for(VentanaTO ven : listaVentanasTO) {
					if(ven.getIdVentana() == key) {
						ven.setListadoActividades(ordenActividades.get(key));
					}
				}
			}
			
		} catch (ServicioNoDisponibleException e) {
			throw new ServicioNoDisponibleException(e);
		}
		return listaVentanasTO;
	}
	
	public boolean eliminarVentana(VentanaTO ventana) throws ServicioNoDisponibleException {
		boolean result = false;
		ventana.setVigencia(0);
		try {
			result = dao.deleteVentana(ventana);
		}catch(ServicioNoDisponibleException e) {
			throw new ServicioNoDisponibleException(e);
		}
		return result;
	}
	
	public void actualizarActividadVentana(VentanaTO ventana) throws ServicioNoDisponibleException {
		try {
			dao.deleteVentanaActividad(ventana);
		}catch(ServicioNoDisponibleException e) {
			throw new ServicioNoDisponibleException(e);
		}
	}
	
	public boolean insertarVentana(VentanaTO ventana) throws ServicioNoDisponibleException{
		boolean result = false;
		try {
			result = dao.insertVentana(ventana);
		}catch(ServicioNoDisponibleException e) {
			throw new ServicioNoDisponibleException(e);
		}
		return result;
	}
	
	public List<ActividadTO> obtenerListadoActividades() throws ServicioNoDisponibleException{
		List<ActividadTO> listaActividades = null;
		try {
			listaActividades = dao.findActividadesSinAsignar();
			
		} catch (ServicioNoDisponibleException e) {
			throw new ServicioNoDisponibleException(e);
		}
		return listaActividades;
	}
	
	public List<ActividadTO> obtenerListadoActividadesAsignadas(int idVentana) throws ServicioNoDisponibleException{
		List<ActividadTO> listaActividades = null;
		try {
			listaActividades = dao.findActividadesAsignadas(idVentana);
			
		} catch (ServicioNoDisponibleException e) {
			throw new ServicioNoDisponibleException(e);
		}
		return listaActividades;
	}
	
	public boolean agregarVentanaActividad(VentanaActividadTO to) throws ServicioNoDisponibleException{
		boolean result = false;
		try {
			result= dao.insertVentanaActividad(to);
		} catch (ServicioNoDisponibleException e) {
			throw new ServicioNoDisponibleException(e);
		}
		return result;
	}
	
	public boolean modificarVentanaActividad(VentanaActividadTO to) throws ServicioNoDisponibleException{
		boolean result = false;
		try {
			result = dao.updateVentanaActividad(to);
		} catch (ServicioNoDisponibleException e) {
			throw new ServicioNoDisponibleException(e);
		}
		return result;
	}
	
	public void updateFechaModificacionVentana(Integer idVentana) throws ServicioNoDisponibleException{
		try {
			dao.updateFechaModificacionVentana(idVentana);
		} catch (ServicioNoDisponibleException e) {
			throw new ServicioNoDisponibleException(e);
		}
	}
}
