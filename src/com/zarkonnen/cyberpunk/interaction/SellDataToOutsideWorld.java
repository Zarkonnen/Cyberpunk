package com.zarkonnen.cyberpunk.interaction;

import com.zarkonnen.cyberpunk.Item;
import com.zarkonnen.cyberpunk.Person;
import java.util.Collections;
import java.util.List;

public class SellDataToOutsideWorld extends AbstractInteraction<Item> implements ItemInteraction {
	public static final int MULT = 2;
	
	public SellDataToOutsideWorld(Person actor, Item target) {
		super(actor, target);
	}
	
	@Override
	public String getName() {
		return "Sell " + target().getName() + " to the outside world";
	}

	@Override
	public String disabledReason() {
		return null;
	}

	@Override
	public String getDescription() {
		return "Sell data.";
	}

	@Override
	public String run() {
		exhaust(3);
		actor().location().map.knownData.add(target());
		actor().money += target().type.value * MULT;
		return "Woo! Money.";
	}

	@Override
	public Item getItem() {
		return target();
	}
	
	public static class F implements InteractionFactory<Item, SellDataToOutsideWorld> {
		@Override
		public List<SellDataToOutsideWorld> make(Person actor, Item t) {
			if (actor.isPlayer || !t.type.data || actor.location().map.knownData.contains(t)) {
				return Collections.emptyList();
			}
			return Collections.singletonList(new SellDataToOutsideWorld(actor, t));
		}
	}
}
