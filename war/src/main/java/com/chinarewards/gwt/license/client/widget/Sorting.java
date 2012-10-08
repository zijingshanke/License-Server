package com.chinarewards.gwt.license.client.widget;

import java.util.Comparator;

public interface Sorting<T> {
	public void sortingCurrentPage(Comparator<T> comparator);

	public void sortingAll(String sorting, String direction);
}
