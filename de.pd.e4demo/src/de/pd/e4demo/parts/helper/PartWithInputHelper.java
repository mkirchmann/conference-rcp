package de.pd.e4demo.parts.helper;

public class PartWithInputHelper<T> implements PartWithInput<T> {
	private final Class<T> inputClass;

	private T input;

	public PartWithInputHelper(final Class<T> clazz) {
		this.inputClass = clazz;
	}

	public T getInput() {
		return input;
	}

	@Override
	public void setInput(final T input) {
		this.input = input;
	}

	@Override
	public Class<T> getInputClass() {
		return inputClass;
	}
}
