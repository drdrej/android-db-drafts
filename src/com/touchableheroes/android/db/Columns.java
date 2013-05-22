package com.touchableheroes.android.db;

import com.touchableheroes.android.db.fields.Column;
import com.touchableheroes.android.db.lang.Table;
import com.touchableheroes.android.db.log.Logger;


/**
 * @author Andreas Siebert, ask@touchableheroes.com
 * 
 * @param <T>
 */
public class Columns<T extends Column> {

	private final Class<T> columnDef;

	public Columns(final Class<T> columnDef) {
		this.columnDef = columnDef;
	}

	public String findTableName() {
		return findTableName(this.columnDef);
	}

	public static String findTableName(final Class<?> tableType) {
		final Table tableDef = tableType.getAnnotation(Table.class);
		if (tableDef == null) {
			Logger
					.debug("Doesn't found @Table-Annotation in [class = "
							+ tableType.getName() + "]");
			return String.valueOf(tableType.getSimpleName().toLowerCase());
		}

		return tableDef.value();
	}

	public String[] fieldNames() {
		return fieldNames(this.columnDef);
	}

	public static <T extends Column> String[] fieldNames(final Class<T> type) {
		final T[] columns = type.getEnumConstants();
		final String[] columnNames = new String[columns.length];

		for (int i = 0; i < columns.length; i++) {
			final T column = columns[i];
			columnNames[i] = column.fieldName();
		}

		return columnNames;
	}

	public String eq(final T column) {
		return column.fieldName() + " = ? ";
	}

}
