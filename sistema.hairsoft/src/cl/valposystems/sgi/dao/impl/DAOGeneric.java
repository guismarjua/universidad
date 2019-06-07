package cl.valposystems.sgi.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.log4j.Logger;

import cl.valposystems.sgi.dao.config.MyBatis;
import cl.valposystems.sgi.dao.mapper.GenericMapper;
import cl.valposystems.sgi.exception.ServicioNoDisponibleException;
import cl.valposystems.sgi.to.AdjuntoTO;
import cl.valposystems.sgi.to.ClasificacionTO;
import cl.valposystems.sgi.to.ComunaTO;
import cl.valposystems.sgi.to.EscalamientoTO;
import cl.valposystems.sgi.to.ProveedorTO;
import cl.valposystems.sgi.to.ProyectoTO;
import cl.valposystems.sgi.to.RegionTO;
import cl.valposystems.sgi.to.RolTO;
import cl.valposystems.sgi.to.SitioTO;
import cl.valposystems.sgi.to.StatusEspecificosTO;
import cl.valposystems.sgi.to.StatusTO;
import cl.valposystems.sgi.to.SupervisionTO;
import cl.valposystems.sgi.to.UsuarioTO;

public class DAOGeneric implements GenericMapper {
	final static Logger logger = Logger.getLogger(DAOGeneric.class);
	
	private MyBatis ds = null;

	public DAOGeneric() throws ServicioNoDisponibleException {
		ds = MyBatis.getInstance();
	}

	@Override
	public List<RolTO> findAllRoles() throws ServicioNoDisponibleException {
		List<RolTO> result = new ArrayList<RolTO>();
		SqlSession session = ds.sqlSessionFactory.openSession();
		try {
			GenericMapper mapper = session.getMapper(GenericMapper.class);
			result = mapper.findAllRoles();
		} catch (Exception e) {
			throw new ServicioNoDisponibleException(e);
		} finally {
			if (session != null) {
				session.close();
			}
		}
		return result;
	}

	@Override
	public List<RegionTO> findAllRegiones() throws ServicioNoDisponibleException {
		List<RegionTO> result = new ArrayList<RegionTO>();
		SqlSession session = ds.sqlSessionFactory.openSession();
		try {
			GenericMapper mapper = session.getMapper(GenericMapper.class);
			result = mapper.findAllRegiones();
		} catch (Exception e) {
			throw new ServicioNoDisponibleException(e);
		} finally {
			if (session != null) {
				session.close();
			}
		}
		return result;
	}

	@Override
	public List<ComunaTO> findAllComunas(Integer id) throws ServicioNoDisponibleException {
		List<ComunaTO> result = new ArrayList<ComunaTO>();
		SqlSession session = ds.sqlSessionFactory.openSession();
		try {
			GenericMapper mapper = session.getMapper(GenericMapper.class);
			result = mapper.findAllComunas(id);
		} catch (Exception e) {
			throw new ServicioNoDisponibleException(e);
		} finally {
			if (session != null) {
				session.close();
			}
		}
		return result;
	}

	@Override
	public List<ClasificacionTO> findAllClasificaciones() throws ServicioNoDisponibleException {
		List<ClasificacionTO> result = new ArrayList<ClasificacionTO>();
		SqlSession session = ds.sqlSessionFactory.openSession();
		try {
			GenericMapper mapper = session.getMapper(GenericMapper.class);
			result = mapper.findAllClasificaciones();
		} catch (Exception e) {
			throw new ServicioNoDisponibleException(e);
		} finally {
			if (session != null) {
				session.close();
			}
		}
		return result;
	}

	@Override
	public List<UsuarioTO> findAllUsuarios() throws ServicioNoDisponibleException {
		List<UsuarioTO> result = new ArrayList<UsuarioTO>();
		SqlSession session = ds.sqlSessionFactory.openSession();
		try {
			GenericMapper mapper = session.getMapper(GenericMapper.class);
			result = mapper.findAllUsuarios();
		} catch (Exception e) {
			throw new ServicioNoDisponibleException(e);
		} finally {
			if (session != null) {
				session.close();
			}
		}
		return result;
	}

