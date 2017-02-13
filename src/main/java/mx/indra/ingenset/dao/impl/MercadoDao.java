package mx.indra.ingenset.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import mx.indra.ingenset.bean.ColocacionMDABean;
import mx.indra.ingenset.bean.ColocacionMTRBean;
import mx.indra.ingenset.dao.IMercadoDao;
import mx.indra.ingenset.utils.HelperJDBC;

@Repository("mercadoDao")
public class MercadoDao extends HelperJDBC implements IMercadoDao {
	
	static final Logger logger = Logger.getLogger(MercadoDao.class);

	@Override
	public List<ColocacionMTRBean> getDataMTR(String tipoMercado, String unidadHidroElectrica, String fechaInicio, Integer fileVersionMTR) {
		
		String method = "getDataMTR :: 1008";
		logger.info("Dao :: MercadoDao :: " + method);
		 
//		String sql = "SELECT /*+ index(INGEN_SET_MARKET.TRD_CEN_MEASURE_VALUE TRD_CEN_MEAS_VA_CMP) */"
//						+ " A.NUMBER_SEQ_SEC, A.NUMBER_VALUE, D.FILE_VERSION"
//						+ " /*+ index(INGEN_SET_MARKET.TRD_CEN_MEASURE_VALUE TRD_CEN_MEAS_VA_CMP) */"
		String sql = "SELECT A.NUMBER_SEQ_SEC, A.NUMBER_VALUE, D.FILE_VERSION"
						+ " FROM INGEN_SET_MARKET.TRD_CEN_MEASURE_VALUE A, INGEN_SET_MARKET.TRD_CEN_MSR_MSR_DETAIL B,"
						+ " INGEN_SET_MARKET.V_MEASURE_ENTITY C, INGEN_SET_MARKET.TRD_CEN_MEASURE_DETAIL D, INGEN_SET_MARKET.TRD_CEN_MEASURE_TYPE E"
						+ " WHERE A.ID_MEASURE_DETAIL=B.ID_MEASURE_DETAIL"
						+ " AND B.ID_MEASURE       =C.ID_MEASURE"
						+ " AND D.ID_MEASURE_DETAIL=B.ID_MEASURE_DETAIL"
						+ " AND E.ID_MEASURE_TYPE  =C.ID_MEASURE_TYPE"
						+ " AND E.ID_MEASURE_TYPE  = 1008"
						+ " AND C.DESC_ENT = '" + unidadHidroElectrica + "'"
						+ " AND TRUNC(TO_DATE('" + fechaInicio + "', 'DD/MM/YY')) BETWEEN TRUNC(D.DATETIME_FROM) AND TRUNC(D.DATETIME_TO)"
						+ " AND B.ID_MEASURE = (SELECT ID_MEASURE FROM INGEN_SET_MARKET.V_MEASURE_ENTITY WHERE DESC_ENT = '" + unidadHidroElectrica + "' AND ID_MEASURE_TYPE = 1008)"
						+ " AND A.NUMBER_SEQ_PRIM = ((TO_DATE('" + fechaInicio + "', 'DD/MM/YY') - TO_DATE(D.DATETIME_FROM, 'DD/MM/YY'))+1)"
						+ " AND D.FILE_VERSION = CONCAT('TX', (SELECT  MAX(REGEXP_REPLACE(UPPER(FILE_VERSION), 'TX', '')) VERSION"
						+ "			FROM INGEN_SET_MARKET.TRD_CEN_MEASURE_VALUE A, INGEN_SET_MARKET.TRD_CEN_MSR_MSR_DETAIL B,"
						+ "			INGEN_SET_MARKET.V_MEASURE_ENTITY C, INGEN_SET_MARKET.TRD_CEN_MEASURE_DETAIL D, INGEN_SET_MARKET.TRD_CEN_MEASURE_TYPE E"
						+ "			WHERE A.ID_MEASURE_DETAIL=B.ID_MEASURE_DETAIL"
						+ "			AND B.ID_MEASURE       =C.ID_MEASURE"
						+ "			AND D.ID_MEASURE_DETAIL=B.ID_MEASURE_DETAIL"
						+ "			AND E.ID_MEASURE_TYPE  =C.ID_MEASURE_TYPE"
						+ "			AND E.ID_MEASURE_TYPE  IN (1008)"
						+ "			AND C.DESC_ENT = '" + unidadHidroElectrica + "'"
						+ "			AND TRUNC(TO_DATE('" + fechaInicio + "', 'DD/MM/YY')) BETWEEN TRUNC(D.DATETIME_FROM) AND TRUNC(D.DATETIME_TO)"
						+ "			AND B.ID_MEASURE = (SELECT ID_MEASURE FROM INGEN_SET_MARKET.V_MEASURE_ENTITY WHERE DESC_ENT = '" + unidadHidroElectrica + "' AND ID_MEASURE_TYPE = 1008)"
						+ "			AND A.NUMBER_SEQ_PRIM = ((TO_DATE('" + fechaInicio + "', 'DD/MM/YY') - TO_DATE(D.DATETIME_FROM, 'DD/MM/YY'))+1)))"
						+ " ORDER BY A.NUMBER_SEQ_PRIM, A.NUMBER_SEQ_SEC, A.ORIGIN, B.DATE_MOD";
		
		List<ColocacionMTRBean> listMTR = getJdbcTemplate().query(sql, new RowMapper<ColocacionMTRBean>() {

			@Override
			public ColocacionMTRBean mapRow(ResultSet rs, int rowNum) throws SQLException {
				
				ColocacionMTRBean mtr = new ColocacionMTRBean();

				mtr.setNUMBER_SEQ_SEC(rs.getInt("NUMBER_SEQ_SEC"));
				mtr.setNUMBER_VALUE(rs.getDouble("NUMBER_VALUE"));
				mtr.setFILE_VERSION(rs.getString("FILE_VERSION"));
				
				return mtr;
				
			}

		});	

		return listMTR;
	}
	
