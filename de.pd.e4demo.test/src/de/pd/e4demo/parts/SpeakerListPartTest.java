package de.pd.e4demo.parts;

import org.eclipse.swt.widgets.Composite;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class SpeakerListPartTest {

	private SpeakerListPart speakerListPart;

	@Mock
	Composite parent;

	@Before
	public void setUp() throws Exception {
		speakerListPart = new SpeakerListPart();
	}

	@Test
	public void testPostConstruct() throws Exception {
		speakerListPart.postConstruct(parent);
	}

	@Test
	public void testPreDestroy() throws Exception {
		speakerListPart.preDestroy();
	}

	@Test
	public void testOnFocus() throws Exception {
		speakerListPart.onFocus();
	}

}
