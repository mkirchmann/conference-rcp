package de.pd.e4demo.parts.helper;

public interface PartWithInput<T> {
	Class<T> getInputClass();

	void setInput(T t);

	T getInput();
}
