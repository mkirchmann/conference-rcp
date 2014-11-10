package de.pd.e4demo.model;

import java.beans.PropertyChangeListener;

public interface PropertyChangeEnabled {
	public void addPropertyChangeListener(final PropertyChangeListener arg0);

	public void removePropertyChangeListener(final PropertyChangeListener arg0);
}
