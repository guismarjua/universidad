package cl.valposystems.sgi.mbean;

import java.io.Serializable;
import java.util.List;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.BarChartModel;
import org.primefaces.model.chart.ChartSeries;

import cl.valposystems.sgi.business.sbean.ActividadItoSessionBean;
import cl.valposystems.sgi.exception.ServicioNoDisponibleException;
import cl.valposystems.sgi.to.ActividadItoTO;


@Named
@SessionScoped
public class ActividadItoMBean implements Serializable{


	/**
	 * 
	 */
	private static final long serialVersionUID = -1719457436822565659L;

	@EJB ActividadItoSessionBean servicio;
	
	
	private BarChartModel model = null;
	private List<ActividadItoTO> listaActvIto= null;
	private ActividadItoTO listaActvItoFecha = null;
	
	
	public void inicializador() {
		listaActvItoFecha = new ActividadItoTO();
		if(listaActvIto != null && !listaActvIto.isEmpty()) {
			listaActvIto.clear();
		}
		model = new BarChartModel();
		model.setTitle("Sin registros");

		ChartSeries cs = new ChartSeries();
		cs.set("ITOS", 0);

		model.addSeries(cs);
		model.getAxis(AxisType.Y).setLabel("Cantidad de Actividades");
		model.getAxis(AxisType.Y).setMin(0);
		model.getAxis(AxisType.Y).setMax(30);		
	}
	
	public void cargarGraficos() throws ServicioNoDisponibleException {
		this.llamarLista();
		model = this.crearModel();    	
    	model.setAnimate(true);	
	}

	
	
	public void llamarLista() throws ServicioNoDisponibleException {
		if(listaActvIto != null && !listaActvIto.isEmpty()) {
			listaActvIto.clear();
		}
		this.listaActvIto = servicio.obtenerActividadItos(listaActvItoFecha);
	}
	
    public BarChartModel crearModel() throws ServicioNoDisponibleException {
    	BarChartModel model = new BarChartModel();
    	
    	model.setAnimate(true);
    	
    	ChartSeries cs = new ChartSeries();     
        if (this.listaActvIto != null && !this.listaActvIto.isEmpty()) {
        	model.setTitle("Cantidad de Actividades Por ITO");
	        for (ActividadItoTO im : this.listaActvIto) {
	        	cs.set(im.getNombreIto(), im.getCantActivIto());
	        	
	        }
	        model.addSeries(cs);
		       
            model.setLegendCols(2);	
            
            model.getAxis(AxisType.X).setTickAngle(5);
            model.getAxis(AxisType.X).setLabel("Nombre Itos");;
            
            
            model.setShowPointLabels(true);
            model.getAxis(AxisType.Y).setLabel("Cantidad de Actividades");
            model.getAxis(AxisType.Y).setMin(0);
            model.getAxis(AxisType.Y).setTickFormat("%'d");
            model.getAxis(AxisType.Y).setMax(this.getMaxTotal(this.listaActvIto) + 5);
            

        } else {
			model.setTitle("Sin registros");
        	cs.set("ITOS", 0);
	 
	        model.addSeries(cs);
	        model.getAxis(AxisType.Y).setLabel("Cantidad de Actividades");
	        model.getAxis(AxisType.Y).setMin(0);        
	        model.getAxis(AxisType.Y).setMax(1); 
        }
        return model;
        
    }
    
    private int getMaxTotal(List<ActividadItoTO> listaActvIto) {
    	int value = 0;
        for (ActividadItoTO im : listaActvIto) {
        	if (value < im.getCantActivIto()) {
        		value = im.getCantActivIto();
        	}
        }
        return value;
    }

	public List<ActividadItoTO> getListaActvIto() {
		return listaActvIto;
	}

	public void setListaActvIto(List<ActividadItoTO> listaActvIto) {
		this.listaActvIto = listaActvIto;
	}

	public ActividadItoTO getListaActvItoFecha() {
		return listaActvItoFecha;
	}

	public void setListaActvItoFecha(ActividadItoTO listaActvItoFecha) {
		this.listaActvItoFecha = listaActvItoFecha;
	}

	public BarChartModel getModel() 
	{
		return model; 
	}

	public void setModel(BarChartModel model) {
		this.model = model;
	}
	
}
