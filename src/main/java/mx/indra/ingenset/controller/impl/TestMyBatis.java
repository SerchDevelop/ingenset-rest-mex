package mx.indra.ingenset.controller.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import mx.indra.ingenset.bean.ColocacionMTRBean;
import mx.indra.ingenset.bean.Fenix;
import mx.indra.ingenset.controller.ITestMyBatis;
import mx.indra.ingenset.service.IMyBatisService;

@Controller
@RequestMapping("/api/mybatis")
public class TestMyBatis implements ITestMyBatis{
	
	static final Logger logger = Logger.getLogger(TestMyBatis.class);
	
	@Autowired
	IMyBatisService serviceMyBatis;
	
	@RequestMapping(value = "/test", method = RequestMethod.GET)
	public @ResponseBody Integer getData() {
		
		String method = "getData";
		logger.info("Controller :: TestMyBatis :: " + method);

		return serviceMyBatis.getData();
	}
	
	@RequestMapping(value = "/test/all", method = RequestMethod.GET)
	public @ResponseBody List<Fenix> getDataAll() {
		
		String method = "getDataAll";
		logger.info("Controller :: TestMyBatis :: " + method);

		return serviceMyBatis.getDataAll();
	}
	
	@RequestMapping(value = "/data/mtr", method = RequestMethod.GET)
	public @ResponseBody List<ColocacionMTRBean> getDataMTR() {
		
		String method = "getDataMTR";
		logger.info("Controller :: TestMyBatis :: " + method);

		return serviceMyBatis.getDataMTR("", "01AMD-U3", "01/08/2016");
	}


}
