/*******************************************************************************
 * Copyright (c) 2010 - 2013 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *     Lars Vogel <lars.Vogel@gmail.com> - Bug 419770
 *******************************************************************************/
package de.pd.e4demo.parts;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

import org.eclipse.e4.core.di.annotations.Optional;
import org.eclipse.e4.ui.di.Focus;
import org.eclipse.e4.ui.di.Persist;
import org.eclipse.e4.ui.di.UIEventTopic;
import org.eclipse.e4.ui.model.application.ui.MDirtyable;
import org.eclipse.e4.ui.workbench.modeling.ESelectionService;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.widgets.Composite;

import de.pd.e4demo.model.ConferenceClientModel;
import de.pd.e4demo.model.TalkClientModel;
import de.pd.e4demo.parts.helper.DefaultSelectionListener;
import de.pd.e4demo.parts.provider.ConferenceLabelProvider;
import de.pd.e4demo.parts.provider.TreeModelContentProvider;

public class ConferenceListPart {

	@Inject
	private MDirtyable dirty;

	@Inject
	ESelectionService selectionService;
	private ConferenceListComposite composite;

	@PostConstruct
	public void createComposite(final Composite parent) {
		// parent.setLayout(new GridLayout(1, false));

		composite = new ConferenceListComposite(parent, SWT.NONE);

		composite.addModifyListener(new ModifyListener() {
			@Override
			public void modifyText(final ModifyEvent e) {
				dirty.setDirty(true);
			}
		});
		composite.setContentProvider(new TreeModelContentProvider());
		composite.setLabelProvider(new ConferenceLabelProvider());
		composite.setInput(createConference());
		composite.addSelectionChangedListener(new DefaultSelectionListener(
				selectionService));
	}

	private Object createConference() {
		final List<TalkClientModel> list1 = new ArrayList<TalkClientModel>();
		final ConferenceClientModel conference1 = new ConferenceClientModel(
				"Super Conference", list1);
		conference1.addChild(new TalkClientModel("Sweet Talk 1", "Talk",
				conference1));
		conference1.addChild(new TalkClientModel("Sweet Talk 2", "Talk",
				conference1));
		conference1
				.addChild(new TalkClientModel("Talk 3", "Talk", conference1));

		final List<TalkClientModel> list2 = new ArrayList<TalkClientModel>();

		final ConferenceClientModel conference2 = new ConferenceClientModel(
				"Mega Conf 2014", list2);
		conference2.addChild(new TalkClientModel(
				"Stock Options and Futures Risks", "Risks of stock options",
				conference1));

		return new Object[] { conference1, conference2 };
	}

	@Focus
	public void setFocus() {
		composite.setFocus();
	}

	@Persist
	public void save() {
		dirty.setDirty(false);
	}

	@Inject
	@Optional
	public void refresh(
			@UIEventTopic(TalkClientModel.TOPIC) final TalkClientModel talk) {
		composite.refresh(talk);
	}
}