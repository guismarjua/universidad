package cl.valposystems.sgi.mbean;

import java.io.Serializable;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.PrimeFaces;

import cl.valposystems.sgi.to.ActividadTO;
import cl.valposystems.sgi.to.ProyectoTO;
import cl.valposystems.sgi.to.SitioTO;


@Named
@SessionScoped
public class RouterMBean implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -7188423633820537149L;
	private String body = null;
	@Inject	SitioMBean sitioMBean;
	@Inject ProyectoMBean proyectoMBean;
	@Inject ActividadMBean actividadMBean;
	@Inject VentanaMBean ventanaMBean;
	@Inject UsuarioMBean usuarioMBean;
	
	@Inject ActividadProyectoMBean actividadProyectoMbean;
	@Inject ActividadVentanasMBean ventanasMBean;
	@Inject VentanaRangoMBean ventanaRangoMBean;
	@Inject ActividadItoMBean actividadItoMBean;
	
	@Inject VentanaClienteMBean ventanaClienteMBean;
	@Inject VentanaItoMBean ventanaItoMBean;
	@Inject VentanaProyectoMBean ventanaProyectoMBean;
	@Inject ActividadClienteMBean actividadClienteMBean;
	@Inject ActividadRangoMBean actividadRangoMBean;
	
	public void moduloPrincipal() {
		setBody("inicio");
	}
	
	public void moduloPrincipalCliente() {
		setBody("inicio");
	}
	
	public void moduloPrincipalITO() {
		setBody("inicio");
	}
	
	public void moduloPrincipalManager() {
		setBody("inicio");
	}
	
	public void modificarActividad(ActividadTO actividad) {
		actividadMBean.cargarMantenedoresModificar(actividad);
		actividadMBean.cargarInfoActividad(actividad);
		setBody("modificar-actividad");
	}
	
	public void moduloMantenedorUsuario() {
		setBody("mantenedor-usuario");
	}
	
	public void manualUsuario() {
		setBody("manual-usuario");
	}
	
	public void moduloAgenda() {
		setBody("modulo-agenda");
	}
	
	public void moduloMantenedorProyecto() {
		setBody("mantenedor-proyecto");
	}
	
	public void moduloMantenedorSitio() {
		sitioMBean.cargaSitios();
		setBody("mantenedor-sitio");
	}
	
	public void moduloGestionVentana() {
		ventanaMBean.inicializar();
		setBody("gestion-ventana");
	}
	
	public void agregarActividad() {
		actividadMBean.cargarMantenedores();
		setBody("agregar-actividad");
	}
	
	public void verActividad() {
		actividadMBean.inicializador();
		setBody("ver-actividad");
	}
	
	public void moduloReportes() {
		actividadProyectoMbean.inicializador();
		ventanaClienteMBean.inicializador();
		ventanaItoMBean.inicializador();
		ventanaProyectoMBean.inicializador();
		ventanaRangoMBean.inicializador();
		actividadClienteMBean.inicializador();
		actividadItoMBean.inicializador();
		actividadRangoMBean.inicializador();
		setBody("modulo-reportes");
	}
	
	public void modificarProyecto(ProyectoTO proyecto) {
		proyectoMBean.cargarMantenedoresModificar(proyecto);
		PrimeFaces.current().executeScript("PF('dlg-modificar-proyecto').show()");
	}

	
	public void moduloDetalleActividad() {
		ventanasMBean.inicializar();
		setBody("detalle-actividad");
	}
	
	public void acercaDe(){
		PrimeFaces.current().executeScript("PF('dialogo-about').show()");
	}
	
	public void agregarUsuario(){
		usuarioMBean.cargarRoles();
		PrimeFaces.current().executeScript("PF('dlg-agregar-usuario').show()");
	}
	
	public void agregarSitio(){
		sitioMBean.cargarMantenedores();
		PrimeFaces.current().executeScript("PF('dialog-mantener-sitio').show()");
	}
	
	public void agregarProyecto(){
		proyectoMBean.cargarMantenedores();
		PrimeFaces.current().executeScript("PF('dialog-mantener-proyecto').show()");
	}
	
	public void modificarSitio(SitioTO sitio) {
		sitioMBean.precargarComunas(sitio);
	}
	
	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}
	
}
