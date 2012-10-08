package com.chinarewards.gwt.license.client.widget;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.i18n.client.NumberFormat;
import com.google.gwt.resources.client.ClientBundle;
import com.google.gwt.resources.client.CssResource;
import com.google.gwt.uibinder.client.UiConstructor;
import com.google.gwt.user.cellview.client.AbstractPager;
import com.google.gwt.user.cellview.client.SimplePager;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HasVerticalAlignment;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.view.client.HasRows;
import com.google.gwt.view.client.Range;

public class EltNewPager extends AbstractPager {

	/**
	 * A ClientBundle that provides images for this widget.
	 */
	public static interface Resources extends ClientBundle {

		// @Source("EltNewPager.css")
		// Style simplePagerStyle();
	}

	/**
	 * Styles used by this widget.
	 */
	public static interface Style extends CssResource {

		/**
		 * Applied to buttons.
		 */
		String button();

		/**
		 * Applied to disabled buttons.
		 */
		String disabledButton();

		/**
		 * Applied to the details text.
		 */
		String pageDetails();
	}

	/**
	 * The location of the text relative to the paging buttons.
	 */
	public static enum TextLocation {
		CENTER, LEFT, RIGHT;
	}

	private static int DEFAULT_FAST_FORWARD_ROWS = 1000;
	private static Resources DEFAULT_RESOURCES;

	private static Resources getDefaultResources() {
		if (DEFAULT_RESOURCES == null) {
			DEFAULT_RESOURCES = GWT.create(Resources.class);
		}
		return DEFAULT_RESOURCES;
	}

	private final Anchor fastForward;

	private final int fastForwardRows;

	private final Anchor firstPage;

	/**
	 * We use an {@link HTML} so we can embed the loading image.
	 */
	private final HTML label = new HTML();

	private final Anchor lastPage;
	private final Anchor nextPage;
	private final Anchor prevPage;
	private final Anchor onePage;
	private final Anchor twoPage;
	private final Anchor threePage;
	private final Anchor fourPage;
	private final Anchor fivePage;
	private HasRows display;

	// /**
	// * The {@link Resources} used by this widget.
	// */
	// private final Resources resources;

	//
	// /**
	// * The {@link Style} used by this widget.
	// */
	// private final Style style;

	/**
	 * Construct a {@link SimplePager} with the default text location.
	 */
	public EltNewPager() {
		this(TextLocation.CENTER);
	}

	/**
	 * Construct a {@link SimplePager} with the specified text location.
	 * 
	 * @param location
	 *            the location of the text relative to the buttons
	 */
	@UiConstructor
	// Hack for Google I/O demo
	public EltNewPager(TextLocation location) {
		this(location, getDefaultResources(), true, DEFAULT_FAST_FORWARD_ROWS,
				true);
	}

