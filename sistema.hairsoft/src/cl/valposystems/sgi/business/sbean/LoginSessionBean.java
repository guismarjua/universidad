package cl.valposystems.sgi.business.sbean;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;

import cl.valposystems.sgi.dao.impl.DAOLogin;
import cl.valposystems.sgi.exception.ServicioNoDisponibleException;
import cl.valposystems.sgi.to.UsuarioTO;

@Stateless
@LocalBean
@TransactionManagement(TransactionManagementType.BEAN)

public class LoginSessionBean {

	private DAOLogin dao; 
	
	@PostConstruct
	public void init() {
		try {
			dao = new DAOLogin();
		} catch (ServicioNoDisponibleException e) {
			System.err.println(e);
		}
	
	}
	
	public LoginSessionBean() {}
	
	public UsuarioTO obtenerInformacionUsuario(String usuario, String contrasena) throws ServicioNoDisponibleException{
		UsuarioTO usuarioTO = null;
		Map<String,Object> parametros = new HashMap<String,Object>();
		parametros.put("usuario", usuario);
		parametros.put("contrasena", contrasena);
		try {
			usuarioTO = dao.findUsuario(parametros);
		} catch (ServicioNoDisponibleException e) {
			throw new ServicioNoDisponibleException(e);
		}
		return usuarioTO;
	}
}
