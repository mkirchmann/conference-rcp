package de.pd.e4demo.parts.provider;

import org.eclipse.jface.viewers.ColumnLabelProvider;

public class LabelProvider extends ColumnLabelProvider {
	@Override
	public String getText(Object element) {
		return element.toString();
	}
}
