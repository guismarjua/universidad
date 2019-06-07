package cl.valposystems.sgi.mbean;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.BarChartModel;
import org.primefaces.model.chart.ChartSeries;

import cl.valposystems.sgi.business.sbean.ActividadSessionBean;
import cl.valposystems.sgi.exception.ServicioNoDisponibleException;
import cl.valposystems.sgi.to.ActividadTO;

@Named
@SessionScoped
public class ActividadRangoMBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -9201458887628310408L;
	
	@EJB
	ActividadSessionBean servicio;

	private BarChartModel model = null;
	private List<ActividadTO> listaActRango = null;
	private ActividadTO listaActRangoFech = null;
	private Date fechaInicio;
	private Date fechaTermino;
	
	
	public void inicializador() {
		listaActRangoFech = new ActividadTO();
		if(listaActRango != null && !listaActRango.isEmpty()) {
			listaActRango.clear();
		}
		model = new BarChartModel();
		model.setTitle("Sin registros");

		ChartSeries cs = new ChartSeries();
		cs.set("fecha", 0);

		model.addSeries(cs);
		model.getAxis(AxisType.Y).setLabel("Cantidad de Ventas");
		model.getAxis(AxisType.Y).setMin(0);
		model.getAxis(AxisType.Y).setMax(30);		
	}
	
	public void cargarGraficos() throws ServicioNoDisponibleException {
		this.llamarLista();
		model = this.crearModel();    	
    	model.setAnimate(true);	
	}
	
	public void llamarLista() throws ServicioNoDisponibleException {
		if(listaActRango != null && !listaActRango.isEmpty()) {
			listaActRango.clear();
		}
		this.listaActRango = servicio.obtenerActividadRango(listaActRangoFech);
		 
	}

	public BarChartModel crearModel() throws ServicioNoDisponibleException {
		BarChartModel model = new BarChartModel();
		
		ChartSeries cs = new ChartSeries();
		if (this.listaActRango != null && !this.listaActRango.isEmpty()) {
			model.setTitle("Cantidad de Ventas");
			String parametro = null;
			for (ActividadTO im : this.listaActRango) {
				if(listaActRangoFech.getAnnio() != null && listaActRangoFech.getAnnio() != "") {
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
				cs.set(parametro, im.getCantidadActv());
			}
			model.addSeries(cs);
			model.setLegendCols(1);

			
			model.getAxis(AxisType.X).setTickAngle(-90);
			model.getAxis(AxisType.X).setLabel("Fechas");
			model.setShowPointLabels(true);
			model.getAxis(AxisType.Y).setLabel("Cantidad de Ventas");
			model.getAxis(AxisType.Y).setMin(0);
			model.getAxis(AxisType.Y).setMax(this.getMaxTotal(this.listaActRango) + 5);
			model.getAxis(AxisType.X).setLabel("Fecha");
		} else {
			model.setTitle("Sin registros");
        	cs.set("Profesional", 0);

			model.addSeries(cs);
			model.getAxis(AxisType.Y).setLabel("Cantidad Actividades");
			model.getAxis(AxisType.Y).setMin(0);
			model.getAxis(AxisType.Y).setMax(1);
		}
		
		return model;

	}

	private int getMaxTotal(List<ActividadTO> listaActRango) {
		int value = 0;
		for (ActividadTO im : listaActRango) {
			if (value < im.getCantidadActv()) {
				value = im.getCantidadActv();
			}
		}
		return value;
	}

	public Date getFechaInicio() {
		return fechaInicio;
	}

	public void setFechaInicio(Date fechaInicio) {
		this.fechaInicio = fechaInicio;
	}

	public Date getFechaTermino() {
		return fechaTermino;
	}

	public void setFechaTermino(Date fechaTermino) {
		this.fechaTermino = fechaTermino;
	}

	public List<ActividadTO> getListaActRango() {
		return listaActRango;
	}

	public void setListaActRango(List<ActividadTO> listaActRango) {
		this.listaActRango = listaActRango;
	}

	public ActividadTO getListaActRangoFech() {
		return listaActRangoFech;
	}

	public void setListaActRangoFech(ActividadTO listaActRangoFech) {
		this.listaActRangoFech = listaActRangoFech;
	}
	
	public BarChartModel getModel() {
		return model;
	}

	public void setModel(BarChartModel model) {
		this.model = model;
	}
	
}
