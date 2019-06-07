package cl.valposystems.sgi.to;

import java.io.Serializable;

import org.eclipse.persistence.jpa.jpql.parser.DateTime;

public class ProductoTO implements Serializable{



	/**
	 * 
	 */
	private static final long serialVersionUID = -3571209882390150743L;

	private int idProducto;
	private String nombreProducto;
	private String marcaProducto;
	private String categoriaProducto;
	private DateTime fechaIngresoProd;
	private int montoProducto;
	private int stockProducto;
	
	public ProductoTO() {
		super();
	}

	public int getIdProducto() {
		return idProducto;
	}

	public void setIdProducto(int idProducto) {
		this.idProducto = idProducto;
	}

	public String getNombreProducto() {
		return nombreProducto;
	}

	public void setNombreProducto(String nombreProducto) {
		this.nombreProducto = nombreProducto;
	}

	public String getMarcaProducto() {
		return marcaProducto;
	}

	public void setMarcaProducto(String marcaProducto) {
		this.marcaProducto = marcaProducto;
	}

	public String getCategoriaProducto() {
		return categoriaProducto;
	}

	public void setCategoriaProducto(String categoriaProducto) {
		this.categoriaProducto = categoriaProducto;
	}

	public DateTime getFechaIngresoProd() {
		return fechaIngresoProd;
	}

	public void setFechaIngresoProd(DateTime fechaIngresoProd) {
		this.fechaIngresoProd = fechaIngresoProd;
	}

	public int getMontoProducto() {
		return montoProducto;
	}

	public void setMontoProducto(int montoProducto) {
		this.montoProducto = montoProducto;
	}

	public int getStockProducto() {
		return stockProducto;
	}

	public void setStockProducto(int stockProducto) {
		this.stockProducto = stockProducto;
	}


	
}
