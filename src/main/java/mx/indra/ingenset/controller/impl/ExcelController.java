package mx.indra.ingenset.controller.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URISyntaxException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import mx.indra.ingenset.bean.MercadoBean;
import mx.indra.ingenset.bean.UnidadGeneradoraBean;
import mx.indra.ingenset.controller.IExcelController;
import mx.indra.ingenset.service.IExcelService;

@Controller
@RequestMapping("/api/excel")
public class ExcelController implements IExcelController{
	
	static final Logger logger = Logger.getLogger(ExcelController.class);
	
	@Autowired
	IExcelService serviceExcel;

	@RequestMapping(value = "/export/", method = RequestMethod.POST)
	public void exportExcel(
				@RequestBody List<MercadoBean> mercadoBean,
				HttpServletRequest request, HttpServletResponse response) throws IOException, URISyntaxException {
		
		String method = "exportExcel";
		logger.info("Controller :: ExcelController :: " + method);
		logger.info("params :: mercadoBean :: " + mercadoBean);
	
		serviceExcel.exportExcel(mercadoBean, request, response);
	}
	
	@RequestMapping(value = "/export/unidadGeneradoraBean", method = RequestMethod.POST)
	public void exportUnidadGeneradoraBeanExcel(
				@RequestBody UnidadGeneradoraBean unidadGeneradoraBean,
				HttpServletRequest request, HttpServletResponse response) throws IOException, URISyntaxException {
		
		String method = "exportUnidadGeneradoraBeanExcel";
		logger.info("Controller :: ExcelController :: " + method);
		logger.info("params :: mercadoBean :: " + unidadGeneradoraBean);
		
		String fechaFormat = unidadGeneradoraBean.getFecha().replaceAll("-", "/");
		DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
		Date fecha = null;
		try {
			fecha = df.parse(fechaFormat);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		String fechaAux = df.format(fecha).toString();
		unidadGeneradoraBean.setFecha(fechaAux);
	
		serviceExcel.exportUnidadGeneradoraBeanExcel(unidadGeneradoraBean, request, response);
	}
	
	@RequestMapping(value = "/export/totalUnidadGeneradoraBean", method = RequestMethod.POST)
	public void totalUnidadGeneradoraBean(
				@RequestBody List<UnidadGeneradoraBean> unidadGeneradoraBean,
				HttpServletRequest request, HttpServletResponse response) throws IOException, URISyntaxException {
		
		String method = "totalUnidadGeneradoraBean";
		logger.info("Controller :: ExcelController :: " + method);
		logger.info("params :: mercadoBean :: " + unidadGeneradoraBean);
			
		serviceExcel.totalUnidadGeneradoraBean(unidadGeneradoraBean, request, response);
	}
    
    @RequestMapping(value="/donwloadItem/" , method = RequestMethod.GET)
    public void doDownload(HttpServletRequest request, HttpServletResponse response) throws IOException, InterruptedException {

		serviceExcel.doDownload(request, response);
    	
    }
    
    /**
     * Method for handling file download request from client
     */
    @RequestMapping(value="/donwloadItem" , method = RequestMethod.GET)
    public void doDownloadItem(HttpServletRequest request, HttpServletResponse response) throws IOException {

        // get absolute path of the application
        String context = request.getContextPath();

//        System.out.println("getContextPath :: " + request.getContextPath());
//        System.out.println("getRequestURL :: " + request.getRequestURL());
//        System.out.println("getRequestURI :: " + request.getRequestURI());
//        System.out.println("getRemoteHost :: " + request.getRemoteHost());
//        System.out.println("getServerName :: " + request.getServerName());
//        System.out.println("getServerPort :: " + request.getServerPort());
//        System.out.println("getPathInfo :: " + request.getPathInfo());
//        System.out.println("getRemoteAddr :: " + request.getRemoteAddr());
        String appPath = request.getRealPath("");
//        System.out.println("context :: " + context + " :: appPath = " + appPath);

        // construct the complete absolute path of the file
        String fullPath =  appPath + "/template/FormatoExcel.xlsx";      
        File downloadFile = new File(fullPath);
        FileInputStream inputStream = new FileInputStream(downloadFile);
         
        // get MIME type of the file
        String mimeType = "application/octet-stream";
        System.out.println("MIME type: " + mimeType);
 
        // set content attributes for the response
        response.setContentType(mimeType);
        response.setContentLength((int) downloadFile.length());
 
        // set headers for the response
        String headerKey = "Content-Disposition";
        String headerValue = String.format("attachment; filename=\"%s\"", downloadFile.getName());
        response.setHeader(headerKey, headerValue);
 
        // get output stream of the response
        OutputStream outStream = response.getOutputStream();
 
        byte[] buffer = new byte[4096];
        int bytesRead = -1;
 
        // write bytes read from the input stream into the output stream
        while ((bytesRead = inputStream.read(buffer)) != -1) {
            outStream.write(buffer, 0, bytesRead);
        }
 
        inputStream.close();
        outStream.close();

    }


}
