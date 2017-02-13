package mx.indra.ingenset.service.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mx.indra.ingenset.bean.EntityDatosDiariosBean;
import mx.indra.ingenset.dao.ICuentaOrdenDao;
import mx.indra.ingenset.service.ICuentaOrdenService;

@Service("serviceCuentaOrden")
public class CuentaOrdenService implements ICuentaOrdenService{
	
	static final Logger logger = Logger.getLogger(MedidasCalculadasService.class);
	
	@Autowired
	ICuentaOrdenDao cuentaOrdenDao;

	@Override
	public List<EntityDatosDiariosBean> getRelationCuentaOrden(Integer entityID) {

		String method = "getRelationCuentaOrden";
		logger.info("Service CuentaOrdenService :: " + method);		

		return cuentaOrdenDao.getRelationCuentaOrden(entityID);
		
	}

}
