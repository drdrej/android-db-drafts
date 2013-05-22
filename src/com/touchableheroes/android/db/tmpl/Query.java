package com.touchableheroes.android.db.tmpl;

import java.util.Map;
import java.util.Map.Entry;

/**
 * @author Andreas Siebert, ask@touchableheroes.com
 */
public class Query {

	private StringBuilder buffer;
	
	public Query(final String tmpl) {
		this.buffer = new StringBuilder(tmpl);
	}
	
	public Query append( final String id, final Object value) {
		final String valueStr = fixValue(value);
		final String key = 	"{" + id + "}";
		
		replaceAll( key, valueStr);
		
		return this;
	}
	
	public Query append( final Map<String, Object> values) {
		for (final Entry<String, Object> entry : values.entrySet()) {
			append( entry.getKey(), entry.getValue() );
		}
		
		return this;
	}
	
	
	public String sql() {
		return this.buffer.toString();
	}
	
	private void replaceAll(final String from, final String to)
	{
	    int index = buffer.indexOf(from);
	    
	    while (index > -1)
	    {
	    	final int startPos = index;
	    	final int endPos = index + from.length();
	        buffer.replace(startPos, endPos, to);
	        
	        final int behindReplacePos = (startPos + to.length()); 
	        index = buffer.indexOf(from, behindReplacePos);
	    }
	}
	
	private String fixValue(final Object value) {
		final String str;

		if (value instanceof String)
			str = (String) value;
		else
			str = String.valueOf(value);

		return str.replace("'", "''");
	}
}
