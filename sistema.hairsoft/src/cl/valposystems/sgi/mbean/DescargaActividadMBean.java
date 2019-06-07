package cl.valposystems.sgi.mbean;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.net.MalformedURLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.itextpdf.io.image.ImageData;
import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.io.source.ByteArrayOutputStream;
import com.itextpdf.kernel.color.Color;
import com.itextpdf.kernel.events.Event;
import com.itextpdf.kernel.events.IEventHandler;
import com.itextpdf.kernel.events.PdfDocumentEvent;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.geom.Rectangle;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfPage;
import com.itextpdf.kernel.pdf.PdfReader;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.kernel.pdf.canvas.PdfCanvas;
import com.itextpdf.layout.Canvas;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.border.Border;
import com.itextpdf.layout.element.AreaBreak;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Image;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.layout.LayoutArea;
import com.itextpdf.layout.layout.LayoutContext;
import com.itextpdf.layout.property.HorizontalAlignment;
import com.itextpdf.layout.property.TextAlignment;
import com.itextpdf.layout.property.VerticalAlignment;
import com.itextpdf.layout.renderer.TableRenderer;

import cl.valposystems.sgi.business.sbean.DescargaActividadSessionBean;
import cl.valposystems.sgi.exception.ServicioNoDisponibleException;
import cl.valposystems.sgi.to.ActividadTO;
import cl.valposystems.sgi.to.AdjuntoTO;
import cl.valposystems.sgi.to.EscalamientoTO;
import cl.valposystems.sgi.to.ProveedorTO;
import cl.valposystems.sgi.to.UsuarioTO;

@Named
@SessionScoped
public class DescargaActividadMBean implements Serializable{

	final static Logger logger = Logger.getLogger(DescargaActividadMBean.class);
	/**
	 * 
	 */
	private static final long serialVersionUID = 6120114474042232821L;
	
	@EJB DescargaActividadSessionBean servicio;
	private ActividadTO actividad = new ActividadTO();
	private List<EscalamientoTO> escalamiento = new ArrayList<EscalamientoTO>();
	private UsuarioTO personalCliente = new UsuarioTO();
	private UsuarioTO personalSupervisor = new UsuarioTO();
	private List<ProveedorTO> proveedor = new ArrayList<ProveedorTO>();
	private List<AdjuntoTO> adjunto	= new ArrayList<AdjuntoTO>();
	
	private Properties prop = new Properties();
	public static final String image = "/resources/img/indice.jpg";
	
