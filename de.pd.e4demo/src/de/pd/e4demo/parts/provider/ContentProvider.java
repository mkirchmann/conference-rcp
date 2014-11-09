package de.pd.e4demo.parts.provider;

import java.util.Collection;

import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.Viewer;

public class ContentProvider implements IStructuredContentProvider {

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void inputChanged(Viewer arg0, Object arg1, Object arg2) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Object[] getElements(Object arg0) {
		Object [] result;
		if (arg0 instanceof Collection) {
			result = ((Collection) arg0).toArray();
		} else {
			result = new Object[]{};
		}
		return result;
	}

}
