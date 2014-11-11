package de.pd.e4demo.parts.helper;

import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class PartWithInputHelperTest {

	PartWithInputHelper<String> partWithInputHelper;

	@Before
	public void before() {
		partWithInputHelper = new PartWithInputHelper<>(String.class);
	}

	@Test
	public void testConstructorResult() {
		final Class<String> result = partWithInputHelper.getInputClass();

		Assertions.assertThat(result).isSameAs(String.class);
	}

	@Test
	public void testGetSetInput() {
		final String input = "blubb";
		partWithInputHelper.setInput(input);

		final String result = partWithInputHelper.getInput();

		Assertions.assertThat(result).isSameAs(input);
	}

}
