package cl.valposystems.sgi.to;

import java.io.Serializable;
import java.util.List;

public class UsuarioTO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 346788988717359787L;
	private int idUsuario;
	private String nombreUsuario;
	private String apellidoUsuario;
	private String abreviaturaUsuario;
	private String telefonoUsuario;
	private String emailUsuario;
	private String rutUsuario;
	private String dvUsuario;
	private String contrasenaUsuario;
	private String cargoUsuario;
	private String skypeUsuario;
	private int vigenciaUsuario;
	private String idRol;
	private int identRol;
	
	private int rolSelected;
	private List<RolTO> roles;
	
	public UsuarioTO() {
		super();
	}
	
	public UsuarioTO(int idUsuario, String nombreUsuario, String apellidoUsuario, String abreviaturaUsuario, String telefonoUsuario, String emailUsuario, String rutUsuario,
			String dvUsuario, String contrasenaUsuario, String cargoUsuario, String skypeUsuario, int vigenciaUsuario, String idRol, int identRol) {
		super();
		this.idUsuario = idUsuario;
		this.nombreUsuario = nombreUsuario;
		this.apellidoUsuario = apellidoUsuario;
		this.setAbreviaturaUsuario(abreviaturaUsuario);
		this.telefonoUsuario = telefonoUsuario;
		this.emailUsuario = emailUsuario;
		this.rutUsuario = rutUsuario;
		this.dvUsuario = dvUsuario;
		this.contrasenaUsuario = contrasenaUsuario;
		this.cargoUsuario = cargoUsuario;
		this.skypeUsuario = skypeUsuario;
		this.vigenciaUsuario = vigenciaUsuario;
		this.idRol = idRol;
		this.identRol = identRol;
	}

	public int getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(int idUsuario) {
		this.idUsuario = idUsuario;
	}

	public String getNombreUsuario() {
		return nombreUsuario;
	}

	public void setNombreUsuario(String nombreUsuario) {
		this.nombreUsuario = nombreUsuario;
	}

	public String getApellidoUsuario() {
		return apellidoUsuario;
	}

	public void setApellidoUsuario(String apellidoUsuario) {
		this.apellidoUsuario = apellidoUsuario;
	}

	public String getTelefonoUsuario() {
		return telefonoUsuario;
	}

	public void setTelefonoUsuario(String telefonoUsuario) {
		this.telefonoUsuario = telefonoUsuario;
	}

	public String getEmailUsuario() {
		return emailUsuario;
	}

	public void setEmailUsuario(String emailUsuario) {
		this.emailUsuario = emailUsuario;
	}

	public String getRutUsuario() {
		return rutUsuario;
	}

	public void setRutUsuario(String rutUsuario) {
		this.rutUsuario = rutUsuario;
	}

	public String getDvUsuario() {
		return dvUsuario;
	}

	public void setDvUsuario(String dvUsuario) {
		this.dvUsuario = dvUsuario;
	}

	public String getContrasenaUsuario() {
		return contrasenaUsuario;
	}

	public void setContrasenaUsuario(String contrasenaUsuario) {
		this.contrasenaUsuario = contrasenaUsuario;
	}

	public String getCargoUsuario() {
		return cargoUsuario;
	}

	public void setCargoUsuario(String cargoUsuario) {
		this.cargoUsuario = cargoUsuario;
	}

	public String getSkypeUsuario() {
		return skypeUsuario;
	}

	public void setSkypeUsuario(String skypeUsuario) {
		this.skypeUsuario = skypeUsuario;
	}

	public int getVigenciaUsuario() {
		return vigenciaUsuario;
	}

	public void setVigenciaUsuario(int vigenciaUsuario) {
		this.vigenciaUsuario = vigenciaUsuario;
	}

	public String getIdRol() {
		return idRol;
	}

	public void setIdRol(String idRol) {
		this.idRol = idRol;
	}

	public String getAbreviaturaUsuario() {
		return abreviaturaUsuario;
	}

	public void setAbreviaturaUsuario(String abreviaturaUsuario) {
		this.abreviaturaUsuario = abreviaturaUsuario;
	}

	public int getIdentRol() {
		return identRol;
	}

	public void setIdentRol(int identRol) {
		this.identRol = identRol;
	}

	public int getRolSelected() {
		return rolSelected;
	}

	public void setRolSelected(int rolSelected) {
		this.rolSelected = rolSelected;
	}

	public List<RolTO> getRoles() {
		return roles;
	}

	public void setRoles(List<RolTO> roles) {
		this.roles = roles;
	}

	
}
