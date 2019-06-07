package cl.valposystems.sgi.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;

import cl.valposystems.sgi.dao.config.MyBatis;
import cl.valposystems.sgi.dao.mapper.VentanaProyectoMapper;
import cl.valposystems.sgi.exception.ServicioNoDisponibleException;
import cl.valposystems.sgi.to.VentanaProyectoTO;

public class DAOVentanaProyecto implements  VentanaProyectoMapper{
	
	private MyBatis ds = null;
	
	public DAOVentanaProyecto() throws ServicioNoDisponibleException {
		 ds = MyBatis.getInstance();
	}

	@Override
	public List<VentanaProyectoTO> findAllVentanaProyecto(VentanaProyectoTO to) throws ServicioNoDisponibleException {
		List<VentanaProyectoTO> result = new ArrayList<VentanaProyectoTO>();
		SqlSession session =  ds.sqlSessionFactory.openSession();
		try {
			VentanaProyectoMapper mapper = session.getMapper(VentanaProyectoMapper.class);
			result = mapper.findAllVentanaProyecto(to);
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