	@Override
	public List<SitioTO> findAllSitios() throws ServicioNoDisponibleException {
		List<SitioTO> result = new ArrayList<SitioTO>();
		SqlSession session = ds.sqlSessionFactory.openSession();
		try {
			GenericMapper mapper = session.getMapper(GenericMapper.class);
			result = mapper.findAllSitios();
		} catch (Exception e) {
			throw new ServicioNoDisponibleException(e);
		} finally {
			if (session != null) {
				session.close();
			}
		}
		return result;
	}

	@Override
	public List<ProyectoTO> findAllProyectos() throws ServicioNoDisponibleException {
		List<ProyectoTO> result = new ArrayList<ProyectoTO>();
		SqlSession session = ds.sqlSessionFactory.openSession();
		try {
			GenericMapper mapper = session.getMapper(GenericMapper.class);
			result = mapper.findAllProyectos();
		} catch (Exception e) {
			throw new ServicioNoDisponibleException(e);
		} finally {
			if (session != null) {
				session.close();
			}
		}
		return result;
	}

	@Override
	public List<SupervisionTO> findAllSupervisiones() throws ServicioNoDisponibleException {
		List<SupervisionTO> result = new ArrayList<SupervisionTO>();
		SqlSession session = ds.sqlSessionFactory.openSession();
		try {
			GenericMapper mapper = session.getMapper(GenericMapper.class);
			result = mapper.findAllSupervisiones();
		} catch (Exception e) {
			throw new ServicioNoDisponibleException(e);
		} finally {
			if (session != null) {
				session.close();
			}
		}
		return result;
	}

	@Override
	public List<StatusTO> findAllStatus() throws ServicioNoDisponibleException {
		List<StatusTO> result = new ArrayList<StatusTO>();
		SqlSession session = ds.sqlSessionFactory.openSession();
		try {
			GenericMapper mapper = session.getMapper(GenericMapper.class);
			result = mapper.findAllStatus();
		} catch (Exception e) {
			throw new ServicioNoDisponibleException(e);
		} finally {
			if (session != null) {
				session.close();
			}
		}
		return result;
	}

	@Override
	public List<UsuarioTO> findAllClientes() throws ServicioNoDisponibleException {
		List<UsuarioTO> result = new ArrayList<UsuarioTO>();
		SqlSession session = ds.sqlSessionFactory.openSession();
		try {
			GenericMapper mapper = session.getMapper(GenericMapper.class);
			result = mapper.findAllClientes();
		} catch (Exception e) {
			throw new ServicioNoDisponibleException(e);
		} finally {
			if (session != null) {
				session.close();
			}
		}
		return result;
	}

	@Override
	public List<UsuarioTO> findUsuariosEscalamiento() throws ServicioNoDisponibleException {
		List<UsuarioTO> result = new ArrayList<UsuarioTO>();
		SqlSession session = ds.sqlSessionFactory.openSession();
		try {
			GenericMapper mapper = session.getMapper(GenericMapper.class);
			result = mapper.findUsuariosEscalamiento();
		} catch (Exception e) {
			throw new ServicioNoDisponibleException(e);
		} finally {
			if (session != null) {
				session.close();
			}
		}
		return result;
	}

