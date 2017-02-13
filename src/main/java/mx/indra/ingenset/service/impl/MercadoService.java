package mx.indra.ingenset.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import mx.indra.ingenset.bean.CentralHidroElectricaBean;
import mx.indra.ingenset.bean.ColocacionMDABean;
import mx.indra.ingenset.bean.ColocacionMTRBean;
import mx.indra.ingenset.bean.EntityBean;
import mx.indra.ingenset.bean.MercadoBean;
import mx.indra.ingenset.bean.MercadoDiaAdelantoBean;
import mx.indra.ingenset.bean.MercadoTiempoRealBean;
import mx.indra.ingenset.bean.UnidadGeneradoraBean;
import mx.indra.ingenset.dao.IMercadoDao;
import mx.indra.ingenset.service.IMercadoService;

@Service("serviceMercado")
public class MercadoService implements IMercadoService {
	
	static final Logger logger = Logger.getLogger(MercadoService.class);
	
	@Autowired
	IMercadoDao mercadoDao;

	@Override
	public List<MercadoBean> getDataTableMarketSelected(String fechaInicio,
			List<CentralHidroElectricaBean> unidadesHidroElectricas) {
		
		String method = "getDataTableMarketSelected";
		logger.info("Controller :: MercadoService :: " + method);
		logger.info("params :: fechaInicio :: " + fechaInicio);


		List<MercadoBean> mercado = new ArrayList<MercadoBean>();
		List<MercadoTiempoRealBean> mercadoTiempoReal = new ArrayList<MercadoTiempoRealBean>();
		List<MercadoDiaAdelantoBean> mercadoDiaAdelanto = new ArrayList<MercadoDiaAdelantoBean>();
		// GetFecha
		String fechaData = fechaInicio;
		for (CentralHidroElectricaBean uHidroElectrica : unidadesHidroElectricas) {
			logger.info("uHidroElectrica.getCentralElectrica() :: " + uHidroElectrica.getCentralElectrica());
			MercadoTiempoRealBean mercadoReal = new MercadoTiempoRealBean();
			MercadoDiaAdelantoBean mercadoAdelanto = new MercadoDiaAdelantoBean();
			List<UnidadGeneradoraBean> unidadGeneradoraReal = new ArrayList<UnidadGeneradoraBean>();
			List<UnidadGeneradoraBean> unidadGeneradoraAdelanto = new ArrayList<UnidadGeneradoraBean>();
			mercadoReal.setFecha(fechaInicio);
			mercadoAdelanto.setFecha(fechaInicio);
			mercadoReal.setCentralElectrica(uHidroElectrica.getCentralElectrica());
			mercadoAdelanto.setCentralElectrica(uHidroElectrica.getCentralElectrica());
			for (String unidadHidroElectrica : uHidroElectrica.getUnidadesHidroElectricas()) {
				logger.info("unidadHidroElectrica :: " + unidadHidroElectrica);
//				Integer fileVersionMTR = mercadoDao.getFileVersionMTR(unidadHidroElectrica);
				List<ColocacionMTRBean> listaMTR = mercadoDao.getDataMTR("", unidadHidroElectrica, fechaData, null);
				unidadGeneradoraReal.add(addDataTiempoReal(listaMTR, uHidroElectrica.getCentralElectrica(), unidadHidroElectrica, fechaData));
				
//				Integer fileVersionMDA = mercadoDao.getFileVersionMDA(unidadHidroElectrica);
				List<ColocacionMDABean> listaMDA = mercadoDao.getDataMDA("", unidadHidroElectrica, fechaData, null);
				unidadGeneradoraAdelanto.add(addDataDiaAdelanto(listaMDA, uHidroElectrica.getCentralElectrica(), unidadHidroElectrica, fechaData));
			}
			
			mercadoReal.setUnidadGeneradora(unidadGeneradoraReal);
			mercadoAdelanto.setUnidadGeneradora(unidadGeneradoraAdelanto);
			mercadoTiempoReal.add(mercadoReal);
			mercadoDiaAdelanto.add(mercadoAdelanto);
		}
		
		MercadoBean mb = new MercadoBean();
		mb.setMercadoTiempoReal(mercadoTiempoReal);
		mb.setMercadoDiaAdelanto(mercadoDiaAdelanto);
		mercado.add(mb);
		
		return mercado;
		
	}
	
