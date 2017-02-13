package mx.indra.ingenset.bean;

import java.util.List;

public class AttributeGroupBean {
	
	private Long id;
	private String name;
	private AttributeFormatBean format;
	private List<AttributeBean> attributes;

	public Long getid() {
		return this.id;
	}

	public void setId(long id) {
		this.id = Long.valueOf(id);
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public AttributeFormatBean getFormat() {
		return this.format;
	}

	public void setFormat(AttributeFormatBean format) {
		this.format = format;
	}

	public List<AttributeBean> getAttributes() {
		return this.attributes;
	}

	public void setAttributes(List<AttributeBean> attributes) {
		this.attributes = attributes;
	}

}
