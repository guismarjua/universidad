package cl.valposystems.sgi.to;

import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.Serializable;

import javax.faces.context.FacesContext;

import org.apache.log4j.Logger;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import org.primefaces.model.UploadedFile;

public class AdjuntoTO implements Serializable{
	final static Logger logger = Logger.getLogger(AdjuntoTO.class);
	/**
	 * 
	 */
	private static final long serialVersionUID = -164617908325129466L;
	private int idAdjunto;
	private String nombreAdjunto;
	private String descripcionAdjunto;
	private UploadedFile archivo;
	private String urlAdjunto;
	private String fileString;
	byte[] fileContent;
	private StreamedContent streamedContent;
	
	public String getNombreAdjunto() {
		return nombreAdjunto;
	}
	public void setNombreAdjunto(String nombreAdjunto) {
		this.nombreAdjunto = nombreAdjunto;
	}
	public String getDescripcionAdjunto() {
		return descripcionAdjunto;
	}
	public void setDescripcionAdjunto(String descripcionAdjunto) {
		this.descripcionAdjunto = descripcionAdjunto;
	}
	public UploadedFile getArchivo() {
		return archivo;
	}
	public void setArchivo(UploadedFile archivo) {
		this.archivo = archivo;
	}
	public String getUrlAdjunto() {
		return urlAdjunto;
	}
	public void setUrlAdjunto(String urlAdjunto) {
		this.urlAdjunto = urlAdjunto;
	}
	public org.primefaces.model.StreamedContent getStreamedContent() {
		if(archivo!=null) {
		logger.info("Clase AdjuntoTO, archivo != null");
		return new DefaultStreamedContent(new ByteArrayInputStream(archivo.getContents()), archivo.getContentType());
		}
		else {
			logger.info("Clase AdjuntoTO, archivo == null");
			String path = urlAdjunto;
	        String contentType = FacesContext.getCurrentInstance().getExternalContext().getMimeType(path);
			try {
				return new DefaultStreamedContent(new FileInputStream(path), contentType);
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				logger.error("Error al retornar DefaultStreamedContent : "+e.getMessage());
			}
		}
		return streamedContent;
		}
	public void setStreamedContent(org.primefaces.model.StreamedContent streamedContent) {
		this.streamedContent = streamedContent;
	}
	public String getFileString() {
		return fileString;
	}
	public void setFileString(String fileString) {
		this.fileString = fileString;
	}
	public byte[] getFileContent() {
		return fileContent;
	}
	public void setFileContent(byte[] fileContent) {
		this.fileContent = fileContent;
	}
	public int getIdAdjunto() {
		return idAdjunto;
	}
	public void setIdAdjunto(int idAdjunto) {
		this.idAdjunto = idAdjunto;
	}

}
