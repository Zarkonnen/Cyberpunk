package com.zarkonnen.cyberpunk;

import com.zarkonnen.cyberpunk.interaction.Interaction;
import java.io.Serializable;
import java.util.ArrayList;

public class Item implements Serializable, HasName {
	public final ItemType type;
	public Tile keyFor;
	public Person blackmailFor;
	public Interaction<?> recordOf;
	public final ArrayList<Item> found = new ArrayList<Item>();
	public int age;
	
	public Item(ItemType type) {
		this.type = type;
	}

	@Override
	public String getName() {
		if (keyFor != null) {
			return (type.data ? "Password " : "Key ") + "for " + keyFor.getName();
		}
		if (blackmailFor != null) {
			return "Blackmail material on " + blackmailFor.getName();
		}
		return type.name;
	}
}
