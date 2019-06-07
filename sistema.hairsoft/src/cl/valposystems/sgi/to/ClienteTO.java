package cl.valposystems.sgi.to;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class ClienteTO implements Serializable{


	/**
	 * 
	 */
	private static final long serialVersionUID = -7841439336412554344L;
	
	private int idCliente;
	private String rutCliente;
	private String nombreCliente;
	private int telefonoCliente;
	private String emailCliente;
	private Date fechaNacCliente;
	private String alergias;
	
	private List<AgendaTO> agenda;
	private List<FichaTecnicaTO> fichaTecnica;
	
	public ClienteTO() {
		super();
	}

	public int getIdCliente() {
		return idCliente;
	}

	public void setIdCliente(int idCliente) {
		this.idCliente = idCliente;
	}

	public String getRutCliente() {
		return rutCliente;
	}

	public void setRutCliente(String rutCliente) {
		this.rutCliente = rutCliente;
	}

	public String getNombreCliente() {
		return nombreCliente;
	}

	public void setNombreCliente(String nombreCliente) {
		this.nombreCliente = nombreCliente;
	}

	public int getTelefonoCliente() {
		return telefonoCliente;
	}

	public void setTelefonoCliente(int telefonoCliente) {
		this.telefonoCliente = telefonoCliente;
	}

	public String getEmailCliente() {
		return emailCliente;
	}

	public void setEmailCliente(String emailCliente) {
		this.emailCliente = emailCliente;
	}

	public Date getFechaNacCliente() {
		return fechaNacCliente;
	}

	public void setFechaNacCliente(Date fechaNacCliente) {
		this.fechaNacCliente = fechaNacCliente;
	}

	public String getAlergias() {
		return alergias;
	}

	public void setAlergias(String alergias) {
		this.alergias = alergias;
	}

	public List<AgendaTO> getAgenda() {
		return agenda;
	}

	public void setAgenda(List<AgendaTO> agenda) {
		this.agenda = agenda;
	}

	public List<FichaTecnicaTO> getFichaTecnica() {
		return fichaTecnica;
	}

	public void setFichaTecnica(List<FichaTecnicaTO> fichaTecnica) {
		this.fichaTecnica = fichaTecnica;
	}
	


	
}
