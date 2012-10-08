package com.chinarewards.gwt.license.client.core.ui.impl;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.chinarewards.gwt.license.client.core.Platform;
import com.chinarewards.gwt.license.client.core.presenter.DockPresenter;
import com.chinarewards.gwt.license.client.core.ui.Dialog;
import com.chinarewards.gwt.license.client.core.ui.DialogCloseListener;
import com.chinarewards.gwt.license.client.core.ui.Editor;
import com.chinarewards.gwt.license.client.core.ui.MenuProcessor;
import com.chinarewards.gwt.license.client.core.ui.SiteManager;
import com.chinarewards.gwt.license.client.core.ui.event.EditorCloseEvent;
import com.chinarewards.gwt.license.client.core.ui.event.EditorCloseHandler;
import com.chinarewards.gwt.license.client.core.ui.event.MenuClickEvent;
import com.chinarewards.gwt.license.client.mvp.EventBus;
import com.chinarewards.gwt.license.client.support.SessionManager;
import com.chinarewards.gwt.license.client.ui.DialogBox;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.user.client.History;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.LayoutPanel;
import com.google.gwt.user.client.ui.Panel;
import com.google.gwt.user.client.ui.RootLayoutPanel;
import com.google.gwt.user.client.ui.ScrollPanel;
import com.google.inject.Inject;

