package com.chinarewards.gwt.license.client.widget;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.i18n.client.NumberFormat;
import com.google.gwt.resources.client.ImageResource;
import com.google.gwt.resources.client.ImageResource.ImageOptions;
import com.google.gwt.user.cellview.client.SimplePager;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.view.client.HasRows;
import com.google.gwt.view.client.Range;

/**
 * 
 * @author yanxin
 * 
 */
public class DefaultPager extends SimplePager {
	/**
	 * A ClientBundle that provides images for this widget.
	 */
	public static interface Resources extends SimplePager.Resources {

		/**
		 * The image used to go to the first page.
		 */
		@ImageOptions(flipRtl = true)
		ImageResource simplePagerFirstPage();

		/**
		 * The disabled first page image.
		 */
		@ImageOptions(flipRtl = true)
		ImageResource simplePagerFirstPageDisabled();

		/**
		 * The image used to go to the last page.
		 */
		@ImageOptions(flipRtl = true)
		ImageResource simplePagerLastPage();

		/**
		 * The disabled last page image.
		 */
		@ImageOptions(flipRtl = true)
		ImageResource simplePagerLastPageDisabled();

		/**
		 * The image used to go to the next page.
		 */
		@ImageOptions(flipRtl = true)
		ImageResource simplePagerNextPage();

		/**
		 * The disabled next page image.
		 */
		@ImageOptions(flipRtl = true)
		ImageResource simplePagerNextPageDisabled();

		/**
		 * The image used to go to the previous page.
		 */
		@ImageOptions(flipRtl = true)
		ImageResource simplePagerPreviousPage();

		/**
		 * The disabled previous page image.
		 */
		@ImageOptions(flipRtl = true)
		ImageResource simplePagerPreviousPageDisabled();

		/**
		 * The styles used in this widget.
		 */
		@Source("DefaultPager.css")
		Style simplePagerStyle();
	}

	private static Resources DEFAULT_RESOURCES;
	private static int DEFAULT_FAST_FORWARD_ROWS = 1000;

	private HasRows display;

	public DefaultPager(TextLocation location) {
		super(location, getDefaultResources(), false,
				DEFAULT_FAST_FORWARD_ROWS, true);
	}

	private static Resources getDefaultResources() {
		if (DEFAULT_RESOURCES == null) {
			DEFAULT_RESOURCES = GWT.create(Resources.class);
		}
		return DEFAULT_RESOURCES;
	}

	/**
	 * Get the text to display in the pager that reflects the state of the
	 * pager.
	 * 
	 * @return the text
	 */
	@Override
	protected String createText() {
		// Default text is 1 based.
		final NumberFormat formatter = NumberFormat.getFormat("#,###");
		final HasRows display = getDisplay();
		Range range = display.getVisibleRange();
		int pageStart = range.getStart() + 1;
		final int pageSize = range.getLength();
		int dataSize = display.getRowCount();
		int endIndex = Math.min(dataSize, pageStart + pageSize - 1);
		endIndex = Math.max(pageStart, endIndex);
		boolean exact = display.isRowCountExact();

		// create listbox
		int currentPage = 0;
		int totalPage = 0;
		final ListBox pages = new ListBox();
		if (dataSize != 0) {
			currentPage = (pageStart - 1) / pageSize + 1;
			totalPage = (dataSize - 1) / pageSize + 1;

			for (int i = 1; i <= totalPage; i++) {
				pages.addItem(i + "", i + "");
			}
			pages.setSelectedIndex(currentPage - 1);
			pages.addChangeHandler(new ChangeHandler() {
				@Override
				public void onChange(ChangeEvent event) {
					String text = pages.getValue(pages.getSelectedIndex());
					int pageNum = (int) formatter.parse(text);
					int index = (pageNum - 1) * pageSize;
					display.setVisibleRange(index, pageSize);
				}
			});
		}

		// create label : total pages
		//getAdditionPanel().clear();
		//getAdditionPanel().add(new Label("共 " + totalPage + " 页记录,转到"));
		//getAdditionPanel().add(pages);
		//getAdditionPanel().add(new Label("页"));

		// return formatter.format(pageStart) + "-" + formatter.format(endIndex)
		// + (exact ? " of " : " of over ") + formatter.format(dataSize);
		return "当前第 " + currentPage + " 页,"+"共"+totalPage+"页,共"+dataSize+"条";
	}

	@Override
	public void setDisplay(HasRows display) {
		this.display = display;
		super.setDisplay(display);
	}

	/**
	 * Set the page start index.
	 * 
	 * @param index
	 *            the index
	 * @see #getPageStart()
	 */
	@Override
	public void setPageStart(int index) {
		if (display != null) {
			Range range = display.getVisibleRange();
			int pageSize = range.getLength();
			index = Math.max(0, index);

			if (index < display.getRowCount()) {
				display.setVisibleRange(index, pageSize);
			}
		}
	}
}
