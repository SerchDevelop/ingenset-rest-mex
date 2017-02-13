package mx.indra.ingenset.controller.impl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import mx.indra.ingenset.controller.ILoadFileController;
import mx.indra.ingenset.service.ILoadFileService;

@Controller
@RequestMapping("/api/loadFile")
public class LoadFileController implements ILoadFileController {
	
	static final Logger logger = Logger.getLogger(LoadFileController.class);
	
	@Autowired
	ILoadFileService serviceLoadFile;
			
	@RequestMapping(value = "/load", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody void loadFile(HttpServletRequest request, HttpServletResponse response) {
		
		String method = "loadFile";
		logger.info("Controller :: LoadFileController :: " + method);
		
		serviceLoadFile.loadFile(request, response);

	}

}
