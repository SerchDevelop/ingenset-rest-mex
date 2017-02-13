package mx.indra.ingenset.controller.impl;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import mx.indra.ingenset.bean.LoginBean;
import mx.indra.ingenset.controller.ILoginController;
import mx.indra.ingenset.service.ILoginService;

@Controller
@RequestMapping("/api/login")
public class LoginController implements ILoginController {
	
	static final Logger logger = Logger.getLogger(LoginController.class);
	
	@Autowired
	ILoginService serviceLogin;
			
	@RequestMapping(value = "/change/", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Integer changePAssword(
			@RequestBody LoginBean datosLogin) {
		
		String method = "changePAssword";
		logger.info("Controller :: LoginController :: " + method);
		logger.info("params :: appUser :: + " + datosLogin.getAppUsername() + " :: appPassword :: " + datosLogin.getAppPassword()
							+ " :: userCode :: " + datosLogin.getUserCode());
		
		return serviceLogin.changePAssword(datosLogin);

	}

}
