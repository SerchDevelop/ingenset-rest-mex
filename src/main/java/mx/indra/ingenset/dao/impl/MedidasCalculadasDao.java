package mx.indra.ingenset.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import mx.indra.ingenset.bean.MedidaCalculadaBean;
import mx.indra.ingenset.bean.MedidaCalculadaGroupBean;
import mx.indra.ingenset.dao.IMedidasCalculadasDao;
import mx.indra.ingenset.utils.HelperJDBC;

@Repository("medidasCalculadasDao")
public class MedidasCalculadasDao extends HelperJDBC implements IMedidasCalculadasDao {
	
	static final Logger logger = Logger.getLogger(MedidasCalculadasDao.class);

	@Override
	public Integer existGroupMedidaCalculada(MedidaCalculadaGroupBean medidaCalculada) {

		String sql = "SELECT COUNT(*) FROM INGEN_SET_MARKET.C_FORMULAS_GROUP WHERE GROUP_NAME = ?";

		return getJdbcTemplate().queryForObject(sql, new Object[] { medidaCalculada.getDescription() }, Integer.class);

	}
	
	@Override
	public Integer firstGroupMedidaCalculada() {

		String sql = "SELECT COUNT(*) FROM INGEN_SET_MARKET.C_FORMULAS_GROUP";

		return getJdbcTemplate().queryForObject(sql, Integer.class);

	}
	
	@Override
	public Integer saveGroupMedidaCalculada(MedidaCalculadaGroupBean medidaCalculada) {

		// INGEN_SET_MARKET.C_FORMULAS_GROUP_SEQUENCE.nextval
		String sql = "INSERT INTO INGEN_SET_MARKET.C_FORMULAS_GROUP(ID_GROUP_FORMULA, GROUP_NAME, CODE_STATUS, DATE_INSERT, DATE_MOD, CODE_USER, PROCESS)"
				+ "	VALUES(INGEN_SET_MARKET.C_FORMULAS_GROUP_SEQUENCE.nextval, ?, 'CES001', sysdate, sysdate, ?, 'Rest')";
		
		return getJdbcTemplate().update(sql, new Object[] {
				medidaCalculada.getDescription(),
				medidaCalculada.getCodeUser()
			});

	}
	
	@Override
	public Integer updateGroupMedidaCalculada(MedidaCalculadaGroupBean medidaCalculada) {

		String sql = "UPDATE INGEN_SET_MARKET.C_FORMULAS_GROUP SET"
				+ "	GROUP_NAME = ?,"
				+ "	DATE_MOD = sysdate,"
				+ "	CODE_USER = ?"
				+ "	WHERE ID_GROUP_FORMULA = ?";

		return getJdbcTemplate().update(sql, new Object[] {
				medidaCalculada.getDescription(),
				medidaCalculada.getCodeUser(),
				medidaCalculada.getGroupID()
		});

	}
	
	@Override
	public Integer deleteGroupMedidaCalculada(MedidaCalculadaGroupBean medidaCalculada) {

		String sql = "UPDATE INGEN_SET_MARKET.C_FORMULAS_GROUP SET"
				+ "	CODE_STATUS = 'CES000',"
				+ "	DATE_MOD = sysdate,"
				+ "	CODE_USER = ?"
				+ "	WHERE ID_GROUP_FORMULA = ?";

		return getJdbcTemplate().update(sql, new Object[] {
				medidaCalculada.getCodeUser(),
				medidaCalculada.getGroupID()
		});

	}

	@Override
	public List<MedidaCalculadaGroupBean> listGroupMedidasCalculadas() {
			
			String method = "listMedidasCalculadas";
			logger.info("Dao :: MedidasCalculadasDao :: " + method);

			String sql = "SELECT ID_GROUP_FORMULA, GROUP_NAME"
					+ "	FROM INGEN_SET_MARKET.C_FORMULAS_GROUP"
					+ "	WHERE CODE_STATUS = 'CES001'"
					+ "	ORDER BY ID_GROUP_FORMULA";
			
			List<MedidaCalculadaGroupBean> listMedidaBean = getJdbcTemplate().query(sql, new RowMapper<MedidaCalculadaGroupBean>() {

				@Override
				public MedidaCalculadaGroupBean mapRow(ResultSet rs, int rowNum) throws SQLException {
					
					MedidaCalculadaGroupBean medidaBean = new MedidaCalculadaGroupBean();

					medidaBean.setGroupID(rs.getInt("ID_GROUP_FORMULA"));
					medidaBean.setDescription(rs.getString("GROUP_NAME"));
					
					return medidaBean;
					
				}

			});

			return listMedidaBean;
	}
	