	@Override
	public List<StatusEspecificosTO> findAllStatusEspecificos(Integer valor) throws ServicioNoDisponibleException {
		List<StatusEspecificosTO> result = new ArrayList<StatusEspecificosTO>();
		SqlSession session = ds.sqlSessionFactory.openSession();
		try {
			GenericMapper mapper = session.getMapper(GenericMapper.class);
			result = mapper.findAllStatusEspecificos(valor);
			if (result.size() > 0) {
				logger.debug("Hay resultados en la tabla");
				for (int i = 0; i < result.size(); i++) {
					logger.debug("Imprimiendo status especificos " + (i+1) + " de consulta a bd: "+result.get(i).getStatusEspecificos());
				}
			} else {
				logger.debug("No hay resultados en la tabla");
			}
		} catch (Exception e) {
			throw new ServicioNoDisponibleException(e);
		} finally {
			if (session != null) {
				session.close();
			}
		}
		return result;
	}
	
	@Override
	public UsuarioTO findDataClientes(Integer idUsuario) throws ServicioNoDisponibleException {
		UsuarioTO result = new UsuarioTO();
		SqlSession session =  ds.sqlSessionFactory.openSession();
		try {
			GenericMapper mapper = session.getMapper(GenericMapper.class);
			result = mapper.findDataClientes(idUsuario);
		}catch (Exception e) {
			throw new ServicioNoDisponibleException(e);
		}finally {
			if (session != null) {
				session.close();
			}
		}
		return result;
	}

	public List<EscalamientoTO> findEscalamiento(Integer idActividad) throws ServicioNoDisponibleException {
		List<EscalamientoTO> result = new ArrayList<EscalamientoTO>();
		SqlSession session =  ds.sqlSessionFactory.openSession();
		try {
			GenericMapper mapper = session.getMapper(GenericMapper.class);
			result = mapper.findEscalamiento(idActividad);
		}catch (Exception e) {
			logger.error("Error en el servicio findEscalamiento: "+e.getMessage());
			throw new ServicioNoDisponibleException(e);
		}finally {
			if (session != null) {
				session.close();
			}
		}
		return result;
	}

	public List<ProveedorTO> findProveedor(Integer idActividad) throws ServicioNoDisponibleException {
		List<ProveedorTO> result = new ArrayList<ProveedorTO>();
		SqlSession session =  ds.sqlSessionFactory.openSession();
		try {
			GenericMapper mapper = session.getMapper(GenericMapper.class);
			result = mapper.findProveedor(idActividad);
			if(result.size() > 0) {
				logger.info("Hay datos en el resultado de proveedores ");
			}
			else {
				logger.info("No hay datos en el resultado de proveedores ");
			}
		}catch (Exception e) {
			throw new ServicioNoDisponibleException(e);
		}finally {
			if (session != null) {
				session.close();
			}
		}
		return result;
	}
	
	public List<AdjuntoTO> findAdjunto(Integer idActividad) throws ServicioNoDisponibleException {
		List<AdjuntoTO> result = new ArrayList<AdjuntoTO>();
		SqlSession session =  ds.sqlSessionFactory.openSession();
		try {
			GenericMapper mapper = session.getMapper(GenericMapper.class);
			result = mapper.findAdjunto(idActividad);
			if(result.size() > 0) {
				logger.info("Hay datos en el resultado de adjuntos ");
			}
			else {
				logger.info("No hay datos en el resultado de adjuntos ");
			}
		}catch (Exception e) {
			logger.error("Problema al ir a buscar adjuntos. Exception: "+e.getMessage());
			throw new ServicioNoDisponibleException(e);
		}finally {
			if (session != null) {
				session.close();
			}
		}
		return result;
	}

