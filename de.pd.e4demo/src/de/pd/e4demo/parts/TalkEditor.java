package de.pd.e4demo.parts;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.inject.Inject;

import org.eclipse.e4.ui.di.Focus;
import org.eclipse.e4.ui.di.Persist;
import org.eclipse.e4.ui.model.application.ui.MContext;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;

import de.pd.e4demo.model.TalkClientModel;
import de.pd.e4demo.parts.helper.DataBindingFactory;
import de.pd.e4demo.parts.helper.NameDescriptionComposite;
import de.pd.e4demo.parts.helper.PartWithInput;
import de.pd.e4demo.parts.helper.PartWithInputHelper;

public class TalkEditor implements PartWithInput<TalkClientModel> {

	final PartWithInputHelper<TalkClientModel> partWithInputHelper;

	public static final String ID = "de.pd.e4demo.partdescriptor.TalkEditor";

	@Inject
	private MContext context;

	@Inject
	private DataBindingFactory dataBindingFactory;

	private NameDescriptionComposite nameDescriptionComposite;

	@Inject
	public TalkEditor() {
		this(new PartWithInputHelper<>(TalkClientModel.class));
	}

	@Inject
	public TalkEditor(
			final PartWithInputHelper<TalkClientModel> partWithInputHelper) {
		this.partWithInputHelper = partWithInputHelper;
	}

	@PostConstruct
	public void postConstruct(final Composite parent) {
		showInput();

		nameDescriptionComposite = new NameDescriptionComposite(parent,
				SWT.NONE);
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

	@Override
	public Class<TalkClientModel> getInputClass() {
		return partWithInputHelper.getInputClass();
	}

	@Override
	public void setInput(final TalkClientModel t) {
		partWithInputHelper.setInput(t);
		dataBindingFactory.createBinding(nameDescriptionComposite, t);
		dataBindingFactory.refreshUi();
	}

	@Override
	public TalkClientModel getInput() {
		return partWithInputHelper.getInput();
	}

}