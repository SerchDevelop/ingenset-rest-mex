package mx.indra.ingenset.service;

import java.util.List;

import mx.indra.ingenset.bean.CentralHidroElectricaBean;
import mx.indra.ingenset.bean.EntityBean;
import mx.indra.ingenset.bean.MercadoBean;

public interface IMercadoService {
		
	public List<MercadoBean> getDataTableMarketSelected(
			String fechaInicio, List<CentralHidroElectricaBean> unidadesHidroElectricas);
	
	public List<EntityBean> getListDataTableMarketSelected(
			String fechaInicio, List<String> unidadesHidroElectricas, String authToken);

}
