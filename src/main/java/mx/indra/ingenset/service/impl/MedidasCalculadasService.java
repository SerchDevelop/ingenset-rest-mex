package mx.indra.ingenset.service.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mx.indra.ingenset.bean.MedidaCalculadaBean;
import mx.indra.ingenset.bean.MedidaCalculadaGroupBean;
import mx.indra.ingenset.dao.IMedidasCalculadasDao;
import mx.indra.ingenset.service.IMedidasCalculadasService;

@Service("serviceMedidasCalculadas")
public class MedidasCalculadasService implements IMedidasCalculadasService {
	
	static final Logger logger = Logger.getLogger(MedidasCalculadasService.class);
	
	@Autowired
	IMedidasCalculadasDao medidasCalculadasDao;

	@Override
	public Integer saveGroupMedidaCalculada(MedidaCalculadaGroupBean medidaCalculada) {

		String method = "saveGroupMedidaCalculada";
		logger.info("Service MedidasCalculadasService :: " + method);
		
		/*
		 * Valido si ya existe la medida
		 */
		
		Integer exists = medidasCalculadasDao.existGroupMedidaCalculada(medidaCalculada);
		if(exists == 0){
			// Valido 1r registro
//			Integer firstRow = medidasCalculadasDao.firstGroupMedidaCalculada();
//			if(firstRow == 0){
				return medidasCalculadasDao.saveGroupMedidaCalculada(medidaCalculada);
//			}else
//				return medidasCalculadasDao.saveGroupMedidaCalculada(medidaCalculada, 2);
		}else
			return -1;
		
	}
	
	@Override
	public Integer updateGroupMedidaCalculada(MedidaCalculadaGroupBean medidaCalculada) {

		String method = "updateGroupMedidaCalculada";
		logger.info("Service MedidasCalculadasService :: " + method);

		Integer exists = medidasCalculadasDao.existGroupMedidaCalculada(medidaCalculada);
		if(exists == 0)
			return medidasCalculadasDao.updateGroupMedidaCalculada(medidaCalculada);
		else
			return -1;
	}

	@Override
	public Integer deleteGroupMedidaCalculada(MedidaCalculadaGroupBean medidaCalculada) {

		String method = "deleteGroupMedidaCalculada";
		logger.info("Service MedidasCalculadasService :: " + method);
		logger.info("Params :: idGroupDelete: " + medidaCalculada.getGroupID() + " :: Description :: " + medidaCalculada.getDescription());

		return medidasCalculadasDao.deleteGroupMedidaCalculada(medidaCalculada);
		
	}

	@Override
	public List<MedidaCalculadaGroupBean> listGroupMedidasCalculadas() {

		String method = "listGroupMedidasCalculadas";
		logger.info("Service MedidasCalculadasService :: " + method);
		
		return medidasCalculadasDao.listGroupMedidasCalculadas();
	}

	@Override
	public Integer saveMedidaCalculada(MedidaCalculadaBean medidaCalculada) {

		String method = "saveMedidaCalculada";
		logger.info("Service MedidasCalculadasService :: " + method);
		
/*		Integer first = medidasCalculadasDao.firstMedidaCalculada();
		if(first == 0)
			return medidasCalculadasDao.saveMedidaCalculada(medidaCalculada, 1);
		else
			return medidasCalculadasDao.saveMedidaCalculada(medidaCalculada, 2);
*/
		return medidasCalculadasDao.saveMedidaCalculada(medidaCalculada);
		
	}
	
	@Override
	public Integer updateMedidaCalculada(MedidaCalculadaBean medidaCalculada) {

		String method = "updateMedidaCalculada";
		logger.info("Service MedidasCalculadasService :: " + method);

		return medidasCalculadasDao.updateMedidaCalculada(medidaCalculada);

	}

	@Override
	public Integer deleteMedidaCalculada(MedidaCalculadaBean medidaCalculada) {

		String method = "deleteMedidaCalculada";
		logger.info("Service MedidasCalculadasService :: " + method);
		
		return medidasCalculadasDao.deleteMedidaCalculada(medidaCalculada);

	}

	@Override
	public List<MedidaCalculadaBean> listMedidasCalculadasGroup(Integer groupMedidaCalculada) {

		String method = "listMedidasCalculadasGroup";
		logger.info("Service MedidasCalculadasService :: " + method);
		logger.info("Params :: groupMedidaCalculada: " + groupMedidaCalculada);
		
		return medidasCalculadasDao.listMedidasCalculadasGroup(groupMedidaCalculada);
		
	}
	
	@Override
	public List<MedidaCalculadaBean> listMedidasCalculadas() {

		String method = "listMedidasCalculadas";
		logger.info("Service MedidasCalculadasService :: " + method);
		
		return medidasCalculadasDao.listMedidasCalculadas();
		
	}
	
	@Override
	public Integer updateVersionFormula(Integer formulaID, Integer versionFormula) {

		String method = "updateVersionFormula";
		logger.info("Service MedidasCalculadasService :: " + method);
		logger.info("Params :: formulaID: " + formulaID + " :: versionFormula: " + versionFormula);
		
		return medidasCalculadasDao.updateVersionFormula(formulaID, versionFormula); 
	}
	
	@Override
	public Integer getVersionFormula(Integer formulaID) {
	
		String method = "getVersionFormula";
		logger.info("Service MedidasCalculadasService :: " + method);
		logger.info("Params :: formulaID: " + formulaID);

		return medidasCalculadasDao.getVersionFormula(formulaID);
	}

	@Override
	public Integer getTotalGroupMC() {

		String method = "getTotalGroupMC";
		logger.info("Service MedidasCalculadasService :: " + method);
		
		return medidasCalculadasDao.getTotalGroupMC();

	}

}
