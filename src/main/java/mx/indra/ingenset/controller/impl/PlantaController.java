package mx.indra.ingenset.controller.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import mx.indra.ingenset.bean.PlantaBean;

@Controller
@RequestMapping("/api/planta")
public class PlantaController {
	
	static final Logger logger = Logger.getLogger(PlantaController.class);
	
	/*
	 * Metodo para obtener el listado de plantas
	 * @param:
	 * @return: List<PlantaBean>
	 * 
	 */
	
	@RequestMapping(value = "/list", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody List<PlantaBean> listadoPlantas() {

		List<PlantaBean> plantas = new ArrayList<PlantaBean>();
		PlantaBean planta1 = new PlantaBean();
		planta1.setUnidadGeneradora("Necaxa");
		
		PlantaBean planta2 = new PlantaBean();
		planta2.setUnidadGeneradora("Tepexic");
		
		PlantaBean planta3 = new PlantaBean();
		planta3.setUnidadGeneradora("Patla");
		
		PlantaBean planta4 = new PlantaBean();
		planta4.setUnidadGeneradora("Lerma");
		
		PlantaBean planta5 = new PlantaBean();
		planta5.setUnidadGeneradora("Tongona");
		
		plantas.add(planta1);
		plantas.add(planta2);
		plantas.add(planta3);
		plantas.add(planta4);
		plantas.add(planta5);
		
		return plantas;

	}

}
