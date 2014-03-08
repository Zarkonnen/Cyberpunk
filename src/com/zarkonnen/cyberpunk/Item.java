package com.zarkonnen.cyberpunk;

import java.io.Serializable;

public class Item implements Serializable, HasName {
	public final ItemType type;

	public Item(ItemType type) {
		this.type = type;
	}

	@Override
	public String getName() {
		return type.name;
	}
}
