package cl.valposystems.sgi.dao.impl;

import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import cl.valposystems.sgi.dao.config.MyBatis;
import cl.valposystems.sgi.dao.mapper.LoginMapper;
import cl.valposystems.sgi.exception.ServicioNoDisponibleException;
import cl.valposystems.sgi.to.UsuarioTO;

public class DAOLogin implements LoginMapper{
	
	private MyBatis ds = null;
	
	public DAOLogin() throws ServicioNoDisponibleException {
		ds = MyBatis.getInstance();
	}

	@Override
	public UsuarioTO findUsuario(Map<String,Object> parametros) throws ServicioNoDisponibleException {
		UsuarioTO result = new UsuarioTO();
		SqlSession session =  ds.sqlSessionFactory.openSession();
		try {
			LoginMapper mapper = session.getMapper(LoginMapper.class);
			result = mapper.findUsuario(parametros);
		}catch (Exception e) {
			throw new ServicioNoDisponibleException(e);
		}finally {
			if (session != null) {
				session.close();
			}
		}
		return result;
	}

}
