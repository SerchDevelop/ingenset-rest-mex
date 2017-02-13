package mx.indra.ingenset.bean;

import java.io.Serializable;
import java.util.List;

public class MercadoDiaAdelantoBean implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String centralElectrica;
	private String fecha;
	private List<UnidadGeneradoraBean> unidadGeneradora;

	
	public MercadoDiaAdelantoBean(){
		
	}


	public String getCentralElectrica() {
		return centralElectrica;
	}


	public void setCentralElectrica(String centralElectrica) {
		this.centralElectrica = centralElectrica;
	}

	public String getFecha() {
		return fecha;
	}


	public void setFecha(String fecha) {
		this.fecha = fecha;
	}


	public List<UnidadGeneradoraBean> getUnidadGeneradora() {
		return unidadGeneradora;
	}


	public void setUnidadGeneradora(List<UnidadGeneradoraBean> unidadGeneradora) {
		this.unidadGeneradora = unidadGeneradora;
	}

}
