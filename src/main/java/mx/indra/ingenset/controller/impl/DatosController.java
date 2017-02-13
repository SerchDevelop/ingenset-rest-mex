package mx.indra.ingenset.controller.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import mx.indra.ingenset.bean.EntityDatosDiariosBean;
import mx.indra.ingenset.bean.ResultadosBean;
import mx.indra.ingenset.controller.IDatosController;
import mx.indra.ingenset.service.IDatosService;

import java.sql.SQLException;
import java.util.List;

import org.apache.log4j.Logger;

@Controller
@RequestMapping("/api/datos")
public class DatosController implements IDatosController 
{
	static final Logger logger = Logger.getLogger(DatosController.class);

	@Autowired
	IDatosService serviceDatos;
		
	@RequestMapping(value = "/datos/", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody List<ResultadosBean> obtenerDatos() throws SQLException {
		
		// OK ANTERIOR
		List<ResultadosBean> res = serviceDatos.getObtenerDatos();
		
		System.out.println("getObtenerDatos - Size: " + res.size());
						
		return res;
	}	
	
//	@RequestMapping(value = "/data/{identificador}/{fecha}/", method = RequestMethod.GET)
//	public @ResponseBody List<ResultadosBean> getDailyData(
//		   @PathVariable(value = "identificador") String identificador,
//		   @PathVariable(value = "fecha") String fecha ) throws SQLException {
//		
		
		@RequestMapping(value = "/data/{identificador}/", method = RequestMethod.GET)
		public @ResponseBody List<ResultadosBean> getDailyData(
			   @PathVariable(value = "identificador") String identificador) throws SQLException {
		
		String method = "getDailyData";
		logger.info("Controller :: DatosController :: " + method);
		//logger.info("params :: fecha :: " + fecha);
		logger.info("params :: identificador :: " + identificador);
		
		System.out.println("Controller :: DatosController -- A");
		
		//return serviceDatos.buscarDatosTablas(identificador,fecha);
		return serviceDatos.buscarDatosTablas(identificador);
	}	
	
}
