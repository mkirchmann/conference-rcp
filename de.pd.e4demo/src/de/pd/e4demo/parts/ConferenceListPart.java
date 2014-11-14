package de.pd.e4demo.parts;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

import org.eclipse.e4.core.di.annotations.Optional;
import org.eclipse.e4.ui.di.Focus;
import org.eclipse.e4.ui.di.UIEventTopic;
import org.eclipse.e4.ui.workbench.modeling.ESelectionService;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;

import de.pd.e4demo.model.ConferenceClientModel;
import de.pd.e4demo.model.TalkClientModel;
import de.pd.e4demo.parts.helper.DefaultSelectionListener;
import de.pd.e4demo.parts.provider.ConferenceLabelProvider;
import de.pd.e4demo.parts.provider.TreeModelContentProvider;
import de.pd.e4demo.parts.ui.ConferenceListComposite;

public class ConferenceListPart {

	@Inject
	ESelectionService selectionService;
	private ConferenceListComposite composite;

	@PostConstruct
	public void createControl(final Composite parent) {
		composite = createComposite(parent);
		composite.setContentProvider(new TreeModelContentProvider());
		composite.setLabelProvider(new ConferenceLabelProvider());
		composite.setInput(createConference());
		composite.addSelectionChangedListener(new DefaultSelectionListener(selectionService));
	}

	protected ConferenceListComposite createComposite(final Composite parent) {
		return new ConferenceListComposite(parent, SWT.NONE);
	}

	protected Object createConference() {
		final List<TalkClientModel> list1 = new ArrayList<TalkClientModel>();
		final ConferenceClientModel conference1 = new ConferenceClientModel("Super Conference", list1);
		conference1.addChild(new TalkClientModel("Tech Talk 1", "Talk", conference1));
		conference1.addChild(new TalkClientModel("Tech Talk 2", "Talk", conference1));
		conference1.addChild(new TalkClientModel("Talk 3", "Talk", conference1));

		final List<TalkClientModel> list2 = new ArrayList<TalkClientModel>();

		final ConferenceClientModel conference2 = new ConferenceClientModel("Mega Conf 2014", list2);
		conference2.addChild(new TalkClientModel("Stock Options and Futures Risks", "Risks of stock options",
				conference1));

		return new Object[] { conference1, conference2 };
	}

	@Focus
	public void setFocus() {
		composite.setFocus();
	}

	@Inject
	@Optional
	public void refresh(@UIEventTopic(TalkClientModel.TOPIC) final TalkClientModel talk) {
		composite.refresh(talk);
	}
}