	@PostConstruct
	public void init() {
		try (InputStream input = (InputStream) DescargaActividadMBean.class.getClassLoader().getResourceAsStream("config.properties")) {
            if (input == null) {
            	logger.error("No se ha podido encontrar el archivo properties");
                return;
            }
            prop.load(input);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
	}
	
	public void certificadoPorActividad(ActividadTO act) throws FileNotFoundException {
		try {
			File file = null;
			file = this.generarPDF(act);
			//download(file.getName(), prop.getProperty("url.files.pdf"));
		} catch (ServicioNoDisponibleException | IOException e) {
			FacesContext context = FacesContext.getCurrentInstance();
			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Error", prop.getProperty("mensaje.error.descarga.pdf")));
		}
	}
	
	public File generarPDF(ActividadTO act) throws ServicioNoDisponibleException, FileNotFoundException{
		File file = null;
		String ruta = null;
		String archivo = null;
		PdfWriter writer = null;
		try {
			this.buscarInformacion(act);
			archivo = new StringBuilder("Reporte_de_Supervision").append(act.getIdActividad()).append(".pdf").toString();
			ruta = servicio.crearRutaCertificados("Actividades");
			writer = new PdfWriter(ruta + archivo);
			PdfDocument pdf = new PdfDocument(writer); 
			Document document = new Document(pdf, PageSize.A4);
			document.setMargins(110, 49, 100, 49);
			TableHeaderEventHandler handler = new TableHeaderEventHandler(document);
	        pdf.addEventHandler(PdfDocumentEvent.END_PAGE, handler);
	        // Hoja 1
	        document.add(new Paragraph());
			document.add(this.tablaProyecto());
			document.add(new Paragraph());
			document.add(this.tablaInicioCRQ());
			document.add(new Paragraph());
			document.add(this.tablaTerminoCRQ());
			document.add(new Paragraph());
			document.add(this.tablaPM());
			document.add(new Paragraph());
			document.add(this.tablaSupervisor());
			document.add(new Paragraph());
			document.add(this.tablaEscalamiento());
			document.add(new Paragraph());
			document.add(this.tablaProveedor());
			document.add(new Paragraph());
			document.add(new AreaBreak());
			// Hoja 2;
			document.add(this.tablaReporte());
			document.add(new AreaBreak());
			// Hoja 3
			document.add(this.tablaImagenes());
			document.close();
			manipulatePdf(ruta+archivo, ruta);
			file = new File(ruta, archivo);
			return file;
		}catch(Exception e){
			throw new ServicioNoDisponibleException(e);
		}
	}
	
	public Table tablaProyecto(){
		Table table = new Table(new float[] { 200, 75, 25, 75, 25, 75, 25});
		Cell header = new Cell(0,7).add(new Paragraph("PROYECTO, MOP, SITIO/NODO")).setTextAlignment(TextAlignment.LEFT).setFontSize(8).setBorderTop(Border.NO_BORDER).setBorderLeft(Border.NO_BORDER).setBorderRight(Border.NO_BORDER);
		table.addCell(header);
		Cell celdaTitulo1 = new Cell().add(new Paragraph("NOMBRE DEL PROYECTO:")).setTextAlignment(TextAlignment.LEFT).setFontSize(8);
		Cell celdaCuerpo1 = new Cell(1,6).add(new Paragraph(actividad.getNombreProyecto() != null ? actividad.getNombreProyecto() : " ")).setTextAlignment(TextAlignment.CENTER).setFontSize(8);
		table.addCell(celdaTitulo1);
		table.addCell(celdaCuerpo1);
		Cell celdaTitulo2 = new Cell().add(new Paragraph("SINTESIS DE ACTIVIDAD:")).setTextAlignment(TextAlignment.LEFT).setFontSize(8);
		Cell celdaCuerpo2 = new Cell(1,6).add(new Paragraph(actividad.getSintesisActividad() != null ? actividad.getSintesisActividad() : " ")).setTextAlignment(TextAlignment.CENTER).setFontSize(8);
		table.addCell(celdaTitulo2);
		table.addCell(celdaCuerpo2);
		Cell celdaTitulo3 = new Cell().add(new Paragraph("TIPO DE SUPERVISIÓN:")).setTextAlignment(TextAlignment.LEFT).setFontSize(8).setBorderBottom(Border.NO_BORDER);
		Cell celdaCuerpo31 = new Cell().add(new Paragraph("EN SITIO")).setTextAlignment(TextAlignment.LEFT).setFontSize(8);
		Cell celdaCuerpo32 = new Cell().add(new Paragraph(!actividad.getSupervisionActividad().equalsIgnoreCase("REMOTA") && !actividad.getSupervisionActividad().equalsIgnoreCase("OTRA")?"X":" ")).setTextAlignment(TextAlignment.CENTER).setFontSize(10);
		Cell celdaCuerpo33 = new Cell().add(new Paragraph("FIJO")).setTextAlignment(TextAlignment.LEFT).setFontSize(8);
		Cell celdaCuerpo34 = new Cell().add(new Paragraph(actividad.getSupervisionActividad().equalsIgnoreCase("FIJO")?"X":" ")).setTextAlignment(TextAlignment.CENTER).setFontSize(10);
		Cell celdaCuerpo35 = new Cell().add(new Paragraph("MOVIL")).setTextAlignment(TextAlignment.LEFT).setFontSize(8);
		Cell celdaCuerpo36 = new Cell().add(new Paragraph(actividad.getSupervisionActividad().equalsIgnoreCase("MOVIL")?"X":" ")).setTextAlignment(TextAlignment.CENTER).setFontSize(10);
		table.addCell(celdaTitulo3);
		table.addCell(celdaCuerpo31);
		table.addCell(celdaCuerpo32);
		table.addCell(celdaCuerpo33);
		table.addCell(celdaCuerpo34);
		table.addCell(celdaCuerpo35);
		table.addCell(celdaCuerpo36);
		Cell celdaTitulo37 = new Cell().add(new Paragraph(" ")).setTextAlignment(TextAlignment.LEFT).setFontSize(8).setBorderTop(Border.NO_BORDER);
		Cell celdaCuerpo38 = new Cell().add(new Paragraph("REMOTA")).setTextAlignment(TextAlignment.LEFT).setFontSize(8);
		Cell celdaCuerpo39 = new Cell().add(new Paragraph(actividad.getSupervisionActividad().equalsIgnoreCase("REMOTA")?"X":" ")).setTextAlignment(TextAlignment.CENTER).setFontSize(10);
		Cell celdaCuerpo310 = new Cell().add(new Paragraph("OTRA")).setTextAlignment(TextAlignment.LEFT).setFontSize(8);
		Cell celdaCuerpo311 = new Cell().add(new Paragraph(actividad.getSupervisionActividad().equalsIgnoreCase("OTRA")?"X":"")).setTextAlignment(TextAlignment.CENTER).setFontSize(10);
		Cell celdaCuerpo312 = new Cell().add(new Paragraph(" ")).setTextAlignment(TextAlignment.LEFT).setFontSize(8).setBorderRight(Border.NO_BORDER);
		Cell celdaCuerpo313 = new Cell().add(new Paragraph(" ")).setTextAlignment(TextAlignment.LEFT).setFontSize(8).setBorderLeft(Border.NO_BORDER);
		table.addCell(celdaTitulo37);
		table.addCell(celdaCuerpo38);
		table.addCell(celdaCuerpo39);
		table.addCell(celdaCuerpo310);
		table.addCell(celdaCuerpo311);
		table.addCell(celdaCuerpo312);
		table.addCell(celdaCuerpo313);
		Cell celdaTitulo4 = new Cell().add(new Paragraph("NOMBRE DEL SITIO/NODO:")).setTextAlignment(TextAlignment.LEFT).setTextAlignment(TextAlignment.LEFT).setFontSize(8);
		Cell celdaCuerpo4 = new Cell(1,6).add(new Paragraph(actividad.getNombreSitio() != null ? actividad.getNombreSitio() : " ")).setTextAlignment(TextAlignment.CENTER).setFontSize(8);
		table.addCell(celdaTitulo4);
		table.addCell(celdaCuerpo4);
		Cell celdaTitulo5 = new Cell().add(new Paragraph("NIVEL DE IMPACTO/RIESGO")).setTextAlignment(TextAlignment.LEFT).setTextAlignment(TextAlignment.LEFT).setFontSize(8);
		Cell celdaCuerpo5 = new Cell(1,6).add(new Paragraph(actividad.getImpactoActividad().toString() != null ? actividad.getImpactoActividad().toString() : " ")).setTextAlignment(TextAlignment.CENTER).setFontSize(8);
		table.addCell(celdaTitulo5);
		table.addCell(celdaCuerpo5);
		return table;
	}
	
	public Table tablaInicioCRQ(){
		SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
		String dateString = format.format(actividad.getFechaInicio());
		Table table = new Table(new float[] { 200, 300});
		Cell header = new Cell(0,3).add(new Paragraph("INICIO DE ACTIVIDAD CRQ")).setTextAlignment(TextAlignment.LEFT).setFontSize(8).setBorderTop(Border.NO_BORDER).setBorderLeft(Border.NO_BORDER).setBorderRight(Border.NO_BORDER);
		table.addCell(header);
		Cell celdaTitulo1 = new Cell().add(new Paragraph("FECHA Y HORA:")).setTextAlignment(TextAlignment.LEFT).setFontSize(8);
		Cell celdaCuerpo1 = new Cell().add(new Paragraph(dateString != null ? dateString : " ")).setTextAlignment(TextAlignment.CENTER).setFontSize(8);
		table.addCell(celdaTitulo1);
		table.addCell(celdaCuerpo1);
		Cell celdaTitulo2 = new Cell().add(new Paragraph("ESTADO DEL SITIO/NODO:")).setTextAlignment(TextAlignment.LEFT).setFontSize(8);
		Cell celdaCuerpo2 = new Cell().add(new Paragraph(actividad.getEstadoInicio() != null ? actividad.getEstadoInicio() : " ")).setTextAlignment(TextAlignment.CENTER).setFontSize(8);
		table.addCell(celdaTitulo2);
		table.addCell(celdaCuerpo2);
		Cell celdaTitulo3 = new Cell().add(new Paragraph("CONFIRMA OPERADOR(A) DEL NOC")).setTextAlignment(TextAlignment.LEFT).setTextAlignment(TextAlignment.LEFT).setFontSize(8);
		Cell celdaCuerpo3 = new Cell().add(new Paragraph(actividad.getOperadorInicio() != null ? actividad.getOperadorInicio() : " ")).setTextAlignment(TextAlignment.CENTER).setFontSize(8);
		table.addCell(celdaTitulo3);
		table.addCell(celdaCuerpo3);
		return table;
	}
	
	public Table tablaTerminoCRQ(){
		SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
		String dateString = format.format(actividad.getFechaTermino());
		Table table = new Table(new float[] { 200, 300});
		Cell header = new Cell(0,3).add(new Paragraph("TERMINO DE ACTIVIDAD CRQ")).setTextAlignment(TextAlignment.LEFT).setFontSize(8).setBorderTop(Border.NO_BORDER).setBorderLeft(Border.NO_BORDER).setBorderRight(Border.NO_BORDER);
		table.addCell(header);
		Cell celdaTitulo1 = new Cell().add(new Paragraph("FECHA Y HORA:")).setTextAlignment(TextAlignment.LEFT).setFontSize(8);
		Cell celdaCuerpo1 = new Cell().add(new Paragraph(dateString != null ? dateString : " ")).setTextAlignment(TextAlignment.CENTER).setFontSize(8);
		table.addCell(celdaTitulo1);
		table.addCell(celdaCuerpo1);
		Cell celdaTitulo2 = new Cell().add(new Paragraph("ESTADO DEL SITIO/NODO:")).setTextAlignment(TextAlignment.LEFT).setFontSize(8);
		Cell celdaCuerpo2 = new Cell().add(new Paragraph(actividad.getEstadoTermino() != null ? actividad.getEstadoTermino()  : " ")).setTextAlignment(TextAlignment.CENTER).setFontSize(8);
		table.addCell(celdaTitulo2);
		table.addCell(celdaCuerpo2);
		Cell celdaTitulo3 = new Cell().add(new Paragraph("CONFIRMA OPERADOR(A) DEL NOC")).setTextAlignment(TextAlignment.LEFT).setFontSize(8);
		Cell celdaCuerpo3 = new Cell().add(new Paragraph(actividad.getOperadorTermino() != null ? actividad.getOperadorTermino()  : " " )).setTextAlignment(TextAlignment.CENTER).setFontSize(8);
		table.addCell(celdaTitulo3);
		table.addCell(celdaCuerpo3);
		Cell celdaTitulo4 = new Cell().add(new Paragraph("LA ACTIVIDAD CONCLUYE DE FORMA")).setTextAlignment(TextAlignment.LEFT).setFontSize(8);
		Cell celdaCuerpo4 = new Cell().add(new Paragraph(actividad.getStatusActividad() != null ? actividad.getStatusActividad()  : " " )).setTextAlignment(TextAlignment.CENTER).setFontSize(8);
		table.addCell(celdaTitulo4);
		table.addCell(celdaCuerpo4);
		return table;
	}
	
	public Table tablaPM() {
		Table table = new Table(new float[] { 125, 125, 125, 125});
		
		Cell header = new Cell(0,5).add(new Paragraph("INFORMACIÓN: PERSONAL DE CLARO PM (INGENIERÍA E IMPLANTACIÓN MÓVIL")).setTextAlignment(TextAlignment.LEFT).setFontSize(8).setBorderTop(Border.NO_BORDER).setBorderLeft(Border.NO_BORDER).setBorderRight(Border.NO_BORDER);
		table.addHeaderCell(header);
		Cell ct1 = new Cell().add(new Paragraph("NOMBRE")).setTextAlignment(TextAlignment.CENTER).setFontSize(8);
		Cell ct2 = new Cell().add(new Paragraph("CARGO")).setTextAlignment(TextAlignment.CENTER).setFontSize(8);
		Cell ct3 = new Cell().add(new Paragraph("TELÉFONO CELULAR")).setTextAlignment(TextAlignment.CENTER).setFontSize(8);
		Cell ct4 = new Cell().add(new Paragraph("CORREO ELECTRONICO")).setTextAlignment(TextAlignment.CENTER).setFontSize(8);

		
		table.addCell(ct1);
		table.addCell(ct2);
		table.addCell(ct3);
		table.addCell(ct4);
		
		Cell celdaTitulo1 = new Cell().add(new Paragraph(personalCliente.getNombreUsuario() +" " + personalCliente.getApellidoUsuario())).setTextAlignment(TextAlignment.CENTER).setFontSize(8);
		Cell celdaTitulo2 = new Cell().add(new Paragraph(personalCliente.getCargoUsuario() != null ? personalCliente.getCargoUsuario() : " ")).setTextAlignment(TextAlignment.CENTER).setFontSize(8);
		Cell celdaTitulo3 = new Cell().add(new Paragraph(personalCliente.getTelefonoUsuario() != null ? personalCliente.getTelefonoUsuario()  : " ")).setTextAlignment(TextAlignment.CENTER).setFontSize(8);
		Cell celdaTitulo4 = new Cell().add(new Paragraph(personalCliente.getEmailUsuario() != null ? personalCliente.getEmailUsuario() : " ")).setTextAlignment(TextAlignment.CENTER).setFontSize(8);

		
		table.addCell(celdaTitulo1);
		table.addCell(celdaTitulo2);
		table.addCell(celdaTitulo3);
		table.addCell(celdaTitulo4);
		
		return table;
	}
	
	public Table tablaSupervisor() {
		Table table = new Table(new float[] { 125, 125, 125, 125});
		
		Cell header = new Cell(0,5).add(new Paragraph("INFORMACIÓN: PERSONAL DE CLARO SUPERVISOR EN SITIO (INGENIERÍA E IMPLANTACIÓN MÓVIL)")).setTextAlignment(TextAlignment.LEFT).setFontSize(8).setBorderTop(Border.NO_BORDER).setBorderLeft(Border.NO_BORDER).setBorderRight(Border.NO_BORDER);
		table.addHeaderCell(header);
		
		Cell ct1 = new Cell().add(new Paragraph("NOMBRE")).setTextAlignment(TextAlignment.CENTER).setFontSize(8);
		Cell ct2 = new Cell().add(new Paragraph("CARGO")).setTextAlignment(TextAlignment.CENTER).setFontSize(8);
		Cell ct3 = new Cell().add(new Paragraph("TELÉFONO CELULAR")).setTextAlignment(TextAlignment.CENTER).setFontSize(8);
		Cell ct4 = new Cell().add(new Paragraph("CORREO ELECTRONICO")).setTextAlignment(TextAlignment.CENTER).setFontSize(8);
		
		table.addCell(ct1);
		table.addCell(ct2);
		table.addCell(ct3);
		table.addCell(ct4);
		
		Cell celdaTitulo1 = new Cell().add(new Paragraph(personalSupervisor.getNombreUsuario() + " "+ personalSupervisor.getApellidoUsuario())).setTextAlignment(TextAlignment.CENTER).setFontSize(8);
		Cell celdaTitulo2 = new Cell().add(new Paragraph(personalSupervisor.getCargoUsuario() != null ? personalSupervisor.getCargoUsuario()  : " " )).setTextAlignment(TextAlignment.CENTER).setFontSize(8);
		Cell celdaTitulo3 = new Cell().add(new Paragraph(personalSupervisor.getTelefonoUsuario() != null ? personalSupervisor.getTelefonoUsuario()  : " " )).setTextAlignment(TextAlignment.CENTER).setFontSize(8);
		Cell celdaTitulo4 = new Cell().add(new Paragraph(personalSupervisor.getEmailUsuario() != null ? personalSupervisor.getEmailUsuario()  : " " )).setTextAlignment(TextAlignment.CENTER).setFontSize(8);

		
		table.addCell(celdaTitulo1);
		table.addCell(celdaTitulo2);
		table.addCell(celdaTitulo3);
		table.addCell(celdaTitulo4);
		
		return table;
	}
	
	public Table tablaEscalamiento() {
		Table table = new Table(new float[] { 125, 125, 125, 125});
		
		Cell header = new Cell(0,5).add(new Paragraph("ESCALAMIENTO PARA ESTA ACTIVIDAD")).setTextAlignment(TextAlignment.LEFT).setFontSize(8).setBorderTop(Border.NO_BORDER).setBorderLeft(Border.NO_BORDER).setBorderRight(Border.NO_BORDER);
		table.addHeaderCell(header);
		
		Cell ct1 = new Cell().add(new Paragraph("NIVEL DE ESCALAMIENTO")).setTextAlignment(TextAlignment.CENTER).setFontSize(8);
		Cell ct2 = new Cell().add(new Paragraph("NOMBRE")).setTextAlignment(TextAlignment.CENTER).setFontSize(8);
		Cell ct3 = new Cell().add(new Paragraph("TELÉFONO CELULAR")).setTextAlignment(TextAlignment.CENTER).setFontSize(8);
		Cell ct4 = new Cell().add(new Paragraph("CORREO ELECTRONICO")).setTextAlignment(TextAlignment.CENTER).setFontSize(8);
		
		table.addCell(ct1);
		table.addCell(ct2);
		table.addCell(ct3);
		table.addCell(ct4);
		
		for(EscalamientoTO to : escalamiento) {
			Cell celdaTitulo1 = new Cell().add(new Paragraph(to.getNivel() != null ? to.getNivel()  : " " )).setTextAlignment(TextAlignment.CENTER).setFontSize(8);
			Cell celdaTitulo2 = new Cell().add(new Paragraph(to.getNombre() != null ? to.getNombre()  : " " )).setTextAlignment(TextAlignment.CENTER).setFontSize(8);
			Cell celdaTitulo3 = new Cell().add(new Paragraph(to.getTelefono() != null ? to.getTelefono()  : " " )).setTextAlignment(TextAlignment.CENTER).setFontSize(8);
			Cell celdaTitulo4 = new Cell().add(new Paragraph(to.getCorreo() != null ? to.getCorreo() : " " )).setTextAlignment(TextAlignment.CENTER).setFontSize(8);
	
			
			table.addCell(celdaTitulo1);
			table.addCell(celdaTitulo2);
			table.addCell(celdaTitulo3);
			table.addCell(celdaTitulo4);
		}
		
		return table;
	}
	
	public Table tablaProveedor() {
		Table table = new Table(new float[] { 100, 100, 100, 100, 100});
		
		Cell header = new Cell(0,6).add(new Paragraph("INFORMACION N° FOLIO: PERSONAL PROVEEDOR/ CLARO EN SITIO Y REMOTO")).setTextAlignment(TextAlignment.LEFT).setFontSize(8).setBorderTop(Border.NO_BORDER).setBorderLeft(Border.NO_BORDER).setBorderRight(Border.NO_BORDER);
		table.addHeaderCell(header);
		
		Cell ct1 = new Cell().add(new Paragraph("NOMBRE")).setTextAlignment(TextAlignment.CENTER).setFontSize(8);
		Cell ct2 = new Cell().add(new Paragraph("EMPRESA")).setTextAlignment(TextAlignment.CENTER).setFontSize(8);
		Cell ct3 = new Cell().add(new Paragraph("CARGO")).setTextAlignment(TextAlignment.CENTER).setFontSize(8);
		Cell ct4 = new Cell().add(new Paragraph("TEL. CELULAR")).setTextAlignment(TextAlignment.CENTER).setFontSize(8);
		Cell ct5 = new Cell().add(new Paragraph("CORREO ELECTRONICO")).setTextAlignment(TextAlignment.CENTER).setFontSize(8);
		
		table.addCell(ct1);
		table.addCell(ct2);
		table.addCell(ct3);
		table.addCell(ct4);
		table.addCell(ct5);
		
		for(ProveedorTO p : proveedor) {
			
			Cell a= new Cell().add(new Paragraph(p.getNombreProveedor() != null ? p.getNombreProveedor()  : " ")).setTextAlignment(TextAlignment.CENTER).setFontSize(8);
			Cell b = new Cell().add(new Paragraph(p.getEmpresaProveedor() != null ? p.getEmpresaProveedor() : " ")).setTextAlignment(TextAlignment.CENTER).setFontSize(8);
			Cell c = new Cell().add(new Paragraph(p.getCargoProveedor() != null? p.getCargoProveedor()  : " ")).setTextAlignment(TextAlignment.CENTER).setFontSize(8);
			Cell d = new Cell().add(new Paragraph(p.getFonoProveedor().toString() != null ? p.getFonoProveedor().toString()  : " ")).setTextAlignment(TextAlignment.CENTER).setFontSize(8);
			Cell e = new Cell().add(new Paragraph(p.getCorreoProveedor() != null ? p.getCorreoProveedor()  : " ")).setTextAlignment(TextAlignment.CENTER).setFontSize(8);
			table.addCell(a);
			table.addCell(b);
			table.addCell(c);
			table.addCell(d);
			table.addCell(e);
		}
	
		return table;
	}

	public Table tablaReporte(){
		Table table = new Table(new float[] { 500});
		Cell celdaTitulo1 = new Cell().add(new Paragraph("REPORTE DE ACTIVIDADES:")).setTextAlignment(TextAlignment.LEFT).setFontSize(8);
		table.addHeaderCell(celdaTitulo1);
		Cell celdaCuerpo= new Cell().add(new Paragraph(actividad.getDetalleTareas())).setTextAlignment(TextAlignment.CENTER).setFontSize(8);
		table.addCell(celdaCuerpo);
		return table;
	}
	
	public Table tablaImagenes(){
		Table table = new Table(new float[] {250,250});
		for(AdjuntoTO adj : adjunto) {
			Cell celdaTitulo1 = new Cell().add(this.tablaImagenes1(adj));
			table.addCell(celdaTitulo1);
		}
		return table;
	}
	
	public Table tablaImagenes1(AdjuntoTO adjunto){
		Table table = new Table(new float[] {250});
		try {
			String imageFile = adjunto.getUrlAdjunto(); 
			ImageData data = ImageDataFactory.create(imageFile);
			Image img = new Image(data); 
			img.setHeight(150);
			img.setWidth(150);
			Cell celdaTitulo1 = new Cell().add(img);
			celdaTitulo1.setBorderLeft(Border.NO_BORDER);
			celdaTitulo1.setBorderRight(Border.NO_BORDER);
			celdaTitulo1.setBorderBottom(Border.NO_BORDER);
            celdaTitulo1.setBorderTop(Border.NO_BORDER);
			table.startNewRow();
			Cell celdaCuerpo1 = new Cell().add(new Paragraph(adjunto.getDescripcionAdjunto())).setTextAlignment(TextAlignment.CENTER).setFontSize(8);
			celdaCuerpo1.setBorderLeft(Border.NO_BORDER);
			celdaCuerpo1.setBorderRight(Border.NO_BORDER);
			celdaCuerpo1.setBorderBottom(Border.NO_BORDER);
			celdaCuerpo1.setBorderTop(Border.NO_BORDER);
			table.addCell(celdaTitulo1);
			table.addCell(celdaCuerpo1);
			table.setHorizontalAlignment(HorizontalAlignment.CENTER);
		}catch(Exception e) {
			
		}
		return table;
	}
	
	public void buscarInformacion(ActividadTO to){
		FacesContext context = FacesContext.getCurrentInstance();
		try {
			actividad = servicio.obtenerActividad(to);
			personalCliente = servicio.obtenerCliente(to.getIdActividad());
			personalSupervisor = servicio.obtenerSupervisor(to.getIdActividad());
			escalamiento = servicio.obtenerEscalamiento(to.getIdActividad());
			proveedor = servicio.obtenerProveedores(to.getIdActividad());
			adjunto = servicio.obtenerAdjuntos(to.getIdActividad());
		}catch(ServicioNoDisponibleException e){
			context.addMessage(null,new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "No logra obtener a los sostenedores."));
		}
	}
	
