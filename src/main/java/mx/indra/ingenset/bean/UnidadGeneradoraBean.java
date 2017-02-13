package mx.indra.ingenset.bean;

import java.io.Serializable;
import java.util.Map;

public class UnidadGeneradoraBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String centralElectrica;
	private String unidadGeneradora;
	private String fecha;
	private Map<String, Double> hora;
//	private List<ColocacionMercadoBean> listaMercado;
//	private List<ColocacionMTRBean> listaColocacionMTR;
//	private List<ColocacionMDABean> listaColocacionMDA;
	
	public UnidadGeneradoraBean(){
		
	}

	public String getCentralElectrica() {
		return centralElectrica;
	}

	public void setCentralElectrica(String centralElectrica) {
		this.centralElectrica = centralElectrica;
	}

	public String getUnidadGeneradora() {
		return unidadGeneradora;
	}

	public void setUnidadGeneradora(String unidadGeneradora) {
		this.unidadGeneradora = unidadGeneradora;
	}

	public String getFecha() {
		return fecha;
	}

	public void setFecha(String fecha) {
		this.fecha = fecha;
	}

	public Map<String, Double> getHora() {
		return hora;
	}

	public void setHora(Map<String, Double> hora) {
		this.hora = hora;
	}

}
