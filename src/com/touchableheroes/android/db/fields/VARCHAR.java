package com.touchableheroes.android.db.fields;


public class VARCHAR extends SQLiteType {

	private int length;

	public VARCHAR(final int length) {
		this.length = length;
	}

	@Override
	public String declaration() {
		return super.declaration() + "(" + this.length + ")";
	}
}
