package cl.valposystems.sgi.to;

import java.io.Serializable;
import java.util.List;

public class SitioTO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1393596553002073101L;
	private int idSitio;
	private String sitioIngenieria;
	private String sitioSinergia;
	private String ubicacionTecnica;
	private String detalleUbicacionTecnica;
	private String direccion;
	private String comuna;
	private String region;
	private String clasificacion;
	private int vigencia;
	private int identRegion;
	private int identComuna;
	private int identClasificacion;
	
	
	private List<RegionTO> regiones;
	private int regionSelected;
	
	private int comunaSelected;
	private int clasificacionSelected;
	
	private List<ComunaTO> comunas;
	private List<ClasificacionTO> clasificaciones;
	
	
	public SitioTO() {
		super();
	}

	public SitioTO(int idSitio, String sitioIngenieria, String sitioSinergia, String ubicacionTecnica,
			String detalleUbicacionTecnica, String direccion, String comuna, String region, String clasificacion,
			int vigencia, int identRegion, int identComuna, int identClasificacion, List<RegionTO> regiones,
			int regionSelected, int comunaSelected, int clasificacionSelected, List<ComunaTO> comunas,
			List<ClasificacionTO> clasificaciones) {
		super();
		this.idSitio = idSitio;
		this.sitioIngenieria = sitioIngenieria;
		this.sitioSinergia = sitioSinergia;
		this.ubicacionTecnica = ubicacionTecnica;
		this.detalleUbicacionTecnica = detalleUbicacionTecnica;
		this.direccion = direccion;
		this.comuna = comuna;
		this.region = region;
		this.clasificacion = clasificacion;
		this.vigencia = vigencia;
		this.identRegion = identRegion;
		this.identComuna = identComuna;
		this.identClasificacion = identClasificacion;
		this.regiones = regiones;
		this.regionSelected = regionSelected;
		this.comunaSelected = comunaSelected;
		this.clasificacionSelected = clasificacionSelected;
		this.comunas = comunas;
		this.clasificaciones = clasificaciones;
	}



	public String getComuna() {
		return comuna;
	}



	public void setComuna(String comuna) {
		this.comuna = comuna;
	}



	public String getRegion() {
		return region;
	}



	public void setRegion(String region) {
		this.region = region;
	}



	public String getClasificacion() {
		return clasificacion;
	}



	public void setClasificacion(String clasificacion) {
		this.clasificacion = clasificacion;
	}



	public int getIdSitio() {
		return idSitio;
	}
	public void setIdSitio(int idSitio) {
		this.idSitio = idSitio;
	}
	public String getSitioIngenieria() {
		return sitioIngenieria;
	}
	public void setSitioIngenieria(String sitioIngenieria) {
		this.sitioIngenieria = sitioIngenieria;
	}
	public String getSitioSinergia() {
		return sitioSinergia;
	}
	public void setSitioSinergia(String sitioSinergia) {
		this.sitioSinergia = sitioSinergia;
	}
	public String getUbicacionTecnica() {
		return ubicacionTecnica;
	}
	public void setUbicacionTecnica(String ubicacionTecnica) {
		this.ubicacionTecnica = ubicacionTecnica;
	}
	public String getDetalleUbicacionTecnica() {
		return detalleUbicacionTecnica;
	}
	public void setDetalleUbicacionTecnica(String detalleUbicacionTecnica) {
		this.detalleUbicacionTecnica = detalleUbicacionTecnica;
	}
	public String getDireccion() {
		return direccion;
	}
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	
	public int getVigencia() {
		return vigencia;
	}
	
	public void setVigencia(int vigencia) {
		this.vigencia = vigencia;
	}
	
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public List<ComunaTO> getComunas() {
		return comunas;
	}

	public void setComunas(List<ComunaTO> comunas) {
		this.comunas = comunas;
	}

	public List<ClasificacionTO> getClasificaciones() {
		return clasificaciones;
	}

	public void setClasificaciones(List<ClasificacionTO> clasificaciones) {
		this.clasificaciones = clasificaciones;
	}

	public int getComunaSelected() {
		return comunaSelected;
	}

	public void setComunaSelected(int comunaSelected) {
		this.comunaSelected = comunaSelected;
	}

	public int getClasificacionSelected() {
		return clasificacionSelected;
	}

	public void setClasificacionSelected(int clasificacionSelected) {
		this.clasificacionSelected = clasificacionSelected;
	}



	public List<RegionTO> getRegiones() {
		return regiones;
	}

	public void setRegiones(List<RegionTO> regiones) {
		this.regiones = regiones;
	}

	public int getRegionSelected() {
		return regionSelected;
	}

	public void setRegionSelected(int regionSelected) {
		this.regionSelected = regionSelected;
	}



	public int getIdentRegion() {
		return identRegion;
	}



	public void setIdentRegion(int identRegion) {
		this.identRegion = identRegion;
	}



	public int getIdentComuna() {
		return identComuna;
	}



	public void setIdentComuna(int identComuna) {
		this.identComuna = identComuna;
	}



	public int getIdentClasificacion() {
		return identClasificacion;
	}



	public void setIdentClasificacion(int identClasificacion) {
		this.identClasificacion = identClasificacion;
	}
	
	
	
	
	
	
}
