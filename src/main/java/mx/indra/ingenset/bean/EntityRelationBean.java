package mx.indra.ingenset.bean;

import java.util.List;

public class EntityRelationBean {
	
	private List<EntityWithRelationBean> parents;
	private List<EntityWithRelationBean> children;

	public List<EntityWithRelationBean> getParents() {
		return this.parents;
	}

	public void setParents(List<EntityWithRelationBean> parents) {
		this.parents = parents;
	}

	public List<EntityWithRelationBean> getChildren() {
		return this.children;
	}

	public void setChildren(List<EntityWithRelationBean> children) {
		this.children = children;
	}

}