	@Override
	public List<ColocacionMDABean> getDataMDA(String tipoMercado, String unidadHidroElectrica, String fechaInicio, Integer fileVersionMDA) {
		
		String method = "getDataMDA :: 1006";
		logger.info("Dao :: MercadoDao :: " + method);

//		String sql = "SELECT /*+ index(INGEN_SET_MARKET.TRD_CEN_MEASURE_VALUE TRD_CEN_MEAS_VA_CMP) */"
//		+ " A.NUMBER_SEQ_SEC, A.NUMBER_VALUE, D.FILE_VERSION"
//		+ " /*+ index(INGEN_SET_MARKET.TRD_CEN_MEASURE_VALUE TRD_CEN_MEAS_VA_CMP) */"
		String sql = "SELECT A.NUMBER_SEQ_SEC, A.NUMBER_VALUE, D.FILE_VERSION"
				+ " FROM INGEN_SET_MARKET.TRD_CEN_MEASURE_VALUE A, INGEN_SET_MARKET.TRD_CEN_MSR_MSR_DETAIL B,"
				+ " INGEN_SET_MARKET.V_MEASURE_ENTITY C, INGEN_SET_MARKET.TRD_CEN_MEASURE_DETAIL D, INGEN_SET_MARKET.TRD_CEN_MEASURE_TYPE E"
				+ " WHERE A.ID_MEASURE_DETAIL=B.ID_MEASURE_DETAIL"
				+ " AND B.ID_MEASURE       =C.ID_MEASURE"
				+ " AND D.ID_MEASURE_DETAIL=B.ID_MEASURE_DETAIL"
				+ " AND E.ID_MEASURE_TYPE  =C.ID_MEASURE_TYPE"
				+ " AND E.ID_MEASURE_TYPE  = 1006"
				+ " AND C.DESC_ENT = '" + unidadHidroElectrica + "'"
				+ " AND TRUNC(TO_DATE('" + fechaInicio + "', 'DD/MM/YY')) BETWEEN TRUNC(D.DATETIME_FROM) AND TRUNC(D.DATETIME_TO)"
				+ " AND B.ID_MEASURE = (SELECT ID_MEASURE FROM INGEN_SET_MARKET.V_MEASURE_ENTITY WHERE DESC_ENT = '" + unidadHidroElectrica + "' AND ID_MEASURE_TYPE = 1006)"
				+ " AND A.NUMBER_SEQ_PRIM = ((TO_DATE('" + fechaInicio + "', 'DD/MM/YY') - TO_DATE(D.DATETIME_FROM, 'DD/MM/YY'))+1)"
				+ " AND D.FILE_VERSION = CONCAT('TX', (SELECT  MAX(REGEXP_REPLACE(UPPER(FILE_VERSION), 'TX', '')) VERSION"
				+ "			FROM INGEN_SET_MARKET.TRD_CEN_MEASURE_VALUE A, INGEN_SET_MARKET.TRD_CEN_MSR_MSR_DETAIL B,"
				+ "			INGEN_SET_MARKET.V_MEASURE_ENTITY C, INGEN_SET_MARKET.TRD_CEN_MEASURE_DETAIL D, INGEN_SET_MARKET.TRD_CEN_MEASURE_TYPE E"
				+ "			WHERE A.ID_MEASURE_DETAIL=B.ID_MEASURE_DETAIL"
				+ "			AND B.ID_MEASURE       =C.ID_MEASURE"
				+ "			AND D.ID_MEASURE_DETAIL=B.ID_MEASURE_DETAIL"
				+ "			AND E.ID_MEASURE_TYPE  =C.ID_MEASURE_TYPE"
				+ "			AND E.ID_MEASURE_TYPE  IN (1006)"
				+ "			AND C.DESC_ENT = '" + unidadHidroElectrica + "'"
				+ "			AND TRUNC(TO_DATE('" + fechaInicio + "', 'DD/MM/YY')) BETWEEN TRUNC(D.DATETIME_FROM) AND TRUNC(D.DATETIME_TO)"
				+ "			AND B.ID_MEASURE = (SELECT ID_MEASURE FROM INGEN_SET_MARKET.V_MEASURE_ENTITY WHERE DESC_ENT = '" + unidadHidroElectrica + "' AND ID_MEASURE_TYPE = 1006)"
				+ "			AND A.NUMBER_SEQ_PRIM = ((TO_DATE('" + fechaInicio + "', 'DD/MM/YY') - TO_DATE(D.DATETIME_FROM, 'DD/MM/YY'))+1)))"
				+ " ORDER BY A.NUMBER_SEQ_PRIM, A.NUMBER_SEQ_SEC, A.ORIGIN, B.DATE_MOD";
		
		List<ColocacionMDABean> listMTR = getJdbcTemplate().query(sql, new RowMapper<ColocacionMDABean>() {

			@Override
			public ColocacionMDABean mapRow(ResultSet rs, int rowNum) throws SQLException {
				
				ColocacionMDABean mtr = new ColocacionMDABean();

				mtr.setNUMBER_SEQ_SEC(rs.getInt("NUMBER_SEQ_SEC"));
				mtr.setNUMBER_VALUE(rs.getDouble("NUMBER_VALUE"));
				mtr.setFILE_VERSION(rs.getString("FILE_VERSION"));

				return mtr;
				
			}

		});
		
		return listMTR;
	}
	
