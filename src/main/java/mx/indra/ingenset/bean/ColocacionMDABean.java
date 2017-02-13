package mx.indra.ingenset.bean;

import java.io.Serializable;

public class ColocacionMDABean implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer NUMBER_SEQ_SEC;
	private Double NUMBER_VALUE;
	private String FILE_VERSION;

	public ColocacionMDABean(){
		
	}

	public Integer getNUMBER_SEQ_SEC() {
		return NUMBER_SEQ_SEC;
	}

	public void setNUMBER_SEQ_SEC(Integer nUMBER_SEQ_SEC) {
		NUMBER_SEQ_SEC = nUMBER_SEQ_SEC;
	}

	public Double getNUMBER_VALUE() {
		return NUMBER_VALUE;
	}

	public void setNUMBER_VALUE(Double nUMBER_VALUE) {
		NUMBER_VALUE = nUMBER_VALUE;
	}

	public String getFILE_VERSION() {
		return FILE_VERSION;
	}

	public void setFILE_VERSION(String fILE_VERSION) {
		FILE_VERSION = fILE_VERSION;
	}

}
