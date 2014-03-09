package com.zarkonnen.cyberpunk.interaction;

import com.zarkonnen.cyberpunk.Item;
import com.zarkonnen.cyberpunk.Person;
import com.zarkonnen.cyberpunk.StringList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class Loot extends AbstractInteraction<Person> {
	public Loot(Person actor, Person target) {
		super(actor, target);
	}

	@Override
	public String getName() {
		return "Loot " + target().getName();
	}

	@Override
	public String disabledReason() {
		return null;
	}

	@Override
	public String getDescription() {
		return "Rifle through their pockets for any valuables.";
	}

	@Override
	public String run() {
		StringList things = new StringList();
		for (Iterator<Item> it = target().inventory.iterator(); it.hasNext();) {
			Item item = it.next();
			if (!item.type.data) {
				actor().inventory.add(item);
				things.add(item);
				it.remove();
			}
		}
		
		if (things.isEmpty()) {
			decreaseRep(2);
			return "You look for valuables, but find none.";
		} else {
			decreaseRep(10);
			return "You find " + things + ".";
		}
	}
	
	public static final class F implements InteractionFactory<Person, Loot> {
		@Override
		public List<Loot> make(Person actor, Person t) {
			if (!t.unconscious()) { return Collections.emptyList(); }
			return Collections.singletonList(new Loot(actor, t));
		}
	}
}
