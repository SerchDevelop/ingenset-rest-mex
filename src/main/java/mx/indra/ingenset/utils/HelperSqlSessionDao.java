package mx.indra.ingenset.utils;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public abstract class HelperSqlSessionDao extends SqlSessionDaoSupport {
	
	private SqlSessionFactory sqlSessionFactory;

	public String ERROR_DATA_NOT_FOUND;

	public HelperSqlSessionDao() {
		// Cargando Log de MyBatis
		org.apache.ibatis.logging.LogFactory.useLog4JLogging();
	}

	public SqlSessionFactory getSqlSessionFactory() {
		return sqlSessionFactory;
	}

	@Autowired
	@Qualifier("sqlSessionFactory")
	public void setSqlSessionFactory(SqlSessionFactoryBean sqlSessionFactoryBean) throws Exception {
		super.setSqlSessionFactory(sqlSessionFactoryBean.getObject());
		this.sqlSessionFactory = sqlSessionFactoryBean.getObject();
	}

}