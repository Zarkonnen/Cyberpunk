package com.zarkonnen.cyberpunk.interaction;

import com.zarkonnen.cyberpunk.Item;
import com.zarkonnen.cyberpunk.Person;
import com.zarkonnen.cyberpunk.Tile;
import java.util.ArrayList;
import java.util.List;

public class Unlock extends AbstractInteraction<Tile> implements ItemInteraction {
	private final Item key;

	public Unlock(Person actor, Tile target, Item key) {
		super(actor, target);
		this.key = key;
	}

	@Override
	public String getName() {
		return "Crack this place open.";
	}

	@Override
	public String disabledReason() {
		if (!target().locked) {
			return "This area is already unlocked.";
		}
		return null;
	}

	@Override
	public String getDescription() {
		return "Allow access to this area with the key or password.";
	}

	@Override
	public String run() {
		exhaust(1);
		target().locked = false;
		return "Now you -- and anyone else -- can do what you like here.";
	}

	@Override
	public Item getItem() {
		return key;
	}
	
	public static class F implements InteractionFactory<Tile, Unlock> {

		@Override
		public List<Unlock> make(Person actor, Tile t) {
			ArrayList<Unlock> l = new ArrayList<Unlock>();
			for (Item it : actor.inventory) {
				if (it.keyFor == t) {
					l.add(new Unlock(actor, t, it));
				}
			}
			return l;
		}
	}
}
