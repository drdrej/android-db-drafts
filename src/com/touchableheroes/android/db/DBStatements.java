package com.touchableheroes.android.db;

import com.touchableheroes.android.db.fields.AutoIncrementable;
import com.touchableheroes.android.db.fields.Column;
import com.touchableheroes.android.db.fields.REAL;
import com.touchableheroes.android.db.fields.SQLiteType;

/**
 * Generieren der Queries aus dem Modell. Evtl. auch zur Laufzeit.
 * 
 * Grunds�tzlich sollte die "OFFLINE"-Generierung angestrebt werden.
 * 
 * @author asiebert
 */
public class DBStatements {

	
	public static <T> String selectValueById(final Class<T> type, final Column column, final Column idCol, final long id ) {
		return "SELECT " + column.fieldName() + " FROM " + Columns.findTableName( type ) + " WHERE " + idCol.fieldName() + " = " + id + ";";
	}
	
	public static <T> String count(final Class<T> type) {
		return "SELECT COUNT(*) FROM " + Columns.findTableName( type );
	}
	
	public static <T> String sum(final Class<T> type, final Column column) {
		final String field;
		if( column.getSQLType().is(REAL.class) )
			field = column.fieldName();
		else
			field = "CAST(" + column.fieldName() + " as REAL)";
		
		return "SELECT SUM(" + field + ") AS " + column.fieldName() + "_sum  FROM " + Columns.findTableName( type );
	}

	
	public static <T> String dropTable(final Class<T> type) {
		return "DROP TABLE IF EXISTS " + Columns.findTableName( type ) + ";";
	}
	
	/**
	 * Gibt einen generierten SQL-String zurück.
	 * 
	 * @param type
	 * @param values
	 * 
	 * @return
	 */
	public static <T extends Column> String createTable(final Class<T> type,
			final T[] values) {

		final StringBuilder builder = new StringBuilder("CREATE TABLE ");
		builder.append(Columns.findTableName(type));
		builder.append( " " );
		builder.append('(');

		boolean isFirst = true;

		for (final T value : values) {
			if (!isFirst)
				builder.append(',');

			builder.append(" ");
			builder.append(value.fieldName());
			builder.append(" ");
			builder.append(value.getSQLType().declaration());
			builder.append(buildAutoIncrementExpr(value));

			isFirst = false;
		}

		builder.append(')');

		return builder.toString();
	}

	public static String buildAutoIncrementExpr( final Column column ) {
		final SQLiteType type = column.getSQLType();
		if( !( type instanceof AutoIncrementable) ) 
			return "";
		
        final AutoIncrementable auto = (AutoIncrementable) type;
        return auto.isAutoIncrement() ? " AUTOINCREMENT" : "";
	}

}
