package com.touchableheroes.android.db.fields;


public interface Column {
	
	public SQLiteType getSQLType();
	
	public String fieldName();
	
}
