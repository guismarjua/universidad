package cl.valposystems.sgi.mbean;

import java.io.Serializable;
import java.util.List;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import org.apache.log4j.Logger;
import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.BarChartModel;
import org.primefaces.model.chart.ChartSeries;

import cl.valposystems.sgi.business.sbean.VentanaProyectoSessionBean;
import cl.valposystems.sgi.exception.ServicioNoDisponibleException;
import cl.valposystems.sgi.to.VentanaProyectoTO;


@Named
@SessionScoped
public class VentanaProyectoMBean implements Serializable{


	/**
	 * 
	 */
	private static final long serialVersionUID = -675038448638469742L;
	final static Logger logger = Logger.getLogger(VentanaProyectoMBean.class);
			
	@EJB
	VentanaProyectoSessionBean servicio;

	private BarChartModel model = null;
	private List<VentanaProyectoTO> listaVentProy = null;
	private VentanaProyectoTO ventProy = null;

	
	public void inicializador() {
		ventProy = new VentanaProyectoTO();
		if(listaVentProy != null && !listaVentProy.isEmpty()) {
			listaVentProy.clear();
		}
		
		model = new BarChartModel();
		model.setTitle("Sin registros");

		ChartSeries cs = new ChartSeries();
		cs.set("Proyectos", 0);

		model.addSeries(cs);
		model.getAxis(AxisType.Y).setLabel("Cantidad de Ventanas");
		model.getAxis(AxisType.Y).setMin(0);
		model.getAxis(AxisType.Y).setMax(30);			
	}
	
	public void cargarGraficos() throws ServicioNoDisponibleException {
		this.llamarLista();
		model = this.crearModel();    	
    	model.setAnimate(true);	
	}
	
	public void llamarLista() throws ServicioNoDisponibleException {
		if(listaVentProy != null && !listaVentProy.isEmpty()) {
			listaVentProy.clear();
		}
		this.listaVentProy = servicio.obtenerVentanaProyectos(ventProy);
		logger.info("retornaron de la lista la cantidad de :" + listaVentProy.size());
	}

	public BarChartModel crearModel() throws ServicioNoDisponibleException {
		BarChartModel model = new BarChartModel();
		ChartSeries cs = new ChartSeries();
		
		if (this.listaVentProy != null && !this.listaVentProy.isEmpty()) {
			model.setTitle("Cantidad de Ventana Por Proyecto");
			for (VentanaProyectoTO im : this.listaVentProy) {
				cs.set(im.getNombreProyecto(), im.getCantidadVentana());
			}
			
			model.addSeries(cs);
			model.setLegendCols(2);

			model.getAxis(AxisType.X).setTickAngle(5);
			model.getAxis(AxisType.X).setLabel("Proyectos");
			

			model.setShowPointLabels(true);
			model.getAxis(AxisType.Y).setLabel("Cantidad de Ventanas");
			model.getAxis(AxisType.Y).setMin(0);
			model.getAxis(AxisType.Y).setTickFormat("%'d");
			model.getAxis(AxisType.Y).setMax(this.getMaxTotal(this.listaVentProy) + 5);

		} else {

			model.setTitle("Sin registros");
        	cs.set("Proyectos", 0);
        	
			model.addSeries(cs);
			model.getAxis(AxisType.Y).setLabel("Cantidad de Ventanas");
			model.getAxis(AxisType.Y).setMin(0);
			model.getAxis(AxisType.Y).setMax(1);
		}
		return model;

	}

	private int getMaxTotal(List<VentanaProyectoTO> listaVentProy) {
		int value = 0;
		for (VentanaProyectoTO im : listaVentProy) {
			if (value < im.getCantidadVentana()) {
				value = im.getCantidadVentana();
			}
		}
		return value;
	}

	public List<VentanaProyectoTO> getListaVentProy() {
		return listaVentProy;
	}

	public void setListaVentProy(List<VentanaProyectoTO> listaVentProy) {
		this.listaVentProy = listaVentProy;
	}
	
	public BarChartModel getModel() {
		return model;
	}

	public VentanaProyectoTO getVentProy() {
		return ventProy;
	}

	public void setVentProy(VentanaProyectoTO ventProy) {
		this.ventProy = ventProy;
	}

	public void setModel(BarChartModel model) {
		this.model = model;
	}

}
