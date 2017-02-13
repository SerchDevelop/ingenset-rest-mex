package mx.indra.ingenset.service;

import java.sql.SQLException;
import java.util.List;
import mx.indra.ingenset.bean.ResultadosBean;

public interface IDatosService 
{
	public List<ResultadosBean> getObtenerDatos() throws SQLException;
	
	//public List<ResultadosBean> buscarDatosTablas(String identificador, String fecha) throws SQLException;
	
	public List<ResultadosBean> buscarDatosTablas(String identificador) throws SQLException;
	
/*	public ArrayList<Fenix> getObtenerDatosFenix() throws SQLException;
	
	public ArrayList<Cenace> getObtenerDatosCenace();
	
	public ArrayList<Resultados> getObtenerDatos2();
	
	public Resultados compararDatos(Fenix f, Cenace c); */

}
