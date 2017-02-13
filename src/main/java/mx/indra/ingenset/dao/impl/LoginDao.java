package mx.indra.ingenset.dao.impl;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

import mx.indra.ingenset.bean.LoginBean;
import mx.indra.ingenset.dao.ILoginDao;
import mx.indra.ingenset.utils.HelperJDBC;

@Repository("loginDao")
public class LoginDao extends HelperJDBC implements ILoginDao {
	
	static final Logger logger = Logger.getLogger(LoginDao.class);

	@Override
	public Integer changePAssword(LoginBean datosLogin) {

		String method = "changePAssword";
		logger.info("Dao :: LoginDao :: " + method);
		
		String sql = "UPDATE INGEN_ADMINIS.APP_USERS SET APP_PASSWORD = '" + datosLogin.getAppPassword() + "' WHERE APP_USERNAME = '" + datosLogin.getAppUsername() + "'";
		
		Integer updatePassword = getJdbcTemplate().update(sql);
		
		logger.info("updatePassword :: " + updatePassword);

		return updatePassword;
	}

}
