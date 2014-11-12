package de.pd.e4demo.parts;

import java.util.Collection;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.inject.Inject;
import javax.inject.Named;

import org.eclipse.e4.core.di.annotations.Optional;
import org.eclipse.e4.ui.di.Focus;
import org.eclipse.e4.ui.di.UIEventTopic;
import org.eclipse.e4.ui.services.IServiceConstants;
import org.eclipse.e4.ui.workbench.modeling.ESelectionService;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;

import de.pd.e4demo.model.ConferenceClientModel;
import de.pd.e4demo.model.TalkClientModel;
import de.pd.e4demo.parts.helper.DefaultSelectionListener;
import de.pd.e4demo.parts.provider.ConferenceLabelProvider;
import de.pd.e4demo.parts.provider.ContentProvider;
import de.pd.e4demo.parts.ui.TalkListComposite;

public class TalkListPart {

	@Inject
	ESelectionService selectionService;
	private TalkListComposite talkListComposite;

	@Inject
	public TalkListPart() {

	}

	@PostConstruct
	public void postConstruct(final Composite parent) {
		talkListComposite = createComposite(parent);
		talkListComposite.setLabelProvider(new ConferenceLabelProvider());
		talkListComposite.setContentProvider(new ContentProvider());
		talkListComposite.addSelectionChangedListener(new DefaultSelectionListener(selectionService));
	}

	protected TalkListComposite createComposite(final Composite parent) {
		return new TalkListComposite(parent, SWT.NONE);
	}

	@Inject
	void setSelection(@Optional @Named(IServiceConstants.ACTIVE_SELECTION) final ConferenceClientModel conference) {
		Collection<TalkClientModel> result;
		if (conference != null) {
			result = conference.getChildren();
			if (talkListComposite != null) {
				talkListComposite.setInput(result);
			}
		}
	}

	@PreDestroy
	public void preDestroy() {
		talkListComposite = null;
	}

	@Focus
	public void onFocus() {
		talkListComposite.setFocus();
	}

	@Inject
	@Optional
	public void refresh(@UIEventTopic(TalkClientModel.TOPIC) final TalkClientModel talk) {
		talkListComposite.refresh(talk);
	}

}