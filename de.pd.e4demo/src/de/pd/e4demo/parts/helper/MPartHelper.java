package de.pd.e4demo.parts.helper;

import org.eclipse.e4.ui.model.application.ui.basic.MPart;

public class MPartHelper {

	private static final String INPUT = "input";
	private final MPart part;

	public MPartHelper(final MPart part) {
		this.part = part;
	}

	public Object getInput() {
		final Object partInstance = part.getObject();
		Object result;
		if (partInstance instanceof PartWithInput) {
			result = ((PartWithInput) partInstance).getInput();
		} else {
			result = null;
		}
		return result;
	}

	public void setInput(final Object selection) {
		final Object partInstance = part.getObject();
		if (partInstance instanceof PartWithInput) {
			final Class inputClazz = ((PartWithInput) partInstance).getInputClass();
			if (inputClazz.isInstance(selection)) {
				((PartWithInput) partInstance).setInput(selection);
			}
		}
		if (part != null) {
			part.getContext().modify(INPUT, selection);
		}
	}
}
