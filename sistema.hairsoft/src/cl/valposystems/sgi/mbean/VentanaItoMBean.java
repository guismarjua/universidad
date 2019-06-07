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

import cl.valposystems.sgi.business.sbean.VentanaItoSessionBean;
import cl.valposystems.sgi.exception.ServicioNoDisponibleException;
import cl.valposystems.sgi.to.VentanaItoTO;

@Named
@SessionScoped
public class VentanaItoMBean implements Serializable {


	/**
	 * 
	 */
	private static final long serialVersionUID = 1539097664205709725L;
	final static Logger logger = Logger.getLogger(VentanaItoMBean.class);
			
	@EJB
	VentanaItoSessionBean servicio;

	private BarChartModel model = null;
	private List<VentanaItoTO> listaVentIto = null;
	private VentanaItoTO listaVenItoFecha = null;
	
	
	public void inicializador() {
		listaVenItoFecha = new VentanaItoTO();
		if(listaVentIto != null && !listaVentIto.isEmpty()) {
			listaVentIto.clear();
		}
		model = new BarChartModel();
		model.setTitle("Sin registros");

		ChartSeries cs = new ChartSeries();
		cs.set("ITOS", 0);

		model.addSeries(cs);
		model.getAxis(AxisType.Y).setLabel("Cantidad de Ventanas");
		model.getAxis(AxisType.Y).setMin(0);
		model.getAxis(AxisType.Y).setMax(30);		
	}
	
	public void cargarGraficos() throws ServicioNoDisponibleException {
		this.llamarListaVentIto();
		model = this.crearModel();    	
    	model.setAnimate(true);	
	}

	
	public void llamarListaVentIto() throws ServicioNoDisponibleException {
		if(listaVentIto != null && !listaVentIto.isEmpty()) {
			listaVentIto.clear();
		}
		this.listaVentIto = servicio.obtenerVetanaItos(listaVenItoFecha);
		logger.info("retornaron de la lista la cantidad de :" + listaVentIto.size());
	}

	public BarChartModel crearModel() throws ServicioNoDisponibleException {
		BarChartModel model = new BarChartModel();
		

		ChartSeries cs = new ChartSeries();
		if (this.listaVentIto != null && !this.listaVentIto.isEmpty()) {
			model.setTitle("Cantidad de Ventana Por Ito");
			for (VentanaItoTO im : this.listaVentIto) {
				cs.set(im.getNombreUsuario(), im.getCantidadVentana());
			}
			model.addSeries(cs);

			model.setLegendCols(2);

			model.getAxis(AxisType.X).setTickAngle(5);
			model.getAxis(AxisType.X).setLabel("ITOS");
			

			model.setShowPointLabels(true);
			model.getAxis(AxisType.Y).setLabel("Cantidad de Ventanas");
			model.getAxis(AxisType.Y).setMin(0);
			model.getAxis(AxisType.Y).setTickFormat("%'d");
			model.getAxis(AxisType.Y).setMax(this.getMaxTotal(this.listaVentIto) + 5);

		} else {

			model.setTitle("Sin registros");
        	cs.set("ITOS", 0);
        	
			model.addSeries(cs);
			model.getAxis(AxisType.Y).setLabel("Cantidad de Ventanas");
			model.getAxis(AxisType.Y).setMin(0);
			model.getAxis(AxisType.Y).setMax(1);
		}
		
		return model;

	}

	private int getMaxTotal(List<VentanaItoTO> lista) {
		int value = 0;
		for (VentanaItoTO im : listaVentIto) {
			if (value < im.getCantidadVentana()) {
				value = im.getCantidadVentana();
			}
		}
		return value;
	}

	public List<VentanaItoTO> getListaVentIto() {
		return listaVentIto;
	}

	public void setListaVentIto(List<VentanaItoTO> listaVentIto) {
		this.listaVentIto = listaVentIto;
	}

	public VentanaItoTO getListaVenItoFecha() {
		return listaVenItoFecha;
	}

	public void setListaVenItoFecha(VentanaItoTO listaVenItoFecha) {
		this.listaVenItoFecha = listaVenItoFecha;
	}

	public BarChartModel getModel() {
		return model;
	}

	public void setModel(BarChartModel model) {
		this.model = model;
	}
	
}
