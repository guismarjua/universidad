package cl.valposystems.sgi.mbean;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import org.primefaces.model.chart.Axis;
import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.BarChartModel;
import org.primefaces.model.chart.ChartSeries;

import cl.valposystems.sgi.business.sbean.ActividadProyectoSessionBean;
import cl.valposystems.sgi.to.ActividadTO;


@Named
@SessionScoped
public class ReporteMBean implements Serializable{


	/**
	 * 
	 */
	private static final long serialVersionUID = -4522398657583519678L;

	//@EJB DAOActividadProyecto daoActProy;
	@EJB ActividadProyectoSessionBean servicio;
	
	//Se declara el model y una lista 
	private BarChartModel modelActProy;
	private List<ActividadTO> listaActv;

	//En el metodo init carga los datos de la lista y llama al metodo que carga el modelo del grafico
	@PostConstruct
	public void init() {
		modelActProy = new BarChartModel();
	}
	//El metodo cargaActProy();
	public void cargaActProy() {
	//Se instancia el modelo
		modelActProy = new BarChartModel();
	//Se crea una serie
	    ChartSeries cantidad = new ChartSeries();
	    cantidad.setLabel("ActvProy");
	//En un for se recorre el arreglo
	    cantidad.set("Hola", 10);
		for(ActividadTO xx:listaActv) {
	//Aqui de la lista se setean los datos eje X, eje Y
			cantidad.set(xx.getNombreProyecto(),xx.getCantidadActv());
		}
		//Se añade la serie al modelo y se setean los titulos
		modelActProy.addSeries(cantidad);
		modelActProy.setTitle("Resumen año ");
		modelActProy.setLegendPosition("ne");
			 Axis xAxis = modelActProy.getAxis(AxisType.X);
			 xAxis.setLabel("Proyecto");


			 Axis yAxis = modelActProy.getAxis(AxisType.Y);
			 yAxis.setLabel("Cantidad");
	}
	public BarChartModel getModelActProy() {
		return modelActProy;
	}
	public void setModelActProy(BarChartModel modelActProy) {
		this.modelActProy = modelActProy;
	}
	public List<ActividadTO> getListaActv() {
		return listaActv;
	}
	public void setListaActv(List<ActividadTO> listaActv) {
		this.listaActv = listaActv;
	}
	
}
