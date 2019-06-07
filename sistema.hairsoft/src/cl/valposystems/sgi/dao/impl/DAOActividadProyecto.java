package cl.valposystems.sgi.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;

import cl.valposystems.sgi.dao.config.MyBatis;
import cl.valposystems.sgi.dao.mapper.ActividadProyectoMapper;
import cl.valposystems.sgi.exception.ServicioNoDisponibleException;
import cl.valposystems.sgi.to.ActividadTO;

public class DAOActividadProyecto implements ActividadProyectoMapper{
	
	private MyBatis ds = null;
	
	public DAOActividadProyecto() throws ServicioNoDisponibleException {
		 ds = MyBatis.getInstance();
	}

	@Override
	public List<ActividadTO> findAllActividadProyecto(ActividadTO to) throws ServicioNoDisponibleException {
		List<ActividadTO> result = new ArrayList<ActividadTO>();
		SqlSession session =  ds.sqlSessionFactory.openSession();
		try {
			ActividadProyectoMapper mapper = session.getMapper(ActividadProyectoMapper.class);
			result = mapper.findAllActividadProyecto(to);
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
	public List<ActividadTO> findAllActividadProyectoFecha(ActividadTO to) throws ServicioNoDisponibleException {
		List<ActividadTO> result = new ArrayList<ActividadTO>();
		SqlSession session =  ds.sqlSessionFactory.openSession();
		try {
			ActividadProyectoMapper mapper = session.getMapper(ActividadProyectoMapper.class);
			result = mapper.findAllActividadProyectoFecha(to);
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
