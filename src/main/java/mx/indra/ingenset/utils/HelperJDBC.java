package mx.indra.ingenset.utils;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;

public class HelperJDBC {
	
	  private static JdbcTemplate jdbcTemplate;
	  
	  public HelperJDBC(){
		  
	  }
	  
	  public HelperJDBC(DataSource dataSource){
		  this.jdbcTemplate = new JdbcTemplate(dataSource);
	  }

	public static JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}

	public static void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		HelperJDBC.jdbcTemplate = jdbcTemplate;
	}

}
