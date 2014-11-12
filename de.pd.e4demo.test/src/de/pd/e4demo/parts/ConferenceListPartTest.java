package de.pd.e4demo.parts;

import org.assertj.core.api.Assertions;
import org.eclipse.e4.ui.workbench.modeling.ESelectionService;
import org.eclipse.swt.widgets.Composite;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import de.pd.e4demo.model.TalkClientModel;
import de.pd.e4demo.parts.ui.ConferenceListComposite;

@RunWith(MockitoJUnitRunner.class)
public class ConferenceListPartTest {
	private ConferenceListPart conferenceListPart;

	@Mock
	ESelectionService selectionService;

	@Mock
	ConferenceListComposite conferenceListComposite;

	@Mock
	private Composite parent;

	@Before
	public void before() {
		conferenceListPart = new ConferenceListPart();
		conferenceListPart.selectionService = selectionService;
	}

	@Test
	public void testCreateControl_SetFocusAndRefresh() {
		final ConferenceListPart spy = Mockito.spy(conferenceListPart);

		final Object conferenceInput = new Object();
		Mockito.doReturn(conferenceInput).when(spy).createConference();
		Mockito.doReturn(conferenceListComposite).when(spy).createComposite(parent);

		spy.createControl(parent);
		Mockito.verify(conferenceListComposite).setInput(conferenceInput);

		spy.setFocus();
		Mockito.verify(conferenceListComposite).setFocus();

		final TalkClientModel talkClientModel = Mockito.mock(TalkClientModel.class);
		spy.refresh(talkClientModel);
		Mockito.verify(conferenceListComposite).refresh(talkClientModel);

	}

	@Test
	public void testCreateConference() {
		final Object result = conferenceListPart.createConference();

		Assertions.assertThat(result).isNotNull();
	}

}
