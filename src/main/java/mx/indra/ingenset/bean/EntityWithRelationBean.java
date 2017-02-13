package mx.indra.ingenset.bean;

public class EntityWithRelationBean {
	
	private Long id;
	private String name;
	private CodeBean type;
	private PeriodBean period;
	private Long priority;
	private Long stateIndex;

	public Long getId() {
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

	public CodeBean getType() {
		return this.type;
	}

	public void setType(CodeBean type) {
		this.type = type;
	}

	public PeriodBean getPeriod() {
		return this.period;
	}

	public void setPeriod(PeriodBean period) {
		this.period = period;
	}

	public Long getPriority() {
		return this.priority;
	}

	public void setPriority(Long priority) {
		this.priority = priority;
	}

	public Long getStateIndex() {
		return this.stateIndex;
	}

	public void setStateIndex(Long stateIndex) {
		this.stateIndex = stateIndex;
	}

}