	@Override
	public Integer firstMedidaCalculada() {

		String sql = "SELECT COUNT(*) FROM INGEN_SET_MARKET.C_FORMULAS_DETAIL";

		return getJdbcTemplate().queryForObject(sql, Integer.class);

	}

	@Override
	public Integer saveMedidaCalculada(MedidaCalculadaBean medidaCalculada) {
		
		String sql = "INSERT INTO INGEN_SET_MARKET.C_FORMULAS_DETAIL(ID_FORMULA, VERSION_FORMULA, ID_GROUP_FORMULA, NAME_MEASURE, DESCRIPTION_MEASURE,"
					+ "	UNIT_CODE, PERIODICITY_CODE, CODE_STATUS, DATE_INSERT, DATE_MOD, CODE_USER, PROCESS)"
					+ "	VALUES(?, ?, ?, ?, ?, ?, ?, 'CES001', sysdate, sysdate, ?, 'Rest')";
		
			return getJdbcTemplate().update(sql, new Object[] { 
					medidaCalculada.getFormulaID(),
					medidaCalculada.getVersionFormula(),
					medidaCalculada.getGroupMedidaCalculadaID(),
					medidaCalculada.getNombreUnidadMedidaCalculada(),
					medidaCalculada.getDescription(),
					medidaCalculada.getUnidad(),
					medidaCalculada.getPeriodicidad(),
					medidaCalculada.getCodeUser()
			});
			
	}
	
	@Override
	public Integer updateMedidaCalculada(MedidaCalculadaBean medidaCalculada) {

		String sql = "UPDATE INGEN_SET_MARKET.C_FORMULAS_DETAIL	SET"
				+ "	NAME_MEASURE = ?,"
				+ "	DESCRIPTION_MEASURE = ?,"
				+ "	UNIT_CODE = ?,"
				+ "	PERIODICITY_CODE = ?,"
				+ "	ID_GROUP_FORMULA = ?,"
				+ "	DATE_MOD = sysdate,"
				+ "	CODE_USER = ?"
				+ "	WHERE ID_FORMULA = ?"
				+ "	AND VERSION_FORMULA = ?";
		
		return getJdbcTemplate().update(sql, new Object[] {
				medidaCalculada.getNombreUnidadMedidaCalculada(),
				medidaCalculada.getDescription(),
				medidaCalculada.getUnidad(),
				medidaCalculada.getPeriodicidad(),
				medidaCalculada.getGroupMedidaCalculadaID(),
				medidaCalculada.getCodeUser(),
				medidaCalculada.getFormulaID(),
				medidaCalculada.getVersionFormula()
		});

	}

	@Override
	public Integer deleteMedidaCalculada(MedidaCalculadaBean medidaCalculada) {

		String sql = "UPDATE INGEN_SET_MARKET.C_FORMULAS_DETAIL	SET"
				+ "	CODE_STATUS = 'CES000',"
				+ "	DATE_MOD = sysdate,"
				+ "	CODE_USER = ?"
				+ "	WHERE ID_FORMULA = ?"
				+ "	AND VERSION_FORMULA = ?";

		return getJdbcTemplate().update(sql, new Object[] {
				medidaCalculada.getCodeUser(),
				medidaCalculada.getFormulaID(),
				medidaCalculada.getVersionFormula()
			});

	}

