package com.chinarewards.license.util;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class HibernateMaxInHelper {

	/**
	 * Split the given list of criteria
	 * 
	 * @param <T>
	 * @param list
	 * @param size
	 * @return
	 */
	public static <T> Map<String/* key */, List<T>/* list of IDs */> split(
			List<T> list, int size) {

		Map<String, List<T>> map = new HashMap<String, List<T>>();

		if (size <= 0) {
			throw new IllegalArgumentException("split size:" + size
					+ " can't less 0");
		}

		if (list == null || list.size() < 1) {
			return map;
		}

		int va = list.size() / size;
		int va2 = list.size() % size;

		// generate key .
		String key = "";
		Random random = new Random(System.currentTimeMillis());

		for (int i = 1; i <= va; i++) {
			key = "key" + String.valueOf(random.nextInt(1000)) + "NO" + i;
			List<T> li = new LinkedList<T>();
			li = list.subList((i - 1) * size, i * size);
			map.put(key, li);
		}

		if (va2 > 0) {
			key = "key" + String.valueOf(random.nextInt(4)) + "NO" + (va + 1);
			List<T> li = new LinkedList<T>();
			li = list.subList(va * size, (va * size) + va2);
			map.put(key, li);
		}

		return map;
	}

}
