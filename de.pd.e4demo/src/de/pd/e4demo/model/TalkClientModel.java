package de.pd.e4demo.model;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class TalkClientModel implements NameDescriptionHolder, PropertyChangeEnabled {
	private String name;
	private String description;

	public static final String TOPIC = "TalkClientModel";

	private final ConferenceClientModel conferenceClientModel;

	private final CustomPropertyChangeSupport support = new CustomPropertyChangeSupport(this);

	public TalkClientModel(final ConferenceClientModel conference) {
		this(null, null, conference);
	}

	public TalkClientModel(final String name, final String description, final ConferenceClientModel conference) {
		this.name = name;
		this.description = description;
		this.conferenceClientModel = conference;
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public void setName(final String name) {
		final PropertyChangeEvent event = support.createEvent(PROP_NAME, this.name, name);
		this.name = name;
		support.firePropertyChange(event);
	}

	@Override
	public String getDescription() {
		return description;
	}

	@Override
	public void setDescription(final String description) {
		final PropertyChangeEvent event = support.createEvent(PROP_DESCRIPTION, this.description, description);
		this.description = description;
		support.firePropertyChange(event);
	}

	public ConferenceClientModel getConferenceClientModel() {
		return conferenceClientModel;
	}

	@Override
	public void addPropertyChangeListener(final PropertyChangeListener arg0) {
		support.addPropertyChangeListener(arg0);
	}

	@Override
	public void removePropertyChangeListener(final PropertyChangeListener arg0) {
		support.removePropertyChangeListener(arg0);
	}
}
