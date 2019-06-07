package cl.valposystems.sgi.dao.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.apache.log4j.Logger;

import cl.valposystems.sgi.dao.config.MyBatis;
import cl.valposystems.sgi.dao.mapper.ActividadMapper;
import cl.valposystems.sgi.exception.ServicioNoDisponibleException;
import cl.valposystems.sgi.to.ActividadTO;
import cl.valposystems.sgi.to.EscalamientoTO;

public class DAOActividad implements ActividadMapper{
	final static Logger logger = Logger.getLogger(DAOActividad.class);
	
	private MyBatis ds = null;
	
	public DAOActividad() throws ServicioNoDisponibleException {
		 ds = MyBatis.getInstance();
	}

	@Override
	public List<ActividadTO> findAllActividades() throws ServicioNoDisponibleException {
		List<ActividadTO> result = new ArrayList<ActividadTO>();
		SqlSession session =  ds.sqlSessionFactory.openSession();
		try {
			ActividadMapper mapper = session.getMapper(ActividadMapper.class);
			result = mapper.findAllActividades();
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
	public List<ActividadTO> findAllActividadesIto(Integer idUsuario) throws ServicioNoDisponibleException {
		List<ActividadTO> result = new ArrayList<ActividadTO>();
		SqlSession session =  ds.sqlSessionFactory.openSession();
		try {
			ActividadMapper mapper = session.getMapper(ActividadMapper.class);
			result = mapper.findAllActividadesIto(idUsuario);
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
	public List<ActividadTO> findAllActividadRango(ActividadTO to) throws ServicioNoDisponibleException {
		List<ActividadTO> result = new ArrayList<ActividadTO>();
		SqlSession session =  ds.sqlSessionFactory.openSession();
		try {
			ActividadMapper mapper = session.getMapper(ActividadMapper.class);
			result = mapper.findAllActividadRango(to);
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
	public boolean insertActividad(ActividadTO actividad) throws ServicioNoDisponibleException {
		SqlSession session = ds.sqlSessionFactory.openSession();
		boolean resultado = false;
		try {
			logger.debug("insertActividad -> antes de mapper");
			ActividadMapper mapper = session.getMapper(ActividadMapper.class);
			mapper.insertActividad(actividad);
			logger.debug("insertActividad -> despues de mapper");
			resultado = true;
			session.commit();
		}catch(Exception e) {
			logger.error("insertActividad ERROR : "+e.getMessage());
			session.rollback();
			throw new ServicioNoDisponibleException(e);
		}finally {
			session.close();
		}
		return resultado;
	}
	
	@Override
	public boolean editActividad(ActividadTO actividad) throws ServicioNoDisponibleException {
		SqlSession session = ds.sqlSessionFactory.openSession();
		boolean resultado = false;
		try {
			logger.debug("editarActividad -> antes de mapper");
			ActividadMapper mapper = session.getMapper(ActividadMapper.class);
			mapper.editActividad(actividad);
			logger.debug("editarActividad -> despues de mapper");
			resultado = true;
			session.commit();
		}catch(Exception e) {
			logger.error("editarActividad mapper ERROR : "+e.getMessage());
			session.rollback();
			throw new ServicioNoDisponibleException(e);
		}finally {
			session.close();
		}
		return resultado;
	}

	@Override
	public Integer findIdActividad(ActividadTO actividad) throws ServicioNoDisponibleException {
		Integer result = 0;
		SqlSession session =  ds.sqlSessionFactory.openSession();
		try {
			ActividadMapper mapper = session.getMapper(ActividadMapper.class);
			result = mapper.findIdActividad(actividad);
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
	public boolean insertPersonalPM(Map<String,Object> parametros) throws ServicioNoDisponibleException {
		SqlSession session = ds.sqlSessionFactory.openSession();
		boolean resultado = false;
		try {
			ActividadMapper mapper = session.getMapper(ActividadMapper.class);
			mapper.insertPersonalPM(parametros);
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
	public boolean modificarPersonalPM(Map<String,Object> parametros) throws ServicioNoDisponibleException {
		SqlSession session = ds.sqlSessionFactory.openSession();
		boolean resultado = false;
		try {
			ActividadMapper mapper = session.getMapper(ActividadMapper.class);
			mapper.modificarPersonalPM(parametros);
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
	public boolean insertPersonalSupervisor(Map<String,Object> parametros)	throws ServicioNoDisponibleException {
		SqlSession session = ds.sqlSessionFactory.openSession();
		boolean resultado = false;
		try {
			ActividadMapper mapper = session.getMapper(ActividadMapper.class);
			mapper.insertPersonalSupervisor(parametros);
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
	public boolean modificarPersonalSupervisor(Map<String,Object> parametros)	throws ServicioNoDisponibleException {
		SqlSession session = ds.sqlSessionFactory.openSession();
		boolean resultado = false;
		try {
			ActividadMapper mapper = session.getMapper(ActividadMapper.class);
			mapper.modificarPersonalSupervisor(parametros);
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
	public boolean insertEscalamiento(Map<String, Object> parametros) throws ServicioNoDisponibleException {
		SqlSession session = ds.sqlSessionFactory.openSession();
		boolean resultado = false;
		try {
			ActividadMapper mapper = session.getMapper(ActividadMapper.class);
			mapper.insertEscalamiento(parametros);
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
	public boolean insertProveedores(Map<String, Object> parametros) throws ServicioNoDisponibleException {
		SqlSession session = ds.sqlSessionFactory.openSession();
		boolean resultado = false;
		try {
			ActividadMapper mapper = session.getMapper(ActividadMapper.class);
			mapper.insertProveedores(parametros);
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
	
	public boolean insertAdjuntos(Map<String, Object> parametros) throws ServicioNoDisponibleException {
		SqlSession session = ds.sqlSessionFactory.openSession();
		boolean resultado = false;
		try {
			ActividadMapper mapper = session.getMapper(ActividadMapper.class);
			mapper.insertAdjuntos(parametros);
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
	public boolean insertObservaciones(Map<String,Object> observaciones) throws ServicioNoDisponibleException {
		SqlSession session = ds.sqlSessionFactory.openSession();
		boolean resultado = false;
		try {
			ActividadMapper mapper = session.getMapper(ActividadMapper.class);
			mapper.insertObservaciones(observaciones);
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
	public ActividadTO findDataActividad(ActividadTO actividad) throws ServicioNoDisponibleException {
		SqlSession session = ds.sqlSessionFactory.openSession();
		ActividadTO resultado = new ActividadTO();
		try {
			ActividadMapper mapper = session.getMapper(ActividadMapper.class);
			resultado = mapper.findDataActividad(actividad);
			if(resultado==null) {
				throw new Exception("No se encuentran datos de la actividad.");
			}
		}catch(Exception e) {
			logger.debug("Hubo un error en encontrar los datos de la actividad. Exception: "+e.getMessage());
			session.rollback();
			throw new ServicioNoDisponibleException(e);
		}finally {
			session.close();
		}
		return resultado;
	}
	
	@Override
	public boolean modificarVigenciaProveedores(Map<String,Object> proveedore) throws ServicioNoDisponibleException {
		SqlSession session = ds.sqlSessionFactory.openSession();
		boolean resultado = true;
		try {
			ActividadMapper mapper = session.getMapper(ActividadMapper.class);
			resultado = mapper.modificarVigenciaProveedores(proveedore);
			session.commit();
		}catch(Exception e) {
			resultado = false;
			logger.debug("Hubo un error en modificar la vigencia de los proveedores. Exception: "+e.getMessage());
			session.rollback();
			throw new ServicioNoDisponibleException(e);
		}finally {
			session.close();
		}
		return resultado;
	}
	
	@Override
	public boolean modificarVigenciaAdjuntos(Map<String,Object> adjunto) throws ServicioNoDisponibleException {
		SqlSession session = ds.sqlSessionFactory.openSession();
		boolean resultado = true;
		try {
			ActividadMapper mapper = session.getMapper(ActividadMapper.class);
			resultado = mapper.modificarVigenciaAdjuntos(adjunto);
			session.commit();
		}catch(Exception e) {
			resultado = false;
			logger.debug("Hubo un error en modificar la vigencia de los adjuntos. Exception: "+e.getMessage());
			session.rollback();
			throw new ServicioNoDisponibleException(e);
		}finally {
			session.close();
		}
		return resultado;
	}
	
	@Override
	public boolean modificarObservaciones(Map<String,Object> observaciones) throws ServicioNoDisponibleException {
		SqlSession session = ds.sqlSessionFactory.openSession();
		boolean resultado = false;
		try {
			ActividadMapper mapper = session.getMapper(ActividadMapper.class);
			mapper.modificarObservaciones(observaciones);
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
	public boolean modificarEscalamiento(Map<String, Object> parametros) throws ServicioNoDisponibleException {
		SqlSession session = ds.sqlSessionFactory.openSession();
		boolean resultado = false;
		try {
			ActividadMapper mapper = session.getMapper(ActividadMapper.class);
			mapper.modificarEscalamiento(parametros);
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
	public List<EscalamientoTO> buscarIdEscalamiento(Integer identificadorActividad) throws ServicioNoDisponibleException {
		SqlSession session = ds.sqlSessionFactory.openSession();
		List<EscalamientoTO> resultado = new ArrayList<EscalamientoTO>();
		try {
			ActividadMapper mapper = session.getMapper(ActividadMapper.class);
			resultado = mapper.buscarIdEscalamiento(identificadorActividad);
		}catch(Exception e) {
			logger.debug("Hubo un error en encontrar los datos de la actividad. Exception: "+e.getMessage());
			session.rollback();
			throw new ServicioNoDisponibleException(e);
		}finally {
			session.close();
		}
		return resultado;
	}
}