	protected class TableHeaderEventHandler implements IEventHandler {
		FacesContext facesContext = FacesContext.getCurrentInstance();
        protected Table table;
        protected float tableHeight;
        protected Document doc;
        
 
        public TableHeaderEventHandler(Document doc) throws MalformedURLException {
            this.doc = doc;
            String fecha = null;
        	if(actividad.getFechaModificacion() != null) {
        		fecha = actividad.getFechaModificacion();
        	}else {
        		fecha = actividad.getFechaCreacion();
            }
        	try {
	        	String date = fecha;
				Date date1= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(date); 
				DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
				String strDate = dateFormat.format(date1);
	            table = new Table(new float[] { 3,3,3 });
	            table.setWidth(500);
	            Cell cell;
	            ServletContext servletContext  = (ServletContext) facesContext.getExternalContext().getContext();
				Image img = new Image(ImageDataFactory.create(servletContext.getRealPath(image)));
				img.setWidth(50);
				cell = new Cell().add(img);
	            cell.setBorderRight(Border.NO_BORDER);
	            cell.setBorderBottom(Border.NO_BORDER);
	            table.addCell(cell);
	            cell = new Cell().add(new Paragraph("\n" +"      Reporte de Supervisión "+ "\n" +"      Proyectos de Plataformas Core Red Móvil")).setTextAlignment(TextAlignment.CENTER).setBold().setFontSize(10).setFontColor(Color.DARK_GRAY);
	            cell.setBorderLeft(Border.NO_BORDER);
	            cell.setBorderRight(Border.NO_BORDER);
	            cell.setBorderBottom(Border.NO_BORDER);
	            table.addCell(cell);
	            cell = new Cell().add(new Paragraph(""));
	            cell.setBorderLeft(Border.NO_BORDER);
	            cell.setBorderBottom(Border.NO_BORDER);
	            table.addCell(cell);
	            
	            cell = new Cell().add(new Paragraph("ID ACCESO "+ actividad.getIdAct().toString())).setTextAlignment(TextAlignment.CENTER).setFontSize(7).setFontColor(Color.DARK_GRAY);
	            table.addCell(cell);
	            cell = new Cell().add(new Paragraph("CRQ " + actividad.getCrqActividad().toString())).setTextAlignment(TextAlignment.CENTER).setFontSize(7).setFontColor(Color.DARK_GRAY);
	            table.addCell(cell);
	            cell = new Cell().add(new Paragraph("Fecha Reporte "+ strDate)).setTextAlignment(TextAlignment.CENTER).setFontSize(7).setFontColor(Color.DARK_GRAY);
	            table.addCell(cell);
	            
	            TableRenderer renderer = (TableRenderer) table.createRendererSubTree();
	            renderer.setParent(new Document(new PdfDocument(new PdfWriter(new ByteArrayOutputStream()))).getRenderer());
	            tableHeight = renderer.layout(new LayoutContext(new LayoutArea(10, PageSize.A4))).getOccupiedArea().getBBox().getHeight();
        	}catch(Exception e) {
        		logger.error(e);
        	}
        }
         
