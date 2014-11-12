package de.pd.e4demo.parts;

import org.eclipse.swt.widgets.Composite;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class ConferenceEditorTest {

	private ConferenceEditor conferenceEditor;

	@Mock
	private Composite parent;

	@Before
	public void before() throws Exception {
		conferenceEditor = new ConferenceEditor();
	}

	@Test
	public void testPostConstruct() throws Exception {
		conferenceEditor.postConstruct(parent);
	}

	@Test
	public void testPreDestroy() throws Exception {
		conferenceEditor.preDestroy();
	}

	@Test
	public void testOnFocus() throws Exception {
		conferenceEditor.onFocus();
	}

	@Test
	public void testSave() throws Exception {
		conferenceEditor.save();
	}

}
