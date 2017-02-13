package mx.indra.ingenset.service.impl;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URISyntaxException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;

import mx.indra.excel.ExportExcel;
import mx.indra.ingenset.bean.MercadoBean;
import mx.indra.ingenset.bean.MercadoDiaAdelantoBean;
import mx.indra.ingenset.bean.MercadoTiempoRealBean;
import mx.indra.ingenset.bean.UnidadGeneradoraBean;
import mx.indra.ingenset.service.IExcelService;

@Service("serviceExcel")
public class ExcelService implements IExcelService {
	
	static final Logger logger = Logger.getLogger(ExcelService.class);
	
	@Autowired
	ServletContext servletContext;
	
	@Override
	public void exportExcel(List<MercadoBean> mercadoBean, HttpServletRequest request, HttpServletResponse response) throws IOException, URISyntaxException {

		String method = "exportExcel";
		logger.info("Controller :: ExcelService :: " + method);
		logger.info("params :: mercadoBean :: " + mercadoBean);

//    	FileInputStream file = new FileInputStream(new File("FormatoExport.xlsx"));
//		ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
//		URL url = classLoader.getResource("FormatoExport.xlsx");
//		FileInputStream file = new FileInputStream(new File(url.toURI().toString()));
    	// Crear el objeto que tendra el libro de Excel
    	XSSFWorkbook workbook = new XSSFWorkbook(this.getClass().getClassLoader().getResourceAsStream("FormatoExport.xls"));
    	int item = 1;
    	
    	ExportExcel exportExcel = new ExportExcel();
		// MercadoBean
		for(MercadoBean mbAux : mercadoBean){
//System.out.println("MercadoBean :: " + mbAux);
			// Mercado Tiempo Real
			for(MercadoTiempoRealBean mtr : mbAux.getMercadoTiempoReal()){
System.out.println("MercadoTiempoRealBean :: " + mtr);
				// Unidad Generadora
				for(UnidadGeneradoraBean unidadGeneradora : mtr.getUnidadGeneradora()){
System.out.println("UnidadGeneradoraBean :: " + unidadGeneradora.getUnidadGeneradora());
					workbook.cloneSheet(0);
					exportExcel.generaExcel(workbook, unidadGeneradora.getCentralElectrica(), unidadGeneradora.getUnidadGeneradora(), unidadGeneradora.getHora(), unidadGeneradora.getFecha());
					item++;
				}
			}

				// Mercado Dia Adelanto
				for(MercadoDiaAdelantoBean mda : mbAux.getMercadoDiaAdelanto()){
System.out.println("MercadoDiaAdelantoBean :: " + mda);
					// Unidad Generadora
					for(UnidadGeneradoraBean unidadGeneradora : mda.getUnidadGeneradora()){
System.out.println("UnidadGeneradoraBean :: " + unidadGeneradora.getUnidadGeneradora());
						workbook.cloneSheet(0);
						exportExcel.generaExcel(workbook, unidadGeneradora.getCentralElectrica(), unidadGeneradora.getUnidadGeneradora(), unidadGeneradora.getHora(), unidadGeneradora.getFecha());
						item++;
					}
				}
		}
		
		// Se elimina la plantilla
//		workbook.removeSheetAt(0);
		
        // Se salva el libro.
        try {
        	String nameFile = "DataMarketExport.xlsx";
//       	System.out.println("nameFile :: " + nameFile);
            FileOutputStream elFichero = new FileOutputStream(nameFile);
            workbook.write(elFichero);
            elFichero.close();
            
            File fileExcel = new File(nameFile);
//            System.out.println("fileExcel :: " + fileExcel.isFile());
//            System.out.println("exists :: " + fileExcel.exists());
//            System.out.println("length :: " + fileExcel.length());
            
        	// Donwload
            // get MIME type of the file
            String mimeType = "application/octet-stream";
//            System.out.println("MIME type: " + mimeType);
     
            // set content attributes for the response
            response.setContentType(mimeType);
            response.setContentLength((int) fileExcel.length());
     
            // set headers for the response
            String headerKey = "Content-Disposition";
            String headerValue = String.format("attachment; filename=\"%s\"", nameFile);
            response.setHeader(headerKey, headerValue);
            
            BufferedInputStream in = new BufferedInputStream(new FileInputStream(fileExcel));
            FileCopyUtils.copy(in, response.getOutputStream());
                        
        } catch (Exception e) {
            e.printStackTrace();
        }  
        
    	// cerramos el libro excel
    	workbook.close();

	}
	
