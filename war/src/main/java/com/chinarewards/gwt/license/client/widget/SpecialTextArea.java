package com.chinarewards.gwt.license.client.widget;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.KeyUpEvent;
import com.google.gwt.event.dom.client.KeyUpHandler;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.view.client.ProvidesKey;

public abstract class SpecialTextArea<T> extends Composite {

	private ProvidesKey<T> keyProvider;

	Map<Object/*key*/, T> itemsSelected = new HashMap<Object, T>();
	private final BulletList list;

	public SpecialTextArea() {
		list = new BulletList();

		FlowPanel panel = new FlowPanel();
		initWidget(panel);

		list.setStyleName("token-input-list-facebook");
		panel.add(list);
		panel.getElement().setAttribute("onclick",
				"document.getElementById('suggestion_box').focus()");
		panel.getElement().setAttribute("style", "float:left;");

		final TextBox itemBox = new TextBox();
		itemBox.getElement().setId("suggestion_box");
		itemBox.getElement()
				.setAttribute(
						"style",
						"outline-color: -moz-use-text-color; outline-style: none; outline-width: medium;");
		final ListItem item = new ListItem();
		item.setStyleName("token-input-input-token-facebook");
		item.add(itemBox);
		list.add(item);
		// // this needs to be on the itemBox rather than box, or backspace will
		// // get executed twice
		// itemBox.addKeyDownHandler(new KeyDownHandler() {
		// public void onKeyDown(KeyDownEvent event) {
		// // handle backspace
		// if (event.getNativeKeyCode() == KeyCodes.KEY_BACKSPACE) {
		// if ("".equals(itemBox.getValue().trim())) {
		// ListItem li = (ListItem) list.getWidget(list
		// .getWidgetCount() - 2);
		// Paragraph p = (Paragraph) li.getWidget(0);
		// if (itemsSelected.contains(p.getText())) {
		// itemsSelected.remove(p.getText());
		// GWT.log("Removing selected item '" + p.getText()
		// + "'", null);
		// GWT.log("Remaining: " + itemsSelected, null);
		// }
		// list.remove(li);
		// itemBox.setFocus(true);
		// }
		// }
		// }
		// });
		itemBox.addKeyUpHandler(new KeyUpHandler() {
			public void onKeyUp(KeyUpEvent event) {
				// handle backspace
				itemBox.setValue("");
			}
		});
	}

	public void addItem(final T t) {
		if (t != null && !itemsSelected.containsKey(getKey(t))) {
			/**
			 * Change to the following structure: <li class="token-input-token-facebook">
			 * <span class="token-input-delete-token-facebook">x</span></li>
			 */

			final ListItem displayItem = new ListItem();
			displayItem.setStyleName("token-input-token-facebook");

			StringBuffer sb = new StringBuffer();
			render(t, sb);
			Paragraph p = new Paragraph(sb.toString());

			displayItem.addClickHandler(new ClickHandler() {
				public void onClick(ClickEvent clickEvent) {
					displayItem
							.addStyleName("token-input-selected-token-facebook");
				}
			});

			/**
			 * TODO: Figure out how to select item and allow deleting with
			 * backspace key displayItem.addKeyDownHandler(new KeyDownHandler()
			 * { public void onKeyDown(KeyDownEvent event) { if
			 * (event.getNativeKeyCode() == KeyCodes.KEY_BACKSPACE) {
			 * removeListItem(displayItem, list); } } });
			 * displayItem.addBlurHandler(new BlurHandler() { public void
			 * onBlur(BlurEvent blurEvent) { displayItem.removeStyleName(
			 * "token-input-selected-token-facebook"); } });
			 */

			Span span = new Span("x");
			span.addClickHandler(new ClickHandler() {
				public void onClick(ClickEvent clickEvent) {
					removeListItem(displayItem, list);
					itemsSelected.remove(getKey(t));
				}
			});

			displayItem.add(p);
			displayItem.add(span);
			// hold the original value of the item selected

			GWT.log("Adding selected item '" + sb + "'", null);
			itemsSelected.put(getKey(t), t);
			GWT.log("Total: " + itemsSelected, null);

			list.insert(displayItem, list.getWidgetCount() - 1);
		}
	}

	private void removeListItem(ListItem displayItem, BulletList list) {
		GWT.log("Removing: "
				+ displayItem.getWidget(0).getElement().getInnerHTML(), null);
		list.remove(displayItem);
	}

	public void clear() {
		while (list.getWidgetCount() - 2 >= 0) {
			ListItem li = (ListItem) list.getWidget(list.getWidgetCount() - 2);
			list.remove(li);
		}
		itemsSelected.clear();
	}

	/**
	 * Returns selected list in no particular order.
	 * 
	 * @return
	 */
	public List<T> getItemList() {
		return new ArrayList<T>(itemsSelected.values());
	}

	/**
	 * Check whether an item had been selected.
	 */
	public boolean containsItem(T item) {
		return itemsSelected.containsKey(getKey(item));
	}

	protected void setKeyProvider(ProvidesKey<T> keyProvider) {
		this.keyProvider = keyProvider;
	}
	
	protected abstract void render(T value, StringBuffer sb);

	private Object getKey(T item) {
		return keyProvider == null ? item : keyProvider.getKey(item);
	}
}
