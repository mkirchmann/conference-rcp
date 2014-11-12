package de.pd.e4demo.model;

import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class TalkClientModelTest {
	private TalkClientModel talkClientModel;

	@Mock
	private ConferenceClientModel conference;

	@Before
	public void before() {
		talkClientModel = new TalkClientModel(conference);
	}

	@Test
	public void testConstructor() {
		final ConferenceClientModel result = talkClientModel.getConferenceClientModel();
		Assertions.assertThat(result).isSameAs(result);
	}

	@Test
	public void testGetSetName() {
		final String name = "pippo";
		talkClientModel.setName(name);
		final String result = talkClientModel.getName();

		Assertions.assertThat(result).isSameAs(name);
	}

	@Test
	public void testGetSetDescription() {
		final String description = "pippo";
		talkClientModel.setDescription(description);
		final String result = talkClientModel.getDescription();

		Assertions.assertThat(result).isSameAs(description);

	}
}
