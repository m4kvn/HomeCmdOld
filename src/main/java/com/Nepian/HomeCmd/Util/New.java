package com.Nepian.HomeCmd.Util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class New {

	public static <T, V> Map<T, V> newMap() {
		return  new HashMap<T, V>();
	}

	public static <T> List<T> newList() {
		return new ArrayList<T>();
	}

	public static <T> Set<T> newSet() {
		return new HashSet<T>();
	}
}
