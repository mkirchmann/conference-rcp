package de.pd.e4demo.handlers;

import org.assertj.core.api.Assertions;
import org.eclipse.e4.ui.model.application.MApplication;
import org.eclipse.e4.ui.model.application.ui.MElementContainer;
import org.eclipse.e4.ui.model.application.ui.MUIElement;
import org.eclipse.e4.ui.model.application.ui.basic.MPart;
import org.eclipse.e4.ui.model.application.ui.basic.MPartStack;
import org.eclipse.e4.ui.workbench.modeling.EModelService;
import org.eclipse.e4.ui.workbench.modeling.EPartService;
import org.eclipse.e4.ui.workbench.modeling.ESelectionService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import de.pd.e4demo.model.ConferenceClientModel;
import de.pd.e4demo.model.TalkClientModel;
import de.pd.e4demo.parts.ConferenceEditor;
import de.pd.e4demo.parts.TalkEditor;
import de.pd.e4demo.parts.helper.MPartHelper;
import de.pd.e4demo.parts.helper.MPartStackHelper;

@RunWith(MockitoJUnitRunner.class)
public class EditHandlerTest {
	private EditHandler editHandler;

	@Mock
	EPartService partService;

	@Mock
	EModelService modelService;

	@Mock
	ESelectionService selectionService;

	@Mock
	MApplication application;

	@Mock
	private MPart activePart;

	@Mock
	private MPartStack editorArea;

	@Mock
	private MPartStackHelper partStackHelper;

	@Mock
	private MPartHelper partHelper;

	@Mock
	private MPart existingOrCreatedPart;

	@Before
	public void before() {
		editHandler = new EditHandler();
		editHandler.application = application;
		editHandler.modelService = modelService;
		editHandler.partService = partService;
		editHandler.selectionService = selectionService;
	}

	@Test
	public void testCanExecute() {
		Mockito.when(selectionService.getSelection()).thenReturn(new Object());

		final boolean result = editHandler.canExecute();

		Assertions.assertThat(result).isTrue();
	}

	@Test
	public void testCanExecute_NoSelection() {
		final boolean result = editHandler.canExecute();

		Assertions.assertThat(result).isFalse();
	}

	@Test
	public void testExecute_createNewPart() {
		final MElementContainer<MUIElement> elementContainer = Mockito.mock(MElementContainer.class);
		Mockito.when(activePart.getParent()).thenReturn(elementContainer);
		final String idBySelection = "blubbId1";
		final TalkClientModel talkClientModel = Mockito.mock(TalkClientModel.class);
		Mockito.when(selectionService.getSelection()).thenReturn(talkClientModel);
		Mockito.when(partService.createPart(idBySelection)).thenReturn(existingOrCreatedPart);
		final EditHandler spy = Mockito.spy(editHandler);
		Mockito.doReturn(partHelper).when(spy).createPartHelper(existingOrCreatedPart);
		Mockito.doReturn(partStackHelper).when(spy).createPartStackHelper(editorArea);
		Mockito.doReturn(editorArea).when(spy).findEditorArea();
		Mockito.doReturn(idBySelection).when(spy).getIdBySelection(talkClientModel);

		spy.execute(activePart);
	}

	@Test
	public void testExecute_useExistingPart() {
		final TalkClientModel talkClientModel = Mockito.mock(TalkClientModel.class);
		Mockito.when(partStackHelper.findPartWithMatchingInput(talkClientModel)).thenReturn(existingOrCreatedPart);
		final MElementContainer<MUIElement> elementContainer = Mockito.mock(MElementContainer.class);
		Mockito.when(activePart.getParent()).thenReturn(elementContainer);
		final String idBySelection = "blubbId1";
		Mockito.when(selectionService.getSelection()).thenReturn(talkClientModel);
		Mockito.when(partService.createPart(idBySelection)).thenReturn(existingOrCreatedPart);
		final EditHandler spy = Mockito.spy(editHandler);
		Mockito.doReturn(partHelper).when(spy).createPartHelper(existingOrCreatedPart);
		Mockito.doReturn(partStackHelper).when(spy).createPartStackHelper(editorArea);
		Mockito.doReturn(editorArea).when(spy).findEditorArea();
		Mockito.doReturn(idBySelection).when(spy).getIdBySelection(talkClientModel);

		spy.execute(activePart);
	}

	@Test
	public void testGetIdBySelection_WithTalkClientModel() {
		final TalkClientModel talkClientModel = Mockito.mock(TalkClientModel.class);

		final String idBySelection = editHandler.getIdBySelection(talkClientModel);

		Assertions.assertThat(idBySelection).isSameAs(TalkEditor.ID);
	}

	@Test
	public void testGetIdBySelection_WithConferenceClientModel() {
		final ConferenceClientModel conferenceClientModel = Mockito.mock(ConferenceClientModel.class);

		final String idBySelection = editHandler.getIdBySelection(conferenceClientModel);

		Assertions.assertThat(idBySelection).isSameAs(ConferenceEditor.ID);
	}

	@Test
	public void testGetIdBySelection_StrangeObject() {
		final String idBySelection = editHandler.getIdBySelection(new Object());

		Assertions.assertThat(idBySelection).isNull();
	}

	@Test
	public void testCreatePartHelper() {
		final MPartHelper result = editHandler.createPartHelper(activePart);
		Assertions.assertThat(result).isNotNull();
	}

	@Test
	public void testCreatePartStackHelper() {
		final MPartStackHelper result = editHandler.createPartStackHelper(editorArea);
		Assertions.assertThat(result).isNotNull();
	}

	@Test
	public void testFindEditorArea() {
		Mockito.when(modelService.find(EditHandler.ID_EDITORAREA, application)).thenReturn(editorArea);
		final MPartStack result = editHandler.findEditorArea();
		Assertions.assertThat(result).isSameAs(editorArea);

	}
}
