package com.touchableheroes.android.db.fields;

/**
 * Eine einfache Darstellung eines SQLite-Typen.
 * 
 * @author asiebert
 *
 */
public class SQLiteType {

	private boolean isPrimary;

	public SQLiteType(final boolean isPrimary) {
		this.isPrimary = isPrimary;
	}
	
	public SQLiteType() {
		this(false);
	}
	
	public String typeName() {
		final Class<? extends SQLiteType> type = this.getClass();
		return type.getSimpleName();
	}
	
	public String declaration() {
		if( !isPrimary() )
			return this.typeName();
		
		return this.typeName() + " PRIMARY KEY";
	}
	
	/**
	 * Ein Feld kann PRIMARY sein.
	 * 
	 * @return
	 */
	public boolean isPrimary() {
		return this.isPrimary;
	}
	
	
	public boolean is( final Class<? extends SQLiteType> otherType ) {
		return ( this.getClass() == otherType );
	}
	
}
