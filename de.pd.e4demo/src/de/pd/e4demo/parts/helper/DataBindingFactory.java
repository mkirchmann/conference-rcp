package de.pd.e4demo.parts.helper;

import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.core.databinding.beans.PojoProperties;
import org.eclipse.core.databinding.observable.value.IObservableValue;
import org.eclipse.e4.core.di.annotations.Creatable;
import org.eclipse.jface.databinding.swt.ISWTObservableValue;
import org.eclipse.jface.databinding.swt.WidgetProperties;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.widgets.Text;

import de.pd.e4demo.model.NameDescriptionHolder;

@Creatable
public class DataBindingFactory implements ModifyListener {
	DataBindingContext bindingContext;

	public void createBinding(final NameDescriptionComposite composite,
			final NameDescriptionHolder model) {
		final DataBindingContext dataBindingContext = getDataBindingContext();
		final Text textName = composite.getTextName();
		final Text textDescription = composite.getTextDescription();

		textName.addModifyListener(this);
		textDescription.addModifyListener(this);
		//
		final ISWTObservableValue<String> observeTextTextNameObserveWidget = WidgetProperties
				.textText().observe(textName);
		final IObservableValue<String> nameNameDescriptionHolderObserveValue = PojoProperties
				.value(NameDescriptionHolder.class,
						NameDescriptionHolder.PROP_NAME, String.class).observe(
						model);
		dataBindingContext.bindValue(observeTextTextNameObserveWidget,
				nameNameDescriptionHolderObserveValue, null, null);
		//
		final ISWTObservableValue<String> observeTextTextDescriptionObserveWidget = WidgetProperties
				.textText().observe(textDescription);
		final IObservableValue<String> descriptionNameDescriptionHolderObserveValue = PojoProperties
				.value(NameDescriptionHolder.class,
						NameDescriptionHolder.PROP_DESCRIPTION, String.class)
						.observe(model);
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

	@Override
	public void modifyText(final ModifyEvent arg0) {
		getDataBindingContext().updateModels();
	}
}
