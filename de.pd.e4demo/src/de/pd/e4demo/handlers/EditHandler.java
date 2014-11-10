package de.pd.e4demo.handlers;

import java.util.List;

import javax.inject.Inject;

import org.eclipse.e4.core.contexts.Active;
import org.eclipse.e4.core.di.annotations.CanExecute;
import org.eclipse.e4.core.di.annotations.Execute;
import org.eclipse.e4.core.di.annotations.Optional;
import org.eclipse.e4.ui.model.application.MApplication;
import org.eclipse.e4.ui.model.application.ui.basic.MPart;
import org.eclipse.e4.ui.model.application.ui.basic.MPartStack;
import org.eclipse.e4.ui.model.application.ui.basic.MStackElement;
import org.eclipse.e4.ui.workbench.modeling.EModelService;
import org.eclipse.e4.ui.workbench.modeling.EPartService;
import org.eclipse.e4.ui.workbench.modeling.EPartService.PartState;
import org.eclipse.e4.ui.workbench.modeling.ESelectionService;

import de.pd.e4demo.model.ConferenceClientModel;
import de.pd.e4demo.model.TalkClientModel;
import de.pd.e4demo.parts.ConferenceEditor;
import de.pd.e4demo.parts.TalkEditor;
import de.pd.e4demo.parts.helper.MPartHelper;
import de.pd.e4demo.parts.helper.MPartStackHelper;

public class EditHandler {

	@Inject
	EPartService partService;

	@Inject
	EModelService modelService;

	@Inject
	ESelectionService selectionService;

	@Inject
	MApplication application;

	@Execute
	public void execute(@Active @Optional final MPart activePart) {
		final Object selection = selectionService.getSelection();
		System.out.println(selection);
		String id;
		final MPart part;
		final MPartStack editorArea = (MPartStack) modelService.find(
				"de.pd.e4demo.partstack.editorarea", application);
		final MPartStackHelper partStackHelper = new MPartStackHelper(
				editorArea);
		final List<MStackElement> children = editorArea.getChildren();
		final MPart partMatchingInput = partStackHelper
				.findPartWithMatchingInput(selection);
		if (partMatchingInput == null) {
			if (selection instanceof ConferenceClientModel) {
				id = ConferenceEditor.ID;
			} else if (selection instanceof TalkClientModel) {
				id = TalkEditor.ID;
			} else {
				id = null;
			}
			if (id != null) {
				part = partService.createPart(id);
				partService.showPart(part, PartState.CREATE);
				new MPartHelper(part).setInput(selection);
				children.add(part);
				partService.showPart(part, PartState.ACTIVATE);
				editorArea.setSelectedElement(part);
				if (activePart != null) {
					activePart.getParent().setSelectedElement(activePart);
				}
			}
		} else {
			editorArea.setSelectedElement(partMatchingInput);
		}
	}

	@CanExecute
	public boolean canExecute() {
		return selectionService.getSelection() != null;
	}

}