	@Override
	public String findAlias(Integer idUsuario) throws ServicioNoDisponibleException {
		String result = null;
		SqlSession session =  ds.sqlSessionFactory.openSession();
		try {
			GenericMapper mapper = session.getMapper(GenericMapper.class);
			result = mapper.findAlias(idUsuario);
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
	public String findStatusEspecifico(Integer idStatusEspecifico) throws ServicioNoDisponibleException {
		String result = null;
		SqlSession session =  ds.sqlSessionFactory.openSession();
		try {
			GenericMapper mapper = session.getMapper(GenericMapper.class);
			result = mapper.findStatusEspecifico(idStatusEspecifico);
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
	public UsuarioTO findClienteActividad(Integer idUsuario) throws ServicioNoDisponibleException {
		UsuarioTO resultado = new UsuarioTO();
		SqlSession session =  ds.sqlSessionFactory.openSession();
		try {
			GenericMapper mapper = session.getMapper(GenericMapper.class);
			resultado =  mapper.findClienteActividad(idUsuario);
		}catch(Exception e) {
			throw new ServicioNoDisponibleException(e);
		}finally {
			if (session != null) {
				session.close();
			}
		}
		return resultado;
	}

	@Override
	public ProyectoTO findProyectoActividad(Integer idProyecto) throws ServicioNoDisponibleException {
		ProyectoTO resultado = new ProyectoTO();
		SqlSession session =  ds.sqlSessionFactory.openSession();
		try {
			GenericMapper mapper = session.getMapper(GenericMapper.class);
			resultado =  mapper.findProyectoActividad(idProyecto);
		}catch(Exception e) {
			throw new ServicioNoDisponibleException(e);
		}finally {
			if (session != null) {
				session.close();
			}
		}
		return resultado;
	}

	@Override
	public SitioTO findSitioActividad(Integer idSitio) throws ServicioNoDisponibleException {
		SitioTO resultado = new SitioTO();
		SqlSession session =  ds.sqlSessionFactory.openSession();
		try {
			GenericMapper mapper = session.getMapper(GenericMapper.class);
			resultado =  mapper.findSitioActividad(idSitio);
		}catch(Exception e) {
			throw new ServicioNoDisponibleException(e);
		}finally {
			if (session != null) {
				session.close();
			}
		}
		return resultado;
	}

	@Override
	public UsuarioTO findUsuarioSeleccionado(Integer idUsuario) throws ServicioNoDisponibleException {
		UsuarioTO resultado = new UsuarioTO();
		SqlSession session =  ds.sqlSessionFactory.openSession();
		try {
			GenericMapper mapper = session.getMapper(GenericMapper.class);
			resultado =  mapper.findUsuarioSeleccionado(idUsuario);
		}catch(Exception e) {
			throw new ServicioNoDisponibleException(e);
		}finally {
			if (session != null) {
				session.close();
			}
		}
		return resultado;
	}

	@Override
	public ProyectoTO findProyectoSeleccionado(Integer idProyecto) throws ServicioNoDisponibleException {
		ProyectoTO resultado = new ProyectoTO();
		SqlSession session =  ds.sqlSessionFactory.openSession();
		try {
			GenericMapper mapper = session.getMapper(GenericMapper.class);
			resultado =  mapper.findProyectoSeleccionado(idProyecto);
		}catch(Exception e) {
			throw new ServicioNoDisponibleException(e);
		}finally {
			if (session != null) {
				session.close();
			}
		}
		return resultado;
	}

	@Override
	public SitioTO findSitioSeleccionado(Integer idSitio) throws ServicioNoDisponibleException {
		SitioTO resultado = new SitioTO();
		SqlSession session =  ds.sqlSessionFactory.openSession();
		try {
			GenericMapper mapper = session.getMapper(GenericMapper.class);
			resultado =  mapper.findSitioSeleccionado(idSitio);
		}catch(Exception e) {
			throw new ServicioNoDisponibleException(e);
		}finally {
			if (session != null) {
				session.close();
			}
		}
		return resultado;
	}

	@Override
	public List<ComunaTO> preloadComunas(Integer idRegion) throws ServicioNoDisponibleException {
		List<ComunaTO> to = null;
		SqlSession session =  ds.sqlSessionFactory.openSession();
		try {
			GenericMapper mapper = session.getMapper(GenericMapper.class);
			to = mapper.preloadComunas(idRegion);
		}catch(Exception e) {
			throw new ServicioNoDisponibleException(e);
		}finally {
			if(session != null) {
				session.close();
			}
		}
		return to;
	}
}