public class SimpleSiteManager implements SiteManager, EditorCloseHandler,
		ValueChangeHandler<String> {

	final EventBus eventBus;

	final SessionManager sessionManager;

	final DockPresenter dockPresenter;

	MenuProcessor menuProcessor;

	RootLayoutPanel root;

	Panel dock;

	Panel menu;

	Panel editor;

	List<Editor> openedEditors = new LinkedList<Editor>();

	Map<String, Dialog> openedDialogs = new HashMap<String, Dialog>();
	Map<String, DialogCloseListener> openedDialogHandlers = new HashMap<String, DialogCloseListener>();
	Map<String, DialogBox> openedDialogBoxes = new HashMap<String, DialogBox>();

	@Inject
	public SimpleSiteManager(EventBus eventBus, SessionManager sessionManager,
			DockPresenter dockPresenter) {
		this.eventBus = eventBus;
		this.sessionManager = sessionManager;
		this.dockPresenter = dockPresenter;
	}

	public void openEditor(final Editor e) {
		// store which tab is which instanceId
		openedEditors.add(e);

		HorizontalPanel panel = new HorizontalPanel();
		Label close = new Label("  ");
		close.setStyleName("close");
		close.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent paramClickEvent) {
				Platform.getInstance().getEditorRegistry()
						.closeEditor(e.getEditorId(), e.getInstanceId());
			}
		});
		Label title = new Label(e.getTitle());
		title.setStyleName("tabTitle");
		Label leftImg = new Label("");
		leftImg.setStyleName("leftImg");
		Label rightImg = new Label("");
		rightImg.setStyleName("rightImg");
		panel.add(leftImg);
		panel.add(title);
		panel.add(close);
		panel.add(rightImg);

		panel.setCellHorizontalAlignment(leftImg, HorizontalPanel.ALIGN_RIGHT);
		panel.setCellHorizontalAlignment(title, HorizontalPanel.ALIGN_RIGHT);
		panel.setCellHorizontalAlignment(close, HorizontalPanel.ALIGN_LEFT);
		panel.setCellHorizontalAlignment(rightImg, HorizontalPanel.ALIGN_RIGHT);

		ScrollPanel sp = new ScrollPanel(e.asWidget());
		editor.add(sp);
		// editor.add(e.asWidget(), g);

		// show editor panels only if there is at least one editor opened.
		if (!openedEditors.isEmpty()) {
			editor.setVisible(true);
		}
	}

	public void initialize(RootLayoutPanel rootPanel) {
		// XXX allocate different areas in root panel
		root = rootPanel;
		// dock = new DockLayoutPanel(Unit.PX);
		dock = dockPresenter.getDisplay().getDock();
		// dock = dock.addNorth(new HTML("<h1>(Header) 欢迎你："
		// + sessionManager.getSession().getLoginName() + "</h1>"), 100);
		// dock.addSouth(new HTML("<em>Footer</em>"), 50);
		// FIXME 20111130
		dockPresenter.getDisplay().setMessage(
				sessionManager.getSession().getLoginName());
		// menu = new LayoutPanel();
		menu = dockPresenter.getDisplay().getMenu();
		// dock.addWest(menu, 200);

		// initialize editors area.
		editor = new LayoutPanel();
		editor.setVisible(false);
		dock.add(editor);

		dockPresenter.bind();
		rootPanel.add(dockPresenter.getDisplay().asWidget());

		hookEditorCloseEvent();
		hookHistoryEvent();
		hookMenuClickEvent();
	}

	public void initializeStaff(RootLayoutPanel rootPanel) {
		dockPresenter.unbind();
		// XXX allocate different areas in root panel
		root = rootPanel;
		// dock = new DockLayoutPanel(Unit.PX);
		// dock = dock.addNorth(new HTML("<h1>(Header) 欢迎你："
		// + sessionManager.getSession().getLoginName() + "</h1>"), 100);
		// dock.addSouth(new HTML("<em>Footer</em>"), 50);
		// FIXME 20111130
		menu = new LayoutPanel();
		// menu = staffPresenter.getDisplay().getMenu();
		// dock.addWest(menu, 200);

		// initialize editors area.
		editor = new FlowPanel();
		editor.setVisible(false);
		dock.add(editor);

		hookEditorCloseEvent();
		hookHistoryEvent();
		hookMenuClickEvent();
	}



	public Panel getMenuArea() {
		return menu;
	}

	private void hookEditorCloseEvent() {
		eventBus.addHandler(EditorCloseEvent.getType(), this);
	}

	private void hookHistoryEvent() {
		// XXX should externalize History handling into separate class
		// See SimpleMenuExecutor
		History.addValueChangeHandler(this);
	}

	private void hookMenuClickEvent() {
		eventBus.addHandler(MenuClickEvent.getType(), new SimpleMenuExecutor(
				this));
	}

	public void onClose(String editorId, String instanceId) {
		// loop to find the editors
		for (int idx = 0; idx < openedEditors.size(); idx++) {
			Editor i = openedEditors.get(idx);
			if (instanceId != null && instanceId.equals(i.getInstanceId())) {
				openedEditors.remove(i);
				// editor.remove(idx);
				break;
			}
		}

		// hide editor panels only if there is no editors opened.
		if (openedEditors.isEmpty()) {
			editor.setVisible(false);
		}

	}

	public void focusEditor(Editor e) {
		// int idx = openedEditors.indexOf(e);
		editor.clear();
		ScrollPanel sp = new ScrollPanel(e.asWidget());
		if (editor instanceof FlowPanel)
			editor.add(e.asWidget());
		else
			editor.add(sp);
		// editor.forceLayout();
	}

	public void onValueChange(ValueChangeEvent<String> event) {
		String token = event.getValue();
		Window.alert("History: " + token);
		// if (token != null && token.startsWith("menu:")) {
		// if (menuProcessor != null) {
		// MenuItem i = menuProcessor.getMenuItem(token.substring(5));
		// if (i != null) {
		// i.execute();
		// }
		// }
		// } else if (token != null && token.startsWith("editor:")) {
		// Window.alert("TODO: Should open an editor: " + token);
		// // XXX how to get the model object to the editor?
		// }
	}

	public void setMenuProcessor(MenuProcessor menuProcessor) {
		this.menuProcessor = menuProcessor;
		// refresh the menu area
		menuProcessor.render(getMenuArea());
	}

	@Override
	public void openDialog(final Dialog dialog,
			final DialogCloseListener handler) {
		// If the instanceId existing, return.
		if (openedDialogs.containsKey(dialog.getInstanceId())) {
			return;
		}
		openedDialogs.put(dialog.getInstanceId(), dialog);
		openedDialogHandlers.put(dialog.getInstanceId(), handler);
		DialogBox dialogBox = new DialogBox();
		ScrollPanel panel = new ScrollPanel();
		panel.add(dialog.asWidget());
		dialogBox.setWidget(panel);
		dialogBox.setGlassEnabled(true);
		dialogBox.setAnimationEnabled(true);
		dialogBox.setText(dialog.getTitle());
		dialogBox.setDialog(dialog);
		// FIXME yanxin 20111130 dialogBox.setDialog(dialog);
		dialogBox.center();
		dialogBox.setPopupPosition(Window.getClientWidth() / 4,
				Window.getClientHeight() / 4);
		dialogBox.show();
		// Set max height for scroll panel. Deduction 30 PX for dialog title.
		// The window client should great than 30px, or it will not open dialog.
		int height = dialog.asWidget().getOffsetHeight();
		if (height > Window.getClientHeight()) {
			panel.setHeight(Window.getClientHeight() - 30 + "px");
		}
		openedDialogBoxes.put(dialog.getInstanceId(), dialogBox);
	}

	@Override
	public void closeDialog(final Dialog dialog) {
		Dialog d = openedDialogs.get(dialog.getInstanceId());
		if (d != null && d.beforeClose()) {
			openedDialogs.remove(dialog.getInstanceId());
			DialogCloseListener handler = openedDialogHandlers.remove(dialog
					.getInstanceId());
			if (handler != null) {
				handler.onClose(dialog.getDialogId(), dialog.getInstanceId());
			}

			// actually close the dialog
			DialogBox box = openedDialogBoxes.remove(dialog.getInstanceId());
			box.hide(true);
		}
	}

	@Override
	public void closeAllDialog() {
		Iterator<Entry<String, Dialog>> it = openedDialogs.entrySet()
				.iterator();
		while (it.hasNext()) {
			Entry<String, Dialog> entry = it.next();
			Dialog d = entry.getValue();
			if (d != null && d.beforeClose()) {
				it.remove();
				DialogCloseListener handler = openedDialogHandlers.remove(d
						.getInstanceId());
				if (handler != null) {
					handler.onClose(d.getDialogId(), d.getInstanceId());
				}

				// actually close the dialog
				DialogBox box = openedDialogBoxes.remove(d.getInstanceId());
				box.hide(true);
			}
		}
	}

}
