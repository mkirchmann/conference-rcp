package de.pd.e4demo.parts.ui;

import org.eclipse.jface.layout.TreeColumnLayout;
import org.eclipse.jface.viewers.ColumnPixelData;
import org.eclipse.jface.viewers.IBaseLabelProvider;
import org.eclipse.jface.viewers.IContentProvider;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.jface.viewers.TreeViewerColumn;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.swt.widgets.TreeColumn;

import de.pd.e4demo.model.ConferenceClientModel;
import de.pd.e4demo.model.TalkClientModel;

public class ConferenceListComposite extends Composite {

	private final TreeViewer treeViewer;

	/**
	 * Create the composite.
	 *
	 * @param parent
	 * @param style
	 */
	public ConferenceListComposite(final Composite parent, final int style) {
		super(parent, style);
		setLayout(new GridLayout(1, false));

		final Composite composite = new Composite(this, SWT.NONE);
		composite.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		final TreeColumnLayout tcl_composite = new TreeColumnLayout();
		composite.setLayout(tcl_composite);

		treeViewer = new TreeViewer(composite, SWT.BORDER);
		final Tree tree = treeViewer.getTree();
		tree.setHeaderVisible(true);
		tree.setLinesVisible(true);

		final TreeViewerColumn treeViewerColumn = new TreeViewerColumn(treeViewer, SWT.NONE);
		final TreeColumn trclmnTitle = treeViewerColumn.getColumn();
		tcl_composite.setColumnData(trclmnTitle, new ColumnPixelData(150, true, true));
		trclmnTitle.setText("Title");

		final TreeViewerColumn treeViewerColumn_1 = new TreeViewerColumn(treeViewer, SWT.NONE);
		final TreeColumn trclmnDescription = treeViewerColumn_1.getColumn();
		tcl_composite.setColumnData(trclmnDescription, new ColumnPixelData(150, true, true));
		trclmnDescription.setText("Description");

	}

	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}

	public void addSelectionChangedListener(final ISelectionChangedListener listener) {
		treeViewer.addSelectionChangedListener(listener);
	}

	public void setContentProvider(final IContentProvider provider) {
		treeViewer.setContentProvider(provider);
	}

	public void setInput(final Object input) {
		treeViewer.setInput(input);
	}

	public void setLabelProvider(final IBaseLabelProvider labelProvider) {
		treeViewer.setLabelProvider(labelProvider);
	}

	public void refresh(final TalkClientModel talk) {
		treeViewer.refresh(talk);
	}

	public void refresh(final ConferenceClientModel conference) {
		treeViewer.refresh(conference);
	}
}
