package de.pd.e4demo.parts.helper;

import java.util.LinkedList;
import java.util.List;

import org.assertj.core.api.Assertions;
import org.eclipse.e4.ui.model.application.ui.basic.MPart;
import org.eclipse.e4.ui.model.application.ui.basic.MPartStack;
import org.eclipse.e4.ui.model.application.ui.basic.MStackElement;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class MPartStackHelperTest {
	private MPartStackHelper helper;

	@Mock
	private MPartStack partStack;

	@Before
	public void before() {
		helper = new MPartStackHelper(partStack);
	}

	@Test
	public void testFindPartWithMatchingInput_NullInput() throws Exception {
		final MPart part = helper.findPartWithMatchingInput(null);
		Assertions.assertThat(part).isSameAs(null);
	}

	@Test
	public void testFindPartWithMatchingInput() throws Exception {

		final Object obj = new Object();

		final List<MStackElement> list = new LinkedList<MStackElement>();
		final MPart part1a = Mockito.mock(MPart.class);
		final MPart part1b = Mockito.mock(MPart.class);
		final MPart part1c = Mockito.mock(MPart.class);
		final MPart part2a = Mockito.mock(MPart.class);
		final MPart part2b = Mockito.mock(MPart.class);
		final MPart part3a = Mockito.mock(MPart.class);
		final MPart part3b = Mockito.mock(MPart.class);

		list.add(Mockito.mock(MStackElement.class));
		list.add(part1a);
		list.add(part1b);
		list.add(part1c);
		list.add(part2a);
		list.add(part3a);
		list.add(part3b);

		Mockito.when(part1b.isVisible()).thenReturn(true);
		Mockito.when(part2b.isVisible()).thenReturn(true);
		Mockito.when(part3b.isVisible()).thenReturn(true);

		final Object partInstance1 = new Object();
		final PartWithInput partWithInput2 = Mockito.mock(PartWithInput.class);
		final PartWithInput partWithInput3 = Mockito.mock(PartWithInput.class);

		Mockito.when(partWithInput2.getInput()).thenReturn(new Object());
		Mockito.when(partWithInput3.getInput()).thenReturn(obj);

		Mockito.when(part1a.getObject()).thenReturn(partInstance1);
		Mockito.when(part1b.getObject()).thenReturn(partInstance1);
		Mockito.when(part2a.getObject()).thenReturn(partWithInput2);
		Mockito.when(part2b.getObject()).thenReturn(partWithInput2);
		Mockito.when(part3a.getObject()).thenReturn(partWithInput3);
		Mockito.when(part3b.getObject()).thenReturn(partWithInput3);

		Mockito.when(partStack.getChildren()).thenReturn(list);

		final MPart part = helper.findPartWithMatchingInput(obj);
		Assertions.assertThat(part).isSameAs(part3b);
	}
}
