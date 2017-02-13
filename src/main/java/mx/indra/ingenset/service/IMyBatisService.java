package mx.indra.ingenset.service;

import java.util.List;

import mx.indra.ingenset.bean.ColocacionMTRBean;
import mx.indra.ingenset.bean.Fenix;

public interface IMyBatisService {
	
	public Integer getData();
	
	public List<Fenix> getAllResultados();
	
	public List<Fenix> getDataAll();

	public List<ColocacionMTRBean> getDataMTR(String tipoMercado, String unidadHidroElectrica, String fechaInicio);

}
