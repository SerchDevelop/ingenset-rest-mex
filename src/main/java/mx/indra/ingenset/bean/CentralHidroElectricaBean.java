package mx.indra.ingenset.bean;

import java.io.Serializable;
import java.util.List;

public class CentralHidroElectricaBean implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String centralElectrica;
	private List<String> unidadesHidroElectricas;
	
	public CentralHidroElectricaBean(){
		
	}

	public String getCentralElectrica() {
		return centralElectrica;
	}

	public void setCentralElectrica(String centralElectrica) {
		this.centralElectrica = centralElectrica;
	}

	public List<String> getUnidadesHidroElectricas() {
		return unidadesHidroElectricas;
	}

	public void setUnidadesHidroElectricas(List<String> unidadesHidroElectricas) {
		this.unidadesHidroElectricas = unidadesHidroElectricas;
	}

}
