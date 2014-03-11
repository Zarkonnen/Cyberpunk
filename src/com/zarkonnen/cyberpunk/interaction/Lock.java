package com.zarkonnen.cyberpunk.interaction;

import com.zarkonnen.cyberpunk.Item;
import com.zarkonnen.cyberpunk.Person;
import com.zarkonnen.cyberpunk.Tile;
import java.util.ArrayList;
import java.util.List;

public class Lock extends AbstractInteraction<Tile> implements ItemInteraction {
	private final Item key;

	public Lock(Person actor, Tile target, Item key) {
		super(actor, target);
		this.key = key;
	}

	@Override
	public String getName() {
		return "Lock this place down.";
	}

	@Override
	public String disabledReason() {
		if (target().locked) {
			return "This area is already locked.";
		}
		return null;
	}

	@Override
	public String getDescription() {
		return "Restrict access to this area with the keycard or password.";
	}

	@Override
	public String run() {
		exhaust(1);
		target().locked = true;
		return "No one else is getting in here now.";
	}

	@Override
	public Item getItem() {
		return key;
	}
	
	public static class F implements InteractionFactory<Tile, Lock> {
		@Override
		public List<Lock> make(Person actor, Tile t) {
			ArrayList<Lock> l = new ArrayList<Lock>();
			for (Item it : actor.inventory) {
				if (it.keyFor == t) {
					l.add(new Lock(actor, t, it));
				}
			}
			return l;
		}
	}
}
