package mx.indra.ingenset.controller.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import mx.indra.ingenset.bean.CentralHidroElectricaBean;
import mx.indra.ingenset.bean.EntityBean;
import mx.indra.ingenset.bean.MercadoBean;
import mx.indra.ingenset.controller.IMercadoController;
import mx.indra.ingenset.service.IMercadoService;

import java.util.List;

import org.apache.log4j.Logger;

@Controller
@RequestMapping("/api/mercado")
public class MercadoController implements IMercadoController {
	
	static final Logger logger = Logger.getLogger(MercadoController.class);
	
	@Autowired
	IMercadoService serviceMercado;	
	
	@RequestMapping(value = "/mostrar/centralElectrica/{fechaInicio}/", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody List<MercadoBean> getDataTableMarketSelected(
				@PathVariable(value = "fechaInicio") String fechaInicio,
				@RequestBody List<CentralHidroElectricaBean> unidadesHidroElectricas) {
		
		String method = "getDataTableMarketSelected";
		logger.info("Controller :: MercadoController :: " + method);
		logger.info("params :: fechaInicio :: " + fechaInicio);
		
		return serviceMercado.getDataTableMarketSelected(fechaInicio, unidadesHidroElectricas);

	}
	
	
	/*
	 * Obtiene datos a partir del ID de la Central Electrica
	 * @param: List<String>
	 */
	@RequestMapping(value = "/mostrar/list/centralElectrica/{fechaInicio}/{authToken}/", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody List<EntityBean> getListDataTableMarketSelected(
				@PathVariable(value = "fechaInicio") String fechaInicio,
				@PathVariable(value = "authToken") String authToken,
				@RequestBody List<String> unidadesHidroElectricas) {
		
		String method = "getListDataTableMarketSelected";
		logger.info("Controller :: MercadoController :: " + method);
		logger.info("params :: fechaInicio :: " + fechaInicio);
		
		return serviceMercado.getListDataTableMarketSelected(fechaInicio, unidadesHidroElectricas, authToken);

	}
	
}
