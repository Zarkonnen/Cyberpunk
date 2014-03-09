package com.zarkonnen.cyberpunk;

import java.io.Serializable;

public class Item implements Serializable, HasName {
	public final ItemType type;
	public Tile keyFor;
	public Person blackmailFor;

	public Item(ItemType type) {
		this.type = type;
	}

	@Override
	public String getName() {
		if (keyFor != null) {
			return "Key for " + keyFor.getName();
		}
		if (blackmailFor != null) {
			return "Blackmail material on " + blackmailFor.getName();
		}
		return type.name;
	}
}
