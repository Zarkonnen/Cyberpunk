package com.zarkonnen.cyberpunk.interaction;

import com.zarkonnen.cyberpunk.Item;
import com.zarkonnen.cyberpunk.Person;
import com.zarkonnen.cyberpunk.Tile;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Buy extends AbstractInteraction<Person> implements ItemInteraction {
	public final Item item;

	public Buy(Item item, Person actor, Person target) {
		super(actor, target);
		this.item = item;
	}
	
	@Override
	public String getName() {
		return "Buy " + item.getName() + " ($" + item.type.value + ")";
	}

	@Override
	public String disabledReason() {
		if (actor().money < item.type.value) {
			return "You can't afford it.";
		}
		if (actor().reputation < target().minDealRep) {
			return "They refuse to do business with you.";
		}
		return null;
	}

	@Override
	public String getDescription() {
		return "Buy " + item.getName() + " from " + target().getName() + " for $" + item.type.value;
	}

	@Override
	public String run() {
		exhaust(1);
		actor().inventory.add(item);
		actor().money -= item.type.value;
		if (!item.type.data) {
			if (target().inventory.contains(item)) {
				target().inventory.remove(item);
				target().money += item.type.value;
			} else {
				target().money += item.type.value / 8; // Sales commission, basically.
				for (Tile.HiddenItem hi : target().location().hiddenItems) {
					if (hi.item == item) {
						target().location().hiddenItems.remove(hi);
						break;
					}
				}
			}
		}
		return null;//"You buy the " + item.getName() + ".";
	}

	@Override
	public Item getItem() {
		return item;
	}
	
	public static class F implements InteractionFactory<Person, Buy> {
		@Override
		public List<Buy> make(Person actor, Person t) {
			if (t.unconscious()) { return Collections.emptyList(); }
			ArrayList<Buy> l = new ArrayList<Buy>();
			for (Item item : t.inventory) {
				if (t.willSell(item)) {
					l.add(new Buy(item, actor, t));
				}
			}
			if (t.workplace == t.location()) {
				for (Tile.HiddenItem item : t.location().hiddenItems) {
					if (t.willSell(item.item)) {
						l.add(new Buy(item.item, actor, t));
					}
				}
			}
			return l;
		}
	}
}
