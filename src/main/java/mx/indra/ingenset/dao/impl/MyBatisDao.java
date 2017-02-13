package mx.indra.ingenset.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import mx.indra.ingenset.bean.ColocacionMTRBean;
import mx.indra.ingenset.bean.Fenix;
import mx.indra.ingenset.dao.IMyBatisDao;
import mx.indra.ingenset.utils.HelperJDBC;

@Repository("myBatisDao")
public class MyBatisDao extends HelperJDBC implements IMyBatisDao {
	
	static final Logger logger = Logger.getLogger(MyBatisDao.class);

	@Override
	public Integer getData() {
		
		String method = "getData";
		logger.info("Dao :: MyBatisDao :: " + method);

		return null;
	}

	@Override
	public List<Fenix> getAllResultados() {
		
		String method = "getAllResultados";
		logger.info("Dao :: MyBatisDao :: " + method);

		String sql = "SELECT * FROM INGEN_SET_MARKET.TEST_FENIX";
		List<Fenix> listFenix = getJdbcTemplate().query(sql, new RowMapper<Fenix>() {

			@Override
			public Fenix mapRow(ResultSet rs, int rowNum) throws SQLException {
				
				Fenix fenix = new Fenix();

				fenix.setDia(rs.getString("FECHA"));
				fenix.setEstatus(rs.getString("ESTATUS_FENIX"));
				fenix.setMontoNeto(rs.getString("MONTO_FENIX"));
				fenix.setIva(rs.getString("IVA_FENIX"));
				fenix.setTotal(rs.getString("TOTAL_FENIX"));

				return fenix;
				
			}

		});
		
		logger.info("Lista :: " + listFenix.size());

		return listFenix;
	}
	
	@Override
	public List<ColocacionMTRBean> getDataMTR(String tipoMercado, String unidadHidroElectrica, String fechaInicio) {
		
		String method = "getDataMTR";
		logger.info("Dao :: MyBatisDao :: " + method);

		String sql = "SELECT   C.MED_DESC, C.DESC_ENT, D.DATETIME_FROM, D.DATETIME_TO, A.ID_MEASURE_DETAIL, A.NUMBER_SEQ_PRIM,"
                          + " A.NUMBER_SEQ_SEC, A.NUMBER_VALUE, D.FILE_VERSION, A.ORIGIN, B.ID_MEASURE, E.CODE_PRIM_TIME_UNIT, E.CODE_SEC_TIME_UNIT"
                          + " FROM INGEN_SET_MARKET.TRD_CEN_MEASURE_VALUE A, INGEN_SET_MARKET.TRD_CEN_MSR_MSR_DETAIL B,"
                          + " INGEN_SET_MARKET.V_MEASURE_ENTITY C, INGEN_SET_MARKET.TRD_CEN_MEASURE_DETAIL D, INGEN_SET_MARKET.TRD_CEN_MEASURE_TYPE E"
                          + " WHERE A.ID_MEASURE_DETAIL=B.ID_MEASURE_DETAIL"
                          + " AND B.ID_MEASURE       =C.ID_MEASURE"
                          + " AND D.ID_MEASURE_DETAIL=B.ID_MEASURE_DETAIL"
                          + " AND E.ID_MEASURE_TYPE  =C.ID_MEASURE_TYPE"
                          + " AND C.MED_DESC = 'Colocación MTR'"
                          + " AND C.DESC_ENT = '" + unidadHidroElectrica + "'"
                          + " AND D.DATETIME_FROM = '" + fechaInicio + "'"
                          + " ORDER BY A.NUMBER_SEQ_SEC";
		
		List<ColocacionMTRBean> listMTR = getJdbcTemplate().query(sql, new RowMapper<ColocacionMTRBean>() {

			@Override
			public ColocacionMTRBean mapRow(ResultSet rs, int rowNum) throws SQLException {
				
				ColocacionMTRBean mtr = new ColocacionMTRBean();

				mtr.setNUMBER_SEQ_SEC(rs.getInt("NUMBER_SEQ_SEC"));
				mtr.setNUMBER_VALUE(rs.getDouble("NUMBER_VALUE"));

				return mtr;
				
			}

		});
		
		for (ColocacionMTRBean colocacionMTRBean : listMTR) {
			logger.info("getNUMBER_SEQ_SEC  :: " + colocacionMTRBean.getNUMBER_SEQ_SEC());
			logger.info("getNUMBER_VALUE  :: " + colocacionMTRBean.getNUMBER_VALUE());
		}
		

		return listMTR;
	}

	  
}
