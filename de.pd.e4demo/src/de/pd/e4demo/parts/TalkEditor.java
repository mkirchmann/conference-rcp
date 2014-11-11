package de.pd.e4demo.parts;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.inject.Inject;

import org.eclipse.e4.core.services.events.IEventBroker;
import org.eclipse.e4.ui.di.Focus;
import org.eclipse.e4.ui.di.Persist;
import org.eclipse.e4.ui.model.application.ui.MDirtyable;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;

import de.pd.e4demo.model.TalkClientModel;
import de.pd.e4demo.parts.helper.PartWithInput;
import de.pd.e4demo.parts.helper.PartWithInputHelper;
import de.pd.e4demo.parts.helper.ui.DataBindingFactory;
import de.pd.e4demo.parts.helper.ui.NameDescriptionComposite;

public class TalkEditor implements PartWithInput<TalkClientModel>, PropertyChangeListener {

	final PartWithInputHelper<TalkClientModel> partWithInputHelper;

	public static final String ID = "de.pd.e4demo.partdescriptor.TalkEditor";

	@Inject
	DataBindingFactory dataBindingFactory;

	@Inject
	IEventBroker eventBroker;

	@Inject
	MDirtyable dirty;

	private NameDescriptionComposite nameDescriptionComposite;

	@Inject
	public TalkEditor() {
		this(new PartWithInputHelper<>(TalkClientModel.class));
	}

	@Inject
	public TalkEditor(final PartWithInputHelper<TalkClientModel> partWithInputHelper) {
		this.partWithInputHelper = partWithInputHelper;
	}

	@PostConstruct
	public void postConstruct(final Composite parent) {
		nameDescriptionComposite = createComposite(parent);
	}

	protected NameDescriptionComposite createComposite(final Composite parent) {
		return new NameDescriptionComposite(parent, SWT.NONE);
	}

	@PreDestroy
	public void preDestroy() {

	}

	@Focus
	public void onFocus() {
		nameDescriptionComposite.setFocus();
	}

	@Persist
	public void save() {
		eventBroker.post(TalkClientModel.TOPIC, partWithInputHelper.getInput());
		dirty.setDirty(false);

	}

	@Override
	public Class<TalkClientModel> getInputClass() {
		return partWithInputHelper.getInputClass();
	}

	@Override
	public void setInput(final TalkClientModel t) {
		partWithInputHelper.setInput(t);
		dataBindingFactory.createBinding(nameDescriptionComposite, t);
		// dataBindingFactory.addListener();

		t.addPropertyChangeListener(this);
	}

	@Override
	public TalkClientModel getInput() {
		return partWithInputHelper.getInput();
	}

	@Override
	public void propertyChange(final PropertyChangeEvent arg0) {
		dirty.setDirty(true);

	}

}