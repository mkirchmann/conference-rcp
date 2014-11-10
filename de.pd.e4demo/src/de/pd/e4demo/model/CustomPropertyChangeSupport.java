package de.pd.e4demo.model;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class CustomPropertyChangeSupport {
	private final PropertyChangeSupport support;

	private final Object source;

	public CustomPropertyChangeSupport(final Object source) {
		support = new PropertyChangeSupport(source);
		this.source = source;
	}

	public PropertyChangeEvent createEvent(final String key, final Object oldValue, final Object newValue) {
		return new PropertyChangeEvent(source, key, oldValue, newValue);
	}

	public void addPropertyChangeListener(final PropertyChangeListener arg0) {
		support.addPropertyChangeListener(arg0);
	}

	public void addPropertyChangeListener(final String arg0, final PropertyChangeListener arg1) {
		support.addPropertyChangeListener(arg0, arg1);
	}

	public void firePropertyChange(final PropertyChangeEvent arg0) {
		support.firePropertyChange(arg0);
	}

	public void firePropertyChange(final String arg0, final int arg1, final int arg2) {
		support.firePropertyChange(arg0, arg1, arg2);
	}

	public void removePropertyChangeListener(final PropertyChangeListener arg0) {
		support.removePropertyChangeListener(arg0);
	}

	public void removePropertyChangeListener(final String arg0, final PropertyChangeListener arg1) {
		support.removePropertyChangeListener(arg0, arg1);
	}

}
