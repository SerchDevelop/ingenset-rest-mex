package mx.indra.ingenset.bean;

import java.util.List;

public class AttributeBean {
	
	private Long id;
	private Long idType;
	private String name;
	private List<AttributeDetailNumberBean> detail;

	public Long getId() {
		return this.id;
	}

	public void setId(long id) {
		this.id = Long.valueOf(id);
	}

	public Long getIdType() {
		return this.idType;
	}

	public void setIdType(long idType) {
		this.idType = Long.valueOf(idType);
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<AttributeDetailNumberBean> getDetail() {
		return this.detail;
	}

	public void setDetail(List<AttributeDetailNumberBean> detail) {
		this.detail = detail;
	}	

}
