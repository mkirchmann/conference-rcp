package de.pd.e4demo.parts.helper;

import java.util.List;

import org.eclipse.e4.ui.model.application.ui.basic.MPart;
import org.eclipse.e4.ui.model.application.ui.basic.MPartStack;
import org.eclipse.e4.ui.model.application.ui.basic.MStackElement;

public class MPartStackHelper {
	private final MPartStack partStack;

	public MPartStackHelper(final MPartStack partStack) {
		this.partStack = partStack;
	}

	public MPart findPartWithMatchingInput(final Object selection) {
		return findPartWithMatchingInput(selection, partStack.getChildren());
	}

	public MPart findPartWithMatchingInput(final Object selection,
			final List<MStackElement> children) {
		MPart partMatchingInput = null;
		if (selection != null) {
			for (final MStackElement mStackElement : children) {
				if (mStackElement instanceof MPart) {
					final Object input = new MPartHelper((MPart) mStackElement)
							.getInput();

					if (input != null && mStackElement.isVisible()
							&& input.equals(selection)) {
						partMatchingInput = (MPart) mStackElement;
					}
				}
			}
		}
		return partMatchingInput;
	}
}
