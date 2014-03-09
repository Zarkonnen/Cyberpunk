package com.zarkonnen.cyberpunk.interaction;

import com.zarkonnen.cyberpunk.Item;
import com.zarkonnen.cyberpunk.Person;
import java.util.Collections;
import java.util.List;

public class Eat extends AbstractInteraction<Item> implements ItemInteraction {
	public Eat(Person actor, Item target) {
		super(actor, target);
	}
	
	
	@Override
	public String getName() {
		return "Eat " + target().getName();
	}

	@Override
	public String disabledReason() {
		if (actor().hunger == 0) {
			return "You're not hungry.";
		}
		return null;
	}

	@Override
	public String description() {
		return "It may be time for a snack.";
	}

	@Override
	public String run() {
		actor().inventory.remove(target());
		actor().hunger = Math.max(0, actor().hunger - target().type.food);
		return "You eat the delicious " + target().getName() + ".";
	}

	@Override
	public Item getItem() {
		return target();
	}
	
	public static class F implements InteractionFactory<Item, Eat> {
		@Override
		public List<Eat> make(Person actor, Item t) {
			if (t.type.food == 0) { return Collections.emptyList(); }
			return Collections.singletonList(new Eat(actor, t));
		}
	}
}