	@Override
	public void exportUnidadGeneradoraBeanExcel(UnidadGeneradoraBean unidadGeneradoraBean, HttpServletRequest request,
			HttpServletResponse response) throws IOException, URISyntaxException {

		String method = "exportUnidadGeneradoraBeanExcel";
		logger.info("Controller :: ExcelService :: " + method);
		logger.info("params :: unidadGeneradoraBean :: " + unidadGeneradoraBean);

    	XSSFWorkbook workbook = new XSSFWorkbook(this.getClass().getClassLoader().getResourceAsStream("FormatoExport.xls"));
		/*
		* Obtenemos la primera pestaña a la que se quiera procesar indicando el indice.
		*/
		XSSFSheet sheet = workbook.getSheetAt(0);
		
		// Se coloca el nombre de la Unidad HidroElectrica
		XSSFCell celdaWrite= sheet.getRow(15).getCell(3);
		HSSFRichTextString texto = new HSSFRichTextString(unidadGeneradoraBean.getUnidadGeneradora());
		celdaWrite.setCellValue(texto.getString());
//		workbook.setSheetName(0, unidadGeneradoraBean.getUnidadGeneradora());
		
		// Se colocan fechas
		celdaWrite= sheet.getRow(3).getCell(3);
		// Se crea el contenido de la celda y se mete en ella.
		texto = new HSSFRichTextString(unidadGeneradoraBean.getFecha());
		celdaWrite.setCellValue(texto.getString());
		
		celdaWrite= sheet.getRow(3).getCell(7);
		// Se crea el contenido de la celda y se mete en ella.
		texto = new HSSFRichTextString(unidadGeneradoraBean.getFecha());
		celdaWrite.setCellValue(texto.getString());
		
		// Se crea la central electrica
		celdaWrite= sheet.getRow(3).getCell(11);
		// Se crea el contenido de la celda y se mete en ella.
		String centralSplit[] = unidadGeneradoraBean.getUnidadGeneradora().split("-");
		texto = new HSSFRichTextString(centralSplit[0]);
		celdaWrite.setCellValue(texto.getString());
		
		// Se crea la unidad hidroelectrica
		celdaWrite= sheet.getRow(3).getCell(15);

		// Se crea el contenido de la celda y se mete en ella.
		texto = new HSSFRichTextString(unidadGeneradoraBean.getUnidadGeneradora());
		celdaWrite.setCellValue(texto.getString());
		
		// Se crean las celdas para MW
		int count = 13;
		for(Integer time = 1; time <= 25; time++){
			celdaWrite= sheet.getRow(count).getCell(3);
			// Se crea el contenido de la celda y se mete en ella.
			if(unidadGeneradoraBean.getHora().get("hora" + time) != null){
//				celdaWrite.setCellValue(unidadGeneradoraBean.getHora().get("hora" + time.toString()));
				Double valorCell = unidadGeneradoraBean.getHora().get("hora" + time.toString());
				celdaWrite.setCellValue(fijarNumero(valorCell,3));
			} else
				celdaWrite.setCellValue("");

			count++;		
		}
    						
        // Se salva el libro.
        try {
        	String nameFile = unidadGeneradoraBean.getUnidadGeneradora() + ".xlsx";
            FileOutputStream elFichero = new FileOutputStream(nameFile);
            workbook.write(elFichero);
            elFichero.close();
            File fileExcel = new File(nameFile);            
        	// Donwload
            // get MIME type of the file
            String mimeType = "application/octet-stream";
//            System.out.println("MIME type: " + mimeType);
     
            // set content attributes for the response
            response.setContentType(mimeType);
            response.setContentLength((int) fileExcel.length());
     
            // set headers for the response
            String headerKey = "Content-Disposition";
            String headerValue = String.format("attachment; filename=\"%s\"", nameFile);
            response.setHeader(headerKey, headerValue);
            
            BufferedInputStream in = new BufferedInputStream(new FileInputStream(fileExcel));
            FileCopyUtils.copy(in, response.getOutputStream());
                        
        } catch (Exception e) {
            e.printStackTrace();
        }  
        
    	// cerramos el libro excel
    	workbook.close();

		
	}
	
