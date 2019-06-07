package cl.valposystems.sgi.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;

import cl.valposystems.sgi.dao.config.MyBatis;
import cl.valposystems.sgi.dao.mapper.ActividadItoMapper;
import cl.valposystems.sgi.exception.ServicioNoDisponibleException;
import cl.valposystems.sgi.to.ActividadItoTO;

public class DAOActividadIto implements ActividadItoMapper{
	
	private MyBatis ds = null;
	
	public DAOActividadIto() throws ServicioNoDisponibleException {
		 ds = MyBatis.getInstance();
	}

	@Override
	public List<ActividadItoTO> findAllActividadIto(ActividadItoTO to) throws ServicioNoDisponibleException {
		List<ActividadItoTO> result = new ArrayList<ActividadItoTO>();
		SqlSession session =  ds.sqlSessionFactory.openSession();
		try {
			ActividadItoMapper mapper = session.getMapper(ActividadItoMapper.class);
			result = mapper.findAllActividadIto(to);
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
