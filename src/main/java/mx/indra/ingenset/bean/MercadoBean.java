package mx.indra.ingenset.bean;

import java.io.Serializable;
import java.util.List;

public class MercadoBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<MercadoTiempoRealBean> mercadoTiempoReal;
	private List<MercadoDiaAdelantoBean> mercadoDiaAdelanto;
	
	public MercadoBean(){
		
	}

	public List<MercadoDiaAdelantoBean> getMercadoDiaAdelanto() {
		return mercadoDiaAdelanto;
	}

	public void setMercadoDiaAdelanto(List<MercadoDiaAdelantoBean> mercadoDiaAdelanto) {
		this.mercadoDiaAdelanto = mercadoDiaAdelanto;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public List<MercadoTiempoRealBean> getMercadoTiempoReal() {
		return mercadoTiempoReal;
	}

	public void setMercadoTiempoReal(List<MercadoTiempoRealBean> mercadoTiempoReal) {
		this.mercadoTiempoReal = mercadoTiempoReal;
	}
	
}
