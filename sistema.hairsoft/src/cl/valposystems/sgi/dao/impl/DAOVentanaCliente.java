package cl.valposystems.sgi.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;

import cl.valposystems.sgi.dao.config.MyBatis;
import cl.valposystems.sgi.dao.mapper.VentanaClienteMapper;
import cl.valposystems.sgi.exception.ServicioNoDisponibleException;
import cl.valposystems.sgi.to.VentanaClienteTO;

public class DAOVentanaCliente implements VentanaClienteMapper {
	
private MyBatis ds = null;
	
	public DAOVentanaCliente() throws ServicioNoDisponibleException {
		 ds = MyBatis.getInstance();
	}

	@Override
	public List<VentanaClienteTO> findAllVentanaCliente(VentanaClienteTO to) throws ServicioNoDisponibleException {
		List<VentanaClienteTO> result = new ArrayList<VentanaClienteTO>();
		SqlSession session =  ds.sqlSessionFactory.openSession();
		try {
			VentanaClienteMapper mapper = session.getMapper(VentanaClienteMapper.class);
			result = mapper.findAllVentanaCliente(to);
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
