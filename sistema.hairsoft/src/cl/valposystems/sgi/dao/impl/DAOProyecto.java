package cl.valposystems.sgi.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;

import cl.valposystems.sgi.dao.config.MyBatis;
import cl.valposystems.sgi.dao.mapper.ProyectosMapper;
import cl.valposystems.sgi.exception.ServicioNoDisponibleException;
import cl.valposystems.sgi.to.ProyectoTO;

public class DAOProyecto implements ProyectosMapper{
	
	private MyBatis ds = null;
	
	public DAOProyecto() throws ServicioNoDisponibleException {
		 ds = MyBatis.getInstance();
	}

	@Override
	public List<ProyectoTO> findAllProyectos() throws ServicioNoDisponibleException {
		List<ProyectoTO> result = new ArrayList<ProyectoTO>();
		SqlSession session =  ds.sqlSessionFactory.openSession();
		try {
			ProyectosMapper mapper = session.getMapper(ProyectosMapper.class);
			result = mapper.findAllProyectos();
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
	public boolean deleteProyecto(ProyectoTO proyecto) throws ServicioNoDisponibleException {
		SqlSession session = ds.sqlSessionFactory.openSession();
		boolean resultado = false;
		try{
			ProyectosMapper mapper = session.getMapper(ProyectosMapper.class);
			resultado = mapper.deleteProyecto(proyecto);
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
	public boolean insertProyecto(ProyectoTO proyecto) throws ServicioNoDisponibleException {
		SqlSession session = ds.sqlSessionFactory.openSession();
		boolean resultado = false;
		try {
			ProyectosMapper mapper = session.getMapper(ProyectosMapper.class);
			mapper.insertProyecto(proyecto);
			resultado = true;
			session.commit();
		}catch(Exception e) {
			throw new ServicioNoDisponibleException(e);
		}finally {
			session.close();
		}
		return resultado;
	}
	
	@Override
	public void updateProyecto(ProyectoTO proyecto) throws ServicioNoDisponibleException {
		SqlSession session = ds.sqlSessionFactory.openSession();
		try {
			ProyectosMapper mapper = session.getMapper(ProyectosMapper.class);
			mapper.updateProyecto(proyecto);
			session.commit();
		}catch(Exception e) {
			session.rollback();
			throw new ServicioNoDisponibleException(e);
		}finally {
			session.close();
		}
	}

}
