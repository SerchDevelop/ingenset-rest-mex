package mx.indra.ingenset.controller;

import java.util.List;

import mx.indra.ingenset.bean.CentralHidroElectricaBean;
import mx.indra.ingenset.bean.EntityBean;
import mx.indra.ingenset.bean.MercadoBean;

public interface IMercadoController {
	
//	public List<MercadoBean> getDataMTRAndMDA(
//			String fechaInicio, List<CentralHidroElectricaBean> unidadesHidroElectricas);
	
	public List<MercadoBean> getDataTableMarketSelected(
			String fechaInicio, List<CentralHidroElectricaBean> unidadesHidroElectricas);

	public List<EntityBean> getListDataTableMarketSelected(
			String fechaInicio, String authToken, List<String> unidadesHidroElectricas);
	
}
