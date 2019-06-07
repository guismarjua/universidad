package cl.valposystems.sgi.mbean;

import java.io.Serializable;
import java.util.List;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.BarChartModel;
import org.primefaces.model.chart.ChartSeries;

import cl.valposystems.sgi.business.sbean.VentanaClienteSessionBean;
import cl.valposystems.sgi.exception.ServicioNoDisponibleException;
import cl.valposystems.sgi.to.VentanaClienteTO;

@Named
@SessionScoped
public class VentanaClienteMBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -9201458887628310408L;
	
	@EJB
	VentanaClienteSessionBean servicio;

	private BarChartModel model = null;
	private List<VentanaClienteTO> listaVentClien = null;
	private VentanaClienteTO listaVentClieFech = null;
	
	public void inicializador() {
		listaVentClieFech = new VentanaClienteTO();
		if(listaVentClien != null && !listaVentClien.isEmpty()) {
			listaVentClien.clear();
		}
		
		model = new BarChartModel();
		model.setTitle("Sin registros");

		ChartSeries cs = new ChartSeries();
		cs.set("Productos", 0);

		model.addSeries(cs);
		model.getAxis(AxisType.Y).setLabel("Cantidad de Ventas");
		model.getAxis(AxisType.Y).setMin(0);
		model.getAxis(AxisType.Y).setMax(30);
		
	}
	
	public void cargarGraficos() throws ServicioNoDisponibleException {
		this.llamarListaClie();
		model = this.crearModel();    	
    	model.setAnimate(true);		
	}
	
	
	public void llamarListaClie() throws ServicioNoDisponibleException {
		if(listaVentClien != null && !listaVentClien.isEmpty()) {
			listaVentClien.clear();
		}
		this.listaVentClien = servicio.obtenerVentanaClientes(listaVentClieFech);		 
	}

	public BarChartModel crearModel() {
		BarChartModel model = new BarChartModel();

		ChartSeries cs = new ChartSeries();
		if (this.listaVentClien != null && !this.listaVentClien.isEmpty()) {
			model.setTitle("Cantidad de Ventas");
			for (VentanaClienteTO im : this.listaVentClien) {
				cs.set(im.getNombreUsuario(), im.getCantidadVentana());
			}
			
			model.addSeries(cs);

			model.setLegendCols(1);

			model.getAxis(AxisType.X).setTickAngle(7);
			model.getAxis(AxisType.X).setLabel("Clientes");
			
			model.setShowPointLabels(true);
			model.getAxis(AxisType.Y).setLabel("Cantidad de Ventas");
			model.getAxis(AxisType.Y).setMin(0);
			model.getAxis(AxisType.Y).setTickFormat("%'d");
			model.getAxis(AxisType.Y).setMax(this.getMaxTotal(this.listaVentClien) + 5);

		} else {
        	model.setTitle("Sin registros");
        	cs.set("Productos", 0);
			model.addSeries(cs);
			model.getAxis(AxisType.Y).setLabel("Cantidad de Ventas");
			model.getAxis(AxisType.Y).setMin(0);
			model.getAxis(AxisType.Y).setMax(1);
		}
		return model;

	}

	private int getMaxTotal(List<VentanaClienteTO> listaVentClien) {
		int value = 0;
		for (VentanaClienteTO im : listaVentClien) {
			if (value < im.getCantidadVentana()) {
				value = im.getCantidadVentana();
			}
		}
		return value;
	}

	public List<VentanaClienteTO> getListaVentClien() {
		return listaVentClien;
	}

	public void setListaVentClien(List<VentanaClienteTO> listaVentClien) {
		this.listaVentClien = listaVentClien;
	}

	public VentanaClienteTO getListaVentClieFech() {
		return listaVentClieFech;
	}

	public void setListaVentClieFech(VentanaClienteTO listaVentClieFech) {
		this.listaVentClieFech = listaVentClieFech;
	}

	public BarChartModel getModel() {
		return model;
	}

	public void setModel(BarChartModel model) {
		this.model = model;
	}
	
}
