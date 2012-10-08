package com.chinarewards.gwt.license.client.dataprovider;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import com.chinarewards.gwt.license.model.SortingDetailClient;
import com.google.gwt.view.client.AsyncDataProvider;
import com.google.gwt.view.client.HasData;
import com.google.gwt.view.client.Range;

/**
 * Base data provider.
 * 
 * @author yanxin
 * @since 0.2.0 2011-1-15
 */
public abstract class BaseDataProvider<T> extends AsyncDataProvider<T> {

	private List<T> list = new ArrayList<T>();
	private HasData<T> display;
	private SortingDetailClient sorting;

	public void reloadToFirstPage() {
		Range r = new Range(0, display.getVisibleRange().getLength());
		display.setVisibleRangeAndClearData(r, true);
	}

	public void reloadCurrentPage() {
		Range r = new Range(display.getVisibleRange().getStart(), display
				.getVisibleRange().getLength());
		display.setVisibleRangeAndClearData(r, true);
	}

	public void sortCurrentPage(Comparator<T> comparator) {
		Collections.sort(getList(), comparator);
		display.setRowData(display.getVisibleRange().getStart(), getList());
	}

	public void sortFromDateBase(String sort, String direction) {
		sorting = new SortingDetailClient();
		sorting.setSort(sort);
		sorting.setDirection(direction);
		reloadCurrentPage();
	}

	@Override
	protected void onRangeChanged(HasData<T> view) {
		int start = view.getVisibleRange().getStart();
		int length = view.getVisibleRange().getLength();
		fetchData(start, length);
	}

	@Override
	public void addDataDisplay(final HasData<T> display) {
		this.display = display;
		super.addDataDisplay(display);
	}

	public abstract void fetchData(int start, int length);

	@Override
	// override the implementation of default!
	public void updateRowData(int start, List<T> list) {
		setList(list);
		// display.setRowData(start, list);
		super.updateRowData(start, list);
	}

	@Override
	// override the implementation of default!
	public void updateRowCount(int size, boolean exact) {
		// display.setRowCount(size, exact);
		super.updateRowCount(size, exact);
	}

	public List<T> getList() {
		return list;
	}

	private void setList(List<T> list) {
		this.list = list;
	}

	public SortingDetailClient getSorting() {
		return sorting;
	}

}
