package cl.valposystems.sgi.business.sbean;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;

import cl.valposystems.sgi.dao.impl.DAOUsuario;
import cl.valposystems.sgi.exception.ServicioNoDisponibleException;
import cl.valposystems.sgi.to.UsuarioTO;

@Stateless
@LocalBean
@TransactionManagement(TransactionManagementType.BEAN)
public class UsuarioSessionBean{

	private DAOUsuario dao; 
	
	@PostConstruct
	public void init() {
		try {
			dao = new DAOUsuario();
		} catch (ServicioNoDisponibleException e) {
			System.err.println(e);
		}
	
	}
	
	public UsuarioSessionBean() {}
	
	public List<UsuarioTO> obtenerUsuarios() throws ServicioNoDisponibleException {
		List<UsuarioTO> listaUsuariosTO = null;
		try {
			listaUsuariosTO = dao.findAllUsuarios();
			
		} catch (ServicioNoDisponibleException e) {
			throw new ServicioNoDisponibleException(e);
		}
		return listaUsuariosTO;
	}
	
	public boolean eliminarUsuario(UsuarioTO usuario) throws ServicioNoDisponibleException {
		boolean result = false;
		usuario.setVigenciaUsuario(0);
		try {
			dao.deleteUsuario(usuario);
		}catch(ServicioNoDisponibleException e) {
			throw new ServicioNoDisponibleException(e);
		}
		return result;
	}
	
	public boolean insertarUsuario(UsuarioTO usuario) throws ServicioNoDisponibleException{
		boolean result = false;
		try {
			//usuario.setIdentRol(1);
			dao.insertUsuario(usuario);
		}catch(ServicioNoDisponibleException e) {
			throw new ServicioNoDisponibleException(e);
		}
		return result;
	}
	
	public void modificarUsuario(UsuarioTO usuario) throws ServicioNoDisponibleException{
		try {
			dao.updateUsuario(usuario);
		}catch(ServicioNoDisponibleException e) {
			throw new ServicioNoDisponibleException(e);
		}
	}
	
	public Integer buscarUsuario(UsuarioTO usuario) throws ServicioNoDisponibleException {
		Integer result = 0;
		try {
			result = dao.findUsuarioByRut(usuario);
		}catch(ServicioNoDisponibleException e) {
			throw new ServicioNoDisponibleException(e);
		}
		return result;
	}
	
	public UsuarioTO seleccionarUsuarioPorId(Integer id) throws ServicioNoDisponibleException{
		UsuarioTO result = null;
		try {
			result = dao.findUsuarioById(id);
		}catch(ServicioNoDisponibleException e){
			throw new ServicioNoDisponibleException(e);
		}
		return result;
	}
	
	public boolean validarRut(String rut) throws ServicioNoDisponibleException {	
		boolean validacion = false;
		String[] guion = rut.split("-");
		try {
			if ((guion.length == 2) && (guion[1].length() == 1) && (!rut.contains(" "))){
				rut = rut.toUpperCase();
				rut = rut.replace(".", "");
				rut = rut.replace("-", "");
				
				int rutAux = Integer.parseInt(rut.substring(0, rut.length() - 1));

				char dv = rut.charAt(rut.length() - 1);

				int m = 0, s = 1;
				for (; rutAux != 0; rutAux /= 10) {
					s = (s + rutAux % 10 * (9 - m++ % 6)) % 11;
				}
				if (dv == (char) (s != 0 ? s + 47 : 75)) {
					validacion = true;
				}				
			}
			else
			{
				validacion = false;
			}
			

		} catch (java.lang.NumberFormatException e) {
			throw new ServicioNoDisponibleException(e);
		} catch (Exception e) {
			throw new ServicioNoDisponibleException(e);
		}
		return validacion;		
	}
	
}
