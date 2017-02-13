package mx.indra.ingenset.bean;

import java.io.Serializable;

public class PlantaBean implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer idUnidadGeneradora;
	private String unidadGeneradora;
	
	public PlantaBean(){
		
	}

	public Integer getIdUnidadGeneradora() {
		return idUnidadGeneradora;
	}

	public void setIdUnidadGeneradora(Integer idUnidadGeneradora) {
		this.idUnidadGeneradora = idUnidadGeneradora;
	}

	public String getUnidadGeneradora() {
		return unidadGeneradora;
	}

	public void setUnidadGeneradora(String unidadGeneradora) {
		this.unidadGeneradora = unidadGeneradora;
	}

}
