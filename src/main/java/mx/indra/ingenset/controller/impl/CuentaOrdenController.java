package mx.indra.ingenset.controller.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import mx.indra.ingenset.bean.EntityDatosDiariosBean;
import mx.indra.ingenset.controller.ICuentaOrdenController;
import mx.indra.ingenset.service.ICuentaOrdenService;

@Controller
@RequestMapping("/api/cuentaOrden")
public class CuentaOrdenController implements ICuentaOrdenController {
	
	static final Logger logger = Logger.getLogger(CuentaOrdenController.class);
	
	@Autowired
	ICuentaOrdenService serviceCuentaOrden;
	
	@RequestMapping(value = "/relation/cuentaOrden/{entityID}", method = RequestMethod.GET)
	public @ResponseBody List<EntityDatosDiariosBean> getRelationCuentaOrden(
		   @PathVariable(value = "entityID") Integer entityID) {
	
	String method = "getRelationCuentaOrden";
	logger.info("Controller :: CuentaOrdenController :: " + method);
	logger.info("params :: entityID :: " + entityID);
	
	return serviceCuentaOrden.getRelationCuentaOrden(entityID);
}

}
