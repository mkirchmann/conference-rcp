package de.pd.e4demo.parts.helper;

import org.eclipse.e4.ui.workbench.modeling.ESelectionService;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.SelectionChangedEvent;

public final class DefaultSelectionListener implements ISelectionChangedListener {
	ESelectionService selectionService;

	public DefaultSelectionListener(ESelectionService selectionService) {
		this.selectionService = selectionService;

	}

	@Override
	public void selectionChanged(SelectionChangedEvent event) {
		ISelection selection = event.getSelection();
		if (selection instanceof IStructuredSelection) {
			selectionService.setSelection(((IStructuredSelection) selection).getFirstElement());
		} else {
			selectionService.setSelection(selection);
		}
	}
}