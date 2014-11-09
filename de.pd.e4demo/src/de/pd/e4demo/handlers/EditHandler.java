package de.pd.e4demo.handlers;

import javax.inject.Inject;

import org.eclipse.e4.core.di.annotations.CanExecute;
import org.eclipse.e4.core.di.annotations.Execute;
import org.eclipse.e4.ui.model.application.ui.basic.MPart;
import org.eclipse.e4.ui.workbench.modeling.EModelService;
import org.eclipse.e4.ui.workbench.modeling.EPartService;
import org.eclipse.e4.ui.workbench.modeling.EPartService.PartState;
import org.eclipse.e4.ui.workbench.modeling.ESelectionService;

import de.pd.e4demo.model.ConferenceClientModel;
import de.pd.e4demo.model.TalkClientModel;
import de.pd.e4demo.parts.ConferenceEditor;
import de.pd.e4demo.parts.TalkEditor;

public class EditHandler {

	@Inject
	EPartService partService;

	@Inject
	EModelService modelService;

	@Inject
	ESelectionService selectionService;

	@Execute
	public void execute() {
		final Object selection = selectionService.getSelection();
		System.out.println(selection);
		String id;
		if (selection instanceof ConferenceClientModel) {
			id = ConferenceEditor.ID;
		} else if (selection instanceof TalkClientModel) {
			id = TalkEditor.ID;
		} else {
			id = null;
		}
		if (id != null) {
			final MPart part = partService.showPart(id, PartState.CREATE);
			part.getContext().modify("input", selection);
			System.out.println("Set input.");
			System.out.println("part class " + part.getObject());
			partService.showPart(part, PartState.ACTIVATE);
		}
	}

	@CanExecute
	public boolean canExecute() {
		return selectionService.getSelection() != null;
	}

}