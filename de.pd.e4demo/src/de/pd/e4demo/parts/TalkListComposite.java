package de.pd.e4demo.parts;

import org.eclipse.jface.layout.TableColumnLayout;
import org.eclipse.jface.viewers.ColumnPixelData;
import org.eclipse.jface.viewers.IContentProvider;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TableViewerColumn;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;

import de.pd.e4demo.model.TalkClientModel;

public class TalkListComposite extends Composite {
	private final Table table;
	private final TableViewer tableViewer;

	public final void setInput(final Object input) {
		tableViewer.setInput(input);
	}

	/**
	 * Create the composite.
	 *
	 * @param parent
	 * @param style
	 */
	public TalkListComposite(final Composite parent, final int style) {
		super(parent, style);
		setLayout(new GridLayout(1, false));

		final Composite composite = new Composite(this, SWT.NONE);
		composite.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		final TableColumnLayout tcl_composite = new TableColumnLayout();
		composite.setLayout(tcl_composite);

		tableViewer = new TableViewer(composite, SWT.BORDER | SWT.FULL_SELECTION);
		table = tableViewer.getTable();
		table.setHeaderVisible(true);
		table.setLinesVisible(true);

		final TableViewerColumn tableViewerColumn = new TableViewerColumn(tableViewer, SWT.NONE);
		final TableColumn tblclmnTitle = tableViewerColumn.getColumn();
		tcl_composite.setColumnData(tblclmnTitle, new ColumnPixelData(150, true, true));
		tblclmnTitle.setText("Title");

		final TableViewerColumn tableViewerColumn_1 = new TableViewerColumn(tableViewer, SWT.NONE);
		final TableColumn tblclmnDescription = tableViewerColumn_1.getColumn();
		tcl_composite.setColumnData(tblclmnDescription, new ColumnPixelData(150, true, true));
		tblclmnDescription.setText("Description");

	}

	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}

	public void setLabelProvider(final ITableLabelProvider labelProvider) {
		tableViewer.setLabelProvider(labelProvider);
	}

	public void addSelectionChangedListener(final ISelectionChangedListener listener) {
		tableViewer.addSelectionChangedListener(listener);
	}

	public void setContentProvider(final IContentProvider provider) {
		tableViewer.setContentProvider(provider);
	}

	public TableViewer getTableViewer() {
		return tableViewer;
	}

	public void refresh(final TalkClientModel talk) {
		tableViewer.refresh(talk);
	}
}
