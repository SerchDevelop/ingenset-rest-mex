package mx.indra.ingenset.controller;

import java.sql.SQLException;
import java.util.List;

import mx.indra.ingenset.bean.EntityDatosDiariosBean;
import mx.indra.ingenset.bean.ResultadosBean;

public interface IDatosController {
	
	public List<ResultadosBean> obtenerDatos() throws SQLException;

}
