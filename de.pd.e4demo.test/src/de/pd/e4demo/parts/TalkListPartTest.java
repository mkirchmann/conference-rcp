package de.pd.e4demo.parts;

import java.util.Collection;
import java.util.List;

import org.eclipse.e4.ui.workbench.modeling.ESelectionService;
import org.eclipse.jface.viewers.IContentProvider;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.swt.widgets.Composite;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InOrder;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import de.pd.e4demo.model.ConferenceClientModel;
import de.pd.e4demo.model.TalkClientModel;
import de.pd.e4demo.parts.ui.TalkListComposite;

@RunWith(MockitoJUnitRunner.class)
public class TalkListPartTest {
	private TalkListPart talkListPart;
	@Mock
	private ESelectionService selectionService;
	@Mock
	private Composite parent;
	@Mock
	private TalkListComposite createComposite;

	@Before
	public void before() {
		talkListPart = new TalkListPart();
		talkListPart.selectionService = selectionService;
	}

	@Test
	public void testCreateControlFocusAndRefresh() {
		final TalkListPart spy = Mockito.spy(talkListPart);

		Mockito.doReturn(createComposite).when(spy).createComposite(parent);

		spy.postConstruct(parent);
		final InOrder inOrder = Mockito.inOrder(spy, createComposite);
		inOrder.verify(spy).createComposite(parent);
		inOrder.verify(createComposite).setLabelProvider(Mockito.any(ITableLabelProvider.class));
		inOrder.verify(createComposite).setContentProvider(Mockito.any(IContentProvider.class));
		inOrder.verify(createComposite).addSelectionChangedListener(Mockito.any(ISelectionChangedListener.class));

		spy.onFocus();
		Mockito.verify(createComposite).setFocus();

		final TalkClientModel talk = Mockito.mock(TalkClientModel.class);
		spy.refresh(talk);
		Mockito.verify(createComposite).refresh(talk);
	}

	@Test
	public void testSetSelection() {
		final TalkListPart spy = Mockito.spy(talkListPart);

		Mockito.doReturn(createComposite).when(spy).createComposite(parent);

		spy.postConstruct(parent);

		final ConferenceClientModel conference = Mockito.mock(ConferenceClientModel.class);
		final Collection<TalkClientModel> children = Mockito.mock(List.class);
		Mockito.when(conference.getChildren()).thenReturn(children);
		spy.setSelection(conference);

		Mockito.verify(createComposite).setInput(children);
	}

	@Test
	public void testPreDestroy() {
		talkListPart.preDestroy();
	}

}
