package mx.indra.ingenset.bean;

import java.io.Serializable;

public class MedidaCalculadaBean implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer formulaID;
	private Integer versionFormula;
	private Integer groupMedidaCalculadaID;
	private String descriptionGroup;
	private String nombreUnidadMedidaCalculada;
	private String description;
	private Integer unidad;
	private String unidadDescription;
	private String periodicidad;
	private String periodicidadDescription;
	private String codeUser;
	
	public void MedidaCalculada(){
		
	}

	public Integer getGroupMedidaCalculadaID() {
		return groupMedidaCalculadaID;
	}

	public void setGroupMedidaCalculadaID(Integer groupMedidaCalculadaID) {
		this.groupMedidaCalculadaID = groupMedidaCalculadaID;
	}

	public Integer getFormulaID() {
		return formulaID;
	}

	public void setFormulaID(Integer formulaID) {
		this.formulaID = formulaID;
	}

	public String getNombreUnidadMedidaCalculada() {
		return nombreUnidadMedidaCalculada;
	}

	public void setNombreUnidadMedidaCalculada(String nombreUnidadMedidaCalculada) {
		this.nombreUnidadMedidaCalculada = nombreUnidadMedidaCalculada;
	}

	public Integer getUnidad() {
		return unidad;
	}

	public void setUnidad(Integer unidad) {
		this.unidad = unidad;
	}

	public String getPeriodicidad() {
		return periodicidad;
	}

	public void setPeriodicidad(String periodicidad) {
		this.periodicidad = periodicidad;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getUnidadDescription() {
		return unidadDescription;
	}

	public void setUnidadDescription(String unidadDescription) {
		this.unidadDescription = unidadDescription;
	}

	public String getPeriodicidadDescription() {
		return periodicidadDescription;
	}

	public void setPeriodicidadDescription(String periodicidadDescription) {
		this.periodicidadDescription = periodicidadDescription;
	}

	public Integer getVersionFormula() {
		return versionFormula;
	}

	public void setVersionFormula(Integer versionFormula) {
		this.versionFormula = versionFormula;
	}

	public String getCodeUser() {
		return codeUser;
	}

	public void setCodeUser(String codeUser) {
		this.codeUser = codeUser;
	}

	public String getDescriptionGroup() {
		return descriptionGroup;
	}

	public void setDescriptionGroup(String descriptionGroup) {
		this.descriptionGroup = descriptionGroup;
	}
	
	

}
