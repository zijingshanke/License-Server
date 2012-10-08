/*
 * Copyright 2010 Google Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package com.chinarewards.gwt.license.client.widget;

import com.google.gwt.cell.client.ClickableTextCell;
import com.google.gwt.core.client.GWT;
import com.google.gwt.resources.client.ClientBundle;
import com.google.gwt.resources.client.ImageResource;
import com.google.gwt.safehtml.shared.SafeHtml;
import com.google.gwt.safehtml.shared.SafeHtmlBuilder;
import com.google.gwt.safehtml.shared.SafeHtmlUtils;
import com.google.gwt.user.cellview.client.Header;
import com.google.gwt.user.client.ui.AbstractImagePrototype;

/**
 * A {@link Header} subclass that maintains sorting state and displays an icon
 * to indicate the sort direction.
 */
public class SortableHeader extends Header<String> {

	/**
	 * Image resources.
	 */
	public static interface Resources extends ClientBundle {

		ImageResource downArrow();

		ImageResource upArrow();
	}

	private static final Resources RESOURCES = GWT.create(Resources.class);
	private static final int IMAGE_WIDTH = 16;
	private static final String DOWN_ARROW = makeImage(RESOURCES.downArrow());
	private static final String UP_ARROW = makeImage(RESOURCES.upArrow());

	private static String makeImage(ImageResource resource) {
		AbstractImagePrototype proto = AbstractImagePrototype.create(resource);
		String html = proto.getHTML().replace("style='",
				"style='position:absolute;right:0px;top:0px;");
		return html;
	}

	private boolean reverseSort = false;
	private boolean sorted = false;
	private String text;

	public SortableHeader(String text) {
		super(new ClickableTextCell());
		this.text = text;
	}

	public boolean getReverseSort() {
		return reverseSort;
	}

	@Override
	public String getValue() {
		return text;
	}


	/* (non-Javadoc)
	 * @see com.google.gwt.user.cellview.client.Header#render(com.google.gwt.safehtml.shared.SafeHtmlBuilder)
	 */
	public void render(SafeHtmlBuilder sb) {
		if (sorted) {
			renderSortedHtml(IMAGE_WIDTH, reverseSort ? DOWN_ARROW
					: UP_ARROW, text, sb);
		} else {
			renderUnsortedHtml(IMAGE_WIDTH, text, sb);
		}
	}

	private void renderSortedHtml(int imageWidth, String arrow, String text, SafeHtmlBuilder sb) {
		
		sb.appendHtmlConstant("<div style=\"position:relative;cursor:hand;cursor:pointer;"
				+ "padding-right:" + imageWidth + "px;\">");
		sb.appendHtmlConstant(arrow);
		sb.appendHtmlConstant("<div>");
		SafeHtml safeValue = SafeHtmlUtils.fromString(text);
		sb.append(safeValue);
		sb.appendHtmlConstant("</div></div>");
		
	}

	private void renderUnsortedHtml(int imageWidth, String text, SafeHtmlBuilder sb) {
		
		sb.appendHtmlConstant("<div style=\"position:relative;cursor:hand;cursor:pointer;"
				+ "padding-right:" + imageWidth + "px;\">");
		sb.appendHtmlConstant("<div style=\"position:absolute;display:none;\"></div><div>");
		SafeHtml safeValue = SafeHtmlUtils.fromString(text);
		sb.append(safeValue).appendHtmlConstant("</div></div>");
		
	}

	public void setReverseSort(boolean reverseSort) {
		this.reverseSort = reverseSort;
	}

	public void setSorted(boolean sorted) {
		this.sorted = sorted;
	}

	public void toggleReverseSort() {
		this.reverseSort = !this.reverseSort;
	}
}
