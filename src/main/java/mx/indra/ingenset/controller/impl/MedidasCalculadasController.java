package mx.indra.ingenset.controller.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import mx.indra.ingenset.bean.MedidaCalculadaBean;
import mx.indra.ingenset.bean.MedidaCalculadaGroupBean;
import mx.indra.ingenset.controller.IMedidasCalculadasController;
import mx.indra.ingenset.service.IMedidasCalculadasService;

@Controller
@RequestMapping("/api/medidasCalculadas")
public class MedidasCalculadasController implements IMedidasCalculadasController {
	
	static final Logger logger = Logger.getLogger(MedidasCalculadasController.class);
	
	@Autowired
	IMedidasCalculadasService serviceMedidasCalculadas;
			
	@RequestMapping(value = "/group/save", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Integer saveGroupMedidaCalculada(
				@RequestBody MedidaCalculadaGroupBean medidaCalculada) {
		
		String method = "saveGroupMedidaCalculada";
		logger.info("Controller :: MedidasCalculadasController :: " + method);
		
		return serviceMedidasCalculadas.saveGroupMedidaCalculada(medidaCalculada);

	}
	
	@Override
	@RequestMapping(value = "/group/update", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Integer updateGroupMedidaCalculada(
			@RequestBody MedidaCalculadaGroupBean medidaCalculada) {

		String method = "updateGroupMedidaCalculada";
		logger.info("Controller :: MedidasCalculadasController :: " + method);
		
		return serviceMedidasCalculadas.updateGroupMedidaCalculada(medidaCalculada);

	}

	@Override
	@RequestMapping(value = "/group/delete", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Integer deleteGroupMedidaCalculada(
			@RequestBody MedidaCalculadaGroupBean medidaCalculada) {

		String method = "deleteGroupMedidaCalculada";
		logger.info("Controller :: MedidasCalculadasController :: " + method);
		
		return serviceMedidasCalculadas.deleteGroupMedidaCalculada(medidaCalculada);

	}

	
	@RequestMapping(value = "/group/list", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody List<MedidaCalculadaGroupBean> listGroupMedidasCalculadas() {
		
		String method = "listGroupMedidasCalculadas";
		logger.info("Controller :: MedidasCalculadasController :: " + method);
		
		return serviceMedidasCalculadas.listGroupMedidasCalculadas();

	}
	
	
	@RequestMapping(value = "/save", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Integer saveMedidaCalculada(
				@RequestBody MedidaCalculadaBean medidaCalculada) {
		
		String method = "saveMedidaCalculada";
		logger.info("Controller :: MedidasCalculadasController :: " + method);
		
		return serviceMedidasCalculadas.saveMedidaCalculada(medidaCalculada);

	}
	
	@RequestMapping(value = "/update", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Integer updateMedidaCalculada(
				@RequestBody MedidaCalculadaBean medidaCalculada) {
		
		String method = "updateMedidaCalculada";
		logger.info("Controller :: MedidasCalculadasController :: " + method);
		
		return serviceMedidasCalculadas.updateMedidaCalculada(medidaCalculada);

	}
	
	@RequestMapping(value = "/delete", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Integer deleteMedidaCalculada(
			@RequestBody MedidaCalculadaBean medidaCalculada) {
		
		String method = "deleteMedidaCalculada";
		logger.info("Controller :: MedidasCalculadasController :: " + method);
		
		return serviceMedidasCalculadas.deleteMedidaCalculada(medidaCalculada);

	}

	@RequestMapping(value = "/unit/list/{groupMedidaCalculada}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody List<MedidaCalculadaBean> listMedidasCalculadasGroup(
				@PathVariable(value = "groupMedidaCalculada") Integer groupMedidaCalculada) {

		String method = "listMedidasCalculadasGroup";
		logger.info("Controller :: MedidasCalculadasController :: " + method);
		logger.info("Params :: groupMedidaCalculada: " + groupMedidaCalculada);
		
		return serviceMedidasCalculadas.listMedidasCalculadasGroup(groupMedidaCalculada);
		
	}
	
	@RequestMapping(value = "/unit/list/", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody List<MedidaCalculadaBean> listMedidasCalculadas() {

		String method = "listMedidasCalculadas";
		logger.info("Controller :: MedidasCalculadasController :: " + method);
		
		return serviceMedidasCalculadas.listMedidasCalculadas();
		
	}
	
	@RequestMapping(value = "/unit/updateVersion/{formulaID}/{versionFormula}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Integer updateVersionFormula(
				@PathVariable(value = "formulaID") Integer formulaID,
				@PathVariable(value = "versionFormula") Integer versionFormula) {

		String method = "updateVersionFormula";
		logger.info("Controller :: MedidasCalculadasController :: " + method);
		logger.info("Params :: formulaID: " + formulaID + " :: versionFormula: " + versionFormula);
		
		return serviceMedidasCalculadas.updateVersionFormula(formulaID, versionFormula);
		
	}
	
	@RequestMapping(value = "/formula/getVersion/{formulaID}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Integer getVersionFormula(
				@PathVariable(value = "formulaID") Integer formulaID) {

		String method = "getVersionFormula";
		logger.info("Controller :: MedidasCalculadasController :: " + method);
		logger.info("Params :: formulaID: " + formulaID);
		
		return serviceMedidasCalculadas.getVersionFormula(formulaID);
		
	}
	
	@RequestMapping(value = "/list/total", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Integer getTotalGroupMC() {

		String method = "getTotalGroupMC";
		logger.info("Controller :: MedidasCalculadasController :: " + method);
		
		return serviceMedidasCalculadas.getTotalGroupMC();
		
	}

}