	@Override
	public List<MedidaCalculadaBean> listMedidasCalculadasGroup(Integer groupMedidaCalculada) {

		String method = "listMedidasCalculadas";
		logger.info("Dao :: MedidasCalculadasDao :: " + method);

		String sql = "SELECT UC.ID_FORMULA, UC.VERSION_FORMULA, UC.ID_GROUP_FORMULA, CFG.GROUP_NAME, UC.NAME_MEASURE, UC.DESCRIPTION_MEASURE, UC.UNIT_CODE,"
				+ "	CASE"
				+ "		WHEN (SELECT COUNT(*) FROM INGEN_SET_MARKET.UNITS WHERE ID_UNIT = UC.UNIT_CODE AND CODE_STATUS = 'CES001') = 0 THEN (SELECT NAME_CURR FROM INGEN_SET_MARKET.CURRENCY WHERE ID_CURRENCY = UC.UNIT_CODE AND CODE_STATUS = 'CES001')"
				+ "		WHEN (SELECT COUNT(*) FROM INGEN_SET_MARKET.UNITS WHERE ID_UNIT = UC.UNIT_CODE AND CODE_STATUS = 'CES001') > 0 THEN (SELECT DESC_UNIT_SHORT FROM INGEN_SET_MARKET.UNITS WHERE ID_UNIT = UC.UNIT_CODE AND CODE_STATUS = 'CES001')"
				+ "		END UNIT_DESC, UC.PERIODICITY_CODE,"
				+ "	(SELECT TEXT FROM INGEN_SET_MARKET.codes c, INGEN_SET_MARKET.dictionary_lang d WHERE c.id_dict = d.id_dict AND id_lang = 3 AND c.code = UC.PERIODICITY_CODE) PERIODICIDAD_DESC"
				+ "	FROM INGEN_SET_MARKET.C_FORMULAS_DETAIL UC, INGEN_SET_MARKET.C_FORMULAS_GROUP CFG"
				+ "	WHERE UC.ID_GROUP_FORMULA = CFG.ID_GROUP_FORMULA"
				+ "	AND UC.CODE_STATUS = 'CES001'"
				+ "	AND ID_GROUP_FORMULA = ?"
				+ "	ORDER BY ID_FORMULA";
		
		List<MedidaCalculadaBean> listMedidaBean = getJdbcTemplate().query(sql, new Object[] { groupMedidaCalculada }, new RowMapper<MedidaCalculadaBean>() {

			@Override
			public MedidaCalculadaBean mapRow(ResultSet rs, int rowNum) throws SQLException {
				
				MedidaCalculadaBean medidaBean = new MedidaCalculadaBean();

				medidaBean.setGroupMedidaCalculadaID(rs.getInt("ID_GROUP_FORMULA"));
				medidaBean.setDescriptionGroup(rs.getString("GROUP_NAME"));
				medidaBean.setFormulaID(rs.getInt("ID_FORMULA"));
				medidaBean.setVersionFormula(rs.getInt("VERSION_FORMULA"));
				medidaBean.setNombreUnidadMedidaCalculada(rs.getString("NAME_MEASURE"));
				medidaBean.setDescription(rs.getString("DESCRIPTION_MEASURE"));
				medidaBean.setUnidad(rs.getInt("UNIT_CODE"));
				medidaBean.setUnidadDescription(rs.getString("UNIT_DESC"));
				medidaBean.setPeriodicidad(rs.getString("PERIODICITY_CODE"));
				medidaBean.setPeriodicidadDescription(rs.getString("PERIODICIDAD_DESC"));
				
				return medidaBean;
				
			}

		});

		return listMedidaBean;
	}
	
	
	@Override
	public List<MedidaCalculadaBean> listMedidasCalculadas() {

		String method = "listMedidasCalculadas";
		logger.info("Dao :: MedidasCalculadasDao :: " + method);

		String sql = "SELECT UC.ID_FORMULA, UC.VERSION_FORMULA, UC.ID_GROUP_FORMULA, CFG.GROUP_NAME, UC.NAME_MEASURE, UC.DESCRIPTION_MEASURE, UC.UNIT_CODE,"
				+ "	CASE"
				+ "		WHEN (SELECT COUNT(*) FROM INGEN_SET_MARKET.UNITS WHERE ID_UNIT = UC.UNIT_CODE AND CODE_STATUS = 'CES001') = 0 THEN (SELECT NAME_CURR FROM INGEN_SET_MARKET.CURRENCY WHERE ID_CURRENCY = UC.UNIT_CODE AND CODE_STATUS = 'CES001')"
				+ "		WHEN (SELECT COUNT(*) FROM INGEN_SET_MARKET.UNITS WHERE ID_UNIT = UC.UNIT_CODE AND CODE_STATUS = 'CES001') > 0 THEN (SELECT DESC_UNIT_SHORT FROM INGEN_SET_MARKET.UNITS WHERE ID_UNIT = UC.UNIT_CODE AND CODE_STATUS = 'CES001')"
				+ "		END UNIT_DESC, UC.PERIODICITY_CODE,"
				+ "	(SELECT TEXT FROM INGEN_SET_MARKET.codes c, INGEN_SET_MARKET.dictionary_lang d WHERE c.id_dict = d.id_dict AND id_lang = 3 AND c.code = UC.PERIODICITY_CODE) PERIODICIDAD_DESC"
				+ "	FROM INGEN_SET_MARKET.C_FORMULAS_DETAIL UC, INGEN_SET_MARKET.C_FORMULAS_GROUP CFG"
				+ "	WHERE UC.ID_GROUP_FORMULA = CFG.ID_GROUP_FORMULA"
				+ "	AND UC.CODE_STATUS = 'CES001'"
				+ "	ORDER BY ID_FORMULA";
		
		List<MedidaCalculadaBean> listMedidaBean = getJdbcTemplate().query(sql, new RowMapper<MedidaCalculadaBean>() {

			@Override
			public MedidaCalculadaBean mapRow(ResultSet rs, int rowNum) throws SQLException {
				
				MedidaCalculadaBean medidaBean = new MedidaCalculadaBean();

				medidaBean.setGroupMedidaCalculadaID(rs.getInt("ID_GROUP_FORMULA"));
				medidaBean.setDescriptionGroup(rs.getString("GROUP_NAME"));
				medidaBean.setFormulaID(rs.getInt("ID_FORMULA"));
				medidaBean.setVersionFormula(rs.getInt("VERSION_FORMULA"));
				medidaBean.setNombreUnidadMedidaCalculada(rs.getString("NAME_MEASURE"));
				medidaBean.setDescription(rs.getString("DESCRIPTION_MEASURE"));
				medidaBean.setUnidad(rs.getInt("UNIT_CODE"));
				medidaBean.setUnidadDescription(rs.getString("UNIT_DESC"));
				medidaBean.setPeriodicidad(rs.getString("PERIODICITY_CODE"));
				medidaBean.setPeriodicidadDescription(rs.getString("PERIODICIDAD_DESC"));
				
				return medidaBean;
				
			}

		});

		return listMedidaBean;
	}
	
	@Override
	public Integer updateVersionFormula(Integer formulaID, Integer versionFormula) {

		String sql = "UPDATE INGEN_SET_MARKET.C_FORMULAS_DETAIL"
				+ "	SET VERSION_FORMULA = ?,"
				+ "	DATE_MOD = sysdate"
				+ "	WHERE ID_FORMULA = ?";

		return getJdbcTemplate().update(sql, new Object[] {
				versionFormula,
				formulaID
		});

	}
	
	@Override
	public Integer getVersionFormula(Integer formulaID) {

		String sql = "SELECT MAX(VER_FORMULA) VER_FORMULA FROM INGEN_SET_MARKET.c_formulas WHERE ID_FORMULA = ?";

		return getJdbcTemplate().queryForObject(sql, new Object[] { formulaID }, Integer.class);
	}

	@Override
	public Integer getTotalGroupMC() {

		String sql = "SELECT COUNT(*) TOTAL FROM INGEN_SET_MARKET.C_FORMULAS_GROUP"
				+ "	WHERE CODE_STATUS = 'CES001'";
		
		Integer totalGroups = getJdbcTemplate().queryForObject(sql, Integer.class);
		
		return totalGroups;
		
	}

}
