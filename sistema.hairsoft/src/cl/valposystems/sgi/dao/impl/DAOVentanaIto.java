package cl.valposystems.sgi.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;

import cl.valposystems.sgi.dao.config.MyBatis;
import cl.valposystems.sgi.dao.mapper.VentanaItoMapper;
import cl.valposystems.sgi.exception.ServicioNoDisponibleException;
import cl.valposystems.sgi.to.VentanaItoTO;

public class DAOVentanaIto implements VentanaItoMapper{

	
	private MyBatis ds = null;
	
	public DAOVentanaIto() throws ServicioNoDisponibleException {
		 ds = MyBatis.getInstance();
	}
	
	@Override
	public List<VentanaItoTO> findAllVentanaIto(VentanaItoTO to) throws ServicioNoDisponibleException {
		List<VentanaItoTO> result = new ArrayList<VentanaItoTO>();
		SqlSession session =  ds.sqlSessionFactory.openSession();
		try {
			VentanaItoMapper mapper = session.getMapper(VentanaItoMapper.class);
			result = mapper.findAllVentanaIto(to);
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
