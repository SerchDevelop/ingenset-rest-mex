package mx.indra.ingenset.dao;

import java.util.List;

import mx.indra.ingenset.bean.EntityDatosDiariosBean;

public interface ICuentaOrdenDao {

	public List<EntityDatosDiariosBean> getRelationCuentaOrden(Integer entityID);
	
}
