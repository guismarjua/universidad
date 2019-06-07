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

import cl.valposystems.sgi.business.sbean.ActividadProyectoSessionBean;
import cl.valposystems.sgi.exception.ServicioNoDisponibleException;
import cl.valposystems.sgi.to.ActividadTO;

@Named
@SessionScoped
public class ActividadProyectoMBean implements Serializable{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = -5203094777409827459L;

	final static Logger logger = Logger.getLogger(ActividadProyectoMBean.class);
			
	@EJB ActividadProyectoSessionBean servicio;
	
	
	private BarChartModel model = null;
	private List<ActividadTO> listaActv= null;
	private ActividadTO listaActvFecha = null;
	
	
	public void inicializador() {
		logger.info("Inicializando ActividadProyectoMBean");
		listaActvFecha = new ActividadTO();
		if(listaActv != null && !listaActv.isEmpty()) {
			listaActv.clear();
		}
		model = new BarChartModel();
		model.setTitle("Sin registros");

		ChartSeries actPro = new ChartSeries();
		actPro.set("Profesional", 0);
		model.addSeries(actPro);
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
		if(listaActv != null && !listaActv.isEmpty()) {
			listaActv.clear();
		}
		listaActv = servicio.obtenerActividadProyectos(listaActvFecha);
		logger.info("retornaron de la lista la cantidad de :" + listaActv.size());
	}
	
    public BarChartModel crearModel() {
    	BarChartModel model = new BarChartModel();
    	
    	ChartSeries actPro = new ChartSeries();     
    	//StringBuilder colores = new StringBuilder();
        if (this.listaActv != null && !this.listaActv.isEmpty()) {
        	model.setTitle("Cantidad de Ventas");
	        for (ActividadTO im : this.listaActv) {
	        	actPro.set(im.getNombreProyecto(), im.getCantidadActv());   	
	        }
	        
	        model.addSeries(actPro);
            model.setLegendCols(1);
            
            model.getAxis(AxisType.X).setTickAngle(7);
            model.getAxis(AxisType.X).setLabel("Profesional");
            
            model.setShowPointLabels(true);
            model.getAxis(AxisType.Y).setLabel("Cantidad de Ventas");
            model.getAxis(AxisType.Y).setMin(0);
            model.getAxis(AxisType.Y).setTickFormat("%'d");
            model.getAxis(AxisType.Y).setMax(this.getMaxTotal(this.listaActv) + 5);
            logger.info("model creado");
        } else {
        	model.setTitle("Sin registros");
        	actPro.set("Sin registros", 0);
	        model.addSeries(actPro);
	        model.getAxis(AxisType.Y).setLabel("Cantidad de Ventas");
	        model.getAxis(AxisType.Y).setMin(0);        
	        model.getAxis(AxisType.Y).setMax(50); 
	        logger.info("model vacio");
        }  
        return model;
    }
    
    private int getMaxTotal(List<ActividadTO> lista) {
    	int value = 0;
        for (ActividadTO im : lista) {
        	if (value < im.getCantidadActv()) {
        		value = im.getCantidadActv();
        	}
        }
        return value;
    }

	public List<ActividadTO> getListaActv() {
		return listaActv;
	}

	public void setListaActv(List<ActividadTO> listaActv) {
		this.listaActv = listaActv;
	}

	public ActividadTO getListaActvFecha() {
		return listaActvFecha;
	}

	public void setListaActvFecha(ActividadTO listaActvFecha) {
		this.listaActvFecha = listaActvFecha;
	}

	public BarChartModel getModel() {
		logger.info("retornando el model");
		return model;
	}	
	
}