        @Override
        public void handleEvent(Event event) {
            PdfDocumentEvent docEvent = (PdfDocumentEvent) event;
            PdfDocument pdfDoc = docEvent.getDocument();
            PdfPage page = docEvent.getPage();
            PdfCanvas canvas = new PdfCanvas(page.newContentStreamBefore(), page.getResources(), pdfDoc);
            Rectangle rect = new Rectangle(pdfDoc.getDefaultPageSize().getX() + doc.getLeftMargin(),
                    750, 190, getTableHeight());
            new Canvas(canvas, pdfDoc, rect)
                    .add(table);
        }
 
        public float getTableHeight() {
            return tableHeight;
        }
    }
	
	protected void manipulatePdf(String ruta, String path) throws IOException, Exception {
		PdfReader reader = new PdfReader(ruta);
		String archivo = "Detalle_de_la_actividad.pdf";
      	PdfWriter writer  = new PdfWriter(path + archivo);
        PdfDocument pdfDoc = new PdfDocument(reader, writer);
        Document doc = new Document(pdfDoc);
        int n = pdfDoc.getNumberOfPages();
        for (int i = 1; i <= n; i++) {
            doc.showTextAligned(new Paragraph(String.format("Página %s de %s", i, n)).setFontSize(9),
                    295, 20, i, TextAlignment.CENTER, VerticalAlignment.TOP, 0);
        }
        doc.close();
        download(archivo, path);
    }

	
	private void download(String archivo, String ruta) throws IOException {
		File file = new File(ruta+archivo);

	    FacesContext facesContext = FacesContext.getCurrentInstance();

	    HttpServletResponse response = 
	            (HttpServletResponse) facesContext.getExternalContext().getResponse();

	    response.reset();
	    response.setHeader("Content-Type", "\"application/pdf\"");
	    response.setHeader("Content-Disposition", "attachment;filename="+archivo);

	    OutputStream responseOutputStream = response.getOutputStream();

	    InputStream fileInputStream = new FileInputStream(file);

	    byte[] bytesBuffer = new byte[2048];
	    int bytesRead;
	    while ((bytesRead = fileInputStream.read(bytesBuffer)) > 0) 
	    {
	        responseOutputStream.write(bytesBuffer, 0, bytesRead);
	    }

	    responseOutputStream.flush();

	    fileInputStream.close();
	    responseOutputStream.close();

	    facesContext.responseComplete();
	}

	
	public ActividadTO getActividad() {
		return actividad;
	}

	public void setActividad(ActividadTO actividad) {
		this.actividad = actividad;
	}

	public List<EscalamientoTO> getEscalamiento() {
		return escalamiento;
	}

	public void setEscalamiento(List<EscalamientoTO> escalamiento) {
		this.escalamiento = escalamiento;
	}

	public UsuarioTO getPersonalCliente() {
		return personalCliente;
	}

	public void setPersonalCliente(UsuarioTO personalCliente) {
		this.personalCliente = personalCliente;
	}

	public UsuarioTO getPersonalSupervisor() {
		return personalSupervisor;
	}

	public void setPersonalSupervisor(UsuarioTO personalSupervisor) {
		this.personalSupervisor = personalSupervisor;
	}

	public List<ProveedorTO> getProveedor() {
		return proveedor;
	}

	public void setProveedor(List<ProveedorTO> proveedor) {
		this.proveedor = proveedor;
	}

	public List<AdjuntoTO> getAdjunto() {
		return adjunto;
	}

	public void setAdjunto(List<AdjuntoTO> adjunto) {
		this.adjunto = adjunto;
	}

}
