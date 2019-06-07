package cl.valposystems.sgi.mbean;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.BarChartModel;
import org.primefaces.model.chart.ChartSeries;

import cl.valposystems.sgi.business.sbean.ActividadVentanasSessionBean;
import cl.valposystems.sgi.exception.ServicioNoDisponibleException;
import cl.valposystems.sgi.to.ActividadVentanasTO;

@Named
@SessionScoped
public class VentanaRangoMBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -9201458887628310408L;
	
	@EJB
	ActividadVentanasSessionBean servicio;

	private BarChartModel model = null;
	private List<ActividadVentanasTO> listaVentRango = null;
	private ActividadVentanasTO listaVentRangoFech = null;
	
	
	
	public void inicializador() {
		listaVentRangoFech = new ActividadVentanasTO();
		if(listaVentRango != null && !listaVentRango.isEmpty()) {
			listaVentRango.clear();
		}
		
		model = new BarChartModel();
		model.setTitle("Sin registros");

		ChartSeries cs = new ChartSeries();
		cs.set("Ventana", 0);

		model.addSeries(cs);
		model.getAxis(AxisType.Y).setLabel("Cantidad de Ventanas");
		model.getAxis(AxisType.Y).setMin(0);
		model.getAxis(AxisType.Y).setMax(30);			
		
	}
	
	public void cargarGraficos() throws ServicioNoDisponibleException {
		this.llamarListaVentanaRango();
		model = this.crearModel();    	
    	model.setAnimate(true);	

	}
	
	
	public void llamarListaVentanaRango() throws ServicioNoDisponibleException {
		if(listaVentRango != null && !listaVentRango.isEmpty()) {
			listaVentRango.clear();
		}
		this.listaVentRango = servicio.obtenerTotalVentanaRango(listaVentRangoFech);
		 
	}

	public BarChartModel crearModel() throws ServicioNoDisponibleException {
		BarChartModel model = new BarChartModel();
		ChartSeries cs = new ChartSeries();
		if (this.listaVentRango != null && !this.listaVentRango.isEmpty()) {
			String parametro = null;
			model.setTitle("Cantidad de Ventanas Por Rango de Fecha");
			
			for (ActividadVentanasTO im : this.listaVentRango) {
				if(listaVentRangoFech.getAnnio() != null && listaVentRangoFech.getAnnio() != "") {
					switch(im.getParametros()) {
						case "1":
							parametro = "ENERO";
							im.setParametros(parametro);
							break;
						case "2":
							parametro = "FEBRERO";
							im.setParametros(parametro);
							break;
						case "3":
							parametro = "MARZO";
							im.setParametros(parametro);
							break;
						case "4":
							parametro = "ABRIL";
							im.setParametros(parametro);
							break;
						case "5":
							parametro = "MAYO";
							im.setParametros(parametro);
							break;
						case "6":
							parametro = "JUNIO";
							im.setParametros(parametro);
							break;
						case "7":
							parametro = "JULIO";
							im.setParametros(parametro);
							break;
						case "8":
							parametro = "AGOSTO";
							im.setParametros(parametro);
							break;
						case "9":
							parametro = "SEPTIEMBRE";
							im.setParametros(parametro);
							break;
						case "10":
							parametro = "OCTUBRE";
							im.setParametros(parametro);
							break;
						case "11":
							parametro = "NOVIEMBRE";
							im.setParametros(parametro);
							break;
						case "12":
							parametro = "DICIEMBRE";
							im.setParametros(parametro);
							break;
					}
				}
				cs.set(parametro, im.getCantidadVentanaActv());

			}
			model.addSeries(cs);

			model.setLegendCols(1);

			model.getAxis(AxisType.X).setTickAngle(7);
			model.getAxis(AxisType.X).setLabel("mes");
			

			model.setShowPointLabels(true);
			model.getAxis(AxisType.Y).setLabel("Cantidad de Ventanas");
			model.getAxis(AxisType.Y).setMin(0);
			model.getAxis(AxisType.Y).setTickFormat("%'d");
			model.getAxis(AxisType.Y).setMax(this.getMaxTotal(this.listaVentRango) + 5);

		} else {

			model.setTitle("Sin registros");
        	cs.set("Ventana", 0);

			model.addSeries(cs);
			model.getAxis(AxisType.Y).setLabel("Cantidad de Ventanas");
			model.getAxis(AxisType.Y).setMin(0);
			model.getAxis(AxisType.Y).setMax(1);
		}
		return model;

	}

	private int getMaxTotal(List<ActividadVentanasTO> listaVentRango) {
		int value = 0;
		for (ActividadVentanasTO im : listaVentRango) {
			if (value < im.getCantidadVentanaActv()) {
				value = im.getCantidadVentanaActv();
			}
		}
		return value;
	}


	public List<ActividadVentanasTO> getListaVentRango() {
		return listaVentRango;
	}

	public void setListaVentRango(List<ActividadVentanasTO> listaVentRango) {
		this.listaVentRango = listaVentRango;
	}

	public ActividadVentanasTO getListaVentRangoFech() {
		return listaVentRangoFech;
	}

	public void setListaVentRangoFech(ActividadVentanasTO listaVentRangoFech) {
		this.listaVentRangoFech = listaVentRangoFech;
	}
	
	public BarChartModel getModel() {
		return model;
	}

	public void setModel(BarChartModel model) {
		this.model = model;
	}
	
}
