package de.pd.e4demo.model;

public class TalkClientModel implements NameDescriptionHolder {
	private String name;
	private String description;

	private final ConferenceClientModel conferenceClientModel;

	public TalkClientModel(final ConferenceClientModel conference) {
		this(null, null, conference);
	}

	public TalkClientModel(final String name, final String description,
			final ConferenceClientModel conference) {
		this.name = name;
		this.description = description;
		this.conferenceClientModel = conference;
	}

	@Override
	public String getName() {
		return name;
	}

	public void setName(final String name) {
		this.name = name;
	}

	@Override
	public String getDescription() {
		return description;
	}

	public void setDescription(final String description) {
		this.description = description;
	}

	public ConferenceClientModel getConferenceClientModel() {
		return conferenceClientModel;
	}
}
