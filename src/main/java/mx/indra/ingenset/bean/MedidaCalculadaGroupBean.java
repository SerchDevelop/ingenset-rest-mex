package mx.indra.ingenset.bean;

import java.io.Serializable;

public class MedidaCalculadaGroupBean implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer groupID;
	private String description;
	private String codeUser;
	
	public void MedidaCalculada(){
		
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Integer getGroupID() {
		return groupID;
	}

	public void setGroupID(Integer groupID) {
		this.groupID = groupID;
	}

	public String getCodeUser() {
		return codeUser;
	}

	public void setCodeUser(String codeUser) {
		this.codeUser = codeUser;
	}

}
