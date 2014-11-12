package de.pd.e4demo.model;

import java.util.List;

import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class ConferenceClientModelTest {
	private ConferenceClientModel conferenceClientModel;

	@Mock
	private List<TalkClientModel> talks;

	@Before
	public void before() {
		conferenceClientModel = new ConferenceClientModel(talks);
	}

	@Test
	public void test() {
		final TalkClientModel talkClientModel = Mockito.mock(TalkClientModel.class);
		conferenceClientModel.addChild(talkClientModel);
		Mockito.verify(talks).add(talkClientModel);
	}

	@Test
	public void testGetSetName() {
		final String name = "pippo";
		conferenceClientModel.setName(name);
		final String result = conferenceClientModel.getName();

		Assertions.assertThat(result).isSameAs(name);
	}

	@Test
	public void testGetSetDescription() {
		final String description = "pippo";
		conferenceClientModel.setDescription(description);
		final String result = conferenceClientModel.getDescription();

		Assertions.assertThat(result).isSameAs(description);

	}
}
