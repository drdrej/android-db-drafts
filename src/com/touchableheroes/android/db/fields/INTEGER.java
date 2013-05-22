package com.touchableheroes.android.db.fields;


public class INTEGER extends SQLiteType implements AutoIncrementable {

	private boolean autoIncrement;

	public INTEGER(){
		this(false, false);
	}
	
	public INTEGER( final boolean isPrimary ) {
		this(isPrimary, false);
	}
	
    public INTEGER(final boolean isPrimary, final boolean autoIncrement){
		super(isPrimary);
		this.autoIncrement = autoIncrement;
	}
    
    public boolean isAutoIncrement() {
		return autoIncrement;
	}
}
