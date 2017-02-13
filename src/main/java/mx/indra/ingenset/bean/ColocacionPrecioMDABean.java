package mx.indra.ingenset.bean;

import java.io.Serializable;

public class ColocacionPrecioMDABean implements Serializable, Comparable<ColocacionPrecioMDABean> {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer sequencia;
	private Double value;

	public ColocacionPrecioMDABean(){
		
	}

	public Integer getSequencia() {
		return sequencia;
	}

	public void setSequencia(Integer sequencia) {
		this.sequencia = sequencia;
	}

	public Double getValue() {
		return value;
	}

	public void setValue(Double value) {
		this.value = value;
	}

	@Override
	public int compareTo(ColocacionPrecioMDABean colocacionMDA) {
		
		if(this.sequencia < colocacionMDA.getSequencia())
			return -1;
		
		else if(this.sequencia > colocacionMDA.getSequencia())
			return 1;
		
		else
			return 0;

	}
	
}