package de.pd.e4demo.parts.provider;

import org.eclipse.jface.viewers.ILabelProviderListener;
import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.swt.graphics.Image;

import de.pd.e4demo.model.NameDescriptionHolder;

public class ConferenceLabelProvider implements ITableLabelProvider {

	enum Column {
		Name, Description
	}

	@Override
	public void addListener(final ILabelProviderListener arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean isLabelProperty(final Object arg0, final String arg1) {
		return true;
	}

	@Override
	public void removeListener(final ILabelProviderListener arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public Image getColumnImage(final Object arg0, final int arg1) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getColumnText(final Object arg0, final int arg1) {
		String result;
		if (arg0 instanceof NameDescriptionHolder) {
			if (arg1 == Column.Name.ordinal()) {
				result = ((NameDescriptionHolder) arg0).getName();
			} else if (arg1 == Column.Description.ordinal()) {
				result = ((NameDescriptionHolder) arg0).getDescription();
			} else {
				result = null;
			}
		} else {
			result = null;
		}
		return result;
	}

}
