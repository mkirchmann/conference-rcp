package de.pd.e4demo.parts;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.inject.Inject;

import org.eclipse.e4.ui.di.Focus;
import org.eclipse.e4.ui.di.Persist;
import org.eclipse.swt.widgets.Composite;

public class ConferenceEditor {
	public static final String ID = "de.pd.e4demo.partdescriptor.ConferenceEditor";

	@Inject
	public ConferenceEditor() {

	}

	@PostConstruct
	public void postConstruct(final Composite parent) {

	}

	@PreDestroy
	public void preDestroy() {

	}

	@Focus
	public void onFocus() {

	}

	@Persist
	public void save() {

	}

}