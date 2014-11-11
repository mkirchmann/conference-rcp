package de.pd.e4demo.parts;

import org.assertj.core.api.Assertions;
import org.eclipse.e4.core.services.events.IEventBroker;
import org.eclipse.e4.ui.model.application.ui.MDirtyable;
import org.eclipse.swt.widgets.Composite;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import de.pd.e4demo.model.TalkClientModel;
import de.pd.e4demo.parts.helper.PartWithInputHelper;
import de.pd.e4demo.parts.helper.ui.DataBindingFactory;
import de.pd.e4demo.parts.helper.ui.NameDescriptionComposite;

@RunWith(MockitoJUnitRunner.class)
public class TalkEditorTest {
	private TalkEditor talkEditor;

	@Mock
	private PartWithInputHelper<TalkClientModel> inputHelper;

	@Mock
	private TalkClientModel talkClientModel;

	@Mock
	private MDirtyable mDirtyAble;
	@Mock
	private IEventBroker eventBroker;

	@Mock
	private DataBindingFactory dataBindingFactory;

	@Mock
	NameDescriptionComposite nameDescriptionComposite;

	@Mock
	private Composite parent;

	@Before
	public void before() {
		Mockito.when(inputHelper.getInputClass()).thenReturn(TalkClientModel.class);

		talkEditor = new TalkEditor(inputHelper);
		talkEditor.dirty = mDirtyAble;
		talkEditor.eventBroker = eventBroker;
		talkEditor.dataBindingFactory = dataBindingFactory;
	}

	@Test
	public void runNoArgConstructor() {
		new TalkEditor();
	}

	@Test
	public void testPreDestroy() {

		talkEditor.preDestroy();
	}

	@Test
	public void testName() throws Exception {
		final TalkEditor spy = Mockito.spy(talkEditor);
		Mockito.doReturn(nameDescriptionComposite).when(spy).createComposite(parent);
		spy.postConstruct(parent);
		spy.setInput(talkClientModel);

		Mockito.verify(dataBindingFactory).createBinding(nameDescriptionComposite, talkClientModel);
		Mockito.verify(inputHelper).setInput(talkClientModel);
	}

	@Test
	public void testGetInput() {
		Mockito.when(inputHelper.getInput()).thenReturn(talkClientModel);
		final TalkClientModel result = talkEditor.getInput();

		Assertions.assertThat(result).isSameAs(talkClientModel);
	}

	@Test
	public void testSave() {

		Mockito.when(inputHelper.getInput()).thenReturn(talkClientModel);
		talkEditor.save();

		// verify
		Mockito.verify(mDirtyAble).setDirty(false);
		Mockito.verify(eventBroker).post(TalkClientModel.TOPIC, talkClientModel);
	}

	@Test
	public void testGetInputClass() {
		final Class<TalkClientModel> inputClass = talkEditor.getInputClass();

		Assertions.assertThat(inputClass).isEqualTo(TalkClientModel.class);
	}

	@Test
	public void testPropertyChange() {
		talkEditor.propertyChange(null);

		Mockito.verify(mDirtyAble).setDirty(true);
	}

	@Test
	public void testOnFocus() {
		final TalkEditor spy = Mockito.spy(talkEditor);
		Mockito.doReturn(nameDescriptionComposite).when(spy).createComposite(parent);
		spy.postConstruct(parent);
		spy.onFocus();

		Mockito.verify(nameDescriptionComposite).setFocus();
	}
}