	public EltNewPager(TextLocation location, Resources resources,
			boolean showFastForwardButton, final int fastForwardRows,
			boolean showLastPageButton) {
		// this.resources = resources;
		this.fastForwardRows = fastForwardRows;
		// this.style = resources.simplePagerStyle();
		// this.style.ensureInjected();

		// Create the buttons.
		// String disabledStyle = style.disabledButton();
		firstPage = new Anchor("首页");
		firstPage.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				firstPage();
			}
		});
		nextPage = new Anchor("下一页");
		nextPage.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				nextPage();
			}
		});
		prevPage = new Anchor("上一页");
		prevPage.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				previousPage();
			}
		});
		if (showLastPageButton) {
			lastPage = new Anchor("末页");
			lastPage.addClickHandler(new ClickHandler() {
				public void onClick(ClickEvent event) {
					lastPage();
				}
			});
		} else {
			lastPage = null;
		}
		if (showFastForwardButton) {
			fastForward = new Anchor("what?");
			fastForward.addClickHandler(new ClickHandler() {
				public void onClick(ClickEvent event) {
					setPage(getPage() + getFastForwardPages());
				}
			});
		} else {
			fastForward = null;
		}
		onePage = new Anchor("1");
		twoPage = new Anchor("2");
		threePage = new Anchor("3");
		fourPage = new Anchor("4");
		fivePage = new Anchor("5");
		// 添加1-5翻页

		onePage.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				setPage(0);
				setButtonStyle(1, getPageCount());
			}
		});

		twoPage.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				setPage(1);
				setButtonStyle(2, getPageCount());
			}
		});

		threePage.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				setPage(2);
				setButtonStyle(3, getPageCount());
			}
		});

		fourPage.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				setPage(3);
				setButtonStyle(4, getPageCount());
			}
		});

		fivePage.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				setPage(4);
				setButtonStyle(5, getPageCount());
			}
		});

		// Construct the widget.
		HorizontalPanel layout = new HorizontalPanel();
		layout.setVerticalAlignment(HasVerticalAlignment.ALIGN_MIDDLE);
		initWidget(layout);
		if (location == TextLocation.RIGHT) {
			layout.add(label);
		}
		layout.add(prevPage);
		layout.add(firstPage);

		if (location == TextLocation.CENTER) {
			layout.add(label);
		}
		layout.add(onePage);
		layout.add(twoPage);
		layout.add(threePage);
		layout.add(fourPage);
		layout.add(fivePage);

		// if (showFastForwardButton) {
		// layout.add(fastForward);
		// }

		if (showLastPageButton) {
			layout.add(lastPage);
		}
		layout.add(nextPage);

		if (location == TextLocation.LEFT) {
			layout.add(label);
		}

		// // Add style names to the cells.
		// firstPage.getElement().getParentElement().addClassName(style.button());
		// prevPage.getElement().getParentElement().addClassName(style.button());
		// label.getElement().getParentElement().addClassName(style.pageDetails());
		// nextPage.getElement().getParentElement().addClassName(style.button());
		// if (showFastForwardButton) {
		// fastForward.getElement().getParentElement().addClassName(style.button());
		// }
		// if (showLastPageButton) {
		// lastPage.getElement().getParentElement().addClassName(style.button());
		// }

		// Disable the buttons by default.
		setDisplay(null);
	}

	@Override
	public void firstPage() {
		super.firstPage();
	}

	@Override
	public int getPage() {
		return super.getPage();
	}

	@Override
	public int getPageCount() {
		return super.getPageCount();
	}

	@Override
	public boolean hasNextPage() {
		return super.hasNextPage();
	}

	@Override
	public boolean hasNextPages(int pages) {
		return super.hasNextPages(pages);
	}

	@Override
	public boolean hasPage(int index) {
		return super.hasPage(index);
	}

	@Override
	public boolean hasPreviousPage() {
		return super.hasPreviousPage();
	}

	@Override
	public boolean hasPreviousPages(int pages) {
		return super.hasPreviousPages(pages);
	}

	@Override
	public void lastPage() {
		super.lastPage();
	}

	@Override
	public void lastPageStart() {
		super.lastPageStart();
	}

	@Override
	public void nextPage() {
		super.nextPage();
	}

	@Override
	public void previousPage() {
		super.previousPage();
	}

	// @Override
	// public void setDisplay(HasRows display) {
	// // Enable or disable all buttons.
	// boolean disableButtons = (display == null);
	// setFastForwardDisabled(disableButtons);
	// setNextPageButtonsDisabled(disableButtons);
	// setPrevPageButtonsDisabled(disableButtons);
	// super.setDisplay(display);
	// }
	@Override
	public void setDisplay(HasRows display) {
		this.display = display;
		super.setDisplay(display);
	}

	@Override
	public void setPage(int index) {
		super.setPage(index);
	}

	@Override
	public void setPageSize(int pageSize) {
		super.setPageSize(pageSize);
	}

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

	/**
	 * Let the page know that the table is loading. Call this method to clear
	 * all data from the table and hide the current range when new data is being
	 * loaded into the table.
	 */
	public void startLoading() {
		getDisplay().setRowCount(0, true);
		label.setHTML("");
	}

	/**
	 * Get the text to display in the pager that reflects the state of the
	 * pager.
	 * 
	 * @return the text
	 */
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
		// boolean exact = display.isRowCountExact();

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

		setButtonStyle(currentPage, totalPage);
		return "";
		// return "当前第 " + currentPage + " 页,"+"共"+totalPage+"页,共"+dataSize+"条";
	}

	public void setButtonStyle(int currentPage, int totalPage) {
		onePage.setStyleName("pagedisable");
		twoPage.setStyleName("pagedisable");
		threePage.setStyleName("pagedisable");
		fourPage.setStyleName("pagedisable");
		fivePage.setStyleName("pagedisable");
		firstPage.setStyleName("pagedisable");
		nextPage.setStyleName("pagedisable");
		prevPage.setStyleName("pagedisable");
		lastPage.setStyleName("pagedisable");
		
		
		if (totalPage >= 1) {
			onePage.setStyleName("");
		}
		if (totalPage >= 2) {
			twoPage.setStyleName("");
			firstPage.setStyleName("");
			nextPage.setStyleName("");
			prevPage.setStyleName("");
			lastPage.setStyleName("");
		}
		if (totalPage >= 3) {
			threePage.setStyleName("");
		}
		if (totalPage >= 4) {
			fourPage.setStyleName("");
		}
		if (totalPage >= 5) {
			fivePage.setStyleName("");
		}

		if (currentPage == 1 && totalPage>=1) {
			onePage.setStyleName("pageon");
		}
		if (currentPage == 2 && totalPage>=2) {
			twoPage.setStyleName("pageon");
		}
		if (currentPage == 3 && totalPage>=3) {
			threePage.setStyleName("pageon");
		}
		if (currentPage == 4 && totalPage>=4) {
			fourPage.setStyleName("pageon");
		}
		if (currentPage == 5 && totalPage>=5) {
			fivePage.setStyleName("pageon");
		}

	}

	@Override
	protected void onRangeOrRowCountChanged() {
		HasRows display = getDisplay();
		label.setText(createText());

		// Update the prev and first buttons.
		setPrevPageButtonsDisabled(!hasPreviousPage());

		// Update the next and last buttons.
		if (isRangeLimited() || !display.isRowCountExact()) {
			setNextPageButtonsDisabled(!hasNextPage());
			setFastForwardDisabled(!hasNextPages(getFastForwardPages()));
		}
	}

	/**
	 * Check if the next button is disabled. Visible for testing.
	 */
	boolean isNextButtonDisabled() {
		// return nextPage.isDisabled();
		return true;
	}

	/**
	 * Check if the previous button is disabled. Visible for testing.
	 */
	boolean isPreviousButtonDisabled() {
		// return prevPage.isDisabled();
		return true;
	}

	/**
	 * Get the number of pages to fast forward based on the current page size.
	 * 
	 * @return the number of pages to fast forward
	 */
	private int getFastForwardPages() {
		int pageSize = getPageSize();
		return pageSize > 0 ? fastForwardRows / pageSize : 0;
	}

	/**
	 * Enable or disable the fast forward button.
	 * 
	 * @param disabled
	 *            true to disable, false to enable
	 */
	private void setFastForwardDisabled(boolean disabled) {
		if (fastForward == null) {
			return;
		}
		if (disabled) {
			// fastForward.setResource(resources.simplePagerFastForwardDisabled());
			// fastForward.getElement().getParentElement().addClassName(
			// style.disabledButton());
		} else {
			// fastForward.setResource(resources.simplePagerFastForward());
			// fastForward.getElement().getParentElement().removeClassName(
			// style.disabledButton());
		}
	}

	/**
	 * Enable or disable the next page buttons.
	 * 
	 * @param disabled
	 *            true to disable, false to enable
	 */
	private void setNextPageButtonsDisabled(boolean disabled) {
		nextPage.setEnabled(disabled);
		if (lastPage != null) {
			lastPage.setEnabled(disabled);
		}
	}

	/**
	 * Enable or disable the previous page buttons.
	 * 
	 * @param disabled
	 *            true to disable, false to enable
	 */
	private void setPrevPageButtonsDisabled(boolean disabled) {
		firstPage.setEnabled(disabled);
		prevPage.setEnabled(disabled);
	}
}
