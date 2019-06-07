package cl.valposystems.sgi.dao.mapper;


import java.util.Map;

import cl.valposystems.sgi.exception.ServicioNoDisponibleException;
import cl.valposystems.sgi.to.UsuarioTO;

public interface LoginMapper {
	
	public UsuarioTO findUsuario(Map<String,Object> parametros) throws ServicioNoDisponibleException;

}
