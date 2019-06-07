package cl.valposystems.sgi.dao.impl;

import java.util.ArrayList;
import java.util.List;


import org.apache.ibatis.session.SqlSession;

import cl.valposystems.sgi.dao.config.MyBatis;
import cl.valposystems.sgi.dao.mapper.VentanasMapper;
import cl.valposystems.sgi.exception.ServicioNoDisponibleException;
import cl.valposystems.sgi.to.ActividadTO;
import cl.valposystems.sgi.to.VentanaActividadTO;
import cl.valposystems.sgi.to.VentanaTO;


public class DAOVentana implements VentanasMapper{

	private MyBatis ds = null;
	
	public DAOVentana() throws ServicioNoDisponibleException {
		 ds = MyBatis.getInstance();
	}

	@Override
	public List<VentanaTO> findAllVentanas() throws ServicioNoDisponibleException {
		List<VentanaTO> result = new ArrayList<VentanaTO>();
		SqlSession session =  ds.sqlSessionFactory.openSession();
		try {
			VentanasMapper mapper = session.getMapper(VentanasMapper.class);
			result = mapper.findAllVentanas();
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
	public boolean deleteVentana(VentanaTO ventana) throws ServicioNoDisponibleException {
		SqlSession session = ds.sqlSessionFactory.openSession();
		boolean resultado = false;
		try{
			VentanasMapper mapper = session.getMapper(VentanasMapper.class);
			resultado = mapper.deleteVentana(ventana);
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
	public boolean insertVentana(VentanaTO ventana) throws ServicioNoDisponibleException {
		SqlSession session = ds.sqlSessionFactory.openSession();
		boolean resultado = false;
		try {
			VentanasMapper mapper = session.getMapper(VentanasMapper.class);
			mapper.insertVentana(ventana);
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
	public List<ActividadTO> findActividadesSinAsignar() throws ServicioNoDisponibleException {
		List<ActividadTO> result = new ArrayList<ActividadTO>();
		SqlSession session =  ds.sqlSessionFactory.openSession();
		try {
			VentanasMapper mapper = session.getMapper(VentanasMapper.class);
			result = mapper.findActividadesSinAsignar();
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
	public List<ActividadTO> findActividadesAsignadas(int idVentana) throws ServicioNoDisponibleException {
		List<ActividadTO> result = new ArrayList<ActividadTO>();
		SqlSession session =  ds.sqlSessionFactory.openSession();
		try {
			VentanasMapper mapper = session.getMapper(VentanasMapper.class);
			result = mapper.findActividadesAsignadas(idVentana);
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
	public boolean insertVentanaActividad(VentanaActividadTO to) throws ServicioNoDisponibleException {
		SqlSession session = ds.sqlSessionFactory.openSession();
		boolean resultado = false;
		try {
			VentanasMapper mapper = session.getMapper(VentanasMapper.class);
			mapper.insertVentanaActividad(to);
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
	public boolean updateVentanaActividad(VentanaActividadTO to) throws ServicioNoDisponibleException {
		SqlSession session = ds.sqlSessionFactory.openSession();
		boolean resultado = false;
		try {
			VentanasMapper mapper = session.getMapper(VentanasMapper.class);
			mapper.updateVentanaActividad(to);
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
	public void updateFechaModificacionVentana(Integer idVentana) throws ServicioNoDisponibleException {
		SqlSession session = ds.sqlSessionFactory.openSession();
		try {
			VentanasMapper mapper = session.getMapper(VentanasMapper.class);
			mapper.updateFechaModificacionVentana(idVentana);
			session.commit();
		}catch(Exception e) {
			session.rollback();
			throw new ServicioNoDisponibleException(e);
		}finally {
			session.close();
		}
	}

	@Override
	public List<VentanaActividadTO> findAllActividadesPorVentana() throws ServicioNoDisponibleException {
		List<VentanaActividadTO> result = new ArrayList<VentanaActividadTO>();
		SqlSession session =  ds.sqlSessionFactory.openSession();
		try {
			VentanasMapper mapper = session.getMapper(VentanasMapper.class);
			result = mapper.findAllActividadesPorVentana();
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
	public void deleteVentanaActividad(VentanaTO ventana) throws ServicioNoDisponibleException {
		SqlSession session = ds.sqlSessionFactory.openSession();
		try {
			VentanasMapper mapper = session.getMapper(VentanasMapper.class);
			mapper.deleteVentanaActividad(ventana);
			session.commit();
		}catch(Exception e) {
			session.rollback();
			throw new ServicioNoDisponibleException(e);
		}finally {
			session.close();
		}
		
	}

}
