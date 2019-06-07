package cl.valposystems.sgi.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;

import cl.valposystems.sgi.dao.config.MyBatis;
import cl.valposystems.sgi.dao.mapper.UsuariosMapper;
import cl.valposystems.sgi.exception.ServicioNoDisponibleException;
import cl.valposystems.sgi.to.UsuarioTO;

public class DAOUsuario implements UsuariosMapper{
	
	private MyBatis ds = null;
	
	public DAOUsuario() throws ServicioNoDisponibleException {
		 ds = MyBatis.getInstance();
	}

	@Override
	public List<UsuarioTO> findAllUsuarios() throws ServicioNoDisponibleException {
		List<UsuarioTO> result = new ArrayList<UsuarioTO>();
		SqlSession session =  ds.sqlSessionFactory.openSession();
		try {
			UsuariosMapper mapper = session.getMapper(UsuariosMapper.class);
			result = mapper.findAllUsuarios();
		}catch (Exception e) {
			throw new ServicioNoDisponibleException(e);
		}finally {
			if (session != null) {
				session.close();
			}
		}
		return result;
	}

	@Override
	public boolean deleteUsuario(UsuarioTO usuario) throws ServicioNoDisponibleException {
		SqlSession session = ds.sqlSessionFactory.openSession();
		boolean resultado = false;
		try{
			UsuariosMapper mapper = session.getMapper(UsuariosMapper.class);
			resultado = mapper.deleteUsuario(usuario);
			session.commit();			
		}catch (Exception e) {
			throw new ServicioNoDisponibleException(e);
		}finally {
			if (session != null) {
				session.close();
			}
		}				
		return resultado;
	}

	@Override
	public boolean insertUsuario(UsuarioTO usuario) throws ServicioNoDisponibleException {
		SqlSession session = ds.sqlSessionFactory.openSession();
		boolean resultado = false;
		try {
			UsuariosMapper mapper = session.getMapper(UsuariosMapper.class);
			mapper.insertUsuario(usuario);
			resultado = true;
			session.commit();
		}catch(Exception e) {
			session.rollback();
			throw new ServicioNoDisponibleException(e);
		}finally {
			session.close();
		}
		return resultado;
	}

	@Override
	public void updateUsuario(UsuarioTO usuario) throws ServicioNoDisponibleException {
		SqlSession session = ds.sqlSessionFactory.openSession();
		try {
			UsuariosMapper mapper = session.getMapper(UsuariosMapper.class);
			mapper.updateUsuario(usuario);
			session.commit();
		}catch(Exception e) {
			session.rollback();
			throw new ServicioNoDisponibleException(e);
		}finally {
			session.close();
		}
	}

	@Override
	public Integer findUsuarioByRut(UsuarioTO usuario) throws ServicioNoDisponibleException {
		SqlSession session = ds.sqlSessionFactory.openSession();
		Integer resultado;
		try {
			UsuariosMapper mapper = session.getMapper(UsuariosMapper.class);
			resultado = mapper.findUsuarioByRut(usuario);
			
		}catch(Exception e) {
			session.rollback();
			throw new ServicioNoDisponibleException(e);
		}finally {
			session.close();
		}
		return resultado;
		
	}

	@Override
	public UsuarioTO findUsuarioById(Integer id) throws ServicioNoDisponibleException {
		UsuarioTO result = new UsuarioTO();
		SqlSession session =  ds.sqlSessionFactory.openSession();
		try {
			UsuariosMapper mapper = session.getMapper(UsuariosMapper.class);
			result = mapper.findUsuarioById(id);
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