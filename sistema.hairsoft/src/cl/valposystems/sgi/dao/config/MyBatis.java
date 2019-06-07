package cl.valposystems.sgi.dao.config;

import java.io.IOException;
import java.io.Reader;
import java.nio.charset.Charset;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import cl.valposystems.sgi.exception.ServicioNoDisponibleException;

public class MyBatis {

	private static final String config = "mybatis-config.xml";
	private static MyBatis instance;
	public SqlSessionFactory sqlSessionFactory;
	
	public static MyBatis getInstance() throws ServicioNoDisponibleException {
		if (instance == null) {
			instance = new MyBatis();
		}
		return instance;
	}
	
	public static MyBatis getInstance(String env) throws ServicioNoDisponibleException {
		if (instance == null) {
			instance = new MyBatis(env);
		}
		return instance;
	}
	
	/**
	 * Configuracion con ambiente por defecto
	 * @throws DAOServicioNoDisponibleException
	 */
	private MyBatis() throws ServicioNoDisponibleException {
		try {
			Resources.setCharset(Charset.forName("UTF-8")); // change the default encoding
			Reader reader = Resources.getResourceAsReader(config);
			sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
		} catch (IOException e) {
			throw new ServicioNoDisponibleException(e);
		} catch (Exception e) {
			throw new ServicioNoDisponibleException(e);
		}
	}
	
	/**
	 * Configuracion seleccionando el ambiente
	 * @param env
	 * @throws DAOServicioNoDisponibleException
	 */
	private MyBatis(String env) throws ServicioNoDisponibleException {

		try {
			sqlSessionFactory = new SqlSessionFactoryBuilder().build(Resources
					.getResourceAsStream(config), env);
		} catch (IOException e) {
			throw new ServicioNoDisponibleException(e);
		} catch (Exception e) {
			throw new ServicioNoDisponibleException(e);
		}
	}	

}