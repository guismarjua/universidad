package cl.valposystems.sgi.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.log4j.Logger;

import cl.valposystems.sgi.dao.config.MyBatis;
import cl.valposystems.sgi.dao.mapper.DescargaActividadMapper;
import cl.valposystems.sgi.exception.ServicioNoDisponibleException;
import cl.valposystems.sgi.to.ActividadTO;
import cl.valposystems.sgi.to.AdjuntoTO;
import cl.valposystems.sgi.to.EscalamientoTO;
import cl.valposystems.sgi.to.ProveedorTO;
import cl.valposystems.sgi.to.UsuarioTO;

public class DAODescargaActividad implements DescargaActividadMapper{

final static Logger logger = Logger.getLogger(DAOActividad.class);
	
	private MyBatis ds = null;
	
	public DAODescargaActividad() throws ServicioNoDisponibleException {
		 ds = MyBatis.getInstance();
	}
	
	@Override
	public ActividadTO findActividad(ActividadTO act) throws ServicioNoDisponibleException {
		ActividadTO result = new ActividadTO();
		SqlSession session =  ds.sqlSessionFactory.openSession();
		try {
			DescargaActividadMapper mapper = session.getMapper(DescargaActividadMapper.class);
			result = mapper.findActividad(act);
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
	public UsuarioTO findUsuarioCliente(Integer idActividad) throws ServicioNoDisponibleException {
		UsuarioTO resultado = new UsuarioTO();
		SqlSession session =  ds.sqlSessionFactory.openSession();
		try {
			DescargaActividadMapper mapper = session.getMapper(DescargaActividadMapper.class);
			resultado = mapper.findUsuarioCliente(idActividad);
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
	public UsuarioTO findUsuarioSupervisor(Integer idActividad) throws ServicioNoDisponibleException {
		UsuarioTO resultado = new UsuarioTO();
		SqlSession session =  ds.sqlSessionFactory.openSession();
		try {
			DescargaActividadMapper mapper = session.getMapper(DescargaActividadMapper.class);
			resultado = mapper.findUsuarioCliente(idActividad);
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
	public List<EscalamientoTO> findEscalamientos(Integer idActividad) throws ServicioNoDisponibleException {
		List<EscalamientoTO> resultado = new ArrayList<EscalamientoTO>();
		SqlSession session =  ds.sqlSessionFactory.openSession();
		try {
			DescargaActividadMapper mapper = session.getMapper(DescargaActividadMapper.class);
			resultado = mapper.findEscalamientos(idActividad);
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
	public List<ProveedorTO> findProveedores(Integer idActividad) throws ServicioNoDisponibleException {
		List<ProveedorTO> resultado = new ArrayList<ProveedorTO>();
		SqlSession session =  ds.sqlSessionFactory.openSession();
		try {
			DescargaActividadMapper mapper = session.getMapper(DescargaActividadMapper.class);
			resultado = mapper.findProveedores(idActividad);
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
	public List<AdjuntoTO> findAdjuntos(Integer idActividad) throws ServicioNoDisponibleException {
		List<AdjuntoTO> resultado = new ArrayList<AdjuntoTO>();
		SqlSession session =  ds.sqlSessionFactory.openSession();
		try {
			DescargaActividadMapper mapper = session.getMapper(DescargaActividadMapper.class);
			resultado = mapper.findAdjuntos(idActividad);
		}catch (Exception e) {
			throw new ServicioNoDisponibleException(e);
		}finally {
			if (session != null) {
				session.close();
			}
		}
		return resultado;
	}
	
}
