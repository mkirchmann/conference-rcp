package de.pd.e4demo;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import de.pd.e4demo.handlers.EditHandlerTest;
import de.pd.e4demo.handlers.SaveHandlerTest;
import de.pd.e4demo.model.ConferenceClientModelTest;
import de.pd.e4demo.model.TalkClientModelTest;
import de.pd.e4demo.parts.ConferenceEditorTest;
import de.pd.e4demo.parts.ConferenceListPartTest;
import de.pd.e4demo.parts.SpeakerListPartTest;
import de.pd.e4demo.parts.TalkEditorTest;
import de.pd.e4demo.parts.TalkListPartTest;
import de.pd.e4demo.parts.helper.AllHelperTests;

@SuiteClasses({
	TalkClientModelTest.class,
	ConferenceClientModelTest.class, // model
	// tests
	SaveHandlerTest.class,
	EditHandlerTest.class, // handler tests.
	// TestSave.class,
	AllHelperTests.class, // helper tests
	ConferenceEditorTest.class, ConferenceListPartTest.class, TalkEditorTest.class, TalkListPartTest.class,
	SpeakerListPartTest.class, // part
	// tests
	E4LifeCycleTest.class })
@RunWith(Suite.class)
public class AllTests {

}
