package cl.valposystems.sgi.business.sbean;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Properties;

import javax.annotation.PostConstruct;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;

import org.apache.log4j.Logger;

import cl.valposystems.sgi.dao.impl.DAODescargaActividad;
import cl.valposystems.sgi.exception.ServicioNoDisponibleException;
import cl.valposystems.sgi.mbean.UsuarioMBean;
import cl.valposystems.sgi.to.ActividadTO;
import cl.valposystems.sgi.to.AdjuntoTO;
import cl.valposystems.sgi.to.EscalamientoTO;
import cl.valposystems.sgi.to.ProveedorTO;
import cl.valposystems.sgi.to.UsuarioTO;

@Stateless
@LocalBean
@TransactionManagement(TransactionManagementType.BEAN)
public class DescargaActividadSessionBean {

	final static Logger logger = Logger.getLogger(ActividadSessionBean.class);
	private Properties prop = new Properties();
	
	private DAODescargaActividad dao;
	
	@PostConstruct
	public void init() {
		try {
			try (InputStream input = UsuarioMBean.class.getClassLoader().getResourceAsStream("config.properties")) {
	            if (input == null) {
	            	logger.error("No se ha podido encontrar el archivo properties");
	                return;
	            }
	            prop.load(input);
	        } catch (IOException ex) {
	            ex.printStackTrace();
	        }
			dao = new DAODescargaActividad();
		} catch (ServicioNoDisponibleException e) {
			System.err.println(e);
		}
	}
	
	public String crearRutaCertificados(String nombre) {
		String splitRutas = null;
		String path = null;
		File folderRaiz = null;
		
		if (this.isWindowsOS()){
			splitRutas =  prop.getProperty("split.rutas.archivo.windows");
			path = prop.getProperty("archivo.certificado.servidor.windows") + nombre + splitRutas;
			folderRaiz = new File(prop.getProperty("archivo.certificado.servidor.windows") + nombre);
		}else{
			splitRutas =  prop.getProperty("split.rutas.archivo.unix");
			path = prop.getProperty("archivo.certificado.servidor.unix") + nombre + splitRutas;
			folderRaiz = new File(prop.getProperty("archivo.certificado.servidor.unix") + nombre);
		}
		if (!folderRaiz.isDirectory()) { 
			folderRaiz.mkdir(); 
		}	
		
		return path;
	}
	
	public ActividadTO obtenerActividad(ActividadTO act) throws ServicioNoDisponibleException{
		ActividadTO to = null;
		try {
			to = dao.findActividad(act);
		}catch(ServicioNoDisponibleException e) {
			logger.error("editarActividad ERROR : "+e.getMessage());
			throw new ServicioNoDisponibleException(e);
		}
		return to;
	}
	
	public UsuarioTO obtenerCliente(Integer idActividad) throws ServicioNoDisponibleException{
		UsuarioTO to = null;
		try {
			to = dao.findUsuarioCliente(idActividad);
		}catch(ServicioNoDisponibleException e) {
			logger.error("editarActividad ERROR : "+e.getMessage());
			throw new ServicioNoDisponibleException(e);
		}
		return to;
	}
	
	public UsuarioTO obtenerSupervisor(Integer idActividad) throws ServicioNoDisponibleException{
		UsuarioTO to = null;
		try {
			to = dao.findUsuarioSupervisor(idActividad);
		}catch(ServicioNoDisponibleException e) {
			logger.error("editarActividad ERROR : "+e.getMessage());
			throw new ServicioNoDisponibleException(e);
		}
		return to;
	}
	
	public List<EscalamientoTO> obtenerEscalamiento(Integer idActividad) throws ServicioNoDisponibleException{
		List<EscalamientoTO> to = null;
		try {
			to = dao.findEscalamientos(idActividad);
		}catch(ServicioNoDisponibleException e) {
			logger.error("editarActividad ERROR : "+e.getMessage());
			throw new ServicioNoDisponibleException(e);
		}
		return to;
	}
	
	public List<ProveedorTO> obtenerProveedores(Integer idActividad) throws ServicioNoDisponibleException{
		List<ProveedorTO> to = null;
		try {
			to = dao.findProveedores(idActividad);
		}catch(ServicioNoDisponibleException e) {
			logger.error("editarActividad ERROR : "+e.getMessage());
			throw new ServicioNoDisponibleException(e);
		}
		return to;
	}

	public List<AdjuntoTO> obtenerAdjuntos(Integer idActividad) throws ServicioNoDisponibleException{
		List<AdjuntoTO> to = null;
		try {
			to = dao.findAdjuntos(idActividad);
		}catch(ServicioNoDisponibleException e) {
			logger.error("editarActividad ERROR : "+e.getMessage());
			throw new ServicioNoDisponibleException(e);
		}
		return to;
	}
	
	public String getOS(){
		return System.getProperty("os.name").toLowerCase();
	}
	
	public boolean isWindowsOS(){
		return (this.getOS().indexOf("win") >= 0);
	}
}
