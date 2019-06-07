package cl.valposystems.sgi.dao.impl;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;

import org.apache.ibatis.session.SqlSession;

import cl.valposystems.sgi.dao.config.MyBatis;
import cl.valposystems.sgi.dao.mapper.ActividadClienteMapper;
import cl.valposystems.sgi.exception.ServicioNoDisponibleException;
import cl.valposystems.sgi.to.ActividadClienteTO;

@Stateless
@LocalBean
@TransactionManagement(TransactionManagementType.BEAN)
public class DAOActividadCliente implements ActividadClienteMapper{
	
	private MyBatis ds = null;
	
	public DAOActividadCliente() throws ServicioNoDisponibleException {
		 ds = MyBatis.getInstance();
	}

	@Override
	public List<ActividadClienteTO> findAllActividadCliente(ActividadClienteTO to) throws ServicioNoDisponibleException {
		List<ActividadClienteTO> result = new ArrayList<ActividadClienteTO>();
		SqlSession session =  ds.sqlSessionFactory.openSession();
		try {
			ActividadClienteMapper mapper = session.getMapper(ActividadClienteMapper.class);
			result = mapper.findAllActividadCliente(to);
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
