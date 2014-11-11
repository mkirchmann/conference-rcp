package de.pd.e4demo.parts.helper;

import org.eclipse.e4.ui.workbench.modeling.ESelectionService;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class DefaultSelectionListenerTest {

	private DefaultSelectionListener defaultSelectionListener;

	@Mock
	private ESelectionService selectionService;

	@Mock
	private SelectionChangedEvent event;

	@Before
	public void before() {
		defaultSelectionListener = new DefaultSelectionListener(selectionService);
	}

	@Test
	public void testSelectionChanged_WithStructuredSelection() {
		final String value = "blubb";
		final IStructuredSelection structuredSelection = Mockito.mock(IStructuredSelection.class);
		Mockito.when(structuredSelection.getFirstElement()).thenReturn(value);
		Mockito.when(event.getSelection()).thenReturn(structuredSelection);

		// execute
		defaultSelectionListener.selectionChanged(event);

		// verify
		selectionService.setSelection(value);

	}

	@Test
	public void testSelectionChanged_WithOtherSelection() {
		final ISelection selection = Mockito.mock(ISelection.class);
		Mockito.when(event.getSelection()).thenReturn(selection);

		// execute
		defaultSelectionListener.selectionChanged(event);

		// verify
		selectionService.setSelection(selection);

	}
}
