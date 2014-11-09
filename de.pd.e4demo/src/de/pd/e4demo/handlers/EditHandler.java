package de.pd.e4demo.handlers;

import java.util.List;

import javax.inject.Inject;

import org.eclipse.e4.core.di.annotations.CanExecute;
import org.eclipse.e4.core.di.annotations.Execute;
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
import de.pd.e4demo.parts.helper.PartWithInput;

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
	public void execute() {
		final Object selection = selectionService.getSelection();
		System.out.println(selection);
		String id;
		final MPart part;
		final MPartStack editorArea = (MPartStack) modelService.find(
				"de.pd.e4demo.partstack.editorarea", application);
		final List<MStackElement> children = editorArea.getChildren();
		final MPart partMatchingInput = findPartWithMatchingInput(selection,
				children);
		if (partMatchingInput == null) {
			if (selection instanceof ConferenceClientModel) {
				id = ConferenceEditor.ID;
			} else if (selection instanceof TalkClientModel) {
				id = TalkEditor.ID;
			} else {
				id = null;
			}
			if (id != null) {
				part = partService.showPart(id, PartState.CREATE);
				setInput(part, selection);
				partService.showPart(part, PartState.ACTIVATE);
				children.add(part);
				editorArea.setSelectedElement(part);
			}
		} else {
			editorArea.setSelectedElement(partMatchingInput);
		}
	}

	protected MPart findPartWithMatchingInput(final Object selection,
			final List<MStackElement> children) {
		MPart partMatchingInput = null;
		if (selection != null) {
			for (final MStackElement mStackElement : children) {
				if (mStackElement instanceof MPart) {
					final Object input = getInput((MPart) mStackElement);

					if (input != null && mStackElement.isVisible()
							&& input.equals(selection)) {
						partMatchingInput = (MPart) mStackElement;
					}
				}
			}
		}
		return partMatchingInput;
	}

	protected Object getInput(final MPart mStackElement) {
		final Object partInstance = mStackElement.getObject();
		Object result;
		if (partInstance instanceof PartWithInput) {
			result = ((PartWithInput) partInstance).getInput();
		} else {
			result = null;
		}
		return result;
	}

	protected void setInput(final MPart part, final Object selection) {
		final Object partInstance = part.getObject();
		if (partInstance instanceof PartWithInput) {
			final Class inputClazz = ((PartWithInput) partInstance)
					.getInputClass();
			if (inputClazz.isInstance(selection)) {
				((PartWithInput) partInstance).setInput(selection);
			}
		}
		part.getContext().modify("input", selection);
		System.out.println("Set input.");
		System.out.println("part class " + part.getObject());

	}

	@CanExecute
	public boolean canExecute() {
		return selectionService.getSelection() != null;
	}

}