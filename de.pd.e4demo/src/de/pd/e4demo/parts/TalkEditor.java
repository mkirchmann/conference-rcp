package de.pd.e4demo.parts;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.inject.Inject;

import org.eclipse.e4.ui.di.Focus;
import org.eclipse.e4.ui.di.Persist;
import org.eclipse.e4.ui.model.application.ui.MContext;
import org.eclipse.e4.ui.services.EContextService;
import org.eclipse.e4.ui.workbench.modeling.EPartService;
import org.eclipse.swt.widgets.Composite;

public class TalkEditor {

	public static final String ID = "de.pd.e4demo.partdescriptor.TalkEditor";

	@Inject
	EContextService contextService;

	@Inject
	EPartService partService;

	@Inject
	MContext context;

	@Inject
	public TalkEditor() {

	}

	@PostConstruct
	public void postConstruct(final Composite parent) {
		showInput();
	}

	@PreDestroy
	public void preDestroy() {

	}

	@Focus
	public void onFocus() {
		showInput();
	}

	private void showInput() {
		final Object object = context.getContext().get("input");

		System.out.println("in editor: " + object);
	}

	@Persist
	public void save() {

	}

}