	@Override
	public Integer getFileVersionMTR(String unidadHidroElectrica) {
		
		String method = "getFileVersionMTR";
		logger.info("Dao :: MercadoDao :: " + method);

		String sql = "SELECT  MAX(REGEXP_REPLACE(UPPER(FILE_VERSION), 'TX', ''))"
				+ " FROM INGEN_SET_MARKET.TRD_CEN_MSR_MSR_DETAIL B, INGEN_SET_MARKET.V_MEASURE_ENTITY C,"
				+ " INGEN_SET_MARKET.TRD_CEN_MEASURE_DETAIL D"
				+ " WHERE D.ID_MEASURE_DETAIL=B.ID_MEASURE_DETAIL"
				+ " AND B.ID_MEASURE       =C.ID_MEASURE"
				+ " AND C.DESC_ENT = '" + unidadHidroElectrica + "'";
		
		Integer medidaMaxima = getJdbcTemplate().queryForObject(sql, Integer.class);		

		return medidaMaxima;
	}
	
	@Override
	public Integer getFileVersionMDA(String unidadHidroElectrica) {
		
		String method = "getFileVersionMDA";
		logger.info("Dao :: MercadoDao :: " + method);

		String sql = "SELECT  MAX(REGEXP_REPLACE(UPPER(FILE_VERSION), 'TX', ''))"
						+ " FROM INGEN_SET_MARKET.TRD_CEN_MSR_MSR_DETAIL B, INGEN_SET_MARKET.V_MEASURE_ENTITY C,"
						+ " INGEN_SET_MARKET.TRD_CEN_MEASURE_DETAIL D"
						+ " WHERE D.ID_MEASURE_DETAIL=B.ID_MEASURE_DETAIL"
						+ " AND B.ID_MEASURE       =C.ID_MEASURE"
						+ " AND C.DESC_ENT = '" + unidadHidroElectrica + "'";
		
		Integer medidaMaxima = getJdbcTemplate().queryForObject(sql, Integer.class);		

		return medidaMaxima;
	}


}