	@Override
	public void totalUnidadGeneradoraBean(List<UnidadGeneradoraBean> unidadGeneradoraBean, HttpServletRequest request,
			HttpServletResponse response) throws IOException, URISyntaxException {

		String method = "exportUnidadGeneradoraBeanExcel";
		logger.info("Controller :: ExcelService :: " + method);
		logger.info("params :: unidadGeneradoraBean :: " + unidadGeneradoraBean);
		
		String fechaFormat = unidadGeneradoraBean.get(0).getFecha().replaceAll("-", "/");
		DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
		Date fecha = null;
		try {
			fecha = df.parse(fechaFormat);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		String fechaAux = df.format(fecha).toString();

    	XSSFWorkbook workbook = new XSSFWorkbook(this.getClass().getClassLoader().getResourceAsStream("FormatoExport.xls"));
		/*
		* Obtenemos la primera pestaña a la que se quiera procesar indicando el indice.
		*/
		XSSFSheet sheet = workbook.getSheetAt(0);
		
		// Se coloca el nombre de la Unidad HidroElectrica
		XSSFCell celdaWrite= sheet.getRow(15).getCell(3);
		HSSFRichTextString texto = new HSSFRichTextString(unidadGeneradoraBean.get(0).getUnidadGeneradora());
		celdaWrite.setCellValue(texto.getString());
//		workbook.setSheetName(0, unidadGeneradoraBean.getUnidadGeneradora());
		
		// Se colocan fechas
		celdaWrite= sheet.getRow(3).getCell(3);
		// Se crea el contenido de la celda y se mete en ella.
		texto = new HSSFRichTextString(fechaAux);
		celdaWrite.setCellValue(texto.getString());
		
		celdaWrite= sheet.getRow(3).getCell(7);
		// Se crea el contenido de la celda y se mete en ella.
		texto = new HSSFRichTextString(fechaAux);
		celdaWrite.setCellValue(texto.getString());
		
		// Se crea la central electrica
		celdaWrite= sheet.getRow(3).getCell(11);
		// Se crea el contenido de la celda y se mete en ella.
		String centralSplit[] = unidadGeneradoraBean.get(0).getUnidadGeneradora().split("-");
		texto = new HSSFRichTextString(centralSplit[0]);
		celdaWrite.setCellValue(texto.getString());
		
		// Se crea la unidad hidroelectrica
		celdaWrite= sheet.getRow(3).getCell(15);

		// Se crea el contenido de la celda y se mete en ella.
		texto = new HSSFRichTextString(unidadGeneradoraBean.get(0).getUnidadGeneradora());
		celdaWrite.setCellValue(texto.getString());
		
    	Double totalHours[] = new Double[26];
		for(int count=1; count<=25; count++){
			totalHours[count] = 0.0;
		}
		
		for (UnidadGeneradoraBean unidad : unidadGeneradoraBean) {
			for(int count=1; count<=25; count++){
				if(unidad.getHora().get("hora" + count) != null){
					totalHours[count] += unidad.getHora().get("hora" + count).doubleValue();
				}
			}
		}
			
		// Se crean las celdas para MW
		int count = 13;
		for(Integer time = 1; time <= 25; time++){
			celdaWrite= sheet.getRow(count).getCell(3);
			// Se crea el contenido de la celda y se mete en ella.
			if(totalHours[time] != null)
				celdaWrite.setCellValue(fijarNumero(totalHours[time].doubleValue(), 3));
			else
				celdaWrite.setCellValue("");

			count++;		
		}
    						
        // Se salva el libro.
        try {
        	String nameFile = unidadGeneradoraBean.get(0).getUnidadGeneradora() + ".xlsx";
            FileOutputStream elFichero = new FileOutputStream(nameFile);
            workbook.write(elFichero);
            elFichero.close();
            File fileExcel = new File(nameFile);            
        	// Donwload
            // get MIME type of the file
            String mimeType = "application/octet-stream";
//            System.out.println("MIME type: " + mimeType);
     
            // set content attributes for the response
            response.setContentType(mimeType);
            response.setContentLength((int) fileExcel.length());
     
            // set headers for the response
            String headerKey = "Content-Disposition";
            String headerValue = String.format("attachment; filename=\"%s\"", nameFile);
            response.setHeader(headerKey, headerValue);
            
            BufferedInputStream in = new BufferedInputStream(new FileInputStream(fileExcel));
            FileCopyUtils.copy(in, response.getOutputStream());
                        
        } catch (Exception e) {
            e.printStackTrace();
        }  
        
    	// cerramos el libro excel
    	workbook.close();
		
	}

	
	public void doDownload(HttpServletRequest request, HttpServletResponse response) throws IOException {

		// get absolute path of the application
	    String context = request.getContextPath();

//	    System.out.println("getContextPath :: " + request.getContextPath());
//	    System.out.println("getRequestURL :: " + request.getRequestURL());
//	    System.out.println("getRequestURI :: " + request.getRequestURI());
//	    System.out.println("getRemoteHost :: " + request.getRemoteHost());
//	    System.out.println("getServerName :: " + request.getServerName());
//	    System.out.println("getServerPort :: " + request.getServerPort());
//	    System.out.println("getPathInfo :: " + request.getPathInfo());
//	    System.out.println("getRemoteAddr :: " + request.getRemoteAddr());
	    String appPath = request.getRealPath("");
	    System.out.println("context :: " + context + " :: appPath = " + appPath);

	    // construct the complete absolute path of the file
	    String fullPath =  appPath + "/template/FormatoExcel.xlsx";      
	    File downloadFile = new File(fullPath);
        // get MIME type of the file
        String mimeType = "application/octet-stream";
//        System.out.println("MIME type: " + mimeType);
 
        // set content attributes for the response
        response.setContentType(mimeType);
        response.setContentLength((int) downloadFile.length());
 
        // set headers for the response
        String headerKey = "Content-Disposition";
        String headerValue = String.format("attachment; filename=\"%s\"", downloadFile.getName());
        response.setHeader(headerKey, headerValue);
        response.getOutputStream().flush();

	}
	
	
    public static double fijarNumero(double numero, int digitos) {
        double resultado;
        resultado = numero * Math.pow(10, digitos);
        resultado = Math.round(resultado);
        resultado = resultado/Math.pow(10, digitos);
        return resultado;
    }

}