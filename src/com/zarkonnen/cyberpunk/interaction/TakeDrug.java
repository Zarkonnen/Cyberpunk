package com.zarkonnen.cyberpunk.interaction;

import com.zarkonnen.cyberpunk.Item;
import com.zarkonnen.cyberpunk.Person;
import java.util.Collections;
import java.util.List;

public class TakeDrug extends AbstractInteraction<Item> implements ItemInteraction {
	public TakeDrug(Person actor, Item target) {
		super(actor, target);
	}
	
	@Override
	public String getName() {
		return "Take " + target().getName();
	}

	@Override
	public String disabledReason() {
		return null;
	}

	@Override
	public String getDescription() {
		return "Drugs are good for you!";
	}

	@Override
	public String run() {
		actor().drugsTaken.add(new Person.Drug(target()));
		actor().inventory.remove(target());
		return "You take the " + target().getName() + ".";
	}

	@Override
	public Item getItem() {
		return target();
	}

	public static class F implements InteractionFactory<Item, TakeDrug> {
		@Override
		public List<TakeDrug> make(Person actor, Item t) {
			if (t.type.drugDuration > 0) {
				return Collections.singletonList(new TakeDrug(actor, t));
			} else {
				return Collections.emptyList();
			}
		}
	}
}
