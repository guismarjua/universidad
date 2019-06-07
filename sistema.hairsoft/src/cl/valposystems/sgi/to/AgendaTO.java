package cl.valposystems.sgi.to;

import java.io.Serializable;
import java.util.List;

import org.eclipse.persistence.jpa.jpql.parser.DateTime;

public class AgendaTO implements Serializable{


	
	/**
	 * 
	 */
	private static final long serialVersionUID = -5406577850540608045L;
	
	private int idAgenda;
	private DateTime fechaAgenda;
	private String observacion;
	
	private List<CitaTO> idCita;
	private List<ProfesionalTO> idProfesional;
	private List<ServicioTO> idServicio;
	private List<ClienteTO> idCliente;
	private List<ProductoTO> idProducto;
	
	public AgendaTO() {
		super();
	}

	public int getIdAgenda() {
		return idAgenda;
	}

	public void setIdAgenda(int idAgenda) {
		this.idAgenda = idAgenda;
	}

	public DateTime getFechaAgenda() {
		return fechaAgenda;
	}

	public void setFechaAgenda(DateTime fechaAgenda) {
		this.fechaAgenda = fechaAgenda;
	}

	public String getObservacion() {
		return observacion;
	}

	public void setObservacion(String observacion) {
		this.observacion = observacion;
	}

	public List<CitaTO> getIdCita() {
		return idCita;
	}

	public void setIdCita(List<CitaTO> idCita) {
		this.idCita = idCita;
	}

	public List<ProfesionalTO> getIdProfesional() {
		return idProfesional;
	}

	public void setIdProfesional(List<ProfesionalTO> idProfesional) {
		this.idProfesional = idProfesional;
	}

	public List<ServicioTO> getIdServicio() {
		return idServicio;
	}

	public void setIdServicio(List<ServicioTO> idServicio) {
		this.idServicio = idServicio;
	}

	public List<ClienteTO> getIdCliente() {
		return idCliente;
	}

	public void setIdCliente(List<ClienteTO> idCliente) {
		this.idCliente = idCliente;
	}

	public List<ProductoTO> getIdProducto() {
		return idProducto;
	}

	public void setIdProducto(List<ProductoTO> idProducto) {
		this.idProducto = idProducto;
	}
	



	
}
