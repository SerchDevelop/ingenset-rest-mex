package mx.indra.ingenset.bean;

import java.io.Serializable;

public class ColocacionPrecioMTRBean implements Serializable, Comparable<ColocacionPrecioMTRBean> {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer sequencia;
	private Double value;

	public ColocacionPrecioMTRBean(){
		
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
	public int compareTo(ColocacionPrecioMTRBean colocacionMTR) {
		
		if(this.sequencia < colocacionMTR.getSequencia())
			return -1;
		
		else if(this.sequencia > colocacionMTR.getSequencia())
			return 1;
		
		else
			return 0;

	}

}
