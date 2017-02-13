package mx.indra.ingenset.dao;

import java.util.List;

import org.apache.ibatis.annotations.Select;

import mx.indra.ingenset.bean.ColocacionMTRBean;
import mx.indra.ingenset.bean.Fenix;


public interface IMyBatisDao {
	
//	@Select("SELECT COUNT(*) FROM INGEN_SET_MARKET.TEST_FENIX")
	public Integer getData();
	
//	@Select("SELECT * FROM INGEN_SET_MARKET.TEST_FENIX")
	public List<Fenix> getAllResultados();

	public List<ColocacionMTRBean> getDataMTR(String tipoMercado, String unidadHidroElectrica, String fechaInicio);

}
