package cl.valposystems.sgi.mbean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;

import cl.valposystems.sgi.business.sbean.ActividadVentanasSessionBean;
import cl.valposystems.sgi.exception.ServicioNoDisponibleException;
import cl.valposystems.sgi.to.ActividadVentanasTO;

@Named
@SessionScoped
public class ActividadVentanasMBean implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -1018974669087137601L;
	
	@EJB ActividadVentanasSessionBean servicio;
	
	private ActividadVentanasTO actVentanas = null;
	private List<ActividadVentanasTO> actividadVentanas;
	
	@PostConstruct
	public void init() {
		actVentanas = new ActividadVentanasTO();
		actividadVentanas = new ArrayList<ActividadVentanasTO>();;
	}
	
	
	public void inicializar() {
		actVentanas = new ActividadVentanasTO();
		if (actividadVentanas != null && !actividadVentanas.isEmpty()) {
			actividadVentanas.clear();
		} 
	}
	
	
	/**
	 * Se obtienen el listado de los sitios existentes
	 */
	public void cargaActividadPorVentanas() {
		FacesContext context = FacesContext.getCurrentInstance();
		try {
			if (actividadVentanas != null && !actividadVentanas.isEmpty()) {
				actividadVentanas.clear();
			} 
			actividadVentanas.addAll(servicio.obtenerActividadesPorVentana(actVentanas));
		} catch (ServicioNoDisponibleException e) {
			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "No logra obtener a los sitios."));
		}
	}

	public List<ActividadVentanasTO> getActividadVentanas() {
		return actividadVentanas;
	}

	public void setActividadVentanas(List<ActividadVentanasTO> actividadVentanas) {
		this.actividadVentanas = actividadVentanas;
	}

	public ActividadVentanasTO getActVentanas() {
		return actVentanas;
	}

	public void setActVentanas(ActividadVentanasTO actVentanas) {
		this.actVentanas = actVentanas;
	}
	
	

}
