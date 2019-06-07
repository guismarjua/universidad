package cl.valposystems.sgi.to;

import java.io.Serializable;
import java.util.List;

public class CitaTO implements Serializable{


	
	/**
	 * 
	 */
	private static final long serialVersionUID = 8298255137364341087L;

	private int idCita;
	
	private List<ProfesionalTO> idProfesional;
	private List<HoraTO> idHora;
	
	public CitaTO() {
		super();
	}

	public int getIdCita() {
		return idCita;
	}

	public void setIdCita(int idCita) {
		this.idCita = idCita;
	}

	public List<ProfesionalTO> getIdProfesional() {
		return idProfesional;
	}

	public void setIdProfesional(List<ProfesionalTO> idProfesional) {
		this.idProfesional = idProfesional;
	}

	public List<HoraTO> getIdHora() {
		return idHora;
	}

	public void setIdHora(List<HoraTO> idHora) {
		this.idHora = idHora;
	}



	
}
