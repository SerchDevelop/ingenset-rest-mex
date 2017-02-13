package mx.indra.ingenset.bean;

public class AttributeValueBean {
	
	private Long id;
	private String data;

	public Float floatData() {
		return Float.valueOf(this.data);
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getData() {
		return this.data;
	}

	public void setData(String data) {
		this.data = data;
	}

}
