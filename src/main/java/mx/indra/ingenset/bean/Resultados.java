package mx.indra.ingenset.bean;

import java.io.Serializable;

public class Resultados implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String precioFenix;
	private String precioCenace;
	private String montoNetoFenix;
	private String montoNetoCenace;
	private String totalFenix;
	private String totalCenace;
	private String estatusFenix;
	private String estatusCenace;
	private String diaFenix;
	private String diaCenace;
	private String ivaFenix;
	private String ivaCenace;
	
	public Resultados(){
		
	}

	public String getPrecioFenix() {
		return precioFenix;
	}

	public void setPrecioFenix(String precioFenix) {
		this.precioFenix = precioFenix;
	}

	public String getPrecioCenace() {
		return precioCenace;
	}

	public void setPrecioCenace(String precioCenace) {
		this.precioCenace = precioCenace;
	}

	public String getMontoNetoFenix() {
		return montoNetoFenix;
	}

	public void setMontoNetoFenix(String montoNetoFenix) {
		this.montoNetoFenix = montoNetoFenix;
	}

	public String getMontoNetoCenace() {
		return montoNetoCenace;
	}

	public void setMontoNetoCenace(String montoNetoCenace) {
		this.montoNetoCenace = montoNetoCenace;
	}

	public String getTotalFenix() {
		return totalFenix;
	}

	public void setTotalFenix(String totalFenix) {
		this.totalFenix = totalFenix;
	}

	public String getTotalCenace() {
		return totalCenace;
	}

	public void setTotalCenace(String totalCenace) {
		this.totalCenace = totalCenace;
	}

	public String getEstatusFenix() {
		return estatusFenix;
	}

	public void setEstatusFenix(String estatusFenix) {
		this.estatusFenix = estatusFenix;
	}

	public String getEstatusCenace() {
		return estatusCenace;
	}

	public void setEstatusCenace(String estatusCenace) {
		this.estatusCenace = estatusCenace;
	}

	public String getDiaFenix() {
		return diaFenix;
	}

	public void setDiaFenix(String diaFenix) {
		this.diaFenix = diaFenix;
	}

	public String getDiaCenace() {
		return diaCenace;
	}

	public void setDiaCenace(String diaCenace) {
		this.diaCenace = diaCenace;
	}

	public String getIvaFenix() {
		return ivaFenix;
	}

	public void setIvaFenix(String ivaFenix) {
		this.ivaFenix = ivaFenix;
	}

	public String getIvaCenace() {
		return ivaCenace;
	}

	public void setIvaCenace(String ivaCenace) {
		this.ivaCenace = ivaCenace;
	}		

}
