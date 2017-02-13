package mx.indra.ingenset.service;

import java.util.List;

import mx.indra.ingenset.bean.MedidaCalculadaBean;
import mx.indra.ingenset.bean.MedidaCalculadaGroupBean;

public interface IMedidasCalculadasService {
	
	public Integer saveGroupMedidaCalculada(MedidaCalculadaGroupBean medidaCalculada);
	
	public Integer updateGroupMedidaCalculada(MedidaCalculadaGroupBean medidaCalculada);
	
	public Integer deleteGroupMedidaCalculada(MedidaCalculadaGroupBean medidaCalculada);
	
	public List<MedidaCalculadaGroupBean> listGroupMedidasCalculadas();
	
	public Integer saveMedidaCalculada(MedidaCalculadaBean medidaCalculada);
	
	public Integer updateMedidaCalculada(MedidaCalculadaBean medidaCalculada);
	
	public Integer deleteMedidaCalculada(MedidaCalculadaBean medidaCalculada);
	
	public List<MedidaCalculadaBean> listMedidasCalculadasGroup(Integer groupMedidaCalculada);
	
	public List<MedidaCalculadaBean> listMedidasCalculadas();
	
	public Integer updateVersionFormula(Integer formulaID, Integer versionFormula);
	
	public Integer getVersionFormula(Integer formulaID);
	
	public Integer getTotalGroupMC();

}
