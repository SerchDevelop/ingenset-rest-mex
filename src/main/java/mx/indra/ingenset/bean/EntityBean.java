package mx.indra.ingenset.bean;

import java.util.List;

public class EntityBean {
	private Long id;
	private String name;
	private String description;
	private CodeBean type;
	private List<AttributeGroupBean> attributeGroups;
	private List<TagBean> tags;
	private EntityRelationBean relations;

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

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public CodeBean getType() {
		return this.type;
	}

	public void setType(CodeBean type) {
		this.type = type;
	}

	public List<AttributeGroupBean> getAttributeGroups() {
		return this.attributeGroups;
	}

	public void setAttributeGroups(List<AttributeGroupBean> attributeGroups) {
		this.attributeGroups = attributeGroups;
	}

	public List<TagBean> getTags() {
		return this.tags;
	}

	public void setTags(List<TagBean> tags) {
		this.tags = tags;
	}

	public EntityRelationBean getRelations() {
		return this.relations;
	}

	public void setRelations(EntityRelationBean relations) {
		this.relations = relations;
	}
}
