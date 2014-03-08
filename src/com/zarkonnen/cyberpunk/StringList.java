package com.zarkonnen.cyberpunk;

import java.util.ArrayList;

public class StringList {
	private final ArrayList<String> l = new ArrayList<String>();
	
	public StringList add(String s) {
		l.add(s);
		return this;
	}
	
	public StringList add(HasName o) {
		l.add(o.getName());
		return this;
	}
	
	public int size() { return l.size(); }
	public boolean isEmpty() { return l.isEmpty(); }
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		boolean first = true;
		for (String s : l) {
			if (!first) {
				sb.append(", ");
			}
			first = false;
			sb.append(s);
		}
		return sb.toString();
	}
}
