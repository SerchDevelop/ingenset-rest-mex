package mx.indra.ingenset.dao;

import java.util.List;

import mx.indra.ingenset.bean.ColocacionMDABean;
import mx.indra.ingenset.bean.ColocacionMTRBean;

public interface IMercadoDao {
	
	public List<ColocacionMTRBean> getDataMTR(String tipoMercado, String unidadHidroElectrica, String fechaInicio, Integer fileVersionMTR);

	public List<ColocacionMDABean> getDataMDA(String tipoMercado, String unidadHidroElectrica, String fechaInicio, Integer fileVersionMDA);

	public Integer getFileVersionMTR(String unidadHidroElectrica);

	public Integer getFileVersionMDA(String unidadHidroElectrica);
		
}
