package mx.indra.ingenset.service;

import java.util.List;

import mx.indra.ingenset.bean.EntityDatosDiariosBean;

public interface ICuentaOrdenService {
	
	public List<EntityDatosDiariosBean> getRelationCuentaOrden(Integer entityID);

}
