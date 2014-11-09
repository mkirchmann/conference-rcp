package de.pd.e4demo.handlers;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;

import org.assertj.core.api.Assertions;
import org.eclipse.e4.ui.model.application.ui.basic.MPart;
import org.eclipse.e4.ui.workbench.modeling.EPartService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class SaveHandlerTest {
	private SaveHandler handler;
	
	@Mock
	private EPartService partService;

	@Before
	public void before() {
		handler = new SaveHandler();
	}
	
	@Test
	public void testSave() throws Exception {
		// execute
		handler.execute(partService);
		
		// verify
		Mockito.verify(partService).saveAll(false);
	}
	
	@Test
	public void testCanExecute_true() {
		MPart mPart  = Mockito.mock(MPart.class);
		Collection<MPart> value = Arrays.asList(mPart  );
		Mockito.when(partService.getDirtyParts()).thenReturn(value);
		// execute
		boolean result = handler.canExecute(partService);
		
		// verify
		Assertions.assertThat(result).isTrue();
		Mockito.verify(partService).getDirtyParts();
	}
	
	@Test
	public void testCanExecute_false1() {
		Collection<MPart> value = Collections.emptyList();
		Mockito.when(partService.getDirtyParts()).thenReturn(value);
		// execute
		boolean result = handler.canExecute(partService);
		
		// verify
		Assertions.assertThat(result).isFalse();
		Mockito.verify(partService).getDirtyParts();
	}
	
	@Test
	public void testCanExecute_false2() {
		// execute
		boolean result = handler.canExecute(null);
		
		// verify
		Assertions.assertThat(result).isFalse();
	}
}
