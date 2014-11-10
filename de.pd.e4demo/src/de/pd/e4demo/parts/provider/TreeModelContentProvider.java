package de.pd.e4demo.parts.provider;

import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.Viewer;

import de.pd.e4demo.model.AbstractTreeModel;

public class TreeModelContentProvider implements ITreeContentProvider {

	@Override
	public void dispose() {
		// TODO Auto-generated method stub

	}

	@Override
	public void inputChanged(final Viewer arg0, final Object arg1, final Object arg2) {
		// TODO Auto-generated method stub

	}

	@Override
	public Object[] getChildren(final Object arg0) {
		Object[] result;
		if (arg0 instanceof AbstractTreeModel) {
			result = ((AbstractTreeModel) arg0).getChildrenAsArray();
		} else {
			result = new Object[] {};
		}
		return result;
	}

	@Override
	public Object[] getElements(final Object arg0) {
		Object[] result;
		if (arg0 instanceof Object[]) {
			result = (Object[]) arg0;
		} else {
			result = new Object[] {};
		}
		return result;
	}

	@Override
	public Object getParent(final Object arg0) {
		Object result;
		if (arg0 instanceof AbstractTreeModel) {
			result = ((AbstractTreeModel) arg0).getParent();
		} else {
			result = null;
		}
		return result;
	}

	@Override
	public boolean hasChildren(final Object arg0) {
		boolean result;
		if (arg0 instanceof AbstractTreeModel) {
			result = ((AbstractTreeModel) arg0).hasChildren();
		} else {
			result = false;
		}
		return result;
	}

}
