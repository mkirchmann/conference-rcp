package de.pd.e4demo.parts.helper;

import org.eclipse.e4.core.contexts.IEclipseContext;
import org.eclipse.e4.ui.model.application.ui.basic.MPart;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class MPartHelperTest {
	private static final String INPUT = "input";

	private MPartHelper mPartHelper;

	@Mock
	private MPart part;

	@Mock
	PartWithInput<String> partWithInput;
	@Mock
	private IEclipseContext eclipseContext;

	@Before
	public void before() {
		mPartHelper = new MPartHelper(part);
		Mockito.when(part.getContext()).thenReturn(eclipseContext);
		Mockito.when(partWithInput.getInputClass()).thenReturn(String.class);
	}

	@Test
	public void testSetInput_AllFine() {
		Mockito.when(part.getObject()).thenReturn(partWithInput);

		final String selection = "blubb";

		// execute
		mPartHelper.setInput(selection);

		// verify
		partWithInput.setInput(selection);
		eclipseContext.modify(INPUT, selection);
	}

	@Test
	public void testSetInput_PartIsNotAPartWithInput() {
		final Object strangePartInstance = Mockito.mock(Object.class);
		Mockito.when(part.getObject()).thenReturn(strangePartInstance);

		final String selection = "blubb";

		// execute
		mPartHelper.setInput(selection);

		// verify
		Mockito.verifyNoMoreInteractions(strangePartInstance);
		eclipseContext.modify(INPUT, selection);
	}

	@Test
	public void testSetInput_NoAMatchingInput() {
		Mockito.when(part.getObject()).thenReturn(partWithInput);

		final Object selection = new Object();

		// execute
		mPartHelper.setInput(selection);

		// verify
		eclipseContext.modify(INPUT, selection);
	}

	@Test
	public void testGetInput() {

	}
}
