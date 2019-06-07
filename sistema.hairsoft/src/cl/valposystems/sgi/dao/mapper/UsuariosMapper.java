package cl.valposystems.sgi.dao.mapper;

import java.util.List;

import cl.valposystems.sgi.exception.ServicioNoDisponibleException;
import cl.valposystems.sgi.to.UsuarioTO;

public interface UsuariosMapper {
	
	public List<UsuarioTO> findAllUsuarios() throws ServicioNoDisponibleException;
	
	public boolean deleteUsuario(UsuarioTO usuario) throws ServicioNoDisponibleException;

	public boolean insertUsuario(UsuarioTO usuario) throws ServicioNoDisponibleException;
	
	public void updateUsuario(UsuarioTO usuario) throws ServicioNoDisponibleException;
	
	public Integer findUsuarioByRut(UsuarioTO usuario) throws ServicioNoDisponibleException;
	
	public UsuarioTO findUsuarioById(Integer id) throws ServicioNoDisponibleException;
}
