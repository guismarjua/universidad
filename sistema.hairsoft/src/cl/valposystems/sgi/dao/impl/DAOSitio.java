package cl.valposystems.sgi.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;

import cl.valposystems.sgi.dao.config.MyBatis;
import cl.valposystems.sgi.dao.mapper.SitiosMapper;
import cl.valposystems.sgi.exception.ServicioNoDisponibleException;
import cl.valposystems.sgi.to.SitioTO;


public class DAOSitio implements SitiosMapper{
	
	private MyBatis ds = null;
	
	public DAOSitio() throws ServicioNoDisponibleException {
		 ds = MyBatis.getInstance();
	}

	@Override
	public List<SitioTO> findAllSitios() throws ServicioNoDisponibleException {
		List<SitioTO> result = new ArrayList<SitioTO>();
		SqlSession session =  ds.sqlSessionFactory.openSession();
		try {
			SitiosMapper mapper = session.getMapper(SitiosMapper.class);
			result = mapper.findAllSitios();
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
	public boolean deleteSitio(SitioTO sitio) throws ServicioNoDisponibleException {
		SqlSession session = ds.sqlSessionFactory.openSession();
		boolean resultado = false;
		try{
			SitiosMapper mapper = session.getMapper(SitiosMapper.class);
			resultado = mapper.deleteSitio(sitio);
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
	public boolean insertSitio(SitioTO sitio) throws ServicioNoDisponibleException {
		SqlSession session = ds.sqlSessionFactory.openSession();
		boolean resultado = false;
		try {
			SitiosMapper mapper = session.getMapper(SitiosMapper.class);
			mapper.insertSitio(sitio);
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
	public void updateSitio(SitioTO sitio) throws ServicioNoDisponibleException {
		SqlSession session = ds.sqlSessionFactory.openSession();
		try {
			SitiosMapper mapper = session.getMapper(SitiosMapper.class);
			mapper.updateSitio(sitio);
			session.commit();
		}catch(Exception e) {
			session.rollback();
			throw new ServicioNoDisponibleException(e);
		}finally {
			session.close();
		}
	}

	@Override
	public Integer findSitioBySitioSinergia(SitioTO sitio) throws ServicioNoDisponibleException {
		Integer result = 0;
		SqlSession session = ds.sqlSessionFactory.openSession();
		try {
			SitiosMapper mapper = session.getMapper(SitiosMapper.class);
			result = mapper.findSitioBySitioSinergia(sitio);
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
	public SitioTO findSitioById(Integer id) throws ServicioNoDisponibleException {
		SqlSession session = ds.sqlSessionFactory.openSession();
		SitioTO result = new SitioTO();
		try {
			SitiosMapper mapper = session.getMapper(SitiosMapper.class);
			result = mapper.findSitioById(id);
		}catch(Exception e) {
			throw new ServicioNoDisponibleException(e);
		}finally {
			session.close();
		}
		return result;
	}

}
