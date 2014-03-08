package com.zarkonnen.cyberpunk;

import java.io.Serializable;

public enum ItemType implements Serializable {
	RAM("RAM");
		
	public final String name;

	private ItemType(String name) {
		this.name = name;
	}
}
