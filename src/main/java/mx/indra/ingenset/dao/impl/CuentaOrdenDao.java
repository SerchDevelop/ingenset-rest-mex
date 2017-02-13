package mx.indra.ingenset.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import mx.indra.ingenset.bean.EntityDatosDiariosBean;
import mx.indra.ingenset.dao.ICuentaOrdenDao;
import mx.indra.ingenset.utils.HelperJDBC;

@Repository("cuentaOrdenDao")
public class CuentaOrdenDao extends HelperJDBC implements ICuentaOrdenDao {
	
	static final Logger logger = Logger.getLogger(CuentaOrdenDao.class);

	@Override
	public List<EntityDatosDiariosBean> getRelationCuentaOrden(Integer entityID) {
		
		String method = "getRelationCuentaOrden";
		logger.info("Dao :: CuentaOrdenDao :: " + method);

		String sql = "  SELECT TCE.DESC_SHORT ,TCER.ID_ENTITY, TCER.ID_ENTITY_RELATION FROM INGEN_SET_MARKET.TRD_CEN_ENTITY_RELATION TCER, INGEN_SET_MARKET.trd_cen_entity TCE"
				+ "	WHERE TCER.ID_ENTITY = ("
				+ "			SELECT TRCE.ID_ENTITY_RELATION	"
				+ "			FROM INGEN_SET_MARKET.TRD_CEN_ENTITY_RELATION TRCE, INGEN_SET_MARKET.TRD_CEN_ENTITY_REL_PERIOD TCERP"
				+ "			WHERE TRCE.ID_ENTITY = TCERP.ID_ENTITY"
				+ "			AND TCERP.ID_ENTITY = ?)"
				+ "	AND TCER.ID_ENTITY_RELATION = TCE.ID_ENTITY";
		
		List<EntityDatosDiariosBean> listBean = getJdbcTemplate().query(sql, new Object[] {
				entityID
			}, new RowMapper<EntityDatosDiariosBean>() {

			@Override
			public EntityDatosDiariosBean mapRow(ResultSet rs, int rowNum) throws SQLException {
				
				EntityDatosDiariosBean entityBean = new EntityDatosDiariosBean();

				entityBean.setDescriptionShort(rs.getString("DESC_SHORT"));
				entityBean.setEntityID(rs.getInt("ID_ENTITY"));
				entityBean.setEntityRelationID(rs.getInt("ID_ENTITY_RELATION"));
				
				return entityBean;
				
			}

		});

		return listBean;
}

}
