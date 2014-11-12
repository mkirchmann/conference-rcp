package de.pd.e4demo;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import de.pd.e4demo.handlers.EditHandlerTest;
import de.pd.e4demo.handlers.SaveHandlerTest;
import de.pd.e4demo.parts.TalkEditorTest;
import de.pd.e4demo.parts.helper.AllHelperTests;

@SuiteClasses({ SaveHandlerTest.class, EditHandlerTest.class,
// TestSave.class,
		AllHelperTests.class, TalkEditorTest.class, E4LifeCycleTest.class })
@RunWith(Suite.class)
public class AllTests {

}
