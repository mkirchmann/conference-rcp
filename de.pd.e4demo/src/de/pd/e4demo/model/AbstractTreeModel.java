package de.pd.e4demo.model;

import java.util.Collection;
import java.util.List;

public class AbstractTreeModel<ParentType, SelfType, ChildrenType> extends AbstractDelegatingModel<SelfType> {
	Collection<ChildrenType> children;
	private final ParentType parent;

	public AbstractTreeModel(final ParentType parent, final SelfType delegate, final List<ChildrenType> children) {
		super(delegate);
		this.parent = parent;
		this.children = children;
	}

	public Collection<ChildrenType> getChildren() {
		return children;
	}

	public ParentType getParent() {
		return parent;
	}

	public boolean hasChildren() {
		return children != null && !children.isEmpty();
	}

	public Object[] getChildrenAsArray() {
		Object[] result;
		if (children == null) {
			result = new Object[] {};
		} else {
			result = children.toArray();
		}
		return result;
	}

	public boolean addChild(final ChildrenType arg0) {
		return children.add(arg0);
	}

	public boolean removeChild(final Object arg0) {
		return children.remove(arg0);
	}

}
