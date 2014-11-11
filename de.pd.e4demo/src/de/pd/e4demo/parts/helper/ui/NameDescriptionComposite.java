package de.pd.e4demo.parts.helper.ui;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

import de.pd.e4demo.model.NameDescriptionHolder;

public class NameDescriptionComposite extends Composite {
	private final Text textName;
	private final Text textDescription;

	NameDescriptionHolder nameDescriptionHolder;

	/**
	 * Create the composite.
	 *
	 * @param parent
	 * @param style
	 */
	public NameDescriptionComposite(final Composite parent, final int style) {
		super(parent, style);
		setLayout(new GridLayout(2, false));

		final Label lblName = new Label(this, SWT.NONE);
		lblName.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblName.setText("Name");

		textName = new Text(this, SWT.BORDER);
		textName.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));

		final Label lblDescription = new Label(this, SWT.NONE);
		lblDescription.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblDescription.setText("Description");

		textDescription = new Text(this, SWT.BORDER);
		textDescription.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
	}

	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}

	public Text getTextName() {
		return textName;
	}

	public Text getTextDescription() {
		return textDescription;
	}

	@Override
	public boolean setFocus() {
		return textName.setFocus();
	}

}
