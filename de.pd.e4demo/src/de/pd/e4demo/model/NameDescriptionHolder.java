package de.pd.e4demo.model;

public interface NameDescriptionHolder {

	String PROP_NAME = "name";
	String PROP_DESCRIPTION = "description";

	void setName(String name);

	void setDescription(String description);

	String getName();

	String getDescription();
}
