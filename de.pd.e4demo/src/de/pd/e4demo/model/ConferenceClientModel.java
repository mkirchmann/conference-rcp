package de.pd.e4demo.model;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.List;

public class ConferenceClientModel extends
		AbstractTreeModel<Object, Object, TalkClientModel> implements
NameDescriptionHolder, PropertyChangeEnabled {
	private String name;
	private String description;

	private final List<TalkClientModel> talks;

	private final CustomPropertyChangeSupport support = new CustomPropertyChangeSupport(
			this);

	public ConferenceClientModel(final List<TalkClientModel> talks) {
		this(null, talks);
	}

	public ConferenceClientModel(final String name,
			final List<TalkClientModel> talks) {
		super((Object) null, (Object) null, talks);
		this.name = name;
		this.talks = talks;
	}

	public List<TalkClientModel> getTalks() {
		return talks;
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public void setName(final String name) {
		final PropertyChangeEvent event = support.createEvent(PROP_NAME,
				this.name, name);
		this.name = name;
		support.firePropertyChange(event);
	}

	@Override
	public String getDescription() {
		return description;
	}

	@Override
	public void setDescription(final String description) {
		final PropertyChangeEvent event = support.createEvent(PROP_DESCRIPTION,
				this.description, description);
		this.description = description;
		support.firePropertyChange(event);
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
