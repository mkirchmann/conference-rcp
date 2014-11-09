package de.pd.e4demo.model;

public class AbstractDelegatingModel<D> {
	private final D delegate;

	public D getDelegate() {
		return delegate;
	}

	public AbstractDelegatingModel(final D delegate) {
		this.delegate = delegate;
	}

}
