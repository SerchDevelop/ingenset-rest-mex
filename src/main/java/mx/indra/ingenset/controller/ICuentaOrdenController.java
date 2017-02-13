package mx.indra.ingenset.controller;

import java.util.List;

import mx.indra.ingenset.bean.EntityDatosDiariosBean;

public interface ICuentaOrdenController {
	
	public List<EntityDatosDiariosBean> getRelationCuentaOrden(Integer entityID);

}
