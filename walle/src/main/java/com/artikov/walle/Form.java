package com.artikov.walle;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

/**
 * Date: 12/11/2016
 * Time: 15:53
 *
 * @author Artur Artikov
 */
public class Form {
	private Map<Field, Object> mValues = new LinkedHashMap<>();

	public <T> void putValue(Field<T> field, T value) {
		mValues.put(field, value);
	}

	@SuppressWarnings("unchecked")
	public <T> T getValue(Field<T> field) {
		return (T) mValues.get(field);
	}

	public <T> boolean containsField(Field<T> field) {
		return mValues.containsKey(field);
	}

	public Set<Field> getFields() {
		return mValues.keySet();
	}
}