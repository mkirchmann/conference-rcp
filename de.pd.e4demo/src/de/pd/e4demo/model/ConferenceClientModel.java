package de.pd.e4demo.model;

import java.util.List;

public class ConferenceClientModel extends
AbstractTreeModel<Object, Object, TalkClientModel> implements
		NameDescriptionHolder {
	private String name;
	private String description;

	private final List<TalkClientModel> talks;

	public ConferenceClientModel(final List<TalkClientModel> talks) {
		this(null, talks);
	}

	public ConferenceClientModel(final String name,
			final List<TalkClientModel> talks) {
		super((Object) null, (Object) null, talks);
		this.name = name;
		this.talks = talks;
	}

	@Override
	public String getName() {
		return name;
	}

	public List<TalkClientModel> getTalks() {
		return talks;
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

}
