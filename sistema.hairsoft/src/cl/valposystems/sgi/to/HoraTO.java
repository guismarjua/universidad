package cl.valposystems.sgi.to;

import java.io.Serializable;
import java.sql.Time;

public class HoraTO implements Serializable{



	/**
	 * 
	 */
	private static final long serialVersionUID = -5854494733151868092L;

	private int idHora;
	private Time hora;
	
	public HoraTO() {
		super();
	}

	public int getIdHora() {
		return idHora;
	}

	public void setIdHora(int idHora) {
		this.idHora = idHora;
	}

	public Time getHora() {
		return hora;
	}

	public void setHora(Time hora) {
		this.hora = hora;
	}



	
}
