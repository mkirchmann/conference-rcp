package de.pd.e4demo.parts.helper;

import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.core.databinding.beans.PojoProperties;
import org.eclipse.core.databinding.observable.value.IObservableValue;
import org.eclipse.e4.core.di.annotations.Creatable;
import org.eclipse.jface.databinding.swt.WidgetProperties;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Text;

import de.pd.e4demo.model.NameDescriptionHolder;

@Creatable
public class DataBindingFactory {
	DataBindingContext bindingContext;

	public void createBinding(final NameDescriptionComposite composite,
			final NameDescriptionHolder model) {
		final DataBindingContext dataBindingContext = getDataBindingContext();
		final Text textName = composite.getTextName();
		final Text textDescription = composite.getTextDescription();
		//
		final IObservableValue observeTextTextNameObserveWidget = WidgetProperties
				.textText(SWT.Modify).observe(textName);
		final IObservableValue nameNameDescriptionHolderObserveValue = PojoProperties
				.value("name").observe(model);
		dataBindingContext.bindValue(observeTextTextNameObserveWidget,
				nameNameDescriptionHolderObserveValue, null, null);
		//
		final IObservableValue observeTextTextDescriptionObserveWidget = WidgetProperties
				.textText(SWT.Modify).observe(textDescription);
		final IObservableValue descriptionNameDescriptionHolderObserveValue = PojoProperties
				.value("description").observe(model);
		dataBindingContext.bindValue(observeTextTextDescriptionObserveWidget,
				descriptionNameDescriptionHolderObserveValue, null, null);
		//
	}

	protected DataBindingContext getDataBindingContext() {
		if (bindingContext == null) {
			bindingContext = new DataBindingContext();
		}
		return bindingContext;
	}

	public void refreshUi() {
		getDataBindingContext().updateTargets();
	}
}
