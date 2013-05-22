package com.touchableheroes.android.db;

import android.content.ContentValues;

import com.touchableheroes.android.db.fields.Column;

public class ContentValuesFactory<T extends Column> {

	private final ContentValues values = new ContentValues();
	private Class<T> columns;
	
	public ContentValuesFactory(final T column, final String value ) {
		this(column);
		set(column, value);
	}
	
	public ContentValuesFactory(final T column, final int value ) {
		this(column);
		set(column, value);
	}

	@SuppressWarnings("unchecked")
	private ContentValuesFactory(final T column) {
		this.columns = (Class<T>) column.getClass();
	}

	public ContentValuesFactory<T> set(final T column,
			final String value) {
		this.values.put( column.fieldName(), value );
		return this;
	}
	
	public ContentValuesFactory<T> set(final T column,
			final int value) {
		this.values.put( column.fieldName(), value );
		return this;
	}
	
	public ContentValuesFactory<T> set(final T column,
			final double value) {
		this.values.put( column.fieldName(), value );
		return this;
	}
	
	public ContentValues values() {
		return this.values;
	}
	
	public Class<T> columns() {
		return this.columns;
	}

	public String tableName() {
		return Columns.findTableName(this.columns);
	}

	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return false;
	}
}
