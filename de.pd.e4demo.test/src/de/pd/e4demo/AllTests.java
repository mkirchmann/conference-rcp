package de.pd.e4demo;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import de.pd.e4demo.handlers.SaveHandlerTest;
import de.pd.e4demo.integrationtests.TestSave;

@SuiteClasses({SaveHandlerTest.class, TestSave.class})
@RunWith(Suite.class)
public class AllTests {

}
