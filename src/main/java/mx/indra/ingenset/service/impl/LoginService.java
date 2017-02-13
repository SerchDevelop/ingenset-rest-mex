package mx.indra.ingenset.service.impl;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mx.indra.ingenset.bean.LoginBean;
import mx.indra.ingenset.dao.ILoginDao;
import mx.indra.ingenset.service.ILoginService;
import mx.indra.ingenset.utils.ThisPasswordEncoder;

@Service("serviceLogin")
public class LoginService implements ILoginService {
	
	@Autowired
	ILoginDao loginDao;
	
	static final Logger logger = Logger.getLogger(LoginService.class);

	@Override
	public Integer changePAssword(LoginBean datosLogin) {

		String method = "getDataTableMarketSelected";
		logger.info("Service :: LoginService :: " + method);
		logger.info("params :: appUser :: + " + datosLogin.getAppUsername() + " :: appPassword :: " + datosLogin.getAppPassword()
							+ " :: userCode :: " + datosLogin.getUserCode());
		
		ThisPasswordEncoder passencoder = new ThisPasswordEncoder();
		String passEncode = passencoder.encodePassword(datosLogin.getAppPassword(), null);
		logger.info("passEncode :: " + passEncode);
		System.out.println("passEncode :: " + passEncode);
		
		datosLogin.setAppPassword(passEncode);


		return loginDao.changePAssword(datosLogin);
	}
	
	

}
