package mx.indra.ingenset.bean;

import java.io.Serializable;

public class EntityDatosDiariosBean implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String descriptionShort;
	private Integer entityID;
	private Integer entityRelationID;
	
	public EntityDatosDiariosBean(){
		
	}

	public String getDescriptionShort() {
		return descriptionShort;
	}

	public void setDescriptionShort(String descriptionShort) {
		this.descriptionShort = descriptionShort;
	}

	public Integer getEntityID() {
		return entityID;
	}

	public void setEntityID(Integer entityID) {
		this.entityID = entityID;
	}

	public Integer getEntityRelationID() {
		return entityRelationID;
	}

	public void setEntityRelationID(Integer entityRelationID) {
		this.entityRelationID = entityRelationID;
	}

}