	@Override
	public List<EntityBean> getListDataTableMarketSelected(String fechaInicio, List<String> unidadesHidroElectricas, String authToken) {

		String method = "getListDataTableMarketSelected";
		logger.info("Controller :: MercadoService :: " + method);
		logger.info("params :: fechaInicio :: " + fechaInicio);
		
		RestTemplate restTemplate = new RestTemplate();
		String URL_GET_UNIDADES_HIDROELECTRICAS = "http://129.152.42.202/ingenset-web-app/rest-api/entity/{entity}";
		Map<String, String> map = new HashMap<String, String>();

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.set("Authorization", authToken);
		headers.set("X-Authorization", authToken);
		headers.set("authToken", authToken);
		
		List<EntityBean> entityBean = new ArrayList<EntityBean>();
		
		
		for (String entityUnidad : unidadesHidroElectricas) {
			map.put("entity", entityUnidad);
			HttpEntity<Map<String, String>> entity = new HttpEntity<Map<String, String>>(map, headers);
			try{
				HttpEntity<EntityBean> respuesta = restTemplate.exchange(URL_GET_UNIDADES_HIDROELECTRICAS, HttpMethod.GET, entity, EntityBean.class, map);
				entityBean.add(respuesta.getBody());
			}
	        catch( RestClientException e ){
	        	e.printStackTrace();			
			} 
		}

		return entityBean;
	}
	
	private static UnidadGeneradoraBean addDataTiempoReal(List<ColocacionMTRBean> listaMTR, String centralElectrica, String unidadGeneradora, String fechaData) {
		
		logger.info("Method :: addDataTiempoReal");
		logger.info("Params centralElectrica :: " + centralElectrica + " :: unidadGeneradora :: " + unidadGeneradora + " :: fechaData :: " + fechaData);
		
		UnidadGeneradoraBean mtr = new UnidadGeneradoraBean();
		mtr.setCentralElectrica(centralElectrica);
		mtr.setFecha(fechaData);
//		mtr.setListaColocacionMTR(listaMTR);
		Map<String, Double> hora = new HashMap<String, Double>();
		for (ColocacionMTRBean colocacionMTRBean : listaMTR) {
			hora.put("hora" + colocacionMTRBean.getNUMBER_SEQ_SEC(), colocacionMTRBean.getNUMBER_VALUE());
			logger.info("getNUMBER_SEQ_SEC :: " + colocacionMTRBean.getNUMBER_SEQ_SEC() + " :: getNUMBER_VALUE :: " + colocacionMTRBean.getNUMBER_VALUE());
		}

		mtr.setHora(hora);

		mtr.setUnidadGeneradora(unidadGeneradora);
		
		return mtr;
		
	}
	
	private static UnidadGeneradoraBean addDataDiaAdelanto(List<ColocacionMDABean> listaMDA, String centralElectrica, String unidadGeneradora, String fechaData) {
		
		logger.info("Method :: addDataDiaAdelanto");
		logger.info("Params centralElectrica :: " + centralElectrica + " :: unidadGeneradora :: " + unidadGeneradora + " :: fechaData :: " + fechaData);
		
		UnidadGeneradoraBean mda = new UnidadGeneradoraBean();
		mda.setCentralElectrica(centralElectrica);
		mda.setFecha(fechaData);
//		mda.setListaColocacionMDA(listaMDA);
		Map<String, Double> hora = new HashMap<String, Double>();
		for (ColocacionMDABean colocacionMDABean : listaMDA) {
			hora.put("hora" + colocacionMDABean.getNUMBER_SEQ_SEC(), colocacionMDABean.getNUMBER_VALUE());
			logger.info("getNUMBER_SEQ_SEC :: " + colocacionMDABean.getNUMBER_SEQ_SEC() + " :: getNUMBER_VALUE :: " + colocacionMDABean.getNUMBER_VALUE());
		}

		mda.setHora(hora);

		mda.setUnidadGeneradora(unidadGeneradora);
		
		return mda;
		
	}

}
