package cl.valposystems.sgi.mbean;

import java.io.Serializable;
import java.util.List;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.BarChartModel;
import org.primefaces.model.chart.ChartSeries;

import cl.valposystems.sgi.business.sbean.ActividadClienteSessionBean;
import cl.valposystems.sgi.exception.ServicioNoDisponibleException;
import cl.valposystems.sgi.to.ActividadClienteTO;

@Named
@SessionScoped
public class ActividadClienteMBean implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -1719457436822565659L;

	@EJB ActividadClienteSessionBean servicio;
	
	private BarChartModel model = null;
	private List<ActividadClienteTO> listaActvClie= null;
	private ActividadClienteTO listaActvClieFecha = null;
	

	public void inicializador() {
		listaActvClieFecha = new ActividadClienteTO();
		if(listaActvClie != null && !listaActvClie.isEmpty()) {
			listaActvClie.clear();
		}
		model = new BarChartModel();
		model.setTitle("Sin registros");

		ChartSeries cs = new ChartSeries();
		cs.set("Clientes", 0);

		model.addSeries(cs);
		model.getAxis(AxisType.Y).setLabel("Cantidad de Actividades");
		model.getAxis(AxisType.Y).setMin(0);
		model.getAxis(AxisType.Y).setMax(30);		
	}
	
	public void cargarGraficos() throws ServicioNoDisponibleException {
		this.llamarListaActividadCliente();
		model = this.crearModel();    	
    	model.setAnimate(true);	
	}
	
	
	public void llamarListaActividadCliente() throws ServicioNoDisponibleException {
		if(listaActvClie != null && !listaActvClie.isEmpty()) {
			listaActvClie.clear();
		}
		this.listaActvClie = servicio.obtenerActividadClientes(listaActvClieFecha);		 
	}
	
    public BarChartModel crearModel() throws ServicioNoDisponibleException {
    	BarChartModel model = new BarChartModel();
    	
    	model.setAnimate(true);
    	
    	ChartSeries cs = new ChartSeries();     
        if (this.listaActvClie != null && !this.listaActvClie.isEmpty()) {
        	model.setTitle("Cantidad de Actividades Por Cliente");
	        for (ActividadClienteTO im : this.listaActvClie) {
	        	cs.set(im.getNombreCliente(), im.getCantActivClie());
	        	
	        }
	        model.addSeries(cs);
		       
	        
            model.setLegendCols(2);	
            
            model.getAxis(AxisType.X).setTickAngle(5);
            model.getAxis(AxisType.X).setLabel("Clientes");;
            
            
            model.setShowPointLabels(true);
            model.getAxis(AxisType.Y).setLabel("Cantidad de Actividades");
            model.getAxis(AxisType.Y).setMin(0);
            model.getAxis(AxisType.Y).setTickFormat("%'d");
            model.getAxis(AxisType.Y).setMax(this.getMaxTotal(this.listaActvClie) + 30);
            

        } else {
			model.setTitle("Sin registros");
        	cs.set("Clientes", 0);
	 
	        model.addSeries(cs);
	        model.getAxis(AxisType.Y).setLabel("Cantidad de Actividades");
	        model.getAxis(AxisType.Y).setMin(0);        
	        model.getAxis(AxisType.Y).setMax(1); 

        }
        return model;
        
    }
    
    private int getMaxTotal(List<ActividadClienteTO> listaActvClie) {
    	int value = 0;
        for (ActividadClienteTO im : listaActvClie) {
        	if (value < im.getCantActivClie()) {
        		value = im.getCantActivClie();
        	}
        }
        return value;
    }

	public List<ActividadClienteTO> getListaActvClie() {
		return listaActvClie;
	}

	public void setListaActvClie(List<ActividadClienteTO> listaActvClie) {
		this.listaActvClie = listaActvClie;
	}

	public ActividadClienteTO getListaActvClieFecha() {
		return listaActvClieFecha;
	}

	public void setListaActvClieFecha(ActividadClienteTO listaActvClieFecha) {
		this.listaActvClieFecha = listaActvClieFecha;
	}

	public BarChartModel getModel() 
	{
		return model; 
	}

	public void setModel(BarChartModel model) {
		this.model = model;
	}
	
	
}
