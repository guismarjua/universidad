package cl.valposystems.sgi.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.log4j.Logger;

import cl.valposystems.sgi.dao.config.MyBatis;
import cl.valposystems.sgi.dao.mapper.ActividadVentanasMapper;
import cl.valposystems.sgi.exception.ServicioNoDisponibleException;
import cl.valposystems.sgi.to.ActividadVentanasTO;

public class DAOActividadVentanas implements ActividadVentanasMapper {
	
	final static Logger logger = Logger.getLogger(DAOGeneric.class);
	
	private MyBatis ds = null;

	public DAOActividadVentanas() throws ServicioNoDisponibleException {
		ds = MyBatis.getInstance();
	}

	@Override
	public List<ActividadVentanasTO> findAllActividadesPorVentana(ActividadVentanasTO actVentana) throws ServicioNoDisponibleException {
		List<ActividadVentanasTO> resultado = new ArrayList<ActividadVentanasTO>();
		SqlSession session =  ds.sqlSessionFactory.openSession();
		try {
			ActividadVentanasMapper mapper = session.getMapper(ActividadVentanasMapper.class);
			resultado = mapper.findAllActividadesPorVentana(actVentana);
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
	public List<ActividadVentanasTO> findAllTotalVentanaRango(ActividadVentanasTO to) throws ServicioNoDisponibleException {
		List<ActividadVentanasTO> result = new ArrayList<ActividadVentanasTO>();
		SqlSession session =  ds.sqlSessionFactory.openSession();
		try {
			ActividadVentanasMapper mapper = session.getMapper(ActividadVentanasMapper.class);
			result = mapper.findAllTotalVentanaRango(to);
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
