package de.pd.e4demo;

import org.eclipse.e4.core.contexts.IEclipseContext;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class E4LifeCycleTest {

	private E4LifeCycle lifeCycle;

	@Mock
	IEclipseContext context;

	@Before
	public void before() throws Exception {
		lifeCycle = new E4LifeCycle();
	}

	@Test
	public void testPostContextCreate() {
		lifeCycle.postContextCreate(context);
	}

	@Test
	public void testPreSave() {
		lifeCycle.preSave(context);
	}

	@Test
	public void testProcessAdditions() {
		lifeCycle.processAdditions(context);
	}

	@Test
	public void testProcessRemovals() {
		lifeCycle.processRemovals(context);
	}

}
