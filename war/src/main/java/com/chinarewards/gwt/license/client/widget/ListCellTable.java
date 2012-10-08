package com.chinarewards.gwt.license.client.widget;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import com.chinarewards.gwt.license.util.StringUtil;
import com.google.gwt.cell.client.Cell;
import com.google.gwt.cell.client.FieldUpdater;
import com.google.gwt.cell.client.ValueUpdater;
import com.google.gwt.core.client.GWT;
import com.google.gwt.resources.client.ImageResource;
import com.google.gwt.resources.client.ImageResource.ImageOptions;
import com.google.gwt.resources.client.ImageResource.RepeatStyle;
import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.cellview.client.Column;
import com.google.gwt.user.cellview.client.TextHeader;

public class ListCellTable<T> extends CellTable<T> {

	private static final int DEFAULT_PAGESIZE = 15;
	private static Resources DEFAULT_RESOURCES;

	public ListCellTable() {
		super(DEFAULT_PAGESIZE, getDefaultResources());
	}

	public ListCellTable(int pageSize, Resources resources) {
		super(pageSize, resources);
		// TODO Auto-generated constructor stub
	}

	public ListCellTable(int pageSize) {
		super(pageSize, getDefaultResources());
	}

	/**
	 * A ClientBundle that provides images for this widget.
	 */
	public static interface Resources extends CellTable.Resources {

		/**
		 * The background used for header cells.
		 */
		@Source("trbg.jpg")
		@ImageOptions(repeatStyle = RepeatStyle.Horizontal)
		ImageResource cellTableHeaderBackground();

		/**
		 * The styles used in this widget.
		 */
		// @Source("listCellTable.css")
		// Style cellTableStyle();

	}

	private static Resources getDefaultResources() {
		if (DEFAULT_RESOURCES == null) {
			DEFAULT_RESOURCES = GWT.create(Resources.class);
		}
		return DEFAULT_RESOURCES;
	}

	/**
	 * @author yanxin
	 * @since 0.2.0 2011-01-15
	 */
	private final List<SortableHeader> allHeaders = new ArrayList<SortableHeader>();
	/**
	 * @author nicho
	 * @since 2012年2月6日 15:55:51
	 */
	private final List<TextHeader> allTextHeaders = new ArrayList<TextHeader>();
	
	
	/**
	 * Just show text , no need sorting.
	 * 
	 * @param <C>
	 * @param text
	 * @param cell
	 * @param getter
	 * @return
	 */
	public <C extends Comparable<C>> Column<T, C> addColumn(final String text,
			final Cell<C> cell, final GetValue<T, C> getter) {
		return addColumn(text, cell, getter, null, null, null, null, null);
	}

	/**
	 * Show text, need sorting.
	 * 
	 * @param <C>
	 * @param text
	 * @param cell
	 * @param getter
	 * @param ref
	 * @param sortingText
	 * @return
	 */
	public <C extends Comparable<C>> Column<T, C> addColumn(final String text,
			final Cell<C> cell, final GetValue<T, C> getter,
			final Sorting<T> ref, final String sortingText) {
		return addColumn(text, cell, getter, null,
				createColumnComparator(getter, false),
				createColumnComparator(getter, true), ref, sortingText);
	}

	/**
	 * Button or others, need event.
	 * 
	 * @param <C>
	 * @param text
	 * @param cell
	 * @param getter
	 * @param fieldUpdater
	 * @return
	 */
	public <C extends Comparable<C>> Column<T, C> addColumn(final String text,
			final Cell<C> cell, final GetValue<T, C> getter,
			final FieldUpdater<T, C> fieldUpdater) {
		return addColumn(text, cell, getter, fieldUpdater, null, null, null,
				null);
	}

	/**
	 * Button or other click text , need sorting.
	 * 
	 * @param <C>
	 * @param text
	 * @param cell
	 * @param getter
	 * @param fieldUpdater
	 * @param ref
	 * @param sortingText
	 * @return
	 */
	public <C extends Comparable<C>> Column<T, C> addColumn(final String text,
			final Cell<C> cell, final GetValue<T, C> getter,
			final FieldUpdater<T, C> fieldUpdater, final Sorting<T> ref,
			final String sortingText) {
		return addColumn(text, cell, getter, fieldUpdater,
				createColumnComparator(getter, false),
				createColumnComparator(getter, true), ref, sortingText);
	}

	private <C> Column<T, C> addColumn(final String text, final Cell<C> cell,
			final GetValue<T, C> getter, final FieldUpdater<T, C> fieldUpdater,
			final Comparator<T> ascComparator,
			final Comparator<T> descComparator, final Sorting<T> ref,
			final String sortingText) {
		// Create the column
		final Column<T, C> column = new Column<T, C>(cell) {
			@Override
			public C getValue(T object) {
				return getter.getValue(object);
			}
		};
		/**
		 * 这里分为可排序的Header和普通文本的Header
		 */
		if (ref != null) {
			final SortableHeader header = new SortableHeader(text);
			allHeaders.add(header);

			// Hook up sorting
			header.setUpdater(new ValueUpdater<String>() {

				@Override
				public void update(String value) {
					header.setSorted(true);
					header.toggleReverseSort();

					for (SortableHeader otherHeader : allHeaders) {
						if (otherHeader != header) {
							otherHeader.setSorted(false);
							otherHeader.setReverseSort(true);
						}
					}
					// table.refreshHeaders(); // TODO remove this when
					// confirmed working.
					ListCellTable.this.redrawHeaders();

					if (StringUtil.isEmpty(sortingText)) {
						ref.sortingCurrentPage(header.getReverseSort() ? descComparator
								: ascComparator);
					} else {
						ref.sortingAll(sortingText,
								header.getReverseSort() ? "desc" : "asc");
					}
				}
			});
			this.addColumn(column, header);
		} else {
			TextHeader header = new TextHeader(text);
			int fal=0;
			for (TextHeader otherHeader : allTextHeaders) {
				if (otherHeader.getValue() == header.getValue()) {
					header=otherHeader;
					fal=1;
				}
			}
			if(fal!=1)
			  allTextHeaders.add(header);
			this.addColumn(column, header);
		}
		if (fieldUpdater != null) {
			column.setFieldUpdater(fieldUpdater);
		}
		return column;
	}

	private <C extends Comparable<C>> Comparator<T> createColumnComparator(
			final GetValue<T, C> getter, final boolean descending) {
		return new Comparator<T>() {
			@Override
			public int compare(T o1, T o2) {
				if (o1 == null && o2 == null) {
					return 0;
				} else if (o1 == null) {
					return descending ? 1 : -1;
				} else if (o2 == null) {
					return descending ? -1 : 1;
				}

				// Compare the column value
				C c1 = getter.getValue(o1);
				C c2 = getter.getValue(o2);
				if (c1 == null && c2 == null) {
					return 0;
				} else if (c1 == null) {
					return descending ? 1 : -1;
				} else if (c2 == null) {
					return descending ? -1 : 1;
				}

				int comparison = c1.compareTo(c2);
				return descending ? -comparison : comparison;
			}
		};
	}

}
