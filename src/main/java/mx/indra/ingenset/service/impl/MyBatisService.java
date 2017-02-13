package mx.indra.ingenset.service.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mx.indra.ingenset.bean.ColocacionMTRBean;
import mx.indra.ingenset.bean.Fenix;
import mx.indra.ingenset.dao.IMyBatisDao;
import mx.indra.ingenset.dao.impl.MyBatisDao;
import mx.indra.ingenset.service.IMyBatisService;

@Service("serviceMyBatis")
public class MyBatisService extends MyBatisDao implements IMyBatisService{
	
	static final Logger logger = Logger.getLogger(MyBatisService.class);
	
	@Autowired
	private IMyBatisDao daoMyBatis;

	@Override
	public Integer getData() {

		String method = "getData";
		logger.info("Service :: MyBatisService :: " + method);
		
		return daoMyBatis.getData();
		
	}

	@Override
	public List<Fenix> getDataAll() {

		return daoMyBatis.getAllResultados();
		
	}
	
	
	@Override
	public List<ColocacionMTRBean> getDataMTR(String tipoMercado, String unidadHidroElectrica, String fechaInicio) {

		return daoMyBatis.getDataMTR("", "01AMD-U3", "01/08/2016");
		
	}


}
